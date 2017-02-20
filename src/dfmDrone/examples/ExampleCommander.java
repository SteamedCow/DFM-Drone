package dfmDrone.examples;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.LEDAnimation;

/**
 * ExampleCommander
 * @author Lasse
 * @version 17-02-2017
 */
public class ExampleCommander
{
    private final IARDrone drone;
    
    public ExampleCommander(IARDrone drone) {
        this.drone = drone;
    }
    
    public void animateLEDs() {
        drone.getCommandManager().setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);
    }
    
    public void takeOffAndLand(long hoverTime) {
        drone.getCommandManager().takeOff();
//        drone.getCommandManager().up(1);
        drone.getCommandManager().waitFor(hoverTime);
        drone.getCommandManager().landing();
//        drone.stop();
    }
}