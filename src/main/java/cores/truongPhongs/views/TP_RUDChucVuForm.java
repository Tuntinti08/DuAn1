package cores.truongPhongs.views;

import cores.truongPhongs.customModels.TP_ChucVuCustom;
import cores.truongPhongs.services.TP_ChucVuService;
import cores.truongPhongs.services.serviceImpls.TP_ChucVuServiceImpl;
import java.awt.Dimension;
import java.awt.Toolkit;
import utilities.MsgBox;

public class TP_RUDChucVuForm extends javax.swing.JFrame {

    private TP_ChucVuService chucVuService;
    TP_ChucVuCustom custom = new TP_ChucVuCustom();

    public TP_RUDChucVuForm() {
        chucVuService = new TP_ChucVuServiceImpl();
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }

    public TP_RUDChucVuForm(TP_ChucVuCustom cs) {
        custom = cs;
        chucVuService = new TP_ChucVuServiceImpl();
        initComponents();
        showData();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }

    public void showData() {
        this.txtMa9.setText(custom.getMa());
        this.txtTen9.setText(custom.getTen());
    }

    public TP_ChucVuCustom getFormData() {
        TP_ChucVuCustom custom = new TP_ChucVuCustom();
        custom.setMa(null);
        custom.setTen(txtTen9.getText());
        return custom;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound19 = new utilities.palette.PanelRound();
        txtMa9 = new utilities.palette.TextField();
        txtTen9 = new utilities.palette.TextField();
        btnX9 = new utilities.palette.UWPButton();
        btnClose9 = new utilities.palette.UWPButton();
        panelRound20 = new utilities.palette.PanelRound();
        test9 = new javax.swing.JLabel();
        erroTen = new javax.swing.JLabel();
        erroMa = new javax.swing.JLabel();
        btnUpdate = new utilities.palette.UWPButton();
        btnDelete = new utilities.palette.UWPButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound19.setBackground(new java.awt.Color(221, 242, 244));
        panelRound19.setRoundBottomLeft(50);
        panelRound19.setRoundBottomRight(50);
        panelRound19.setRoundTopLeft(50);
        panelRound19.setRoundTopRight(50);

        txtMa9.setLabelText("Mã");

        txtTen9.setLabelText("Tên ");

        btnX9.setBackground(new java.awt.Color(255, 51, 51));
        btnX9.setText("X");
        btnX9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnX9ActionPerformed(evt);
            }
        });

        btnClose9.setBackground(new java.awt.Color(255, 51, 51));
        btnClose9.setText("Close");
        btnClose9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose9ActionPerformed(evt);
            }
        });

        panelRound20.setBackground(new java.awt.Color(51, 153, 255));
        panelRound20.setRoundBottomLeft(50);
        panelRound20.setRoundBottomRight(50);
        panelRound20.setRoundTopLeft(50);
        panelRound20.setRoundTopRight(50);

        test9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FPT_Polytechnic_doc.png"))); // NOI18N

        javax.swing.GroupLayout panelRound20Layout = new javax.swing.GroupLayout(panelRound20);
        panelRound20.setLayout(panelRound20Layout);
        panelRound20Layout.setHorizontalGroup(
            panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound20Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(test9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound20Layout.setVerticalGroup(
            panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(test9, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        erroTen.setForeground(new java.awt.Color(255, 51, 51));

        erroMa.setForeground(new java.awt.Color(255, 51, 51));

        btnUpdate.setBackground(new java.awt.Color(255, 153, 102));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound19Layout = new javax.swing.GroupLayout(panelRound19);
        panelRound19.setLayout(panelRound19Layout);
        panelRound19Layout.setHorizontalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound19Layout.createSequentialGroup()
                .addComponent(panelRound20, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound19Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClose9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound19Layout.createSequentialGroup()
                            .addGap(83, 83, 83)
                            .addGroup(panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTen9, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMa9, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(erroTen, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(erroMa, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound19Layout.createSequentialGroup()
                            .addGap(302, 302, 302)
                            .addComponent(btnX9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound19Layout.setVerticalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound19Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnX9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtMa9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(erroMa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTen9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(erroTen, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
            .addComponent(panelRound20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnX9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnX9ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnX9ActionPerformed

    private void btnClose9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose9ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnClose9ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        TP_ChucVuCustom check = chucVuService.checkValidate(getFormData(), erroMa, erroTen);
        if (check == null) {
            return;
        }
        TP_ChucVuCustom cs = new TP_ChucVuCustom(custom.getId(), custom.getMa(), check.getTen());
        if (chucVuService.updateChucVu(cs)) {
            MsgBox.alert(this, "Sửa thành công");
            this.setVisible(false);
        } else {
            MsgBox.alert(this, "Sửa thất bại");
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       if (chucVuService.deleteChucVu(custom.getId())) {
            MsgBox.alert(this, "Xóa thành công");
            this.setVisible(false);
        } else {
            MsgBox.alert(this, "Xóa thất bại");
            this.setVisible(true);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(TP_RUDChucVuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TP_RUDChucVuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TP_RUDChucVuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TP_RUDChucVuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TP_RUDChucVuForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.palette.UWPButton btnClose;
    private utilities.palette.UWPButton btnClose1;
    private utilities.palette.UWPButton btnClose2;
    private utilities.palette.UWPButton btnClose3;
    private utilities.palette.UWPButton btnClose4;
    private utilities.palette.UWPButton btnClose5;
    private utilities.palette.UWPButton btnClose6;
    private utilities.palette.UWPButton btnClose7;
    private utilities.palette.UWPButton btnClose8;
    private utilities.palette.UWPButton btnClose9;
    private utilities.palette.UWPButton btnDelete;
    private utilities.palette.UWPButton btnSave;
    private utilities.palette.UWPButton btnSave1;
    private utilities.palette.UWPButton btnSave2;
    private utilities.palette.UWPButton btnSave3;
    private utilities.palette.UWPButton btnSave4;
    private utilities.palette.UWPButton btnSave5;
    private utilities.palette.UWPButton btnSave6;
    private utilities.palette.UWPButton btnSave7;
    private utilities.palette.UWPButton btnSave8;
    private utilities.palette.UWPButton btnUpdate;
    private utilities.palette.UWPButton btnX;
    private utilities.palette.UWPButton btnX1;
    private utilities.palette.UWPButton btnX2;
    private utilities.palette.UWPButton btnX3;
    private utilities.palette.UWPButton btnX4;
    private utilities.palette.UWPButton btnX5;
    private utilities.palette.UWPButton btnX6;
    private utilities.palette.UWPButton btnX7;
    private utilities.palette.UWPButton btnX8;
    private utilities.palette.UWPButton btnX9;
    private javax.swing.JLabel erroMa;
    private javax.swing.JLabel erroTen;
    private utilities.palette.PanelRound panelRound1;
    private utilities.palette.PanelRound panelRound10;
    private utilities.palette.PanelRound panelRound11;
    private utilities.palette.PanelRound panelRound12;
    private utilities.palette.PanelRound panelRound13;
    private utilities.palette.PanelRound panelRound14;
    private utilities.palette.PanelRound panelRound15;
    private utilities.palette.PanelRound panelRound16;
    private utilities.palette.PanelRound panelRound17;
    private utilities.palette.PanelRound panelRound18;
    private utilities.palette.PanelRound panelRound19;
    private utilities.palette.PanelRound panelRound2;
    private utilities.palette.PanelRound panelRound20;
    private utilities.palette.PanelRound panelRound3;
    private utilities.palette.PanelRound panelRound4;
    private utilities.palette.PanelRound panelRound5;
    private utilities.palette.PanelRound panelRound6;
    private utilities.palette.PanelRound panelRound7;
    private utilities.palette.PanelRound panelRound8;
    private utilities.palette.PanelRound panelRound9;
    private javax.swing.JLabel test;
    private javax.swing.JLabel test1;
    private javax.swing.JLabel test2;
    private javax.swing.JLabel test3;
    private javax.swing.JLabel test4;
    private javax.swing.JLabel test5;
    private javax.swing.JLabel test6;
    private javax.swing.JLabel test7;
    private javax.swing.JLabel test8;
    private javax.swing.JLabel test9;
    private utilities.palette.TextField txtMa;
    private utilities.palette.TextField txtMa1;
    private utilities.palette.TextField txtMa2;
    private utilities.palette.TextField txtMa3;
    private utilities.palette.TextField txtMa4;
    private utilities.palette.TextField txtMa5;
    private utilities.palette.TextField txtMa6;
    private utilities.palette.TextField txtMa7;
    private utilities.palette.TextField txtMa8;
    private utilities.palette.TextField txtMa9;
    private utilities.palette.TextField txtTen;
    private utilities.palette.TextField txtTen1;
    private utilities.palette.TextField txtTen2;
    private utilities.palette.TextField txtTen3;
    private utilities.palette.TextField txtTen4;
    private utilities.palette.TextField txtTen5;
    private utilities.palette.TextField txtTen6;
    private utilities.palette.TextField txtTen7;
    private utilities.palette.TextField txtTen8;
    private utilities.palette.TextField txtTen9;
    // End of variables declaration//GEN-END:variables
}
