package dfmDrone;

import org.opencv.core.Core;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.NavDataManager;
import de.yadrone.base.video.xuggler.XugglerDecoder;
import dfmDrone.data.Config;
import dfmDrone.data.Data;
import dfmDrone.data.PropertyHandler;
import dfmDrone.data.PropertyHandler.PropertyLabel;
import dfmDrone.gui.GUIController;
import dfmDrone.listeners.AttitudeListener;
import dfmDrone.listeners.ErrorListener;
import dfmDrone.utils.DFMLogger;
import java.util.logging.Level;

/**
 * DFMDrone
 * @author Lasse
 * @version 16-02-2017
 */
public class DFMDrone 
{
    public static GUIController guiController;
    
    public static void main(String[] args) {
        DFMLogger.logger.log(Level.INFO, "Booting program");
        //Load properties
        DFMLogger.logger.log(Level.CONFIG, "Load properties");
        PropertyHandler propHandler = new PropertyHandler(Data.PROPERTIES_FILEPATH, true);
        DFMLogger.setLevel(Level.parse(propHandler.get(PropertyLabel.LoggerLevel)));
        
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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
            
            //Setup Video Listener and controller
            DFMLogger.logger.log(Level.CONFIG, "Setup Video Listener & controller");
            guiController = new GUIController(drone, propHandler);
            
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
}