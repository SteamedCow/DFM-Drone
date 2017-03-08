package dfmDrone.video;

import de.yadrone.base.IARDrone;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * VideoListener
 * @author Lasse
 * @version 17-02-2017
 */
public class VideoListener extends JFrame
{
    public VideoListener(final IARDrone drone) {
        super("YADrone Tutorial");
            
        //Setup GUI Window Listener
        System.out.println("\n---- Setup GUI Window Listener ---");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("\n--- GUI Closed ---");
                System.out.println("Stopping Drone");
                drone.stop();
                System.out.println("Closing Program");
                System.exit(0);
            }
        });
        
        //Add buttons
        System.out.println("Add buttons to gui");
        setContentPane(new MenuPanel(drone));
        
        setSize(1200, 600);
        setVisible(true);
    }
}