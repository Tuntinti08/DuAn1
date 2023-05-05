package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.NvqlXemThongTinCaNhanCustom;
import domainModels.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

/**
 *
 * @author window
 */
public class NvqlXemThongTinCaNhanRepository {

    public List<NvqlXemThongTinCaNhanCustom> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlXemThongTinCaNhanCustom("
                + " m.id,"
                + " m.ma as ma,"
                + " m.ten as ten,"
                + " m.sdt as sdt,"
                + " m.email as email,"
                + " m.matKhau as matKhau,"
                + " m.ngaySinh as ngaySinh,"
                + " m.gioiTinh as gioiTinh,"
                + " m.diaChi as diaChi"
                + " )"
                + " from domainModels.NhanVien m");
//        query.setParameter("id", id);
        List<NvqlXemThongTinCaNhanCustom> list = query.getResultList();
        session.close();
        return list;
    }
    
    public NvqlXemThongTinCaNhanCustom getMatKhauByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlXemThongTinCaNhanCustom("
                + " m.id,"
                + " m.ma as ma,"
                + " m.ten as ten,"
                + " m.sdt as sdt,"
                + " m.email as email,"
                + " m.matKhau as matKhau,"
                + " m.ngaySinh as ngaySinh,"
                + " m.gioiTinh as gioiTinh,"
                + " m.diaChi as diaChi"
                + " )"
                + " from domainModels.NhanVien m where m.email = :email");
        query.setParameter("email", email);
        NvqlXemThongTinCaNhanCustom kh = null;
        try {
            kh = (NvqlXemThongTinCaNhanCustom) query.getSingleResult();
        } catch (Exception e) {
        }
        session.close();
        if (kh == null) {
            return null;
        }
        return kh;
    }

    public NvqlXemThongTinCaNhanCustom findByMatKhau(String matKhau) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlXemThongTinCaNhanCustom("
                + " m.id,"
                + " m.ma as ma,"
                + " m.ten as ten,"
                + " m.email as email,"
                + " m.sdt as sdt,"
                + " m.matKhau as matKhau,"
                + " m.ngaySinh as ngaySinh,"
                + " m.gioiTinh as gioiTinh,"
                + " m.diaChi as diaChi"
                + " )"
                + " from domainModels.NhanVien m where m.matKhau = :matKhau ");
        query.setParameter("matKhau", matKhau);
        NvqlXemThongTinCaNhanCustom kh = null;
        try {
            kh = (NvqlXemThongTinCaNhanCustom) query.getSingleResult();
        } catch (Exception e) {
        }
        session.close();
        if (kh == null) {
            return null;
        }
        return kh;
    }

    public boolean checkMatKhau(String matKhau) {
        NvqlXemThongTinCaNhanCustom ql = findByMatKhau(matKhau);
        if (ql != null) {
            return true;
        }
        return false;
    }

    public void doiMatKhau(NhanVien nv) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(nv);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
