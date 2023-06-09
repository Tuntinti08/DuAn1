/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.component;

import cores.logins.custom.NhanVienCustom;
import cores.nhanVienQuanLy.customModels.NvqlXemThongTinCaNhanCustom;
import cores.nhanVienQuanLy.services.NvqlXemThongTinCaNhanService;
import cores.nhanVienQuanLy.services.serviceImpls.NvqlXemThongTinCaNhanServiceImpl;
import cores.nhanVienQuanLy.views.NvqlXemThongTinCaNhanForm;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import utilities.Auth;
import cores.logins.custom.NhanVienCustom;
import cores.nhanVienQuanLy.customModels.NvqlXemThongTinCaNhanCustom;
import cores.nhanVienQuanLy.services.serviceImpls.NvqlXemThongTinCaNhanServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utilities.Converter;
import cores.nhanVienQuanLy.services.NvqlXemThongTinCaNhanService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utilities.Auth;
import utilities.MaTuSinh;

/**
 *
 * @author QUOC HUY
 */
public class Header extends javax.swing.JPanel {

    private NhanVienCustom nv;
    private List<NvqlXemThongTinCaNhanCustom> listNvghXemThongTinCaNhanCustom = new ArrayList<>();
    private NvqlXemThongTinCaNhanService khXemThongTinCaNhanService = new NvqlXemThongTinCaNhanServiceImpl();

    /**
     * Creates new form NewJPanel
     */
    public Header() {
        initComponents();
        nv = Auth.nhanVien;
        txtName.setText("Hello, " + nv.getTen());
    }
    
    public void showData(NhanVienCustom a) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        a = Auth.nhanVien;
        Date d = new Date(a.getNgaySinh());
        txtMa.setText(a.getMa());
        txtTen.setText(a.getTen());
        txtGioiTinh.setText(Converter.trangThaiGioiTinh(a.getGioiTinh()));
        txtDienThoai.setText(a.getSdt());
        txtNgaySinh.setText(simpleDateFormat.format(d).toString());
        txtDiaChi.setText(a.getDiaChi());
        txtVaiTro.setText(a.getChucVu().getTen());
    }

    public NhanVienCustom checkPass() {
        String oldPass = txtOldPass.getText();
        String newPass = txtNewPass.getText();
        String cFPass = txtConfirmPass.getText();
        if (oldPass.isEmpty() || newPass.isEmpty() || cFPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống trường nào");
            return null;
        }
        if (!Auth.nhanVien.getMatKhau().equals(oldPass)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu cũ chưa chính xác");
            return null;
        }
        if (oldPass.equals(newPass)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu cũ không được giống mật khẩu mới");
            return null;
        }
        if (!newPass.equals(cFPass)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận chưa đúng");
            return null;
        }

        Auth.nhanVien.setMatKhau(newPass);
        return Auth.nhanVien;
    }

    public NhanVienCustom doiPass() {
        String newPass = MaTuSinh.gen("MK");
        Auth.nhanVien.setMatKhau(newPass);
        return Auth.nhanVien;
    }
    
    public void clearForm() {
        txtOldPass.setText("");
        txtNewPass.setText("");
        txtConfirmPass.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Info = new javax.swing.JDialog();
        materialTabbed1 = new utilities.palette.MaterialTabbed();
        jPanel2 = new javax.swing.JPanel();
        txtNgaySinh = new utilities.palette.TextField();
        txtMa = new utilities.palette.TextField();
        txtDienThoai = new utilities.palette.TextField();
        txtVaiTro = new utilities.palette.TextField();
        txtDiaChi = new utilities.palette.TextField();
        txtGioiTinh = new utilities.palette.TextField();
        txtTen = new utilities.palette.TextField();
        jPanel3 = new javax.swing.JPanel();
        txtOldPass = new utilities.palette.PasswordField();
        txtConfirmPass = new utilities.palette.PasswordField();
        txtNewPass = new utilities.palette.PasswordField();
        btnHuyBo = new utilities.palette.UWPButton();
        btnXacNhan = new utilities.palette.UWPButton();
        lblDoiMatKhau = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        imgInfor = new views.swing.Button();
        btnSap = new views.swing.Button();
        btnClose = new views.swing.Button();

        Info.setSize(new java.awt.Dimension(314, 536));

        materialTabbed1.setBackground(new java.awt.Color(255, 255, 255));

        txtNgaySinh.setEditable(false);
        txtNgaySinh.setLabelText("Ngày sinh: ");

        txtMa.setEditable(false);
        txtMa.setLabelText("Mã: ");

        txtDienThoai.setEditable(false);
        txtDienThoai.setLabelText("Điện thoại: ");

        txtVaiTro.setEditable(false);
        txtVaiTro.setLabelText("Vai trò: ");

        txtDiaChi.setEditable(false);
        txtDiaChi.setLabelText("Địa chỉ: ");

        txtGioiTinh.setEditable(false);
        txtGioiTinh.setLabelText("Giới tính: ");

        txtTen.setEditable(false);
        txtTen.setLabelText("Tên: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Thông tin tài khoản", jPanel2);

        txtOldPass.setLabelText("Mật khẩu hiện tại: ");
        txtOldPass.setShowAndHide(true);

        txtConfirmPass.setLabelText("Xác nhân mật khẩu mới: ");
        txtConfirmPass.setShowAndHide(true);

        txtNewPass.setLabelText("Mật khẩu mới: ");
        txtNewPass.setShowAndHide(true);

        btnHuyBo.setText("HỦY BỎ");
        btnHuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyBoActionPerformed(evt);
            }
        });

        btnXacNhan.setText("XÁC NHẬN");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        lblDoiMatKhau.setText("Quên mật khẩu");
        lblDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoiMatKhauMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblDoiMatKhau)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Đổi mật khẩu", jPanel3);

        javax.swing.GroupLayout InfoLayout = new javax.swing.GroupLayout(Info.getContentPane());
        Info.getContentPane().setLayout(InfoLayout);
        InfoLayout.setHorizontalGroup(
            InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        InfoLayout.setVerticalGroup(
            InfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(643, 107));

        txtName.setText("Hello, Admin");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FPT_Polytechnic.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        imgInfor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Unknown person.png"))); // NOI18N
        imgInfor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imgInforActionPerformed(evt);
            }
        });

        btnSap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/minus (1).png"))); // NOI18N

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSap, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(imgInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSap, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgInfor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtName)
                                .addGap(17, 17, 17))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imgInforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imgInforActionPerformed
        Info.setVisible(true);
        Info.setLocationRelativeTo(null);
        listNvghXemThongTinCaNhanCustom = khXemThongTinCaNhanService.getAll();
        showData(nv);
    }//GEN-LAST:event_imgInforActionPerformed

    private void btnHuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyBoActionPerformed
        Info.setVisible(false);
    }//GEN-LAST:event_btnHuyBoActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        NhanVienCustom kh = checkPass();
        if (kh == null) {
            return;
        }
        String oldPass = txtOldPass.getText();
        String newPass = txtNewPass.getText();

        khXemThongTinCaNhanService.doiMatKhau(kh);
        JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
        clearForm();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void lblDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoiMatKhauMouseClicked
        NhanVienCustom kh = doiPass();
        khXemThongTinCaNhanService.doiMatKhau(kh);
        String ToEmail = "huynqph26782@fpt.edu.vn";
        String FromEmail = "haipxph26772@fpt.edu.vn";
        String Pass = "79802003hai";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FromEmail, Pass);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FromEmail));
            message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(ToEmail)
            );
            message.setText("Your new password is: " + kh.getMatKhau() + ", Please don't be stupid!!");
            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Mật khẩu của bạn đã được gửi về mail, vui lòng check mail của bạn!");
            System.out.println("Done success");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblDoiMatKhauMouseClicked

    public void addClose(ActionListener e) {
        btnClose.addActionListener(e);
    }

    public void addSap(ActionListener e) {
        btnSap.addActionListener(e);
    }

    public void openNavBar(ActionListener e) {
        imgInfor.addActionListener(e);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Info;
    private views.swing.Button btnClose;
    private utilities.palette.UWPButton btnHuyBo;
    private views.swing.Button btnSap;
    private utilities.palette.UWPButton btnXacNhan;
    private views.swing.Button imgInfor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDoiMatKhau;
    private utilities.palette.MaterialTabbed materialTabbed1;
    private utilities.palette.PasswordField txtConfirmPass;
    private utilities.palette.TextField txtDiaChi;
    private utilities.palette.TextField txtDienThoai;
    private utilities.palette.TextField txtGioiTinh;
    private utilities.palette.TextField txtMa;
    private javax.swing.JLabel txtName;
    private utilities.palette.PasswordField txtNewPass;
    private utilities.palette.TextField txtNgaySinh;
    private utilities.palette.PasswordField txtOldPass;
    private utilities.palette.TextField txtTen;
    private utilities.palette.TextField txtVaiTro;
    // End of variables declaration//GEN-END:variables
}
