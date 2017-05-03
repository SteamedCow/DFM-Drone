package dfmDrone.video;

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.BatteryListener;
import dfmDrone.examples.Commander;
import java.awt.Color;
import whiteBalance.exceptions.DetectionException;
import whiteBalance.tools.Calibrator;

/**
 * MenuPanel
 * @author Lasse
 * @version 08-03-2017
 */
public class MenuPanel extends javax.swing.JPanel
{
    private static boolean running = false;
    protected static Integer[] colorOffset = null;
    private final Commander cmd;
    private VideoPanel video;
    
    public MenuPanel(IARDrone drone) {
        initComponents();
        this.cmd = new Commander(drone, drone.getCommandManager());
        this.video = new VideoPanel(drone);
        
        jpVideo.add(this.video);
        
        //Setup Battery Listener
        System.out.println("\n---- Setup Battery Listener ---");
        drone.getNavDataManager().addBatteryListener(new BatteryListener() {
            @Override
            public void batteryLevelChanged(int i) {
                jlBattery.setText("Battery: " + (100 - i) + "%");
            }

            @Override
            public void voltageChanged(int i) { }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbStartStop = new javax.swing.JButton();
        jbKill = new javax.swing.JButton();
        jlStatus = new javax.swing.JLabel();
        jpVideo = new javax.swing.JPanel();
        jbWB = new javax.swing.JButton();

        jbStartStop.setText("Start");
        jbStartStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbStartStopActionPerformed(evt);
            }
        });

        jbKill.setBackground(new java.awt.Color(240, 0, 0));
        jbKill.setText("Kill");
        jbKill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbKillActionPerformed(evt);
            }
        });

        jlStatus.setText("Status: Landed");

        javax.swing.GroupLayout jpVideoLayout = new javax.swing.GroupLayout(jpVideo);
        jpVideo.setLayout(jpVideoLayout);
        jpVideoLayout.setHorizontalGroup(
            jpVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpVideoLayout.setVerticalGroup(
            jpVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );

        jlBattery.setText("Battery:");

        jlDistance.setText("Distance: ");

        jbWB.setText("White Balance");
        jbWB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbWBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbKill)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbStartStop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbWB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlStatus)
                .addContainerGap(98, Short.MAX_VALUE))
            .addComponent(jpVideo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jlBattery)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlDistance))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbKill)
                    .addComponent(jbStartStop)
                    .addComponent(jlStatus)
                    .addComponent(jbWB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpVideo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlBattery)
                    .addComponent(jlDistance)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbStartStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbStartStopActionPerformed
        if(running) {
            try {
                if(cmd.land()) {
                    running = false;
                jlStatus.setText("Status: Landing");
                    jbStartStop.setText("Start");
                }
            } 
            catch (Exception e) {
                e.printStackTrace();
                jlStatus.setForeground(Color.red);
                jlStatus.setText("Error: Could not land!");
            }
            jlStatus.setText("Status: Landed");
        }
        else {
            try {
                cmd.takeOff();
                
//                cmd.animateLEDs(10);
                running = true;
                jlStatus.setText("Status: Flying");
                jbStartStop.setText("Stop");
                Thread.sleep(7000);
                cmd.scan();
            } 
            catch (Exception e) {
                e.printStackTrace();
                jlStatus.setForeground(Color.red);
                jlStatus.setText("Error: Could not start!");
            }
        }
    }//GEN-LAST:event_jbStartStopActionPerformed

    private void jbKillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbKillActionPerformed
        cmd.kill();
    }//GEN-LAST:event_jbKillActionPerformed

    private void jbWBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbWBActionPerformed
        try {
            Calibrator calib = new Calibrator(video.image, true);
            colorOffset = calib.calibrate(3, 3, 3);
        } catch (DetectionException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbWBActionPerformed
    
    protected static void updateDistanceDisplay(double distance) {
        StringBuilder sb = new StringBuilder("Distance: ");
        if(distance > 1000000)
            sb.append(String.format("%.2f", distance / 1000000)).append(" km");
        else if(distance > 1000)
            sb.append(String.format("%.2f", distance / 1000)).append(" m");
        else if(distance > 10)
            sb.append(String.format("%.1f", distance / 10)).append(" cm");
        else
            sb.append(String.format("%.0f", distance)).append(" mm");
        
        jlDistance.setText(sb.toString());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbKill;
    private javax.swing.JButton jbStartStop;
    private javax.swing.JButton jbWB;
    private static final javax.swing.JLabel jlBattery = new javax.swing.JLabel();
    private static final javax.swing.JLabel jlDistance = new javax.swing.JLabel();
    private javax.swing.JLabel jlStatus;
    private javax.swing.JPanel jpVideo;
    // End of variables declaration//GEN-END:variables
}