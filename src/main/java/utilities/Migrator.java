package utilities;

import domainModels.ChiTietPhieuNhap;
import domainModels.ChiTietPhieuXuat;
import domainModels.ChiTietSanPham;
import domainModels.ChucVu;
import domainModels.DonVi;
import domainModels.KhachHang;
import domainModels.NhaCungCap;
import domainModels.NhanVien;
import domainModels.PhieuNhap;
import domainModels.PhieuXuat;
import domainModels.SanPham;
import infrastructures.constant.DanhGiaConstant;
import infrastructures.constant.GioiTinhConstant;
import infrastructures.constant.KhachHangConstant;
import infrastructures.constant.MauConstant;
import infrastructures.constant.TrangThaiNhanVienConstant;
import infrastructures.constant.TrangThaiPhieuConstant;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Phong
 */
public class Migrator {

    //Tạo DB trong SQL SERVER = SOFT2041_PTPM
    //Sau đó tiến hành chạy đển zen bảng
    public static void main(String[] args) {
        // Tạo đối tượng ServiceRegistry từ hibernate.cfg.xml
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("META-INF/hibernate.cfg.xml")
                .applySetting("hibernate.hbm2ddl.auto", "create")
                .build();

        // Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
        Metadata metadata = new MetadataSources(serviceRegistry)
                .getMetadataBuilder().build();

        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        // insert database
        // Chức vụ
        ChucVu chucVu = new ChucVu();
        chucVu.setMa("CV0001");
        chucVu.setTen("Trưởng Phòng");
        session.save(chucVu);

        ChucVu chucVu1 = new ChucVu();
        chucVu1.setMa("CV0002");
        chucVu1.setTen("Nhân viên quản lý");
        session.save(chucVu1);

        ChucVu chucVu2 = new ChucVu();
        chucVu2.setMa("CV0003");
        chucVu2.setTen("Nhân viên");
        session.save(chucVu2);

        DonVi dv = new DonVi();
        dv.setDonViGoc("Đôi");
        dv.setDonViQuyDoi("Thùng");
        dv.setSoLuong(50);
        session.save(dv);

        DonVi dv1 = new DonVi();
        dv1.setDonViGoc("Đôi");
        dv1.setDonViQuyDoi("Túi");
        dv1.setSoLuong(10);
        session.save(dv1);

        DonVi dv2 = new DonVi();
        dv2.setDonViGoc("Đôi");
        dv2.setDonViQuyDoi("Lô");
        dv2.setSoLuong(500);
        session.save(dv2);

        DonVi dv3 = new DonVi();
        dv3.setDonViGoc("Đôi");
        dv3.setDonViQuyDoi("Đôi");
        dv3.setSoLuong(1);
        session.save(dv3);

        NhanVien a = new NhanVien();
        a.setDiaChi("Hà Nội");
        a.setEmail("a");
        a.setGioiTinh(GioiTinhConstant.NAM);
        a.setHinhAnh(null);
        a.setIdChucVu(chucVu);
        a.setMa(MaTuSinh.gen("NV"));
        a.setMatKhau("1");
        a.setNgaySinh(DateTimeUtil.convertDateToTimeStampSecond());
        a.setSdt("0328843156");
        a.setTen("Nguyễn Quốc Huy");
        a.setTrangThai(TrangThaiNhanVienConstant.DANG_HOAT_DONG);
        session.save(a);

        NhanVien b = new NhanVien();
        b.setDiaChi("Hà Nội");
        b.setEmail("b");
        b.setGioiTinh(GioiTinhConstant.NAM);
        b.setHinhAnh(null);
        b.setIdChucVu(chucVu1);
        b.setMa(MaTuSinh.gen("NV"));
        b.setMatKhau("1");
        b.setNgaySinh(DateTimeUtil.convertDateToTimeStampSecond());
        b.setSdt("0328843156");
        b.setTen("Nguyễn Quốc Huy");
        b.setTrangThai(TrangThaiNhanVienConstant.DANG_HOAT_DONG);
        session.save(b);

        NhanVien nhanVien = new NhanVien();
        nhanVien.setDiaChi("Hà Nội");
        nhanVien.setEmail("huynqph26782@fpt.edu.vn");
        nhanVien.setGioiTinh(GioiTinhConstant.NAM);
        nhanVien.setHinhAnh(null);
        nhanVien.setIdChucVu(chucVu);
        nhanVien.setMa(MaTuSinh.gen("NV"));
        nhanVien.setMatKhau("1");
        nhanVien.setNgaySinh(DateTimeUtil.convertDateToTimeStampSecond());
        nhanVien.setSdt("0328843156");
        nhanVien.setTen("Nguyễn Quốc Huy");
        nhanVien.setTrangThai(TrangThaiNhanVienConstant.DANG_HOAT_DONG);
        session.save(nhanVien);

        NhanVien nhanVien1 = new NhanVien();
        nhanVien1.setDiaChi("Hà Nội");
        nhanVien1.setEmail("pengoanchamhoc@gmail.com");
        nhanVien1.setGioiTinh(GioiTinhConstant.NAM);
        nhanVien1.setHinhAnh(null);
        nhanVien1.setIdChucVu(chucVu1);
        nhanVien1.setMa(MaTuSinh.gen("NV"));
        nhanVien1.setMatKhau("1");
        nhanVien1.setNgaySinh(DateTimeUtil.convertDateToTimeStampSecond());
        nhanVien1.setSdt("0328843156");
        nhanVien1.setTen("Nguyễn Quốc Huy");
        nhanVien1.setTrangThai(TrangThaiNhanVienConstant.DANG_HOAT_DONG);
        session.save(nhanVien1);

        NhanVien nhanVien2 = new NhanVien();
        nhanVien2.setDiaChi("Hà Nội");
        nhanVien2.setEmail("a");
        nhanVien2.setGioiTinh(GioiTinhConstant.NAM);
        nhanVien2.setHinhAnh(null);
        nhanVien2.setIdChucVu(chucVu);
        nhanVien2.setMa(MaTuSinh.gen("NV"));
        nhanVien2.setMatKhau("a");
        nhanVien2.setNgaySinh(DateTimeUtil.convertDateToTimeStampSecond());
        nhanVien2.setSdt("0328843156");
        nhanVien2.setTen("Nguyễn Quốc Huy");
        nhanVien2.setTrangThai(TrangThaiNhanVienConstant.DANG_HOAT_DONG);
        session.save(nhanVien2);

        KhachHang kh = new KhachHang();
        kh.setDanhGia(DanhGiaConstant.TAM_ON);
        kh.setDiaChi("Hà Nội");
        kh.setEmail("taintph26788@fpt.edu.vn");
        kh.setGioiTinh(GioiTinhConstant.NAM);
        kh.setHinhAnh(null);
        kh.setMa(MaTuSinh.gen("KH"));
        kh.setMatKhau("1");
        kh.setNgaySinh(DateTimeUtil.convertDateToTimeStampSecond());
        kh.setSdt("0327777777");
        kh.setTen("Nguyễn Tiến Tài");
        kh.setTrangThai(KhachHangConstant.DANG_LAM_VIEC);
        session.save(kh);

        KhachHang kh1 = new KhachHang();
        kh1.setDanhGia(DanhGiaConstant.TAM_ON);
        kh1.setDiaChi("Hà Nội");
        kh1.setEmail("taintph26788@fpt.edu.vn");
        kh1.setGioiTinh(GioiTinhConstant.NAM);
        kh1.setHinhAnh(null);
        kh1.setMa(MaTuSinh.gen("KH"));
        kh1.setMatKhau("1");
        kh1.setNgaySinh(DateTimeUtil.convertDateToTimeStampSecond());
        kh1.setSdt("0327777777");
        kh1.setTen("Nguyễn Văn Thắng");
        kh1.setTrangThai(KhachHangConstant.DANG_LAM_VIEC);
        session.save(kh1);

        KhachHang kh2 = new KhachHang();
        kh2.setDanhGia(DanhGiaConstant.TAM_ON);
        kh2.setDiaChi("Hà Nội");
        kh2.setEmail("taintph26788@fpt.edu.vn");
        kh2.setGioiTinh(GioiTinhConstant.NAM);
        kh2.setHinhAnh(null);
        kh2.setMa(MaTuSinh.gen("KH"));
        kh2.setMatKhau("1");
        kh2.setNgaySinh(DateTimeUtil.convertDateToTimeStampSecond());
        kh2.setSdt("0327777777");
        kh2.setTen("Phạm Hải Quân");
        kh2.setTrangThai(KhachHangConstant.DANG_LAM_VIEC);
        session.save(kh2);

        NhaCungCap ncc = new NhaCungCap();
        ncc.setDanhGia(DanhGiaConstant.TAM_ON);
        ncc.setDiaChi("Hà Nội");
        ncc.setEmail("huynqph26782@fpt.edu.vn");
        ncc.setMa(MaTuSinh.gen("NCC"));
        ncc.setSdt("0321111111");
        ncc.setTen("Phạm Xuân Hải");
        ncc.setTrangThai(KhachHangConstant.SAP_BO);
        session.save(ncc);

        NhaCungCap ncc1 = new NhaCungCap();
        ncc1.setDanhGia(DanhGiaConstant.TAM_ON);
        ncc1.setDiaChi("Hà Nội");
        ncc1.setEmail("huynqph26782@fpt.edu.vn");
        ncc1.setMa(MaTuSinh.gen("NCC"));
        ncc1.setSdt("0321111111");
        ncc1.setTen("Đinh Anh Tuấn");
        ncc1.setTrangThai(KhachHangConstant.SAP_BO);
        session.save(ncc1);

        NhaCungCap ncc2 = new NhaCungCap();
        ncc2.setDanhGia(DanhGiaConstant.TAM_ON);
        ncc2.setDiaChi("Hà Nội");
        ncc2.setEmail("huynqph26782@fpt.edu.vn");
        ncc2.setMa(MaTuSinh.gen("NCC"));
        ncc2.setSdt("0321111111");
        ncc2.setTen("Nguyễn Thị Khánh Linh");
        ncc2.setTrangThai(KhachHangConstant.SAP_BO);
        session.save(ncc2);

        PhieuNhap pn = new PhieuNhap();
        pn.setGhiChu("Giày đẹp");
        pn.setMaPhieu(MaTuSinh.gen("PN"));
        pn.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        pn.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        pn.setNhaCungCap(ncc2);
        pn.setNhanVien(nhanVien1);
        pn.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(pn);

        PhieuNhap pn1 = new PhieuNhap();
        pn1.setGhiChu("Giày đẹp");
        pn1.setMaPhieu(MaTuSinh.gen("PN"));
        pn1.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        pn1.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        pn1.setNhaCungCap(ncc2);
        pn1.setNhanVien(nhanVien1);
        pn1.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(pn1);

        PhieuNhap pn2 = new PhieuNhap();
        pn2.setGhiChu("Giày đẹp");
        pn2.setMaPhieu(MaTuSinh.gen("PN"));
        pn2.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        pn2.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        pn2.setNhaCungCap(ncc);
        pn2.setNhanVien(nhanVien1);
        pn2.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(pn2);

        PhieuNhap pn3 = new PhieuNhap();
        pn3.setGhiChu("Giày đẹp");
        pn3.setMaPhieu(MaTuSinh.gen("PN"));
        pn3.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        pn3.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        pn3.setNhaCungCap(ncc1);
        pn3.setNhanVien(nhanVien1);
        pn3.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(pn3);

        PhieuNhap pn4 = new PhieuNhap();
        pn4.setGhiChu("Giày đẹp");
        pn4.setMaPhieu(MaTuSinh.gen("PN"));
        pn4.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        pn4.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        pn4.setNhaCungCap(ncc);
        pn4.setNhanVien(nhanVien1);
        pn4.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(pn4);

        PhieuXuat px = new PhieuXuat();
        px.setGhiChu("Giày đẹp");
        px.setKhachHang(kh2);
        px.setMaPhieu(MaTuSinh.gen("PX"));
        px.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        px.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        px.setNhanVien(nhanVien1);
        px.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(px);

        PhieuXuat px1 = new PhieuXuat();
        px1.setGhiChu("Giày đẹp");
        px1.setKhachHang(kh1);
        px1.setMaPhieu(MaTuSinh.gen("PX"));
        px1.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        px1.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        px1.setNhanVien(nhanVien1);
        px1.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(px1);

        PhieuXuat px2 = new PhieuXuat();
        px2.setGhiChu("Giày đẹp");
        px2.setKhachHang(kh);
        px2.setMaPhieu(MaTuSinh.gen("PX"));
        px2.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        px2.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        px2.setNhanVien(nhanVien1);
        px2.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(px2);

        PhieuXuat px3 = new PhieuXuat();
        px3.setGhiChu("Giày đẹp");
        px3.setKhachHang(kh);
        px3.setMaPhieu(MaTuSinh.gen("PX"));
        px3.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        px3.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        px3.setNhanVien(nhanVien1);
        px3.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(px3);

        PhieuXuat px4 = new PhieuXuat();
        px4.setGhiChu("Giày đẹp");
        px4.setKhachHang(kh);
        px4.setMaPhieu(MaTuSinh.gen("PX"));
        px4.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        px4.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        px4.setNhanVien(nhanVien1);
        px4.setTrangThai(TrangThaiPhieuConstant.DA_THANH_TOAN);
        session.save(px4);
        
        PhieuXuat px5 = new PhieuXuat();
        px5.setGhiChu("Giày đẹp");
        px5.setKhachHang(kh);
        px5.setMaPhieu(MaTuSinh.gen("PX"));
        px5.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond() - 172804492);
        px5.setNhanVien(nhanVien1);
        px5.setTrangThai(TrangThaiPhieuConstant.CHO_THANH_TOAN);
        session.save(px5);

        PhieuXuat px6 = new PhieuXuat();
        px6.setGhiChu("Giày đẹp");
        px6.setKhachHang(kh);
        px6.setMaPhieu(MaTuSinh.gen("PX"));
        px6.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        px6.setNhanVien(nhanVien1);
        px6.setTrangThai(TrangThaiPhieuConstant.CHO_THANH_TOAN);
        session.save(px6);

        PhieuXuat px7 = new PhieuXuat();
        px7.setGhiChu("Giày đẹp");
        px7.setKhachHang(kh);
        px7.setMaPhieu(MaTuSinh.gen("PX"));
        px7.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        px7.setNhanVien(nhanVien1);
        px7.setTrangThai(TrangThaiPhieuConstant.CHO_THANH_TOAN);
        session.save(px7);

        SanPham sanPham = new SanPham();
        sanPham.setMa(MaTuSinh.gen("SP"));
        sanPham.setTen("Giày Nike");
        session.save(sanPham);

        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setDonVi(dv3);
        ctsp.setGiaBan(new BigDecimal(10000));
        ctsp.setGiaNhap(new BigDecimal(1000));
        ctsp.setHinhAnh(null);
        ctsp.setMau(MauConstant.CAM);
        ctsp.setNamBaoHanh(1);
        ctsp.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp.setSanPham(sanPham);
        ctsp.setSize(38);
        ctsp.setSoLuongTon(700);
        ctsp.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp);
        
        ChiTietPhieuXuat bot = new ChiTietPhieuXuat();
        bot.setIdChiTietSp(ctsp);
        bot.setIdPhieuXuat(px7);
        bot.setSoLuong(100);
        session.save(bot);
        
        ChiTietPhieuXuat bot1 = new ChiTietPhieuXuat();
        bot1.setIdChiTietSp(ctsp);
        bot1.setIdPhieuXuat(px6);
        bot1.setSoLuong(100);
        session.save(bot1);
        
        ChiTietPhieuXuat bot2 = new ChiTietPhieuXuat();
        bot2.setIdChiTietSp(ctsp);
        bot2.setIdPhieuXuat(px5);
        bot2.setSoLuong(100);
        session.save(bot2);

        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
        ctpn.setIdChiTietSp(ctsp);
        ctpn.setIdPhieuNhap(pn);
        ctpn.setSoLuong(1000);
        ctpn.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn);

        ChiTietSanPham ctsp2 = new ChiTietSanPham();
        ctsp2.setDonVi(dv3);
        ctsp2.setGiaBan(new BigDecimal(1000000));
        ctsp2.setGiaNhap(new BigDecimal(1000));
        ctsp2.setHinhAnh(null);
        ctsp2.setMau(MauConstant.VANG);
        ctsp2.setNamBaoHanh(1);
        ctsp2.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp2.setSanPham(sanPham);
        ctsp2.setSize(40);
        ctsp2.setSoLuongTon(150);
        ctsp2.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp2);

        ChiTietPhieuXuat ctpx = new ChiTietPhieuXuat();
        ctpx.setIdChiTietSp(ctsp2);
        ctpx.setIdPhieuXuat(px2);
        ctpx.setSoLuong(50);
        session.save(ctpx);

        ChiTietPhieuNhap ctpn2 = new ChiTietPhieuNhap();
        ctpn2.setIdChiTietSp(ctsp2);
        ctpn2.setIdPhieuNhap(pn1);
        ctpn2.setSoLuong(200);
        ctpn2.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn2);

        ChiTietSanPham ctsp1 = new ChiTietSanPham();
        ctsp1.setDonVi(dv3);
        ctsp1.setGiaBan(new BigDecimal(10000));
        ctsp1.setGiaNhap(new BigDecimal(100));
        ctsp1.setHinhAnh(null);
        ctsp1.setMau(MauConstant.DEN);
        ctsp1.setNamBaoHanh(1);
        ctsp1.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp1.setSanPham(sanPham);
        ctsp1.setSize(38);
        ctsp1.setSoLuongTon(2);
        ctsp1.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp1);

        ChiTietPhieuNhap ctpn1 = new ChiTietPhieuNhap();
        ctpn1.setIdChiTietSp(ctsp1);
        ctpn1.setIdPhieuNhap(pn2);
        ctpn1.setSoLuong(1000);
        ctpn1.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn1);

        ChiTietSanPham ctsp3 = new ChiTietSanPham();
        ctsp3.setDonVi(dv3);
        ctsp3.setGiaBan(new BigDecimal(10000));
        ctsp3.setGiaNhap(new BigDecimal(1000));
        ctsp3.setHinhAnh(null);
        ctsp3.setMau(MauConstant.VANG);
        ctsp3.setNamBaoHanh(1);
        ctsp3.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp3.setSanPham(sanPham);
        ctsp3.setSize(40);
        ctsp3.setSoLuongTon(150);
        ctsp3.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp3);

        ChiTietPhieuXuat ctpx1 = new ChiTietPhieuXuat();
        ctpx1.setIdChiTietSp(ctsp3);
        ctpx1.setIdPhieuXuat(px1);
        ctpx1.setSoLuong(50);
        session.save(ctpx1);

        ChiTietPhieuNhap ctpn3 = new ChiTietPhieuNhap();
        ctpn3.setIdChiTietSp(ctsp3);
        ctpn3.setIdPhieuNhap(pn4);
        ctpn3.setSoLuong(200);
        ctpn3.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn3);

        ChiTietSanPham ctsp4 = new ChiTietSanPham();
        ctsp4.setDonVi(dv3);
        ctsp4.setGiaBan(new BigDecimal(10000));
        ctsp4.setGiaNhap(new BigDecimal(1000));
        ctsp4.setHinhAnh(null);
        ctsp4.setMau(MauConstant.VANG);
        ctsp4.setNamBaoHanh(1);
        ctsp4.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp4.setSanPham(sanPham);
        ctsp4.setSize(40);
        ctsp4.setSoLuongTon(150);
        ctsp4.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp4);

        ChiTietPhieuXuat ctpx2 = new ChiTietPhieuXuat();
        ctpx2.setIdChiTietSp(ctsp4);
        ctpx2.setIdPhieuXuat(px);
        ctpx2.setSoLuong(50);
        session.save(ctpx2);

        ChiTietPhieuNhap ctpn4 = new ChiTietPhieuNhap();
        ctpn4.setIdChiTietSp(ctsp4);
        ctpn4.setIdPhieuNhap(pn3);
        ctpn4.setSoLuong(200);
        ctpn4.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn4);

        SanPham sanPham1 = new SanPham();
        sanPham1.setMa(MaTuSinh.gen("SP"));
        sanPham1.setTen("Giày Gucci");
        session.save(sanPham1);

        ChiTietSanPham ctsp5 = new ChiTietSanPham();
        ctsp5.setDonVi(dv3);
        ctsp5.setGiaBan(new BigDecimal(10000));
        ctsp5.setGiaNhap(new BigDecimal(1000));
        ctsp5.setHinhAnh(null);
        ctsp5.setMau(MauConstant.CAM);
        ctsp5.setNamBaoHanh(1);
        ctsp5.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp5.setSanPham(sanPham1);
        ctsp5.setSize(38);
        ctsp5.setSoLuongTon(1000);
        ctsp5.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp5);

        ChiTietPhieuNhap ctpn5 = new ChiTietPhieuNhap();
        ctpn5.setIdChiTietSp(ctsp5);
        ctpn5.setIdPhieuNhap(pn);
        ctpn5.setSoLuong(1000);
        ctpn5.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn5);

        ChiTietSanPham ctsp6 = new ChiTietSanPham();
        ctsp6.setDonVi(dv3);
        ctsp6.setGiaBan(new BigDecimal(1000000));
        ctsp6.setGiaNhap(new BigDecimal(1000));
        ctsp6.setHinhAnh(null);
        ctsp6.setMau(MauConstant.TRANG);
        ctsp6.setNamBaoHanh(1);
        ctsp6.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp6.setSanPham(sanPham1);
        ctsp6.setSize(30);
        ctsp6.setSoLuongTon(150);
        ctsp6.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp6);

        ChiTietPhieuXuat ctpx3 = new ChiTietPhieuXuat();
        ctpx3.setIdChiTietSp(ctsp);
        ctpx3.setIdPhieuXuat(px4);
        ctpx3.setSoLuong(50);
        session.save(ctpx3);

        ChiTietPhieuNhap ctpn6 = new ChiTietPhieuNhap();
        ctpn6.setIdChiTietSp(ctsp6);
        ctpn6.setIdPhieuNhap(pn1);
        ctpn6.setSoLuong(200);
        ctpn6.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn6);

        ChiTietSanPham ctsp7 = new ChiTietSanPham();
        ctsp7.setDonVi(dv3);
        ctsp7.setGiaBan(new BigDecimal(10000));
        ctsp7.setGiaNhap(new BigDecimal(100));
        ctsp7.setHinhAnh(null);
        ctsp7.setMau(MauConstant.DEN);
        ctsp7.setNamBaoHanh(1);
        ctsp7.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp7.setSanPham(sanPham1);
        ctsp7.setSize(38);
        ctsp7.setSoLuongTon(2);
        ctsp7.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp7);

        ChiTietPhieuNhap ctpn7 = new ChiTietPhieuNhap();
        ctpn7.setIdChiTietSp(ctsp7);
        ctpn7.setIdPhieuNhap(pn2);
        ctpn7.setSoLuong(1000);
        ctpn7.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn7);

        ChiTietSanPham ctsp8 = new ChiTietSanPham();
        ctsp8.setDonVi(dv3);
        ctsp8.setGiaBan(new BigDecimal(10000));
        ctsp8.setGiaNhap(new BigDecimal(1000));
        ctsp8.setHinhAnh(null);
        ctsp8.setMau(MauConstant.VANG);
        ctsp8.setNamBaoHanh(1);
        ctsp8.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp8.setSanPham(sanPham1);
        ctsp8.setSize(40);
        ctsp8.setSoLuongTon(150);
        ctsp8.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp8);

        ChiTietPhieuXuat ctpx4 = new ChiTietPhieuXuat();
        ctpx4.setIdChiTietSp(ctsp8);
        ctpx4.setIdPhieuXuat(px);
        ctpx4.setSoLuong(50);
        session.save(ctpx4);

        ChiTietPhieuNhap ctpn8 = new ChiTietPhieuNhap();
        ctpn8.setIdChiTietSp(ctsp8);
        ctpn8.setIdPhieuNhap(pn4);
        ctpn8.setSoLuong(200);
        ctpn8.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn8);

        SanPham sanPham2 = new SanPham();
        sanPham2.setMa(MaTuSinh.gen("SP"));
        sanPham2.setTen("Giày Chanel");
        session.save(sanPham2);

        ChiTietSanPham ctsp9 = new ChiTietSanPham();
        ctsp9.setDonVi(dv3);
        ctsp9.setGiaBan(new BigDecimal(10000));
        ctsp9.setGiaNhap(new BigDecimal(1000));
        ctsp9.setHinhAnh(null);
        ctsp9.setMau(MauConstant.CAM);
        ctsp9.setNamBaoHanh(1);
        ctsp9.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp9.setSanPham(sanPham2);
        ctsp9.setSize(38);
        ctsp9.setSoLuongTon(1000);
        ctsp9.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp9);

        ChiTietPhieuNhap ctpn9 = new ChiTietPhieuNhap();
        ctpn9.setIdChiTietSp(ctsp9);
        ctpn9.setIdPhieuNhap(pn);
        ctpn9.setSoLuong(1000);
        ctpn9.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn9);

        ChiTietSanPham ctsp10 = new ChiTietSanPham();
        ctsp10.setDonVi(dv3);
        ctsp10.setGiaBan(new BigDecimal(1000000));
        ctsp10.setGiaNhap(new BigDecimal(1000));
        ctsp10.setHinhAnh(null);
        ctsp10.setMau(MauConstant.TRANG);
        ctsp10.setNamBaoHanh(1);
        ctsp10.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp10.setSanPham(sanPham2);
        ctsp10.setSize(30);
        ctsp10.setSoLuongTon(150);
        ctsp10.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp10);

        ChiTietPhieuXuat ctpx5 = new ChiTietPhieuXuat();
        ctpx5.setIdChiTietSp(ctsp10);
        ctpx5.setIdPhieuXuat(px3);
        ctpx5.setSoLuong(50);
        session.save(ctpx5);

        ChiTietPhieuNhap ctpn10 = new ChiTietPhieuNhap();
        ctpn10.setIdChiTietSp(ctsp10);
        ctpn10.setIdPhieuNhap(pn1);
        ctpn10.setSoLuong(200);
        ctpn10.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn10);

        ChiTietSanPham ctsp11 = new ChiTietSanPham();
        ctsp11.setDonVi(dv3);
        ctsp11.setGiaBan(new BigDecimal(10000));
        ctsp11.setGiaNhap(new BigDecimal(100));
        ctsp11.setHinhAnh(null);
        ctsp11.setMau(MauConstant.DEN);
        ctsp11.setNamBaoHanh(1);
        ctsp11.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp11.setSanPham(sanPham2);
        ctsp11.setSize(38);
        ctsp11.setSoLuongTon(2);
        ctsp11.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp11);

        ChiTietPhieuNhap ctpn11 = new ChiTietPhieuNhap();
        ctpn11.setIdChiTietSp(ctsp11);
        ctpn11.setIdPhieuNhap(pn3);
        ctpn11.setSoLuong(1000);
        ctpn11.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn11);

        ChiTietSanPham ctsp12 = new ChiTietSanPham();
        ctsp12.setDonVi(dv3);
        ctsp12.setGiaBan(new BigDecimal(10000));
        ctsp12.setGiaNhap(new BigDecimal(1000));
        ctsp12.setHinhAnh(null);
        ctsp12.setMau(MauConstant.VANG);
        ctsp12.setNamBaoHanh(1);
        ctsp12.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp12.setSanPham(sanPham2);
        ctsp12.setSize(40);
        ctsp12.setSoLuongTon(150);
        ctsp12.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp12);

        ChiTietPhieuXuat ctpx6 = new ChiTietPhieuXuat();
        ctpx6.setIdChiTietSp(ctsp12);
        ctpx6.setIdPhieuXuat(px3);
        ctpx6.setSoLuong(50);
        session.save(ctpx6);

        ChiTietPhieuNhap ctpn12 = new ChiTietPhieuNhap();
        ctpn12.setIdChiTietSp(ctsp12);
        ctpn12.setIdPhieuNhap(pn4);
        ctpn12.setSoLuong(200);
        ctpn12.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn12);

        SanPham sanPham3 = new SanPham();
        sanPham3.setMa(MaTuSinh.gen("SP"));
        sanPham3.setTen("Giày Adidas");
        session.save(sanPham3);

        ChiTietSanPham ctsp13 = new ChiTietSanPham();
        ctsp13.setDonVi(dv3);
        ctsp13.setGiaBan(new BigDecimal(10000));
        ctsp13.setGiaNhap(new BigDecimal(1000));
        ctsp13.setHinhAnh(null);
        ctsp13.setMau(MauConstant.CAM);
        ctsp13.setNamBaoHanh(1);
        ctsp13.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp13.setSanPham(sanPham3);
        ctsp13.setSize(38);
        ctsp13.setSoLuongTon(1000);
        ctsp13.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp13);

        ChiTietPhieuNhap ctpn13 = new ChiTietPhieuNhap();
        ctpn13.setIdChiTietSp(ctsp13);
        ctpn13.setIdPhieuNhap(pn);
        ctpn13.setSoLuong(1000);
        ctpn13.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn13);

        ChiTietSanPham ctsp14 = new ChiTietSanPham();
        ctsp14.setDonVi(dv3);
        ctsp14.setGiaBan(new BigDecimal(1000000));
        ctsp14.setGiaNhap(new BigDecimal(1000));
        ctsp14.setHinhAnh(null);
        ctsp14.setMau(MauConstant.TRANG);
        ctsp14.setNamBaoHanh(1);
        ctsp14.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp14.setSanPham(sanPham3);
        ctsp14.setSize(30);
        ctsp14.setSoLuongTon(150);
        ctsp14.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp14);

        ChiTietPhieuXuat ctpx7 = new ChiTietPhieuXuat();
        ctpx7.setIdChiTietSp(ctsp14);
        ctpx7.setIdPhieuXuat(px3);
        ctpx7.setSoLuong(50);
        session.save(ctpx7);

        ChiTietPhieuNhap ctpn14 = new ChiTietPhieuNhap();
        ctpn14.setIdChiTietSp(ctsp14);
        ctpn14.setIdPhieuNhap(pn1);
        ctpn14.setSoLuong(200);
        ctpn14.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn14);

        ChiTietSanPham ctsp15 = new ChiTietSanPham();
        ctsp15.setDonVi(dv3);
        ctsp15.setGiaBan(new BigDecimal(10000));
        ctsp15.setGiaNhap(new BigDecimal(100));
        ctsp15.setHinhAnh(null);
        ctsp15.setMau(MauConstant.DEN);
        ctsp15.setNamBaoHanh(1);
        ctsp15.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp15.setSanPham(sanPham3);
        ctsp15.setSize(38);
        ctsp15.setSoLuongTon(2);
        ctsp15.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp15);

        ChiTietPhieuNhap ctpn15 = new ChiTietPhieuNhap();
        ctpn15.setIdChiTietSp(ctsp15);
        ctpn15.setIdPhieuNhap(pn3);
        ctpn15.setSoLuong(1000);
        ctpn15.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn15);

        ChiTietSanPham ctsp16 = new ChiTietSanPham();
        ctsp16.setDonVi(dv3);
        ctsp16.setGiaBan(new BigDecimal(10000));
        ctsp16.setGiaNhap(new BigDecimal(1000));
        ctsp16.setHinhAnh(null);
        ctsp16.setMau(MauConstant.VANG);
        ctsp16.setNamBaoHanh(1);
        ctsp16.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp16.setSanPham(sanPham3);
        ctsp16.setSize(40);
        ctsp16.setSoLuongTon(150);
        ctsp16.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp16);

        ChiTietPhieuXuat ctpx8 = new ChiTietPhieuXuat();
        ctpx8.setIdChiTietSp(ctsp16);
        ctpx8.setIdPhieuXuat(px4);
        ctpx8.setSoLuong(50);
        session.save(ctpx8);

        ChiTietPhieuNhap ctpn16 = new ChiTietPhieuNhap();
        ctpn16.setIdChiTietSp(ctsp16);
        ctpn16.setIdPhieuNhap(pn4);
        ctpn16.setSoLuong(200);
        ctpn16.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn16);

        ChiTietSanPham ctsp17 = new ChiTietSanPham();
        ctsp17.setDonVi(dv3);
        ctsp17.setGiaBan(new BigDecimal(10000));
        ctsp17.setGiaNhap(new BigDecimal(1000));
        ctsp17.setHinhAnh(null);
        ctsp17.setMau(MauConstant.VANG);
        ctsp17.setNamBaoHanh(1);
        ctsp17.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp17.setSanPham(sanPham3);
        ctsp17.setSize(40);
        ctsp17.setSoLuongTon(150);
        ctsp17.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp17);

        ChiTietPhieuXuat ctpx9 = new ChiTietPhieuXuat();
        ctpx9.setIdChiTietSp(ctsp17);
        ctpx9.setIdPhieuXuat(px3);
        ctpx9.setSoLuong(50);
        session.save(ctpx9);

        ChiTietPhieuNhap ctpn17 = new ChiTietPhieuNhap();
        ctpn17.setIdChiTietSp(ctsp17);
        ctpn17.setIdPhieuNhap(pn2);
        ctpn17.setSoLuong(200);
        ctpn17.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn17);

        SanPham sanPham4 = new SanPham();
        sanPham4.setMa(MaTuSinh.gen("SP"));
        sanPham4.setTen("Giày abcxyz");
        session.save(sanPham4);

        ChiTietSanPham ctsp18 = new ChiTietSanPham();
        ctsp18.setDonVi(dv3);
        ctsp18.setGiaBan(new BigDecimal(10000));
        ctsp18.setGiaNhap(new BigDecimal(1000));
        ctsp18.setHinhAnh(null);
        ctsp18.setMau(MauConstant.CAM);
        ctsp18.setNamBaoHanh(1);
        ctsp18.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp18.setSanPham(sanPham4);
        ctsp18.setSize(38);
        ctsp18.setSoLuongTon(1000);
        ctsp18.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp18);

        ChiTietPhieuNhap ctpn18 = new ChiTietPhieuNhap();
        ctpn18.setIdChiTietSp(ctsp18);
        ctpn18.setIdPhieuNhap(pn);
        ctpn18.setSoLuong(1000);
        ctpn18.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn18);

        ChiTietSanPham ctsp19 = new ChiTietSanPham();
        ctsp19.setDonVi(dv3);
        ctsp19.setGiaBan(new BigDecimal(1000000));
        ctsp19.setGiaNhap(new BigDecimal(1000));
        ctsp19.setHinhAnh(null);
        ctsp19.setMau(MauConstant.TRANG);
        ctsp19.setNamBaoHanh(1);
        ctsp19.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp19.setSanPham(sanPham4);
        ctsp19.setSize(30);
        ctsp19.setSoLuongTon(150);
        ctsp19.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp19);

        ChiTietPhieuXuat ctpx10 = new ChiTietPhieuXuat();
        ctpx10.setIdChiTietSp(ctsp19);
        ctpx10.setIdPhieuXuat(px1);
        ctpx10.setSoLuong(50);
        session.save(ctpx10);

        ChiTietPhieuNhap ctpn19 = new ChiTietPhieuNhap();
        ctpn19.setIdChiTietSp(ctsp19);
        ctpn19.setIdPhieuNhap(pn1);
        ctpn19.setSoLuong(200);
        ctpn19.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn19);

        ChiTietSanPham ctsp20 = new ChiTietSanPham();
        ctsp20.setDonVi(dv3);
        ctsp20.setGiaBan(new BigDecimal(10000));
        ctsp20.setGiaNhap(new BigDecimal(100));
        ctsp20.setHinhAnh(null);
        ctsp20.setMau(MauConstant.DEN);
        ctsp20.setNamBaoHanh(1);
        ctsp20.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp20.setSanPham(sanPham4);
        ctsp20.setSize(38);
        ctsp20.setSoLuongTon(2);
        ctsp20.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp20);

        ChiTietPhieuNhap ctpn20 = new ChiTietPhieuNhap();
        ctpn20.setIdChiTietSp(ctsp20);
        ctpn20.setIdPhieuNhap(pn3);
        ctpn20.setSoLuong(1000);
        ctpn20.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn20);

        ChiTietSanPham ctsp21 = new ChiTietSanPham();
        ctsp21.setDonVi(dv3);
        ctsp21.setGiaBan(new BigDecimal(10000));
        ctsp21.setGiaNhap(new BigDecimal(1000));
        ctsp21.setHinhAnh(null);
        ctsp21.setMau(MauConstant.VANG);
        ctsp21.setNamBaoHanh(1);
        ctsp21.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp21.setSanPham(sanPham4);
        ctsp21.setSize(40);
        ctsp21.setSoLuongTon(150);
        ctsp21.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp21);

        ChiTietPhieuXuat ctpx11 = new ChiTietPhieuXuat();
        ctpx11.setIdChiTietSp(ctsp21);
        ctpx11.setIdPhieuXuat(px2);
        ctpx11.setSoLuong(50);
        session.save(ctpx11);

        ChiTietPhieuNhap ctpn21 = new ChiTietPhieuNhap();
        ctpn21.setIdChiTietSp(ctsp21);
        ctpn21.setIdPhieuNhap(pn4);
        ctpn21.setSoLuong(200);
        ctpn21.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn21);

        ChiTietSanPham ctsp22 = new ChiTietSanPham();
        ctsp22.setDonVi(dv3);
        ctsp22.setGiaBan(new BigDecimal(10000));
        ctsp22.setGiaNhap(new BigDecimal(1000));
        ctsp22.setHinhAnh(null);
        ctsp22.setMau(MauConstant.VANG);
        ctsp22.setNamBaoHanh(1);
        ctsp22.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp22.setSanPham(sanPham4);
        ctsp22.setSize(40);
        ctsp22.setSoLuongTon(150);
        ctsp22.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp22);

        ChiTietPhieuXuat ctpx12 = new ChiTietPhieuXuat();
        ctpx12.setIdChiTietSp(ctsp22);
        ctpx12.setIdPhieuXuat(px1);
        ctpx12.setSoLuong(50);
        session.save(ctpx12);

        ChiTietPhieuNhap ctpn22 = new ChiTietPhieuNhap();
        ctpn22.setIdChiTietSp(ctsp22);
        ctpn22.setIdPhieuNhap(pn2);
        ctpn22.setSoLuong(200);
        ctpn22.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn22);

        SanPham sanPham5 = new SanPham();
        sanPham5.setMa(MaTuSinh.gen("SP"));
        sanPham5.setTen("Giày bata");
        session.save(sanPham5);

        ChiTietSanPham ctsp23 = new ChiTietSanPham();
        ctsp23.setDonVi(dv3);
        ctsp23.setGiaBan(new BigDecimal(10000));
        ctsp23.setGiaNhap(new BigDecimal(1000));
        ctsp23.setHinhAnh(null);
        ctsp23.setMau(MauConstant.CAM);
        ctsp23.setNamBaoHanh(1);
        ctsp23.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp23.setSanPham(sanPham5);
        ctsp23.setSize(38);
        ctsp23.setSoLuongTon(1000);
        ctsp23.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp23);

        ChiTietPhieuNhap ctpn23 = new ChiTietPhieuNhap();
        ctpn23.setIdChiTietSp(ctsp23);
        ctpn23.setIdPhieuNhap(pn);
        ctpn23.setSoLuong(1000);
        ctpn23.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn23);

        ChiTietSanPham ctsp24 = new ChiTietSanPham();
        ctsp24.setDonVi(dv3);
        ctsp24.setGiaBan(new BigDecimal(1000000));
        ctsp24.setGiaNhap(new BigDecimal(1000));
        ctsp24.setHinhAnh(null);
        ctsp24.setMau(MauConstant.TRANG);
        ctsp24.setNamBaoHanh(1);
        ctsp24.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp24.setSanPham(sanPham5);
        ctsp24.setSize(30);
        ctsp24.setSoLuongTon(150);
        ctsp24.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp24);

        ChiTietPhieuXuat ctpx13 = new ChiTietPhieuXuat();
        ctpx13.setIdChiTietSp(ctsp24);
        ctpx13.setIdPhieuXuat(px1);
        ctpx13.setSoLuong(50);
        session.save(ctpx13);

        ChiTietPhieuNhap ctpn24 = new ChiTietPhieuNhap();
        ctpn24.setIdChiTietSp(ctsp24);
        ctpn24.setIdPhieuNhap(pn1);
        ctpn24.setSoLuong(200);
        ctpn24.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn24);

        ChiTietSanPham ctsp25 = new ChiTietSanPham();
        ctsp25.setDonVi(dv3);
        ctsp25.setGiaBan(new BigDecimal(10000));
        ctsp25.setGiaNhap(new BigDecimal(100));
        ctsp25.setHinhAnh(null);
        ctsp25.setMau(MauConstant.DEN);
        ctsp25.setNamBaoHanh(1);
        ctsp25.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp25.setSanPham(sanPham5);
        ctsp25.setSize(38);
        ctsp25.setSoLuongTon(2);
        ctsp25.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp25);

        ChiTietPhieuNhap ctpn25 = new ChiTietPhieuNhap();
        ctpn25.setIdChiTietSp(ctsp25);
        ctpn25.setIdPhieuNhap(pn3);
        ctpn25.setSoLuong(1000);
        ctpn25.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn25);

        ChiTietSanPham ctsp26 = new ChiTietSanPham();
        ctsp26.setDonVi(dv3);
        ctsp26.setGiaBan(new BigDecimal(10000));
        ctsp26.setGiaNhap(new BigDecimal(1000));
        ctsp26.setHinhAnh(null);
        ctsp26.setMau(MauConstant.VANG);
        ctsp26.setNamBaoHanh(1);
        ctsp26.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp26.setSanPham(sanPham5);
        ctsp26.setSize(40);
        ctsp26.setSoLuongTon(150);
        ctsp26.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp26);

        ChiTietPhieuXuat ctpx14 = new ChiTietPhieuXuat();
        ctpx14.setIdChiTietSp(ctsp26);
        ctpx14.setIdPhieuXuat(px4);
        ctpx14.setSoLuong(50);
        session.save(ctpx14);

        ChiTietPhieuNhap ctpn26 = new ChiTietPhieuNhap();
        ctpn26.setIdChiTietSp(ctsp26);
        ctpn26.setIdPhieuNhap(pn4);
        ctpn26.setSoLuong(200);
        ctpn26.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn26);

        ChiTietSanPham ctsp27 = new ChiTietSanPham();
        ctsp27.setDonVi(dv3);
        ctsp27.setGiaBan(new BigDecimal(10000));
        ctsp27.setGiaNhap(new BigDecimal(1000));
        ctsp27.setHinhAnh(null);
        ctsp27.setMau(MauConstant.VANG);
        ctsp27.setNamBaoHanh(1);
        ctsp27.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp27.setSanPham(sanPham5);
        ctsp27.setSize(40);
        ctsp27.setSoLuongTon(150);
        ctsp27.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp27);

        ChiTietPhieuXuat ctpx15 = new ChiTietPhieuXuat();
        ctpx15.setIdChiTietSp(ctsp27);
        ctpx15.setIdPhieuXuat(px);
        ctpx15.setSoLuong(50);
        session.save(ctpx15);

        ChiTietPhieuNhap ctpn27 = new ChiTietPhieuNhap();
        ctpn27.setIdChiTietSp(ctsp27);
        ctpn27.setIdPhieuNhap(pn2);
        ctpn27.setSoLuong(200);
        ctpn27.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn27);

        SanPham sanPham6 = new SanPham();
        sanPham6.setMa(MaTuSinh.gen("SP"));
        sanPham6.setTen("Giày hết lười");
        session.save(sanPham6);

        ChiTietSanPham ctsp28 = new ChiTietSanPham();
        ctsp28.setDonVi(dv3);
        ctsp28.setGiaBan(new BigDecimal(10000));
        ctsp28.setGiaNhap(new BigDecimal(1000));
        ctsp28.setHinhAnh(null);
        ctsp28.setMau(MauConstant.CAM);
        ctsp28.setNamBaoHanh(1);
        ctsp28.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp28.setSanPham(sanPham6);
        ctsp28.setSize(38);
        ctsp28.setSoLuongTon(1000);
        ctsp28.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp28);

        ChiTietPhieuNhap ctpn28 = new ChiTietPhieuNhap();
        ctpn28.setIdChiTietSp(ctsp28);
        ctpn28.setIdPhieuNhap(pn);
        ctpn28.setSoLuong(1000);
        ctpn28.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn28);

        ChiTietSanPham ctsp29 = new ChiTietSanPham();
        ctsp29.setDonVi(dv3);
        ctsp29.setGiaBan(new BigDecimal(1000000));
        ctsp29.setGiaNhap(new BigDecimal(1000));
        ctsp29.setHinhAnh(null);
        ctsp29.setMau(MauConstant.TRANG);
        ctsp29.setNamBaoHanh(1);
        ctsp29.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp29.setSanPham(sanPham6);
        ctsp29.setSize(30);
        ctsp29.setSoLuongTon(150);
        ctsp29.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp29);

        ChiTietPhieuXuat ctpx16 = new ChiTietPhieuXuat();
        ctpx16.setIdChiTietSp(ctsp29);
        ctpx16.setIdPhieuXuat(px2);
        ctpx16.setSoLuong(50);
        session.save(ctpx16);

        ChiTietPhieuNhap ctpn29 = new ChiTietPhieuNhap();
        ctpn29.setIdChiTietSp(ctsp29);
        ctpn29.setIdPhieuNhap(pn1);
        ctpn29.setSoLuong(200);
        ctpn29.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn29);

        ChiTietSanPham ctsp30 = new ChiTietSanPham();
        ctsp30.setDonVi(dv3);
        ctsp30.setGiaBan(new BigDecimal(10000));
        ctsp30.setGiaNhap(new BigDecimal(100));
        ctsp30.setHinhAnh(null);
        ctsp30.setMau(MauConstant.DEN);
        ctsp30.setNamBaoHanh(1);
        ctsp30.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp30.setSanPham(sanPham6);
        ctsp30.setSize(38);
        ctsp30.setSoLuongTon(2);
        ctsp30.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp30);

        ChiTietPhieuNhap ctpn30 = new ChiTietPhieuNhap();
        ctpn30.setIdChiTietSp(ctsp30);
        ctpn30.setIdPhieuNhap(pn3);
        ctpn30.setSoLuong(1000);
        ctpn30.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn30);

        ChiTietSanPham ctsp31 = new ChiTietSanPham();
        ctsp31.setDonVi(dv3);
        ctsp31.setGiaBan(new BigDecimal(10000));
        ctsp31.setGiaNhap(new BigDecimal(1000));
        ctsp31.setHinhAnh(null);
        ctsp31.setMau(MauConstant.VANG);
        ctsp31.setNamBaoHanh(1);
        ctsp31.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp31.setSanPham(sanPham6);
        ctsp31.setSize(40);
        ctsp31.setSoLuongTon(150);
        ctsp31.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp31);

        ChiTietPhieuXuat ctpx17 = new ChiTietPhieuXuat();
        ctpx17.setIdChiTietSp(ctsp31);
        ctpx17.setIdPhieuXuat(px3);
        ctpx17.setSoLuong(50);
        session.save(ctpx17);

        ChiTietPhieuNhap ctpn31 = new ChiTietPhieuNhap();
        ctpn31.setIdChiTietSp(ctsp31);
        ctpn31.setIdPhieuNhap(pn4);
        ctpn31.setSoLuong(200);
        ctpn31.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn31);

        ChiTietSanPham ctsp32 = new ChiTietSanPham();
        ctsp32.setDonVi(dv3);
        ctsp32.setGiaBan(new BigDecimal(10000));
        ctsp32.setGiaNhap(new BigDecimal(1000));
        ctsp32.setHinhAnh(null);
        ctsp32.setMau(MauConstant.VANG);
        ctsp32.setNamBaoHanh(1);
        ctsp32.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp32.setSanPham(sanPham6);
        ctsp32.setSize(40);
        ctsp32.setSoLuongTon(150);
        ctsp32.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp32);

        ChiTietPhieuXuat ctpx18 = new ChiTietPhieuXuat();
        ctpx18.setIdChiTietSp(ctsp32);
        ctpx18.setIdPhieuXuat(px3);
        ctpx18.setSoLuong(50);
        session.save(ctpx18);

        ChiTietPhieuNhap ctpn33 = new ChiTietPhieuNhap();
        ctpn33.setIdChiTietSp(ctsp32);
        ctpn33.setIdPhieuNhap(pn2);
        ctpn33.setSoLuong(200);
        ctpn33.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn33);

        SanPham sanPham7 = new SanPham();
        sanPham7.setMa(MaTuSinh.gen("SP"));
        sanPham7.setTen("Giày thượng đình");
        session.save(sanPham7);

        ChiTietSanPham ctsp34 = new ChiTietSanPham();
        ctsp34.setDonVi(dv3);
        ctsp34.setGiaBan(new BigDecimal(10000));
        ctsp34.setGiaNhap(new BigDecimal(1000));
        ctsp34.setHinhAnh(null);
        ctsp34.setMau(MauConstant.CAM);
        ctsp34.setNamBaoHanh(1);
        ctsp34.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp34.setSanPham(sanPham7);
        ctsp34.setSize(38);
        ctsp34.setSoLuongTon(1000);
        ctsp34.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp34);

        ChiTietPhieuNhap ctpn34 = new ChiTietPhieuNhap();
        ctpn34.setIdChiTietSp(ctsp34);
        ctpn34.setIdPhieuNhap(pn);
        ctpn34.setSoLuong(1000);
        ctpn34.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn34);

        ChiTietSanPham ctsp35 = new ChiTietSanPham();
        ctsp35.setDonVi(dv3);
        ctsp35.setGiaBan(new BigDecimal(1000000));
        ctsp35.setGiaNhap(new BigDecimal(1000));
        ctsp35.setHinhAnh(null);
        ctsp35.setMau(MauConstant.TRANG);
        ctsp35.setNamBaoHanh(1);
        ctsp35.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp35.setSanPham(sanPham7);
        ctsp35.setSize(30);
        ctsp35.setSoLuongTon(150);
        ctsp35.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp35);

        ChiTietPhieuXuat ctpx19 = new ChiTietPhieuXuat();
        ctpx19.setIdChiTietSp(ctsp35);
        ctpx19.setIdPhieuXuat(px1);
        ctpx19.setSoLuong(50);
        session.save(ctpx19);

        ChiTietPhieuNhap ctpn35 = new ChiTietPhieuNhap();
        ctpn35.setIdChiTietSp(ctsp35);
        ctpn35.setIdPhieuNhap(pn1);
        ctpn35.setSoLuong(200);
        ctpn35.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn35);

        ChiTietSanPham ctsp36 = new ChiTietSanPham();
        ctsp36.setDonVi(dv3);
        ctsp36.setGiaBan(new BigDecimal(10000));
        ctsp36.setGiaNhap(new BigDecimal(100));
        ctsp36.setHinhAnh(null);
        ctsp36.setMau(MauConstant.DEN);
        ctsp36.setNamBaoHanh(1);
        ctsp36.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp36.setSanPham(sanPham7);
        ctsp36.setSize(38);
        ctsp36.setSoLuongTon(2);
        ctsp36.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp36);

        ChiTietPhieuNhap ctpn36 = new ChiTietPhieuNhap();
        ctpn36.setIdChiTietSp(ctsp36);
        ctpn36.setIdPhieuNhap(pn3);
        ctpn36.setSoLuong(1000);
        ctpn36.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn36);

        ChiTietSanPham ctsp37 = new ChiTietSanPham();
        ctsp37.setDonVi(dv3);
        ctsp37.setGiaBan(new BigDecimal(10000));
        ctsp37.setGiaNhap(new BigDecimal(1000));
        ctsp37.setHinhAnh(null);
        ctsp37.setMau(MauConstant.VANG);
        ctsp37.setNamBaoHanh(1);
        ctsp37.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp37.setSanPham(sanPham7);
        ctsp37.setSize(40);
        ctsp37.setSoLuongTon(150);
        ctsp37.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp37);

        ChiTietPhieuXuat ctpx20 = new ChiTietPhieuXuat();
        ctpx20.setIdChiTietSp(ctsp37);
        ctpx20.setIdPhieuXuat(px2);
        ctpx20.setSoLuong(50);
        session.save(ctpx20);

        ChiTietPhieuNhap ctpn37 = new ChiTietPhieuNhap();
        ctpn37.setIdChiTietSp(ctsp37);
        ctpn37.setIdPhieuNhap(pn4);
        ctpn37.setSoLuong(200);
        ctpn37.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn37);

        ChiTietSanPham ctsp38 = new ChiTietSanPham();
        ctsp38.setDonVi(dv3);
        ctsp38.setGiaBan(new BigDecimal(10000));
        ctsp38.setGiaNhap(new BigDecimal(1000));
        ctsp38.setHinhAnh(null);
        ctsp38.setMau(MauConstant.VANG);
        ctsp38.setNamBaoHanh(1);
        ctsp38.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp38.setSanPham(sanPham7);
        ctsp38.setSize(40);
        ctsp38.setSoLuongTon(150);
        ctsp38.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp38);

        ChiTietPhieuXuat ctpx21 = new ChiTietPhieuXuat();
        ctpx21.setIdChiTietSp(ctsp38);
        ctpx21.setIdPhieuXuat(px2);
        ctpx21.setSoLuong(50);
        session.save(ctpx21);

        ChiTietPhieuNhap ctpn38 = new ChiTietPhieuNhap();
        ctpn38.setIdChiTietSp(ctsp38);
        ctpn38.setIdPhieuNhap(pn2);
        ctpn38.setSoLuong(200);
        ctpn38.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn38);

        SanPham sanPham8 = new SanPham();
        sanPham8.setMa(MaTuSinh.gen("SP"));
        sanPham8.setTen("Giày đá bóng");
        session.save(sanPham8);

        ChiTietSanPham ctsp39 = new ChiTietSanPham();
        ctsp39.setDonVi(dv3);
        ctsp39.setGiaBan(new BigDecimal(10000));
        ctsp39.setGiaNhap(new BigDecimal(1000));
        ctsp39.setHinhAnh(null);
        ctsp39.setMau(MauConstant.CAM);
        ctsp39.setNamBaoHanh(1);
        ctsp39.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp39.setSanPham(sanPham8);
        ctsp39.setSize(38);
        ctsp39.setSoLuongTon(1000);
        ctsp39.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp39);

        ChiTietPhieuNhap ctpn39 = new ChiTietPhieuNhap();
        ctpn39.setIdChiTietSp(ctsp39);
        ctpn39.setIdPhieuNhap(pn);
        ctpn39.setSoLuong(1000);
        ctpn39.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn39);

        ChiTietSanPham ctsp40 = new ChiTietSanPham();
        ctsp40.setDonVi(dv3);
        ctsp40.setGiaBan(new BigDecimal(1000000));
        ctsp40.setGiaNhap(new BigDecimal(1000));
        ctsp40.setHinhAnh(null);
        ctsp40.setMau(MauConstant.TRANG);
        ctsp40.setNamBaoHanh(1);
        ctsp40.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp40.setSanPham(sanPham8);
        ctsp40.setSize(30);
        ctsp40.setSoLuongTon(150);
        ctsp40.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp40);

        ChiTietPhieuXuat ctpx22 = new ChiTietPhieuXuat();
        ctpx22.setIdChiTietSp(ctsp40);
        ctpx22.setIdPhieuXuat(px1);
        ctpx22.setSoLuong(50);
        session.save(ctpx22);

        ChiTietPhieuNhap ctpn40 = new ChiTietPhieuNhap();
        ctpn40.setIdChiTietSp(ctsp40);
        ctpn40.setIdPhieuNhap(pn1);
        ctpn40.setSoLuong(200);
        ctpn40.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn40);

        ChiTietSanPham ctsp41 = new ChiTietSanPham();
        ctsp41.setDonVi(dv3);
        ctsp41.setGiaBan(new BigDecimal(10000));
        ctsp41.setGiaNhap(new BigDecimal(100));
        ctsp41.setHinhAnh(null);
        ctsp41.setMau(MauConstant.DEN);
        ctsp41.setNamBaoHanh(1);
        ctsp41.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp41.setSanPham(sanPham8);
        ctsp41.setSize(38);
        ctsp41.setSoLuongTon(2);
        ctsp41.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp41);

        ChiTietPhieuNhap ctpn41 = new ChiTietPhieuNhap();
        ctpn41.setIdChiTietSp(ctsp41);
        ctpn41.setIdPhieuNhap(pn3);
        ctpn41.setSoLuong(1000);
        ctpn41.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn41);

        ChiTietSanPham ctsp42 = new ChiTietSanPham();
        ctsp42.setDonVi(dv3);
        ctsp42.setGiaBan(new BigDecimal(10000));
        ctsp42.setGiaNhap(new BigDecimal(1000));
        ctsp42.setHinhAnh(null);
        ctsp42.setMau(MauConstant.VANG);
        ctsp42.setNamBaoHanh(1);
        ctsp42.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp42.setSanPham(sanPham8);
        ctsp42.setSize(40);
        ctsp42.setSoLuongTon(150);
        ctsp42.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp42);

        ChiTietPhieuXuat ctpx23 = new ChiTietPhieuXuat();
        ctpx23.setIdChiTietSp(ctsp42);
        ctpx23.setIdPhieuXuat(px3);
        ctpx23.setSoLuong(50);
        session.save(ctpx23);

        ChiTietPhieuNhap ctpn42 = new ChiTietPhieuNhap();
        ctpn42.setIdChiTietSp(ctsp42);
        ctpn42.setIdPhieuNhap(pn4);
        ctpn42.setSoLuong(200);
        ctpn42.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn42);

        ChiTietSanPham ctsp43 = new ChiTietSanPham();
        ctsp43.setDonVi(dv3);
        ctsp43.setGiaBan(new BigDecimal(10000));
        ctsp43.setGiaNhap(new BigDecimal(1000));
        ctsp43.setHinhAnh(null);
        ctsp43.setMau(MauConstant.VANG);
        ctsp43.setNamBaoHanh(1);
        ctsp43.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp43.setSanPham(sanPham8);
        ctsp43.setSize(40);
        ctsp43.setSoLuongTon(150);
        ctsp43.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp43);

        ChiTietPhieuXuat ctpx24 = new ChiTietPhieuXuat();
        ctpx24.setIdChiTietSp(ctsp43);
        ctpx24.setIdPhieuXuat(px4);
        ctpx24.setSoLuong(50);
        session.save(ctpx24);

        ChiTietPhieuNhap ctpn43 = new ChiTietPhieuNhap();
        ctpn43.setIdChiTietSp(ctsp43);
        ctpn43.setIdPhieuNhap(pn2);
        ctpn43.setSoLuong(200);
        ctpn43.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn43);

        SanPham sanPham9 = new SanPham();
        sanPham9.setMa(MaTuSinh.gen("SP"));
        sanPham9.setTen("Giày đi mưa");
        session.save(sanPham9);

        ChiTietSanPham ctsp44 = new ChiTietSanPham();
        ctsp44.setDonVi(dv3);
        ctsp44.setGiaBan(new BigDecimal(10000));
        ctsp44.setGiaNhap(new BigDecimal(1000));
        ctsp44.setHinhAnh(null);
        ctsp44.setMau(MauConstant.CAM);
        ctsp44.setNamBaoHanh(1);
        ctsp44.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp44.setSanPham(sanPham9);
        ctsp44.setSize(38);
        ctsp44.setSoLuongTon(1000);
        ctsp44.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp44);

        ChiTietPhieuNhap ctpn44 = new ChiTietPhieuNhap();
        ctpn44.setIdChiTietSp(ctsp44);
        ctpn44.setIdPhieuNhap(pn);
        ctpn44.setSoLuong(1000);
        ctpn44.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn44);

        ChiTietSanPham ctsp45 = new ChiTietSanPham();
        ctsp45.setDonVi(dv3);
        ctsp45.setGiaBan(new BigDecimal(1000000));
        ctsp45.setGiaNhap(new BigDecimal(1000));
        ctsp45.setHinhAnh(null);
        ctsp45.setMau(MauConstant.TRANG);
        ctsp45.setNamBaoHanh(1);
        ctsp45.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp45.setSanPham(sanPham9);
        ctsp45.setSize(30);
        ctsp45.setSoLuongTon(150);
        ctsp45.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp45);

        ChiTietPhieuXuat ctpx25 = new ChiTietPhieuXuat();
        ctpx25.setIdChiTietSp(ctsp45);
        ctpx25.setIdPhieuXuat(px4);
        ctpx25.setSoLuong(50);
        session.save(ctpx25);

        ChiTietPhieuNhap ctpn45 = new ChiTietPhieuNhap();
        ctpn45.setIdChiTietSp(ctsp45);
        ctpn45.setIdPhieuNhap(pn1);
        ctpn45.setSoLuong(200);
        ctpn45.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn45);

        ChiTietSanPham ctsp46 = new ChiTietSanPham();
        ctsp46.setDonVi(dv3);
        ctsp46.setGiaBan(new BigDecimal(10000));
        ctsp46.setGiaNhap(new BigDecimal(100));
        ctsp46.setHinhAnh(null);
        ctsp46.setMau(MauConstant.DEN);
        ctsp46.setNamBaoHanh(1);
        ctsp46.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp46.setSanPham(sanPham9);
        ctsp46.setSize(38);
        ctsp46.setSoLuongTon(2);
        ctsp46.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp46);

        ChiTietPhieuNhap ctpn46 = new ChiTietPhieuNhap();
        ctpn46.setIdChiTietSp(ctsp46);
        ctpn46.setIdPhieuNhap(pn3);
        ctpn46.setSoLuong(1000);
        ctpn46.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn46);

        ChiTietSanPham ctsp47 = new ChiTietSanPham();
        ctsp47.setDonVi(dv3);
        ctsp47.setGiaBan(new BigDecimal(10000));
        ctsp47.setGiaNhap(new BigDecimal(1000));
        ctsp47.setHinhAnh(null);
        ctsp47.setMau(MauConstant.VANG);
        ctsp47.setNamBaoHanh(1);
        ctsp47.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp47.setSanPham(sanPham9);
        ctsp47.setSize(40);
        ctsp47.setSoLuongTon(150);
        ctsp47.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp47);

        ChiTietPhieuXuat ctpx26 = new ChiTietPhieuXuat();
        ctpx26.setIdChiTietSp(ctsp47);
        ctpx26.setIdPhieuXuat(px3);
        ctpx26.setSoLuong(50);
        session.save(ctpx26);

        ChiTietPhieuNhap ctpn47 = new ChiTietPhieuNhap();
        ctpn47.setIdChiTietSp(ctsp47);
        ctpn47.setIdPhieuNhap(pn4);
        ctpn47.setSoLuong(200);
        ctpn47.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn47);

        ChiTietSanPham ctsp48 = new ChiTietSanPham();
        ctsp48.setDonVi(dv3);
        ctsp48.setGiaBan(new BigDecimal(10000));
        ctsp48.setGiaNhap(new BigDecimal(1000));
        ctsp48.setHinhAnh(null);
        ctsp48.setMau(MauConstant.VANG);
        ctsp48.setNamBaoHanh(1);
        ctsp48.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp48.setSanPham(sanPham9);
        ctsp48.setSize(40);
        ctsp48.setSoLuongTon(150);
        ctsp48.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp48);

        ChiTietPhieuXuat ctpx27 = new ChiTietPhieuXuat();
        ctpx27.setIdChiTietSp(ctsp48);
        ctpx27.setIdPhieuXuat(px1);
        ctpx27.setSoLuong(50);
        session.save(ctpx27);

        ChiTietPhieuNhap ctpn49 = new ChiTietPhieuNhap();
        ctpn49.setIdChiTietSp(ctsp48);
        ctpn49.setIdPhieuNhap(pn2);
        ctpn49.setSoLuong(200);
        ctpn49.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn49);

        SanPham sanPham10 = new SanPham();
        sanPham10.setMa(MaTuSinh.gen("SP"));
        sanPham10.setTen("Giày snacker");
        session.save(sanPham10);

        ChiTietSanPham ctsp50 = new ChiTietSanPham();
        ctsp50.setDonVi(dv3);
        ctsp50.setGiaBan(new BigDecimal(10000));
        ctsp50.setGiaNhap(new BigDecimal(1000));
        ctsp50.setHinhAnh(null);
        ctsp50.setMau(MauConstant.CAM);
        ctsp50.setNamBaoHanh(1);
        ctsp50.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp50.setSanPham(sanPham10);
        ctsp50.setSize(38);
        ctsp50.setSoLuongTon(1000);
        ctsp50.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp50);

        ChiTietPhieuNhap ctpn50 = new ChiTietPhieuNhap();
        ctpn50.setIdChiTietSp(ctsp50);
        ctpn50.setIdPhieuNhap(pn);
        ctpn50.setSoLuong(1000);
        ctpn50.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn50);

        ChiTietSanPham ctsp51 = new ChiTietSanPham();
        ctsp51.setDonVi(dv3);
        ctsp51.setGiaBan(new BigDecimal(1000000));
        ctsp51.setGiaNhap(new BigDecimal(1000));
        ctsp51.setHinhAnh(null);
        ctsp51.setMau(MauConstant.TRANG);
        ctsp51.setNamBaoHanh(1);
        ctsp51.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp51.setSanPham(sanPham10);
        ctsp51.setSize(30);
        ctsp51.setSoLuongTon(150);
        ctsp51.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp51);

        ChiTietPhieuXuat ctpx28 = new ChiTietPhieuXuat();
        ctpx28.setIdChiTietSp(ctsp51);
        ctpx28.setIdPhieuXuat(px1);
        ctpx28.setSoLuong(50);
        session.save(ctpx28);

        ChiTietPhieuNhap ctpn51 = new ChiTietPhieuNhap();
        ctpn51.setIdChiTietSp(ctsp51);
        ctpn51.setIdPhieuNhap(pn1);
        ctpn51.setSoLuong(200);
        ctpn51.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn51);

        ChiTietSanPham ctsp52 = new ChiTietSanPham();
        ctsp52.setDonVi(dv3);
        ctsp52.setGiaBan(new BigDecimal(10000));
        ctsp52.setGiaNhap(new BigDecimal(100));
        ctsp52.setHinhAnh(null);
        ctsp52.setMau(MauConstant.DEN);
        ctsp52.setNamBaoHanh(1);
        ctsp52.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp52.setSanPham(sanPham10);
        ctsp52.setSize(38);
        ctsp52.setSoLuongTon(2);
        ctsp52.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp52);

        ChiTietPhieuNhap ctpn52 = new ChiTietPhieuNhap();
        ctpn52.setIdChiTietSp(ctsp52);
        ctpn52.setIdPhieuNhap(pn3);
        ctpn52.setSoLuong(1000);
        ctpn52.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn52);

        ChiTietSanPham ctsp53 = new ChiTietSanPham();
        ctsp53.setDonVi(dv3);
        ctsp53.setGiaBan(new BigDecimal(10000));
        ctsp53.setGiaNhap(new BigDecimal(1000));
        ctsp53.setHinhAnh(null);
        ctsp53.setMau(MauConstant.VANG);
        ctsp53.setNamBaoHanh(1);
        ctsp53.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp53.setSanPham(sanPham10);
        ctsp53.setSize(40);
        ctsp53.setSoLuongTon(150);
        ctsp53.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp53);

        ChiTietPhieuXuat ctpx29 = new ChiTietPhieuXuat();
        ctpx29.setIdChiTietSp(ctsp53);
        ctpx29.setIdPhieuXuat(px);
        ctpx29.setSoLuong(50);
        session.save(ctpx29);

        ChiTietPhieuNhap ctpn53 = new ChiTietPhieuNhap();
        ctpn53.setIdChiTietSp(ctsp53);
        ctpn53.setIdPhieuNhap(pn4);
        ctpn53.setSoLuong(200);
        ctpn53.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn53);

        ChiTietSanPham ctsp54 = new ChiTietSanPham();
        ctsp54.setDonVi(dv3);
        ctsp54.setGiaBan(new BigDecimal(10000));
        ctsp54.setGiaNhap(new BigDecimal(1000));
        ctsp54.setHinhAnh(null);
        ctsp54.setMau(MauConstant.VANG);
        ctsp54.setNamBaoHanh(1);
        ctsp54.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp54.setSanPham(sanPham10);
        ctsp54.setSize(40);
        ctsp54.setSoLuongTon(150);
        ctsp54.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
        session.save(ctsp54);

        ChiTietPhieuXuat ctpx30 = new ChiTietPhieuXuat();
        ctpx30.setIdChiTietSp(ctsp54);
        ctpx30.setIdPhieuXuat(px);
        ctpx30.setSoLuong(50);
        session.save(ctpx30);

        ChiTietPhieuNhap ctpn54 = new ChiTietPhieuNhap();
        ctpn54.setIdChiTietSp(ctsp54);
        ctpn54.setIdPhieuNhap(pn2);
        ctpn54.setSoLuong(200);
        ctpn54.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn54);

        SanPham sanPham11 = new SanPham();
        sanPham11.setMa(MaTuSinh.gen("SP"));
        sanPham11.setTen("Giày puma");
        session.save(sanPham11);

        PhieuNhap pn5 = new PhieuNhap();
        pn5.setGhiChu("Giày đẹp");
        pn5.setMaPhieu(MaTuSinh.gen("PN"));
        pn5.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        pn5.setNhaCungCap(ncc);
        pn5.setNhanVien(nhanVien1);
        pn5.setTrangThai(TrangThaiPhieuConstant.CHO_THANH_TOAN);
        session.save(pn5);

        PhieuNhap pn6 = new PhieuNhap();
        pn6.setGhiChu("Giày đẹp");
        pn6.setMaPhieu(MaTuSinh.gen("PN"));
        pn6.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        pn6.setNhaCungCap(ncc);
        pn6.setNhanVien(nhanVien1);
        pn6.setTrangThai(TrangThaiPhieuConstant.CHO_THANH_TOAN);
        session.save(pn6);

        ChiTietSanPham ctsp55 = new ChiTietSanPham();
        ctsp55.setDonVi(dv3);
        ctsp55.setGiaBan(new BigDecimal(10000));
        ctsp55.setGiaNhap(new BigDecimal(1000));
        ctsp55.setHinhAnh(null);
        ctsp55.setMau(MauConstant.VANG);
        ctsp55.setNamBaoHanh(1);
        ctsp55.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp55.setSanPham(sanPham11);
        ctsp55.setSize(40);
        ctsp55.setSoLuongTon(150);
        ctsp55.setTrangThai(TrangThaiSanPhamConstanst.CHO_XAC_NHAN);
        session.save(ctsp55);

        ChiTietPhieuNhap ctpn55 = new ChiTietPhieuNhap();
        ctpn55.setIdChiTietSp(ctsp55);
        ctpn55.setIdPhieuNhap(pn5);
        ctpn55.setSoLuong(200);
        ctpn55.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn55);

        SanPham sanPham12 = new SanPham();
        sanPham12.setMa(MaTuSinh.gen("SP"));
        sanPham12.setTen("Giày zezy");
        session.save(sanPham12);

        SanPham sanPham13 = new SanPham();
        sanPham13.setMa(MaTuSinh.gen("SP"));
        sanPham13.setTen("Giày cao cổ");
        session.save(sanPham13);

        ChiTietSanPham ctsp57 = new ChiTietSanPham();
        ctsp57.setDonVi(dv3);
        ctsp57.setGiaBan(new BigDecimal(10000));
        ctsp57.setGiaNhap(new BigDecimal(1000));
        ctsp57.setHinhAnh(null);
        ctsp57.setMau(MauConstant.VANG);
        ctsp57.setNamBaoHanh(1);
        ctsp57.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        ctsp57.setSanPham(sanPham13);
        ctsp57.setSize(40);
        ctsp57.setSoLuongTon(150);
        ctsp57.setTrangThai(TrangThaiSanPhamConstanst.CHO_XAC_NHAN);
        session.save(ctsp57);

        ChiTietPhieuNhap ctpn57 = new ChiTietPhieuNhap();
        ctpn57.setIdChiTietSp(ctsp57);
        ctpn57.setIdPhieuNhap(pn5);
        ctpn57.setSoLuong(200);
        ctpn57.setMaSanPhamNhaCungCap(MaTuSinh.gen("SP"));
        session.save(ctpn57);

        SanPham sanPham14 = new SanPham();
        sanPham14.setMa(MaTuSinh.gen("SP"));
        sanPham14.setTen("Giày thấp cổ");
        session.save(sanPham14);

        SanPham sanPham15 = new SanPham();
        sanPham15.setMa(MaTuSinh.gen("SP"));
        sanPham15.setTen("Giày lười");
        session.save(sanPham15);

        SanPham sanPham16 = new SanPham();
        sanPham16.setMa(MaTuSinh.gen("SP"));
        sanPham16.setTen("Giày đinh");
        session.save(sanPham16);

        SanPham sanPham17 = new SanPham();
        sanPham17.setMa(MaTuSinh.gen("SP"));
        sanPham17.setTen("Giày avatar");
        session.save(sanPham17);

        SanPham sanPham18 = new SanPham();
        sanPham18.setMa(MaTuSinh.gen("SP"));
        sanPham18.setTen("Bot beautifull");
        session.save(sanPham18);

        SanPham sanPham19 = new SanPham();
        sanPham19.setMa(MaTuSinh.gen("SP"));
        sanPham19.setTen("Giày leo núi");
        session.save(sanPham19);

        SanPham sanPham20 = new SanPham();
        sanPham20.setMa(MaTuSinh.gen("SP"));
        sanPham20.setTen("Giày nhóm 5");
        session.save(sanPham20);

        trans.commit();
        session.close();
    }
}
