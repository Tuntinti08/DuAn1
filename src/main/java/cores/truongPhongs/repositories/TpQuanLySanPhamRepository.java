package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TpQuanLySanPhamCustom;
import domainModels.SanPham;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

/**
 *
 * @author MMC
 */
public class TpQuanLySanPhamRepository {

    public List<TpQuanLySanPhamCustom> getAll(String ten) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createNativeQuery("""
                                                  SELECT sp.id, sp.Ma, sp.Ten,
                                                  (
                                          SELECT MAX(ctsp.GiaBan) FROM ChiTietSanPham ctsp 
                                          WHERE ctsp.IdSanPham = sp.Id
                                          ) AS maxGiaBan , 
                                          (
                                        SELECT MIN(ctsp.GiaBan) FROM ChiTietSanPham ctsp 
                                        WHERE ctsp.IdSanPham = sp.Id
                                        ) AS minGiaBan , 
                                          (
                                        SELECT MAX(ctsp.GiaNhap) FROM ChiTietSanPham ctsp 
                                        WHERE ctsp.IdSanPham = sp.Id
                                        ) AS maxGiaNhap , 
                                          (
                                        SELECT MIN(ctsp.GiaNhap) FROM ChiTietSanPham ctsp 
                                        WHERE ctsp.IdSanPham = sp.Id
                                        ) AS minGiaNhap , 
                                          (
                                            SELECT SUM(ctsp.SoLuongTon) FROM ChiTietSanPham ctsp 
                                            WHERE ctsp.IdSanPham = sp.Id
                                            ) AS soLuongTon 
                                                  FROM SanPham sp 
                                                  WHERE sp.Ten LIKE CONCAT('%',:ten,'%') OR sp.Ma LIKE CONCAT('%',:ten,'%')
                                                  GROUP BY sp.Id, sp.Ma, sp.Ten
                                                  """);
        query.setParameter("ten", ten);
        List<Object[]> listQuery = query.getResultList();
        List<TpQuanLySanPhamCustom> list = new ArrayList<>();
        listQuery.stream().forEach(el -> {
            list.add(new TpQuanLySanPhamCustom(UUID.fromString((String) el[0]), (String) el[1], (String) el[2], (BigDecimal) el[3], (BigDecimal) el[4], (BigDecimal) el[5], (BigDecimal) el[6], (Integer) el[7]));
        });
        s.close();
        return list;
    }
    
    public List<TpQuanLySanPhamCustom> getFindByMaSpNcc(String maSpNcc) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createNativeQuery("""
                                                  SELECT sp.id, sp.Ma, sp.Ten,
                                                  (
                                          SELECT MAX(ctsp.GiaBan) FROM ChiTietSanPham ctsp 
                                          WHERE ctsp.IdSanPham = sp.Id
                                          ) AS maxGiaBan , 
                                          (
                                        SELECT MIN(ctsp.GiaBan) FROM ChiTietSanPham ctsp 
                                        WHERE ctsp.IdSanPham = sp.Id
                                        ) AS minGiaBan , 
                                          (
                                        SELECT MAX(ctsp.GiaNhap) FROM ChiTietSanPham ctsp 
                                        WHERE ctsp.IdSanPham = sp.Id
                                        ) AS maxGiaNhap , 
                                          (
                                        SELECT MIN(ctsp.GiaNhap) FROM ChiTietSanPham ctsp 
                                        WHERE ctsp.IdSanPham = sp.Id
                                        ) AS minGiaNhap , 
                                          (
                                            SELECT SUM(ctsp.SoLuongTon) FROM ChiTietSanPham ctsp 
                                            WHERE ctsp.IdSanPham = sp.Id
                                            ) AS soLuongTon 
                                                  FROM SanPham sp join ChiTietSanPham ctsp on sp.Id = ctsp.IdSanPham
                                                  				join ChiTietPhieuNhap ctpn on ctsp.Id = ctpn.IdChiTietSp
                                                  WHERE ctpn.MaSanPhamNhaCungCap LIKE CONCAT('%',:maSpNcc,'%')
                                                  GROUP BY sp.Id, sp.Ma, sp.Ten
                                                  """);
        query.setParameter("maSpNcc", maSpNcc);
        List<Object[]> listQuery = query.getResultList();
        List<TpQuanLySanPhamCustom> list = new ArrayList<>();
        listQuery.stream().forEach(el -> {
            list.add(new TpQuanLySanPhamCustom(UUID.fromString((String) el[0]), (String) el[1], (String) el[2], (BigDecimal) el[3], (BigDecimal) el[4], (BigDecimal) el[5], (BigDecimal) el[6], (Integer) el[7]));
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
            Query q = s.createNativeQuery("""
                                          UPDATE SanPham 
                                          SET Ten = :ten
                                          WHERE Id = :id
                                          """).setParameter("ten", sp.getTen()).setParameter("id", sp.getId().toString());
            q.executeUpdate();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public boolean deleteSanPham(UUID id) {
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createNativeQuery("""
                                          DELETE SanPham 
                                          WHERE Id = :id
                                          """).setParameter("id", id.toString());
            if(q.executeUpdate() <= 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
