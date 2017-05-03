package dfmDrone.examples;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.LEDAnimation;
import dfmDrone.data.Drone;
import dfmDrone.video.MenuPanel;

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
        MenuPanel.updateLastCMDDisplay("LEDS");
        dCmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, duration);
    }
    
    public void takeOffAndLand(long hoverTime) {
        MenuPanel.updateLastCMDDisplay("TAKE OFF AND LAND");
        Drone.flying = true;
        dCmd.takeOff();
        dCmd.waitFor(hoverTime);
        dCmd.landing();
        Drone.flying = false;
    }
    
    public void takeOff() {
        MenuPanel.updateLastCMDDisplay("TAKE OFF");
        Drone.flying = true;
        dCmd.takeOff();
        dCmd.hover();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
        }
        animateLEDs(5);
        System.out.println("FLY UP!");
        moveVertical(5000);
    }
    
    public void scan(){
        MenuPanel.updateLastCMDDisplay("SCAN");
    	dCmd.spinLeft(15);
    }
    
    public void moveVertical(int val) {
        MenuPanel.updateLastCMDDisplay("MOVE VERTICAL " + val);
        if(val > 0)
            dCmd.up(val);
        else
            dCmd.down(-val);
        
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//        }
//        dCmd.hover();
    }
    
    public boolean land() {
        MenuPanel.updateLastCMDDisplay("LAND");
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
        MenuPanel.updateLastCMDDisplay("KILL");
    }
}