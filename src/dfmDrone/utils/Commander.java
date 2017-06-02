package dfmDrone.utils;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.LEDAnimation;
import dfmDrone.gui.GUIController;

/**
 * Commander
 * @author Lasse
 * @version 17-02-2017
 */
public class Commander
{
    private final IARDrone drone;
    private final CommandManager dCmd;
    private final GUIController controller;
    private boolean block = false;
    
    public Commander(GUIController controller) {
        this.controller = controller;
        drone = this.controller.getDrone();
        dCmd = drone.getCommandManager();
    }
    
    public void animateLEDs(int duration) {
        if(!block) {
            controller.updateLastCMDDisplay("LEDS");
            dCmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, duration);
        }
    }
    
    public void takeOffAndLand(long hoverTime) {
        block = false;
        
        controller.updateLastCMDDisplay("TAKE OFF AND LAND");
        
        controller.droneFlying = true;
        controller.setBusy(true);
        
        dCmd.takeOff();
        dCmd.waitFor(hoverTime);
        dCmd.landing();
        
        controller.droneFlying = false;
        controller.setBusy(false);
    }
    
    public void takeOff() {
        block = false;
        
        controller.updateLastCMDDisplay("TAKE OFF");
        controller.droneFlying = true;
        controller.setBusy(true);
        dCmd.takeOff();
        dCmd.hover();
        
        controller.setBusy(false);
    }
    
    public void scan(){
        if(!block) {
            controller.updateLastCMDDisplay("SCAN");
            dCmd.spinLeft(15);
        }
    }
    
    public void moveVertical(int val) {
        if(!block) {
            controller.updateLastCMDDisplay("MOVE VERTICAL " + val);
            controller.setBusy(true);
            if(val > 0)
                dCmd.up(val);
            else
                dCmd.down(val);
            
            controller.setBusy(false);
        }
        
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//        }
//        dCmd.hover();
    }
    
    public boolean land() {
        block = true;
        controller.updateLastCMDDisplay("LAND");
        controller.setBusy(true);
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
            controller.setBusy(false);
            controller.droneFlying = false;
        }
        return success;
    }
    
    public void kill() {
        block = true;
        controller.setBusy(true);
        dCmd.emergency();
        
        controller.setBusy(false);
        controller.droneFlying = false;
        controller.updateLastCMDDisplay("KILL");
    }
}