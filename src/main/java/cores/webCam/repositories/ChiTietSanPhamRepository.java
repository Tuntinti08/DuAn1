/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.webCam.repositories;

import cores.webCam.customModels.ChiTietSanPhamCustom;
import domainModels.ChiTietPhieuXuat;
import domainModels.ChiTietSanPham;
import domainModels.PhieuXuat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.HibernateUtil;

/**
 *
 * @author QUOC HUY
 */
public class ChiTietSanPhamRepository {
    public List<ChiTietSanPham> getListChiTietSanPham() {
        List<ChiTietSanPham> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createNativeQuery("SELECT * FROM ChiTietSanPham", ChiTietSanPham.class);
        list = q.getResultList();
        return list;
    }
    
    public static void main(String[] args) {
        List<ChiTietSanPham> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createNativeQuery("SELECT * FROM ChiTietSanPham", ChiTietSanPham.class);
        list = q.getResultList();
        list.forEach(el -> {
            System.out.println(el.getId());
        });
        System.out.println(list.size());
    }
    
    public boolean addChiTietPhieuXuat(UUID idPhieuXuat, UUID idCtsp, int sl) {
        Transaction tran = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            PhieuXuat px = s.find(PhieuXuat.class, idPhieuXuat);
            ChiTietSanPham ctsp = s.find(ChiTietSanPham.class, idCtsp);
            ctsp.setSoLuongTon(ctsp.getSoLuongTon() - sl);
            s.update(ctsp);
            ChiTietPhieuXuat ctpx = new ChiTietPhieuXuat(px, ctsp, sl);
            s.save(ctpx);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
