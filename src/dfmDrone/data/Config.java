package dfmDrone.data;

import de.yadrone.base.command.VideoCodec;

/**
 * Config
 * @author Lasse
 * @version 23-02-2017
 */
public class Config 
{
    public static String droneAddress = "192.168.1.1";
    public static int maxAltitude = 2;
    public static int vidFPS = 15;
    public static VideoCodec vidCodec = VideoCodec.H264_360P;
    
    public static double portalHeight = 850; //in mm
    public static double camConst = 1.61806;
}