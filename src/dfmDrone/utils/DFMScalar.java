package dfmDrone.utils;

import java.io.Serializable;
import org.opencv.core.Scalar;

/**
 * DFMScalar
 * @author Lasse
 * @version 11-06-2017
 */
public class DFMScalar implements Serializable
{
    public double[] val = new double[3];
    
    public DFMScalar(double v0, double v1, double v2) {
        val = new double[]{v0,v1,v2};
    }
    
    public DFMScalar(Scalar scalar) {
        this(scalar.val[0], scalar.val[1], scalar.val[2]);
    }
    
    public Scalar toScalar() {
        return new Scalar(val[0], val[1], val[2]);
    }
    
    public void toDFMScalar(Scalar scalar) {
        this.val = scalar.val;
    }
}