package dfmDrone.listeners;

import dfmDrone.gui.GUIController;
import dfmDrone.utils.DFMLogger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

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
        DFMLogger.logger.config("Setup GUI Window Listener");
        this.controller = controller;
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("\n--- GUI Closed ---");
        DFMLogger.logger.log(Level.INFO, "GUI Closed");
        DFMLogger.logger.fine("Stopping Drone");
        controller.getDrone().stop();
        DFMLogger.logger.fine("Closing Program");
        DFMLogger.close();
        System.exit(0);
    }
}