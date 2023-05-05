package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.ChiTietPhieuHoanXuatCustom;
import cores.nhanVienQuanLy.customModels.PhieuHoanXuatCustom;
import domainModels.ChiTietPhieuHoanXuat;
import domainModels.PhieuHoanXuat;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utilities.HibernateUtil;

public class Tai_LuongHoanXuat_Repository {

    public List<PhieuHoanXuatCustom> getListPHX() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.nhanVienQuanLy.customModels.PhieuHoanXuatCustom("
                + "phx.id as id,"
                + "phx.ngayTao as ngayTao,"
                + "phx.ngayThanhToan as ngayThanhToan,"
                + "phx.ghiChu as ghiChu,"
                + "phx.liDo as liDo,"
                + "phx.trangThai as trangThai,"
                + "phx.phieuXuat as phieuXuat"
                + ") FROM domainModels.PhieuHoanXuat phx ORDER BY phx.ngayTao DESC");
        List<PhieuHoanXuatCustom> list = query.getResultList();
        return list;
    }

    public void updatePHX(PhieuHoanXuat phx) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(phx);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPHX(PhieuHoanXuat phx) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(phx);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addChiTietPhieuHoanXuat(ChiTietPhieuHoanXuat ctphx) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(ctphx);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ChiTietPhieuHoanXuatCustom> getListCTphxByID(UUID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.nhanVienQuanLy.customModels.ChiTietPhieuHoanXuatCustom("
                + "ctphx.idPhieuHoanXuat as idPhieuHoanXuat,"
                + "ctphx.idChiTietSp as idChiTietSp,"
                + "ctphx.soLuong as soLuong,"
                + "ctphx.liDo as liDo"
                + ") FROM domainModels.ChiTietPhieuHoanXuat ctphx WHERE ctphx.idPhieuHoanXuat.id = :id");
        query.setParameter("id", id);
        List<ChiTietPhieuHoanXuatCustom> list = query.getResultList();
        return list;
    }

    public List<ChiTietPhieuHoanXuatCustom> getListCTphx() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT new cores.nhanVienQuanLy.customModels.ChiTietPhieuHoanXuatCustom("
                + "ctphx.idPhieuHoanXuat as idPhieuHoanXuat,"
                + "ctphx.idChiTietSp as idChiTietSp,"
                + "ctphx.soLuong as soLuong,"
                + "ctphx.liDo as liDo"
                + ") FROM domainModels.ChiTietPhieuHoanXuat ctphx");
        List<ChiTietPhieuHoanXuatCustom> list = query.getResultList();
        return list;
    }

    public void updateCtPHX(ChiTietPhieuHoanXuat ctphx) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(ctphx);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
