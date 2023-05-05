package cores.khachHangs.repositories;

import cores.khachHangs.customModels.KhXemThongTinCaNhanCustom;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

/**
 *
 * @author window
 */
public class KhXemThongTinCaNhanRepository {

    public List<KhXemThongTinCaNhanCustom> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query query = session.createQuery("select "
                + " new cores.khachHangs.customModels.KhXemThongTinCaNhanCustom("
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
                + " from domainModels.KhachHang m where m.id = '6BFD5C71-97CF-D241-B418-519239F9212E' ");
//        query.setParameter("id", id);
        List<KhXemThongTinCaNhanCustom> list = query.getResultList();
        session.close();
        return list;
    }
    
    public KhXemThongTinCaNhanCustom findByMatKhau(String matKhau) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query query = session.createQuery("select "
                + " new cores.khachHangs.customModels.KhXemThongTinCaNhanCustom("
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
                + " from domainModels.KhachHang m where m.id = '6BFD5C71-97CF-D241-B418-519239F9212E' AND m.matKhau = :matKhau ");
        query.setParameter("matKhau", matKhau);
        KhXemThongTinCaNhanCustom kh = null;
        try {
            kh = (KhXemThongTinCaNhanCustom) query.getSingleResult();
        } catch (Exception e) {
        }
        session.close();
        if(kh == null){
            return null;
        }
        return kh;
    }
    
     public boolean checkMatKhau(String matKhau){
        KhXemThongTinCaNhanCustom ql = findByMatKhau(matKhau);
        if(ql != null){
            return true;
        }
        return false;
    }


    public void doiMatKhau(String matKhau) {
        Transaction transaction = null;
        String hql = "UPDATE KhachHang m SET MatKhau = :matKhau where m.id = '6BFD5C71-97CF-D241-B418-519239F9212E'";
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            transaction = session.beginTransaction();
            query.setParameter("matKhau", matKhau);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
    
}
