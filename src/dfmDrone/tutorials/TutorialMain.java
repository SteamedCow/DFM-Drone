package dfmDrone.tutorials;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.exception.ARDroneException;

public class TutorialMain
{
    public static void main(String[] args) {
        IARDrone drone = null;
        try {
            // Tutorial Section 1
            drone = new ARDrone();
            drone.addExceptionListener((ARDroneException e) -> {
                e.printStackTrace();
            });
            
            drone.start();
            
            // Tutorial Section 2
//            new TutorialAttitudeListener(drone);
            
            // Tutorial Section 3
//            new TutorialVideoListener(drone);
            
            // Tutorial Section 4
            TutorialCommander commander = new TutorialCommander(drone);
            commander.animateLEDs();
            commander.takeOffAndLand();
//            commander.leftRightForwardBackward();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (drone != null)
                drone.stop();            
            System.exit(0);
        }
    }
}
