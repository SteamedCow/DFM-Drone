package dfmDrone;

import org.opencv.core.Core;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.NavDataManager;
import de.yadrone.base.video.xuggler.XugglerDecoder;
import dfmDrone.data.Config;
import dfmDrone.data.Data;
import dfmDrone.data.HSVHandler;
import dfmDrone.data.PropertyHandler;
import dfmDrone.data.PropertyHandler.PropertyLabel;
import dfmDrone.gui.Controller;
import dfmDrone.listeners.AttitudeListener;
import dfmDrone.listeners.ErrorListener;
import dfmDrone.utils.DFMLogger;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import org.apache.commons.io.FileUtils;
import org.jcodec.common.IOUtils;

/**
 * DFMDrone
 * @author Lasse
 * @version 16-02-2017
 */
public class DFMDrone
{
    public static Controller guiController;
    
    public static void main(String[] args) {
        DFMLogger.logger.log(Level.INFO, "Booting program");
        //Load properties
        DFMLogger.logger.log(Level.CONFIG, "Load properties");
        PropertyHandler propHandler = new PropertyHandler(Data.PROPERTIES_FILEPATH, true);
        DFMLogger.setLevel(Level.parse(propHandler.get(PropertyLabel.LoggerLevel)));
        //Load HSV settings
        DFMLogger.logger.log(Level.CONFIG, "Load hsv settings");
        HSVHandler hsvHandler = new HSVHandler(Data.HSVSETTINGS_FILEPATH, true);
        
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//            loadLibrary();
        }
        catch(UnsatisfiedLinkError e) {
            e.printStackTrace();
            DFMLogger.logger.log(Level.SEVERE, "Could not load libraries: {0}", e.getMessage());
            System.exit(2150);
        }
        IARDrone drone;
        
        //Setup Drone
        DFMLogger.logger.log(Level.CONFIG, "Setup Drone");
        try {
            drone = new ARDrone(propHandler.get(PropertyLabel.DroneIP), new XugglerDecoder());
            drone.start();
            
            //Setup Error Listener
            DFMLogger.logger.log(Level.CONFIG, "Setup Error Listener");
            drone.addExceptionListener(new ErrorListener());
            
            //Set Configurations
            DFMLogger.logger.log(Level.CONFIG, "Set Configurations");
            drone.getCommandManager().setOutdoor(Boolean.parseBoolean(propHandler.get(PropertyLabel.Outdoor)), Boolean.parseBoolean(propHandler.get(PropertyLabel.Hull)));
            drone.setMaxAltitude(Integer.parseInt(propHandler.get(PropertyLabel.MaxAltitude)));
            drone.setMinAltitude(Integer.parseInt(propHandler.get(PropertyLabel.MinAltitude)));
            drone.getCommandManager().setVideoCodecFps(Integer.parseInt(propHandler.get(PropertyLabel.VideoFrameRate)));
            drone.getCommandManager().setVideoCodec(Config.VIDEO_CODEC);
            Config.PORTAL_HEIGHT = Integer.parseInt(propHandler.get(PropertyLabel.PortalHeight));
            Config.CAMERA_CONSTANT = Double.parseDouble(propHandler.get(PropertyLabel.CameraConstant));
            
            //Setup Video Listener and controller
            DFMLogger.logger.log(Level.CONFIG, "Setup Video Listener & controller");
            guiController = new Controller(drone, propHandler, hsvHandler);
            
            //Setup Attitude Listener
            DFMLogger.logger.log(Level.CONFIG, "Setup Attitude Listener");
            NavDataManager navDatMan = drone.getNavDataManager();
            DFMLogger.logger.log(Level.CONFIG, "Adding listener");
            navDatMan.addAttitudeListener(new AttitudeListener(guiController) {});
        }
        catch (Exception e) {
            e.printStackTrace();
            DFMLogger.logger.log(Level.SEVERE, "Could not run main: {0}", e.getMessage());
        }
        finally {
            DFMLogger.logger.log(Level.CONFIG, "END OF MAIN");
        }
    }
    
    private static void loadLibrary() {
        try {
            InputStream in = null;
            File fileOut = null;
            String osName = System.getProperty("os.name");
            DFMLogger.logger.log(Level.CONFIG, "OS detected: {0}", osName);
            if(osName.startsWith("Windows")){
                int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
                if(bitness == 32){
                    DFMLogger.logger.config("32 bit detected");
                    in = DFMDrone.class.getResourceAsStream("libs/opencv support/Windows 8.1/x86/opencv_java320.dll");
                    fileOut = File.createTempFile("lib", ".dll");
                }
                else if (bitness == 64){
                    DFMLogger.logger.config("64 bit detected");
                    in = DFMDrone.class.getResourceAsStream("opencv_java320.dll");
                    fileOut = File.createTempFile("lib", ".dll");
                }
                else{
                    DFMLogger.logger.warning("Unknown bit detected - trying with 32 bit");
                    in = DFMDrone.class.getResourceAsStream("/opencv/x86/opencv_java245.dll");
                    fileOut = File.createTempFile("lib", ".dll");
                }
            }
            else if(osName.equals("Mac OS X")){
                in = DFMDrone.class.getResourceAsStream("/opencv/mac/libopencv_java245.dylib");
                fileOut = File.createTempFile("lib", ".dylib");
            }
            
            
            OutputStream out = FileUtils.openOutputStream(fileOut);
            System.out.println("in=" + in);
            System.out.println("out=" + out);
            IOUtils.copy(in, out);
            in.close();
            out.close();
            System.load(fileOut.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load opencv native library", e);
        }
    }
}