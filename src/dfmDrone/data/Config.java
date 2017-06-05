package dfmDrone.data;

import de.yadrone.base.command.VideoCodec;
import java.util.logging.Level;

/**
 * Config
 * @author Lasse
 * @version 23-02-2017
 */
public class Config 
{
    protected final static String DEFAULT_DRONE_IP = "192.168.1.1";
    protected final static int DEFAULT_MAX_ALTITUDE = 3500; // in mm
    protected final static int DEFAULT_VIDEO_FRAMERATE = 15;
    protected final static boolean DEFAULT_OUTDOOR = false;
    protected final static boolean DEFAULT_HULL = true;
    protected final static int DEFAULT_PORTAL_HEIGHT = 850; //in mm
    protected final static double DEFAULT_CAMERA_CONSTANT = 1.61806;
    protected final static Level DEFAULT_LOGGER_LEVEL = Level.INFO;
    
    public final static VideoCodec VIDEO_CODEC = VideoCodec.H264_360P;
    public final static int CMDQ_TIMEOUT = 200; //in ms
}