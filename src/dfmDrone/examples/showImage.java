/**
 * 
 */
package dfmDrone.examples;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author Asger
 *
 * 22/03/2017
 */
public class showImage extends JPanel{
	Image img;

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	
	showImage(Image img){
		this.img=img;
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(img, 0, 0,null);
	}
	
	

}
