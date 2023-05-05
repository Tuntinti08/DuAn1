
package cores.truongPhongs.views;

import cores.truongPhongs.customModels.ThongKe_SanPham_Custom;
import cores.truongPhongs.services.ThongKe_SanPham_Service;
import cores.truongPhongs.services.serviceImpls.ThongKe_SanPham_ServiceImpl;
import java.util.ArrayList;
import java.util.List;


public class ThongKe_SanPham_View extends javax.swing.JPanel {
    private ThongKe_SanPham_Service service ;
    private List<ThongKe_SanPham_Custom> list = new ArrayList<>();
    public ThongKe_SanPham_View() {
        initComponents();
        service = new ThongKe_SanPham_ServiceImpl();
        list  = service.getABC(txtDateBanBD.getDate().getTime(), txtDateBanKT.getDate().getTime());
        btnSoSPBanNhieuNhat.setText(list + "");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSoSPBanNhieuNhat = new utilities.palette.ButtonGradient();
        uWPButton1 = new utilities.palette.UWPButton();
        txtDateBanBD = new com.toedter.calendar.JDateChooser();
        lable1 = new utilities.palette.lable();
        lable2 = new utilities.palette.lable();
        txtDateBanKT = new com.toedter.calendar.JDateChooser();

        btnSoSPBanNhieuNhat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSoSPBanNhieuNhat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSoSPBanNhieuNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoSPBanNhieuNhatActionPerformed(evt);
            }
        });

        uWPButton1.setBackground(new java.awt.Color(0, 255, 0));
        uWPButton1.setText("Số sản phẩm bán nhiều nhất trong 1 ngày");
        uWPButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lable1.setText("Ngày bắt đầu");

        lable2.setText("Ngày kết thúc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lable2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDateBanKT, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(txtDateBanBD, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(uWPButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSoSPBanNhieuNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(uWPButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDateBanBD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lable2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateBanKT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(btnSoSPBanNhieuNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSoSPBanNhieuNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoSPBanNhieuNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSoSPBanNhieuNhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.palette.ButtonGradient btnSoSPBanNhieuNhat;
    private utilities.palette.lable lable1;
    private utilities.palette.lable lable2;
    private com.toedter.calendar.JDateChooser txtDateBanBD;
    private com.toedter.calendar.JDateChooser txtDateBanKT;
    private utilities.palette.UWPButton uWPButton1;
    // End of variables declaration//GEN-END:variables
}
