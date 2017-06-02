package dfmDrone.gui;

import dfmDrone.listeners.BatteryListener;
import de.yadrone.base.IARDrone;
import dfmDrone.ai.AILogic;
import dfmDrone.data.PropertyHandler;
import dfmDrone.data.PropertyHandler.PropertyLabel;
import dfmDrone.listeners.CameraSwitchListener;
import dfmDrone.listeners.GUIWindowListener;
import dfmDrone.listeners.VideoListener;
import dfmDrone.ai.Commander;
import dfmDrone.utils.DFMLogger;
import dfmDrone.utils.OpenCVUtils.ImageAnalyticsModel;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import javax.swing.JFrame;

/**
 * GUIController
 * @author SteamedCow
 * @version 18-05-2017
 */
public class GUIController 
{
    public boolean droneFlying = false;
    private boolean droneBusy = false;
    
    private final PropertyHandler propHandler;
    private final MenuPanel menu;
    private final JFrame window;
    private final VideoPanel video;
    private final AILogic droneLogic;
    
    protected final Commander cmd;
    protected final IARDrone drone;
    
    public GUIController(IARDrone drone, PropertyHandler propHandler) {
        this.drone = drone;
        this.propHandler = propHandler;
        cmd = new Commander(this);
        droneLogic = new AILogic(this, cmd);
        
        video = new VideoPanel(this);
        video.setSize((int) (640 * 1.5), (int) (360 * 1.5));
        video.addVideoListener(new VideoListener(this));
        video.addCameraSwitchListener(new CameraSwitchListener(this));
        
        menu = new MenuPanel(this);
        menu.addVideoPanel(video);
        menu.addBatteryListener(new BatteryListener(this));
        
        window = new JFrame("Killer Drone");
        window.addWindowListener(new GUIWindowListener(this));
        window.setSize(1200, 600);
        window.setContentPane(menu);
        window.setVisible(true);
    }
    
    public boolean isBusy() {
        return droneBusy;
    }
    
    public void setBusy(boolean busy) {
        DFMLogger.logger.log(Level.FINER, "Busy: {0}", busy);
        droneBusy = busy;
        menu.updateBusy(busy);
    }
    
    public String getProperty(PropertyLabel propLabel) {
        return propHandler.get(propLabel);
    }

    public IARDrone getDrone() {
        return drone;
    }
    
    public BufferedImage getVideoFrame() {
        return video.imageRaw;
    }

    public void updateImage(BufferedImage image) {
        video.updateImage(image);
    }

    public void updateBatteryDisplay(int batteryLevel) {
        menu.updateBatteryDisplay(batteryLevel);
    }

    public void updateDistanceDisplay(double distance) {
        menu.updateDistanceDisplay(distance);
    }

    public void updateLastCMDDisplay(String command) {
        menu.updateLastCMDDisplay(command);
    }

    public void updateNavigationDisplay(float pitch, float roll, float yaw) {
        menu.updateNavigationDisplay(pitch, roll, yaw);
    }
    
    protected void computeFlight(ImageAnalyticsModel imageAnalytics) {
        droneLogic.compute(imageAnalytics);
    }
}