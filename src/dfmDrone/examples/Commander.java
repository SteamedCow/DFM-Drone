package dfmDrone.examples;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.LEDAnimation;
import dfmDrone.data.Drone;

/**
 * Commander
 * @author Lasse
 * @version 17-02-2017
 */
public class Commander
{
    private final IARDrone drone;
    private final CommandManager dCmd;
    
    public Commander(IARDrone drone, CommandManager commandManager) {
        this.drone = drone;
        this.dCmd = commandManager;
    }
    
    public void animateLEDs(int duration) {
        dCmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, duration);
    }
    
    public void takeOffAndLand(long hoverTime) {
        Drone.flying = true;
        dCmd.takeOff();
        dCmd.waitFor(hoverTime);
        dCmd.landing();
        Drone.flying = false;
    }
    
    public void takeOff() {
        Drone.flying = true;
        dCmd.takeOff();
        dCmd.hover();
        
    }
    public void scan(){
    	dCmd.spinLeft(15);
    }
    
    public boolean land() {
        boolean success = false;
        try {
            dCmd.landing();
            success = true;
        } 
        catch (Exception e) {
            System.err.println("Could not land drone: " + e.getMessage());
            e.printStackTrace();
            success = false;
        }
        finally {
         //   dCmd.stop();
            Drone.flying = false;
        }
        return success;
    }
    
    public void kill() {
        dCmd.emergency();
        Drone.flying = false;
    }
}