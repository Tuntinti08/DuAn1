package cores.truongPhongs.views;

import cores.truongPhongs.customModels.TpPhieuNhapChiTietCustom;
import cores.truongPhongs.customModels.TpPhieuNhapCustom;
import cores.truongPhongs.customModels.TpQuanLyDonViCustom;
import cores.truongPhongs.customModels.TpQuanLyNamBHCustom;
import cores.truongPhongs.customModels.TpQuanLySanPhamCustom;
import cores.truongPhongs.customModels.TpXemChiTietSanPhamCustom;
import cores.truongPhongs.services.TpPhieuNhapChiTietService;
import cores.truongPhongs.services.TpQuanLyChiTietSanPhamService;
import cores.truongPhongs.services.TpXemChiTietSanPhamService;
import cores.truongPhongs.services.serviceImpls.TpPhieuNhapChiTietServiceImpl;
import cores.truongPhongs.services.serviceImpls.TpQuanLyChiTietSanPhamServiceImpl;
import cores.truongPhongs.services.serviceImpls.TpXemChiTietSanPhamImpl;
import domainModels.ChiTietSanPham;
import domainModels.PhieuNhap;
import infrastructures.constant.MauConstant;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import utilities.Converter;
import utilities.MsgBox;

/**
 *
 * @author MMC
 */
public class TpLuongNhapAddChiTietSanPhamOldForm extends javax.swing.JFrame {

    private TpXemChiTietSanPhamService serviceChiTietSP = new TpXemChiTietSanPhamImpl();
    private TpQuanLyChiTietSanPhamService serviceSp = new TpQuanLyChiTietSanPhamServiceImpl();
    private List<TpQuanLyDonViCustom> listDonVi = new ArrayList<>();
    private List<TpQuanLyNamBHCustom> listNBH = new ArrayList<>();
    private List<TpQuanLySanPhamCustom> listSanPham = new ArrayList<>();
    private List<TpXemChiTietSanPhamCustom> listCTSP = new ArrayList<>();
    String duongdananh = getClass().getResource("/icons/FPT_Polytechnic_doc.png").getPath();
    TpXemChiTietSanPhamCustom ct = new TpXemChiTietSanPhamCustom();
    private TpPhieuNhapCustom phieuNhap;
    private TpPhieuNhapChiTietService pnctService;

    public void setPhieuNhap(TpPhieuNhapCustom phieuNhap) {
        this.phieuNhap = phieuNhap;
    }

    public TpLuongNhapAddChiTietSanPhamOldForm() {
        initComponents();
        pnctService = new TpPhieuNhapChiTietServiceImpl();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        listDonVi = serviceSp.getAllDonVi();
        listSanPham = serviceSp.getAllSanPham();
        loadCbb();
    }

    public TpXemChiTietSanPhamCustom mouseClickSanPham(int row) {
        return listCTSP.get(row);
    }

    public void loadCbb() {
        cbbMauSac.removeAllItems();
        cbbDonVi.removeAllItems();
        cbbSanPham.removeAllItems();
        for (TpQuanLyDonViCustom dv : listDonVi) {
            cbbDonVi.addItem(dv.getDonViQuyDoi());
        }
        for (TpQuanLySanPhamCustom sp : listSanPham) {
            cbbSanPham.addItem(sp.getTen());
        }
        cbbTrangThai.addItem(Converter.trangThaiSanPham(TrangThaiSanPhamConstanst.DA_MO_BAN));
        cbbTrangThai.addItem(Converter.trangThaiSanPham(TrangThaiSanPhamConstanst.CHO_XAC_NHAN));
       
        cbbMauSac.addItem(Converter.trangThaiMauSac(MauConstant.VANG));
        cbbMauSac.addItem(Converter.trangThaiMauSac(MauConstant.XANH_LA));
        cbbMauSac.addItem(Converter.trangThaiMauSac(MauConstant.DO));
        cbbMauSac.addItem(Converter.trangThaiMauSac(MauConstant.XANH_DUONG));
        cbbMauSac.addItem(Converter.trangThaiMauSac(MauConstant.HONG));
        cbbMauSac.addItem(Converter.trangThaiMauSac(MauConstant.CAM));
        cbbMauSac.addItem(Converter.trangThaiMauSac(MauConstant.DEN));
        cbbMauSac.addItem(Converter.trangThaiMauSac(MauConstant.TRANG));
    }

    public TpXemChiTietSanPhamCustom getFormData() {
        TpXemChiTietSanPhamCustom sp = new TpXemChiTietSanPhamCustom();
        TpQuanLyDonViCustom dv = new TpQuanLyDonViCustom();
        dv = listDonVi.get(cbbDonVi.getSelectedIndex());
        int sl = dv.soLuongQuyDoi(Integer.parseInt(txtSoLuongNhap.getText()));
//        boolean check = true;
//        if (txtGiaNhap.getText().trim().length() == 0) {
//            lblGiaNhap.setText("Giá nhập không được để trống!");
//            check = false;
//        }
//        if (txtSoLuongNhap.getText().trim().length() == 0) {
//            lblSoLuong.setText("Số lượng không được để trống!");
//            check = false;
//        }
//        if (check == false) {
//            return null;
//        }
//        try {
//            sl = Integer.parseInt(txtSoLuongNhap.getText());
//            if (sl <= 0) {
//                lblSoLuong.setText("Số lượng phải lớn hơn 0!");
//                check = false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            lblSoLuong.setText("Bạn phải nhập số lượng là số!");
//            check = false;
//        }
//
//        BigDecimal gia = null;
//        try {
//            gia = BigDecimal.valueOf(Long.parseLong(txtGiaNhap.getText()));
//            if (gia.compareTo(new BigDecimal(0)) <= 0) {
//                lblGiaNhap.setText("Giá nhập phải lớn hơn 0!");
//                check = false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            lblGiaNhap.setText("Bạn phải nhập giá nhập là số!");
//            check = false;
//        }
//        if (check == false) {
//            return null;
//        }
        sp.setSoLuongTon(sl);
        sp.setGiaNhap(BigDecimal.valueOf(Long.parseLong(txtGiaNhap.getText())));
        sp.setDonVi(ct.getDonVi());
        sp.setHinhAnh(ct.getHinhAnh());
        sp.setMau(ct.getMau());
        sp.setSize(ct.getSize());
        sp.setNamBaoHanh(ct.getNamBaoHanh());
        sp.setSanPham(ct.getSanPham());
        sp.setHinhAnh(duongdananh);
        sp.setTrangThai(ct.getTrangThai().CHO_XAC_NHAN);
        sp.setNgayTao(new Date().getTime());
        return sp;
    }

    public void showData() {
        txtMa.setText(ct.getSanPham().getMa());
        txtTenSp.setText(ct.getSanPham().getTen());
        txtSize.setText(ct.getSize() + "");
        cbbDonVi.setSelectedItem(ct.getDonVi().getDonViQuyDoi());
        cbbTrangThai.setSelectedItem(Converter.trangThaiSanPham(ct.getTrangThai()));
        cbbMauSac.setSelectedItem(Converter.trangThaiMauSac(ct.getMau()).toString());
        cbbSanPham.setSelectedItem(ct.getSanPham().getTen());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new utilities.palette.PanelRound();
        btnSave = new utilities.palette.UWPButton();
        btnClose = new utilities.palette.UWPButton();
        panelRound2 = new utilities.palette.PanelRound();
        test1 = new javax.swing.JLabel();
        lblHinhAnh = new utilities.palette.lable();
        panelRound3 = new utilities.palette.PanelRound();
        txtMa = new utilities.palette.TextField();
        txtTenSp = new utilities.palette.TextField();
        txtSoLuongNhap = new utilities.palette.TextField();
        txtGiaNhap = new utilities.palette.TextField();
        cbbSanPham = new utilities.palette.Combobox();
        cbbMauSac = new utilities.palette.Combobox();
        cbbDonVi = new utilities.palette.Combobox();
        cbbTrangThai = new utilities.palette.Combobox();
        txtSize = new utilities.palette.TextField();
        lblSoLuong = new javax.swing.JLabel();
        lblGiaNhap = new javax.swing.JLabel();
        uWPButton1 = new utilities.palette.UWPButton();
        erroHinhAnh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setBackground(new java.awt.Color(221, 242, 244));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        btnSave.setBackground(new java.awt.Color(255, 153, 102));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClose.setBackground(new java.awt.Color(255, 51, 51));
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        panelRound2.setBackground(new java.awt.Color(51, 153, 255));
        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundBottomRight(50);
        panelRound2.setRoundTopLeft(50);
        panelRound2.setRoundTopRight(50);

        test1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FPT_Polytechnic_doc.png"))); // NOI18N

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(test1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(test1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        lblHinhAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(50);
        panelRound3.setRoundBottomRight(50);
        panelRound3.setRoundTopLeft(50);
        panelRound3.setRoundTopRight(50);

        txtMa.setEditable(false);
        txtMa.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtMa.setLabelText("Mã sản phẩm");

        txtTenSp.setEditable(false);
        txtTenSp.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtTenSp.setLabelText("Tên sản phẩm");

        txtSoLuongNhap.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtSoLuongNhap.setLabelText("Số lượng nhập");

        txtGiaNhap.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtGiaNhap.setLabelText("Giá nhập");

        cbbSanPham.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbbSanPham.setLabeText("Chọn Sản Phẩm");

        cbbMauSac.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbbMauSac.setLabeText("Chọn Màu Sắc");

        cbbDonVi.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbbDonVi.setLabeText("Chọn Đơn Vị");

        cbbTrangThai.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbbTrangThai.setLabeText("Trạng thái");

        txtSize.setEditable(false);
        txtSize.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtSize.setLabelText("Size");

        lblSoLuong.setForeground(new java.awt.Color(255, 0, 51));

        lblGiaNhap.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                    .addComponent(lblGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        uWPButton1.setBackground(new java.awt.Color(255, 51, 51));
        uWPButton1.setText("X");
        uWPButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uWPButton1ActionPerformed(evt);
            }
        });

        erroHinhAnh.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(erroHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(uWPButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(erroHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(uWPButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(199, Short.MAX_VALUE))
            .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uWPButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uWPButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_uWPButton1ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        TpXemChiTietSanPhamCustom check = getFormData();
        if (check == null) {
            return;
        }
        TpXemChiTietSanPhamCustom aaaa = serviceChiTietSP.addCTSanPham(check);
        int soLuongQuyDoi = aaaa.getSoLuongTon() * Integer.parseInt(txtSoLuongNhap.getText());
        System.out.println(aaaa.getSoLuongTon());
        System.out.println(Integer.parseInt(txtSoLuongNhap.getText()));
        System.out.println(soLuongQuyDoi);
        
        ChiTietSanPham hihi = new ChiTietSanPham();
        hihi.setId(aaaa.getId());
        PhieuNhap haha = new PhieuNhap();
        haha.setId(phieuNhap.getId());
        TpPhieuNhapChiTietCustom tpPhieuNhapChiTietCustom = new TpPhieuNhapChiTietCustom(
                soLuongQuyDoi,
                hihi,
                haha
        );
        pnctService.addCTPN(tpPhieuNhapChiTietCustom);
        MsgBox.alert(this, "Thêm thành công sản phẩm vào chi tiết phiếu nhập");
        this.setVisible(false);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        try {
            JFileChooser f = new JFileChooser();
            f.setDialogTitle("Mở file");
            f.showOpenDialog(null);
            File ftenanh = f.getSelectedFile();
            duongdananh = ftenanh.getAbsolutePath();

            lblHinhAnh.setIcon(new javax.swing.ImageIcon(duongdananh));
            System.out.println(duongdananh);

        } catch (Exception e) {
            System.out.println("Ban chua chon anh");
            System.out.println(duongdananh);
        }
    }//GEN-LAST:event_lblHinhAnhMouseClicked

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
            java.util.logging.Logger.getLogger(TpLuongNhapAddChiTietSanPhamOldForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TpLuongNhapAddChiTietSanPhamOldForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TpLuongNhapAddChiTietSanPhamOldForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TpLuongNhapAddChiTietSanPhamOldForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TpLuongNhapAddChiTietSanPhamOldForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.palette.UWPButton btnClose;
    private utilities.palette.UWPButton btnSave;
    private utilities.palette.Combobox cbbDonVi;
    private utilities.palette.Combobox cbbMauSac;
    private utilities.palette.Combobox cbbSanPham;
    private utilities.palette.Combobox cbbTrangThai;
    private javax.swing.JLabel erroHinhAnh;
    private javax.swing.JLabel lblGiaNhap;
    private utilities.palette.lable lblHinhAnh;
    private javax.swing.JLabel lblSoLuong;
    private utilities.palette.PanelRound panelRound1;
    private utilities.palette.PanelRound panelRound2;
    private utilities.palette.PanelRound panelRound3;
    private javax.swing.JLabel test1;
    private utilities.palette.TextField txtGiaNhap;
    private utilities.palette.TextField txtMa;
    private utilities.palette.TextField txtSize;
    private utilities.palette.TextField txtSoLuongNhap;
    private utilities.palette.TextField txtTenSp;
    private utilities.palette.UWPButton uWPButton1;
    // End of variables declaration//GEN-END:variables
}
