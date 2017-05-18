package dfmDrone.gui;

import dfmDrone.utils.OpenCVUtils;
import com.google.zxing.Result;
import de.yadrone.base.video.ImageListener;
import dfmDrone.data.Config;
import static dfmDrone.utils.OpenCVUtils.findAndDrawEllipse;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import navigation.tools.DistanceMeaure;
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
            if(!controller.flying) {
                //Correct colors
                if (MenuPanel.colorOffset != null) {
                    WhiteBalance wb = new WhiteBalance(MenuPanel.colorOffset[0], MenuPanel.colorOffset[1], MenuPanel.colorOffset[2]);
                    Calibrator calib = new Calibrator(imageRaw, true);
                    wb.colorImage(calib.getImage());
                }
                
                //Analyse imageRaw
                OpenCVUtils.ImageAnalyticsModel imageAnalytics = findAndDrawEllipse(OpenCVUtils.bufferedImageToMat(imageRaw));
                BufferedImage imgAnalysed = (BufferedImage) OpenCVUtils.toBufferedImage(imageAnalytics.sourceImg);
                
                //Compute and show distance to portal if a portal is found
                if(imageAnalytics.rect != null) {
                    double distance = DistanceMeaure.getDistanceToObject(imageAnalytics.sourceImg.height(), imageAnalytics.rect.height, Config.portalHeight, Config.camConst);
                    controller.updateDistanceDisplay(distance);
                
//                centerVertical(rect.y, sourceImg.height());
                }
                
                //QR Scan
                Result qr = OpenCVUtils.QRScanner(imageRaw);
                if (qr != null)
                    System.out.println(qr.getText() + ": " + new Date().getSeconds());
                
                g.drawImage(imgAnalysed, 0, 0, getWidth(), getHeight(), null);
            }
            else
                g.drawImage(imageRaw, 0, 0, getWidth(), getHeight(), null);
        }
    }
    
    public void centerVertical(double objCenterY, double imageHeight) {
        controller.flying = true;
        double centerHeight = imageHeight/2;
        
        if(objCenterY - centerHeight > 10 || objCenterY - centerHeight < -10) {
            if(objCenterY > centerHeight) {
                System.out.println("up");
                controller.cmd.moveVertical(-50);
            }
            else {
                System.out.println("down");
                controller.cmd.moveVertical(50);
            }
        }
        controller.flying = false;
    }
    
    public void ortogonalPlacement(Point2D center, Dimension dim, double distance) {
        double aspect = dim.height / dim.width;
        //TODO
    }
}