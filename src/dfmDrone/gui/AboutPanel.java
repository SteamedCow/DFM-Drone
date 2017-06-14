package dfmDrone.gui;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * AboutPanel
 * @author Lasse
 * @version 13-06-2017
 */
public class AboutPanel extends javax.swing.JPanel
{
    private int start = 0;
    private int end = 0;
    
    public AboutPanel() {
        initComponents();
        
        DefaultCaret caret = (DefaultCaret) jtpContent.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        
        StyledDocument doc = jtpContent.getStyledDocument();
        
        StyleContext context = new StyleContext();
        Style labelStyle = context.getStyle(StyleContext.DEFAULT_STYLE);
        
        SimpleAttributeSet title = new SimpleAttributeSet();
        StyleConstants.setAlignment(title, StyleConstants.ALIGN_CENTER);
        StyleConstants.setBold(title, true);
        StyleConstants.setFontSize(title, 13);
        
        SimpleAttributeSet highlight = new SimpleAttributeSet();
        StyleConstants.setAlignment(highlight, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(highlight, 12);
        
        SimpleAttributeSet normal = new SimpleAttributeSet();
        StyleConstants.setAlignment(normal, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(normal, 11);
        
        Icon logo = new ImageIcon(getClass().getResource("/dfmDrone/gui/symbols/dfm_logo_36.png"));
        JLabel logoLabel = new JLabel(logo);
        StyleConstants.setComponent(labelStyle, logoLabel);
        
        Icon license = new ImageIcon(getClass().getResource("/dfmDrone/gui/symbols/License.png"));
        JLabel licenseLabel = new JLabel(license);
        
        try {
            doc.insertString(0, "Logo", labelStyle);
            start = doc.getLength();
            addText(getAboutProject(), highlight, doc);
            addText("\n-- Creators --\n", title, doc);
            addText(getAboutUs(), normal, doc);
            addText("\n-- License --\n", title, doc);
            addText(getAboutLicense(), normal, doc);
            StyleConstants.setComponent(labelStyle, licenseLabel);
            doc.insertString(doc.getLength(), "License", labelStyle);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
    
    private void addText(String text, SimpleAttributeSet style, StyledDocument doc) throws BadLocationException {
        end =+ text.length() + 1;
        doc.insertString(doc.getLength(), text, style);
        doc.setParagraphAttributes(start, end, style, true);
        start = end;
    }
    
    private static String getAboutProject() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\nDFM Drone Client version: 1.0 (June 2017)\n");
        sb.append("Drone client for the Parrot AR2.0 Drone\n");
        sb.append("This project is a result of a group project at the Technical Univercity of Denmark (DTU)\n");
        
        return sb.toString();
    }
    
    private static String getAboutUs() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Mathias Kvisgaard Hansen\n");
        sb.append("Magnus Erengaard Enevoldsen\n");
        sb.append("Martin Rødgaard\n");
        sb.append("Asger Stage\n");
        sb.append("Frank Thomsen\n");
        sb.append("Lasse Holm Nielsen\n");
        
        sb.append("\nSupervisors:\n");
        sb.append("Paul FischerPaul\n");
        sb.append("Henrik Bechmann\n");
        sb.append("Kurt Jeritslev\n");
        
        return sb.toString();
    }
    
    private static String getAboutLicense() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Icons:\n");
        sb.append("Artist: Icons8\nWebsite: icons8.com\n");
        sb.append("\nCode:\n");
        sb.append("Licensed under Creative Commons BY-NC-SA 4.0\nWebsite: creativecommons.org/licenses/by-nc-sa/4.0/\n");
        
        return sb.toString();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtpContent = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jbExit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("About   ");
        jLabel1.setFocusable(false);

        jtpContent.setEditable(false);
        jtpContent.setBackground(new java.awt.Color(240, 240, 240));
        jtpContent.setFocusable(false);
        jScrollPane2.setViewportView(jtpContent);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        jPanel1.add(jLabel3);

        jbExit.setText("OK");
        jbExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExitActionPerformed(evt);
            }
        });
        jPanel1.add(jbExit);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(jLabel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExitActionPerformed
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }//GEN-LAST:event_jbExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbExit;
    private javax.swing.JTextPane jtpContent;
    // End of variables declaration//GEN-END:variables
}