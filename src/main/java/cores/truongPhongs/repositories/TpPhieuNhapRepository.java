package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TpPhieuNhapCustom;
import domainModels.NhanVien;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.HibernateUtil;
import domainModels.PhieuNhap;
import infrastructures.constant.TrangThaiPhieuConstant;
import java.util.UUID;
import net.bytebuddy.asm.Advice;
import org.hibernate.Transaction;

/**
 *
 * @author Acer
 */
public class TpPhieuNhapRepository {

    public List<TpPhieuNhapCustom> getListPhieuNhap() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("Select new  cores.truongPhongs.customModels.TpPhieuNhapCustom ( "
                + "p.id as id,"
                + "p.maPhieu as maPhieu,"
                + "p.ghiChu as ghiChu,"
                + "p.ngayThanhToan as ngayThanhToan,"
                + "p.ngayTao as ngayTao,"
                + "p.trangThai as trangThai,"
                + "p.nhanVien.id as idNhanVien,"
                + "p.nhanVien.ten as tenNhanVien,"
                + "p.nhaCungCap.id as idNcc,"
                + "p.nhaCungCap.ten as tenNcc) "
                + "from domainModels.PhieuNhap p ORDER BY p.ngayTao DESC");
        List<TpPhieuNhapCustom> listPn = q.getResultList();
        s.close();
        return listPn;
    }

    public PhieuNhap addPn(PhieuNhap p) {
        Transaction tran = null;
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            tran = s.beginTransaction();
            s.save(p);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
        }
        return p;
    }

    public boolean updatePn(PhieuNhap p) {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            s.update(p);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public boolean deletePn(UUID id) {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            PhieuNhap p = s.find(PhieuNhap.class, id);
            s.delete(p);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public PhieuNhap findPhieuNhapById(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        PhieuNhap p = s.find(PhieuNhap.class, id);
        s.close();
        return p;
    }

    public List<TpPhieuNhapCustom> getListByNgayTao(Long ngayBatDau, Long ngayKetThuc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("Select new  cores.truongPhongs.customModels.TpPhieuNhapCustom ( "
                + "p.id as id,"
                + "p.maPhieu as maPhieu,"
                + "p.ghiChu as ghiChu,"
                + "p.ngayThanhToan as ngayThanhToan,"
                + "p.ngayTao as ngayTao,"
                + "p.trangThai as trangThai,"
                + "p.nhanVien.id as idNhanVien,"
                + "p.nhanVien.ten as tenNhanVien,"
                + "p.nhaCungCap.id as idNcc,"
                + "p.nhaCungCap.ten as tenNcc) "
                + "from domainModels.PhieuNhap p WHERE p.ngayTao >= :ngayBatDau AND p.ngayTao <= :ngayKetThuc  ORDER BY p.ngayTao DESC");
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        List<TpPhieuNhapCustom> list = query.getResultList();
        return list;
    }

    public List<TpPhieuNhapCustom> getListByNgayThanhToan(Long ngayBatDau, Long ngayKetThuc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("Select new  cores.truongPhongs.customModels.TpPhieuNhapCustom ( "
                + "p.id as id,"
                + "p.maPhieu as maPhieu,"
                + "p.ghiChu as ghiChu,"
                + "p.ngayThanhToan as ngayThanhToan,"
                + "p.ngayTao as ngayTao,"
                + "p.trangThai as trangThai,"
                + "p.nhanVien.id as idNhanVien,"
                + "p.nhanVien.ten as tenNhanVien,"
                + "p.nhaCungCap.id as idNcc,"
                + "p.nhaCungCap.ten as tenNcc) "
                + "from domainModels.PhieuNhap p WHERE p.ngayThanhToan >= :ngayBatDau AND p.ngayThanhToan <= :ngayKetThuc  ORDER BY p.ngayThanhToan DESC");
        query.setParameter("ngayBatDau", ngayBatDau);
        query.setParameter("ngayKetThuc", ngayKetThuc);
        List<TpPhieuNhapCustom> list = query.getResultList();
        return list;
        
    }

    public NhanVien getNhanVienByMa(String ma) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createQuery("FROM domainModels.NhanVien kh WHERE kh.ma =:ma");
        query.setParameter("ma", ma);
        NhanVien nv = (NhanVien) query.getSingleResult();
        return nv;
    }
     public List<TpPhieuNhapCustom> getListPnById(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("Select new  cores.truongPhongs.customModels.TpPhieuNhapCustom ( "
                + "p.id as id,"
                + "p.maPhieu as maPhieu,"
                + "p.ghiChu as ghiChu,"
                + "p.ngayThanhToan as ngayThanhToan,"
                + "p.ngayTao as ngayTao,"
                + "p.trangThai as trangThai,"
                + "p.nhanVien.id as idNhanVien,"
                + "p.nhanVien.ten as tenNhanVien,"
                + "p.nhaCungCap.id as idNcc,"
                + "p.nhaCungCap.ten as tenNcc) "
                + "from domainModels.PhieuNhap p WHERE p.id like CONCAT('%',:id,'%')");
       query.setParameter("id", id);
        List<TpPhieuNhapCustom> list = query.getResultList();
        return list;
        
    }
      public List<TpPhieuNhapCustom> getListByTenNv(String tenNhanVien,TrangThaiPhieuConstant tt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("Select new  cores.truongPhongs.customModels.TpPhieuNhapCustom ( "
                + "p.id as id,"
                + "p.maPhieu as maPhieu,"
                + "p.ghiChu as ghiChu,"
                + "p.ngayThanhToan as ngayThanhToan,"
                + "p.ngayTao as ngayTao,"
                + "p.trangThai as trangThai,"
                + "p.nhanVien.id as idNhanVien,"
                + "p.nhanVien.ten as tenNhanVien,"
                + "p.nhaCungCap.id as idNcc,"
                + "p.nhaCungCap.ten as tenNcc) "
                + "from domainModels.PhieuNhap p WHERE p.nhanVien.ten like CONCAT('%',:tenNhanVien,'%') and p.trangThai = :tt "
                + "order by  p.ngayTao DESC");
       query.setParameter("tenNhanVien", tenNhanVien);
       query.setParameter("tt", tt);
        List<TpPhieuNhapCustom> list = query.getResultList();
        return list;
        
    }
       public List<TpPhieuNhapCustom> getListByTenNcc(String tenNcc, TrangThaiPhieuConstant tt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("Select new  cores.truongPhongs.customModels.TpPhieuNhapCustom ( "
                + "p.id as id,"
                + "p.maPhieu as maPhieu,"
                + "p.ghiChu as ghiChu,"
                + "p.ngayThanhToan as ngayThanhToan,"
                + "p.ngayTao as ngayTao,"
                + "p.trangThai as trangThai,"
                + "p.nhanVien.id as idNhanVien,"
                + "p.nhanVien.ten as tenNhanVien,"
                + "p.nhaCungCap.id as idNcc,"
                + "p.nhaCungCap.ten as tenNcc) "
                + "from domainModels.PhieuNhap p WHERE p.nhaCungCap.ten like CONCAT('%',:tenNcc,'%') and p.trangThai = :tt "
                + "order by p.ngayTao DESC" );
       query.setParameter("tenNcc", tenNcc);
       query.setParameter("tt", tt);
        List<TpPhieuNhapCustom> list = query.getResultList();
        return list;
        
    }
 
       public List<TpPhieuNhapCustom> getListByMaPhieu(String tenNcc, TrangThaiPhieuConstant tt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("Select new  cores.truongPhongs.customModels.TpPhieuNhapCustom ( "
                + "p.id as id,"
                + "p.maPhieu as maPhieu,"
                + "p.ghiChu as ghiChu,"
                + "p.ngayThanhToan as ngayThanhToan,"
                + "p.ngayTao as ngayTao,"
                + "p.trangThai as trangThai,"
                + "p.nhanVien.id as idNhanVien,"
                + "p.nhanVien.ten as tenNhanVien,"
                + "p.nhaCungCap.id as idNcc,"
                + "p.nhaCungCap.ten as tenNcc) "
                + "from domainModels.PhieuNhap p WHERE p.maPhieu like CONCAT('%',:tenNcc,'%') and p.trangThai = :tt "
                + "order by p.ngayTao DESC" );
       query.setParameter("tenNcc", tenNcc);
       query.setParameter("tt", tt);
        List<TpPhieuNhapCustom> list = query.getResultList();
        return list;
        
    }

    

   
    
}
