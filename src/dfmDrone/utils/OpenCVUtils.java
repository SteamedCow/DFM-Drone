package dfmDrone.utils;

import boofcv.alg.color.ColorHsv;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import dfmDrone.data.Data;
import dfmDrone.data.DummyData;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.List;
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

/**
 * OpenCVUtils
 * @author SteamedCow
 * @author as
 * @version 18-05-2017
 */
public class OpenCVUtils
{
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
    
    public static Result QRScanner(BufferedImage image) {
        Result result = null;
        
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        
        try {
            result = new MultiFormatReader().decode(bitmap);
        } catch (NotFoundException e) { }
        
        return result;
    }
    
    public synchronized static ImageAnalyticsModel findAndDrawEllipse(Mat sourceImg) {
        Rect rect = null;
        Mat hsvImg = new Mat();
        Imgproc.cvtColor(sourceImg, hsvImg, Imgproc.COLOR_BGR2HSV);
        Mat lower_hue_range = new Mat();
        Mat upper_hue_range = new Mat();
//        Core.inRange(hsvImg, new Scalar(0, 100, 45), new Scalar(15, 255, 255), lower_hue_range);
//        Core.inRange(hsvImg, new Scalar(160, 100, 45), new Scalar(180, 255, 255), upper_hue_range);
        Core.inRange(hsvImg, new Scalar(DummyData.lowerB1H, DummyData.lowerB1S, DummyData.lowerB1V),
                new Scalar(DummyData.upperB1H, DummyData.upperB1S, DummyData.upperB1V), lower_hue_range);
        Core.inRange(hsvImg, new Scalar(DummyData.lowerB2H, DummyData.lowerB2S, DummyData.lowerB2V),
                new Scalar(DummyData.upperB2H, DummyData.upperB2S, DummyData.upperB2V), upper_hue_range);
        Mat red_hue_image = new Mat();
        Core.addWeighted(lower_hue_range, 1.0, upper_hue_range, 1.0, 0, red_hue_image);
//        Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
        Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1, 1));
        
//        Imgproc.blur(red_hue_image, red_hue_image, new Size(9, 9));
        Imgproc.blur(red_hue_image, red_hue_image, new Size(9, 9), new Point(2, 2));
         Imgproc.erode(red_hue_image, red_hue_image, erodeElement);
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
                rect = Imgproc.boundingRect(points);
                
                // draw enclosing rectangle (all same color, but you could use
                // variable i to make them unique)
                Imgproc.rectangle(sourceImg, rect.tl(), rect.br(), new Scalar(255, 0, 0), 1, 8, 0);
                Imgproc.ellipse(sourceImg, rotatedrectbest, new Scalar(255, 192, 203), 4, 8);
            }
        }
        catch (CvException e) {
            System.out.println("Ingen ellipse fundet: " + e);
        }
        if(Data.SHOW_BINARY)
            return new ImageAnalyticsModel(red_hue_image, rect, rotatedrectbest);
        else
            return new ImageAnalyticsModel(sourceImg, rect, rotatedrectbest);
    }
    
    public static class ImageAnalyticsModel {
        public Mat sourceImg;
        public Rect rect;
        public RotatedRect rotatedrectbest;

        public ImageAnalyticsModel(Mat sourceImg, Rect rect, RotatedRect rotatedrectbest) {
            this.sourceImg = sourceImg;
            this.rect = rect;
            this.rotatedrectbest = rotatedrectbest;
        }
    }
    
    public static double[] hsvToRGB(int hue, int saturation, int value) {
        double[] rgb = new double[3];
        
        double s = 0, v = 0;
        if(saturation != 0)
            s = saturation/255.0;
        if(value != 0)
            v = value/255.0;
        
        ColorHsv.hsvToRgb(Math.toRadians(hue * 2), s, v, rgb);
        rgb[0] *= 255;
        rgb[1] *= 255;
        rgb[2] *= 255;
        
        return rgb;
    }
}