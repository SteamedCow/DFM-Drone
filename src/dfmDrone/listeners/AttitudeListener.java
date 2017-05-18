package dfmDrone.listeners;

import dfmDrone.gui.GUIController;

/**
 * AttitudeListener
 * @author SteamedCow
 * @version 18-05-2017
 */
public class AttitudeListener implements de.yadrone.base.navdata.AttitudeListener
{
    private final GUIController controller;

    public AttitudeListener(GUIController controller) {
        this.controller = controller;
    }
    
    @Override
    public void attitudeUpdated(float pitch, float roll, float yaw) {
        controller.updateNavigationDisplay(pitch, roll, yaw);
    }
    
    @Override
    public void attitudeUpdated(float pitch, float roll) { }
    
    @Override
    public void windCompensation(float pitch, float roll) { }
}