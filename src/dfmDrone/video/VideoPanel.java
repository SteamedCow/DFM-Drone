package dfmDrone.video;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoChannel;
import dfmDrone.data.Config;
import georegression.struct.shapes.EllipseRotated_F64;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import navigation.tools.DistanceMeaure;
import navigation.tools.Navigator;
import whiteBalance.tools.Measure;

/**
 * VideoPanel
 * @author Lasse
 * @version 08-03-2017
 */
public class VideoPanel extends JPanel
{
    private BufferedImage image = null;
    private final Navigator nav;
    private Measure ms;
    private final DistanceMeaure dm;
    
    private EllipseRotated_F64 portal;
    private double distance;
    
    public VideoPanel(IARDrone drone) {
        setSize((int) (640*1.5), (int) (360*1.5));
        setBackground(Color.WHITE);
        
        //Setup Navigators
        System.out.println("\n---- Setup Camera Switch Listener ---");
        nav = new Navigator();
        dm = new DistanceMeaure();
        
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
        portal = null;
        if(image != null) {
            ms = new Measure(image);
            try {
                portal = ms.findMaxEllipse(true);
                if(portal != null) {
                    nav.flyToPortal(portal, image, true);
                    distance = DistanceMeaure.getDistanceToObject(image.getHeight(), (portal.a * 2 + portal.b * 2) / 2, Config.portalHeight, 1.6404040404040405);
                    MenuPanel.updateDistanceDisplay(distance);
                }
            } catch (Exception e) { }
            
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    }
}