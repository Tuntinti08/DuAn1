package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtspCustom;
import domainModels.ChiTietSanPham;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.HibernateUtil;

/**
 *
 * @author window
 */
public class NvqlLuongKiemKeCtspRepository {

    public List<NvqlLuongKiemKeCtspCustom> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtspCustom("
                + " m.id,"
                + " m.sanPham.ma as ma,"
                + " m.sanPham.ten as ten,"
                + " m.soLuongTon as soLuongTon,"
                + " m.donVi as donVi,"
                + " m.mau as mau,"
                + " m.namBaoHanh as namBaoHanh,"
                + " m.GiaBan as giaBan,"
                + " m.GiaNhap as giaNhap,"
                + " m.size as size, "
                + " m.ngayTao as ngayTao,"
                + " m.trangThai as trangThai) "
                + " from domainModels.ChiTietSanPham m "
                + " ORDER BY m.soLuongTon DESC");
        List<NvqlLuongKiemKeCtspCustom> list = query.getResultList();
        session.close();
        return list;
    }

    public void updateSoLuong(ChiTietSanPham nvqlLuongKiemKeCtspCustom) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trans = s.beginTransaction();
            ChiTietSanPham c = s.find(ChiTietSanPham.class, nvqlLuongKiemKeCtspCustom.getId());
            c.setSoLuongTon(nvqlLuongKiemKeCtspCustom.getSoLuongTon());
            c.setTrangThai(TrangThaiSanPhamConstanst.CHO_XAC_NHAN);
            s.update(c);
            trans.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
        }
    }

    public void updateTrangThaiSp(ChiTietSanPham nvqlLuongKiemKeCtspCustom) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trans = s.beginTransaction();
            ChiTietSanPham c = s.find(ChiTietSanPham.class, nvqlLuongKiemKeCtspCustom.getId());
            c.setTrangThai(TrangThaiSanPhamConstanst.DA_MO_BAN);
            s.update(c);
            trans.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
        }
    }

    public List<NvqlLuongKiemKeCtspCustom> getListByMaSp(String ma) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtspCustom("
                + " m.id,"
                + " m.sanPham.ma as ma,"
                + " m.sanPham.ten as ten,"
                + " m.soLuongTon as soLuongTon,"
                + " m.donVi as donVi,"
                + " m.mau as mau,"
                + " m.namBaoHanh as namBaoHanh,"
                + " m.GiaBan as giaBan,"
                + " m.GiaNhap as giaNhap,"
                + " m.size as size, "
                + " m.ngayTao as ngayTao,"
                + " m.trangThai as trangThai) "
                + " from domainModels.ChiTietSanPham m "
                + " WHERE m.trangThai = :trangThai  and  m.sanPham.ma like CONCAT('%',:ma,'%') "
                + "order by m.soLuongTon DESC");
        query.setParameter("trangThai", TrangThaiSanPhamConstanst.DA_MO_BAN);
        query.setParameter("ma", ma);
        List<NvqlLuongKiemKeCtspCustom> list = query.getResultList();
        return list;

    }

    public List<NvqlLuongKiemKeCtspCustom> getListByTenSp(String ma) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtspCustom("
                + " m.id,"
                + " m.sanPham.ma as ma,"
                + " m.sanPham.ten as ten,"
                + " m.soLuongTon as soLuongTon,"
                + " m.donVi as donVi,"
                + " m.mau as mau,"
                + " m.namBaoHanh as namBaoHanh,"
                + " m.GiaBan as giaBan,"
                + " m.GiaNhap as giaNhap,"
                + " m.size as size, "
                + " m.ngayTao as ngayTao,"
                + " m.trangThai as trangThai) "
                + " from domainModels.ChiTietSanPham m "
                + " WHERE m.trangThai = :trangThai  and  m.sanPham.ten like CONCAT('%',:ma,'%') "
                + "order by m.soLuongTon DESC");
        query.setParameter("trangThai", TrangThaiSanPhamConstanst.DA_MO_BAN);
        query.setParameter("ma", ma);
        List<NvqlLuongKiemKeCtspCustom> list = query.getResultList();
        return list;

    }

    public List<NvqlLuongKiemKeCtspCustom> getListByGiaBan(BigDecimal giaBatDau, BigDecimal giaKetThuc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtspCustom("
                + " m.id,"
                + " m.sanPham.ma as ma,"
                + " m.sanPham.ten as ten,"
                + " m.soLuongTon as soLuongTon,"
                + " m.donVi as donVi,"
                + " m.mau as mau,"
                + " m.namBaoHanh as namBaoHanh,"
                + " m.GiaBan as giaBan,"
                + " m.GiaNhap as giaNhap,"
                + " m.size as size, "
                + " m.ngayTao as ngayTao,"
                + " m.trangThai as trangThai) "
                + " from domainModels.ChiTietSanPham m "
                + " WHERE m.trangThai = :trangThai  and  m.GiaBan >= :giaBatDau AND m.GiaBan <= :giaKetThuc "
                + " order by m.soLuongTon DESC");
          query.setParameter("trangThai", TrangThaiSanPhamConstanst.DA_MO_BAN);
        query.setParameter("giaBatDau", giaBatDau);
        query.setParameter("giaKetThuc", giaKetThuc);
        List<NvqlLuongKiemKeCtspCustom> list = query.getResultList();
        return list;

    }

}
