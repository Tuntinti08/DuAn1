package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCustom;
import domainModels.PhieuKiemKe;
import infrastructures.constant.TrangThaiPhieuKiemConstant;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.HibernateUtil;

/**
 *
 * @author window
 */
public class NvqlLuongKiemKeRepository {

    public List<NvqlLuongKiemKeCustom> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCustom("
                + " m.id,"
                + " m.ma as maPhieuKiem,"
                + " m.ngayTao as ngayTao,"
                + " m.nhanVien as idNV,"
                + " m.trangThai as trangThai,"
                + " m.ghiChu as ghiChu) "
                + " from domainModels.PhieuKiemKe m ORDER BY m.ngayTao DESC");
        List<NvqlLuongKiemKeCustom> list = query.getResultList();
        session.close();
        return list;
    }

    public void them(PhieuKiemKe phieuKiemKe) {
        Transaction t = null;
        String check;
        try ( Session session = new HibernateUtil().getSessionFactory().openSession();) {
            t = session.beginTransaction();
            session.save(phieuKiemKe);
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
            t.rollback(); //hoàn lại kết quả
        }
    }

    public boolean updateTrangThai(PhieuKiemKe phieuKiemKe) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = s.beginTransaction();
            s.update(phieuKiemKe);
            Query q = s.createNativeQuery("""
            UPDATE chitietsanpham
            SET TrangThai = :trangThai 
            WHERE Id IN 
            (SELECT IdChiTietSP FROM chitietphieukiemke WHERE idphieukiemke = :idPhieuKiem)
                                          """);
            q.setParameter("trangThai", 1);
            q.setParameter("idPhieuKiem", phieuKiemKe.getId());
            q.executeUpdate();
            transaction.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
            return false;
        }
        return true;
    }

    public List<NvqlLuongKiemKeCustom> getListByNgayTao(Long ngayBatDau, Long ngayKetThuc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCustom("
                + " m.id,"
                + " m.ma as maPhieuKiem,"
                + " m.ngayTao as ngayTao,"
                + " m.nhanVien as idNV,"
                + " m.trangThai as trangThai,"
                + " m.ghiChu as ghiChu) "
                + " from domainModels.PhieuKiemKe m WHERE m.ngayTao > :ngayBatDau AND m.ngayTao < :ngayKetThuc  ORDER BY m.ngayTao DESC");
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        List<NvqlLuongKiemKeCustom> list = query.getResultList();
        return list;
    }
      public List<NvqlLuongKiemKeCustom> getListByMa(String ma, TrangThaiPhieuKiemConstant tt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCustom("
                + " m.id,"
                + " m.ma as maPhieuKiem,"
                + " m.ngayTao as ngayTao,"
                + " m.nhanVien as idNV,"
                + " m.trangThai as trangThai,"
                + " m.ghiChu as ghiChu) "
                + " from domainModels.PhieuKiemKe m WHERE m.ma like CONCAT('%',:ma,'%') and m.trangThai = :tt "
                + "order by m.ngayTao DESC" );
       query.setParameter("ma", ma);
       query.setParameter("tt", tt);
        List<NvqlLuongKiemKeCustom> list = query.getResultList();
        return list;
        
    }
         public List<NvqlLuongKiemKeCustom> getListByTenNv(String ma, TrangThaiPhieuKiemConstant tt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCustom("
                + " m.id,"
                + " m.ma as maPhieuKiem,"
                + " m.ngayTao as ngayTao,"
                + " m.nhanVien as idNV,"
                + " m.trangThai as trangThai, "
                + " m.ghiChu as ghiChu) "
                + " from domainModels.PhieuKiemKe m WHERE m.nhanVien.ten like CONCAT('%',:ma,'%') and m.trangThai = :tt "
                + "order by m.ngayTao DESC" );
       query.setParameter("ma", ma);
       query.setParameter("tt", tt);
        List<NvqlLuongKiemKeCustom> list = query.getResultList();
        return list;
        
    }

}
