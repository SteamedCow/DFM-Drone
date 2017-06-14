/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dfmDrone.gui;

import static java.awt.TextArea.SCROLLBARS_VERTICAL_ONLY;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;

/**
 *
 * @author magnu
 */
public class HelpPanel extends javax.swing.JPanel {
    
    String welcomeText = "Velkommen til drone projektet.\n"
            + "I denne menu vil du finde hjælp og forklaring på diverse ting i interfacet.";
    String interfaceText = "Det forventes at der er oprettet forbindelse til dronen inden kørsel af program.\n\n"
            + "Når dronen er forbundet og vores program køres, vent da på at kameraet opretter forbindelse, før dronen startes, for bedste resultat\n\n"
            + "Start-knap: Starter dronen, den vil lave en take-off, og hover indtil den finder hvad den søger efter\n\n"
            + "Kill-knap: Får dronen til at akut lande\n\n"
            + "Binary-knap: Viser hvad dronen ser i sort/hvid. Dette giver et godt indblik i hvor meget af de røde ringe den registrere\n\n"
            + "Tabel: Her kan du se informationer om dronens status, som fx. hvor meget kapacitet batteriet har tilbage, og hvad den sidste kommando den udførte var\n\n"
            + "Tekstfelt: Her kan du se alle kommandoer dronen har udført, i rækkefølge med tidspunkt";
    String manualText = "Manual Control er et simpelt panel hvor du kan overwrite dronens nuværende kommandoer.\n\n"
            + "tilgængelige kommandoer:"
            + "Op\n\nNed\n\nSpin mod højre\n\nSpin mod venstre\n\nFlyv til højre\n\nFlyv til venstre\n\nFlyv frem\n\nFlyv bagud";
    String hsvText = "I HSV menuen kan du indstille dronens kameras HSV (Hue Saturation Value), for bedre at kunne se objekterne du søger";
    String whitebalanceText = "White balance kører vores whitebalance program.\n\n"
            + "Whitebalance bruger dronens kamera justere farverne efter omgivelsernes belysning.\n\n"
            + "Når programmet køres skal brugeren holde et udprintet billede* af firkanter med forskellige farver op foran dronens kamera.\n"
            + "Brugeren skal holde billedet så præcist som muligt, og tage et billede, hvorefter WhiteBalance programmet vil validere om den kan se alle farver i billedet, og de fire cirkler rundt i kanterne\n"
            + "såfremt alt er fundet, vil programmet indstille farverne til passende værdier.\n\n"
            + "*Billede findes i \"WhiteBalance\\WhiteBalance\\chart\\White Balance Color Chart (calibaretor)\"";
    String settingsText = "I settings menuen kan du indstille dronen efter behov\n\n"
            + "MaxAltitude: indstiller hvor højt dronen må flyve i mm\n\n"
            + "MinAltitude: indstiller hvor lavt dronen må flyve i mm\n\n"
            + "Portal Height:\n\n"
            + "Camera Constant\n\n"
            + "Video Frame Rate: indstiller opdateringshastigheden på dronens kamera";
    
    
    /**
     * Creates new form HelpPanel
     */
    public HelpPanel() {
        initComponents();
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Welcome", "Interface", "Manual Control", "HSV filter", "White balance", "Settings"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        setWelcome();
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                jList1 = (JList) mouseEvent.getSource();
                int index = jList1.locationToIndex(mouseEvent.getPoint());
                    switch (index) {
                        case 0:
                            setWelcome();
                            break;
                        case 1:
                            setInterface();
                            break;
                        case 2:
                            setManual();
                            break;
                        case 3:
                            setHSV();
                            break;
                        case 4:
                            setWhitebalance();
                            break;
                        case 5:
                            setSettings();
                            break;
                        default:
                            setWelcome();
                            break;
                    }
            }
        };
        jList1.addMouseListener(mouseListener);
    }
    
    //{"Welcome", "Interface", "Manual Control", "HSV filter", "White balance", "Settings"}
    private void setWelcome() {
        textArea1.setText(welcomeText);
    }
    private void setInterface() {
        textArea1.setText(interfaceText);
    }
    private void setManual() {
        textArea1.setText(manualText);
    }
    private void setHSV() {
        textArea1.setText(hsvText);
    }
    private void setWhitebalance() {
        textArea1.setText(whitebalanceText);
    }
    private void setSettings() {
        textArea1.setText(settingsText);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new java.awt.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        textArea1 = new java.awt.TextArea("", 20 , 100 , SCROLLBARS_VERTICAL_ONLY);

        textField1.setText("textField1");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(textArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.TextArea textArea1;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
    
}
