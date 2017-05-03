package dfmDrone.video;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoChannel;
import dfmDrone.data.Config;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

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
    
    protected BufferedImage image = null;
    protected BufferedImage Img2show = null;
//    private final Navigator nav;
//    private final DistanceMeaure dm;
    
//    private EllipseRotated_F64 portal;
    private double distance;
    Result result = null;
    
    public VideoPanel(IARDrone drone) {
        setSize((int) (640 * 1.5), (int) (360 * 1.5));
        setBackground(Color.WHITE);
        
        // Setup Navigators
//        nav = new Navigator();
//        dm = new DistanceMeaure();
        
        // Setup Camera Switch Listener
        System.out.println("\n---- Setup Camera Switch Listener ---");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drone.getCommandManager().setVideoChannel(VideoChannel.NEXT);
            }
        });
        
        // Setup Image Listener
        System.out.println("\n---- Setup Image Listener ---");
        drone.getVideoManager().addImageListener((BufferedImage newImage) -> {
            image = newImage;
            
            SwingUtilities.invokeLater(() -> {
                repaint();
            });
        });
    }
    
    @Override
    synchronized public void paint(Graphics g) {
//        portal = null;
        
        if (image != null) {
            
            if (MenuPanel.colorOffset != null) {
                WhiteBalance wb = new WhiteBalance(MenuPanel.colorOffset[0], MenuPanel.colorOffset[1],
                        MenuPanel.colorOffset[2]);
                Calibrator calib = new Calibrator(image, true);
                wb.colorImage(calib.getImage());
                
            }
            
            Mat sourceImg = findAndDrawEllipse(bufferedImageToMat(image));
            Img2show = (BufferedImage) toBufferedImage(sourceImg);
            
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
                if (result!=null)
                    System.out.println(result.getText() + ": " + new Date().getSeconds());
            } catch (Exception e) { }
            
            g.drawImage(Img2show, 0, 0, getWidth(), getHeight(), null);
        }
    }
    
    synchronized private Mat findAndDrawEllipse(Mat sourceImg) {
        Mat hsvImg = new Mat();
        Imgproc.cvtColor(sourceImg, hsvImg, Imgproc.COLOR_BGR2HSV);
        Mat lower_hue_range = new Mat();
        Mat upper_hue_range = new Mat();
        Core.inRange(hsvImg, new Scalar(0, 100, 45), new Scalar(15, 255, 255), lower_hue_range);
        Core.inRange(hsvImg, new Scalar(160, 100, 45), new Scalar(180, 255, 255), upper_hue_range);
        Mat red_hue_image = new Mat();
        Core.addWeighted(lower_hue_range, 1.0, upper_hue_range, 1.0, 0, red_hue_image);
//        Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
//        Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2, 2));
        
        Imgproc.blur(red_hue_image, red_hue_image, new Size(9, 9));
        // Imgproc.erode(red_hue_image, red_hue_image, erodeElement);
        // Imgproc.dilate(Binarized, Binarized, dilateElement);
        // init
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        
        // find contours
        Imgproc.findContours(red_hue_image, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
        // if any contour exist...
        MatOfPoint2f approxCurve = new MatOfPoint2f();
        
        // For each contour found
        MatOfPoint2f contour2fbest = null;
        RotatedRect rotatedrect;
        RotatedRect rotatedrectbest = null;
        double aspect;
        for (MatOfPoint contour : contours) {
            // Convert contours(i) from MatOfPoint to MatOfPoint2f
            if (contour.toArray().length > 5) {
                rotatedrect = Imgproc.fitEllipse(new MatOfPoint2f(contour.toArray()));
                aspect = rotatedrect.boundingRect().height / rotatedrect.boundingRect().width;
                if (aspect > 0.9 && aspect < 1.8 && rotatedrect.boundingRect().area() > 20000 &&rotatedrect.boundingRect().area()<300000) {
                    if (rotatedrectbest == null) {
                        rotatedrectbest = rotatedrect;
                        contour2fbest = new MatOfPoint2f(contour.toArray());
                    } else if (rotatedrectbest.boundingRect().area() < rotatedrect.boundingRect().area()) {
                        rotatedrectbest = rotatedrect;
                        contour2fbest = new MatOfPoint2f(contour.toArray());
                    }
                }
            }
        }
        try {
            if (contour2fbest != null && rotatedrectbest != null) {
                double approxDistance = Imgproc.arcLength(contour2fbest, true) * 0.02;
                Imgproc.approxPolyDP(contour2fbest, approxCurve, approxDistance, true);
                
                // Convert back to MatOfPoint
                MatOfPoint points = new MatOfPoint(approxCurve.toArray());
                
                // Get bounding rect of contour
                Rect rect = Imgproc.boundingRect(points);
                
                // draw enclosing rectangle (all same color, but you could use
                // variable i to make them unique)
                Imgproc.rectangle(sourceImg, rect.tl(), rect.br(), new Scalar(255, 0, 0), 1, 8, 0);
                Imgproc.ellipse(sourceImg, rotatedrectbest, new Scalar(255, 192, 203), 4, 8);
                
                //Compute and show distance to portal
                distance = DistanceMeaure.getDistanceToObject(image.getHeight(), rect.height, Config.portalHeight, Config.camConst);
                MenuPanel.updateDistanceDisplay(distance);
            }
        }
        catch (CvException e) {
            System.out.println("Ingen ellipse fundet: " + e);
        }
        return sourceImg;
    }
    
    public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
    
    public static Image toBufferedImage(Mat m) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1)
            type = BufferedImage.TYPE_3BYTE_BGR;
        
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }
}