package dfmDrone.gui;

import dfmDrone.data.Config;
import dfmDrone.listeners.MenuCheckItemListener;
import dfmDrone.listeners.MenuItemListener;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * GUIMenuBar
 * @author SteamedCow
 * @version 10-06-2017
 */
public class GUIMenuBar extends JMenuBar
{
    public static final String AC_WB = "miacWB";
    public static final String AC_HSV = "miacHSV";
    public static final String AC_SETTINGS = "miacSET";
    public static final String AC_MANUAL = "miacMAN";
    
    public static final String AC_BCAM = "mciacBCAM";
    public static final String AC_BRIGHT = "mciacBRIGHT";
    public static final String AC_AI = "mciacAI";
    public static final String AC_SCAN = "mciacSCAN";
    
    public static final String AC_HELP = "miacHELP";
    public static final String AC_ABOUT = "miacABOUT";
    
    public GUIMenuBar(Controller controller) {
        setBackground(Color.LIGHT_GRAY);
        MenuItemListener mil = new MenuItemListener(controller);
        int defaultShortcutKey = Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask();
        
        //Tools menu
        JMenu tools = new JMenu("Tools");
        
        JMenuItem miHSV = new JMenuItem("Adjust HSV filter");
        JMenuItem miWB = new JMenuItem("White Balance");
        JMenuItem miManual = new JMenuItem("Manual Control");
        
        miHSV.setAccelerator(KeyStroke.getKeyStroke('H', defaultShortcutKey));
        miWB.setAccelerator(KeyStroke.getKeyStroke('W', defaultShortcutKey));
        miManual.setAccelerator(KeyStroke.getKeyStroke('M', defaultShortcutKey));
        
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
        
        //Options menu
        JMenu options = new JMenu("Options");
        
        JCheckBoxMenuItem cbmiBottomCam = new JCheckBoxMenuItem("Bottom camera", false);
        JCheckBoxMenuItem cbmiBright = new JCheckBoxMenuItem("Color brighter", Config.colorBrighter);
        JCheckBoxMenuItem cbmiAI = new JCheckBoxMenuItem("Enable AI", true);
        JCheckBoxMenuItem cbmiScan = new JCheckBoxMenuItem("Scan", true);
        
        MenuCheckItemListener mcil = new MenuCheckItemListener(controller, cbmiScan);
        cbmiBright.setAccelerator(KeyStroke.getKeyStroke('B', defaultShortcutKey));
        
        cbmiBottomCam.setActionCommand(AC_BCAM);
        cbmiBright.setActionCommand(AC_BRIGHT);
        cbmiAI.setActionCommand(AC_AI);
        cbmiScan.setActionCommand(AC_SCAN);
        
        cbmiBottomCam.addItemListener(mcil);
        cbmiBright.addItemListener(mcil);
        cbmiAI.addItemListener(mcil);
        cbmiScan.addItemListener(mcil);
        
        options.add(cbmiBottomCam);
        options.add(cbmiBright);
        options.addSeparator();
        options.add(cbmiAI);
        options.add(cbmiScan);
        
        //Help menu
        JMenu help = new JMenu("Help");
        
        JMenuItem miSettings = new JMenuItem("Settings");
        JMenuItem miHelp = new JMenuItem("Help");
        JMenuItem miAbout = new JMenuItem("About");
        
        miSettings.setAccelerator(KeyStroke.getKeyStroke('S', defaultShortcutKey));
        miHelp.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.ALT_DOWN_MASK));
        
        miSettings.setActionCommand(AC_SETTINGS);
        miHelp.setActionCommand(AC_HELP);
        miAbout.setActionCommand(AC_ABOUT);
        
        miSettings.addActionListener(mil);
        miHelp.addActionListener(mil);
        miAbout.addActionListener(mil);
        
        help.add(miSettings);
        help.addSeparator();
        help.add(miHelp);
        help.add(miAbout);
        
        //Menu
        add(tools);
        add(options);
        add(help);
    }
}