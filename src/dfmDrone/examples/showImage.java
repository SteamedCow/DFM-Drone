package dfmDrone.examples;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * @author Asger
 * 22/03/2017
 */
public class showImage extends JPanel {
    private final Image img;
    
    public showImage(Image img){
        this.img=img;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0,null);
    }
}