package dfmDrone.video;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoChannel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * VideoListener
 * @author Lasse
 * @version 17-02-2017
 */
public class VideoListener extends JFrame
{
    private BufferedImage image = null;
    
    public VideoListener(final IARDrone drone) {
        super("YADrone Tutorial");
        setSize((int) (640*1.5), (int) (360*1.5));
        setVisible(true);
        
        //Setup Image Listener
        System.out.println("\n---- Setup Image Listener ---");
        drone.getVideoManager().addImageListener((BufferedImage newImage) -> {
            image = newImage;
            SwingUtilities.invokeLater(() -> {
                repaint();
            });
        });
            
        //Setup Camera Switch Listener
        System.out.println("\n---- Setup Camera Switch Listener ---");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drone.getCommandManager().setVideoChannel(VideoChannel.NEXT);
            }
        });
            
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
    }
    
    @Override
    public void paint(Graphics g) {
        if (image != null)
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}