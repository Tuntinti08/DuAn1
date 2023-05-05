package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TpPhieuNhapChiTietCustom;
import domainModels.ChiTietPhieuNhap;
import domainModels.ChiTietSanPham;
import domainModels.PhieuNhap;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

/**
 *
 * @author Acer
 */
public class TpPhieuNhapChiTietRepository {

    public List<TpPhieuNhapChiTietCustom> getListCTPhieuNhapByID(UUID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.truongPhongs.customModels.TpPhieuNhapChiTietCustom("
                + "ctpx.soLuong as soLuong,"
                + "ctpx.idChiTietSp as idChiTietSp,"
                + "ctpx.idPhieuNhap as idPhieuNhap"
                + ") FROM domainModels.ChiTietPhieuNhap ctpx WHERE ctpx.idPhieuNhap.id =:id");
        query.setParameter("id", id);
        List<TpPhieuNhapChiTietCustom> list = query.getResultList();
        return list;
    }

    public ChiTietPhieuNhap addPhieuNhap(ChiTietPhieuNhap pn) {
        Transaction tran = null;
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            tran = s.beginTransaction();
            s.save(pn);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
        }
        return pn;
    }

    public boolean upDatePhieuNhap(ChiTietPhieuNhap pn) {
        Transaction tran = null;
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            tran = s.beginTransaction();
            s.save(pn);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public void upDatePN(ChiTietPhieuNhap pn) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tran = s.beginTransaction();
            s.update(pn);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PhieuNhap findPnById(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        PhieuNhap pn = s.find(PhieuNhap.class, id);
        s.close();
        return pn;
    }

    public ChiTietSanPham findSpById(UUID id) {

        Session s = HibernateUtil.getSessionFactory().openSession();
        ChiTietSanPham pn = s.find(ChiTietSanPham.class, id);
        s.close();
        return pn;
    }

    public boolean addCTPN(ChiTietPhieuNhap ctpx) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trans = s.beginTransaction();
            s.save(ctpx);
            trans.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
            return false;
        }
        return true;
    }
}
