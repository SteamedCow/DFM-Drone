package dfmDrone.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import dfmDrone.data.Config;
import dfmDrone.data.PropertyHandler.PropertyLabel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.io.IOException;

/**
 * Created by as on 6/5/17.
 */
public class OptionsPanel extends JFrame
{
    private JSlider jsMaxAltitude;
    private JPanel Jpanel;
    private JTextField maxAltitudeLabel;
    private JTextField portalHeightText;
    private JSlider jsMinAltitude;
    private JTextField cameraConstantText;
    private JTextField videoFrameText;
    private JLabel minAltitudeLabel;
    private final Controller controller;

    public OptionsPanel(Controller controller) {
        this.controller = controller;
        $$$setupUI$$$();
        createUIComponents();
        setTitle("settings");
        setContentPane(Jpanel);
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    public OptionsPanel(HelpPanel helppanel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void jsMaxAltitudeStateChange(ChangeEvent evt) {
        JSlider source = (JSlider) evt.getSource();
        if (!source.getValueIsAdjusting()) {
            try {
                controller.getDrone().setMaxAltitude(source.getValue());
                maxAltitudeLabel.setText("MaxAltitude: " + source.getValue());
                controller.propHandler.save(PropertyLabel.MaxAltitude, source.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        maxAltitudeLabel.setText("MaxAltitude: " + source.getValue());
        maxAltitudeLabel.validate();
    }

    public void jsMinAltitudeStateChange(ChangeEvent evt) {
        JSlider source = (JSlider) evt.getSource();
        if (!source.getValueIsAdjusting()) {
            try {
                controller.getDrone().setMinAltitude(source.getValue());
                minAltitudeLabel.setText("MinAltitude: " + source.getValue());
                controller.propHandler.save(PropertyLabel.MinAltitude, source.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        maxAltitudeLabel.setText("MinAltitude: " + source.getValue());
    }

    private void createUIComponents() {
        jsMaxAltitude = new JSlider(JSlider.HORIZONTAL, 0, 10000, Integer.parseInt(controller.getProperty(PropertyLabel.MaxAltitude)));
        jsMinAltitude = new JSlider(JSlider.HORIZONTAL, 0, 4000, Integer.parseInt(controller.getProperty(PropertyLabel.MinAltitude)));
        maxAltitudeLabel = new JTextField();
        maxAltitudeLabel.setText("MaxAltitude: " + controller.getProperty(PropertyLabel.MaxAltitude));
        minAltitudeLabel = new JLabel();
        minAltitudeLabel.setText("MinAltitude: " + controller.getProperty(PropertyLabel.MinAltitude));
        jsMinAltitude.addChangeListener(this::jsMinAltitudeStateChange);
        jsMaxAltitude.addChangeListener(this::jsMaxAltitudeStateChange);
        portalHeightText = new JTextField(8);
        cameraConstantText = new JTextField();
        videoFrameText = new JTextField();

        portalHeightText.setText(controller.getProperty(PropertyLabel.PortalHeight));
        videoFrameText.setText(controller.getProperty(PropertyLabel.VideoFrameRate));
        cameraConstantText.setText(controller.getProperty(PropertyLabel.CameraConstant));

        portalHeightText.addActionListener(e -> {
            JTextField textfield = (JTextField) e.getSource();
            String text = textfield.getText();
            try {
                int i = Integer.parseInt(text);
                controller.propHandler.save(PropertyLabel.PortalHeight, i);
                Config.PORTAL_HEIGHT = i;
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
                Config.CAMERA_CONSTANT = i;
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        Jpanel = new JPanel();
        Jpanel.setLayout(new GridLayoutManager(12, 3, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        Jpanel.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        Jpanel.add(spacer2, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        jsMaxAltitude.setMajorTickSpacing(2500);
        jsMaxAltitude.setMaximum(10000);
        jsMaxAltitude.setMinorTickSpacing(500);
        jsMaxAltitude.setPaintLabels(true);
        jsMaxAltitude.setPaintTicks(true);
        Jpanel.add(jsMaxAltitude, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Jpanel.add(portalHeightText, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        jsMinAltitude.setMajorTickSpacing(1000);
        jsMinAltitude.setMaximum(4000);
        jsMinAltitude.setMinorTickSpacing(250);
        jsMinAltitude.setPaintLabels(true);
        jsMinAltitude.setPaintTicks(true);
        Jpanel.add(jsMinAltitude, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        final JLabel label1 = new JLabel();
        label1.setText("Portal Height");
        Jpanel.add(label1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Jpanel.add(minAltitudeLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Camera Constant");
        Jpanel.add(label2, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Jpanel.add(cameraConstantText, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Video Frame Rate");
        Jpanel.add(label3, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Jpanel.add(videoFrameText, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        maxAltitudeLabel.setEditable(false);
        Jpanel.add(maxAltitudeLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label1.setLabelFor(portalHeightText);
        label2.setLabelFor(cameraConstantText);
        label3.setLabelFor(videoFrameText);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return Jpanel;
    }
}