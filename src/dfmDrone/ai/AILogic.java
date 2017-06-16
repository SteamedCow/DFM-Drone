package dfmDrone.ai;

import com.google.zxing.Result;
import dfmDrone.ai.CommandQueue.Command;
import dfmDrone.data.Config;
import dfmDrone.data.PropertyHandler.PropertyLabel;
import dfmDrone.gui.Controller;
import dfmDrone.utils.OpenCVUtils;
import dfmDrone.utils.OpenCVUtils.ImageAnalyticsModel;
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
    private final Controller controller;
    private final CommandQueue cmdQ;
    private double OldRatio = -1;
    private boolean movedRight;
    private boolean scan = false;


    public AILogic(Controller controller, CommandQueue cmdQ) {
        this.controller = controller;
        this.cmdQ = cmdQ;
    }
    
    public void compute(ImageAnalyticsModel imageAnalytics) {
//        if(controller.cmdQ.isDroneFlying()) {
            //Compute and show distance to portal if a portal is found
            if(imageAnalytics.rect != null) {
                scan = false;
                double distance = DistanceMeaure.getDistanceToObject(imageAnalytics.sourceImg.height(), imageAnalytics.rect.height, Config.PORTAL_HEIGHT, Double.parseDouble(controller.getProperty(PropertyLabel.CameraConstant)));
//                System.out.println("sourceImg.height(): "+imageAnalytics.sourceImg.height() +", sourceImg.width: " +imageAnalytics.sourceImg.width()+", rect.height: "+imageAnalytics.rect.height + ",portal height: "+ Config.PORTAL_HEIGHT+" Camera Constant: "+Double.parseDouble((controller.getProperty((PropertyLabel.CameraConstant)))));
                controller.updateDistanceDisplay(distance);
                

                    if(rotatePlacement(imageAnalytics.rect.x + imageAnalytics.rect.width/2, imageAnalytics.sourceImg.width())) {
                        if(centerHorizontal(imageAnalytics.rect.height, imageAnalytics.rect.width, imageAnalytics.sourceImg.width())) {
                            if(centerVertical(imageAnalytics.rect.y + imageAnalytics.rect.height/2, imageAnalytics.sourceImg.height())) {
                            controller.updateLogDisplay("-CENTERED-");
                            if(distance>= 2500){
                                cmdQ.add(Command.Forward, 15,600) ;
                            }
                            else {
                                cmdQ.add(Command.Forward, 15, (int) distance / 550 * 1000/2, CommandQueue.PushType.Block);
                                System.out.println("Distance: "+distance);
// cmdQ.add(Command.Land,-1,-1, CommandQueue.PushType.IgnoreBusy,CommandQueue.PushType.Block, CommandQueue.PushType.IgnoreBlock);
                            }
                        }
                    }
                }
            }
            else { //If no portal found
//                if(!scan) {
//                    controller.updateLogDisplay("Scanning");
//                    scan = true;
//                    cmdQ.add(Command.SpinLeft, 15, 500);
//                }
//                else {
//                    cmdQ.add(Command.SpinLeft, 7, 300);
//                }
            }
            
            //QR Scan
            Result qr = QRScan(controller.getVideoFrame());
            if (qr != null)
                System.out.println(qr.getText() + ": " + new Date().getSeconds());
//        }
    }
    
    private Result QRScan(BufferedImage img) {
        if(img != null)
            return OpenCVUtils.QRScanner(img);
        else
            throw new NullPointerException("No image provided for QR scan");
    }
    
    private boolean centerVertical(double objCenterY, double imageHeight) {
        double centerHeight = imageHeight/2;
        
        if(objCenterY - centerHeight > 25 || objCenterY - centerHeight < -25) {
            if(objCenterY < centerHeight)
                cmdQ.add(Command.MoveUp, 10, 500);
            else
                cmdQ.add(Command.MoveDown, 10, 500);
            
            return false;
        }
        return true;
    }
    
    private boolean centerHorizontal(double objHeight, double objWidth, double imageWidth){
        double ratio = objWidth/objHeight;

        if(ratio > 0.95){
            return true;
        }

        if(OldRatio == -1){
            
            cmdQ.add(Command.MoveRight, 8, 300);
            movedRight = true;
        }
        else{
            if(ratio > OldRatio){
                if(movedRight){
                    cmdQ.add(Command.MoveRight, 8, 300);
                }
                else{
                    cmdQ.add(Command.MoveLeft, 7, 300);
                    movedRight = false;
                }
            }
            else if(ratio < OldRatio){
                if(movedRight){
                    cmdQ.add(Command.MoveLeft, 8, 300);
                    movedRight = false;
                }
                else{
                    cmdQ.add(Command.MoveRight, 7, 300);
                    movedRight = true;
                }
            }
        }
        OldRatio = ratio;
        return false;
    }
    
    private boolean rotatePlacement(double objCenterX, double imageWidth) {
        double centerWidth = imageWidth/2;
        
        if(objCenterX - centerWidth > 50 || objCenterX - centerWidth < -50) {
            if(objCenterX < centerWidth) {
                cmdQ.add(Command.SpinLeft, 12, 200);
            }
            else {
                cmdQ.add(Command.SpinRight, 11, 200);
            }
            return false;
        }
        return true;
    }
}
