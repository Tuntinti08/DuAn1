package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TP_HoanNhap_PhieuNhapCustom;
import cores.truongPhongs.customModels.TP_HoanNhap_ctpCusTom;
import cores.truongPhongs.customModels.TP_HoanNhap_spCustom;
import cores.truongPhongs.customModels.TP_PhieuHoanNhapCustom;
import domainModels.ChiTietPhieuHoanNhap;
import domainModels.ChiTietPhieuHoanNhapId;
import domainModels.ChiTietSanPham;
import domainModels.PhieuHoanNhap;
import domainModels.PhieuNhap;
import infrastructures.constant.TrangThaiPhieuHoanConstant;
import infrastructures.constant.TrangThaiPhieuNhapConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.HibernateUtil;

/**
 *
 * @author QUOC HUY
 */
public class TP_PhieuHoanNhapRepository {

    public List<TP_HoanNhap_PhieuNhapCustom> getListPhieuNhap() {
        List<TP_HoanNhap_PhieuNhapCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_PhieuNhapCustom ("
                + " pn.id as id, "
                + " pn.maPhieu as maPhieu, "
                + " pn.ngayTao as ngayTao, "
                + " pn.ghiChu as ghiChu, "
                + " pn.ngayThanhToan as ngayThanhToan, "
                + " pn.trangThai as trangThai, "
                + " pn.nhanVien as nhanhVien, "
                + " pn.nhaCungCap as nhaCungCap"
                + ") FROM domainModels.PhieuNhap pn WHERE pn.ngayThanhToan IS NULL");
        list = q.getResultList();
        return list;
    }
    public List<TP_HoanNhap_PhieuNhapCustom> findAllPnByMaPhieu(String ma) {
        List<TP_HoanNhap_PhieuNhapCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_PhieuNhapCustom ("
                + " pn.id as id, "
                + " pn.maPhieu as maPhieu, "
                + " pn.ngayTao as ngayTao, "
                + " pn.ghiChu as ghiChu, "
                + " pn.ngayThanhToan as ngayThanhToan, "
                + " pn.trangThai as trangThai, "
                + " pn.nhanVien as nhanhVien, "
                + " pn.nhaCungCap as nhaCungCap"
                + ") FROM domainModels.PhieuNhap pn WHERE pn.ngayThanhToan IS NULL  "
                + "and pn.maPhieu like CONCAT('%',:ma,'%')  ");
        q.setParameter("ma", ma);
        list = q.getResultList();
        return list;
    }
    public List<TP_HoanNhap_PhieuNhapCustom> findAllPnByNcc(String ma) {
        List<TP_HoanNhap_PhieuNhapCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_PhieuNhapCustom ("
                + " pn.id as id, "
                + " pn.maPhieu as maPhieu, "
                + " pn.ngayTao as ngayTao, "
                + " pn.ghiChu as ghiChu, "
                + " pn.ngayThanhToan as ngayThanhToan, "
                + " pn.trangThai as trangThai, "
                + " pn.nhanVien as nhanhVien, "
                + " pn.nhaCungCap as nhaCungCap"
                + ") FROM domainModels.PhieuNhap pn WHERE pn.ngayThanhToan IS NULL  "
                + "and pn.nhaCungCap.ten like CONCAT('%',:ma,'%')  ");
        q.setParameter("ma", ma);
        list = q.getResultList();
        return list;
    }
      public List<TP_HoanNhap_PhieuNhapCustom> findAllPnByNv(String ma) {
        List<TP_HoanNhap_PhieuNhapCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_PhieuNhapCustom ("
                + " pn.id as id, "
                + " pn.maPhieu as maPhieu, "
                + " pn.ngayTao as ngayTao, "
                + " pn.ghiChu as ghiChu, "
                + " pn.ngayThanhToan as ngayThanhToan, "
                + " pn.trangThai as trangThai, "
                + " pn.nhanVien as nhanhVien, "
                + " pn.nhaCungCap as nhaCungCap"
                + ") FROM domainModels.PhieuNhap pn WHERE pn.ngayThanhToan IS NULL  "
                + "and pn.nhanVien.ten like CONCAT('%',:ma,'%') ");
        q.setParameter("ma", ma);
        list = q.getResultList();
        return list;
    }
      public List<TP_HoanNhap_PhieuNhapCustom> findAllPnNgayTao(Long ngayBatDau, Long ngayKetThuc) {
        List<TP_HoanNhap_PhieuNhapCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_PhieuNhapCustom ("
                + " pn.id as id, "
                + " pn.maPhieu as maPhieu, "
                + " pn.ngayTao as ngayTao, "
                + " pn.ghiChu as ghiChu, "
                + " pn.ngayThanhToan as ngayThanhToan, "
                + " pn.trangThai as trangThai, "
                + " pn.nhanVien as nhanhVien, "
                + " pn.nhaCungCap as nhaCungCap"
                + ") FROM domainModels.PhieuNhap pn WHERE pn.ngayThanhToan IS NULL  "
                + "and pn.ngayTao >= :ngayBatDau AND pn.ngayTao <= :ngayKetThuc  ORDER BY pn.ngayTao DESC ");
        q.setParameter("ngayBatDau", ngayBatDau);
        q.setParameter("ngayKetThuc", ngayKetThuc);
        list = q.getResultList();
        return list;
    }

    public List<TP_PhieuHoanNhapCustom> getListPhieuHoanNhap() {
        List<TP_PhieuHoanNhapCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_PhieuHoanNhapCustom ("
                + " p.id, "
                + " p.ngayTao, "
                + " p.ghiChu, "
                + " p.liDo, "
                + " p.ngayThanhToan,"
                + " p.trangThai, "
                + " p.phieuNhap"
                + ") FROM domainModels.PhieuHoanNhap p ORDER BY p.ngayTao DESC");
        list = q.getResultList();
        return list;
    }

    public List<TP_HoanNhap_spCustom> getListSpByPhieuNhap(UUID idPhieuNhap) {
        List<TP_HoanNhap_spCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        PhieuNhap pn = s.find(PhieuNhap.class, idPhieuNhap);
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_spCustom ("
                + " pn.idChiTietSp.id as id, "
                + " pn.idChiTietSp.soLuongTon as soLuongTon, "
                + " pn.idChiTietSp.hinhAnh as hinhAnh, "
                + " pn.idChiTietSp.GiaNhap as GiaNhap, "
                + " pn.idChiTietSp.GiaBan as GiaBan, "
                + " pn.idChiTietSp.namBaoHanh as namBaoHanh, "
                + " pn.idChiTietSp.mau as mau, "
                + " pn.idChiTietSp.sanPham as sanPham, "
                + " pn.idChiTietSp.donVi as donVi"
                + ") FROM domainModels.ChiTietPhieuNhap pn WHERE pn.idPhieuNhap = :idPhieuNhap "
             );
        q.setParameter("idPhieuNhap", pn);
        list = q.getResultList();
        return list;
    }

    public List<TP_HoanNhap_spCustom> getListByMaSpByPhieuNhap(UUID idPhieuNhap, String maSp) {
        List<TP_HoanNhap_spCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        PhieuNhap pn = s.find(PhieuNhap.class, idPhieuNhap);
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_spCustom ("
                + " pn.idChiTietSp.id as id, "
                + " pn.idChiTietSp.soLuongTon as soLuongTon, "
                + " pn.idChiTietSp.hinhAnh as hinhAnh, "
                + " pn.idChiTietSp.GiaNhap as GiaNhap, "
                + " pn.idChiTietSp.GiaBan as GiaBan, "
                + " pn.idChiTietSp.namBaoHanh as namBaoHanh, "
                + " pn.idChiTietSp.mau as mau, "
                + " pn.idChiTietSp.sanPham as sanPham, "
                + " pn.idChiTietSp.donVi as donVi"
                + ") FROM domainModels.ChiTietPhieuNhap pn WHERE pn.idPhieuNhap = :idPhieuNhap "
                + "and pn.idChiTietSp.sanPham.ma like CONCAT('%',:maSp,'%')");
        q.setParameter("idPhieuNhap", pn);
        q.setParameter("maSp", maSp);
        list = q.getResultList();
        return list;
    }
      public List<TP_HoanNhap_spCustom> getListByTenSpByPhieuNhap(UUID idPhieuNhap, String tenSp) {
        List<TP_HoanNhap_spCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        PhieuNhap pn = s.find(PhieuNhap.class, idPhieuNhap);
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_spCustom ("
                + " pn.idChiTietSp.id as id, "
                + " pn.idChiTietSp.soLuongTon as soLuongTon, "
                + " pn.idChiTietSp.hinhAnh as hinhAnh, "
                + " pn.idChiTietSp.GiaNhap as GiaNhap, "
                + " pn.idChiTietSp.GiaBan as GiaBan, "
                + " pn.idChiTietSp.namBaoHanh as namBaoHanh, "
                + " pn.idChiTietSp.mau as mau, "
                + " pn.idChiTietSp.sanPham as sanPham, "
                + " pn.idChiTietSp.donVi as donVi"
                + ") FROM domainModels.ChiTietPhieuNhap pn WHERE pn.idPhieuNhap = :idPhieuNhap "
                + "and pn.idChiTietSp.sanPham.ten like CONCAT('%',:tenSp,'%')");
        q.setParameter("idPhieuNhap", pn);
        q.setParameter("tenSp", tenSp);
        list = q.getResultList();
        return list;
    }

    public boolean addPhieuHoanNhap(PhieuHoanNhap phn) {
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = s.beginTransaction();
            s.saveOrUpdate(phn);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addChiTietPhieuHoanNhap(UUID sp, UUID idPhieuHoanNhap, int soLuongHoan, String lyDo) {
        System.out.println(soLuongHoan);
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            PhieuHoanNhap phn = s.find(PhieuHoanNhap.class, idPhieuHoanNhap);
            ChiTietSanPham ctsp = s.find(ChiTietSanPham.class, sp);
            ChiTietPhieuHoanNhapId idChiTietPhieuHoan = new ChiTietPhieuHoanNhapId(phn, ctsp);
            ChiTietPhieuHoanNhap ctphnFind = s.find(ChiTietPhieuHoanNhap.class, idChiTietPhieuHoan);
            Transaction tran = s.beginTransaction();
            if (ctphnFind != null) {
                ctphnFind.setSoLuong(ctphnFind.getSoLuong() + soLuongHoan);
                ctphnFind.setLiDo(lyDo);
                s.saveOrUpdate(ctphnFind);
            } else {
                ChiTietPhieuHoanNhap ctphn = new ChiTietPhieuHoanNhap();
                ctphn.setIdChiTietSp(ctsp);
                ctphn.setIdPhieuHoanNhap(phn);
                ctphn.setSoLuong(soLuongHoan);
                ctphn.setLiDo(lyDo);
                s.saveOrUpdate(ctphn);
            }
            ctsp.setSoLuongTon(ctsp.getSoLuongTon() - soLuongHoan);
            s.saveOrUpdate(ctsp);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeChiTietPhieuHoanNhap(UUID sp, UUID idPhieuHoanNhap, int soLuongHoan) {
//        System.out.println(soLuongHoan);
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            PhieuHoanNhap phn = s.find(PhieuHoanNhap.class, idPhieuHoanNhap);
            ChiTietSanPham ctsp = s.find(ChiTietSanPham.class, sp);
            ctsp.setSoLuongTon(ctsp.getSoLuongTon() + soLuongHoan);
            s.saveOrUpdate(ctsp);
            Transaction tran = s.beginTransaction();
            ChiTietPhieuHoanNhapId chiTietPhieuHoanNhapId = new ChiTietPhieuHoanNhapId(phn, ctsp);
            ChiTietPhieuHoanNhap ctphn = s.find(ChiTietPhieuHoanNhap.class, chiTietPhieuHoanNhapId);
            ctphn.setSoLuong(ctphn.getSoLuong() - soLuongHoan);
            s.saveOrUpdate(ctphn);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<TP_HoanNhap_ctpCusTom> getListCtpByPhieuHoanNhap(UUID idPhieuHoanNhap) {
        List<TP_HoanNhap_ctpCusTom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        PhieuHoanNhap pn = s.find(PhieuHoanNhap.class, idPhieuHoanNhap);
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_ctpCusTom ("
                + " pn.idChiTietSp.id as id, "
                + " pn.idChiTietSp.soLuongTon as soLuongTon, "
                + " pn.idChiTietSp.hinhAnh as hinhAnh, "
                + " pn.idChiTietSp.GiaNhap as GiaNhap, "
                + " pn.idChiTietSp.GiaBan as GiaBan, "
                + " pn.idChiTietSp.namBaoHanh as namBaoHanh, "
                + " pn.idChiTietSp.mau as mau, "
                + " pn.idChiTietSp.sanPham as sanPham, "
                + " pn.idChiTietSp.donVi as donVi, "
                + " pn.soLuong,"
                + " pn.liDo"
                + ") FROM domainModels.ChiTietPhieuHoanNhap pn WHERE pn.idPhieuHoanNhap = :idPhieuNhap");
        q.setParameter("idPhieuNhap", pn);
        list = q.getResultList();
        return list;
    }
     public List<TP_HoanNhap_ctpCusTom> getListSpByMaByChiTietPhieuHoanNhap(UUID idPhieuHoanNhap, String ma) {
        List<TP_HoanNhap_ctpCusTom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        PhieuHoanNhap pn = s.find(PhieuHoanNhap.class, idPhieuHoanNhap);
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_ctpCusTom ("
                + " pn.idChiTietSp.id as id, "
                + " pn.idChiTietSp.soLuongTon as soLuongTon, "
                + " pn.idChiTietSp.hinhAnh as hinhAnh, "
                + " pn.idChiTietSp.GiaNhap as GiaNhap, "
                + " pn.idChiTietSp.GiaBan as GiaBan, "
                + " pn.idChiTietSp.namBaoHanh as namBaoHanh, "
                + " pn.idChiTietSp.mau as mau, "
                + " pn.idChiTietSp.sanPham as sanPham, "
                + " pn.idChiTietSp.donVi as donVi, "
                + " pn.soLuong,"
                + " pn.liDo"
                + ") FROM domainModels.ChiTietPhieuHoanNhap pn WHERE pn.idPhieuHoanNhap = :idPhieuNhap "
                + "and pn.idChiTietSp.sanPham.ma like CONCAT('%',:ma,'%') ");
        
        q.setParameter("idPhieuNhap", pn);
        q.setParameter("ma", ma);
        list = q.getResultList();
        return list;
    }
       public List<TP_HoanNhap_ctpCusTom> getListSpByTenByChiTietPhieuHoanNhap(UUID idPhieuHoanNhap, String ma) {
        List<TP_HoanNhap_ctpCusTom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        PhieuHoanNhap pn = s.find(PhieuHoanNhap.class, idPhieuHoanNhap);
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_HoanNhap_ctpCusTom ("
                + " pn.idChiTietSp.id as id, "
                + " pn.idChiTietSp.soLuongTon as soLuongTon, "
                + " pn.idChiTietSp.hinhAnh as hinhAnh, "
                + " pn.idChiTietSp.GiaNhap as GiaNhap, "
                + " pn.idChiTietSp.GiaBan as GiaBan, "
                + " pn.idChiTietSp.namBaoHanh as namBaoHanh, "
                + " pn.idChiTietSp.mau as mau, "
                + " pn.idChiTietSp.sanPham as sanPham, "
                + " pn.idChiTietSp.donVi as donVi, "
                + " pn.soLuong,"
                + " pn.liDo"
                + ") FROM domainModels.ChiTietPhieuHoanNhap pn WHERE pn.idPhieuHoanNhap = :idPhieuNhap "
                + "and pn.idChiTietSp.sanPham.ten like CONCAT('%',:ma,'%') ");
        
        q.setParameter("idPhieuNhap", pn);
        q.setParameter("ma", ma);
        list = q.getResultList();
        return list;
    }

    public void updatePhieuHoanNhap(PhieuHoanNhap phn) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = s.beginTransaction();
            s.saveOrUpdate(phn);
            transaction.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TP_PhieuHoanNhapCustom> getListByTenNv(String ma, TrangThaiPhieuHoanConstant tt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.truongPhongs.customModels.TP_PhieuHoanNhapCustom ("
                + " p.id, "
                + " p.ngayTao, "
                + " p.ghiChu, "
                + " p.liDo, "
                + " p.ngayThanhToan,"
                + " p.trangThai, "
                + " p.phieuNhap"
                + ") FROM domainModels.PhieuHoanNhap p WHERE p.phieuNhap.nhanVien.ten like CONCAT('%',:ma,'%') and p.trangThai = :tt "
                + "order by p.ngayTao DESC");
        query.setParameter("ma", ma);
        query.setParameter("tt", tt);
        List<TP_PhieuHoanNhapCustom> list = query.getResultList();
        return list;

    }

    public List<TP_PhieuHoanNhapCustom> getListByTenNcc(String ma, TrangThaiPhieuHoanConstant tt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.truongPhongs.customModels.TP_PhieuHoanNhapCustom ("
                + " p.id, "
                + " p.ngayTao, "
                + " p.ghiChu, "
                + " p.liDo, "
                + " p.ngayThanhToan,"
                + " p.trangThai, "
                + " p.phieuNhap"
                + ") FROM domainModels.PhieuHoanNhap p WHERE p.phieuNhap.nhaCungCap.ten like CONCAT('%',:ma,'%') and p.trangThai = :tt "
                + "order by p.ngayTao DESC");
        query.setParameter("ma", ma);
        query.setParameter("tt", tt);
        List<TP_PhieuHoanNhapCustom> list = query.getResultList();
        return list;

    }

    public List<TP_PhieuHoanNhapCustom> getListByNgayTao(Long ngayBatDau, Long ngayKetThuc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.truongPhongs.customModels.TP_PhieuHoanNhapCustom ("
                + " p.id, "
                + " p.ngayTao, "
                + " p.ghiChu, "
                + " p.liDo, "
                + " p.ngayThanhToan,"
                + " p.trangThai, "
                + " p.phieuNhap"
                + ") FROM domainModels.PhieuHoanNhap p WHERE p.ngayTao >= :ngayBatDau AND p.ngayTao <= :ngayKetThuc  "
                + "order by p.ngayTao DESC");
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        List<TP_PhieuHoanNhapCustom> list = query.getResultList();
        return list;

    }

    public List<TP_PhieuHoanNhapCustom> getListByNgayThanhToan(Long ngayBatDau, Long ngayKetThuc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.truongPhongs.customModels.TP_PhieuHoanNhapCustom ("
                + " p.id, "
                + " p.ngayTao, "
                + " p.ghiChu, "
                + " p.liDo, "
                + " p.ngayThanhToan,"
                + " p.trangThai, "
                + " p.phieuNhap"
                + ") FROM domainModels.PhieuHoanNhap p WHERE p.ngayThanhToan >= :ngayBatDau AND p.ngayThanhToan <= :ngayKetThuc  "
                + "order by p.ngayTao DESC");
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        List<TP_PhieuHoanNhapCustom> list = query.getResultList();
        return list;

    }
}
