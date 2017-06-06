package dfmDrone.listeners;

import de.yadrone.base.command.VideoChannel;
import dfmDrone.gui.GUIController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * CameraSwitchListener
 * @author SteamedCow
 * @version 18-05-2017
 */
public class CameraSwitchListener extends MouseAdapter
{
//    private final GUIController controller;

    public CameraSwitchListener(GUIController controller) {
//        this.controller = controller;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
//        controller.getDrone().getCommandManager().setVideoChannel(VideoChannel.NEXT);
    }
}