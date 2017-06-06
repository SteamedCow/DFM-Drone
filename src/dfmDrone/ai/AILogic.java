package dfmDrone.ai;

import com.google.zxing.Result;
import dfmDrone.ai.CommandQueue.Command;
import dfmDrone.data.Config;
import dfmDrone.data.PropertyHandler.PropertyLabel;
import dfmDrone.gui.GUIController;
import dfmDrone.utils.DFMLogger;
import dfmDrone.utils.OpenCVUtils;
import dfmDrone.utils.OpenCVUtils.ImageAnalyticsModel;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Date;
import navigation.tools.DistanceMeaure;

/**
 * AILogic
 * @author Lasse
 * @version 30-05-2017
 */
public class AILogic 
{
    private final GUIController controller;
    private final CommandQueue cmdQ;

    public AILogic(GUIController controller, CommandQueue cmdQ) {
        this.controller = controller;
        this.cmdQ = cmdQ;
    }
    
    public void compute(ImageAnalyticsModel imageAnalytics) {
        if(controller.cmdQ.isDroneFlying()) {
            //Compute and show distance to portal if a portal is found
            if(imageAnalytics.rect != null) {
                double distance = DistanceMeaure.getDistanceToObject(imageAnalytics.sourceImg.height(), imageAnalytics.rect.height, Config.PORTAL_HEIGHT, Double.parseDouble(controller.getProperty(PropertyLabel.CameraConstant)));
                controller.updateDistanceDisplay(distance);
                
                centerVertical(imageAnalytics.rect.y + imageAnalytics.rect.height/2, imageAnalytics.sourceImg.height());
            }
            
            //QR Scan
            Result qr = QRScan(controller.getVideoFrame());
            if (qr != null)
                System.out.println(qr.getText() + ": " + new Date().getSeconds());
        }
    }
    
    private Result QRScan(BufferedImage img) {
        if(img != null)
            return OpenCVUtils.QRScanner(img);
        else
            throw new NullPointerException("No image provided for QR scan");
    }
    
    private void centerVertical(double objCenterY, double imageHeight) {
        double centerHeight = imageHeight/2;
        
        if(objCenterY - centerHeight > 0 || objCenterY - centerHeight < -75) {
            if(objCenterY < centerHeight) {
                cmdQ.add(Command.MoveUp, 10, 500);
            }
            else {
                cmdQ.add(Command.MoveDown, 10, 500);
            }
        }
    }
    
    private void ortogonalPlacement(Point2D center, Dimension dim, double distance) {
        double aspect = dim.height / dim.width;
        //TODO
    }
}
