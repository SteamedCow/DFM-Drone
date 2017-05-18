package dfmDrone.gui;

import dfmDrone.listeners.BatteryListener;
import de.yadrone.base.IARDrone;
import dfmDrone.listeners.CameraSwitchListener;
import dfmDrone.listeners.GUIWindowListener;
import dfmDrone.listeners.VideoListener;
import dfmDrone.utils.Commander;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * GUIController
 * @author SteamedCow
 * @version 18-05-2017
 */
public class GUIController 
{
    public boolean flying = false;
    
    private final MenuPanel menu;
    private final JFrame window;
    private final VideoPanel video;
    
    protected final Commander cmd;
    protected final IARDrone drone;
    
    public GUIController(IARDrone drone) {
        this.drone = drone;
        cmd = new Commander(this);
        
        video = new VideoPanel(this);
        video.setSize((int) (640 * 1.5), (int) (360 * 1.5));
        video.setBackground(Color.WHITE);
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
}