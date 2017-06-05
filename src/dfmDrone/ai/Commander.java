package dfmDrone.ai;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.command.LEDAnimation;
import dfmDrone.gui.GUIController;
import dfmDrone.utils.DFMLogger;
import java.util.logging.Level;

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
    
    protected void animateLEDs(int duration) {
        controller.updateLastCMDDisplay("LEDS");
        DFMLogger.logger.fine("cmd - LED");
        dCmd.setLedsAnimation(LEDAnimation.BLINK_ORANGE, 3, duration);
    }
    
    protected void takeOffAndLand(long hoverTime) {
        controller.updateLastCMDDisplay("TAKE OFF AND LAND");
        DFMLogger.logger.info("cmd - Take off and land");
        
        controller.setBusy(true);
        
        dCmd.takeOff();
        dCmd.waitFor(hoverTime);
        dCmd.landing();
        
        controller.setBusy(false);
    }
    
    protected void takeOff() {
        controller.updateLastCMDDisplay("TAKE OFF");
        DFMLogger.logger.info("cmd - Take off");
        controller.setBusy(true);
        dCmd.takeOff();
        dCmd.waitFor(1000);
        sleep(1000);
        DFMLogger.logger.info("cmd - Take off - Complete");
        
        controller.setBusy(false);
    }
    
    protected boolean land() {
        controller.setBusy(true);
        
        controller.updateLastCMDDisplay("LAND");
        DFMLogger.logger.info("cmd - Land");
        boolean success = false;
        try {
            dCmd.landing();
            success = true;
        }
        catch (Exception e) {
            System.err.println("Could not land drone: " + e.getMessage());
            DFMLogger.logger.log(Level.WARNING, "Could not land drone: {0}", e.getMessage());
            e.printStackTrace();
            success = false;
        }
        finally {
            controller.setBusy(false);
        }
        return success;
    }
    
    protected void kill() {
        controller.setBusy(true);
        dCmd.emergency();
        
        controller.setBusy(false);
        controller.updateLastCMDDisplay("KILL");
        DFMLogger.logger.info("cmd - KILL");
    }
    
    protected void scan(){
        controller.updateLastCMDDisplay("SCAN");
        DFMLogger.logger.fine("cmd - Scan");
        dCmd.spinLeft(15);
    }
    
    /**
     * Move the drone up or down for a specific duration
     * @param speed
     *      Milimeters per second
     * @param duration
     *      Time in miliseconds
     */
    protected void moveVertival(int speed, int duration) {
        controller.updateLastCMDDisplay("MOVE VERTICAL " + speed);
        DFMLogger.logger.log(Level.FINE, "cmd - Move vertical for {0}s at {1}mm/s", new Integer[]{duration, speed});
        controller.setBusy(true);
        
        if(speed > 0 && duration > -1)
            dCmd.up(speed).down(duration);
        else if(speed > 0)
            dCmd.up(speed);
        else if(speed < 0 && duration > -1)
            dCmd.down(speed).down(duration);
        else
            dCmd.down(speed);
        
        controller.setBusy(false);
    }
    
    /**
     * Move the drone up or down
     * @param speed
     *      Milimeters per second
     */
    protected void moveVertical(int speed) {
        moveVertival(speed, -1);
    }
    
    private void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}