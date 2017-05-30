package dfmDrone.gui;

import dfmDrone.utils.OpenCVUtils;
import de.yadrone.base.video.ImageListener;
import static dfmDrone.utils.OpenCVUtils.findAndDrawEllipse;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import whiteBalance.tools.Calibrator;
import whiteBalance.tools.WhiteBalance;

/**
 * VideoPanel
 *
 * @author Lasse
 * @version 08-03-2017
 */
public class VideoPanel extends JPanel {
    private final GUIController controller;
    protected BufferedImage imageRaw = null;
    
    public VideoPanel(GUIController controller) {
        this.controller = controller;
    }
    
    protected void addVideoListener(ImageListener videoListener) {
        // Setup Image Listener
        System.out.println("\n---- Setup Image Listener ---");
        controller.drone.getVideoManager().addImageListener(videoListener);
    }
    
    protected void addCameraSwitchListener(MouseAdapter cameraSwitchListener) {
        // Setup Camera Switch Listener
        System.out.println("\n---- Setup Camera Switch Listener ---");
        addMouseListener(cameraSwitchListener);
    }
    
    protected void updateImage(BufferedImage newImage) {
        imageRaw = newImage;
        
        SwingUtilities.invokeLater(() -> {
            repaint();
        });
    }
    
    @Override
    synchronized public void paint(Graphics g) {
        if (imageRaw != null) {
            //Correct colors
            if (MenuPanel.colorOffset != null) {
                WhiteBalance wb = new WhiteBalance(MenuPanel.colorOffset[0], MenuPanel.colorOffset[1], MenuPanel.colorOffset[2]);
                Calibrator calib = new Calibrator(imageRaw, true);
                wb.colorImage(calib.getImage());
            }
            
            //Analyse imageRaw
            OpenCVUtils.ImageAnalyticsModel imageAnalytics = findAndDrawEllipse(OpenCVUtils.bufferedImageToMat(imageRaw));
            
            //compute flight
            if(imageAnalytics != null) {
                controller.computeFlight(imageAnalytics);
                
//                //Compute and show distance to portal if a portal is found
//                if(imageAnalytics.rect != null) {
//                    double distance = DistanceMeaure.getDistanceToObject(imageAnalytics.sourceImg.height(), imageAnalytics.rect.height, Config.portalHeight, Config.camConst);
//                    controller.updateDistanceDisplay(distance);
//                    
//                    centerVertical(rect.y, sourceImg.height());
//                }
//                
//                //QR Scan
//                Result qr = OpenCVUtils.QRScanner(imageRaw);
//                if (qr != null)
//                    System.out.println(qr.getText() + ": " + new Date().getSeconds());
                
                //Draw analysed image
                BufferedImage imgAnalysed = (BufferedImage) OpenCVUtils.toBufferedImage(imageAnalytics.sourceImg);
                g.drawImage(imgAnalysed, 0, 0, getWidth(), getHeight(), null);
            }
            else
                g.drawImage(imageRaw, 0, 0, getWidth(), getHeight(), null);
        }
        else
            g.drawString("WAITING FOR INPUT..", this.getWidth()/2, this.getHeight()/2);
    }
}