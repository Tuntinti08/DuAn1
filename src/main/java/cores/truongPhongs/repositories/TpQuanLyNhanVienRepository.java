package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TP_ChucVuCustom;
import cores.truongPhongs.customModels.TpNhanVienCustom;
import domainModels.ChucVu;
import domainModels.NhanVien;
import infrastructures.constant.TrangThaiNhanVienConstant;
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
 * @author LENOVO
 */
public class TpQuanLyNhanVienRepository {

    public List<TpNhanVienCustom> getList() {
        List<TpNhanVienCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpNhanVienCustom ("
                + " nv.id as id, "
                + " nv.ma as ma, "
                + " nv.ten as ten, "
                + " nv.sdt as sdt, "
                + " nv.email as email, "
                + " nv.matKhau as matKhau, "
                + " nv.ngaySinh as ngaySinh, "
                + " nv.hinhAnh as hinhAnh, "
                + " nv.gioiTinh as gioiTinh, "
                + " nv.diaChi as diaChi, "
                + " nv.trangThai as trangThai, "
                + " nv.idChucVu as ten "
                + " ) FROM domainModels.NhanVien nv"
        );
        list = q.getResultList();
        s.close();
        return list;
    }

    public NhanVien addNhanVien(NhanVien nv) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tran = null;
            tran = s.beginTransaction();
            s.save(nv);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            s.close();
            return null;
        }
        return nv;
    }

    public boolean updateNhanVien(NhanVien nv) {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {

            tran = s.beginTransaction();
            s.update(nv);
            tran.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public boolean deleteNhanVien(UUID id) {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {

            tran = s.beginTransaction();
            NhanVien nv = s.find(NhanVien.class, id);
            nv.setTrangThai(TrangThaiNhanVienConstant.DA_NGHI_VIEC);
            s.update(nv);
            tran.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public TpNhanVienCustom findByMa(String ma) {
        TpNhanVienCustom nvc = new TpNhanVienCustom();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpNhanVienCustom ("
                    + " nv.id as id, "
                    + " nv.ma as ma, "
                    + " nv.ten as ten, "
                    + " nv.sdt as sdt, "
                    + " nv.email as email, "
                    + " nv.matKhau as matKhau, "
                    + " nv.ngaySinh as ngaySinh, "
                    + " nv.hinhAnh as hinhAnh, "
                    + " nv.gioiTinh as gioiTinh, "
                    + " nv.diaChi as diaChi, "
                    + " nv.trangThai as trangThai, "
                    + " nv.idChucVu as ten "
                    + ") FROM domainModels.NhanVien nv WHERE nv.ma = :ma");

            q.setParameter("ma", ma);
            nvc = (TpNhanVienCustom) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        return nvc;
    }

    public List<TpNhanVienCustom> findAllByMa(String ma, TrangThaiNhanVienConstant tt) {
        List<TpNhanVienCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpNhanVienCustom ("
                + " nv.id as id, "
                + " nv.ma as ma, "
                + " nv.ten as ten, "
                + " nv.sdt as sdt, "
                + " nv.email as email, "
                + " nv.matKhau as matKhau, "
                + " nv.ngaySinh as ngaySinh, "
                + " nv.hinhAnh as hinhAnh, "
                + " nv.gioiTinh as gioiTinh, "
                + " nv.diaChi as diaChi, "
                + " nv.trangThai as trangThai, "
                + " nv.idChucVu as ten "
                + " ) FROM domainModels.NhanVien nv WHERE nv.ma LIKE CONCAT('%',:ma,'%') AND nv.trangThai = :tt"
        );
        q.setParameter("ma", ma);
        q.setParameter("tt", tt);
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TpNhanVienCustom> findAllByTen(String ten, TrangThaiNhanVienConstant tt) {
        List<TpNhanVienCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpNhanVienCustom ("
                + " nv.id as id, "
                + " nv.ma as ma, "
                + " nv.ten as ten, "
                + " nv.sdt as sdt, "
                + " nv.email as email, "
                + " nv.matKhau as matKhau, "
                + " nv.ngaySinh as ngaySinh, "
                + " nv.hinhAnh as hinhAnh, "
                + " nv.gioiTinh as gioiTinh, "
                + " nv.diaChi as diaChi, "
                + " nv.trangThai as trangThai, "
                + " nv.idChucVu as ten "
                + " ) FROM domainModels.NhanVien nv WHERE nv.ten LIKE CONCAT('%',:ten,'%') AND nv.trangThai = :tt"
        );
        q.setParameter("ten", ten);
        q.setParameter("tt", tt);
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TpNhanVienCustom> findAllByDiaChi(String diaChi, TrangThaiNhanVienConstant tt) {
        List<TpNhanVienCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpNhanVienCustom ("
                + " nv.id as id, "
                + " nv.ma as ma, "
                + " nv.ten as ten, "
                + " nv.sdt as sdt, "
                + " nv.email as email, "
                + " nv.matKhau as matKhau, "
                + " nv.ngaySinh as ngaySinh, "
                + " nv.hinhAnh as hinhAnh, "
                + " nv.gioiTinh as gioiTinh, "
                + " nv.diaChi as diaChi, "
                + " nv.trangThai as trangThai, "
                + " nv.idChucVu as ten "
                + " ) FROM domainModels.NhanVien nv WHERE nv.diaChi LIKE CONCAT('%',:diaChi,'%') AND nv.trangThai = :tt"
        );
        q.setParameter("diaChi", diaChi);
        q.setParameter("tt", tt);
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TP_ChucVuCustom> getListCV() {
        List<TP_ChucVuCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.truongPhongs.customModels.TP_ChucVuCustom ("
                + "cv.id as id, "
                + "cv.ma as ma, "
                + "cv.ten as ten"
                + ") FROM domainModels.ChucVu cv ");
        list = q.getResultList();
        s.close();
        return list;
    }

    public ChucVu findIDCV(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        ChucVu sp = s.find(ChucVu.class, id);
        t.commit();
        s.close();
        return sp;
    }
}
