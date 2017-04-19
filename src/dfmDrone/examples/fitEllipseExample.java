/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfmDrone.examples;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Asger
 */
public class fitEllipseExample {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat sourceImg = new Mat();
		sourceImg = Imgcodecs.imread("portaler.png");
		Mat grayScaleImg = new Mat();
		Mat hsvImg=new Mat();
		Imgproc.cvtColor(sourceImg, hsvImg, Imgproc.COLOR_BGR2HSV);
		Mat lower_hue_range = new Mat();
		Mat upper_hue_range = new Mat();
		Core.inRange(hsvImg, new Scalar(0,70,100), new Scalar(10,255,255), lower_hue_range);
		Core.inRange(hsvImg, new Scalar(160,60,100), new Scalar(179,255,255), upper_hue_range);
		Mat red_hue_image = new Mat();
		Core.addWeighted(lower_hue_range, 1.0, upper_hue_range, 1.0, 0, red_hue_image);
	
		//Imgproc.cvtColor(hsvImg, grayScaleImg, Imgproc.COLOR_BGR2GRAY);
		Mat Binarized= new Mat();
		Imgproc.threshold(grayScaleImg,Binarized, 55,255, Imgproc.THRESH_BINARY_INV); // would set everything &gt;= 123 to 255
		Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
		Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1,1));
		
		Imgproc.blur(red_hue_image, red_hue_image, new Size(7,7));
//Imgproc.erode(red_hue_image, red_hue_image, erodeElement);
//Imgproc.dilate(Binarized, Binarized, dilateElement);

	
		sourceImg =findAndDrawEllipse(red_hue_image, sourceImg);

		 
		JFrame frame = new JFrame("Picture");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1333, 700);
		BufferedImage Img2show = (BufferedImage) toBufferedImage(sourceImg);
		
		Image scaled = Img2show.getScaledInstance(1333, 700, Image.SCALE_DEFAULT);
		frame.setContentPane(new showImage(scaled));
		frame.setVisible(true);

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

	private static Mat findAndDrawEllipse(Mat maskedImage, Mat frame) {
		// init
		List<MatOfPoint> contours = new ArrayList<>();
		Mat hierarchy = new Mat();

		// find contours
		Imgproc.findContours(maskedImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
		System.out.println("After findcontours");
		// if any contour exist...
		if (hierarchy.size().height > 0 && hierarchy.size().width > 0) {
			// for each contour, display it in blue
			for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0]) {
				System.out.println(idx);
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
	        
	        Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(255, 0, 0),1, 8,0); 
	        Imgproc.ellipse(frame,rotatedrect,new Scalar(255, 192,203),4,8);
	        
	    }
	    catch(CvException e){
	    	e.printStackTrace();
	    	System.out.println("Ingen ellipse fundet");
	    	
	    }

	
	

		return frame;
	
}
}
