package dfmDrone.listeners;

import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;

/**
 * ErrorListener
 * @author SteamedCow
 * @version 18-05-2017
 */
public class ErrorListener implements IExceptionListener
{
    @Override
    public void exeptionOccurred(ARDroneException e) {
        System.err.println("[ERROR LISTENER]: " + e.getMessage());
    }
}