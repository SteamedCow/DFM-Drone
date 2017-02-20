package dfmDrone.examples;

import de.yadrone.apps.tutorial.TutorialCommander;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.LEDAnimation;

/**
 * ExampleHover
 * @author Lasse
 * @version 17-02-2017
 */
public class ExampleHover 
{
    private final IARDrone drone;

    public ExampleHover(IARDrone drone) {
        this.drone = drone;
    }
    
    public void start(long hoverTime, boolean ledAnimation) {
        System.out.println("\n--- Starting Hover Example ---");
        ExampleCommander commander = new ExampleCommander(drone);
        if(ledAnimation)
            commander.animateLEDs();
        
        commander.takeOffAndLand(hoverTime);
        
//        if(ledAnimation)
//            drone.getCommandManager().setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, 10);
//        
//        drone.getCommandManager().takeOff();
//        drone.getCommandManager().waitFor(hoverTime);
//        drone.getCommandManager().landing();
        System.out.println("--- Hover Example Complete ---");
    }
}