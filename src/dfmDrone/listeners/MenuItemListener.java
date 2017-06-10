package dfmDrone.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MenuItemListener
 * @author SteamedCow
 * @version 10-06-2017
 */
public class MenuItemListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}