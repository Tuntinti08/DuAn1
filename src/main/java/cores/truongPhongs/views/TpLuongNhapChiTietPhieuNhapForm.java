package cores.truongPhongs.views;

import cores.truongPhongs.customModels.TpPhieuNhapChiTietCustom;
import cores.truongPhongs.customModels.TpPhieuNhapCustom;
import cores.truongPhongs.customModels.TpXemChiTietSanPhamCustom;
import cores.truongPhongs.services.TpPhieuNhapChiTietService;
import cores.truongPhongs.services.TpXemChiTietSanPhamService;
import cores.truongPhongs.services.serviceImpls.TpPhieuNhapChiTietServiceImpl;
import cores.truongPhongs.services.serviceImpls.TpXemChiTietSanPhamImpl;
import infrastructures.constant.MauConstant;
import infrastructures.constant.TrangThaiPhieuConstant;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilities.Converter;
import utilities.MsgBox;

/**
 *
 * @author admin
 */
public class TpLuongNhapChiTietPhieuNhapForm extends javax.swing.JFrame {

    private TpPhieuNhapCustom phieuNhap;
    private TpXemChiTietSanPhamService ctspService;
    private List<TpXemChiTietSanPhamCustom> listSp;
    private TpPhieuNhapChiTietService phieuNhapChiTietService;
    private List<TpPhieuNhapChiTietCustom> listCtpnCustom = new ArrayList<>();

    public void setPhieuNhap(TpPhieuNhapCustom phieuNhap) {
        this.phieuNhap = phieuNhap;
    }

    public TpLuongNhapChiTietPhieuNhapForm() {
        initComponents();
        phieuNhapChiTietService = new TpPhieuNhapChiTietServiceImpl();
        ctspService = new TpXemChiTietSanPhamImpl();
        listSp = ctspService.listCtsp();
    }

    public void loadTable(List<TpPhieuNhapChiTietCustom> list) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0 VNĐ");
        DefaultTableModel dtm = (DefaultTableModel) this.tbCtpn.getModel();
        dtm.setRowCount(0);
        for (TpPhieuNhapChiTietCustom ctpx : list) {
            Object[] rowData = {
                dtm.getRowCount() + 1,
                ctpx.getIdSanPham().getSanPham().getTen(),
                Converter.trangThaiMauSac(ctpx.getIdSanPham().getMau()),
                ctpx.getIdSanPham().getSize(),
                ctpx.getIdSanPham().getDonVi().getDonViGoc(),
                ctpx.getIdSanPham().getNamBaoHanh(),
                ctpx.getIdSanPham().getSoLuongTon(),
                formatter.format(ctpx.getIdSanPham().getGiaNhap())
            };
            dtm.addRow(rowData);
        }
    }

    public List<TpPhieuNhapChiTietCustom> listSearch(int rdo) {
        // nhập vào 
        String timKiem = this.txtSearch.getText();
        List<TpPhieuNhapChiTietCustom> listTimKiem = new ArrayList<>();

        // tìm kiếm theo tên mã vị trí
        checkCbb(phieuNhapChiTietService.loc(this.cbbMauSac.getSelectedIndex())).forEach(el -> {
            String search = "";
            List<String> strings = new ArrayList<>();

            // truyền tham số
            switch (rdo) {
                case 0:
                    search = el.getIdSanPham().getSanPham().getMa();
                    break;
                case 1:
                    search = el.getIdSanPham().getSanPham().getTen();
                    break;
                case 2:
                    search = String.valueOf(el.getIdSanPham().getNamBaoHanh());
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

    public List<TpPhieuNhapChiTietCustom> checkCbb(MauConstant cs) {
        List<TpPhieuNhapChiTietCustom> listTimKiem = new ArrayList<>();
        listCtpnCustom.forEach(el -> {
            if (el.getIdSanPham().getMau() == cs) {
                listTimKiem.add(el);
            }
        });
        return listTimKiem;
    }

    public void searchRadio() {
        if (rdoMaSp.isSelected()) {
            loadTable(listSearch(0));
        } else if (rdoTenSp.isSelected()) {
            loadTable(listSearch(1));
        } else {
            loadTable(listSearch(2));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UpdateCtsp = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        buttonGradient1 = new utilities.palette.ButtonGradient();
        panelRound1 = new utilities.palette.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCtpn = new utilities.palette.TableDark_1();
        panelRound4 = new utilities.palette.PanelRound();
        rdoMaSp = new utilities.palette.RadioButtonCustom();
        rdoTenSp = new utilities.palette.RadioButtonCustom();
        txtSearch = new utilities.palette.SearchCustom.TextFieldAnimation();
        rdoNbh = new utilities.palette.RadioButtonCustom();
        cbbMauSac = new utilities.palette.Combobox();
        panelRound5 = new utilities.palette.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        panelRound8 = new utilities.palette.PanelRound();
        lbHinhAnh = new utilities.palette.MyButton();
        panelRound15 = new utilities.palette.PanelRound();
        myButton6 = new utilities.palette.MyButton();
        myButton7 = new utilities.palette.MyButton();
        txtMaPhieu = new utilities.palette.TextField();
        txtMau = new utilities.palette.TextField();
        txtTenSP = new utilities.palette.TextField();
        txtDonVi = new utilities.palette.TextField();
        txtNamBH = new utilities.palette.TextField();
        txtGiaNhap = new utilities.palette.TextField();
        panelRound19 = new utilities.palette.PanelRound();
        btnShow18 = new utilities.palette.MyButton();
        btnShow19 = new utilities.palette.MyButton();

        UpdateCtsp.setSize(new java.awt.Dimension(400, 300));

        jLabel1.setText("Số lượng bạn muốn thay đổi");

        jLabel3.setText("Giá nhập bạn muốn thay đổi");

        buttonGradient1.setText("XÁC NHẬN");
        buttonGradient1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UpdateCtspLayout = new javax.swing.GroupLayout(UpdateCtsp.getContentPane());
        UpdateCtsp.getContentPane().setLayout(UpdateCtspLayout);
        UpdateCtspLayout.setHorizontalGroup(
            UpdateCtspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateCtspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UpdateCtspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpdateCtspLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(UpdateCtspLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UpdateCtspLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        UpdateCtspLayout.setVerticalGroup(
            UpdateCtspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpdateCtspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(UpdateCtspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UpdateCtspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));

        panelRound1.setBackground(new java.awt.Color(221, 242, 244));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        tbCtpn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Màu", "Size", "Đơn vị", "Năm bảo hành", "Số lượng", "Giá nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCtpn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCtpnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbCtpn);

        panelRound4.setBackground(new java.awt.Color(67, 130, 187));
        panelRound4.setRoundBottomLeft(50);
        panelRound4.setRoundBottomRight(50);
        panelRound4.setRoundTopLeft(50);
        panelRound4.setRoundTopRight(50);

        rdoMaSp.setBackground(new java.awt.Color(67, 130, 187));
        rdoMaSp.setForeground(new java.awt.Color(255, 255, 255));
        rdoMaSp.setText("Mã sản phẩm");
        rdoMaSp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        rdoTenSp.setBackground(new java.awt.Color(67, 130, 187));
        rdoTenSp.setForeground(new java.awt.Color(255, 255, 255));
        rdoTenSp.setText("Tên sản phẩm");
        rdoTenSp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });

        rdoNbh.setBackground(new java.awt.Color(67, 130, 187));
        rdoNbh.setForeground(new java.awt.Color(255, 255, 255));
        rdoNbh.setText("Năm bảo hành");
        rdoNbh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        cbbMauSac.setBackground(new java.awt.Color(67, 130, 187));
        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blue", "Red", "Pig" }));
        cbbMauSac.setSelectedIndex(-1);
        cbbMauSac.setToolTipText("Chọn màu sắc sản phẩm muốn tìm kiếm");
        cbbMauSac.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbMauSac.setLabeText("Màu sắc");
        cbbMauSac.setLineColor(new java.awt.Color(145, 200, 249));
        cbbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauSacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(rdoMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoNbh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(rdoMaSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdoTenSp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdoNbh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelRound5.setBackground(new java.awt.Color(67, 130, 187));
        panelRound5.setRoundBottomLeft(50);
        panelRound5.setRoundBottomRight(50);
        panelRound5.setRoundTopLeft(50);
        panelRound5.setRoundTopRight(50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DANH SÁCH PHIẾU NHẬP CHI TIẾT");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addContainerGap(28, Short.MAX_VALUE))
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

        lbHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.png"))); // NOI18N
        lbHinhAnh.setBorderColor(new java.awt.Color(221, 242, 244));
        lbHinhAnh.setColor(new java.awt.Color(221, 242, 244));
        lbHinhAnh.setRadius(50);

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound15.setBackground(new java.awt.Color(67, 130, 187));
        panelRound15.setRoundBottomLeft(50);
        panelRound15.setRoundBottomRight(50);
        panelRound15.setRoundTopLeft(50);
        panelRound15.setRoundTopRight(50);

        myButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.png"))); // NOI18N
        myButton6.setBorderColor(new java.awt.Color(221, 242, 244));
        myButton6.setColor(new java.awt.Color(221, 242, 244));
        myButton6.setRadius(50);

        myButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete_2.png"))); // NOI18N
        myButton7.setBorderColor(new java.awt.Color(221, 242, 244));
        myButton7.setColor(new java.awt.Color(221, 242, 244));
        myButton7.setRadius(50);

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(myButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtMaPhieu.setEditable(false);
        txtMaPhieu.setBackground(new java.awt.Color(221, 242, 244));
        txtMaPhieu.setToolTipText("");
        txtMaPhieu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMaPhieu.setLabelText("Mã phiếu");

        txtMau.setEditable(false);
        txtMau.setBackground(new java.awt.Color(221, 242, 244));
        txtMau.setToolTipText("");
        txtMau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMau.setLabelText("Màu");

        txtTenSP.setEditable(false);
        txtTenSP.setBackground(new java.awt.Color(221, 242, 244));
        txtTenSP.setToolTipText("");
        txtTenSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTenSP.setLabelText("Tên sản phẩm");

        txtDonVi.setEditable(false);
        txtDonVi.setBackground(new java.awt.Color(221, 242, 244));
        txtDonVi.setToolTipText("");
        txtDonVi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDonVi.setLabelText("Đơn vị");

        txtNamBH.setEditable(false);
        txtNamBH.setBackground(new java.awt.Color(221, 242, 244));
        txtNamBH.setToolTipText("");
        txtNamBH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNamBH.setLabelText("Năm bảo hành");

        txtGiaNhap.setEditable(false);
        txtGiaNhap.setBackground(new java.awt.Color(221, 242, 244));
        txtGiaNhap.setToolTipText("");
        txtGiaNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaNhap.setLabelText("Giá nhập");

        panelRound19.setBackground(new java.awt.Color(102, 255, 255));
        panelRound19.setRoundBottomLeft(50);
        panelRound19.setRoundBottomRight(50);
        panelRound19.setRoundTopLeft(50);
        panelRound19.setRoundTopRight(50);

        btnShow18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Show.png"))); // NOI18N
        btnShow18.setToolTipText("Hiển thị");
        btnShow18.setBorderColor(new java.awt.Color(221, 242, 244));
        btnShow18.setColor(new java.awt.Color(221, 242, 244));
        btnShow18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnShow18.setRadius(50);
        btnShow18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShow18ActionPerformed(evt);
            }
        });

        btnShow19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Delete.png"))); // NOI18N
        btnShow19.setToolTipText("Đóng form");
        btnShow19.setBorderColor(new java.awt.Color(221, 242, 244));
        btnShow19.setColor(new java.awt.Color(221, 242, 244));
        btnShow19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnShow19.setRadius(50);
        btnShow19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShow19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound19Layout = new javax.swing.GroupLayout(panelRound19);
        panelRound19.setLayout(panelRound19Layout);
        panelRound19Layout.setHorizontalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound19Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnShow18, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnShow19, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        panelRound19Layout.setVerticalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnShow19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnShow18, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelRound1Layout.createSequentialGroup()
                                    .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(167, 167, 167)
                                    .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelRound1Layout.createSequentialGroup()
                                    .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelRound19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(115, 115, 115)))))
                .addGap(31, 31, 31))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(57, 57, 57))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelRound19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShow18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShow18ActionPerformed
        listCtpnCustom = phieuNhapChiTietService.getListCTPhieuNhapByID(this.phieuNhap.getId());
        loadTable(listCtpnCustom);
    }//GEN-LAST:event_btnShow18ActionPerformed

    private void btnShow19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShow19ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnShow19ActionPerformed

    private void cbbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauSacActionPerformed
        // TODO add your handling code here:
        searchRadio();
    }//GEN-LAST:event_cbbMauSacActionPerformed

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        // TODO add your handling code here:
        searchRadio();
    }//GEN-LAST:event_txtSearchMouseClicked

    private void myButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myButton8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton8MouseClicked

    private void tbCtpnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCtpnMouseClicked
        int row = this.tbCtpn.getSelectedRow();
        if (row == -1) {
            return;
        }
        TpPhieuNhapChiTietCustom ctpn = listCtpnCustom.get(row);
        txtMaPhieu.setText(ctpn.getIdPhieuNhap().getId().toString());
        txtTenSP.setText(ctpn.getIdSanPham().getSanPham().getTen());
        txtMau.setText(Converter.trangThaiMauSac(ctpn.getIdSanPham().getMau()));
        txtDonVi.setText(ctpn.getIdSanPham().getDonVi().getDonViQuyDoi());
        txtNamBH.setText(String.valueOf(ctpn.getIdSanPham().getNamBaoHanh()));
        txtGiaNhap.setText(String.valueOf(ctpn.getIdSanPham().getGiaNhap()));

        if (phieuNhap.getTrangThai().equals(TrangThaiPhieuConstant.DA_THANH_TOAN)) {
            MsgBox.alert(this, "Phiếu nhập này đã ở trạng thái đã thanh toán nên không thể sửa số lượng! ");
            return;
        }
        UpdateCtsp.setVisible(true);

    }//GEN-LAST:event_tbCtpnMouseClicked

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        if (txtSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
            return;
        }
        if (txtGia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán không được để trống");
            return;
        }
        try {
            int a = Integer.parseInt(txtSoLuong.getText());
            Double b = Double.parseDouble(txtGia.getText());
            if (a <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn phải nhập số lượng lớn hơn 0");
                return;
            }
            if (b <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn phải nhập giá bán lớn hơn 0");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập là kiểu số");
            return;
        }
        int row = this.tbCtpn.getSelectedRow();

        TpPhieuNhapChiTietCustom ctpn = listCtpnCustom.get(row);
        for (TpXemChiTietSanPhamCustom ctsp : ctspService.listCtsp()) {
            if (ctpn.getIdSanPham().getId().equals(ctsp.getId())) {
                ctsp.setSoLuongTon(Integer.parseInt(txtSoLuong.getText()));
                ctsp.setGiaNhap(BigDecimal.valueOf(Double.parseDouble(txtGia.getText())));
                ctspService.updateCTSP(ctsp);
            }
        }
        ctpn.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        phieuNhapChiTietService.upDatePN(ctpn);
        listCtpnCustom.set(row, ctpn);
        MsgBox.alert(this, "Bạn đã update số lượng và đơn giá thành công");
        loadTable(listCtpnCustom);
        UpdateCtsp.dispose();
    }//GEN-LAST:event_buttonGradient1ActionPerformed

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
            java.util.logging.Logger.getLogger(TpLuongNhapChiTietPhieuNhapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TpLuongNhapChiTietPhieuNhapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TpLuongNhapChiTietPhieuNhapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TpLuongNhapChiTietPhieuNhapForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TpLuongNhapChiTietPhieuNhapForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog UpdateCtsp;
    private utilities.palette.MyButton btnShow18;
    private utilities.palette.MyButton btnShow19;
    private utilities.palette.ButtonGradient buttonGradient1;
    private utilities.palette.Combobox cbbMauSac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private utilities.palette.MyButton lbHinhAnh;
    private utilities.palette.MyButton myButton6;
    private utilities.palette.MyButton myButton7;
    private utilities.palette.PanelRound panelRound1;
    private utilities.palette.PanelRound panelRound15;
    private utilities.palette.PanelRound panelRound19;
    private utilities.palette.PanelRound panelRound4;
    private utilities.palette.PanelRound panelRound5;
    private utilities.palette.PanelRound panelRound8;
    private utilities.palette.RadioButtonCustom rdoMaSp;
    private utilities.palette.RadioButtonCustom rdoNbh;
    private utilities.palette.RadioButtonCustom rdoTenSp;
    private utilities.palette.TableDark_1 tbCtpn;
    private utilities.palette.TextField txtDonVi;
    private javax.swing.JTextField txtGia;
    private utilities.palette.TextField txtGiaNhap;
    private utilities.palette.TextField txtMaPhieu;
    private utilities.palette.TextField txtMau;
    private utilities.palette.TextField txtNamBH;
    private utilities.palette.SearchCustom.TextFieldAnimation txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private utilities.palette.TextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
