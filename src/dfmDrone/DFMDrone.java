package dfmDrone;

import org.opencv.core.Core;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.NavDataManager;
import de.yadrone.base.video.xuggler.XugglerDecoder;
import dfmDrone.data.Config;
import dfmDrone.gui.GUIController;
import dfmDrone.listeners.AttitudeListener;
import dfmDrone.listeners.ErrorListener;

/**
 * DFMDrone
 * @author Lasse
 * @version 16-02-2017
 */
public class DFMDrone 
{
    public static GUIController guiController;
    
    public static void main(String[] args) {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        IARDrone drone;
        
        //Setup Drone
        System.out.println("\n---- Setup Drone ---");
        try {
            drone = new ARDrone(Config.droneAddress, new XugglerDecoder());
            drone.start();
            
            //Setup Error Listener
            System.out.println("\n---- Setup Error Listener ---");
            drone.addExceptionListener(new ErrorListener());
            
            //Set Configurations
            System.out.println("\n---- Set Configurations ---");
            drone.setMaxAltitude(Config.maxAltitude);
            drone.getCommandManager().setVideoCodecFps(Config.vidFPS);
            drone.getCommandManager().setVideoCodec(Config.vidCodec);
            
            //Setup Video Listener and controller
            System.out.println("\n---- Setup Video Listener & controller ---");
            guiController = new GUIController(drone);
            
            //Setup Attitude Listener
            System.out.println("\n---- Setup Attitude Listener ---");
            NavDataManager navDatMan = drone.getNavDataManager();
            System.out.println("Adding listener");
            navDatMan.addAttitudeListener(new AttitudeListener(guiController) {});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("\n --- END OF MAIN ---");
        }
    }
}