package dfmDrone.listeners;

import dfmDrone.gui.GUIController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * GUIWindowListener
 * @author SteamedCow
 * @version 18-05-2017
 */
public class GUIWindowListener extends WindowAdapter
{
    private final GUIController controller;

    public GUIWindowListener(GUIController controller) {
        //Setup GUI Window Listener
        System.out.println("\n---- Setup GUI Window Listener ---");
        this.controller = controller;
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("\n--- GUI Closed ---");
        System.out.println("Stopping Drone");
        controller.getDrone().stop();
        System.out.println("Closing Program");
        System.exit(0);
    }
}