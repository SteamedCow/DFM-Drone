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
    private final static String welcomeText = "Velkommen til drone projektet.\n"
            + "I denne menu vil du finde hjælp og forklaring på diverse emner i interfacet.\n"
            + "Brug venstremenuen til at navigere mellem emnerne.";
    
    private final static String interfaceText = "Det forventes at der er oprettet forbindelse til dronen inden kørsel af program.\n\n"
            + "Når dronen er forbundet og vores program køres, vent da på at kameraet opretter forbindelse, før dronen startes, for bedste resultat\n\n"
            + "Start-knap: Starter dronen, den vil lave en take-off, og hover indtil den finder hvad den søger efter\n\n"
            + "Kill-knap: Får dronen til at akut lande\n\n"
            + "Binary-knap: Viser hvad dronen ser i sort/hvid. Dette giver et godt indblik i hvor meget af de røde ringe den registrere\n\n"
            + "Tabel: Her kan du se informationer om dronens status, som fx. hvor meget kapacitet batteriet har tilbage, og hvad den sidste kommando den udførte var\n\n"
            + "Tekstfelt: Her kan du se alle kommandoer dronen har udført, i rækkefølge med tidspunkt";
    
    private final static String manualText = "Manual Control er et simpelt panel hvor du kan overwrite dronens nuværende kommandoer.\n\n"
            + "tilgængelige kommandoer:"
            + "Op\n\nNed\n\nSpin mod højre\n\nSpin mod venstre\n\nFlyv til højre\n\nFlyv til venstre\n\nFlyv frem\n\nFlyv bagud";
    
    private final static String hsvText = "I HSV menuen kan du indstille dronens kameras HSV (Hue Saturation Value), for bedre at kunne se objekterne du søger";
    
    private final static String whitebalanceText = "White balance kører vores whitebalance program.\n\n"
            + "Whitebalance bruger dronens kamera justere farverne efter omgivelsernes belysning.\n\n"
            + "Når programmet køres skal brugeren holde et udprintet billede* af firkanter med forskellige farver op foran dronens kamera.\n"
            + "Brugeren skal holde billedet så præcist som muligt, og tage et billede, hvorefter WhiteBalance programmet vil validere om den kan se alle farver i billedet, og de fire cirkler rundt i kanterne\n"
            + "såfremt alt er fundet, vil programmet indstille farverne til passende værdier.\n\n"
            + "*Billede findes i \"WhiteBalance\\WhiteBalance\\chart\\White Balance Color Chart (calibaretor)\"";
    
    private final static String settingsText = "I settings menuen kan du indstille dronen efter behov\n\n"
            + "MaxAltitude: indstiller hvor højt dronen må flyve i mm\n\n"
            + "MinAltitude: indstiller hvor lavt dronen må flyve i mm\n\n"
            + "Portal Height:\n\n"
            + "Camera Constant\n\n"
            + "Video Frame Rate: indstiller opdateringshastigheden på dronens kamera";
    
    public HelpPanel() {
        initComponents();
        
        jlNavigation.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Welcome", "Interface", "Manual Control", "HSV filter", "White balance", "Settings"};
            
            @Override
            public int getSize() { return strings.length; }
            
            @Override
            public String getElementAt(int i) { return strings[i]; }
        });
        
        setWelcome();
        
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                jlNavigation = (JList) mouseEvent.getSource();
                int index = jlNavigation.locationToIndex(mouseEvent.getPoint());
                switch (index) {
                    default: case 0:
                        setWelcome(); break;
                    case 1:
                        setInterface(); break;
                    case 2:
                        setManual(); break;
                    case 3:
                        setHSV(); break;
                    case 4:
                        setWhitebalance(); break;
                    case 5:
                        setSettings(); break;
                }
            }
        };
        
        jlNavigation.addMouseListener(mouseListener);
        taContent.setEditable(false);
    }
    
    private void setWelcome() {
        taContent.setText(welcomeText);
    }
    
    private void setInterface() {
        taContent.setText(interfaceText);
    }
    
    private void setManual() {
        taContent.setText(manualText);
    }
    
    private void setHSV() {
        taContent.setText(hsvText);
    }
    
    private void setWhitebalance() {
        taContent.setText(whitebalanceText);
    }
    
    private void setSettings() {
        taContent.setText(settingsText);
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
        jlNavigation = new javax.swing.JList<String>();
        taContent = new java.awt.TextArea("", 20 , 100 , SCROLLBARS_VERTICAL_ONLY);

        textField1.setText("textField1");

        jlNavigation.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlNavigation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taContent, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taContent, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> jlNavigation;
    private java.awt.TextArea taContent;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}