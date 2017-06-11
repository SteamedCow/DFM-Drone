package dfmDrone.gui;

import dfmDrone.listeners.MenuItemListener;
import java.awt.HeadlessException;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.KeyEvent;

/**
 * GUIMenuBar
 * @author SteamedCow
 * @version 10-06-2017
 */
public class GUIMenuBar extends MenuBar
{
    public static final String AC_WB = "miacWB";
    public static final String AC_HSV = "miacHSV";
    public static final String AC_SETTINGS = "miacSET";
    public static final String AC_MANUAL = "miacMAN";
    public static final String AC_HELP = "miacHELP";
    public static final String AC_ABOUT = "miacABOUT";
    
    public GUIMenuBar(Controller controller) throws HeadlessException {
        MenuItemListener mil = new MenuItemListener(controller);
        
        //Tools menu
        Menu tools = new Menu("Tools");
        
        MenuItem miHSV = new MenuItem("Adjust HSV filter", new MenuShortcut(KeyEvent.VK_H, true));
        MenuItem miWB = new MenuItem("White Balance", new MenuShortcut(KeyEvent.VK_W, true));
        MenuItem miManual = new MenuItem("Manual Control", new MenuShortcut(KeyEvent.VK_M));
        miHSV.setActionCommand(AC_HSV);
        miWB.setActionCommand(AC_WB);
        miManual.setActionCommand(AC_MANUAL);
        miHSV.addActionListener(mil);
        miWB.addActionListener(mil);
        miManual.addActionListener(mil);
        
        tools.add(miHSV);
        tools.add(miWB);
        tools.addSeparator();
        tools.add(miManual);
        
        //Help menu
        Menu help = new Menu("Help");
        
        MenuItem miSettings = new MenuItem("Settings", new MenuShortcut(KeyEvent.VK_S));
        MenuItem miHelp = new MenuItem("Help", new MenuShortcut(KeyEvent.VK_H));
        MenuItem miAbout = new MenuItem("About");
        miSettings.setActionCommand(AC_SETTINGS);
        miHelp.setActionCommand(AC_HELP);
        miAbout.setActionCommand(AC_ABOUT);
        miSettings.addActionListener(mil);
        miHelp.addActionListener(mil);
        miAbout.addActionListener(mil);
        miHelp.setEnabled(false);
        miAbout.setEnabled(false);
        
        help.add(miSettings);
        help.addSeparator();
        help.add(miHelp);
        help.add(miAbout);
        
        //Menu
        add(tools);
        setHelpMenu(help);
    }
}