package dfmDrone.gui;

import dfmDrone.data.PropertyHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.io.IOException;

/**
 * Created by as on 6/5/17.
 */
public class OptionsPanel {
    private JSlider jsAltitude;
    private JSlider slider2;
    private final GUIController controller;

    public OptionsPanel(GUIController controller) {
        this.controller = controller;
        initComponents();
    }

    public void initComponents() {
        jsAltitude = new javax.swing.JSlider(JSlider.HORIZONTAL, 0, 10000, Integer.parseInt(controller.getProperty(PropertyHandler.PropertyLabel.MaxAltitude)));
        jsAltitude.addChangeListener(this::jsAltitudeStateChange);
        jsAltitude.setMajorTickSpacing(500);
        jsAltitude.setMinorTickSpacing(50);
        jsAltitude.setPaintTicks(true);
        jsAltitude.setPaintLabels(true);
    }

    public void jsAltitudeStateChange(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()){
            controller.getDrone().setMaxAltitude(source.getValue());
            try{
                controller.propHandler.saveMaxAltitude(source.getValue());}
            catch(IOException ex){
                ex.printStackTrace();
            }

        }

    }
}
