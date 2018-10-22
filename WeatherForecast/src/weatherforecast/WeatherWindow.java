/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherforecast;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author cstuser
 */
public class WeatherWindow extends javax.swing.JFrame {

    /**
     * Creates new form WeatherWindow
     */
    public WeatherWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        DegreesTextField = new javax.swing.JTextField();
        DgreesLabel = new javax.swing.JLabel();
        PrecipitationCheckBox = new javax.swing.JCheckBox();
        NightCheckBox = new javax.swing.JCheckBox();
        FahrenheitCheckBox = new javax.swing.JCheckBox();
        ImagePanel = new weatherforecast.ImagePanel();
        SetIconButton = new javax.swing.JButton();
        TodayWeatherButton = new javax.swing.JButton();
        MessageLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        OverrideIconMenuItem = new javax.swing.JMenuItem();
        QuitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Weather Forecast");

        DgreesLabel.setText("Degrees");

        PrecipitationCheckBox.setText("Precipitations?");

        NightCheckBox.setText("Night?");

        FahrenheitCheckBox.setText("Fahrenheit?");

        javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(ImagePanel);
        ImagePanel.setLayout(ImagePanelLayout);
        ImagePanelLayout.setHorizontalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );
        ImagePanelLayout.setVerticalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );

        SetIconButton.setText("Set Icon");
        SetIconButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetIconButtonActionPerformed(evt);
            }
        });

        TodayWeatherButton.setText("Today's Weather");
        TodayWeatherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TodayWeatherButtonActionPerformed(evt);
            }
        });

        MessageLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MessageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        File.setText("File");
        File.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileActionPerformed(evt);
            }
        });

        OverrideIconMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        OverrideIconMenuItem.setText("Override Icon");
        OverrideIconMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OverrideIconMenuItemActionPerformed(evt);
            }
        });
        File.add(OverrideIconMenuItem);

        QuitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        QuitMenuItem.setText("Quit");
        QuitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitMenuItemActionPerformed(evt);
            }
        });
        File.add(QuitMenuItem);

        jMenuBar1.add(File);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FahrenheitCheckBox)
                            .addComponent(NightCheckBox)
                            .addComponent(PrecipitationCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DegreesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DgreesLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(ImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SetIconButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TodayWeatherButton)
                        .addGap(71, 71, 71))))
            .addComponent(MessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DegreesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DgreesLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PrecipitationCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NightCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FahrenheitCheckBox))
                    .addComponent(ImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TodayWeatherButton)
                    .addComponent(SetIconButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TodayWeatherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TodayWeatherButtonActionPerformed
        try {
            DegreesTextField.setText("10");
            if (!PrecipitationCheckBox.isSelected()) {
                PrecipitationCheckBox.doClick();
            }
            if (NightCheckBox.isSelected()) {
                NightCheckBox.doClick();
            }
            if (FahrenheitCheckBox.isSelected()) {
                FahrenheitCheckBox.doClick();
            }

            displayMessageWithIcon();
        } catch (Exception e) {
            MessageLabel.setText("Error!");
            ImagePanel.setImage("images/error.png");
        }

    }//GEN-LAST:event_TodayWeatherButtonActionPerformed

    private void SetIconButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetIconButtonActionPerformed
        displayMessageWithIcon();
    }//GEN-LAST:event_SetIconButtonActionPerformed

    private void FileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileActionPerformed

    }//GEN-LAST:event_FileActionPerformed

    private void OverrideIconMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OverrideIconMenuItemActionPerformed
        try {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("images"));
            FileNameExtensionFilter ff = new FileNameExtensionFilter("images", "jpg", "png", "BMP");
            fc.setFileFilter(ff);
            fc.addChoosableFileFilter(new FileNameExtensionFilter("jpg files", "jpg"));
            fc.addChoosableFileFilter(new FileNameExtensionFilter("png files", "png"));
            fc.addChoosableFileFilter(new FileNameExtensionFilter("bmp files", "BMP"));

            int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                ImagePanel.setImage(file.getAbsolutePath());
            } else {

            }
        } catch (Exception e) {
            MessageLabel.setText("Error!");
            ImagePanel.setImage("images/error.png");
        }
    }//GEN-LAST:event_OverrideIconMenuItemActionPerformed

    private void QuitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitMenuItemActionPerformed
        try {
            System.exit(0);
        }catch (Exception e) {
            MessageLabel.setText("Error!");
            ImagePanel.setImage("images/error.png");
        }
    }//GEN-LAST:event_QuitMenuItemActionPerformed

    private void displayMessageWithIcon() {
        try {
            String message = "Today is ";
            double temperature = Double.parseDouble(DegreesTextField.getText());
            boolean isNight = false;

            if (NightCheckBox.isSelected()) {
                message = "Tonight is ";
                isNight = true;
            }

            if (PrecipitationCheckBox.isSelected()) {
                if ((FahrenheitCheckBox.isSelected() && temperature > 32) || (!FahrenheitCheckBox.isSelected() && temperature > 0)) {
                    message = message + "raining ";

                    if (isNight) {
                        ImagePanel.setImage("images/raining_night.png");
                    } else {
                        ImagePanel.setImage("images/raining.png");
                    }
                } else {
                    message = message + "snowing ";

                    if (isNight) {
                        ImagePanel.setImage("images/snowing_night.png");
                    } else {
                        ImagePanel.setImage("images/snowing.png");
                    }
                }

            } else {
                message = message + "clear sky ";

                if (isNight) {
                    ImagePanel.setImage("images/clear_night.png");
                } else {
                    ImagePanel.setImage("images/clear.png");
                }
            }

            message = message + "with a temerature of " + temperature;

            if (FahrenheitCheckBox.isSelected()) {
                message = message + "°F";
            } else {
                message = message + "°C";
            }

            MessageLabel.setText(message);
        } catch (Exception e) {
            MessageLabel.setText("Error!");
            ImagePanel.setImage("images/error.png");
        }
    }
 

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WeatherWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WeatherWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WeatherWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WeatherWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WeatherWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DegreesTextField;
    private javax.swing.JLabel DgreesLabel;
    private javax.swing.JCheckBox FahrenheitCheckBox;
    private javax.swing.JMenu File;
    private weatherforecast.ImagePanel ImagePanel;
    private javax.swing.JLabel MessageLabel;
    private javax.swing.JCheckBox NightCheckBox;
    private javax.swing.JMenuItem OverrideIconMenuItem;
    private javax.swing.JCheckBox PrecipitationCheckBox;
    private javax.swing.JMenuItem QuitMenuItem;
    private javax.swing.JButton SetIconButton;
    private javax.swing.JButton TodayWeatherButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
