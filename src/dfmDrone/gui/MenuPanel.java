package dfmDrone.gui;

import de.yadrone.base.navdata.BatteryListener;
import dfmDrone.ai.CommandQueue.Command;
import dfmDrone.ai.CommandQueue.PushType;
import dfmDrone.utils.DFMLogger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import whiteBalance.exceptions.DetectionException;
import whiteBalance.tools.Calibrator;

/**
 * MenuPanel
 * <b>OBS: Nogle metoder i denne klasse kræver at .form filen også redigeres!</b>
 * @author Lasse
 * @version 08-03-2017
 */
public class MenuPanel extends javax.swing.JPanel
{
    private final GUIController controller;
    protected static Integer[] colorOffset = null;
    
    private final HashMap<InfoLabel, Integer> tableMatrix = new HashMap<>();
    private DefaultTableModel jtModel;
    
    public MenuPanel(GUIController controller) {
        this.controller = controller;
        initComponents();
        initTable();
        
        DefaultCaret caret = (DefaultCaret)jTextArea1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        
        updateLogger("rgerf1");
        updateLogger("ashdiashdahsidh2");
        updateLogger("blahblah3");
        updateLogger("kjaekjas4");
    }
    
    private enum InfoLabel {
        Status, LastCommand, Busy, Battery, Distance, Tilt, Roll, Rotate;
    }
    
    private void initTable() {
        tableMatrix.put(InfoLabel.Status, 0);
        tableMatrix.put(InfoLabel.LastCommand, 1);
        tableMatrix.put(InfoLabel.Busy, 2);
        tableMatrix.put(InfoLabel.Battery, 3);
        tableMatrix.put(InfoLabel.Distance, 4);
        tableMatrix.put(InfoLabel.Tilt, 5);
        tableMatrix.put(InfoLabel.Roll, 6);
        tableMatrix.put(InfoLabel.Rotate, 7);
        
        jtModel = (DefaultTableModel) jtInfoTable.getModel();
        jtModel.setRowCount(tableMatrix.size());
        
        for (Entry<InfoLabel, Integer> rowSet : tableMatrix.entrySet()) 
            jtModel.setValueAt(rowSet.getKey().toString(), rowSet.getValue(), 0);
    }
    
    protected void addVideoPanel(JPanel video) {
        jpVideo.add(video);
    }
    
    protected void addBatteryListener(BatteryListener batteryListener) {
        //Setup Battery Listener
        System.out.println("\n---- Setup Battery Listener ---");
        DFMLogger.logger.config("Setup Battery Listener");
        controller.drone.getNavDataManager().addBatteryListener(batteryListener);
    }
    
    /**
     * <b>OBS: Rediger kun i denne metode hvis form filen også redigeres!</b>
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTitle = new javax.swing.JLabel();
        jspUI = new javax.swing.JScrollPane();
        jpUI = new javax.swing.JPanel();
        jbKill = new javax.swing.JButton();
        jbStartStop = new javax.swing.JButton();
        jbWB = new javax.swing.JButton();
        jspInfTable = new javax.swing.JScrollPane();
        jtInfoTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jpVideo = new javax.swing.JPanel();

        jlTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlTitle.setText("Don Frankos zzKillerDronezz");

        jspUI.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jspUI.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jbKill.setBackground(new java.awt.Color(240, 0, 0));
        jbKill.setText("Kill");
        jbKill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbKillActionPerformed(evt);
            }
        });

        jbStartStop.setText("Start");
        jbStartStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbStartStopActionPerformed(evt);
            }
        });

        jbWB.setText("White Balance");
        jbWB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbWBActionPerformed(evt);
            }
        });

        jspInfTable.setBorder(null);
        jspInfTable.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jtInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtInfoTable.setEnabled(false);
        jtInfoTable.getTableHeader().setReorderingAllowed(false);
        jspInfTable.setViewportView(jtInfoTable);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jpUILayout = new javax.swing.GroupLayout(jpUI);
        jpUI.setLayout(jpUILayout);
        jpUILayout.setHorizontalGroup(
            jpUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpUILayout.createSequentialGroup()
                        .addGroup(jpUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpUILayout.createSequentialGroup()
                                .addComponent(jbStartStop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbKill))
                            .addComponent(jbWB))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpUILayout.createSequentialGroup()
                        .addGroup(jpUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jspInfTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jpUILayout.setVerticalGroup(
            jpUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpUILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbStartStop)
                    .addComponent(jbKill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspInfTable, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbWB)
                .addContainerGap())
        );

        jspUI.setViewportView(jpUI);

        javax.swing.GroupLayout jpVideoLayout = new javax.swing.GroupLayout(jpVideo);
        jpVideo.setLayout(jpVideoLayout);
        jpVideoLayout.setHorizontalGroup(
            jpVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );
        jpVideoLayout.setVerticalGroup(
            jpVideoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jspUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpVideo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpVideo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspUI))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * <b>OBS: Rediger kun i denne metode hvis form filen også redigeres!</b>
     */
    private void jbStartStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbStartStopActionPerformed
        updateLogger("SOmethingsadasd");
        if(controller.cmdQ.isDroneFlying()) {
            DFMLogger.logger.info("Land command requested");
            try {
                if(controller.cmdQ.add(Command.Land, -1, -1, PushType.Instant, PushType.Block, PushType.IgnoreBlock, PushType.IgnoreBusy)) {
                    setInfoValue(InfoLabel.Status, "Landing");
                    jbStartStop.setText("Start");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                setInfoValue(InfoLabel.Status, "Error: Could not land!");
            }
            setInfoValue(InfoLabel.Status, "Landed");
        }
        else {
            DFMLogger.logger.info("Takeoff command requested");
            try {
//                cmd.animateLEDs(10);
                controller.cmdQ.add(Command.TakeOff, -1, -1, PushType.Block);
                
                setInfoValue(InfoLabel.Status, "Flying");
                jbStartStop.setText("Stop");
//                
//                cmd.scan();
            } 
            catch (Exception e) {
                e.printStackTrace();
                setInfoValue(InfoLabel.Status, "Error: Could not start!");
            }
        }
    }//GEN-LAST:event_jbStartStopActionPerformed

    /**
     * <b>OBS: Rediger kun i denne metode hvis form filen også redigeres!</b>
     */
    private void jbKillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbKillActionPerformed
        controller.cmdQ.add(Command.Kill, -1, -1, PushType.Block, PushType.Instant, PushType.IgnoreBlock, PushType.IgnoreBusy);
    }//GEN-LAST:event_jbKillActionPerformed

    /**
     * <b>OBS: Rediger kun i denne metode hvis form filen også redigeres!</b>
     */
    private void jbWBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbWBActionPerformed
        try {
            Calibrator calib = new Calibrator(controller.getVideoFrame(), true);
            colorOffset = calib.calibrate(3, 3, 3);
        } catch (DetectionException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbWBActionPerformed
    
    protected void updateBusy(Boolean busy) {
        setInfoValue(InfoLabel.Busy, busy.toString());
    }
    
    protected void updateBatteryDisplay(int batteryLevel) {
        setInfoValue(InfoLabel.Battery, (100 - batteryLevel) + "%");
    }
    
    protected void updateDistanceDisplay(double distance) {
        StringBuilder sb = new StringBuilder();
        if(distance > 1000000)
            sb.append(String.format("%.2f", distance / 1000000)).append(" km");
        else if(distance > 1000)
            sb.append(String.format("%.2f", distance / 1000)).append(" m");
        else if(distance > 10)
            sb.append(String.format("%.1f", distance / 10)).append(" cm");
        else
            sb.append(String.format("%.0f", distance)).append(" mm");
        
        setInfoValue(InfoLabel.Distance, sb.toString());
    }
    
    protected void updateNavigationDisplay(Float tilt, Float roll, Float spin) {
        setInfoValue(InfoLabel.Tilt, tilt.toString());
        setInfoValue(InfoLabel.Roll, roll.toString());
        setInfoValue(InfoLabel.Rotate, spin.toString());
    }
    
    protected void updateLastCMDDisplay(String cmd) {
        setInfoValue(InfoLabel.LastCommand, cmd);
    }
    
    protected void updateLogger(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss - ");
      //  jTextArea1.append(sdf.format(new Date()) + s +"\n");
        jTextArea1.setText(sdf.format(new Date()) + s +"\n" + jTextArea1.getText());
        
    }
    
    
    
    private void setInfoValue(InfoLabel label, String value) {
        if(tableMatrix.containsKey(label))
            jtModel.setValueAt(value, tableMatrix.get(label), 1);
        else
            throw new NoSuchFieldError("No row value assigned to the label: " + label.name());
    }
    
    /**
     * <b>OBS: Rediger kun disse variabler hvis form filen også redigeres!</b>
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jbKill;
    private javax.swing.JButton jbStartStop;
    private javax.swing.JButton jbWB;
    private javax.swing.JLabel jlTitle;
    private javax.swing.JPanel jpUI;
    private javax.swing.JPanel jpVideo;
    private javax.swing.JScrollPane jspInfTable;
    private javax.swing.JScrollPane jspUI;
    private javax.swing.JTable jtInfoTable;
    // End of variables declaration//GEN-END:variables
}
