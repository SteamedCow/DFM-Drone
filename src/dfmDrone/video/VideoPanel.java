package dfmDrone.video;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoChannel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * VideoPanel
 * @author Lasse
 * @version 08-03-2017
 */
public class VideoPanel extends JPanel
{
    private BufferedImage image = null;
    
    public VideoPanel(IARDrone drone) {
        setSize((int) (640*1.5), (int) (360*1.5));
        setBackground(Color.WHITE);
        
        //Setup Camera Switch Listener
        System.out.println("\n---- Setup Camera Switch Listener ---");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drone.getCommandManager().setVideoChannel(VideoChannel.NEXT);
            }
        });
        
        //Setup Image Listener
        System.out.println("\n---- Setup Image Listener ---");
        drone.getVideoManager().addImageListener((BufferedImage newImage) -> {
            image = newImage;
            SwingUtilities.invokeLater(() -> {
                repaint();
            });
        });
    }
    
    @Override
    public void paint(Graphics g) {
        if (image != null)
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}