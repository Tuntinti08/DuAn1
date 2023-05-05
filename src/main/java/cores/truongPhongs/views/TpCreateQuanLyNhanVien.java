package cores.truongPhongs.views;

import cores.truongPhongs.customModels.TP_ChucVuCustom;
import cores.truongPhongs.customModels.TpNhanVienCustom;
import cores.truongPhongs.services.TpQuanLyNhanVienSevice;
import cores.truongPhongs.services.serviceImpls.TpQuanLyNhanVienServiceImpl;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import utilities.MsgBox;

/**
 *
 * @author LENOVO
 */
public class TpCreateQuanLyNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form CreateQuanLyNhanVienView
     */
    private TpQuanLyNhanVienSevice nhanVienSevice;

    private List<TP_ChucVuCustom> ListCV;
   String duongdananh = getClass().getResource("/icons/FPT_Polytechnic_doc.png").getPath();

    public TpCreateQuanLyNhanVien() {
        
        nhanVienSevice = new TpQuanLyNhanVienServiceImpl();

        ListCV = new ArrayList<>();
        initComponents();
        loadList();
        loadCBB();

        nhanVienSevice.loadComboboxTT(cbbTrangThai);
        nhanVienSevice.loadComboboxGT(cbbGioiTinh);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }

    public TpNhanVienCustom getFormDataCreate() {
        TpNhanVienCustom nvc = new TpNhanVienCustom();
        nvc.setMa(this.txtMa.getText().trim());
        nvc.setTen(txtTen.getText().trim());
        nvc.setSdt(txtSDT.getText().trim());
        nvc.setEmail(txtEmail.getText().trim());
        nvc.setDiaChi(txtDiaChi.getText().trim());
        nvc.setMatKhau(txtMatKhau.getText());
        nvc.setHinhAnh(duongdananh);
        try {

            nvc.setNgaySinh(txtDate.getDate().getTime());
        } catch (Exception e) {
//lại
            e.printStackTrace();
              nvc.setNgaySinh(null);
//            nvc.setNgaySinh(DateTimeUtil.convertDateToTimeStampSecond());
        }
//        nvc.setNgaySinh(txtDate.getDate().getTime());
        nvc.setTrangThai(nhanVienSevice.loc(this.cbbTrangThai.getSelectedIndex()));
        nvc.setGioiTinh(nhanVienSevice.loc1(this.cbbGioiTinh.getSelectedIndex()));
        nvc.setIdChucVu(nhanVienSevice.findIDCV(nhanVienSevice.getListCV().get(cbbChucVu.getSelectedIndex()).getId()));
        return nvc;
    }

    public void loadList() {
        ListCV = nhanVienSevice.getListCV();

    }

    public void loadCBB() {

        cbbChucVu.removeAllItems();
        for (TP_ChucVuCustom tP_ChucVuCustom : ListCV) {
            cbbChucVu.addItem(tP_ChucVuCustom.getTen());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelRound1 = new utilities.palette.PanelRound();
        panelRound2 = new utilities.palette.PanelRound();
        erroMa = new utilities.palette.lable();
        txtMa = new utilities.palette.TextField();
        txtTen = new utilities.palette.TextField();
        erroTen = new utilities.palette.lable();
        txtEmail = new utilities.palette.TextField();
        erroEmail = new utilities.palette.lable();
        txtSDT = new utilities.palette.TextField();
        erroSDT = new utilities.palette.lable();
        erroMatKhau = new utilities.palette.lable();
        txtDiaChi = new utilities.palette.TextField();
        erroDiaChi = new utilities.palette.lable();
        txtDate = new com.toedter.calendar.JDateChooser();
        erroNgaySinh = new utilities.palette.lable();
        cbbGioiTinh = new utilities.palette.Combobox();
        cbbTrangThai = new utilities.palette.Combobox();
        cbbChucVu = new utilities.palette.Combobox();
        txtMatKhau = new utilities.palette.PasswordField();
        panelRound3 = new utilities.palette.PanelRound();
        test = new javax.swing.JLabel();
        lbHinhAnh = new javax.swing.JButton();
        btnThem = new utilities.palette.UWPButton();
        btnThoat = new utilities.palette.UWPButton();
        uWPButton1 = new utilities.palette.UWPButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setBackground(new java.awt.Color(221, 242, 244));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundBottomRight(50);
        panelRound2.setRoundTopLeft(50);
        panelRound2.setRoundTopRight(50);

        erroMa.setFont(new java.awt.Font("Constantia", 1, 8)); // NOI18N

        txtMa.setLabelText("Mã");

        txtTen.setLabelText("Tên");

        erroTen.setFont(new java.awt.Font("Constantia", 1, 8)); // NOI18N

        txtEmail.setLabelText("Email");

        erroEmail.setFont(new java.awt.Font("Constantia", 1, 8)); // NOI18N

        txtSDT.setLabelText("SDT");

        erroSDT.setFont(new java.awt.Font("Constantia", 1, 8)); // NOI18N

        erroMatKhau.setFont(new java.awt.Font("Constantia", 1, 8)); // NOI18N

        txtDiaChi.setLabelText("Địa Chỉ");

        erroDiaChi.setFont(new java.awt.Font("Constantia", 1, 8)); // NOI18N

        erroNgaySinh.setFont(new java.awt.Font("Constantia", 1, 8)); // NOI18N

        cbbGioiTinh.setLabeText("Giới Tính");
        cbbGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbGioiTinhActionPerformed(evt);
            }
        });

        cbbTrangThai.setLabeText("Trạng Thái");

        cbbChucVu.setLabeText("Chức Vụ");
        cbbChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChucVuActionPerformed(evt);
            }
        });

        txtMatKhau.setLabelText("Mật khẩu mới: ");
        txtMatKhau.setShowAndHide(true);

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(erroNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(erroDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(erroMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(erroSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(erroEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(erroTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(erroMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbbChucVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                        .addComponent(cbbTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(erroMa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(erroTen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                        .addComponent(erroEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(erroSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(erroMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(erroDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(erroNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        panelRound3.setBackground(new java.awt.Color(51, 153, 255));
        panelRound3.setRoundBottomLeft(50);
        panelRound3.setRoundBottomRight(50);
        panelRound3.setRoundTopLeft(50);
        panelRound3.setRoundTopRight(50);

        test.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FPT_Polytechnic_doc.png"))); // NOI18N

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(test, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        lbHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHinhAnhMouseClicked(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(255, 102, 51));
        btnThem.setText("Save");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(255, 51, 0));
        btnThoat.setText("Close");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        uWPButton1.setBackground(new java.awt.Color(255, 51, 51));
        uWPButton1.setText("X");
        uWPButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uWPButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lbHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(uWPButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uWPButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(lbHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        TpNhanVienCustom check = nhanVienSevice.checkValidateCreate(getFormDataCreate(), erroMa, erroTen, erroEmail, erroSDT, erroMatKhau, erroDiaChi, erroNgaySinh);
        if (check == null) {
            return;
        }
        if (nhanVienSevice.addNhanVien(check) == null) {
            MsgBox.alert(this, "Thêm thất bại");
            this.setVisible(true);
        } else {
            MsgBox.alert(this, "Thêm thành công");
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void lbHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHinhAnhMouseClicked

  try {
            JFileChooser f = new JFileChooser();
            f.setDialogTitle("Mở file");
            f.showOpenDialog(null);
            File ftenanh = f.getSelectedFile();
            duongdananh = ftenanh.getAbsolutePath();

            lbHinhAnh.setIcon(new javax.swing.ImageIcon(duongdananh));
            System.out.println(duongdananh);

        } catch (Exception e) {
            System.out.println("Ban chua chon anh");
            System.out.println(duongdananh);
        }
    }//GEN-LAST:event_lbHinhAnhMouseClicked

    private void uWPButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uWPButton1ActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_uWPButton1ActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnThoatActionPerformed

    private void cbbChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbChucVuActionPerformed

    private void cbbGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbGioiTinhActionPerformed

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
            java.util.logging.Logger.getLogger(TpCreateQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TpCreateQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TpCreateQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TpCreateQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TpCreateQuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.palette.UWPButton btnThem;
    private utilities.palette.UWPButton btnThoat;
    private javax.swing.ButtonGroup buttonGroup1;
    private utilities.palette.Combobox cbbChucVu;
    private utilities.palette.Combobox cbbGioiTinh;
    private utilities.palette.Combobox cbbTrangThai;
    private utilities.palette.lable erroDiaChi;
    private utilities.palette.lable erroEmail;
    private utilities.palette.lable erroMa;
    private utilities.palette.lable erroMatKhau;
    private utilities.palette.lable erroNgaySinh;
    private utilities.palette.lable erroSDT;
    private utilities.palette.lable erroTen;
    private javax.swing.JButton lbHinhAnh;
    private utilities.palette.PanelRound panelRound1;
    private utilities.palette.PanelRound panelRound2;
    private utilities.palette.PanelRound panelRound3;
    private javax.swing.JLabel test;
    private com.toedter.calendar.JDateChooser txtDate;
    private utilities.palette.TextField txtDiaChi;
    private utilities.palette.TextField txtEmail;
    private utilities.palette.TextField txtMa;
    private utilities.palette.PasswordField txtMatKhau;
    private utilities.palette.TextField txtSDT;
    private utilities.palette.TextField txtTen;
    private utilities.palette.UWPButton uWPButton1;
    // End of variables declaration//GEN-END:variables
}