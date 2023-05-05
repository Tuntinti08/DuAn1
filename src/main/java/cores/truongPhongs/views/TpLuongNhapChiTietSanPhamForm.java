/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cores.truongPhongs.views;

import cores.truongPhongs.customModels.TpPhieuNhapCustom;
import cores.truongPhongs.customModels.TpXemChiTietSanPhamCustom;
import cores.truongPhongs.services.TpXemChiTietSanPhamService;
import cores.truongPhongs.services.serviceImpls.TpXemChiTietSanPhamImpl;
import infrastructures.constant.MauConstant;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilities.Converter;
import utilities.Page;
import utilities.palette.SearchCustom.EventCallBack;
import utilities.palette.SearchCustom.EventTextField;

/**
 *
 * @author Acer
 */
public class TpLuongNhapChiTietSanPhamForm extends javax.swing.JFrame {

    private TpXemChiTietSanPhamService ctspService;
    private List<TpXemChiTietSanPhamCustom> listSp = new ArrayList<>();
    private TpPhieuNhapCustom phieuNhap;
    private TpLuongNhapAddChiTietSanPhamOldForm createViewAddSpOld;
    private TpLuongNhapAddChiTietSanPhamNewForm createViewAddSpNew;
    String duongdananh = getClass().getResource("/icons/FPT_Polytechnic_doc.png").getPath();
    private Page p;

    private int limit = 5;

    private int offset = 0;

    private int sizes = 0;

    private int index = 1;

    public void setPhieuNhap(TpPhieuNhapCustom phieuNhap) {
        this.phieuNhap = phieuNhap;
    }

    public TpLuongNhapChiTietSanPhamForm() {
        createViewAddSpOld = new TpLuongNhapAddChiTietSanPhamOldForm();
        createViewAddSpNew = new TpLuongNhapAddChiTietSanPhamNewForm();
        initComponents();
        ctspService = new TpXemChiTietSanPhamImpl();
        listSp = ctspService.listCtsp();
        p = new Page();
        sizes = listSp.size();
        loadIndex();
        rdoMaSanPham.setSelected(true);
        cbbTrangThai.setSelectedIndex(0);
        txtSearch.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                //  Test
                try {
                    for (int i = 1; i <= 100; i++) {

                        Thread.sleep(5);
                    }
                    call.done();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public TpLuongNhapChiTietSanPhamForm(UUID id) {
        initComponents();
        ctspService = new TpXemChiTietSanPhamImpl();
        listSp = ctspService.listCtsp();
        p = new Page();
        sizes = listSp.size();
        loadIndex();
        txtSearch.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                //  Test
                try {
                    for (int i = 1; i <= 100; i++) {

                        Thread.sleep(5);
                    }
                    call.done();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }

    private void loadTableNcc(List<TpXemChiTietSanPhamCustom> list) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0 VNĐ");
        DefaultTableModel dtm = (DefaultTableModel) this.tblCtsp.getModel();
        dtm.setRowCount(0);
        for (TpXemChiTietSanPhamCustom sp : list) {
            Object[] rowData = {
                dtm.getRowCount() + 1,
                sp.getSanPham().getMa(),
                sp.getSanPham().getTen(),
                sp.getSoLuongTon(),
                formatter.format(sp.getGiaNhap()),
                sp.getDonVi().getDonViGoc(),
                Converter.trangThaiMauSac(sp.getMau()),
                sp.getSize(),
                sp.getNamBaoHanh(),
                Converter.trangThaiSanPham(sp.getTrangThai())
            };
            dtm.addRow(rowData);
        }
    }

    public List<TpXemChiTietSanPhamCustom> listSearch(int rdo) {
        // nhập vào 
        String timKiem = this.txtSearch.getText();
        List<TpXemChiTietSanPhamCustom> listTimKiem = new ArrayList<>();

        // tìm kiếm theo tên mã vị trí
        checkCbb(ctspService.locTt(this.cbbTrangThai.getSelectedIndex())).forEach(el -> {
            String search = "";
            List<String> strings = new ArrayList<>();

            // truyền tham số
            switch (rdo) {
                case 0:
                    search = el.getSanPham().getMa();
                    break;
                case 1:
                    search = el.getSanPham().getTen();
                    break;

            }
            for (int i = 0; i <= search.length(); i++) {
                String newMa = search.substring(0, i);
                strings.add(newMa);
            }
            // so sánh mảng vừa cắt với phần tử nhập vào
            for (String e : strings) {
                if (e.equalsIgnoreCase(timKiem)) {
                    listTimKiem.add(el);
                }
            }
        });

        return listTimKiem;
    }

    public List<TpXemChiTietSanPhamCustom> checkCbb(TrangThaiSanPhamConstanst cs) {
        List<TpXemChiTietSanPhamCustom> listTimKiem = new ArrayList<>();
        listSp.forEach(el -> {
            if (el.getTrangThai() == cs) {
                listTimKiem.add(el);
            }
        });
        return listTimKiem;
    }

    public List<TpXemChiTietSanPhamCustom> checkCbbMauSac(MauConstant cs) {
        List<TpXemChiTietSanPhamCustom> listTimKiem = new ArrayList<>();
        listSp.forEach(el -> {
            if (el.getMau() == cs) {
                listTimKiem.add(el);
            }
        });
        return listTimKiem;
    }

    public void timKiemTheoGia() {
        if (txtGiaFrom.getText().trim().length() == 0) {
            return;
        }
        if (txtGiaTo.getText().trim().length() == 0) {
            return;
        }
        String giaFrom = txtGiaFrom.getText().toString();
        String giaTo = txtGiaTo.getText().toString();
        BigDecimal giaF;
        BigDecimal giaT;
        try {
            giaF = new BigDecimal(giaFrom);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là kiểu Bigdecimal ! ");
            e.printStackTrace();
            return;
        }
        try {
            giaT = new BigDecimal(giaTo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là kiểu Bigdecimal ! ");
            e.printStackTrace();
            return;
        }

        List<TpXemChiTietSanPhamCustom> listPn = ctspService.getListGiaNhap(giaF, giaT);
         loadTableNcc(ctspService.phanTrang(listPn, offset, limit));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelRound1 = new utilities.palette.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCtsp = new utilities.palette.TableDark_1();
        panelRound4 = new utilities.palette.PanelRound();
        rdoMaSanPham = new utilities.palette.RadioButtonCustom();
        rdoTenSanPham = new utilities.palette.RadioButtonCustom();
        txtSearch = new utilities.palette.SearchCustom.TextFieldAnimation();
        cbbTrangThai = new utilities.palette.Combobox();
        panelRound5 = new utilities.palette.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        panelRound8 = new utilities.palette.PanelRound();
        lbHinhAnh = new utilities.palette.MyButton();
        txtMa = new utilities.palette.TextField();
        txtMau = new utilities.palette.TextField();
        txtTenSp = new utilities.palette.TextField();
        txtGiaNhap = new utilities.palette.TextField();
        txtTrangThai = new utilities.palette.TextField();
        txtSoLuongTon = new utilities.palette.TextField();
        txtNamBH = new utilities.palette.TextField();
        txtDonVi = new utilities.palette.TextField();
        panelRound6 = new utilities.palette.PanelRound();
        jButton2 = new javax.swing.JButton();
        btnHienThi1 = new utilities.palette.MyButton();
        btnSearch = new utilities.palette.MyButton();
        txtGiaFrom = new utilities.palette.TextField();
        txtGiaTo = new utilities.palette.TextField();
        jLabel3 = new javax.swing.JLabel();
        btnAddNew = new utilities.palette.MyButton();
        btnPre = new utilities.palette.UWPButton();
        txtIndex = new javax.swing.JLabel();
        btnNext = new utilities.palette.UWPButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelRound1.setBackground(new java.awt.Color(221, 242, 244));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        tblCtsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn", "Giá nhập", "Đơn vị", "Màu", "Size", "Năm bảo hành", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCtsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCtspMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCtsp);

        panelRound4.setBackground(new java.awt.Color(67, 130, 187));
        panelRound4.setRoundBottomLeft(50);
        panelRound4.setRoundBottomRight(50);
        panelRound4.setRoundTopLeft(50);
        panelRound4.setRoundTopRight(50);

        rdoMaSanPham.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup1.add(rdoMaSanPham);
        rdoMaSanPham.setForeground(new java.awt.Color(255, 255, 255));
        rdoMaSanPham.setText("Mã sản phẩm");
        rdoMaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        rdoTenSanPham.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup1.add(rdoTenSanPham);
        rdoTenSanPham.setForeground(new java.awt.Color(255, 255, 255));
        rdoTenSanPham.setText("Tên sản phẩm");
        rdoTenSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTenSanPhamActionPerformed(evt);
            }
        });

        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });

        cbbTrangThai.setBackground(new java.awt.Color(67, 130, 187));
        cbbTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chờ xác nhận", "Đã mở bán" }));
        cbbTrangThai.setSelectedIndex(-1);
        cbbTrangThai.setToolTipText("Chọn trạng thái sản phẩm muốn tìm kiếm");
        cbbTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbTrangThai.setLabeText("Trạng thái");
        cbbTrangThai.setLineColor(new java.awt.Color(145, 200, 249));
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(rdoMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(rdoTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoMaSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound4Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addComponent(rdoTenSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        panelRound5.setBackground(new java.awt.Color(67, 130, 187));
        panelRound5.setRoundBottomLeft(50);
        panelRound5.setRoundBottomRight(50);
        panelRound5.setRoundTopLeft(50);
        panelRound5.setRoundTopRight(50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DANH SÁCH SẢN PHẨM");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound8.setBackground(new java.awt.Color(67, 130, 187));
        panelRound8.setRoundBottomLeft(50);
        panelRound8.setRoundBottomRight(50);
        panelRound8.setRoundTopLeft(50);
        panelRound8.setRoundTopRight(50);

        lbHinhAnh.setBorderColor(new java.awt.Color(221, 242, 244));
        lbHinhAnh.setColor(new java.awt.Color(221, 242, 244));
        lbHinhAnh.setRadius(50);
        lbHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHinhAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtMa.setEditable(false);
        txtMa.setBackground(new java.awt.Color(221, 242, 244));
        txtMa.setToolTipText("");
        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMa.setLabelText("Mã sản phẩm");

        txtMau.setEditable(false);
        txtMau.setBackground(new java.awt.Color(221, 242, 244));
        txtMau.setToolTipText("");
        txtMau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMau.setLabelText("Màu");

        txtTenSp.setEditable(false);
        txtTenSp.setBackground(new java.awt.Color(221, 242, 244));
        txtTenSp.setToolTipText("");
        txtTenSp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTenSp.setLabelText("Tên sản phẩm");

        txtGiaNhap.setEditable(false);
        txtGiaNhap.setBackground(new java.awt.Color(221, 242, 244));
        txtGiaNhap.setToolTipText("");
        txtGiaNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaNhap.setLabelText("Giá nhập");

        txtTrangThai.setEditable(false);
        txtTrangThai.setBackground(new java.awt.Color(221, 242, 244));
        txtTrangThai.setToolTipText("");
        txtTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTrangThai.setLabelText("Trạng thái");

        txtSoLuongTon.setEditable(false);
        txtSoLuongTon.setBackground(new java.awt.Color(221, 242, 244));
        txtSoLuongTon.setToolTipText("");
        txtSoLuongTon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoLuongTon.setLabelText("Số lượng tồn");

        txtNamBH.setEditable(false);
        txtNamBH.setBackground(new java.awt.Color(221, 242, 244));
        txtNamBH.setToolTipText("");
        txtNamBH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNamBH.setLabelText("Năm bảo hành");

        txtDonVi.setEditable(false);
        txtDonVi.setBackground(new java.awt.Color(221, 242, 244));
        txtDonVi.setToolTipText("");
        txtDonVi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDonVi.setLabelText("Đơn vị");

        panelRound6.setBackground(new java.awt.Color(0, 255, 255));
        panelRound6.setRoundBottomLeft(50);
        panelRound6.setRoundBottomRight(50);
        panelRound6.setRoundTopLeft(50);
        panelRound6.setRoundTopRight(50);

        jButton2.setBackground(new java.awt.Color(255, 255, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Delete.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnHienThi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Show.png"))); // NOI18N
        btnHienThi1.setText("\n");
        btnHienThi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHienThi1ActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtGiaFrom.setLabelText("Từ");

        txtGiaTo.setLabelText("Đến");

        jLabel3.setText("Tìm kiếm theo giá:");

        btnAddNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Addd.png"))); // NOI18N
        btnAddNew.setToolTipText("Thêm sản phẩm mới");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGiaFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtGiaTo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHienThi1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnHienThi1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/skip-previous-circle-solid-24.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        txtIndex.setText("1/1");

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/skip-next-circle-solid-24.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(txtTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addGap(32, 32, 32))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(440, 440, 440)
                .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(txtIndex)
                .addGap(67, 67, 67)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtIndex)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
        loadTableNcc(ctspService.phanTrang(checkCbb(ctspService.locTt(cbbTrangThai.getSelectedIndex())), offset, limit)) ;

    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    public TpXemChiTietSanPhamCustom mouseClickSanPham(int row) {
        return listSp.get(row);
    }

    public void fillData(int i) {
        TpXemChiTietSanPhamCustom ct = listSp.get(i);
        txtMa.setText(ct.getSanPham().getMa());
        txtTenSp.setText(ct.getSanPham().getTen());
        txtDonVi.setText(ct.getDonVi().getDonViGoc());
        txtNamBH.setText(String.valueOf(ct.getNamBaoHanh()));
        txtMau.setText(Converter.trangThaiMauSac(ct.getMau()));
        txtTrangThai.setText(Converter.trangThaiSanPham(ct.getTrangThai()));
    }


    private void tblCtspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCtspMouseClicked
        // TODO add your handling code here:
        int row = this.tblCtsp.getSelectedRow();
        if (row == -1) {
            return;
        }

        TpXemChiTietSanPhamCustom tpXemChiTietSanPhamCustom = mouseClickSanPham(row);

        txtMa.setText(tblCtsp.getValueAt(row, 1).toString());
        txtTenSp.setText(tblCtsp.getValueAt(row, 2).toString());
        txtSoLuongTon.setText(tblCtsp.getValueAt(row, 3).toString());
        txtGiaNhap.setText(tblCtsp.getValueAt(row, 4).toString());
        txtDonVi.setText(tblCtsp.getValueAt(row, 5).toString());
        txtMau.setText(tblCtsp.getValueAt(row, 6).toString());
        txtNamBH.setText(tblCtsp.getValueAt(row, 7).toString());
        txtTrangThai.setText(tblCtsp.getValueAt(row, 8).toString());

        fillData(row);
        createViewAddSpOld.ct = listSp.get(row);
        createViewAddSpOld.setPhieuNhap(phieuNhap);
        createViewAddSpOld.setVisible(true);
        createViewAddSpOld.loadCbb();
        createViewAddSpOld.showData();

    }//GEN-LAST:event_tblCtspMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnHienThi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHienThi1ActionPerformed
        listSp = ctspService.listCtsp();
        loadTableNcc(ctspService.phanTrang(listSp, offset, limit));
    }//GEN-LAST:event_btnHienThi1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
//        if (txtGiaFrom.getText().trim().length() == 0 || txtGiaTo.getText().trim().length() == 0) {
//            MsgBox.alert(this, "Bạn phải nhập khoảng giá tìm kiếm");
//            return;
//        }
//        listCTSP = luongService.getListCTSanPhamBanHang(new BigDecimal(txtGiaFrom.getText()), new BigDecimal(txtGiaTo.getText()));
//        loadTable(listCTSP);
//        searchRadio();
        
        timKiemTheoGia();

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        createViewAddSpNew.setPhieuNhap(phieuNhap);
        createViewAddSpNew.setVisible(true);
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void rdoTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoTenSanPhamActionPerformed

    public List<TpXemChiTietSanPhamCustom> getListByTT(int rdo) {
        String timKiem = this.txtSearch.getText();
        listSp = ctspService.findAllByKhAndNV(timKiem, ctspService.locTt(cbbTrangThai.getSelectedIndex()), rdo);
        return listSp;
    }

    public void searchhRadio() {
        if (rdoMaSanPham.isSelected()) {
            loadTableNcc(ctspService.phanTrang(getListByTT(0), offset, limit));
        }
        if (rdoTenSanPham.isSelected()) {
            loadTableNcc(ctspService.phanTrang(getListByTT(1), offset, limit));
        }
    }
    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        // TODO add your handling code here:
//        searchRadio();
//        timKiemTheoGia();
        searchhRadio();
        

    }//GEN-LAST:event_txtSearchMouseClicked

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

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        index = p.prevIndex(offset, limit, index);
        offset = p.prev(offset, limit);
        loadIndex();
        //        loadTable(getList);
        loadTableNcc(ctspService.phanTrang(listSp, offset, limit));
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        index = p.nextIndex(offset, limit, sizes, index);
        offset = p.next(offset, limit, sizes);
        loadIndex();
        //        loadTable(getList);
        loadTableNcc(ctspService.phanTrang(listSp, offset, limit));
    }//GEN-LAST:event_btnNextActionPerformed
    private void loadIndex() {
        this.txtIndex.setText(String.valueOf(index) + " / " + (Math.round((sizes / limit) + 0.5)));
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
            java.util.logging.Logger.getLogger(TpLuongNhapChiTietSanPhamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TpLuongNhapChiTietSanPhamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TpLuongNhapChiTietSanPhamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TpLuongNhapChiTietSanPhamForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TpLuongNhapChiTietSanPhamForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.palette.MyButton btnAddNew;
    private utilities.palette.MyButton btnHienThi1;
    private utilities.palette.UWPButton btnNext;
    private utilities.palette.UWPButton btnPre;
    private utilities.palette.MyButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private utilities.palette.Combobox cbbTrangThai;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private utilities.palette.MyButton lbHinhAnh;
    private utilities.palette.PanelRound panelRound1;
    private utilities.palette.PanelRound panelRound4;
    private utilities.palette.PanelRound panelRound5;
    private utilities.palette.PanelRound panelRound6;
    private utilities.palette.PanelRound panelRound8;
    private utilities.palette.RadioButtonCustom rdoMaSanPham;
    private utilities.palette.RadioButtonCustom rdoTenSanPham;
    private utilities.palette.TableDark_1 tblCtsp;
    private utilities.palette.TextField txtDonVi;
    private utilities.palette.TextField txtGiaFrom;
    private utilities.palette.TextField txtGiaNhap;
    private utilities.palette.TextField txtGiaTo;
    private javax.swing.JLabel txtIndex;
    private utilities.palette.TextField txtMa;
    private utilities.palette.TextField txtMau;
    private utilities.palette.TextField txtNamBH;
    private utilities.palette.SearchCustom.TextFieldAnimation txtSearch;
    private utilities.palette.TextField txtSoLuongTon;
    private utilities.palette.TextField txtTenSp;
    private utilities.palette.TextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
