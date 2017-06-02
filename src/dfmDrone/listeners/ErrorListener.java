package dfmDrone.listeners;

import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;
import dfmDrone.utils.DFMLogger;
import java.util.logging.Level;

/**
 * ErrorListener
 * @author SteamedCow
 * @version 18-05-2017
 */
public class ErrorListener implements IExceptionListener
{
    @Override
    public void exeptionOccurred(ARDroneException e) {
        DFMLogger.logger.log(Level.INFO, "[ERROR LISTENER]: {0}", e.getMessage());
        System.err.println("[ERROR LISTENER]: " + e.getMessage());
    }
}