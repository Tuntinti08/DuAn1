/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.ThongKe_SanPham_Custom;
import domainModels.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.HibernateUtil;

/**
 *
 * @author asus
 */
public class ThongKe_SanPham_Repository {

    public static List<ThongKe_SanPham_Custom> getABC(Long ngaybd, Long ngaykt) {
        List<ThongKe_SanPham_Custom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createNativeQuery("""                                           
                                      select sp.Id, sp.ma, sp.ten,sum(ctpx.soluong) as SL, px.ngayThanhToan from sanpham sp
                                                                            join chiTietSanPham ctsp on ctsp.idSanPham = sp.id
                                                                            join chitietPhieuXuat ctpx on ctsp.id = ctpx.IdChiTietSP
                                                                            join phieuXuat px  on px.id = ctpx.idphieuXuat
                                                                            where px.NgayThanhToan  >= :giaBatDau AND  px.NgayThanhToan <=  :giaKetThuc
                                                                            group by sp.id, sp.ma, sp.ten,px.NgayThanhToan
                                                                            order by sum(ctpx.soluong) Desc                                         
                                      """, SanPham.class);
        q.setParameter("giaBatDau", ngaybd);
        q.setParameter("giaKetThuc", ngaykt);
        list = q.getResultList();
        s.close();
        System.out.println(list.size());
        return list;
    }

    public static List<ThongKe_SanPham_Custom> getXYZ(Long ngaybd, Long ngaykt) {
        List<ThongKe_SanPham_Custom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createNativeQuery("""                                           
                                      select sp.Id, sp.ma, sp.ten,sum(ctpn.soluong) as SL, pn.ngayThanhToan from sanpham sp
                                                                            join chiTietSanPham ctsp on ctsp.idSanPham = sp.id
                                                                            join ChiTietPhieuNhap ctpn on ctsp.id = ctpn.IdChiTietSP
                                                                            join PhieuNhap pn  on pn.id = ctpn.IdPhieuNhap
                                                                            where pn.NgayThanhToan  >= :giaBatDau AND  pn.NgayThanhToan <=  :giaKetThuc
                                                                            group by sp.id, sp.ma, sp.ten,pn.NgayThanhToan
                                                                            order by sum(ctpn.soluong) Desc                                
                                      """, SanPham.class);
        q.setParameter("giaBatDau", ngaybd);
        q.setParameter("giaKetThuc", ngaykt);
        list = q.getResultList();
        s.close();
        System.out.println(list.size());
        return list;
    }
}
