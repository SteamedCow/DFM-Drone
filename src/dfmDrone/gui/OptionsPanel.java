package dfmDrone.gui;

import dfmDrone.data.PropertyHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.io.IOException;

/**
 * Created by as on 6/5/17.
 */
public class OptionsPanel extends JFrame {
    private JSlider jsAltitude;
    private JSlider slider2;
    private JPanel Jpanel;
    private JLabel maxAltitudeLabel;
    private JLabel label2;
    private final GUIController controller;

    public OptionsPanel(GUIController controller) {
        this.controller = controller;
        createUIComponents();
        setTitle("settings");
        setContentPane(Jpanel);
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }


    public void jsAltitudeStateChange(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()){
            controller.getDrone().setMaxAltitude(source.getValue());
            maxAltitudeLabel.setText("MaxAltitude: "+source.getValue());
            try{
                controller.propHandler.saveMaxAltitude(source.getValue());}
            catch(IOException ex){
                ex.printStackTrace();
            }

        }

    }

    private void createUIComponents() {
       jsAltitude = new javax.swing.JSlider(JSlider.HORIZONTAL, 0, 10000, Integer.parseInt(controller.getProperty(PropertyHandler.PropertyLabel.MaxAltitude)));
       maxAltitudeLabel.setText("MaxAltitude: "+ controller.getProperty(PropertyHandler.PropertyLabel.MaxAltitude));
        jsAltitude.addChangeListener(this::jsAltitudeStateChange);
    }
}
