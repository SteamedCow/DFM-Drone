/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfmDrone.examples;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import whiteBalance.tools.ImageLoader;

/**
 *
 * @author Asger
 */
public class QRScanExample {

    public static void main(String[] args) {

        Result result = null;

        BufferedImage img= null;
        try {
          img = ImageIO.read(new File("C:\\Users\\Asger\\Documents\\NetBeansProjects\\DFM-Drone\\pics\\static_qr_code_without_logo.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {

            result = new MultiFormatReader().decode(bitmap);
        } catch (NotFoundException e) {
            // fall thru, it means there is no QR code in image
        }
        if (result!=null){
            System.out.println(result.getText());
        }
        }
    }



