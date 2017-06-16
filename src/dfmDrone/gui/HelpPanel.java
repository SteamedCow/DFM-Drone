package dfmDrone.gui;

import dfmDrone.utils.DFMLogger;
import java.awt.Color;
import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author magnu
 */
public class HelpPanel extends javax.swing.JPanel {
    private static final String URL_ATT_NAME = "URL";
    
    private final StyledDocument doc;
    private final Style labelStyle;
    private final Style urlStyle;
    private final SimpleAttributeSet titleStyle;
    private final SimpleAttributeSet highlightStyle;
    private final SimpleAttributeSet normal;
    
    public HelpPanel() {
        initComponents();
        
        //Turn autoscroll off
        DefaultCaret caret = (DefaultCaret) jtpContent.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        
        //Load doc
        doc = jtpContent.getStyledDocument();
        
        //Set tileStyle
        titleStyle = new SimpleAttributeSet();
        StyleConstants.setAlignment(titleStyle, StyleConstants.ALIGN_CENTER);
        StyleConstants.setBold(titleStyle, true);
        StyleConstants.setFontSize(titleStyle, 13);
        
        //Set HighlightStyle
        highlightStyle = new SimpleAttributeSet();
        StyleConstants.setAlignment(highlightStyle, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontSize(highlightStyle, 12);
        
        //Set normalStyle
        normal = new SimpleAttributeSet();
        StyleConstants.setAlignment(normal, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontSize(normal, 11);
        
        //Set labelStyle/icons
        StyleContext context = new StyleContext();
        labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);
        
        //Set urlStyle (not clicked)
        urlStyle = doc.addStyle(null, labelStyle);
        StyleConstants.setForeground(urlStyle, Color.BLUE);
        StyleConstants.setAlignment(urlStyle, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(urlStyle, 11);
        urlStyle.addAttribute(URL_ATT_NAME, new Object());
        
        jlNavigation.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {"Welcome", "Interface", "Manual Control", "HSV filter", "White balance", "Settings"};
            
            @Override
            public int getSize() { return strings.length; }
            
            @Override
            public String getElementAt(int i) { return strings[i]; }
        });
        
        setWelcome();
    }
    
    private void setWelcome() {
        try {
            doc.remove(0, doc.getLength());
            
            //Logo
            Icon logoIcon = new ImageIcon(getClass().getResource("/dfmDrone/gui/symbols/dfm_logo_36.png"));
            JLabel logoLabel = new JLabel(logoIcon);
            StyleConstants.setComponent(labelStyle, logoLabel);
            doc.insertString(0, "Logo", labelStyle);
            
            addText("\nVelkommen til DFM drone projektet.\n", titleStyle, doc);
            addText("\nI denne menu vil du finde hjælp og forklaring på diverse emner i interfacet.\n"
                    + "\nBrug venstremenuen til at navigere mellem emnerne.", highlightStyle, doc);
        } catch (BadLocationException e) {
            DFMLogger.logger.warning("Could not load help text for welcome");
            e.printStackTrace();
        }
    }
    
    private void setInterface() {
        try {
            //Menu icon
            Icon panelIcon = new ImageIcon(getClass().getResource("/dfmDrone/gui/symbols/gui_menu_help.jpg"));
            JLabel panelLabel = new JLabel(panelIcon);
            StyleConstants.setComponent(labelStyle, panelLabel);
            
            doc.remove(0, doc.getLength());
            addText("Interface\n", titleStyle, doc);
            addText("\n- Opstart -", highlightStyle, doc);
            addText("\nDet forventes at der er oprettet forbindelse til dronen inden kørsel af program.\n"
                    + "Når dronen er forbundet og DFM drone programet køres, vent da på at kameraet opretter forbindelse, før dronen startes.\n", normal, doc);
            addText("\n- Kontrolpanelet -\n", highlightStyle, doc);
            addText("\n ", titleStyle, doc);
            doc.insertString(doc.getLength(), "Panel\n", labelStyle);
            addText("\n1 - Videodisplay: Her vises videostreamen fra dronens kamera. Kameraet kan skiftes til at vise feed fra bundkameraet via menuen i toppen af vinduet\n"
                    + "\n2 - Tabel: Her kan du se informationer om dronens status, som fx. hvor meget kapacitet batteriet har tilbage, og hvad den sidste kommando den udførte var\n"
                    + "\n3 - Logfelt: Her kan du se alle kommandoer dronen har udført, i rækkefølge med tidspunkt\n"
                    + "\n4 - Batteri: Her vises hvor meget strøm der er tilbage på dronen. Dronen kan ikke starte hvis batteri nivauet er under 20% (vises som helt tomt batteri)\n"
                    + "\n5 - Start-knap: Starter dronen, den vil lave en take-off, og hover indtil den finder hvad den søger efter\n"
                    + "\n6 - Kill-knap: Får dronen til at akut lande\n"
                    + "\n7 - Binary-knap: Viser hvad dronen ser i sort/hvid. Dette giver et godt indblik i hvor meget af de røde ringe den registrere\n", normal, doc);
        } catch (BadLocationException e) {
            DFMLogger.logger.warning("Could not load help text for interface");
            e.printStackTrace();
        }
    }
    
    private void setManual() {
        try {
            //Manual icon
            Icon panelIcon = new ImageIcon(getClass().getResource("/dfmDrone/gui/symbols/manual_panel_help.jpg"));
            JLabel panelLabel = new JLabel(panelIcon);
            StyleConstants.setComponent(labelStyle, panelLabel);
            
            doc.remove(0, doc.getLength());
            addText("Manuel Styring\n", titleStyle, doc);
            addText("\nManual Control er et simpelt panel hvor du kan overwrite dronens nuværende kommandoer.\n", normal, doc);
            addText("\n ", titleStyle, doc);
            doc.insertString(doc.getLength(), "Panel\n", labelStyle);
            addText("tilgængelige kommandoer med genvejstaster:\n"
                    + "Op = SPACE\n"
                    + "Ned = CTRL\n"
                    + "Drej til højre = E\n"
                    + "Drej til venstre = Q\n"
                    + "Flyv til højre = A\n"
                    + "Flyv til venstre = D\n"
                    + "Flyv frem = W\n"
                    + "Flyv tilbage = S", normal, doc);
        } catch (BadLocationException e) {
            DFMLogger.logger.warning("Could not load help text for manual");
            e.printStackTrace();
        }
    }
    
    private void setHSV() {
        try {
            doc.remove(0, doc.getLength());
            addText("Farve Justering\n", titleStyle, doc);
            addText("\nI HSV menuen kan du indstille dronens kameras HSV (Hue Saturation Value), for bedre at kunne se objekterne du søger", normal, doc);
        } catch (BadLocationException e) {
            DFMLogger.logger.warning("Could not load help text for HSV");
            e.printStackTrace();
        }
    }
    
    private void setWhitebalance() {
        try {
            doc.remove(0, doc.getLength());
            addText("White Balance\n", titleStyle, doc);
            addText("\nWhite balance kører vores whitebalance program.\n\n"
                    + "Whitebalance bruger dronens kamera justere farverne efter omgivelsernes belysning.\n\n"
                    + "Når programmet køres skal brugeren holde et udprintet billede* af firkanter med forskellige farver op foran dronens kamera.\n"
                    + "Brugeren skal holde billedet så præcist som muligt, og tage et billede, hvorefter WhiteBalance programmet vil validere om den kan se alle farver i billedet, og de fire cirkler rundt i kanterne\n"
                    + "såfremt alt er fundet, vil programmet indstille farverne til passende værdier.\n\n"
                    + "*Billede findes i \"WhiteBalance\\WhiteBalance\\chart\\White Balance Color Chart (calibaretor)\"", normal, doc);
        } catch (BadLocationException e) {
            DFMLogger.logger.warning("Could not load help text for white balance");
            e.printStackTrace();
        }
    }
    
    private void setSettings() {
        try {
            doc.remove(0, doc.getLength());
            addText("Indstillinger\n", titleStyle, doc);
            addText("\nI settings menuen kan du indstille dronen efter behov\n\n"
                    + "MaxAltitude: indstiller hvor højt dronen må flyve i mm\n\n"
                    + "MinAltitude: indstiller hvor lavt dronen må flyve i mm\n\n"
                    + "Portal Height:\n\n"
                    + "Camera Constant\n\n"
                    + "Video Frame Rate: indstiller opdateringshastigheden på dronens kamera", normal, doc);
        } catch (BadLocationException e) {
            DFMLogger.logger.warning("Could not load help text for settings");
            e.printStackTrace();
        }
    }
    
    private void addLink(String url, Style style, StyledDocument doc) throws BadLocationException {
        doc.insertString(doc.getLength(), url, style);
    }
    
    private void addText(String text, SimpleAttributeSet style, StyledDocument doc) throws BadLocationException {
        int start = doc.getLength();
        int end = start + text.length() + 1;
        doc.insertString(start, text, style);
        doc.setParagraphAttributes(start, end, style, true);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jtpContent = new javax.swing.JTextPane();
        jlTitle = new javax.swing.JLabel();

        textField1.setText("textField1");

        jlNavigation.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlNavigation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlNavigationMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jlNavigation);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtpContent.setEditable(false);
        jtpContent.setBackground(new java.awt.Color(240, 240, 240));
        jtpContent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtpContentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtpContent);

        jlTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlTitle.setText("Help Options");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jlTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void jtpContentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtpContentMouseClicked
        JTextPane textPane = (JTextPane) evt.getComponent();
        Element elem = doc.getCharacterElement(textPane.viewToModel(evt.getPoint()));
        
        if (!elem.getAttributes().isDefined(URL_ATT_NAME))
            return;
        
        int len = elem.getEndOffset() - elem.getStartOffset();
        final String url;
        
        try {
            url = doc.getText(elem.getStartOffset(), len);
        } catch (BadLocationException e) {
            DFMLogger.logger.log(Level.WARNING, "can't get URL", e);
            return;
        }
        
        Runnable run = () -> {
            try {
                URI uri = new URL("http://" + url).toURI();
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(uri);
                    } catch (Exception e) {
                        DFMLogger.logger.log(Level.INFO, "Failed to open link: {0}", e.getMessage());
                        e.printStackTrace();
                    }
                }
            } catch (URISyntaxException | MalformedURLException e) {
                DFMLogger.logger.log(Level.INFO, "Failed to open link: {0}", e.getMessage());
                e.printStackTrace();
            }
        };
        new Thread(run).start();
    }//GEN-LAST:event_jtpContentMouseClicked

    private void jlNavigationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlNavigationMouseClicked
        jlNavigation = (JList) evt.getSource();
        int index = jlNavigation.locationToIndex(evt.getPoint());
        switch (index) {
            default: case 0: setWelcome(); break;
            case 1: setInterface(); break;
            case 2: setManual(); break;
            case 3: setHSV(); break;
            case 4: setWhitebalance(); break;
            case 5: setSettings(); break;
        }
    }//GEN-LAST:event_jlNavigationMouseClicked
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> jlNavigation;
    private javax.swing.JLabel jlTitle;
    private javax.swing.JTextPane jtpContent;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}