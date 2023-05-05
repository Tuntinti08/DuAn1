package cores.nhanVienQuanLy.views;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import cores.exportPDF.services.ExportSanPhamService;
import cores.exportPDF.services.serviceImpls.ExportSanPhamServiceImpl;
import cores.nhanVienQuanLy.customModels.Luong_ChiTietPhieuXuatCustom;
import cores.nhanVienQuanLy.customModels.PhieuXuatCustom;
import cores.nhanVienQuanLy.services.NVQLQuanLyPhieuXuatService;
import cores.nhanVienQuanLy.services.Tai_NvqlLuongPhieuXuatService;
import cores.nhanVienQuanLy.services.serviceImpls.NVQLQuanLyPhieuXuatServiceImpl;
import cores.nhanVienQuanLy.services.serviceImpls.Tai_NvqlLuongPhieuXuatServiceImpl;
import cores.webCam.serivces.ChiTietSanPhamService;
import cores.webCam.serivces.serviceImpl.ChiTietSanPhamServiceImpl;
import domainModels.PhieuXuat;
import infrastructures.constant.TrangThaiPhieuConstant;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lombok.Synchronized;
import utilities.Converter;
import utilities.DateTimeUtil;
import utilities.MsgBox;
import utilities.Page;

/**
 *
 * @author Acer
 */
public class Tai_NvqlLuongPhieuXuatView extends javax.swing.JPanel {

    private Tai_NvqlLuongPhieuXuatService luongPxService;
    private NVQLQuanLyPhieuXuatService phieuXuatService;
    private List<PhieuXuatCustom> listPhieuXuat;
    private Tai_LuongPhieuXuat_CTPhieuXuatView ctpxView;
    private Tai_LuongPhieuXuat_CTSanPhamView ctspView;
    private Tai_ChonKhachHangView chonKhView;
    private ExportSanPhamService esps;
    private ChiTietSanPhamService serviceCam;
    List<Luong_ChiTietPhieuXuatCustom> listCTPX;
    private ConcurrentHashMap<UUID, domainModels.ChiTietSanPham> map;
    private DecimalFormat formatter = new DecimalFormat("###,###,##0 VNĐ");
    String pattern = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    private WebcamPanel jpanl = null;
    private Webcam webcam1;
    private Thread capture;

    private Page p;

    private int limit = 7;

    private int offset = 0;

    private int sizes = 0;

    private int index = 1;

    private domainModels.ChiTietSanPham sanPhamQuet;

    public Tai_NvqlLuongPhieuXuatView() {
        serviceCam = new ChiTietSanPhamServiceImpl();
        initComponents();
        diaLogCam.setLocationRelativeTo(null);
        p = new Page();
        listCTPX = new ArrayList<>();
        luongPxService = new Tai_NvqlLuongPhieuXuatServiceImpl();
        phieuXuatService = new NVQLQuanLyPhieuXuatServiceImpl();
        listPhieuXuat = phieuXuatService.getList();
        phieuXuatService.loadComBox(cbbTrangThai);
        esps = new ExportSanPhamServiceImpl();
        sizes = listPhieuXuat.size();
        loadTablePhieuXuat(phieuXuatService.phanTrang(listPhieuXuat, offset, limit));
        clearForm();
    }

    private void loadTablePhieuXuat(List<PhieuXuatCustom> listPX) {
        DefaultTableModel dtm = (DefaultTableModel) this.tblPhieuXuat.getModel();
        dtm.setRowCount(0);

        for (PhieuXuatCustom el : listPX) {
            Date ngayNhan = new Date(el.getNgayTao());
            Object[] rowData = {
                dtm.getRowCount() + 1,
                el.getMaPhieu() == null ? "Không có mã" : el.getMaPhieu(),
                simpleDateFormat.format(ngayNhan),
                el.getNgayThanhToan() == null ? "Chưa thanh toán" : simpleDateFormat.format(el.getNgayThanhToan()),
                Converter.TrangThaiPhieuXuat(el.getTrangThai()),
                el.getNhanVien().getTen(),
                el.getKhachHang().getTen()
            };
            dtm.addRow(rowData);
        }
    }

    private void clearForm() {
        txtGhiChu.setText("");
        txtMaKhachHang.setText("");
        txtMaPhieu.setText("");
        txtNgayTao.setText("");
        txtNgayThanhToan.setText("");
        txtMaNhanVien.setText("");
        txtTienKhachDua.setText("");
        txtTienPhaitra.setText("");
        txtTienThua.setText("");
        txtTrangThai.setText("");
        rdoMa.setSelected(false);
        rdoKhachHang.setSelected(false);
        rdoNhanVien.setSelected(false);
        cbbTrangThai.setSelectedItem(Converter.TrangThaiPhieuXuat(TrangThaiPhieuConstant.DA_THANH_TOAN));
        txtTimKiem.setText("");
        ngayBatDau.setDate(null);
        ngayKetThuc.setDate(null);
        rdoNgayTao.setSelected(false);
        rdoNgayThanhToan.setSelected(false);
        sizes = listPhieuXuat.size();
        offset = 0;
        index = 1;
        loadIndex();
        sanPhamQuet = null;
    }

    private void loadIndex() {
        this.txtIndex.setText(String.valueOf(index) + " / " + (Math.round((sizes / limit) + 0.5)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        diaLogCam = new javax.swing.JFrame();
        panelRound6 = new utilities.palette.PanelRound();
        panelRound16 = new utilities.palette.PanelRound();
        btnCloseCam = new utilities.palette.MyButton();
        btnThemVaoCTPX = new utilities.palette.MyButton();
        jLabel5 = new javax.swing.JLabel();
        txtMaSPQuet = new utilities.palette.TextField();
        txtTenSpQuet = new utilities.palette.TextField();
        txtMauQuet = new utilities.palette.TextField();
        txtSoLuongQuet = new utilities.palette.TextField();
        txtGiaBanQuet = new utilities.palette.TextField();
        txtSoLuongMuaQuet = new utilities.palette.TextField();
        txtSizeQuet = new utilities.palette.TextField();
        txtNgayNhapQuet = new utilities.palette.TextField();
        panelRound2 = new utilities.palette.PanelRound();
        mainCam = new javax.swing.JPanel();
        panelRound1 = new utilities.palette.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuXuat = new utilities.palette.TableDark_1();
        panelRound4 = new utilities.palette.PanelRound();
        rdoNhanVien = new utilities.palette.RadioButtonCustom();
        rdoKhachHang = new utilities.palette.RadioButtonCustom();
        rdoMa = new utilities.palette.RadioButtonCustom();
        txtTimKiem = new utilities.palette.TextField();
        buttonGradient1 = new utilities.palette.ButtonGradient();
        panelRound5 = new utilities.palette.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        panelRound8 = new utilities.palette.PanelRound();
        cbbTrangThai = new utilities.palette.Combobox();
        panelRound15 = new utilities.palette.PanelRound();
        btnCtPhieuXuat = new utilities.palette.MyButton();
        btnCreatPhieuXuat = new utilities.palette.MyButton();
        btnChiTietSP = new utilities.palette.MyButton();
        btnChiTietSP1 = new utilities.palette.MyButton();
        btnQuetMa = new utilities.palette.MyButton();
        btnShow1 = new utilities.palette.MyButton();
        txtIndex = new javax.swing.JLabel();
        panelRound3 = new utilities.palette.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        txtNgayTao = new utilities.palette.TextField();
        txtMaPhieu = new utilities.palette.TextField();
        textAreaScroll1 = new utilities.palette.TextAreaScroll();
        txtGhiChu = new utilities.palette.TextAreaCustom();
        txtTienPhaitra = new utilities.palette.TextField();
        btnThanhToan = new utilities.palette.MyButton();
        txtTrangThai = new utilities.palette.TextField();
        txtNgayThanhToan = new utilities.palette.TextField();
        txtTienKhachDua = new utilities.palette.TextField();
        txtTienThua = new utilities.palette.TextField();
        txtMaKhachHang = new utilities.palette.TextField();
        txtMaNhanVien = new utilities.palette.TextField();
        btnPre = new utilities.palette.UWPButton();
        btnNext = new utilities.palette.UWPButton();
        panelRound10 = new utilities.palette.PanelRound();
        rdoNgayTao = new utilities.palette.RadioButtonCustom();
        rdoNgayThanhToan = new utilities.palette.RadioButtonCustom();
        ngayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ngayKetThuc = new com.toedter.calendar.JDateChooser();

        diaLogCam.setUndecorated(true);
        diaLogCam.setSize(new java.awt.Dimension(1033, 643));

        panelRound6.setBackground(new java.awt.Color(228, 206, 224));
        panelRound6.setRoundBottomLeft(50);
        panelRound6.setRoundBottomRight(50);
        panelRound6.setRoundTopLeft(50);
        panelRound6.setRoundTopRight(50);

        panelRound16.setBackground(new java.awt.Color(67, 130, 187));
        panelRound16.setRoundBottomLeft(50);
        panelRound16.setRoundBottomRight(50);
        panelRound16.setRoundTopLeft(50);
        panelRound16.setRoundTopRight(50);

        btnCloseCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Log out.png"))); // NOI18N
        btnCloseCam.setToolTipText("Phiếu nhập chi tiết");
        btnCloseCam.setBorderColor(new java.awt.Color(221, 242, 244));
        btnCloseCam.setColor(new java.awt.Color(221, 242, 244));
        btnCloseCam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCloseCam.setRadius(50);
        btnCloseCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseCamActionPerformed(evt);
            }
        });

        btnThemVaoCTPX.setBackground(new java.awt.Color(221, 242, 244));
        btnThemVaoCTPX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Addd.png"))); // NOI18N
        btnThemVaoCTPX.setToolTipText("Thêm mới phiếu nhập");
        btnThemVaoCTPX.setBorderColor(new java.awt.Color(221, 242, 244));
        btnThemVaoCTPX.setColor(new java.awt.Color(221, 242, 244));
        btnThemVaoCTPX.setEnabled(false);
        btnThemVaoCTPX.setRadius(50);
        btnThemVaoCTPX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoCTPXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound16Layout = new javax.swing.GroupLayout(panelRound16);
        panelRound16.setLayout(panelRound16Layout);
        panelRound16Layout.setHorizontalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound16Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btnThemVaoCTPX, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnCloseCam, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        panelRound16Layout.setVerticalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemVaoCTPX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCloseCam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Thông tin sản phẩm");

        txtMaSPQuet.setEditable(false);
        txtMaSPQuet.setBackground(new java.awt.Color(228, 206, 224));
        txtMaSPQuet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSPQuet.setLabelText("Mã SP");
        txtMaSPQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPQuetActionPerformed(evt);
            }
        });

        txtTenSpQuet.setEditable(false);
        txtTenSpQuet.setBackground(new java.awt.Color(228, 206, 224));
        txtTenSpQuet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenSpQuet.setLabelText("Tên SP");

        txtMauQuet.setEditable(false);
        txtMauQuet.setBackground(new java.awt.Color(228, 206, 224));
        txtMauQuet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMauQuet.setLabelText("Màu sắc");

        txtSoLuongQuet.setEditable(false);
        txtSoLuongQuet.setBackground(new java.awt.Color(228, 206, 224));
        txtSoLuongQuet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuongQuet.setLabelText("Số lượng trong kho");
        txtSoLuongQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongQuetActionPerformed(evt);
            }
        });

        txtGiaBanQuet.setEditable(false);
        txtGiaBanQuet.setBackground(new java.awt.Color(228, 206, 224));
        txtGiaBanQuet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaBanQuet.setLabelText("Giá bán");
        txtGiaBanQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanQuetActionPerformed(evt);
            }
        });

        txtSoLuongMuaQuet.setEditable(false);
        txtSoLuongMuaQuet.setBackground(new java.awt.Color(228, 206, 224));
        txtSoLuongMuaQuet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuongMuaQuet.setLabelText("Số lượng mua");
        txtSoLuongMuaQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongMuaQuetActionPerformed(evt);
            }
        });

        txtSizeQuet.setEditable(false);
        txtSizeQuet.setBackground(new java.awt.Color(228, 206, 224));
        txtSizeQuet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSizeQuet.setLabelText("Size");
        txtSizeQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSizeQuetActionPerformed(evt);
            }
        });

        txtNgayNhapQuet.setEditable(false);
        txtNgayNhapQuet.setBackground(new java.awt.Color(228, 206, 224));
        txtNgayNhapQuet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayNhapQuet.setLabelText("Ngày nhập");
        txtNgayNhapQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayNhapQuetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                        .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelRound6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5))
                            .addComponent(txtGiaBanQuet, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                            .addComponent(txtSoLuongQuet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMauQuet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenSpQuet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaSPQuet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoLuongMuaQuet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSizeQuet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayNhapQuet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                        .addComponent(panelRound16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaSPQuet, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenSpQuet, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMauQuet, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSizeQuet, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNgayNhapQuet, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSoLuongQuet, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGiaBanQuet, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSoLuongMuaQuet, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(panelRound16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        panelRound2.setBackground(new java.awt.Color(221, 242, 244));
        panelRound2.setRoundBottomLeft(50);
        panelRound2.setRoundBottomRight(50);
        panelRound2.setRoundTopLeft(50);
        panelRound2.setRoundTopRight(50);

        javax.swing.GroupLayout mainCamLayout = new javax.swing.GroupLayout(mainCam);
        mainCam.setLayout(mainCamLayout);
        mainCamLayout.setHorizontalGroup(
            mainCamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        mainCamLayout.setVerticalGroup(
            mainCamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(mainCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );

        javax.swing.GroupLayout diaLogCamLayout = new javax.swing.GroupLayout(diaLogCam.getContentPane());
        diaLogCam.getContentPane().setLayout(diaLogCamLayout);
        diaLogCamLayout.setHorizontalGroup(
            diaLogCamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diaLogCamLayout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        diaLogCamLayout.setVerticalGroup(
            diaLogCamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diaLogCamLayout.createSequentialGroup()
                .addGroup(diaLogCamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelRound1.setBackground(new java.awt.Color(221, 242, 244));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);

        tblPhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã phiếu", "Ngày tạo", "Ngày thanh toán", "Trạng thái", "Nhân viên", "Khách hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuXuatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieuXuat);

        panelRound4.setBackground(new java.awt.Color(67, 130, 187));
        panelRound4.setRoundBottomLeft(50);
        panelRound4.setRoundBottomRight(50);
        panelRound4.setRoundTopLeft(50);
        panelRound4.setRoundTopRight(50);

        rdoNhanVien.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        rdoNhanVien.setText("Nhân viên");
        rdoNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNhanVienActionPerformed(evt);
            }
        });

        rdoKhachHang.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup1.add(rdoKhachHang);
        rdoKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        rdoKhachHang.setText("Khách Hàng");
        rdoKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKhachHangActionPerformed(evt);
            }
        });

        rdoMa.setBackground(new java.awt.Color(67, 130, 187));
        buttonGroup1.add(rdoMa);
        rdoMa.setForeground(new java.awt.Color(255, 255, 255));
        rdoMa.setText("Mã");
        rdoMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMaActionPerformed(evt);
            }
        });

        txtTimKiem.setLabelText("Search");

        buttonGradient1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search.png"))); // NOI18N
        buttonGradient1.setToolTipText("Tìm kiếm theo mã phiếu, nhân viên, khách hàng, ngày tạo,...");
        buttonGradient1.setColor1(new java.awt.Color(51, 255, 255));
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        panelRound5.setBackground(new java.awt.Color(67, 130, 187));
        panelRound5.setRoundBottomLeft(50);
        panelRound5.setRoundBottomRight(50);
        panelRound5.setRoundTopLeft(50);
        panelRound5.setRoundTopRight(50);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DANH SÁCH PHIẾU XUẤT");

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound8.setBackground(new java.awt.Color(67, 130, 187));
        panelRound8.setRoundBottomLeft(50);
        panelRound8.setRoundBottomRight(50);
        panelRound8.setRoundTopLeft(50);
        panelRound8.setRoundTopRight(50);

        cbbTrangThai.setBackground(new java.awt.Color(67, 130, 187));
        cbbTrangThai.setForeground(new java.awt.Color(255, 255, 255));
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
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound15.setBackground(new java.awt.Color(67, 130, 187));
        panelRound15.setRoundBottomLeft(50);
        panelRound15.setRoundBottomRight(50);
        panelRound15.setRoundTopLeft(50);
        panelRound15.setRoundTopRight(50);

        btnCtPhieuXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file.png"))); // NOI18N
        btnCtPhieuXuat.setToolTipText("Giỏ hàng của phiếu xuất");
        btnCtPhieuXuat.setBorderColor(new java.awt.Color(221, 242, 244));
        btnCtPhieuXuat.setColor(new java.awt.Color(221, 242, 244));
        btnCtPhieuXuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCtPhieuXuat.setRadius(50);
        btnCtPhieuXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCtPhieuXuatActionPerformed(evt);
            }
        });

        btnCreatPhieuXuat.setBackground(new java.awt.Color(221, 242, 244));
        btnCreatPhieuXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Addd.png"))); // NOI18N
        btnCreatPhieuXuat.setToolTipText("Thêm mới phiếu xuất");
        btnCreatPhieuXuat.setBorderColor(new java.awt.Color(221, 242, 244));
        btnCreatPhieuXuat.setColor(new java.awt.Color(221, 242, 244));
        btnCreatPhieuXuat.setRadius(50);
        btnCreatPhieuXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatPhieuXuatActionPerformed(evt);
            }
        });

        btnChiTietSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-to-basket.png"))); // NOI18N
        btnChiTietSP.setToolTipText("Thêm sản phẩm muốn bán");
        btnChiTietSP.setBorderColor(new java.awt.Color(221, 242, 244));
        btnChiTietSP.setColor(new java.awt.Color(221, 242, 244));
        btnChiTietSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChiTietSP.setRadius(50);
        btnChiTietSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietSPActionPerformed(evt);
            }
        });

        btnChiTietSP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Print.png"))); // NOI18N
        btnChiTietSP1.setToolTipText("In bill phiếu xuất");
        btnChiTietSP1.setBorderColor(new java.awt.Color(221, 242, 244));
        btnChiTietSP1.setColor(new java.awt.Color(221, 242, 244));
        btnChiTietSP1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChiTietSP1.setRadius(50);
        btnChiTietSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietSP1ActionPerformed(evt);
            }
        });

        btnQuetMa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Camera.png"))); // NOI18N
        btnQuetMa.setToolTipText("Quét sản phẩm");
        btnQuetMa.setBorderColor(new java.awt.Color(221, 242, 244));
        btnQuetMa.setColor(new java.awt.Color(221, 242, 244));
        btnQuetMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuetMa.setRadius(50);
        btnQuetMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetMaActionPerformed(evt);
            }
        });

        btnShow1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Show.png"))); // NOI18N
        btnShow1.setToolTipText("Hiện thị danh sách phiếu xuất");
        btnShow1.setBorderColor(new java.awt.Color(221, 242, 244));
        btnShow1.setColor(new java.awt.Color(221, 242, 244));
        btnShow1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnShow1.setRadius(50);
        btnShow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShow1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(btnCreatPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCtPhieuXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChiTietSP, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChiTietSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addComponent(btnQuetMa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound15Layout.createSequentialGroup()
                    .addContainerGap(421, Short.MAX_VALUE)
                    .addComponent(btnShow1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(111, 111, 111)))
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnQuetMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTietSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTietSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCreatPhieuXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCtPhieuXuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnShow1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        btnChiTietSP1.getAccessibleContext().setAccessibleDescription("Xuất phiếu xuất");

        txtIndex.setText("1/1");

        panelRound3.setBackground(new java.awt.Color(228, 206, 224));
        panelRound3.setRoundBottomLeft(50);
        panelRound3.setRoundBottomRight(50);
        panelRound3.setRoundTopLeft(50);
        panelRound3.setRoundTopRight(50);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thông tin phiếu");

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

        txtTienPhaitra.setEditable(false);
        txtTienPhaitra.setBackground(new java.awt.Color(228, 206, 224));
        txtTienPhaitra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienPhaitra.setLabelText("Tiền phải trả");

        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/credit-card.png"))); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
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

        txtTrangThai.setEditable(false);
        txtTrangThai.setBackground(new java.awt.Color(228, 206, 224));
        txtTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTrangThai.setLabelText("Trạng thái");
        txtTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiActionPerformed(evt);
            }
        });

        txtNgayThanhToan.setEditable(false);
        txtNgayThanhToan.setBackground(new java.awt.Color(228, 206, 224));
        txtNgayThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtNgayThanhToan.setLabelText("Ngày thanh toán");

        txtTienKhachDua.setBackground(new java.awt.Color(228, 206, 224));
        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienKhachDua.setLabelText("Tiền khách đưa");
        txtTienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienKhachDuaCaretUpdate(evt);
            }
        });
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        txtTienThua.setEditable(false);
        txtTienThua.setBackground(new java.awt.Color(228, 206, 224));
        txtTienThua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTienThua.setLabelText("Tiền thừa");
        txtTienThua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienThuaCaretUpdate(evt);
            }
        });

        txtMaKhachHang.setEditable(false);
        txtMaKhachHang.setBackground(new java.awt.Color(228, 206, 224));
        txtMaKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaKhachHang.setLabelText("Khách Hàng");

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setBackground(new java.awt.Color(228, 206, 224));
        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaNhanVien.setLabelText("Nhân Viên");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTienThua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTrangThai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienPhaitra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(24, Short.MAX_VALUE))))
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienPhaitra, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/skip-previous-circle-solid-24.png"))); // NOI18N
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/skip-next-circle-solid-24.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        panelRound10.setBackground(new java.awt.Color(67, 130, 187));
        panelRound10.setRoundBottomLeft(50);
        panelRound10.setRoundBottomRight(50);
        panelRound10.setRoundTopLeft(50);
        panelRound10.setRoundTopRight(50);

        rdoNgayTao.setBackground(new java.awt.Color(255, 153, 0));
        buttonGroup2.add(rdoNgayTao);
        rdoNgayTao.setForeground(new java.awt.Color(255, 255, 255));
        rdoNgayTao.setText("Ngày Tạo");
        rdoNgayTao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        rdoNgayThanhToan.setBackground(new java.awt.Color(255, 102, 0));
        buttonGroup2.add(rdoNgayThanhToan);
        rdoNgayThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        rdoNgayThanhToan.setText("Ngày Thanh Toán");
        rdoNgayThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdoNgayThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNgayThanhToanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("From:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("To:");

        javax.swing.GroupLayout panelRound10Layout = new javax.swing.GroupLayout(panelRound10);
        panelRound10.setLayout(panelRound10Layout);
        panelRound10Layout.setHorizontalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(rdoNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(rdoNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );
        panelRound10Layout.setVerticalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ngayKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ngayBatDau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelRound1Layout.createSequentialGroup()
                                    .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(panelRound10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(txtIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRound10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, 681, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoMaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_rdoMaActionPerformed
        //searchRadio();
    }//GEN-LAST:event_rdoMaActionPerformed

    private void rdoKhachHangActionPerformed(ActionEvent evt) {//GEN-FIRST:event_rdoKhachHangActionPerformed
        //searchRadio();
    }//GEN-LAST:event_rdoKhachHangActionPerformed

    private void rdoNhanVienActionPerformed(ActionEvent evt) {//GEN-FIRST:event_rdoNhanVienActionPerformed
//        searchRadio();
    }//GEN-LAST:event_rdoNhanVienActionPerformed

    private void cbbTrangThaiActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
//        searchRadio();
    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void btnCreatPhieuXuatActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnCreatPhieuXuatActionPerformed
        chonKhView = new Tai_ChonKhachHangView();
        chonKhView.setVisible(true);
    }//GEN-LAST:event_btnCreatPhieuXuatActionPerformed

    private void btnQuetMaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnQuetMaActionPerformed
        int row = this.tblPhieuXuat.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn phiếu xuất");
            return;
        }
        if (listPhieuXuat.get(row).getTrangThai() != TrangThaiPhieuConstant.CHO_THANH_TOAN) {
            JOptionPane.showMessageDialog(this, "Phiếu không thể mở máy quét khi ở trạng thái khác đã thanh toán");
            return;
        }

        this.txtMaSPQuet.setText("");
        this.txtTenSpQuet.setText("");
        this.txtMauQuet.setText("");
        this.txtSizeQuet.setText("");
        this.txtNgayNhapQuet.setText("");
        this.txtSoLuongQuet.setText("");
        this.txtGiaBanQuet.setText("");
        this.txtSoLuongMuaQuet.setText("");
        String input = JOptionPane.showInputDialog(this, "Mời bạn chọn cổng (Ví dụ: 0, 1, 2, ... => Cổng mạc định là 0)", "Lựa chọn !!!", JOptionPane.QUESTION_MESSAGE);
        int cong = 0;
        try {
            cong = Integer.parseInt(input);
            initWebCame(cong);
            map = serviceCam.getMapChiTietSanPham();
            this.diaLogCam.setVisible(true);
            captureThread();
            capture.start();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnQuetMaActionPerformed

    @Synchronized
    public void captureThread() {
        capture = new Thread() {
            @Override
            public synchronized void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        Result result = null;
                        BufferedImage image = null;

                        if (webcam1.isOpen()) {
                            if ((image = webcam1.getImage()) == null) {
                                continue;
                            }
                        }

                        image = webcam1.getImage();
                        File fileImg = new File("cde.png");
                        ImageIO.write(image, "png", fileImg);

                        LuminanceSource source = new BufferedImageLuminanceSource(image);
                        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                        result = new MultiFormatReader().decode(bitmap);
                        System.out.println(result.getText());
                        if (map.containsKey(UUID.fromString(result.getText()))) {
                            sanPhamQuet = map.get(UUID.fromString(result.getText()));
                            txtMaSPQuet.setText(sanPhamQuet.getSanPham().getMa());
                            txtTenSpQuet.setText(sanPhamQuet.getSanPham().getTen());
                            txtMauQuet.setText(Converter.trangThaiMauSac(sanPhamQuet.getMau()));
                            txtSizeQuet.setText(String.valueOf(sanPhamQuet.getSize()));
                            txtNgayNhapQuet.setText(simpleDateFormat.format(sanPhamQuet.getNgayTao()));
                            txtSoLuongQuet.setText(String.valueOf(sanPhamQuet.getSoLuongTon()));
                            txtGiaBanQuet.setText(formatter.format(sanPhamQuet.getGiaBan()));
                            if (sanPhamQuet.getTrangThai() == TrangThaiSanPhamConstanst.DA_MO_BAN) {
                                btnThemVaoCTPX.setEnabled(true);
                                txtSoLuongMuaQuet.setEditable(true);
                            } else {
                                btnThemVaoCTPX.setEnabled(false);
                                txtSoLuongMuaQuet.setEditable(false);
                            }
                        }
                        fileImg.delete();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (NotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
    }

    public void initWebCame(int cong) {
        try {
            webcam1 = Webcam.getWebcams().get(cong); // Camera 1
            webcam1.setViewSize(WebcamResolution.VGA.getSize()); // set size
            jpanl = new WebcamPanel(webcam1);
            webcam1.open();

            mainCam.add(jpanl);
            mainCam.setLayout(new FlowLayout());
            jpanl.setVisible(true);

        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Cam không có kết nối");
            e.printStackTrace();
            diaLogCam.setVisible(false);
            mainCam.removeAll();
            webcam1.close();
        }
    }

    private void btnCtPhieuXuatActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnCtPhieuXuatActionPerformed
        if (chon() == null) {
            return;
        }
        ctpxView = new Tai_LuongPhieuXuat_CTPhieuXuatView();
        ctpxView.setPhieuXuat(chon());
        ctpxView.setVisible(true);
    }//GEN-LAST:event_btnCtPhieuXuatActionPerformed

    private void txtTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangThaiActionPerformed

    private void btnChiTietSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietSPActionPerformed
        if (listPhieuXuat.get(tblPhieuXuat.getSelectedRow()).getTrangThai().equals(TrangThaiPhieuConstant.DA_THANH_TOAN)) {
            MsgBox.alert(this, "Phiếu xuất đã ở trạng thái đã thanh toán nên không thể mua hàng !");
            return;
        }
        if (chon() == null) {
            return;
        }
        ctspView = new Tai_LuongPhieuXuat_CTSanPhamView();
        ctspView.setPhieuXuat(chon());
        ctspView.setVisible(true);
    }//GEN-LAST:event_btnChiTietSPActionPerformed

    private void tblPhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuXuatMouseClicked
        int row = this.tblPhieuXuat.getSelectedRow();
        if (row == -1) {
            return;
        }
        PhieuXuatCustom px = listPhieuXuat.get(row);

        String maPhieu = this.tblPhieuXuat.getValueAt(row, 1).toString();
        String ngayTao = this.tblPhieuXuat.getValueAt(row, 2).toString();
        String ngayThanhToan = this.tblPhieuXuat.getValueAt(row, 3).toString();
        String trangThai = this.tblPhieuXuat.getValueAt(row, 4).toString();

        txtMaPhieu.setText(maPhieu);
        txtNgayTao.setText(ngayTao);
        txtNgayThanhToan.setText(ngayThanhToan);
        txtTrangThai.setText(trangThai);
        txtMaKhachHang.setText(px.getKhachHang().getTen());
        txtMaNhanVien.setText(px.getNhanVien().getTen());
        txtGhiChu.setText(listPhieuXuat.get(row).getGhiChu());
        double tien = 0;
        List<Luong_ChiTietPhieuXuatCustom> listCTPX = luongPxService.getListCTPhieuXuatByID(listPhieuXuat.get(row).getId());
        for (Luong_ChiTietPhieuXuatCustom ctpx : listCTPX) {
            tien += ctpx.getIdChiTietSp().getGiaBan().multiply(new BigDecimal(ctpx.getSoLuong())).doubleValue();
        }
        int tongTien = (int) tien;
        txtTienPhaitra.setText(formatter.format(tongTien) + "");
        if (px.getTrangThai() == TrangThaiPhieuConstant.CHO_THANH_TOAN && tongTien != 0) {
            btnThanhToan.setEnabled(true);
            txtTienKhachDua.setEditable(true);
        } else {
            btnThanhToan.setEnabled(false);
            txtTienKhachDua.setEditable(false);
            txtTienKhachDua.setText("");
            txtTienThua.setText("");
        }
        if (px.getTrangThai() == TrangThaiPhieuConstant.DA_THANH_TOAN) {
            txtTienKhachDua.setText("");
            txtTienThua.setText("");

        }
        if (txtTienKhachDua.getText().equals("")) {
            txtTienThua.setText("");
        }

    }//GEN-LAST:event_tblPhieuXuatMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        int row = this.tblPhieuXuat.getSelectedRow();
        if (row == -1) {
            MsgBox.alert(this, "Bạn phải chọn 1 phiếu xuất");
            return;
        }
        if (listPhieuXuat.get(row).getTrangThai() == TrangThaiPhieuConstant.DA_THANH_TOAN) {
            MsgBox.alert(this, "Phiếu xuất này đã thanh toán. Vui lòng mời bạn thanh toán phiếu xuất khác");
            return;
        }
        for (Luong_ChiTietPhieuXuatCustom ctpx : luongPxService.getListCTPhieuXuatByID(chon().getId())) {
            if (ctpx.getIdChiTietSp().getTrangThai().equals(TrangThaiSanPhamConstanst.CHO_XAC_NHAN)) {
                MsgBox.alert(this, "Sản phẩm trong giỏ hàng hiện chưa thể bán nên phiếu xuất này chưa thể thanh toán được!");
                return;
            }
        }
        String tongTien = txtTienPhaitra.getText();
        String tongTienNew = tongTien.replace(",", "").replace("VNĐ", "").replace(".", "").replace(" ", "");
        String tienKhachDua = txtTienKhachDua.getText();
        if (tienKhachDua.trim().length() == 0) {
            MsgBox.alert(this, "Bạn phải nhập tiền trước khi bấm nút thanh toán");
            return;
        }
        double tienKhach = 0;
        try {
            tienKhach = Double.parseDouble(tienKhachDua);
            if (tienKhach <= 0) {
                MsgBox.alert(this, "Phải nhập tiền là kiểu số nguyên dương");
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            MsgBox.alert(this, "Phải nhập tiền là kiểu số");
            return;
        }
        if (tienKhach < Double.valueOf(tongTienNew)) {
            MsgBox.alert(this, "Phải nhập tiền lớn hơn tiền phải trả");
            return;
        }

        PhieuXuatCustom pxcs = listPhieuXuat.get(row);
        pxcs.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        pxcs.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        pxcs.setGhiChu(txtGhiChu.getText());
        phieuXuatService.updatePhieuXuat(pxcs);
        MsgBox.alert(this, "Bạn đã thanh toán thành công");
        listPhieuXuat.set(row, pxcs);
        loadTablePhieuXuat(phieuXuatService.phanTrang(listPhieuXuat, offset, limit));
        clearForm();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTienThuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienThuaCaretUpdate

    }//GEN-LAST:event_txtTienThuaCaretUpdate

    private void txtTienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienKhachDuaCaretUpdate
        
    }//GEN-LAST:event_txtTienKhachDuaCaretUpdate

    private void btnChiTietSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietSP1ActionPerformed
        int row = this.tblPhieuXuat.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một dòng");
            return;
        }
        PhieuXuatCustom ctsp = listPhieuXuat.get(row);
        String fileName = ctsp.getKhachHang().getTen() + new Date(DateTimeUtil.convertDateToTimeStampSecond()).toString() + ".pdf";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            if (esps.exportPhieuXuat(fileChooser.getSelectedFile().getPath() + "\\" + fileName.replaceAll(":", "_"), ctsp.getId())) {
                JOptionPane.showMessageDialog(this, "Xuất file thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xuất file thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Folder Selected: ");
        }
    }//GEN-LAST:event_btnChiTietSP1ActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        searchRadio();
        loadTablePhieuXuat(listPhieuXuat);
        if (rdoNgayTao.isSelected() || rdoNgayThanhToan.isSelected()) {
            TimKiemTheoNgay();
        }
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        index = p.prevIndex(offset, limit, index);
        offset = p.prev(offset, limit);
        loadIndex();
        loadTablePhieuXuat(phieuXuatService.phanTrang(listPhieuXuat, offset, limit));
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        index = p.nextIndex(offset, limit, sizes, index);
        offset = p.next(offset, limit, sizes);
        loadIndex();
        loadTablePhieuXuat(phieuXuatService.phanTrang(listPhieuXuat, offset, limit));
    }//GEN-LAST:event_btnNextActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        String tongTien = txtTienPhaitra.getText();
        String tongTienNew = tongTien.replace(",", "").replace("VNĐ", "").replace(".", "").replaceAll(" ", "");
        String tienKhachDua = txtTienKhachDua.getText().replace(",", "");
        double tienKhach = 0;
        if (tienKhachDua.trim().length() == 0) {
            txtTienThua.setText("");
        }
        if (listPhieuXuat.get(tblPhieuXuat.getSelectedRow()).getTrangThai().equals(TrangThaiPhieuConstant.DA_THANH_TOAN)) {
            txtTienKhachDua.setText("");
            txtTienThua.setText("");
        }
        if (tongTien.trim().length() == 0) {
            txtTienKhachDua.setText("");
            txtTienThua.setText("");
        }
        try {
            tienKhach = Double.parseDouble(tienKhachDua);
            if (tienKhach <= 0) {
                MsgBox.alert(this, "Phải nhập tiền là kiểu số nguyên dương");
                return;
            }
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }

        try {
            double tienThua = tienKhach - Double.valueOf(tongTienNew);
            txtTienThua.setText(formatter.format(tienThua));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void btnShow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShow1ActionPerformed
        listPhieuXuat = phieuXuatService.getList();
        loadTablePhieuXuat(phieuXuatService.phanTrang(listPhieuXuat, offset, limit));
        clearForm();
    }//GEN-LAST:event_btnShow1ActionPerformed

    private void btnCloseCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseCamActionPerformed
        diaLogCam.setVisible(false);
        mainCam.removeAll();
        webcam1.close();
    }//GEN-LAST:event_btnCloseCamActionPerformed

    private void btnThemVaoCTPXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoCTPXActionPerformed
        String slm = this.txtSoLuongMuaQuet.getText();
        if (slm.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn phải nhập số lượng mua");
            return;
        }
        int soLuongMua = 0;
        try {
            soLuongMua = Integer.parseInt(slm);
            if (soLuongMua <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn phải nhập số nguyên dương");
                return;
            }
            if (soLuongMua > sanPhamQuet.getSoLuongTon()) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn không đủ");
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Bạn phải nhập kiểu số");
            return;
        }
        
        if(sanPhamQuet.getTrangThai() != TrangThaiSanPhamConstanst.DA_MO_BAN) {
            JOptionPane.showMessageDialog(this, "Sản phẩm không thể bán");
            return;
        }
        
        if (serviceCam.addChiTietPhieuXuat(this.listPhieuXuat.get(this.tblPhieuXuat.getSelectedRow()).getId(), sanPhamQuet.getId(), soLuongMua)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            diaLogCam.setVisible(false);
            mainCam.removeAll();
            webcam1.close();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btnThemVaoCTPXActionPerformed

    private void txtMaSPQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPQuetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPQuetActionPerformed

    private void txtSoLuongQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongQuetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongQuetActionPerformed

    private void txtGiaBanQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanQuetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanQuetActionPerformed

    private void txtSoLuongMuaQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongMuaQuetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongMuaQuetActionPerformed

    private void txtSizeQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSizeQuetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSizeQuetActionPerformed

    private void txtNgayNhapQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayNhapQuetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayNhapQuetActionPerformed

    private void rdoNgayThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNgayThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNgayThanhToanActionPerformed

    public void TimKiemTheoNgay() {
        if (ngayBatDau.getDate() == null) {
            MsgBox.alert(this, "Bạn phải chọn ngày bắt đầu");
            return;
        }
        if (ngayKetThuc.getDate() == null) {
            MsgBox.alert(this, "Bạn phải chọn ngày Kết thúc");
            return;
        }
        if (!rdoNgayTao.isSelected() && !rdoNgayThanhToan.isSelected()) {
            MsgBox.alert(this, "Bạn phải lựa chọn ngày");
            return;
        }

        if (rdoNgayTao.isSelected()) {
            listPhieuXuat = phieuXuatService.getListByNgayTao(ngayBatDau.getDate().getTime(), ngayKetThuc.getDate().getTime());
            loadTablePhieuXuat(listPhieuXuat);
        } else {
            listPhieuXuat = phieuXuatService.getListByNgayThanhToan(ngayBatDau.getDate().getTime(), ngayKetThuc.getDate().getTime());
            loadTablePhieuXuat(listPhieuXuat);
        }
    }

    public List<PhieuXuatCustom> getListByTT(int rdo) {
        String timKiem = this.txtTimKiem.getText();
        listPhieuXuat = phieuXuatService.findAllByKhAndNV(timKiem, phieuXuatService.loc(cbbTrangThai.getSelectedIndex()), rdo);
        return listPhieuXuat;
    }

    public void searchRadio() {
        if (rdoNhanVien.isSelected()) {
            loadTablePhieuXuat(getListByTT(0));
        } else if (rdoKhachHang.isSelected()) {
            loadTablePhieuXuat(getListByTT(1));
        } else if (rdoMa.isSelected()) {
            loadTablePhieuXuat(getListByTT(2));
        } else {
            loadTablePhieuXuat(getListByTT(3));
        }
    }

    public PhieuXuat chon() {
        int row = this.tblPhieuXuat.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn 1 phiếu xuất");
            return null;
        }
        PhieuXuat px = new PhieuXuat();
        px.setId(listPhieuXuat.get(row).getId());
        px.setNgayTao(listPhieuXuat.get(row).getNgayTao());
        px.setNgayThanhToan(listPhieuXuat.get(row).getNgayThanhToan());
        px.setNgayTao(listPhieuXuat.get(row).getNgayTao());
        px.setGhiChu(listPhieuXuat.get(row).getGhiChu());
        px.setKhachHang(listPhieuXuat.get(row).getKhachHang());
        px.setNhanVien(listPhieuXuat.get(row).getNhanVien());
        px.setTrangThai(listPhieuXuat.get(row).getTrangThai());
        return px;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.palette.MyButton btnChiTietSP;
    private utilities.palette.MyButton btnChiTietSP1;
    private utilities.palette.MyButton btnCloseCam;
    private utilities.palette.MyButton btnCreatPhieuXuat;
    private utilities.palette.MyButton btnCtPhieuXuat;
    private utilities.palette.UWPButton btnNext;
    private utilities.palette.UWPButton btnPre;
    private utilities.palette.MyButton btnQuetMa;
    private utilities.palette.MyButton btnShow1;
    private utilities.palette.MyButton btnThanhToan;
    private utilities.palette.MyButton btnThemVaoCTPX;
    private utilities.palette.ButtonGradient buttonGradient1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private utilities.palette.Combobox cbbTrangThai;
    private javax.swing.JFrame diaLogCam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainCam;
    private com.toedter.calendar.JDateChooser ngayBatDau;
    private com.toedter.calendar.JDateChooser ngayKetThuc;
    private utilities.palette.PanelRound panelRound1;
    private utilities.palette.PanelRound panelRound10;
    private utilities.palette.PanelRound panelRound15;
    private utilities.palette.PanelRound panelRound16;
    private utilities.palette.PanelRound panelRound2;
    private utilities.palette.PanelRound panelRound3;
    private utilities.palette.PanelRound panelRound4;
    private utilities.palette.PanelRound panelRound5;
    private utilities.palette.PanelRound panelRound6;
    private utilities.palette.PanelRound panelRound8;
    private utilities.palette.RadioButtonCustom rdoKhachHang;
    private utilities.palette.RadioButtonCustom rdoMa;
    private utilities.palette.RadioButtonCustom rdoNgayTao;
    private utilities.palette.RadioButtonCustom rdoNgayThanhToan;
    private utilities.palette.RadioButtonCustom rdoNhanVien;
    private utilities.palette.TableDark_1 tblPhieuXuat;
    private utilities.palette.TextAreaScroll textAreaScroll1;
    private utilities.palette.TextAreaCustom txtGhiChu;
    private utilities.palette.TextField txtGiaBanQuet;
    private javax.swing.JLabel txtIndex;
    private utilities.palette.TextField txtMaKhachHang;
    private utilities.palette.TextField txtMaNhanVien;
    private utilities.palette.TextField txtMaPhieu;
    private utilities.palette.TextField txtMaSPQuet;
    private utilities.palette.TextField txtMauQuet;
    private utilities.palette.TextField txtNgayNhapQuet;
    private utilities.palette.TextField txtNgayTao;
    private utilities.palette.TextField txtNgayThanhToan;
    private utilities.palette.TextField txtSizeQuet;
    private utilities.palette.TextField txtSoLuongMuaQuet;
    private utilities.palette.TextField txtSoLuongQuet;
    private utilities.palette.TextField txtTenSpQuet;
    private utilities.palette.TextField txtTienKhachDua;
    private utilities.palette.TextField txtTienPhaitra;
    private utilities.palette.TextField txtTienThua;
    private utilities.palette.TextField txtTimKiem;
    private utilities.palette.TextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
