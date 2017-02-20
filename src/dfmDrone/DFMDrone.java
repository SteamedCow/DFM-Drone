package dfmDrone;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.NavDataManager;
import de.yadrone.base.video.xuggler.XugglerDecoder;
import dfmDrone.examples.ExampleHover;
import dfmDrone.video.VideoListener;

/**
 * DFMDrone
 * @author Lasse
 * @version 16-02-2017
 */
public class DFMDrone 
{
    public static void main(String[] args) {
        IARDrone  drone;
        XugglerDecoder vidDecoder = new XugglerDecoder();
        
        //Setup Drone
        System.out.println("\n---- Setup Drone ---");
        try {
            drone = new ARDrone("192.168.1.1", vidDecoder);
            drone.start();
            drone.setMaxAltitude(2);
            
            //Setup Error Listener
            System.out.println("\n---- Setup Error Listener ---");
            drone.addExceptionListener((ARDroneException e) -> {
                System.err.println("[ERROR LISTENER]: " + e.getMessage());
                e.printStackTrace();
            });
            
            //Setup Attitude Listener
            System.out.println("\n---- Setup Attitude Listener ---");
            NavDataManager navDatMan = drone.getNavDataManager();
            System.out.println("Adding listener");
            navDatMan.addAttitudeListener(new AttitudeListener() {
                @Override
                public void attitudeUpdated(float pitch, float roll, float yaw) {
//                    System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw);
                }
                
                @Override
                public void attitudeUpdated(float pitch, float roll) { }
                
                @Override
                public void windCompensation(float pitch, float roll) { }
            });
            
            //Setup Video Listener
            System.out.println("\n---- Setup Video Listener ---");
            VideoListener videoListener = new VideoListener(drone);
            
            //Start Flight Example
            System.out.println("\n---- Start Flight Example ---");
            ExampleHover hoverEaxmple = new ExampleHover(drone);
            hoverEaxmple.start(10000, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("\n --- END OF MAIN ---");
//            if (drone != null) {
//                System.out.println("\n--- Stoppig Drone ---");
//                drone.stop();
//            }
//            else {
//                System.out.println("\n--- Closing Program ---");
//                System.exit(0);
//            }
        }
    }
}