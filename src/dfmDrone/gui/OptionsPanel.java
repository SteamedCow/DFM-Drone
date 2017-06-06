package dfmDrone.gui;

import dfmDrone.data.Config;
import dfmDrone.data.PropertyHandler.PropertyLabel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by as on 6/5/17.
 */
public class OptionsPanel extends JFrame {
    private JSlider jsMaxAltitude;
    private JPanel Jpanel;
    private JLabel maxAltitudeLabel;
    private JTextField portalHeightText;
    private JSlider jsMinAltitude;
    private JTextField cameraConstantText;
    private JTextField videoFrameText;
    private JLabel minAltitudeLabel;
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


    public void jsMaxAltitudeStateChange(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {

            try {
                controller.getDrone().setMaxAltitude(source.getValue());
                maxAltitudeLabel.setText("MaxAltitude: " + source.getValue());
                controller.propHandler.save(PropertyLabel.MaxAltitude, source.getValue());


            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        maxAltitudeLabel.setText("MaxAltitude: " + source.getValue());
    }

    public void jsMinAltitudeStateChange(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {

            try {
                controller.getDrone().setMinAltitude(source.getValue());
                maxAltitudeLabel.setText("MinAltitude: " + source.getValue());
                controller.propHandler.save(PropertyLabel.MinAltitude, source.getValue());


            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        maxAltitudeLabel.setText("MaxAltitude: " + source.getValue());
    }


    private void createUIComponents() {

        jsMaxAltitude = new javax.swing.JSlider(JSlider.HORIZONTAL, 0, 10000, Integer.parseInt(controller.getProperty(PropertyLabel.MaxAltitude)));
        jsMinAltitude = new javax.swing.JSlider(JSlider.HORIZONTAL, 0, 4000, Integer.parseInt(controller.getProperty(PropertyLabel.MinAltitude)));
        maxAltitudeLabel = new javax.swing.JLabel();
        maxAltitudeLabel.setText("MaxAltitude: " + controller.getProperty(PropertyLabel.MaxAltitude));
        minAltitudeLabel = new javax.swing.JLabel();
        minAltitudeLabel.setText("MinAltitude: " + controller.getProperty(PropertyLabel.MinAltitude));
        jsMinAltitude.addChangeListener(this::jsMinAltitudeStateChange);
        jsMaxAltitude.addChangeListener(this::jsMaxAltitudeStateChange);
        portalHeightText = new javax.swing.JTextField(8);
        cameraConstantText = new javax.swing.JTextField();
        videoFrameText = new javax.swing.JTextField();

        portalHeightText.setText(controller.getProperty(PropertyLabel.PortalHeight));
        videoFrameText.setText(controller.getProperty(PropertyLabel.VideoFrameRate));
        cameraConstantText.setText(controller.getProperty(PropertyLabel.CameraConstant));

        portalHeightText.addActionListener(e -> {
            JTextField textfield = (JTextField) e.getSource();
            String text = textfield.getText();
            try {
                int i = Integer.parseInt(text);
                controller.propHandler.save(PropertyLabel.PortalHeight, i);
                Config.PORTAL_HEIGHT=i;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        cameraConstantText.addActionListener(e -> {
            JTextField textfield = (JTextField) e.getSource();
            String text = textfield.getText();
            try {
                double i = Double.parseDouble(text);
                controller.propHandler.saveCameraConstant(i);
                Config.CAMERA_CONSTANT=i;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        videoFrameText.addActionListener(e -> {
            JTextField textfield = (JTextField) e.getSource();
            String text = textfield.getText();
            try {
                int i = Integer.parseInt(text);
                controller.propHandler.save(PropertyLabel.VideoFrameRate, i);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


    }
}
