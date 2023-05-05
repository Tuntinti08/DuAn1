package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TP_ChucVuCustom;
import domainModels.ChucVu;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

public class TP_ChucVuRepository {

    public List<TP_ChucVuCustom> getList() {
        List<TP_ChucVuCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_ChucVuCustom ("
                + "cv.id as id, "
                + "cv.ma as ma, "
                + "cv.ten as ten"
                + ") FROM domainModels.ChucVu cv ");
        list = q.getResultList();
        s.close();
        return list;
    }

    public ChucVu addChucVu(ChucVu cv) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tran = null;
            tran = s.beginTransaction();
            s.save(cv);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
            return null;
        }
        return cv;
    }

    public boolean updateChucVu(ChucVu cv) {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            s.update(cv);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public boolean deleteChucVu(UUID id) {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            ChucVu cv = s.find(ChucVu.class, id);
            System.out.println(id);
            s.delete(cv);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public TP_ChucVuCustom findByMa(String ma) {
        TP_ChucVuCustom cv = new TP_ChucVuCustom();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_ChucVuCustom ("
                    + "cv.id as id, "
                    + "cv.ma as ma, "
                    + "cv.ten as ten"
                    + ") FROM domainModels.ChucVu cv WHERE cv.ma = :ma");
            q.setParameter("ma", ma);
            cv = (TP_ChucVuCustom) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        return cv;
    }

    public List<TP_ChucVuCustom> findAllByMa(String ma) {
        List<TP_ChucVuCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_ChucVuCustom ("
                + "cv.id as id, "
                + "cv.ma as ma, "
                + "cv.ten as ten"
                + ") FROM domainModels.ChucVu cv WHERE cv.ma LIKE CONCAT('%',:ma,'%') ");
        q.setParameter("ma", ma);
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TP_ChucVuCustom> findAllByTen(String ten) {
        List<TP_ChucVuCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_ChucVuCustom ("
                + "cv.id as id, "
                + "cv.ma as ma, "
                + "cv.ten as ten"
                + ") FROM domainModels.ChucVu cv WHERE cv.ten LIKE CONCAT('%',:ten,'%') ");
        q.setParameter("ten", ten);
        list = q.getResultList();
        s.close();
        return list;
    }

}
