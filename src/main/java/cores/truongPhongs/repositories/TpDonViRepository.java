package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TpDonViCustom;
import domainModels.DonVi;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

/**
 *
 * @author LENOVO
 */
public class TpDonViRepository {

    public List<TpDonViCustom> getList() {
        List<TpDonViCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpDonViCustom ("
                + " dv.id as id, "
                + " dv.donViGoc as donViGoc, "
                + " dv.donViQuyDoi as donViQuyDoi, "
                + " dv.soLuong as soLuong "
                + " ) FROM domainModels.DonVi dv"
        );
        list = q.getResultList();
        s.close();
        return list;

    }

    public DonVi addDonVi(DonVi dv) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tran = null;
            tran = s.beginTransaction();
            s.save(dv);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
            return null;
        }
        return dv;
    }

    public boolean updateDonVi(DonVi dv) {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            s.update(dv);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public boolean deleteDonVi(UUID id) {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            DonVi dv = s.find(DonVi.class, id);
            s.delete(dv);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public TpDonViCustom findByDonViGoc(String donViGoc) {
        TpDonViCustom dvc = new TpDonViCustom();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpDonViCustom ("
                    + " dv.id as id, "
                    + " dv.donViGoc as donViGoc, "
                    + " dv.donViQuyDoi as donViQuyDoi, "
                    + " dv.soLuong as soLuong "
                    + ") FROM domainModels.DonVi dv WHERE dv.donViGoc = :donViGoc");
            q.setParameter("donViGoc", donViGoc);
            dvc = (TpDonViCustom) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        return dvc;
    }

    public List<TpDonViCustom> findAllByDonViGoc(String donViGoc) {
        List<TpDonViCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpDonViCustom ("
                + " dv.id as id, "
                + " dv.donViGoc as donViGoc, "
                + " dv.donViQuyDoi as donViQuyDoi, "
                + " dv.soLuong as soLuong "
                + " ) FROM domainModels.DonVi dv WHERE dv.donViGoc LIKE CONCAT('%',:donViGoc,'%')"
        );
        q.setParameter("donViGoc", donViGoc);

        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TpDonViCustom> findAllByDonViQuyDoi(String donViQuyDoi) {
        List<TpDonViCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpDonViCustom ("
                + " dv.id as id, "
                + " dv.donViGoc as donViGoc, "
                + " dv.donViQuyDoi as donViQuyDoi, "
                + " dv.soLuong as soLuong "
                + " ) FROM domainModels.DonVi dv WHERE dv.donViQuyDoi LIKE CONCAT('%',:donViQuyDoi,'%')"
        );
        q.setParameter("donViQuyDoi", donViQuyDoi);

        list = q.getResultList();
        s.close();
        return list;
    }
}
