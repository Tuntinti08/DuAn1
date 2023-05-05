/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TP_SanPhamCustom;
import cores.truongPhongs.customModels.TpQuanLySanPhamCustom;
import domainModels.SanPham;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.HibernateUtil;

/**
 *
 * @author asus
 */
public class TP_SanPhamRepository {
    public List<TpQuanLySanPhamCustom> getAll(String ten) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createNativeQuery("""
                                                  SELECT sp.id, sp.Ma, sp.Ten,
                                                  MAX(ctsp.GiaBan) as giaBanMax, MIN(ctsp.GiaBan) as giaBanMin,
                                                  MAX(ctsp.GiaNhap) as giaNhapMax, MIN(ctsp.GiaNhap) as giaNhapMin,
                                                  SUM(ctsp.SoLuongTon) as soLuongTon
                                                  FROM SanPham sp join ChiTietSanPham ctsp
                                                  ON sp.Id = ctsp.IdSanPham
                                                  WHERE sp.Ten LIKE CONCAT('%',:ten,'%') OR sp.Ma LIKE CONCAT('%',:ten,'%')
                                                  GROUP BY sp.Id, sp.Ma, sp.Ten
                                                  """);
        query.setParameter("ten", ten);
        List<Object[]> listQuery = query.getResultList();
        List<TpQuanLySanPhamCustom> list = new ArrayList<>();
        listQuery.stream().forEach(el -> {
            list.add(new TpQuanLySanPhamCustom(UUID.fromString((String) el[0]), (String) el[1], (String) el[2], (BigDecimal) el[3], (BigDecimal) el[4], (BigDecimal) el[5], (BigDecimal) el[6], (int) el[7]));
        });
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
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
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
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
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
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            javax.persistence.Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLySanPhamCustom ("
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

    public List<TpQuanLySanPhamCustom> findAllByMa(String ma) {
        List<TpQuanLySanPhamCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLySanPhamCustom ("
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
        javax.persistence.Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLySanPhamCustom ("
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
