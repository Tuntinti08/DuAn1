package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.NvqlQuanLyPhieuHoanXuatCustom;
import domainModels.PhieuHoanXuat;
import domainModels.PhieuXuat;
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
public class NvqlQuanLyPhieuHoanXuatRepository {

    public List<NvqlQuanLyPhieuHoanXuatCustom> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.nhanVienQuanLy.customModels.NvqlQuanLyPhieuHoanXuatCustom("
                + "phx.id as id,"
                + "phx.ngayTao as ngayTao,"
                + "phx.ngayThanhToan as ngayThanhToan,"
                + "phx.ghiChu as ghiChu,"
                + "phx.liDo as liDo,"
                + "phx.trangThai as trangThai,"
                + "phx.phieuXuat as phieuXuat"
                + ") FROM domainModels.PhieuHoanXuat phx");
        List<NvqlQuanLyPhieuHoanXuatCustom> list = query.getResultList();
        return list;
    }

    public List<NvqlQuanLyPhieuHoanXuatCustom> getListByNgayTao(Long ngayBatDau, Long ngayKetThuc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.nhanVienQuanLy.customModels.NvqlQuanLyPhieuHoanXuatCustom("
                + "phx.id as id,"
                + "phx.ngayTao as ngayTao,"
                + "phx.ngayThanhToan as ngayThanhToan,"
                + "phx.ghiChu as ghiChu,"
                + "phx.liDo as liDo,"
                + "phx.trangThai as trangThai,"
                + "phx.phieuXuat as phieuXuat"
                + ") FROM domainModels.PhieuHoanXuat phx WHERE phx.ngayTao >= :ngayBatDau AND phx.ngayTao <= :ngayKetThuc");
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        List<NvqlQuanLyPhieuHoanXuatCustom> list = query.getResultList();
        return list;
    }

    public List<NvqlQuanLyPhieuHoanXuatCustom> getListByNgayThanhToan(Long ngayBatDau, Long ngayKetThuc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.nhanVienQuanLy.customModels.NvqlQuanLyPhieuHoanXuatCustom("
                + "phx.id as id,"
                + "phx.ngayTao as ngayTao,"
                + "phx.ngayThanhToan as ngayThanhToan,"
                + "phx.ghiChu as ghiChu,"
                + "phx.liDo as liDo,"
                + "phx.trangThai as trangThai,"
                + "phx.phieuXuat as phieuXuat"
                + ") FROM domainModels.PhieuHoanXuat phx WHERE phx.ngayThanhToan >= :ngayBatDau AND phx.ngayThanhToan <= :ngayKetThuc");
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        List<NvqlQuanLyPhieuHoanXuatCustom> list = query.getResultList();
        return list;
    }

    public PhieuHoanXuat addPhieuHoanXuat(PhieuHoanXuat phx) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = null;
            transaction = s.beginTransaction();
            s.save(phx);;
            transaction.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
        }
        return phx;
    }

    public boolean updatePhieuHoanXuat(PhieuHoanXuat phx) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = null;
            transaction = s.beginTransaction();
            s.update(phx);
            transaction.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
            return false;
        }
        return true;
    }

    public boolean deletePhieuHoanXuat(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = null;
            transaction = s.beginTransaction();
            PhieuHoanXuat phx = s.find(PhieuHoanXuat.class, id);
            s.delete(phx);
            transaction.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
            return false;
        }
        return true;
    }

    public NvqlQuanLyPhieuHoanXuatCustom findById(UUID id) {
        NvqlQuanLyPhieuHoanXuatCustom phxcs = new NvqlQuanLyPhieuHoanXuatCustom();
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT new cores.nhanVienQuanLy.customModels.NvqlQuanLyPhieuHoanXuatCustom("
                    + "phx.id as id,"
                    + "phx.ngayTao as ngayTao,"
                    + "phx.ngayThanhToan as ngayThanhToan,"
                    + "phx.ghiChu as ghiChu,"
                    + "phx.liDo as liDo,"
                    + "phx.trangThai as trangThai,"
                    + "phx.phieuXuat as phieuXuat"
                    + ") FROM domainModels.PhieuHoanXuat phx WHERE phx.id =:id");
            query.setParameter("id", id);
            phxcs = (NvqlQuanLyPhieuHoanXuatCustom) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        return phxcs;
    }

    public List<PhieuXuat> getListMaPhieuXuat() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("FROM domainModels.PhieuXuat nv");
        List<PhieuXuat> list = query.getResultList();
        return list;
    }
}
