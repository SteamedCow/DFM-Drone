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
    public GUIMenuBar() throws HeadlessException {
        //Tools menu
        Menu tools = new Menu("Tools");
        
        MenuItem miHSV = new MenuItem("Adjust HSV filter", new MenuShortcut(KeyEvent.VK_H));
        MenuItem miSettings = new MenuItem("Settings");
        miHSV.setActionCommand("miHSV");
        miSettings.setActionCommand("miSettings");
        miHSV.addActionListener(new MenuItemListener());
        miSettings.addActionListener(new MenuItemListener());
        
        tools.add(miHSV);
        tools.addSeparator();
        tools.add(miSettings);
        
        //Help menu
        Menu help = new Menu("Help");
        
        MenuItem miGuide = new MenuItem("Guide");
        MenuItem miAbout = new MenuItem("About");
        miGuide.setActionCommand("miGuide");
        miAbout.setActionCommand("miAbout");
        miGuide.addActionListener(new MenuItemListener());
        miAbout.addActionListener(new MenuItemListener());
        
        help.add(miGuide);
        help.add(miAbout);
        
        //Menu
        add(tools);
        setHelpMenu(help);
    }
}