package dfmDrone.listeners;

import de.yadrone.base.video.ImageListener;
import dfmDrone.gui.GUIController;
import java.awt.image.BufferedImage;

/**
 * VideoListener
 * @author SteamedCow
 * @version 18-05-2017
 */
public class VideoListener implements ImageListener
{
    private final GUIController controller;
    private int i = 0;

    public VideoListener(GUIController controller) {
        this.controller = controller;
    }
    
    @Override
    public void imageUpdated(BufferedImage bi) {
//        if(i == 3)
            controller.updateImage(bi);
        
//        i++;
//        
//        if(i > 3)
//            i = 0;
    }
}