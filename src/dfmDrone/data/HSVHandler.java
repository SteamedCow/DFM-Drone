package dfmDrone.data;

import dfmDrone.utils.DFMLogger;
import dfmDrone.utils.DFMScalar;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import org.opencv.core.Scalar;

/**
 * HSVHandler
 * @author Lasse
 * @version 11-06-2017
 */
public class HSVHandler implements Serializable
{
    //Range 1
    private static final Scalar DEFAULT_R1_LOWER = new Scalar(0, 128, 41); //HSV = 0, 128, 41 (0, 128, 41)
    private static final Scalar DEFAULT_R1_UPPER = new Scalar(6, 255, 219); //HSV = 6, 255, 219 (6, 255, 219)
    
    //Range 2
    private static final Scalar DEFAULT_R2_LOWER = new Scalar(169, 128, 41); //HSV = 169, 128, 41 (169, 128, 41)
    private static final Scalar DEFAULT_R2_UPPER = new Scalar(179, 255, 219); //HSV = 179, 255, 219 (179, 255, 219)
    
    private final String filePath;
    private static HSVSetting hsvSetting;
    
    public HSVHandler(String filePath, boolean loadFile) {
        this.filePath = filePath;
        
        if(loadFile) {
            if(load(false) == null) {
                DFMLogger.logger.log(Level.WARNING, "HSV settings file not found \"{0}\". Creating HSV file with default values", filePath);
                resetToDefault();
            }
        }
    }
    
    public HSVSetting load(boolean verbose) {
        HSVSetting setting;
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            setting = (HSVSetting) ois.readObject();
            updateSettings(setting);
            DFMLogger.logger.log(Level.CONFIG, "HSV settings file loaded: {0}", hsvSetting);
        } catch (Exception e) {
            e.printStackTrace();
            if(verbose)
                DFMLogger.logger.log(Level.SEVERE, "Could not load HSV settings file: {0}", e.getMessage());
        }
        
        return hsvSetting;
    }
    
    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(hsvSetting);
            DFMLogger.logger.log(Level.CONFIG, "HSV settings file saved: {0}", hsvSetting);
        } catch (Exception e) {
            DFMLogger.logger.log(Level.SEVERE, "Could not save HSV settings: {0}", e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void resetToDefault() {
        updateSettings(new HSVSetting(DEFAULT_R1_LOWER, DEFAULT_R1_UPPER, DEFAULT_R2_LOWER, DEFAULT_R2_UPPER));
        save();
        load(true);
    }
    
    public HSVSetting getSettings() {
        return hsvSetting;
    }
    
    public void updateSettings(HSVSetting setting) {
        this.hsvSetting = setting;
    }
    
    public void updateRange1Lower(Scalar hsvValue) {
        this.hsvSetting.setR1Lower(hsvValue);
    }
    
    public Scalar getRange1Lower() {
        return this.hsvSetting.getR1Lower();
    }
    
    public void updateRange1Upper(Scalar hsvValue) {
        this.hsvSetting.setR1Upper(hsvValue);
    }
    
    public Scalar getRange1Upper() {
        return this.hsvSetting.getR1Upper();
    }
    
    public void updateRange2Lower(Scalar hsvValue) {
        this.hsvSetting.setR2Lower(hsvValue);
    }
    
    public Scalar getRange2Lower() {
        return this.hsvSetting.getR2Lower();
    }
    
    public void updateRange2Upper(Scalar hsvValue) {
        this.hsvSetting.setR2Upper(hsvValue);
    }
    
    public Scalar getRange2Upper() {
        return this.hsvSetting.getR2Upper();
    }
    
    public class HSVSetting implements Serializable {
        //Range 1
        private DFMScalar r1Lower;
        private DFMScalar r1Upper;
        
        //Range 2
        private DFMScalar r2Lower;
        private DFMScalar r2Upper;

//        public HSVSetting(HSVSetting hsvSetting) {
//            this.r1Lower = new DFMScalar(hsvSetting.getR1Lower());
//            this.r1Upper = new DFMScalar(hsvSetting.getR1Upper());
//            this.r2Lower = new DFMScalar(hsvSetting.getR2Lower());
//            this.r2Upper = new DFMScalar(hsvSetting.getR2Upper());
//        }

        public HSVSetting(Scalar r1Lower, Scalar r1Upper, Scalar r2Lower, Scalar r2Upper) {
            this.r1Lower = new DFMScalar(r1Lower);
            this.r1Upper = new DFMScalar(r1Upper);
            this.r2Lower = new DFMScalar(r2Lower);
            this.r2Upper = new DFMScalar(r2Upper);
        }

        @Override
        public HSVSetting clone() throws CloneNotSupportedException {
            return new HSVSetting(r1Lower.toScalar(), r1Upper.toScalar(), r2Lower.toScalar(), r2Upper.toScalar());
        }

//        @Override
//        protected Object clone() throws CloneNotSupportedException {
//            return super.clone();
//        }

        public Scalar getR1Lower() {
            return r1Lower.toScalar();
        }

        public void setR1Lower(Scalar r1Lower) {
            this.r1Lower = new DFMScalar(r1Lower);
        }

        public Scalar getR1Upper() {
            return r1Upper.toScalar();
        }

        public void setR1Upper(Scalar r1Upper) {
            this.r1Upper = new DFMScalar(r1Upper);
        }

        public Scalar getR2Lower() {
            return r2Lower.toScalar();
        }

        public void setR2Lower(Scalar r2Lower) {
            this.r2Lower = new DFMScalar(r2Lower);
        }

        public Scalar getR2Upper() {
            return r2Upper.toScalar();
        }

        public void setR2Upper(Scalar r2Upper) {
            this.r2Upper = new DFMScalar(r2Upper);
        }

        @Override
        public String toString() {
            return "HSVSetting{r1Lower=" + r1Lower + ", r1Upper=" + r1Upper + ", r2Lower=" + r2Lower + ", r2Upper=" + r2Upper + '}';
        }
    }
}