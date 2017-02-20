package dfmDrone.tutorials;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoChannel;

public class TutorialVideoListener extends JFrame
{
    private BufferedImage image = null;
    
    public TutorialVideoListener(final IARDrone drone) {
        super("YADrone Tutorial");
        
        setSize(640,360);
        setVisible(true);
        
        drone.getVideoManager().addImageListener((BufferedImage newImage) -> {
            image = newImage;
            SwingUtilities.invokeLater(() -> {
                repaint();
            });
        });
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                drone.getCommandManager().setVideoChannel(VideoChannel.NEXT);
            }
        });
        
        // close the
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                drone.stop();
                System.exit(0);
            }
        });
    }
    
    @Override
    public synchronized void paint(Graphics g) {
        if (image != null)
            g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }
}