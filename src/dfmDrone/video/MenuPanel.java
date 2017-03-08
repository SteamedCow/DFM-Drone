package dfmDrone.video;

import de.yadrone.base.IARDrone;
import dfmDrone.examples.Commander;
import java.awt.Color;

/**
 * MenuPanel
 * @author Lasse
 * @version 08-03-2017
 */
public class MenuPanel extends javax.swing.JPanel
{
    private static boolean running = false;
    private final Commander cmd;
    
    public MenuPanel(IARDrone drone) {
        initComponents();
        this.cmd = new Commander(drone, drone.getCommandManager());
        
        jpVideo.add(new VideoPanel(drone));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbStartStop = new javax.swing.JButton();
        jbKill = new javax.swing.JButton();
        jlStatus = new javax.swing.JLabel();
        jpVideo = new javax.swing.JPanel();

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
            .addGap(0, 260, Short.MAX_VALUE)
        );

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
                .addComponent(jlStatus)
                .addContainerGap(205, Short.MAX_VALUE))
            .addComponent(jpVideo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbKill)
                    .addComponent(jbStartStop)
                    .addComponent(jlStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpVideo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
//                cmd.takeOff();
                cmd.animateLEDs(10);
                running = true;
                jlStatus.setText("Status: Flying");
                jbStartStop.setText("Stop");
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbKill;
    private javax.swing.JButton jbStartStop;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JPanel jpVideo;
    // End of variables declaration//GEN-END:variables
}