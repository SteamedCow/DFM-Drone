package dfmDrone.listeners;

import dfmDrone.gui.Controller;

/**
 * BatteryListener
 * @author SteamedCow
 * @version 18-05-2017
 */
public class BatteryListener implements de.yadrone.base.navdata.BatteryListener
{
    private final Controller controller;

    public BatteryListener(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void batteryLevelChanged(int i) {
        controller.updateBatteryDisplay(i);
    }

    @Override
    public void voltageChanged(int i) { }
}