package dfmDrone;

import org.opencv.core.Core;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.NavDataManager;
import de.yadrone.base.video.xuggler.XugglerDecoder;
import dfmDrone.data.Config;
import dfmDrone.video.MenuPanel;
import dfmDrone.video.VideoListener;
import java.net.SocketTimeoutException;

/**
 * DFMDrone
 * @author Lasse
 * @version 16-02-2017
 */
public class DFMDrone 
{
    public static void main(String[] args) {
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        IARDrone  drone;
        
        //Setup Drone
        System.out.println("\n---- Setup Drone ---");
        try {
            drone = new ARDrone(Config.droneAddress, new XugglerDecoder());
            drone.start();
            
            //Setup Error Listener
            System.out.println("\n---- Setup Error Listener ---");
            drone.addExceptionListener((ARDroneException e) -> {
                System.err.println("[ERROR LISTENER]: " + e.getMessage());
            });
            
            //Set Configurations
            System.out.println("\n---- Set Configurations ---");
            drone.setMaxAltitude(Config.maxAltitude);
            drone.getCommandManager().setVideoCodecFps(Config.vidFPS);
            drone.getCommandManager().setVideoCodec(Config.vidCodec);
            
            //Setup Attitude Listener
            System.out.println("\n---- Setup Attitude Listener ---");
            NavDataManager navDatMan = drone.getNavDataManager();
            System.out.println("Adding listener");
            navDatMan.addAttitudeListener(new AttitudeListener() {
                @Override
                public void attitudeUpdated(float pitch, float roll, float yaw) {
                    MenuPanel.updateNavigationDisplay(pitch, roll, yaw);
//                    System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw);
                }
                
                @Override
                public void attitudeUpdated(float pitch, float roll) { }
                
                @Override
                public void windCompensation(float pitch, float roll) { }
            });
            
            //Setup Video Listener
            System.out.println("\n---- Setup Video Listener ---");
            new VideoListener(drone);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("\n --- END OF MAIN ---");
        }
    }
}