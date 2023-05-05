/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cores.nhanVienQuanLy.views;

import cores.nhanVienQuanLy.customModels.ThongKePhieuXuat_NhapCustom;
import cores.nhanVienQuanLy.services.ThongKePhieuXuatService;
import cores.nhanVienQuanLy.services.serviceImpls.ThongKePhieuXuatServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MMC
 */
public class ThongKePhieuXuatForm extends javax.swing.JPanel {

    /**
     * Creates new form ThongKePhieuXuat
     */
    private ThongKePhieuXuatService servicePX;
    private List<ThongKePhieuXuat_NhapCustom> listPX = new ArrayList<>();
    private DefaultTableModel dtm = new DefaultTableModel();
    public ThongKePhieuXuatForm() {

        initComponents();
        String[] header = {"Số lượng","Đơn giá","Tổng tiền"};
        dtm.setColumnIdentifiers(header);
        servicePX = new ThongKePhieuXuatServiceImpl();
//        listPX = servicePX.thongKeTienThanhToanMotNgay(txtNgayBatDau.getDate().getTime(), txtNgayKetThuc.getDate().getTime());
//        listPX = servicePX.thongKeTienThanhToanMotNgay(1669209358066L, 1667578037073L);
//        System.out.println(servicePX.thongKeTienThanhToanMotNgay(1669209358066L, 1667578037073L));
//        btnSoTienPhieuXuat.setText(listPX + "");

//        listPX = servicePX.thongKeTienThanhToanMotNgay(txtNgayBatDauN.getDate().getTime(), txtNgayKetThucN.getDate().getTime());
//        System.out.println(servicePX.thongKeTienThanhToanMotNgay(0L, 16666666L));
//        btnSoTienPhieuNhap.setText(listPX + "");
    }

    public void showData(List<ThongKePhieuXuat_NhapCustom> list){
        dtm.setRowCount(0);
        for (ThongKePhieuXuat_NhapCustom tk : list) {
            dtm.addRow(new Object[]{
                tk.getSoLuong(),tk.getDonGia()
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uWPButton1 = new utilities.palette.UWPButton();
        btnSoTienPhieuXuat = new utilities.palette.ButtonGradient();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        lable1 = new utilities.palette.lable();
        btnSoTienPhieuNhap = new utilities.palette.ButtonGradient();
        uWPButton2 = new utilities.palette.UWPButton();
        lable2 = new utilities.palette.lable();
        txtNgayBatDauN = new com.toedter.calendar.JDateChooser();
        lable3 = new utilities.palette.lable();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        txtNgayKetThucN = new com.toedter.calendar.JDateChooser();
        lable4 = new utilities.palette.lable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPhieuXuat = new utilities.palette.TableDark_1();

        uWPButton1.setBackground(new java.awt.Color(0, 255, 0));
        uWPButton1.setText("Số tiền thanh toán phiếu xuất trong 1 ngày");
        uWPButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnSoTienPhieuXuat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSoTienPhieuXuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSoTienPhieuXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoTienPhieuXuatActionPerformed(evt);
            }
        });

        lable1.setText("Ngày bắt đầu:");

        btnSoTienPhieuNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSoTienPhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        uWPButton2.setBackground(new java.awt.Color(0, 255, 0));
        uWPButton2.setText("Số tiền thanh toán phiếu nhập trong 1 ngày");
        uWPButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lable2.setText("Ngày Bắt Đầu:");

        lable3.setText("Ngày kết thúc:");

        lable4.setText("Ngày Kết Thúc:");

        tbPhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbPhieuXuat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lable3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52)
                        .addComponent(btnSoTienPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(uWPButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lable2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNgayBatDauN, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lable4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54)
                                        .addComponent(txtNgayKetThucN, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(72, 72, 72)
                                .addComponent(btnSoTienPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addComponent(uWPButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(uWPButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lable3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnSoTienPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(uWPButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lable2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayBatDauN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSoTienPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lable4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayKetThucN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSoTienPhieuXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoTienPhieuXuatActionPerformed
        Long ngayBD = txtNgayBatDau.getDate().getTime();
        Long ngayKT = txtNgayKetThuc.getDate().getTime();
        if (ngayBD.equals("") && ngayKT.equals("")) {
            return;
        } else {
            showData(servicePX.thongKeTienThanhToanMotNgay(ngayBD, ngayKT));
        }
    }//GEN-LAST:event_btnSoTienPhieuXuatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.palette.ButtonGradient btnSoTienPhieuNhap;
    private utilities.palette.ButtonGradient btnSoTienPhieuXuat;
    private javax.swing.JScrollPane jScrollPane1;
    private utilities.palette.lable lable1;
    private utilities.palette.lable lable2;
    private utilities.palette.lable lable3;
    private utilities.palette.lable lable4;
    private utilities.palette.TableDark_1 tbPhieuXuat;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayBatDauN;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private com.toedter.calendar.JDateChooser txtNgayKetThucN;
    private utilities.palette.UWPButton uWPButton1;
    private utilities.palette.UWPButton uWPButton2;
    // End of variables declaration//GEN-END:variables
}
