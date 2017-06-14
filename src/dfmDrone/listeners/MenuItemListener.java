package dfmDrone.listeners;

import de.yadrone.base.command.VideoChannel;
import dfmDrone.gui.AboutPanel;
import dfmDrone.gui.Controller;
import dfmDrone.gui.GUIMenuBar;
import dfmDrone.gui.HSVSettingsPanel;
import dfmDrone.gui.HelpPanel;
import dfmDrone.gui.ManualPanel;
import dfmDrone.gui.OptionsPanel;
import dfmDrone.utils.DFMLogger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import whiteBalance.exceptions.DetectionException;
import whiteBalance.tools.Calibrator;

/**
 * MenuItemListener
 * @author SteamedCow
 * @version 10-06-2017
 */
public class MenuItemListener implements ActionListener
{
    private final Controller controller;
    private final HSVSettingsPanel hsvPanel;

    public MenuItemListener(Controller controller) {
        this.controller = controller;
        this.hsvPanel = new HSVSettingsPanel(controller.hsvHandler);;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        switch(evt.getActionCommand()) {
            case GUIMenuBar.AC_HSV: {
                JFrame frame = new JFrame("HSV Settings");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(hsvPanel);
                hsvPanel.updateTemp(hsvPanel.getHSVSettings());
                frame.pack();
                frame.setVisible(true);
                break;
            }
            case GUIMenuBar.AC_WB: {
                try {
                    Calibrator calib = new Calibrator(controller.getVideoFrame(), true);
                    controller.setColorOffset(calib.calibrate(3, 3, 3));
                } catch (DetectionException e) {
                    e.printStackTrace();
                }
                break;
            }
            case GUIMenuBar.AC_MANUAL: {
                JFrame frame = new JFrame("Manual control");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new ManualPanel(controller));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                break;
            }
            case GUIMenuBar.AC_BCAM: {
                if(((JCheckBoxMenuItem) evt.getSource()).getState())
                    controller.getDrone().getCommandManager().setVideoChannel(VideoChannel.HORI);
                else
                    controller.getDrone().getCommandManager().setVideoChannel(VideoChannel.VERT);
                break;
            }
            case GUIMenuBar.AC_SETTINGS: {
                new OptionsPanel(controller);
                break;
            }
            case GUIMenuBar.AC_HELP: {
                JFrame frame = new JFrame("Help");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new HelpPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                break;
            }
            case GUIMenuBar.AC_ABOUT: {
                JFrame frame = new JFrame("About");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new AboutPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                break;
            }
            default: {
                DFMLogger.logger.log(Level.WARNING, "Unknown command: {0}", evt.getActionCommand());
                break;
            }
        }
    }
}