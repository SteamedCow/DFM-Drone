package dfmDrone.examples;

import de.yadrone.base.IARDrone;

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
        System.out.println("--- Hover Example Complete ---");
    }
}