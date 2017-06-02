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
    
    public Commander(GUIController controller) {
        this.controller = controller;
        drone = this.controller.getDrone();
        dCmd = drone.getCommandManager();
    }
    
    public void animateLEDs(int duration) {
        controller.updateLastCMDDisplay("LEDS");
        dCmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, duration);
    }
    
    public void takeOffAndLand(long hoverTime) {
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
        controller.updateLastCMDDisplay("TAKE OFF");
        controller.droneFlying = true;
        controller.setBusy(true);
        dCmd.takeOff();
        dCmd.hover();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }        
        controller.setBusy(false);
    }
    
    public void scan(){
        controller.updateLastCMDDisplay("SCAN");
    	dCmd.spinLeft(15);
    }
    
    public void moveVertical(int val) {
        controller.updateLastCMDDisplay("MOVE VERTICAL " + val);
        controller.setBusy(true);
        if(val > 0)
            dCmd.up(val);
        else
            dCmd.down(-val);
        
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//        }
//        dCmd.hover();
        controller.setBusy(false);
    }
    
    public boolean land() {
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
        controller.setBusy(true);
        dCmd.emergency();
        
        controller.setBusy(false);
        controller.droneFlying = false;
        controller.updateLastCMDDisplay("KILL");
    }
}