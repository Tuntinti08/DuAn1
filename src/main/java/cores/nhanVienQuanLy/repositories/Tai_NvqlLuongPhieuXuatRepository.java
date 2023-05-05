package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.LuongBanHang_ChiTietSanPhamCustom;
import cores.nhanVienQuanLy.customModels.Luong_ChiTietPhieuXuatCustom;
import cores.nhanVienQuanLy.customModels.Tai_SanPhamCustom;
import domainModels.ChiTietPhieuNhap;
import domainModels.ChiTietPhieuXuat;
import domainModels.ChiTietSanPham;
import domainModels.NhanVien;
import domainModels.SanPham;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

/**
 *
 * @author admin
 */
public class Tai_NvqlLuongPhieuXuatRepository {

    public List<LuongBanHang_ChiTietSanPhamCustom> getListCTSanPhamBanHang() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("SELECT new cores.nhanVienQuanLy.customModels.LuongBanHang_ChiTietSanPhamCustom("
                + "ctpn.idChiTietSp.id as id,"
                + "ctpn.idChiTietSp.soLuongTon as soLuongTon,"
                + "ctpn.idChiTietSp.hinhAnh as hinhAnh,"
                + "ctpn.idChiTietSp.GiaNhap as GiaNhap,"
                + "ctpn.idChiTietSp.GiaBan as GiaBan,"
                + "ctpn.idChiTietSp.namBaoHanh as namBaoHanh,"
                + "ctpn.idChiTietSp.mau as mau,"
                + "ctpn.idChiTietSp.trangThai as trangThai,"
                + "ctpn.idChiTietSp.sanPham as sanPham,"
                + "ctpn.idChiTietSp.donVi as donVi, "
                + "ctpn.idChiTietSp.size as size, "
                + "ctpn.idChiTietSp.ngayTao as ngayTao,"
                + "ctpn.idPhieuNhap.nhaCungCap"
                + ")FROM domainModels.ChiTietPhieuNhap ctpn ");
        List<LuongBanHang_ChiTietSanPhamCustom> listCTSP = query.getResultList();
        return listCTSP;
    }

    public List<LuongBanHang_ChiTietSanPhamCustom> getListCTSanPham() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("SELECT new cores.nhanVienQuanLy.customModels.LuongBanHang_ChiTietSanPhamCustom("
                + "ctpn.idChiTietSp.id as id,"
                + "ctpn.idChiTietSp.soLuongTon as soLuongTon,"
                + "ctpn.idChiTietSp.hinhAnh as hinhAnh,"
                + "ctpn.idChiTietSp.GiaNhap as GiaNhap,"
                + "ctpn.idChiTietSp.GiaBan as GiaBan,"
                + "ctpn.idChiTietSp.namBaoHanh as namBaoHanh,"
                + "ctpn.idChiTietSp.mau as mau,"
                + "ctpn.idChiTietSp.trangThai as trangThai,"
                + "ctpn.idChiTietSp.sanPham as sanPham,"
                + "ctpn.idChiTietSp.donVi as donVi, "
                + "ctpn.idChiTietSp.size as size, "
                + "ctpn.idChiTietSp.ngayTao as ngayTao,"
                + "ctpn.idPhieuNhap.nhaCungCap"
                + ")FROM domainModels.ChiTietPhieuNhap ctpn WHERE ctpn.idChiTietSp.trangThai = :trangThai");
        query.setParameter("trangThai", TrangThaiSanPhamConstanst.DA_MO_BAN);
        List<LuongBanHang_ChiTietSanPhamCustom> listCTSP = query.getResultList();
        return listCTSP;
    }

    public List<LuongBanHang_ChiTietSanPhamCustom> getListCTSanPhamByID(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("SELECT new cores.nhanVienQuanLy.customModels.LuongBanHang_ChiTietSanPhamCustom("
                + "ctpn.idChiTietSp.id as id,"
                + "ctpn.idChiTietSp.soLuongTon as soLuongTon,"
                + "ctpn.idChiTietSp.hinhAnh as hinhAnh,"
                + "ctpn.idChiTietSp.GiaNhap as GiaNhap,"
                + "ctpn.idChiTietSp.GiaBan as GiaBan,"
                + "ctpn.idChiTietSp.namBaoHanh as namBaoHanh,"
                + "ctpn.idChiTietSp.mau as mau,"
                + "ctpn.idChiTietSp.trangThai as trangThai,"
                + "ctpn.idChiTietSp.sanPham as sanPham,"
                + "ctpn.idChiTietSp.donVi as donVi, "
                + "ctpn.idChiTietSp.size as size, "
                + "ctpn.idChiTietSp.ngayTao as ngayTao,"
                + "ctpn.idPhieuNhap.nhaCungCap"
                + ")FROM domainModels.ChiTietPhieuNhap ctpn WHERE ctpn.idChiTietSp.sanPham.id =: id AND ctpn.idChiTietSp.trangThai = :trangThai");
        query.setParameter("trangThai", TrangThaiSanPhamConstanst.DA_MO_BAN);
        query.setParameter("id", id);
        List<LuongBanHang_ChiTietSanPhamCustom> listCTSP = query.getResultList();
        return listCTSP;
    }

    public NhanVien getNhanVienByMa(String ma) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("FROM domainModels.NhanVien kh WHERE kh.ma =:ma");
        query.setParameter("ma", ma);
        NhanVien nv = (NhanVien) query.getSingleResult();
        return nv;
    }

    public List<Luong_ChiTietPhieuXuatCustom> getListCTPhieuXuat(UUID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.nhanVienQuanLy.customModels.Luong_ChiTietPhieuXuatCustom("
                + "ctpx.idPhieuXuat as idPhieuXuat,"
                + "ctpx.idChiTietSp as idChiTietSp,"
                + "ctpx.soLuong as soLuong"
                + ") FROM domainModels.ChiTietPhieuXuat ctpx WHERE ctpx.idPhieuXuat.id =:id");
        query.setParameter("id", id);
        List<Luong_ChiTietPhieuXuatCustom> list = query.getResultList();
        return list;
    }

    public List<Luong_ChiTietPhieuXuatCustom> getListCTPhieuXuat() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.nhanVienQuanLy.customModels.Luong_ChiTietPhieuXuatCustom("
                + "ctpx.idPhieuXuat as idPhieuXuat,"
                + "ctpx.idChiTietSp as idChiTietSp,"
                + "ctpx.soLuong as soLuong"
                + ") FROM domainModels.ChiTietPhieuXuat ctpx");
        List<Luong_ChiTietPhieuXuatCustom> list = query.getResultList();
        return list;
    }

    public void addCTPX(ChiTietPhieuXuat ctpx) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trans = s.beginTransaction();
            s.save(ctpx);
            trans.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
//            return false;
        }
//        return true;
    }

    public void updateCTPX(ChiTietPhieuXuat ctpx) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trans = s.beginTransaction();
            s.update(ctpx);
            trans.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
        }
    }

//    public ChiTietSanPham addCTSP(ChiTietSanPham ctsp) {
//        Session s = HibernateUtil.getSessionFactory().openSession();
//        try {
//            Transaction trans = s.beginTransaction();
//            s.save(ctsp);
//            trans.commit();
//            s.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            s.close();
//        }
//        return ctsp;
//    }
    public void updateCTSP(ChiTietSanPham ctsp) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trans = s.beginTransaction();
            s.update(ctsp);
            trans.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
        }
    }

    public ChiTietPhieuNhap findCTpnByID(UUID idCTSP) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery(" FROM domainModels.ChiTietPhieuNhap ctpn WHERE ctpn.idChiTietSp.id =:id");
        query.setParameter("id", idCTSP);
        ChiTietPhieuNhap ctpn = (ChiTietPhieuNhap) query.getSingleResult();
        return ctpn;
    }

    public List<Tai_SanPhamCustom> getListSP() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("SELECT new cores.nhanVienQuanLy.customModels.Tai_SanPhamCustom("
                + "sp.id as id,"
                + "sp.ma as ma,"
                + "sp.ten as ten"
                + ") FROM domainModels.SanPham sp ");
        List<Tai_SanPhamCustom> list = query.getResultList();
        return list;
    }

    public List<Tai_SanPhamCustom> getListSPByMa(String ma) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("SELECT new cores.nhanVienQuanLy.customModels.Tai_SanPhamCustom("
                + "sp.id as id,"
                + "sp.ma as ma,"
                + "sp.ten as ten"
                + ") FROM domainModels.SanPham sp where sp.ma LIKE CONCAT('%',:ma,'%')");
        query.setParameter("ma", ma);
        List<Tai_SanPhamCustom> list = query.getResultList();
        return list;
    }

    public List<Tai_SanPhamCustom> getListSPByTen(String ma) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("SELECT new cores.nhanVienQuanLy.customModels.Tai_SanPhamCustom("
                + "sp.id as id,"
                + "sp.ma as ma,"
                + "sp.ten as ten"
                + ") FROM domainModels.SanPham sp where sp.ten LIKE CONCAT('%',:ma,'%')");
        query.setParameter("ma", ma);
        List<Tai_SanPhamCustom> list = query.getResultList();
        return list;
    }
}
