package dfmDrone.listeners;

import dfmDrone.gui.GUIController;

/**
 * BatteryListener
 * @author SteamedCow
 * @version 18-05-2017
 */
public class BatteryListener implements de.yadrone.base.navdata.BatteryListener
{
    private final GUIController controller;

    public BatteryListener(GUIController controller) {
        this.controller = controller;
    }
    
    @Override
    public void batteryLevelChanged(int i) {
        controller.updateBatteryDisplay(i);
    }

    @Override
    public void voltageChanged(int i) { }
}