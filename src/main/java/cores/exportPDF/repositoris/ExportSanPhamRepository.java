package cores.exportPDF.repositoris;

import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtspCustom;
import domainModels.ChiTietPhieuXuat;
import domainModels.ChiTietSanPham;
import domainModels.PhieuXuat;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.HibernateUtil;

/**
 *
 * @author QUOC HUY
 */
public class ExportSanPhamRepository {

    public ChiTietSanPham findChiTietSanPham(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createNativeQuery("""
                                      SELECT * FROM ChiTietSanPham ctsp WHERE ctsp.Id = :Id
                                      """, ChiTietSanPham.class).setParameter("Id", id.toString());
        ChiTietSanPham ctsp = (ChiTietSanPham) q.getSingleResult();
        System.out.println(ctsp.getId());
        s.close();
        return ctsp;
    }
    
    public static void main(String[] args) {
        List<ChiTietSanPham> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createNativeQuery("""
                                      SELECT * FROM ChiTietSanPham ctsp
                                      """, ChiTietSanPham.class);
        list = q.getResultList();
        list.forEach(el -> {
            System.out.println(el.getId());
        });
    }

    public PhieuXuat findPhieuXuat(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        PhieuXuat px = s.find(PhieuXuat.class, id);
        s.close();
        return px;
    }

    public List<ChiTietPhieuXuat> findChiTietSanPhamByIdPhieuXuat(UUID id) {
        List<ChiTietPhieuXuat> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM domainModels.ChiTietPhieuXuat ctsp WHERE ctsp.idPhieuXuat.id = :id");
        q.setParameter("id", id);
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<NvqlLuongKiemKeCtspCustom> getAllSanPhamKiemKe() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        javax.persistence.Query query = session.createQuery("select "
                + " new cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtspCustom("
                + " m.id,"
                + " m.sanPham.ma as ma,"
                + " m.sanPham.ten as ten,"
                + " m.soLuongTon as soLuongTon,"
                + " m.donVi as donVi,"
                + " m.mau as mau,"
                + " m.namBaoHanh as namBaoHanh,"
                + " m.GiaBan as giaBan,"
                + " m.size as size, "
                + " m.ngayTao as ngayTao) "
                + " from domainModels.ChiTietSanPham m "
                + " WHERE m.trangThai = :trangThai "
                + " ORDER BY m.soLuongTon DESC");
        query.setParameter("trangThai", TrangThaiSanPhamConstanst.DA_MO_BAN);
        List<NvqlLuongKiemKeCtspCustom> list = query.getResultList();
        session.close();
        return list;
    }
}
