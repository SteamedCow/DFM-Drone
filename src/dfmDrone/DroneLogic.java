package dfmDrone;

import com.google.zxing.Result;
import dfmDrone.data.PropertyHandler.PropertyLabel;
import dfmDrone.gui.GUIController;
import dfmDrone.utils.Commander;
import dfmDrone.utils.OpenCVUtils;
import dfmDrone.utils.OpenCVUtils.ImageAnalyticsModel;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Date;
import navigation.tools.DistanceMeaure;

/**
 * DroneLogic
 * @author Lasse
 * @version 30-05-2017
 */
public class DroneLogic 
{
    private final GUIController controller;
    private final Commander cmd;

    public DroneLogic(GUIController controller, Commander cmd) {
        this.controller = controller;
        this.cmd = cmd;
    }
    
    public void compute(ImageAnalyticsModel imageAnalytics) {
        if(!controller.droneBusy && controller.droneFlying) {
            //Compute and show distance to portal if a portal is found
            if(imageAnalytics.rect != null) {
                double distance = DistanceMeaure.getDistanceToObject(imageAnalytics.sourceImg.height(), imageAnalytics.rect.height, Integer.parseInt(controller.getProperty(PropertyLabel.PortalHeight)), Double.parseDouble(controller.getProperty(PropertyLabel.CameraConstant)));
                controller.updateDistanceDisplay(distance);
                
//                centerVertical(rect.y, sourceImg.height());
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
        controller.droneFlying = true;
        double centerHeight = imageHeight/2;
        
        if(objCenterY - centerHeight > 10 || objCenterY - centerHeight < -10) {
            if(objCenterY > centerHeight) {
                System.out.println("up");
                cmd.moveVertical(-50);
            }
            else {
                System.out.println("down");
                cmd.moveVertical(50);
            }
        }
        controller.droneFlying = false;
    }
    
    private void ortogonalPlacement(Point2D center, Dimension dim, double distance) {
        double aspect = dim.height / dim.width;
        //TODO
    }
}