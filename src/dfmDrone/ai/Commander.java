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
    
    protected void takeOffAndLand(int hoverTime) {
        controller.setBusy(true);
        controller.updateLastCMDDisplay("TAKE OFF AND LAND");
        DFMLogger.logger.info("cmd - Take off and land");
        
        dCmd.takeOff();
        dCmd.waitFor(hoverTime);
        animateLEDs(hoverTime);
        sleep(hoverTime);
        dCmd.landing();
        
        controller.setBusy(false);
    }
    
    protected void takeOff() {
        controller.setBusy(true);
        controller.updateLastCMDDisplay("TAKE OFF");
        DFMLogger.logger.info("cmd - Take off");
        dCmd.takeOff();
        dCmd.waitFor(2000);
        sleep(2000);
        dCmd.up(2500).doFor(2000);
        dCmd.hover();
        sleep(2000);
        dCmd.setLedsAnimation(LEDAnimation.BLINK_RED, 1, 500);
        sleep(500);
        dCmd.hover();
        
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
            dCmd.waitFor(2000);
            sleep(2000);
            success = true;
        }
        catch (Exception e) {
            System.err.println("Could not land drone: " + e.getMessage());
            DFMLogger.logger.log(Level.WARNING, "Could not land drone: {0}", e.getMessage());
            controller.updateLogDisplay("Could not land drone: " + e.getMessage());
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
        
        controller.updateLastCMDDisplay("KILL");
        DFMLogger.logger.info("cmd - KILL");
        controller.setBusy(false);
    }
    
    /**
     * Rotate the drone arond itself for a specific duration and speed
     * <br>Negative speed value spins the drone anti-clockwise/left while a positive value spins it clockwise/right
     * @param speed
     *      Rotation in radians per second
     * @param duration
     *      Time in miliseconds
     */
    protected void rotate(int speed, int duration){
        controller.updateLastCMDDisplay("ROTATE " + speed + " for " + duration);
        DFMLogger.logger.log(Level.FINE, "cmd - Rotate for {0}ms at {1}mm/s", new Integer[]{duration, speed});
        controller.setBusy(true);
        
        if(speed > 0 && duration > 0)
            dCmd.spinRight(speed).doFor(duration);
        else if(speed > 0)
            dCmd.spinRight(speed);
        else if(speed <= 0 && duration > 0)
            dCmd.spinLeft(speed).doFor(duration);
        else
            dCmd.spinLeft(speed);
        
        sleep(duration);
        dCmd.hover();
        
        controller.setBusy(false);
    }
    
    protected void hover(int duration){
        controller.updateLastCMDDisplay("HOVER for" + duration);
        controller.setBusy(true);
        controller.updateLogDisplay("HOVER");
        
        if(duration == -1){
            dCmd.hover();
        }
        else{
            dCmd.hover().doFor(duration);
            sleep(duration);
        }
        
        controller.setBusy(false);
    }
    
    /**
     * Move the drone up or down for a specific duration
     * <br>Negative speed value moves the drone left while a positive value moves it right
     * @param speed
     *      Milimeters per second
     * @param duration
     *      Time in miliseconds
     */
    protected void moveHorizontal(int speed, int duration){
        controller.updateLastCMDDisplay("MOVE HORIZONTAL " + speed + " for " + duration);
        DFMLogger.logger.log(Level.FINE, "cmd - Move horizontal for {0}ms at {1}mm/s", new Integer[]{duration, speed});
        controller.setBusy(true);
        
        if(speed > 0 && duration > 0)
            dCmd.goRight(speed).doFor(duration);
        else if(speed > 0)
            dCmd.goRight(speed);
        else if(speed <= 0 && duration > 0)
            dCmd.goLeft(-speed).doFor(duration);
        else
            dCmd.goLeft(-speed);
        
        sleep(duration);
        dCmd.hover();
        
        controller.setBusy(false);
    }
    
     /**
     * Move the drone up or down for a specific duration
     * <br>Negative speed value moves the drone backwards while a positive value moves it forward
     * @param speed
     *      Milimeters per second
     * @param duration
     *      Time in miliseconds
     */
    protected void move(int speed, int duration){
        if(speed > 0){
        controller.updateLastCMDDisplay("MOVE FORWARD" + speed + " for " + duration);
        DFMLogger.logger.log(Level.FINE, "cmd - Move forward for {0}ms at {1}mm/s", new Integer[]{duration, speed});
        }
        else{
        controller.updateLastCMDDisplay("MOVE  BACKWARD" + speed + " for " + duration);
        DFMLogger.logger.log(Level.FINE, "cmd - Move backward for {0}ms at {1}mm/s", new Integer[]{duration, speed});    
        }
        controller.setBusy(true);
        
        if(speed > 0 && duration > 0)
            dCmd.forward(speed).doFor(duration);
        else if(speed > 0)
            dCmd.forward(speed);
        else if(speed <= 0 && duration > 0)
            dCmd.backward(-speed).doFor(duration);
        else
            dCmd.backward(-speed);
        
        sleep(duration);
        dCmd.hover();
        
        controller.setBusy(false);
    }
    
    /**
     * Move the drone up or down for a specific duration
     * <br>Negative speed value moves the drone down while a positive value moves it up
     * @param speed
     *      Milimeters per second
     * @param duration
     *      Time in miliseconds
     */
    protected void moveVertival(int speed, int duration) {
        controller.updateLastCMDDisplay("MOVE VERTICAL " + speed + " for " + duration);
        DFMLogger.logger.log(Level.FINE, "cmd - Move vertical for {0}ms at {1}mm/s", new Integer[]{duration, speed});
        controller.setBusy(true);
        
        if(speed > 0 && duration > 0)
            dCmd.up(speed).doFor(duration);
        else if(speed > 0)
            dCmd.up(speed);
        else if(speed <= 0 && duration > 0)
            dCmd.down(-speed).doFor(duration);
        else
            dCmd.down(-speed);
        
        sleep(duration);
        dCmd.hover();
        
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