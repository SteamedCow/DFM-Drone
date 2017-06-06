package dfmDrone;

import dfmDrone.data.DummyData;

/**
 * HSVSettingsPanel
 * @author Lasse
 * @version 06-06-2017
 */
public class HSVSettingsPanel extends javax.swing.JPanel
{
    public HSVSettingsPanel() {
        initComponents();
        
        LH1Slider.setValue(DummyData.lowerB1H);
        LH1Value.setText("" + DummyData.lowerB1H);
        LS1Slider.setValue(DummyData.lowerB1S);
        LS1Value.setText("" + DummyData.lowerB1S);
        LV1Slider.setValue(DummyData.lowerB1V);
        LV1Value.setText("" + DummyData.lowerB1V);
        
        UH1Slider.setValue(DummyData.upperB1H);
        UH1Value.setText("" + DummyData.upperB1H);
        US1Slider.setValue(DummyData.upperB1S);
        US1Value.setText("" + DummyData.upperB1S);
        UV1Slider.setValue(DummyData.upperB1V);
        UV1Value.setText("" + DummyData.upperB1V);
        
        LH2Slider.setValue(DummyData.lowerB2H);
        LH2Value.setText("" + DummyData.lowerB2H);
        LS2Slider.setValue(DummyData.lowerB2S);
        LS2Value.setText("" + DummyData.lowerB2S);
        LV2Slider.setValue(DummyData.lowerB2V);
        LV2Value.setText("" + DummyData.lowerB2V);
        
        UH2Slider.setValue(DummyData.upperB2H);
        UH2Value.setText("" + DummyData.upperB2H);
        US2Slider.setValue(DummyData.upperB2S);
        US2Value.setText("" + DummyData.upperB2S);
        UV2Slider.setValue(DummyData.upperB2V);
        UV2Value.setText("" + DummyData.upperB2V);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        LH1Slider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        LH1Value = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LS1Slider = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        LS1Value = new javax.swing.JLabel();
        LV1Slider = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        LV1Value = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        UV1Slider = new javax.swing.JSlider();
        jLabel10 = new javax.swing.JLabel();
        UV1Value = new javax.swing.JLabel();
        UH1Slider = new javax.swing.JSlider();
        jLabel12 = new javax.swing.JLabel();
        UH1Value = new javax.swing.JLabel();
        US1Slider = new javax.swing.JSlider();
        jLabel14 = new javax.swing.JLabel();
        US1Value = new javax.swing.JLabel();
        LV2Slider = new javax.swing.JSlider();
        US2Slider = new javax.swing.JSlider();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        LV2Value = new javax.swing.JLabel();
        US2Value = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        UV2Slider = new javax.swing.JSlider();
        jLabel21 = new javax.swing.JLabel();
        UV2Value = new javax.swing.JLabel();
        UH2Slider = new javax.swing.JSlider();
        jLabel23 = new javax.swing.JLabel();
        LH2Slider = new javax.swing.JSlider();
        jLabel24 = new javax.swing.JLabel();
        LH2Value = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        LS2Slider = new javax.swing.JSlider();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        LS2Value = new javax.swing.JLabel();
        UH2Value = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Range 1");

        LH1Slider.setMaximum(180);
        LH1Slider.setValue(90);
        LH1Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                LH1SliderStateChanged(evt);
            }
        });

        jLabel2.setText("Hue");

        LH1Value.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Lower");

        LS1Slider.setMaximum(255);
        LS1Slider.setValue(128);
        LS1Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                LS1SliderStateChanged(evt);
            }
        });

        jLabel5.setText("Saturation");

        LS1Value.setText("0");

        LV1Slider.setMaximum(255);
        LV1Slider.setValue(128);
        LV1Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                LV1SliderStateChanged(evt);
            }
        });

        jLabel7.setText("Value");

        LV1Value.setText("0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Upper");

        UV1Slider.setMaximum(255);
        UV1Slider.setValue(128);
        UV1Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                UV1SliderStateChanged(evt);
            }
        });

        jLabel10.setText("Value");

        UV1Value.setText("0");

        UH1Slider.setMaximum(180);
        UH1Slider.setValue(90);
        UH1Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                UH1SliderStateChanged(evt);
            }
        });

        jLabel12.setText("Hue");

        UH1Value.setText("0");

        US1Slider.setMaximum(255);
        US1Slider.setValue(128);
        US1Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                US1SliderStateChanged(evt);
            }
        });

        jLabel14.setText("Saturation");

        US1Value.setText("0");

        LV2Slider.setMaximum(255);
        LV2Slider.setValue(128);
        LV2Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                LV2SliderStateChanged(evt);
            }
        });

        US2Slider.setMaximum(255);
        US2Slider.setValue(128);
        US2Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                US2SliderStateChanged(evt);
            }
        });

        jLabel16.setText("Value");

        jLabel17.setText("Saturation");

        LV2Value.setText("0");

        US2Value.setText("0");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Upper");

        UV2Slider.setMaximum(255);
        UV2Slider.setValue(128);
        UV2Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                UV2SliderStateChanged(evt);
            }
        });

        jLabel21.setText("Value");

        UV2Value.setText("0");

        UH2Slider.setMaximum(180);
        UH2Slider.setValue(90);
        UH2Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                UH2SliderStateChanged(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Range 2");

        LH2Slider.setMaximum(180);
        LH2Slider.setValue(90);
        LH2Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                LH2SliderStateChanged(evt);
            }
        });

        jLabel24.setText("Hue");

        LH2Value.setText("0");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Lower");

        LS2Slider.setMaximum(255);
        LS2Slider.setValue(128);
        LS2Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                LS2SliderStateChanged(evt);
            }
        });

        jLabel27.setText("Saturation");

        jLabel28.setText("Hue");

        LS2Value.setText("0");

        UH2Value.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel23))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(US1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(UV1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(UH1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(UH1Value, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                            .addComponent(UV1Value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(US1Value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LV1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(LH1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(LS1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LH1Value, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(LS1Value, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(LV1Value, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel26))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(US2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(UV2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(UH2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(UH2Value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(UV2Value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(US2Value, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel27)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LV2Slider, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                            .addComponent(LH2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(LS2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LH2Value, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(LS2Value, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(LV2Value, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LH1Value)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(LH1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LS1Value)
                    .addComponent(jLabel5)
                    .addComponent(LS1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LV1Value)
                    .addComponent(jLabel7)
                    .addComponent(LV1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(UH1Value)
                    .addComponent(jLabel12)
                    .addComponent(UH1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(US1Value)
                    .addComponent(jLabel14)
                    .addComponent(US1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(UV1Value)
                    .addComponent(jLabel10)
                    .addComponent(UV1Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LH2Value)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(LH2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LS2Value)
                    .addComponent(jLabel27)
                    .addComponent(LS2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LV2Value)
                    .addComponent(jLabel16)
                    .addComponent(LV2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(UH2Value)
                    .addComponent(jLabel28)
                    .addComponent(UH2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(US2Value)
                    .addComponent(jLabel17)
                    .addComponent(US2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(UV2Value)
                    .addComponent(jLabel21)
                    .addComponent(UV2Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LH1SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_LH1SliderStateChanged
        LH1Value.setText("" + LH1Slider.getValue());
        DummyData.lowerB1H = LH1Slider.getValue();
    }//GEN-LAST:event_LH1SliderStateChanged

    private void LS1SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_LS1SliderStateChanged
        LS1Value.setText("" + LS1Slider.getValue());
        DummyData.lowerB1S = LS1Slider.getValue();
    }//GEN-LAST:event_LS1SliderStateChanged

    private void LV1SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_LV1SliderStateChanged
        LV1Value.setText("" + LV1Slider.getValue());
        DummyData.lowerB1V = LV1Slider.getValue();
    }//GEN-LAST:event_LV1SliderStateChanged

    private void UH1SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_UH1SliderStateChanged
        UH1Value.setText("" + UH1Slider.getValue());
        DummyData.upperB1H = UH1Slider.getValue();
    }//GEN-LAST:event_UH1SliderStateChanged

    private void US1SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_US1SliderStateChanged
        US1Value.setText("" + US1Slider.getValue());
        DummyData.upperB1S = US1Slider.getValue();
    }//GEN-LAST:event_US1SliderStateChanged

    private void UV1SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_UV1SliderStateChanged
        UV1Value.setText("" + UV1Slider.getValue());
        DummyData.upperB1V = UV1Slider.getValue();
    }//GEN-LAST:event_UV1SliderStateChanged

    private void LS2SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_LS2SliderStateChanged
        LS2Value.setText("" + LS2Slider.getValue());
        DummyData.lowerB2S = LS2Slider.getValue();
    }//GEN-LAST:event_LS2SliderStateChanged

    private void LV2SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_LV2SliderStateChanged
        LV2Value.setText("" + LV2Slider.getValue());
        DummyData.lowerB2V = LV2Slider.getValue();
    }//GEN-LAST:event_LV2SliderStateChanged

    private void UH2SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_UH2SliderStateChanged
        UH2Value.setText("" + UH2Slider.getValue());
        DummyData.upperB2H = UH2Slider.getValue();
    }//GEN-LAST:event_UH2SliderStateChanged

    private void US2SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_US2SliderStateChanged
        US2Value.setText("" + US2Slider.getValue());
        DummyData.upperB2S = US2Slider.getValue();
    }//GEN-LAST:event_US2SliderStateChanged

    private void UV2SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_UV2SliderStateChanged
        UV2Value.setText("" + UV2Slider.getValue());
        DummyData.upperB2V = UV2Slider.getValue();
    }//GEN-LAST:event_UV2SliderStateChanged

    private void LH2SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_LH2SliderStateChanged
        LH2Value.setText("" + LH2Slider.getValue());
        DummyData.lowerB2H = LH2Slider.getValue();
    }//GEN-LAST:event_LH2SliderStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider LH1Slider;
    private javax.swing.JLabel LH1Value;
    private javax.swing.JSlider LH2Slider;
    private javax.swing.JLabel LH2Value;
    private javax.swing.JSlider LS1Slider;
    private javax.swing.JLabel LS1Value;
    private javax.swing.JSlider LS2Slider;
    private javax.swing.JLabel LS2Value;
    private javax.swing.JSlider LV1Slider;
    private javax.swing.JLabel LV1Value;
    private javax.swing.JSlider LV2Slider;
    private javax.swing.JLabel LV2Value;
    private javax.swing.JSlider UH1Slider;
    private javax.swing.JLabel UH1Value;
    private javax.swing.JSlider UH2Slider;
    private javax.swing.JLabel UH2Value;
    private javax.swing.JSlider US1Slider;
    private javax.swing.JLabel US1Value;
    private javax.swing.JSlider US2Slider;
    private javax.swing.JLabel US2Value;
    private javax.swing.JSlider UV1Slider;
    private javax.swing.JLabel UV1Value;
    private javax.swing.JSlider UV2Slider;
    private javax.swing.JLabel UV2Value;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}