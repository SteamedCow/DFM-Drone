package dfmDrone.listeners;

import dfmDrone.gui.Controller;
import dfmDrone.gui.GUIMenuBar;
import dfmDrone.gui.HSVSettingsPanel;
import dfmDrone.gui.ManualPanel;
import dfmDrone.gui.OptionsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public MenuItemListener(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        switch(evt.getActionCommand()) {
            case GUIMenuBar.AC_HSV: {
                JFrame frame = new JFrame("HSV Settings");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new HSVSettingsPanel(controller.hsvHandler));
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
                frame.setVisible(true);
                break;
            }
            case GUIMenuBar.AC_SETTINGS: {
                new OptionsPanel(controller);
                break;
            }
            case GUIMenuBar.AC_HELP: {
                break;
            }
            case GUIMenuBar.AC_ABOUT: {
                break;
            }
            default: {
                break;
            }
        }
    }
}