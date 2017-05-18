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

    public VideoListener(GUIController controller) {
        this.controller = controller;
    }
    
    @Override
    public void imageUpdated(BufferedImage bi) {
        controller.updateImage(bi);
    }
}