package dfmDrone.utils;
import dfmDrone.data.Data;
import java.util.logging.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Log
 * Logger klasse som skriver events i programmet ned i en log fil og viser dem i terminalen.
 * @author Lasse Holm Nielsen - S123954
 * @version 28-11-2015
 */
public class DFMLogger 
{
    public static final Logger logger = Logger.getLogger("Log");
    private static FileHandler fh;

    /**
     * Static konstruktør for loggeren. Ændrer formattet
     */
    static {
        try {
            fh = new FileHandler(Data.LOG_FILEPATH, true);
            
            fh.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord rec) {
                    StringBuilder buf = new StringBuilder(1000);
                    buf.append(new SimpleDateFormat("d-M-yy HH:mm:ss").format(new java.util.Date()));
                    buf.append(": [");
                    buf.append(rec.getLevel());
                    buf.append("] ");
                    buf.append(formatMessage(rec));
                    if(rec.getLevel().intValue() > Level.INFO.intValue())
                        buf.append("(").append(rec.getSourceClassName()).append('.').append(rec.getSourceMethodName()).append(')');
                    buf.append("\r\n");
                    
                    try {
                        if(rec.getLevel().intValue() > Level.WARNING.intValue())
                            JOptionPane.showMessageDialog(new JFrame(), rec.getLevel()+"\n"+formatMessage(rec), "DFM Drone - ERROR", JOptionPane.ERROR_MESSAGE);
                        else if(rec.getLevel().intValue() > Level.INFO.intValue())
                            JOptionPane.showMessageDialog(new JFrame(), rec.getLevel()+"\n"+formatMessage(rec), "DFM Drone - Warning", JOptionPane.WARNING_MESSAGE);
                    } catch (Exception e) {
                        System.err.println("! LOGGER ERROR !");
                        e.printStackTrace();
                    }
                    
                    return buf.toString();
                    }
                });
            
            logger.addHandler(fh);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void close() {
        logger.removeHandler(fh);
        fh.flush();
        fh.close();
    }
    
    /**
     * Angiver hvilket nivau der skal logges.
     * @param level 
     *      Logger nivau
     */
    public static void setLevel(Level level) {
        logger.setLevel(level);
    }
}