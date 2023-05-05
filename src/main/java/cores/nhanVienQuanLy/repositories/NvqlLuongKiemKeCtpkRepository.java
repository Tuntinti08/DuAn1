package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtpkCustom;
import domainModels.ChiTietPhieuKiemKe;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

/**
 *
 * @author window
 */
public class NvqlLuongKiemKeCtpkRepository {

    public List<NvqlLuongKiemKeCtpkCustom> getAll(UUID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtpkCustom("
                + " m.idChiTietSp.sanPham.ma as ma,"
                + " m.idChiTietSp.sanPham.ten as ten,"
                + " m.soLuongTon as soLuongTon,"
                + " m.soLuongThucTon as soLuongThucTon,"
                + " m.idPhieuKiemKe as idPhieuKiemKe,"
                + " m.idChiTietSp as idChiTietSanPham,"
                + " m.liDo"
                + " )"
                + " from domainModels.ChiTietPhieuKiemKe m WHERE m.idPhieuKiemKe.id = :id");
        query.setParameter("id", id);
        List<NvqlLuongKiemKeCtpkCustom> list = query.getResultList();
        session.close();
        return list;
    }

    public void addCTPK(ChiTietPhieuKiemKe ctpx) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trans = s.beginTransaction();
            s.saveOrUpdate(ctpx);
            trans.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
        }
    }

    public void updateCTPKK(ChiTietPhieuKiemKe ctpx) {
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
    
    public boolean updateSoLuongTon(UUID id, int sl) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = s.beginTransaction();
            Query q = s.createNativeQuery("""
            UPDATE chitietsanpham
            SET SoLuongTon = :soLuongTon 
            WHERE id = :idChiTietSP
            """);
            q.setParameter("soLuongTon", sl);
            q.setParameter("idChiTietSP", id);
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
}
