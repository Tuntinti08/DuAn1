package cores.truongPhongs.views;

import cores.importPdf.views.ImportView;
import cores.truongPhongs.customModels.TpPhieuNhapChiTietCustom;
import cores.truongPhongs.customModels.TpPhieuNhapCustom;
import cores.truongPhongs.services.TpPhieuNhapChiTietService;
import cores.truongPhongs.services.serviceImpls.TpPhieuNhapServiceImpl;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import cores.truongPhongs.services.TpPhieuNhapService;
import cores.truongPhongs.services.serviceImpls.TpPhieuNhapChiTietServiceImpl;
import domainModels.NhanVien;
import infrastructures.constant.TrangThaiPhieuConstant;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.swing.JOptionPane;
import utilities.Auth;
import utilities.Converter;
import utilities.MaTuSinh;
import utilities.MsgBox;
import utilities.Page;
import utilities.palette.SearchCustom.EventCallBack;
import utilities.palette.SearchCustom.EventTextField;

/**
 *
 * @author Acer
 */
public class TpLuongNhapView extends javax.swing.JPanel {

    /**
     * Creates new form luongNhapHang
     */
    private TpPhieuNhapService phieuNhapService;
    private List<TpPhieuNhapCustom> listPn;
    private NhanVien nhanVien;
    private TpLuongNhapChiTietSanPhamForm ctspView;
    private TpLuongNhapChiTietPhieuNhapForm ctpnView;
    private TpPhieuNhapChiTietService tpncts;
    private List<TpPhieuNhapChiTietCustom> listCtpn = new ArrayList<>();
    private Page p;
    
    private DecimalFormat formatter = new DecimalFormat("###,###,##0 VNĐ");
    
    private int limit = 7;
    
    private int offset = 0;
    
    private int sizes = 0;
    
    private int index = 1;
    
    public TpLuongNhapView() {
        initComponents();
        rdoMa.setSelected(true);
        listPn = new ArrayList<>();
        phieuNhapService = new TpPhieuNhapServiceImpl();
        tpncts = new TpPhieuNhapChiTietServiceImpl();
        ctspView = new TpLuongNhapChiTietSanPhamForm();
        ctpnView = new TpLuongNhapChiTietPhieuNhapForm();
        clearForm();
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
        cbbTrangThai.setSelectedIndex(0);
        p = new Page();
        listPn = phieuNhapService.getListPn();
        sizes = listPn.size();
        loadTablePn(phieuNhapService.phanTrang(listPn, offset, limit));
        loadIndex();
    }
    
    public TpLuongNhapView(UUID id) {
        initComponents();
        p = new Page();
        nhanVien = new NhanVien();
        nhanVien.setId(Auth.nhanVien.getId());
        nhanVien.setMa(Auth.nhanVien.getMa());
        nhanVien.setEmail(Auth.nhanVien.getEmail());
        nhanVien.setDiaChi(Auth.nhanVien.getDiaChi());
        nhanVien.setGioiTinh(Auth.nhanVien.getGioiTinh());
        nhanVien.setHinhAnh(Auth.nhanVien.getHinhAnh());
        nhanVien.setIdChucVu(Auth.nhanVien.getChucVu());
        nhanVien.setMatKhau(Auth.nhanVien.getMatKhau());
        nhanVien.setNgaySinh(Auth.nhanVien.getNgaySinh());
        nhanVien.setSdt(Auth.nhanVien.getSdt());
        nhanVien.setTen(Auth.nhanVien.getTen());
        nhanVien.setTrangThai(Auth.nhanVien.getTrangThai());
        phieuNhapService = new TpPhieuNhapServiceImpl();
        ctspView = new TpLuongNhapChiTietSanPhamForm();
        TpPhieuNhapCustom phieuNhap = new TpPhieuNhapCustom();
        phieuNhap.setIdNcc(id);
        phieuNhap.setMaPhieu(MaTuSinh.gen("PN"));
        phieuNhap.setIdNhanVien(nhanVien.getId());
        phieuNhap.setNgayTao(new Date().getTime());
        phieuNhapService.addPn(phieuNhap);
        listPn = new ArrayList<>();
        listPn = phieuNhapService.getListPn();
        sizes = listPn.size();
        loadTablePn(phieuNhapService.phanTrang(listPn, offset, limit));
    }
    
    private void loadTablePn(List<TpPhieuNhapCustom> list) {
        DefaultTableModel dtm = (DefaultTableModel) this.tblPhieuNhap.getModel();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        dtm.setRowCount(0);
        for (TpPhieuNhapCustom pn : list) {
            Object[] rowData = {
                dtm.getRowCount() + 1,
                pn.getMaPhieu(),
                simpleDateFormat.format(new Date(pn.getNgayTao())),
                pn.getNgayThanhToan() == null ? "Chưa có" : simpleDateFormat.format(new Date(pn.getNgayThanhToan())),
                pn.getTenNcc(),
                pn.getTenNhanVien(),
                Converter.trangThaiPhieuNhap(pn.getTrangThai())
            };
            dtm.addRow(rowData);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        panelRound1 = new utilities.palette.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuNhap = new utilities.palette.TableDark_1();
        panelRound4 = new utilities.palette.PanelRound();
        rdoMa = new utilities.palette.RadioButtonCustom();
        rdoNcc = new utilities.palette.RadioButtonCustom();
        rdoNhanVien = new utilities.palette.RadioButtonCustom();
        txtSearch = new utilities.palette.SearchCustom.TextFieldAnimation();
        panelRound5 = new utilities.palette.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        panelRound8 = new utilities.palette.PanelRound();
        cbbTrangThai = new utilities.palette.Combobox();
        panelRound9 = new utilities.palette.PanelRound();
        rdoNgayTao = new utilities.palette.RadioButtonCustom();
        rdoNgayThanhToan = new utilities.palette.RadioButtonCustom();
        ngayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ngayKetThuc = new com.toedter.calendar.JDateChooser();
        btnSearch2 = new utilities.palette.MyButton();
        panelRound15 = new utilities.palette.PanelRound();
        btnPhieuNhapChiTiet = new utilities.palette.MyButton();
        btnThemPhieuNhapMoi = new utilities.palette.MyButton();
        btnThemSpVaoPhieu = new utilities.palette.MyButton();
        btnShow = new utilities.palette.MyButton();
        btnPhieuNhapChiTiet1 = new utilities.palette.MyButton();
        btnPre = new utilities.palette.UWPButton();
        txtIndex = new javax.swing.JLabel();
        btnNext = new utilities.palette.UWPButton();
        panelRound3 = new utilities.palette.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        txtNgayTao = new utilities.palette.TextField();
        txtMaPhieu = new utilities.palette.TextField();
        textAreaScroll1 = new utilities.palette.TextAreaScroll();
        txtGhiChu = new utilities.palette.TextAreaCustom();
        txtTienPhaiTra = new utilities.palette.TextField();
        btnThanhToan = new utilities.palette.MyButton();
        txtNhaCungCap = new utilities.palette.TextField();
        txtNhanVien = new utilities.palette.TextField();
        txtTrangThai = new utilities.palette.TextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelRound1.setBackground(new java.awt.Color(221, 242, 244));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        tblPhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã phiếu", "Ngày tạo", "Ngày thanh toán", "Nhà cung cấp", "Nhân viên", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuNhap);

        panelRound4.setBackground(new java.awt.Color(67, 130, 187));
        panelRound4.setRoundBottomLeft(50);
        panelRound4.setRoundBottomRight(50);
        panelRound4.setRoundTopLeft(50);
        panelRound4.setRoundTopRight(50);

        rdoMa.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup1.add(rdoMa);
        rdoMa.setForeground(new java.awt.Color(255, 255, 255));
        rdoMa.setText("Mã");
        rdoMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        rdoNcc.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup1.add(rdoNcc);
        rdoNcc.setForeground(new java.awt.Color(255, 255, 255));
        rdoNcc.setText("Nhà cung cấp");
        rdoNcc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        rdoNhanVien.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        rdoNhanVien.setText("Nhân viên");
        rdoNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(rdoMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdoNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(rdoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound5.setBackground(new java.awt.Color(67, 130, 187));
        panelRound5.setRoundBottomLeft(50);
        panelRound5.setRoundBottomRight(50);
        panelRound5.setRoundTopLeft(50);
        panelRound5.setRoundTopRight(50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DANH SÁCH PHIẾU NHẬP");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound8.setBackground(new java.awt.Color(67, 130, 187));
        panelRound8.setForeground(new java.awt.Color(255, 255, 255));
        panelRound8.setRoundBottomLeft(50);
        panelRound8.setRoundBottomRight(50);
        panelRound8.setRoundTopLeft(50);
        panelRound8.setRoundTopRight(50);

        cbbTrangThai.setBackground(new java.awt.Color(67, 130, 187));
        cbbTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chờ thanh toán", "Đã hủy", "Đã thanh toán" }));
        cbbTrangThai.setSelectedIndex(-1);
        cbbTrangThai.setToolTipText("Chọn trạng thái để tìm kiếm");
        cbbTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbTrangThai.setLabeText("Trạng thái");
        cbbTrangThai.setLineColor(new java.awt.Color(145, 200, 249));
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound8Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelRound9.setBackground(new java.awt.Color(67, 130, 187));
        panelRound9.setRoundBottomLeft(50);
        panelRound9.setRoundBottomRight(50);
        panelRound9.setRoundTopLeft(50);
        panelRound9.setRoundTopRight(50);

        rdoNgayTao.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup2.add(rdoNgayTao);
        rdoNgayTao.setForeground(new java.awt.Color(255, 255, 255));
        rdoNgayTao.setText("Ngày Tạo");
        rdoNgayTao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        rdoNgayThanhToan.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup2.add(rdoNgayThanhToan);
        rdoNgayThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        rdoNgayThanhToan.setSelected(true);
        rdoNgayThanhToan.setText("Ngày Thanh Toán");
        rdoNgayThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        ngayBatDau.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                ngayBatDauInputMethodTextChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("From:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("To:");

        btnSearch2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search.png"))); // NOI18N
        btnSearch2.setToolTipText("Tìm kiếm theo ngày");
        btnSearch2.setRadius(50);
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound9Layout.createSequentialGroup()
                        .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound9Layout.createSequentialGroup()
                        .addComponent(btnSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        panelRound15.setBackground(new java.awt.Color(67, 130, 187));
        panelRound15.setRoundBottomLeft(50);
        panelRound15.setRoundBottomRight(50);
        panelRound15.setRoundTopLeft(50);
        panelRound15.setRoundTopRight(50);

        btnPhieuNhapChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file.png"))); // NOI18N
        btnPhieuNhapChiTiet.setToolTipText("Phiếu nhập chi tiết");
        btnPhieuNhapChiTiet.setBorderColor(new java.awt.Color(221, 242, 244));
        btnPhieuNhapChiTiet.setColor(new java.awt.Color(221, 242, 244));
        btnPhieuNhapChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPhieuNhapChiTiet.setRadius(50);
        btnPhieuNhapChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuNhapChiTietActionPerformed(evt);
            }
        });

        btnThemPhieuNhapMoi.setBackground(new java.awt.Color(221, 242, 244));
        btnThemPhieuNhapMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Addd.png"))); // NOI18N
        btnThemPhieuNhapMoi.setToolTipText("Thêm mới phiếu nhập");
        btnThemPhieuNhapMoi.setBorderColor(new java.awt.Color(221, 242, 244));
        btnThemPhieuNhapMoi.setColor(new java.awt.Color(221, 242, 244));
        btnThemPhieuNhapMoi.setRadius(50);
        btnThemPhieuNhapMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPhieuNhapMoiActionPerformed(evt);
            }
        });

        btnThemSpVaoPhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-to-basket.png"))); // NOI18N
        btnThemSpVaoPhieu.setToolTipText("Thêm sản phẩm muốn nhập");
        btnThemSpVaoPhieu.setBorderColor(new java.awt.Color(221, 242, 244));
        btnThemSpVaoPhieu.setColor(new java.awt.Color(221, 242, 244));
        btnThemSpVaoPhieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemSpVaoPhieu.setRadius(50);
        btnThemSpVaoPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSpVaoPhieuActionPerformed(evt);
            }
        });

        btnShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Show.png"))); // NOI18N
        btnShow.setToolTipText("Hiện thị danh sách phiếu xuất");
        btnShow.setBorderColor(new java.awt.Color(221, 242, 244));
        btnShow.setColor(new java.awt.Color(221, 242, 244));
        btnShow.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnShow.setRadius(50);
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        btnPhieuNhapChiTiet1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Upload.png"))); // NOI18N
        btnPhieuNhapChiTiet1.setToolTipText("Import sản phẩm");
        btnPhieuNhapChiTiet1.setBorderColor(new java.awt.Color(221, 242, 244));
        btnPhieuNhapChiTiet1.setColor(new java.awt.Color(221, 242, 244));
        btnPhieuNhapChiTiet1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPhieuNhapChiTiet1.setRadius(50);
        btnPhieuNhapChiTiet1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuNhapChiTiet1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPhieuNhapChiTiet1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemPhieuNhapMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPhieuNhapChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThemSpVaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemSpVaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPhieuNhapChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemPhieuNhapMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPhieuNhapChiTiet1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(panelRound9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelRound1Layout.createSequentialGroup()
                                    .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(67, 67, 67)
                                    .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(txtIndex)
                        .addGap(67, 67, 67)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(panelRound9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtIndex)))
                .addGap(38, 38, 38))
        );

        panelRound3.setBackground(new java.awt.Color(228, 206, 224));
        panelRound3.setRoundBottomLeft(50);
        panelRound3.setRoundBottomRight(50);
        panelRound3.setRoundTopLeft(50);
        panelRound3.setRoundTopRight(50);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thông tin phiếu nhập");

        txtNgayTao.setEditable(false);
        txtNgayTao.setBackground(new java.awt.Color(228, 206, 224));
        txtNgayTao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNgayTao.setLabelText("Ngày tạo");

        txtMaPhieu.setEditable(false);
        txtMaPhieu.setBackground(new java.awt.Color(228, 206, 224));
        txtMaPhieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaPhieu.setLabelText("Mã phiếu ");

        textAreaScroll1.setBackground(new java.awt.Color(153, 204, 255));
        textAreaScroll1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textAreaScroll1.setLabelText("Ghi chú");

        txtGhiChu.setBackground(new java.awt.Color(228, 206, 224));
        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        txtGhiChu.setDisabledTextColor(new java.awt.Color(204, 204, 255));
        textAreaScroll1.setViewportView(txtGhiChu);

        txtTienPhaiTra.setEditable(false);
        txtTienPhaiTra.setBackground(new java.awt.Color(228, 206, 224));
        txtTienPhaiTra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienPhaiTra.setLabelText("Tiền phải trả");

        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/credit-card.png"))); // NOI18N
        btnThanhToan.setText("XÁC NHẬN");
        btnThanhToan.setToolTipText("THANH TOÁN");
        btnThanhToan.setBorderColor(new java.awt.Color(221, 242, 244));
        btnThanhToan.setColor(new java.awt.Color(221, 242, 244));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setRadius(50);
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtNhaCungCap.setEditable(false);
        txtNhaCungCap.setBackground(new java.awt.Color(228, 206, 224));
        txtNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNhaCungCap.setLabelText("Nhà cung cấp");

        txtNhanVien.setEditable(false);
        txtNhanVien.setBackground(new java.awt.Color(228, 206, 224));
        txtNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNhanVien.setLabelText("Nhân viên");

        txtTrangThai.setEditable(false);
        txtTrangThai.setBackground(new java.awt.Color(228, 206, 224));
        txtTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTrangThai.setLabelText("Trạng thái");
        txtTrangThai.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTrangThaiCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTienPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtNgayTao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtNhaCungCap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtNhanVien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTrangThai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))))
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)))
                .addContainerGap(6, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txtNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtTienPhaiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbTrangThaiActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
        searchhRadio();
    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void btnThemPhieuNhapMoiActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnThemPhieuNhapMoiActionPerformed
        TpLuongNhapThemNccVaoPhieuNhapForm themNcc = new TpLuongNhapThemNccVaoPhieuNhapForm();
        themNcc.setVisible(true);

    }//GEN-LAST:event_btnThemPhieuNhapMoiActionPerformed

    private void btnPhieuNhapChiTietActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnPhieuNhapChiTietActionPerformed
        if (chon() == null) {
            return;
        }
        TpLuongNhapChiTietPhieuNhapForm chonStpn = new TpLuongNhapChiTietPhieuNhapForm();
        chonStpn.setPhieuNhap(chon());
        chonStpn.setVisible(true);
    }//GEN-LAST:event_btnPhieuNhapChiTietActionPerformed
    
    public TpPhieuNhapCustom chon() {
        int row = this.tblPhieuNhap.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn 1 phiếu nhập");
            return null;
        }
        TpPhieuNhapCustom px = new TpPhieuNhapCustom();
        px.setId(listPn.get(row).getId());
        px.setMaPhieu(listPn.get(row).getMaPhieu());
        px.setNgayTao(listPn.get(row).getNgayTao());
        px.setNgayThanhToan(listPn.get(row).getNgayThanhToan());
        px.setNgayTao(listPn.get(row).getNgayTao());
        px.setGhiChu(listPn.get(row).getGhiChu());
        px.setIdNcc(listPn.get(row).getIdNcc());
        px.setIdNhanVien(listPn.get(row).getId());
        px.setTrangThai(listPn.get(row).getTrangThai());
        return px;
    }
    private void btnThemSpVaoPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSpVaoPhieuActionPerformed
        if (chon() == null) {
            return;
        }
        int row = this.tblPhieuNhap.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một phiếu nhập!");
            return;
        }
        if (tblPhieuNhap.getValueAt(row, 6).toString().equals("Đã thanh toán") || tblPhieuNhap.getValueAt(row, 6).toString().equals("Đã hủy")) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một phiếu nhập chưa thanh toán!");
            return;
        }
        TpLuongNhapChiTietSanPhamForm chonSp = new TpLuongNhapChiTietSanPhamForm();
        chonSp.setPhieuNhap(chon());
        chonSp.setVisible(true);
    }//GEN-LAST:event_btnThemSpVaoPhieuActionPerformed
    
    public List<TpPhieuNhapCustom> getListByTT(int rdo) {
        String timKiem = this.txtSearch.getText();
        listPn = phieuNhapService.phanTrang(phieuNhapService.findAllByKhAndNV(timKiem, phieuNhapService.loc(cbbTrangThai.getSelectedIndex()), rdo), offset, limit);
        return listPn;
    }
    
    public void searchhRadio() {
        
        if (rdoMa.isSelected()) {
            loadTablePn(getListByTT(0));
        }
        if (rdoNcc.isSelected()) {
            loadTablePn(getListByTT(1));
        }
        if (rdoNhanVien.isSelected()) {
            loadTablePn(getListByTT(2));
        }
    }
    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        // TODO add your handling code here:
        searchhRadio();
        sizes = listPn.size();
        loadIndex();
    }//GEN-LAST:event_txtSearchMouseClicked

    private void tblPhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapMouseClicked
        clearForm();
        int row = tblPhieuNhap.getSelectedRow();
        if (row == -1) {
            return;
        }
        String maPhieu = this.tblPhieuNhap.getValueAt(row, 1).toString();
        String ngayTao = this.tblPhieuNhap.getValueAt(row, 2).toString();
        String ngayThanhToan = this.tblPhieuNhap.getValueAt(row, 3).toString();
        String nhaCungCap = this.tblPhieuNhap.getValueAt(row, 4).toString();
        String nhanVien = this.tblPhieuNhap.getValueAt(row, 5).toString();
        String trangThai = this.tblPhieuNhap.getValueAt(row, 6).toString();
        
        txtMaPhieu.setText(maPhieu);
        txtNgayTao.setText(ngayTao);
        txtTrangThai.setText(trangThai);
        txtNhanVien.setText(nhanVien);
        txtNhaCungCap.setText(nhaCungCap);
        TpPhieuNhapCustom pxcs = phieuNhapService.phanTrang(listPn, offset, limit).get(row);
        if (pxcs.getGhiChu() == null) {
            txtGhiChu.setText("new");
        } else {
            txtGhiChu.setText(pxcs.getGhiChu());
        }
        
        double tien = 0;
        List<TpPhieuNhapChiTietCustom> listCTPX = tpncts.getListCTPhieuNhapByID(phieuNhapService.phanTrang(listPn, offset, limit).get(row).getId());
        for (TpPhieuNhapChiTietCustom ctpx : listCTPX) {
            tien += ctpx.getSoLuong() * ctpx.getIdSanPham().getGiaNhap().doubleValue();
        }
        txtTienPhaiTra.setText(formatter.format(tien) + "");
        
        if (txtTrangThai.getText().equalsIgnoreCase("Đã Thanh Toán")) {
            btnThanhToan.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
        }

    }//GEN-LAST:event_tblPhieuNhapMouseClicked
    
    private void clearForm() {
        txtGhiChu.setText("");
        txtMaPhieu.setText("");
        txtNgayTao.setText("");
        txtNhaCungCap.setText("");
        txtTienPhaiTra.setText("");
        txtTrangThai.setText("");
        rdoMa.setSelected(false);
        rdoNhanVien.setSelected(false);
        cbbTrangThai.setSelectedItem(Converter.TrangThaiPhieuXuat(TrangThaiPhieuConstant.DA_THANH_TOAN));
        ngayBatDau.setDate(null);
        ngayKetThuc.setDate(null);
        rdoNgayTao.setSelected(false);
        rdoNgayThanhToan.setSelected(false);
        sizes = listPn.size();
    }
    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        listPn = phieuNhapService.getListPn();
        
        loadTablePn(phieuNhapService.phanTrang(listPn, offset, limit));
        clearForm();
    }//GEN-LAST:event_btnShowActionPerformed
    public void TimKiemTheoNgay() {
        if (ngayBatDau.getDate() == null) {
            
            return;
        }
        if (ngayKetThuc.getDate() == null) {
            
            return;
        }
        
        if (rdoNgayTao.isSelected()) {
            listPn = phieuNhapService.phanTrang(phieuNhapService.getListByNgayTao(ngayBatDau.getDate().getTime(), ngayKetThuc.getDate().getTime()), offset, limit);
            loadTablePn(listPn);
            sizes = listPn.size();
            loadIndex();
        } else {
            listPn = phieuNhapService.phanTrang(phieuNhapService.getListByNgayThanhToan(ngayBatDau.getDate().getTime(), ngayKetThuc.getDate().getTime()), offset, limit);
            loadTablePn(listPn);
            sizes = listPn.size();
            loadIndex();
        }
    }
    

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        clearForm();
        int row = this.tblPhieuNhap.getSelectedRow();
        if (row == -1) {
            MsgBox.alert(this, "Bạn phải chọn 1 phiếu nhập");
            return;
        }
        listCtpn = tpncts.getListCTPhieuNhapByID(listPn.get(row).getId());
        if (listCtpn.size() == 0) {
            MsgBox.alert(this, "Bạn phải thêm sản phẩm trước khi thanh toán");
            return;
        }
        if (listPn.get(row).getTrangThai() == TrangThaiPhieuConstant.DA_THANH_TOAN) {
            MsgBox.alert(this, "Phiếu nhập này đã thanh toán. Vui lòng thanh toán phiếu nhập khác");
            return;
        }
        
        TpPhieuNhapCustom pxcs = listPn.get(row);
        pxcs.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        pxcs.setGhiChu(txtGhiChu.getText());
        System.out.println(pxcs.getGhiChu());
        phieuNhapService.updatePn(pxcs);
        
        MsgBox.alert(this, "Bạn đã xác nhận thành công");
        listPn.set(row, pxcs);
        loadTablePn(phieuNhapService.phanTrang(listPn, offset, limit));
        clearForm();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void ngayBatDauInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ngayBatDauInputMethodTextChanged
        // TODO add your handling code here:
        TimKiemTheoNgay();
    }//GEN-LAST:event_ngayBatDauInputMethodTextChanged

    private void btnPhieuNhapChiTiet1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuNhapChiTiet1ActionPerformed
        int row = tblPhieuNhap.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một dòng", "ERROR !!!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (listPn.get(row).getTrangThai() == TrangThaiPhieuConstant.DA_THANH_TOAN) {
            MsgBox.alert(this, "Phiếu nhập này đã thanh toán. Vui lòng thanh toán phiếu nhập khác");
            return;
        }
        
        TpPhieuNhapCustom pxcs = listPn.get(row);
        ImportView im = new ImportView();
        im.setIdPhieuNhap(pxcs.getId());
        im.setVisible(true);
    }//GEN-LAST:event_btnPhieuNhapChiTiet1ActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        index = p.prevIndex(offset, limit, index);
        offset = p.prev(offset, limit);
        loadIndex();
        //        loadTable(getList);
        loadTablePn(phieuNhapService.phanTrang(listPn, offset, limit));
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        index = p.nextIndex(offset, limit, sizes, index);
        offset = p.next(offset, limit, sizes);
        loadIndex();
        //        loadTable(getList);
//        listPn = phieuNhapService.getListPn();
        loadTablePn(phieuNhapService.phanTrang(listPn, offset, limit));
    }//GEN-LAST:event_btnNextActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
        // TODO add your handling code here:
        TimKiemTheoNgay();
    }//GEN-LAST:event_btnSearch2ActionPerformed

    private void txtTrangThaiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTrangThaiCaretUpdate
        // TODO add your handling code here
        if (txtTrangThai.getText().equalsIgnoreCase("Đã Thanh Toán")) {
            btnThanhToan.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
        }
    }//GEN-LAST:event_txtTrangThaiCaretUpdate
    private void loadIndex() {
        this.txtIndex.setText(String.valueOf(index) + " / " + (Math.round((sizes / limit) + 0.5)));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.palette.UWPButton btnNext;
    private utilities.palette.MyButton btnPhieuNhapChiTiet;
    private utilities.palette.MyButton btnPhieuNhapChiTiet1;
    private utilities.palette.UWPButton btnPre;
    private utilities.palette.MyButton btnSearch2;
    private utilities.palette.MyButton btnShow;
    private utilities.palette.MyButton btnThanhToan;
    private utilities.palette.MyButton btnThemPhieuNhapMoi;
    private utilities.palette.MyButton btnThemSpVaoPhieu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private utilities.palette.Combobox cbbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser ngayBatDau;
    private com.toedter.calendar.JDateChooser ngayKetThuc;
    private utilities.palette.PanelRound panelRound1;
    private utilities.palette.PanelRound panelRound15;
    private utilities.palette.PanelRound panelRound3;
    private utilities.palette.PanelRound panelRound4;
    private utilities.palette.PanelRound panelRound5;
    private utilities.palette.PanelRound panelRound8;
    private utilities.palette.PanelRound panelRound9;
    private utilities.palette.RadioButtonCustom rdoMa;
    private utilities.palette.RadioButtonCustom rdoNcc;
    private utilities.palette.RadioButtonCustom rdoNgayTao;
    private utilities.palette.RadioButtonCustom rdoNgayThanhToan;
    private utilities.palette.RadioButtonCustom rdoNhanVien;
    private utilities.palette.TableDark_1 tblPhieuNhap;
    private utilities.palette.TextAreaScroll textAreaScroll1;
    private utilities.palette.TextAreaCustom txtGhiChu;
    private javax.swing.JLabel txtIndex;
    private utilities.palette.TextField txtMaPhieu;
    private utilities.palette.TextField txtNgayTao;
    private utilities.palette.TextField txtNhaCungCap;
    private utilities.palette.TextField txtNhanVien;
    private utilities.palette.SearchCustom.TextFieldAnimation txtSearch;
    private utilities.palette.TextField txtTienPhaiTra;
    private utilities.palette.TextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
