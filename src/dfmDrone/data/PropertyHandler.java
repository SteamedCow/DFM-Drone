package dfmDrone.data;
import dfmDrone.utils.DFMLogger;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;

/**
 * PropertyHandler
 * @author Lasse
 * @version 28-11-2015
 */
public class PropertyHandler 
{
    public enum PropertyLabel {
        DroneIP, MaxAltitude, MinAltitude, VideoFrameRate, Outdoor, Hull, PortalHeight, CameraConstant, LoggerLevel;
    }
    
    private final Properties prop = new Properties();
    private final String filepath;

    public PropertyHandler(String filepath, boolean createIfMissing) {
        this.filepath = filepath;
        
        if(createIfMissing) {
            try {
                loadProperties();
            } catch (IOException e) {
                DFMLogger.logger.log(Level.WARNING, "Property file not found \"{0}\". Creating property file with default values: {1}", new String[]{filepath, e.getMessage()});
                try {
                    saveProperties(Config.DEFAULT_DRONE_IP, Config.DEFAULT_MAX_ALTITUDE, Config.DEFAULT_MIN_ALTITUDE, Config.DEFAULT_VIDEO_FRAMERATE, Config.DEFAULT_OUTDOOR, Config.DEFAULT_HULL, Config.DEFAULT_PORTAL_HEIGHT, Config.DEFAULT_CAMERA_CONSTANT, Config.DEFAULT_LOGGER_LEVEL);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        else
            try {
                loadProperties();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    /**
     * Indlæser properties fra property filen
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadProperties() throws FileNotFoundException, IOException {
        try (InputStream input = new FileInputStream(filepath)) {
            prop.load(input);
        }
    }
    
    /**
     * Gemmer properties i en property fil (laver en ny hvis ingen findes)
     * @param droneIP
     *      Dronens IP addresse
     * @param maxAltitude
     *      Max flyvehøjde i milimeter
     * @param minAltitude
     *      Minimum flyvehøjde i milimeter
     * @param VideoFrameRate
     *      Framerate for videooptagelse for dronens kameraer
     * @param outdoor
     *      Is the drone flying outdoor
     * @param hull
     *      Is the outdoor hull mounted on the drone
     * @param PortalHeight
     *      Højden af portalerne i milimeter
     * @param CameraConstant
     *      Kamerakonstant som bruges til at måle afstand til objekter i videostreamen
     * @param loggerLevel
     *      Logger niveua
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveProperties(String droneIP, Integer maxAltitude, Integer minAltitude, Integer VideoFrameRate, Boolean outdoor, Boolean hull, Integer PortalHeight, Double CameraConstant, Level loggerLevel) throws FileNotFoundException, IOException {
        try (OutputStream output = new FileOutputStream(filepath)) {
            prop.setProperty(PropertyLabel.DroneIP.name(), droneIP);
            prop.setProperty(PropertyLabel.MaxAltitude.name(), maxAltitude.toString());
            prop.setProperty(PropertyLabel.MinAltitude.name(), minAltitude.toString());
            prop.setProperty(PropertyLabel.VideoFrameRate.name(), VideoFrameRate.toString());
            prop.setProperty(PropertyLabel.Outdoor.name(), outdoor.toString());
            prop.setProperty(PropertyLabel.Hull.name(), hull.toString());
            prop.setProperty(PropertyLabel.PortalHeight.name(), PortalHeight.toString());
            prop.setProperty(PropertyLabel.CameraConstant.name(), CameraConstant.toString());
            prop.setProperty(PropertyLabel.LoggerLevel.name(), loggerLevel.getName());
            prop.store(output, "DFM drone client properties");
            DFMLogger.logger.info("Properties saved");
        }
    }

    public void saveCameraConstant(double cameraConstant) throws FileNotFoundException, IOException
    {
        try( OutputStream output = new FileOutputStream(filepath)){
            prop.setProperty(PropertyLabel.CameraConstant.name(), String.valueOf(cameraConstant));
            prop.store(output,"DFM drone client properties");
        }
    }

    public void save(PropertyLabel propLabel, Integer value) throws FileNotFoundException, IOException
    {
        try( OutputStream output = new FileOutputStream(filepath)){
            prop.setProperty(propLabel.name(), value.toString());
            prop.store(output,"DFM drone client properties");
        }
    }
    
    /**
     * Henter properties for den angivende nøgle
     * @param propLabel
     *      Nøgle for den property der skal findes
     * @return 
     *      Den funde property
     */
    public String get(PropertyLabel propLabel) {
        final String value = prop.getProperty(propLabel.name(), "MISSING PROPERTY");
        
        if(value == null)
            DFMLogger.logger.log(Level.WARNING, "Property for key \"{0}\" is missing", propLabel.name());
        
        return value;
    }
}