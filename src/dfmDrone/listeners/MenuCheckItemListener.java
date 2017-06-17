package dfmDrone.listeners;

import de.yadrone.base.command.VideoChannel;
import dfmDrone.data.Config;
import dfmDrone.gui.Controller;
import dfmDrone.gui.GUIMenuBar;
import dfmDrone.utils.DFMLogger;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import javax.swing.JCheckBoxMenuItem;

/**
 * MenuCheckItemListener
 * @author Lasse
 * @version 17-06-2017
 */
public class MenuCheckItemListener implements ItemListener
{
    private final Controller controller;
    private final JCheckBoxMenuItem cbmiScan;

    public MenuCheckItemListener(Controller controller, JCheckBoxMenuItem cbmiScan) {
        this.controller = controller;
        this.cbmiScan = cbmiScan;
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt) {
        JCheckBoxMenuItem c = (JCheckBoxMenuItem) evt.getItem();
        
        switch(c.getActionCommand()) {
            case GUIMenuBar.AC_BCAM: {
                VideoChannel vc;
                
                if(c.getState())
                    vc = VideoChannel.VERT;
                else
                    vc = VideoChannel.HORI;
                
                controller.getDrone().getCommandManager().setVideoChannel(vc);
                break;
            }
            case GUIMenuBar.AC_BRIGHT: {
                Config.colorBrighter = c.getState();
                break;
            }
            case GUIMenuBar.AC_AI: {
                boolean enabled = c.getState();
                
                Config.ai = enabled;
                cbmiScan.setEnabled(enabled);
                
                if(!enabled)
                    cbmiScan.setState(false);
                System.out.println("AI=" + Config.ai);
                break;
            }
            case GUIMenuBar.AC_SCAN: {
                Config.scan = c.getState();
                System.out.println("Scan=" + Config.scan);
                break;
            }
            default: {
                DFMLogger.logger.log(Level.WARNING, "Unknown command: {0}", c.getActionCommand());
                break;
            }
        }
    }
}