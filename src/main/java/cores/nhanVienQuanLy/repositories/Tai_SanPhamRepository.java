/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.nhanVienQuanLy.repositories;

import cores.truongPhongs.customModels.TpQuanLyChiTietSanPhamCustom;
import cores.truongPhongs.customModels.TpQuanLyDonViCustom;
import cores.truongPhongs.customModels.TpQuanLySanPhamCustom;
import domainModels.ChiTietSanPham;
import domainModels.DonVi;
import domainModels.SanPham;
import infrastructures.constant.MauConstant;
import java.math.BigDecimal;
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
 * @author admin
 */
public class Tai_SanPhamRepository {

    public List<TpQuanLySanPhamCustom> getAll(String ten) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("SELECT new cores.truongPhongs.customModels.TpQuanLySanPhamCustom("
                + "sp.id as,"
                + " sp.ma as ma,"
                + " sp.ten as ten)"
                + "FROM SanPham sp WHERE sp.ten LIKE CONCAT('%',:ten,'%') OR sp.ma LIKE CONCAT('%',:ten,'%')\n"
                + "GROUP BY sp.id, sp.ma, sp.ten");
        query.setParameter("ten", ten);
        List<TpQuanLySanPhamCustom> list = query.getResultList();
        s.close();
        return list;
    }

    public SanPham addSanPham(SanPham sp) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tran = null;
            tran = s.beginTransaction();
            s.save(sp);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
            return null;
        }
        return sp;
    }

    public boolean updateSanPham(SanPham sp) {
        Transaction tran = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            s.update(sp);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public boolean deleteSanPham(UUID id) {
        Transaction tran = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            SanPham cs = s.find(SanPham.class, id);
            s.delete(cs);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public TpQuanLySanPhamCustom findByMa(String ma) {
        TpQuanLySanPhamCustom sp = new TpQuanLySanPhamCustom();
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLySanPhamCustom ("
                    + "sp.id as id,"
                    + "sp.ma as ma,"
                    + "sp.ten as ten"
                    + ") from domainModels.SanPham sp WHERE sp.ma = :ma");
            q.setParameter("ma", ma);
            sp = (TpQuanLySanPhamCustom) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        return sp;
    }

    // cách tìm kiếm thứ 2
    public List<TpQuanLySanPhamCustom> findAllByMa(String ma) {
        List<TpQuanLySanPhamCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLySanPhamCustom ("
                + "sp.id as id,"
                + "sp.ma as ma,"
                + "sp.ten as ten"
                + ") from domainModels.SanPham sp WHERE sp.ma LIKE CONCAT('%',:ma,'%') ");
        q.setParameter("ma", ma);
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TpQuanLySanPhamCustom> findAllByTen(String ten) {
        List<TpQuanLySanPhamCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLySanPhamCustom ("
                + "sp.id as id,"
                + "sp.ma as ma,"
                + "sp.ten as ten"
                + ") from domainModels.SanPham sp WHERE sp.ten LIKE CONCAT('%',:ten,'%') ");
        q.setParameter("ten", ten);
        list = q.getResultList();
        s.close();
        return list;
    }

}
