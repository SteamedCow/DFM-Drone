package dfmDrone.tutorials;

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.AttitudeListener;
import de.yadrone.base.navdata.BatteryListener;

public class TutorialAttitudeListener 
{
    public TutorialAttitudeListener(IARDrone drone) {
        drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {
            @Override
            public void attitudeUpdated(float pitch, float roll, float yaw) {
                System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw);
            }
            
            @Override
            public void attitudeUpdated(float pitch, float roll) { }
            
            @Override
            public void windCompensation(float pitch, float roll) { }
        });
        
        drone.getNavDataManager().addBatteryListener(new BatteryListener() {            
            @Override
            public void batteryLevelChanged(int percentage) {
                System.out.println("Battery: " + percentage + " %");
            }
            
            @Override
            public void voltageChanged(int vbat_raw) { }
        });
    }
}