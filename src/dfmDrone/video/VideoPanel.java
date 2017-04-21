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
import georegression.struct.shapes.EllipseRotated_F64;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
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
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import navigation.tools.DistanceMeaure;
import navigation.tools.Navigator;
import whiteBalance.tools.Calibrator;
import whiteBalance.tools.Measure;
import whiteBalance.tools.WhiteBalance;

/**
 * VideoPanel
 * @author Lasse
 * @version 08-03-2017
 */
public class VideoPanel extends JPanel
{
	
    protected BufferedImage image = null;
   protected BufferedImage Img2show=null;
    private final Navigator nav;
    private Measure ms;
    private final DistanceMeaure dm;
    
    
    private EllipseRotated_F64 portal;
    private double distance;
    Result result = null;

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
        	
        
            if(MenuPanel.colorOffset != null) {
                WhiteBalance wb = new WhiteBalance(MenuPanel.colorOffset[0], MenuPanel.colorOffset[1], MenuPanel.colorOffset[2]);
                Calibrator calib = new Calibrator(image, true);
                wb.colorImage(calib.getImage());
                
            }
        	Mat sourceImg = new Mat();
    		sourceImg =findAndDrawEllipse(bufferedImageToMat(image));
    		Img2show= (BufferedImage) toBufferedImage(sourceImg);
        
            
          /*  ms = new Measure(image);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                portal = ms.findMaxEllipse(true, 0.18);
                result = new MultiFormatReader().decode(bitmap);
                if (result!=null){
            System.out.println(result.getText());
        }
                if(portal != null) {
                    nav.flyToPortal(portal, image, true);
                    distance = DistanceMeaure.getDistanceToObject(image.getHeight(), (portal.a * 2 + portal.b * 2) / 2, Config.portalHeight, 1.6404040404040405);
                    MenuPanel.updateDistanceDisplay(distance);
                }
            } catch (Exception e) { }
           */ 
            g.drawImage(Img2show, 0, 0, getWidth(), getHeight(), null);
        }}
    
        

    private static Mat findAndDrawEllipse(Mat sourceImg) {
		Mat hsvImg=new Mat();
		Imgproc.cvtColor(sourceImg, hsvImg, Imgproc.COLOR_BGR2HSV);
		Mat lower_hue_range = new Mat();
		Mat upper_hue_range = new Mat();
		Core.inRange(hsvImg, new Scalar(0,100,45), new Scalar(15,255,255), lower_hue_range);
		Core.inRange(hsvImg, new Scalar(160,100,45), new Scalar(180,255,255), upper_hue_range);
		Mat red_hue_image = new Mat();
		Core.addWeighted(lower_hue_range, 1.0, upper_hue_range, 1.0, 0, red_hue_image);
		Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
		Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2,2));
		
		Imgproc.blur(red_hue_image, red_hue_image, new Size(11,11));
//Imgproc.erode(red_hue_image, red_hue_image, erodeElement);
//Imgproc.dilate(Binarized, Binarized, dilateElement);
		// init
		List<MatOfPoint> contours = new ArrayList<>();
		Mat hierarchy = new Mat();

		// find contours
		Imgproc.findContours(red_hue_image, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
		// if any contour exist...
		if (hierarchy.size().height > 0 && hierarchy.size().width > 0) {
			// for each contour, display it in blue
			for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0]) {
				
			//	Imgproc.drawContours(frame, contours, idx, new Scalar(250, 0, 0), 3);
			
			}
		}
		MatOfPoint2f approxCurve = new MatOfPoint2f();

	    //For each contour found
		MatOfPoint2f contour2f = null;
		 RotatedRect rotatedrect = null;
	    for (int i=0; i<contours.size(); i++)
	    {
	        //Convert contours(i) from MatOfPoint to MatOfPoint2f
	    	if(contour2f==null)contour2f = new MatOfPoint2f( contours.get(i).toArray() );
	    	if(contours.get(i).size().area()>contour2f.size().area())
	    	contour2f = new MatOfPoint2f( contours.get(i).toArray() );
	        //Processing on mMOP2f1 which is in type MatOfPoint2f
	    }
	    try{
	    	
	    Imgproc.fitEllipse(contour2f);
	    rotatedrect= Imgproc.fitEllipse(contour2f);
	   
	        double approxDistance = Imgproc.arcLength(contour2f, true)*0.02;
	        Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
	        

	        //Convert back to MatOfPoint
	        MatOfPoint points = new MatOfPoint( approxCurve.toArray() );

	        // Get bounding rect of contour
	        Rect rect = Imgproc.boundingRect(points);
	        

	         // draw enclosing rectangle (all same color, but you could use variable i to make them unique)
	        
	        Imgproc.rectangle(sourceImg, rect.tl(), rect.br(), new Scalar(255, 0, 0),1, 8,0); 
	        Imgproc.ellipse(sourceImg,rotatedrect,new Scalar(255, 192,203),4,8);
	    	
	    }
	    catch(CvException e){
	    	System.out.println("Ingen ellipse fundet");
	    	
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
	if (m.channels() > 1) {
		type = BufferedImage.TYPE_3BYTE_BGR;
	}
	int bufferSize = m.channels() * m.cols() * m.rows();
	byte[] b = new byte[bufferSize];
	m.get(0, 0, b); // get all the pixels
	BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
	final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	System.arraycopy(b, 0, targetPixels, 0, b.length);
	return image;

}
}