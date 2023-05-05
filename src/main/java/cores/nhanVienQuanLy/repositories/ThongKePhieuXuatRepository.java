/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.ThongKePhieuXuat_NhapCustom;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

/**
 *
 * @author MMC
 */
public class ThongKePhieuXuatRepository {

    public List<ThongKePhieuXuat_NhapCustom> thongKeTienThanhToanMotNgay(Long ngayBD, Long ngayKT) {
        Transaction tran = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        tran = s.beginTransaction();
        javax.persistence.Query query = s.createNativeQuery("select SUM(ctpx.SoLuong * ctsp.GiaBan) AS dongia from SanPham sp\n"
                + "join ChiTietSanPham ctsp on ctsp.IdSanPham = sp.Id\n"
                + "join ChiTietPhieuXuat ctpx on ctsp.Id = ctpx.IdChiTietSP\n"
                + "join PhieuXuat px on px.Id = ctpx.IdPhieuXuat\n"
                + "where px.NgayThanhToan >= giaBatDau AND px.NgayThanhToan <= giaKetThuc\n"
                + "order by SUM(ctpx.SoLuong * ctsp.GiaBan) DESC");

        query.setParameter("giaBatDau", ngayBD);
        query.setParameter("giaKetThuc", ngayKT);
        List<ThongKePhieuXuat_NhapCustom> tkc = query.getResultList();
        s.close();
        return tkc;
    }

    public List<ThongKePhieuXuat_NhapCustom> thongKeTienThanhToanMotNgayPhieuNhap(Long ngayBD, Long ngayKT) {
        Transaction tran = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        tran = s.beginTransaction();
        javax.persistence.Query query = s.createNativeQuery("select SUM(ctpn.SoLuong * ctsp.GiaBan) AS dongia from SanPham sp\n"
                + "join ChiTietSanPham ctsp on ctsp.IdSanPham = sp.Id\n"
                + "join ChiTietPhieuNhap ctpn on ctsp.Id = ctpn.IdChiTietSP\n"
                + "join PhieuNhap pn on pn.Id = ctpn.IdPhieuNhap\n"
                + "where pn.NgayThanhToan >= giaBatDau AND pn.NgayThanhToan <= giaKetThuc\n"
                + "order by SUM(ctpn.SoLuong * ctsp.GiaBan) DESC");

        query.setParameter("giaBatDau", ngayBD);
        query.setParameter("giaKetThuc", ngayKT);
        List<ThongKePhieuXuat_NhapCustom> tkc = query.getResultList();
        s.close();
        return tkc;
    }
}
