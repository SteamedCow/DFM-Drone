package dfmDrone.listeners;

import de.yadrone.base.video.ImageListener;
import dfmDrone.gui.Controller;
import java.awt.image.BufferedImage;

/**
 * VideoListener
 * @author SteamedCow
 * @version 18-05-2017
 */
public class VideoListener implements ImageListener
{
    private final Controller controller;

    public VideoListener(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void imageUpdated(BufferedImage bi) {
        controller.updateImage(bi);
    }
}