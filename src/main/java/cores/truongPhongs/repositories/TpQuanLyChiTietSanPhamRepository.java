package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TpQuanLyChiTietSanPhamCustom;
import cores.truongPhongs.customModels.TpQuanLyDonViCustom;
import cores.truongPhongs.customModels.TpQuanLySanPhamCustom;
import domainModels.ChiTietSanPham;
import domainModels.DonVi;
import domainModels.SanPham;
import infrastructures.constant.MauConstant;
import java.math.BigDecimal;
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
 * @author MMC
 */
public class TpQuanLyChiTietSanPhamRepository {

    public List<TpQuanLyChiTietSanPhamCustom> getAll(UUID idSp, String maNcc, String tenNcc, String maSpNcc, String emailNcc, String sdtNcc) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query query = s.createNativeQuery("""
                SELECT 
                ctsp.Id,
                ctsp.SoLuongTon,
                ctsp.HinhAnh,
                ctsp.GiaNhap,
                ctsp.GiaBan,
                ctsp.Mau,
                dv.DonViQuyDoi,
                ctsp.namBaoHanh,
                ctsp.TrangThai,
                ctsp.Size,
                ncc.Ten,
                ctpn.MaSanPhamNhaCungCap,
                ctsp.createDate,
                dv.id
                FROM ChiTietSanPham ctsp
                join DonVi dv on ctsp.IdDonVi = dv.Id
                join ChiTietPhieuNhap ctpn on ctpn.IdChiTietSP = ctsp.Id
                join PhieuNhap pn on ctpn.IdPhieuNhap = pn.Id
                join NhaCungCap ncc on ncc.Id = pn.IdNhaCungCap
                WHERE ctsp.IdSanPham = :idSp AND ncc.Ma LIKE CONCAT('%', :maNcc,'%')
                                          AND ncc.Ten LIKE CONCAT('%', :tenNcc,'%')
                                          AND ctpn.MaSanPhamNhaCungCap LIKE CONCAT('%', :maSpNcc,'%')
                                          AND ncc.Email LIKE CONCAT('%', :emailNcc,'%')
                                          AND ncc.Sdt LIKE CONCAT('%', :sdtNcc,'%')
                              
            """).setParameter("idSp", idSp.toString())
                .setParameter("maNcc", maNcc)
                .setParameter("tenNcc", tenNcc)
                .setParameter("maSpNcc", maSpNcc)
                .setParameter("emailNcc", emailNcc)
                .setParameter("sdtNcc", sdtNcc);
        List<Object[]> listQuery = query.getResultList();
        List<TpQuanLyChiTietSanPhamCustom> list = new ArrayList<>();
        listQuery.parallelStream().forEach(el -> {
            UUID id = null;
            int soLuongTon = 0;
            String hinhAnh = "";
            BigDecimal giaNhap = (BigDecimal) el[3];
            BigDecimal giaBan = (BigDecimal) el[4];
            int mau = 0;
            String doViQuyDoi = "Không có";
            int namBaoHanh = 0;
            int trangThai = 0;
            int size = 0;
            String tenNcc1 = "Chưa có";
            String maSpNcc1 = "Chưa có";
            Long ngayNhap = 0L;

            if (!"null".equalsIgnoreCase((String) el[0])) {
                id = UUID.fromString((String) el[0]);
            }
            if (!"null".equalsIgnoreCase(String.valueOf((Integer) el[1]))) {
                soLuongTon = (Integer) el[1];
            }
            if (!"null".equalsIgnoreCase((String) el[2])) {
                hinhAnh = (String) el[2];
            }
            if (!"null".equalsIgnoreCase(String.valueOf((Integer) el[5]))) {
                mau = (Integer) el[5];
            }
            if (!"null".equalsIgnoreCase((String) el[6])) {
                doViQuyDoi = (String) el[6];
            }
            if (!"null".equalsIgnoreCase(String.valueOf((Integer) el[7]))) {
                namBaoHanh = (Integer) el[7];
            }
            if (!"null".equalsIgnoreCase(String.valueOf((Integer) el[8]))) {
                trangThai = (Integer) el[8];
            }
            if (!"null".equalsIgnoreCase(String.valueOf((Integer) el[9]))) {
                size = (Integer) el[9];
            }

            if (!"null".equalsIgnoreCase((String) el[10])) {
                tenNcc1 = (String) el[10];
            }

            if (!"null".equalsIgnoreCase((String) el[11])) {
                maSpNcc1 = (String) el[11];
            }
            if (!"null".equalsIgnoreCase(String.valueOf((BigDecimal) el[12]))) {
                ngayNhap = Long.valueOf(String.valueOf((BigDecimal) el[12]));
            }

            list.add(new TpQuanLyChiTietSanPhamCustom(id, soLuongTon, hinhAnh, giaNhap, giaBan, mau, doViQuyDoi, namBaoHanh, trangThai, size, tenNcc1, maSpNcc1, ngayNhap, UUID.fromString((String) el[13])
            ));
        });
        s.close();
        return list;
    }

    public List<DonVi> getCbbDonVi() {
        List<DonVi> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createNativeQuery("select * from DonVi dv", DonVi.class);
        list = q.getResultList();
        s.close();
        return list;
    }

    public boolean updateChiTietSanPham(TpQuanLyChiTietSanPhamCustom sp) {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Transaction tran = s.beginTransaction();
            Query q = s.createNativeQuery("""
                                          UPDATE [dbo].[ChiTietSanPham]
                                             SET 
                                                [GiaBan] = :giaBan
                                                ,[GiaNhap] = :giaNhap
                                                ,[HinhAnh] = :hinhAnh
                                                ,[Mau] = :mau
                                                ,[namBaoHanh] = :namBh 
                                                ,[Size] = :size
                                                ,[SoLuongTon] = :soLuongTon
                                                ,[TrangThai] = :trangThai
                                                ,[IdDonVi] = :donVi
                                           WHERE [Id] = :id
                                          """)
                    .setParameter("giaBan", sp.getGiaBan())
                    .setParameter("giaNhap", sp.getGiaNhap())
                    .setParameter("hinhAnh", sp.getHinhAnh())
                    .setParameter("mau", sp.getMau())
                    .setParameter("namBh", sp.getNamBaoHanh())
                    .setParameter("size", sp.getSize())
                    .setParameter("soLuongTon", sp.getSoLuongTon())
                    .setParameter("trangThai", sp.getTrangThai())
                    .setParameter("donVi", sp.getIdDv().toString())
                    .setParameter("id", sp.getId().toString());
            System.out.println(sp.getDoViQuyDoi());
            System.out.println(sp.getId());
            if (q.executeUpdate() < 0) {
                tran.commit();
                s.close();
                return false;
            }
            tran.commit();
            s.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ChiTietSanPham addCTSanPham(ChiTietSanPham sp) {
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

    public boolean updateCTSanPham(ChiTietSanPham sp) {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            s.update(sp);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
        return true;
    }

    public TpQuanLyChiTietSanPhamCustom findById(UUID id) {
        TpQuanLyChiTietSanPhamCustom sp = new TpQuanLyChiTietSanPhamCustom();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLyChiTietSanPhamCustom ("
                    + "ct.id as id,"
                    + "ct.soLuongTon as soLuongTon,"
                    + "ct.hinhAnh as hinhAnh,"
                    + "ct.GiaNhap as GiaNhap,"
                    + "ct.GiaBan as GiaBan,"
                    + "ct.mau as mau,"
                    + "ct.sanPham as ten,"
                    + "ct.donVi as donViGoc,"
                    + "ct.namBaoHanh as namBaoHanh,"
                    + "ct.trangThai as trangThai, "
                    + "ct.size as size,"
                    + "ct.ngayTao as ngayTao"
                    + ") from domainModels.ChiTietSanPham ct WHERE ct.id = :id");
            q.setParameter("id", id);
            sp = (TpQuanLyChiTietSanPhamCustom) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        return sp;
    }

    public TpQuanLyChiTietSanPhamCustom findByGia(BigDecimal gia) {
        TpQuanLyChiTietSanPhamCustom sp = new TpQuanLyChiTietSanPhamCustom();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLyChiTietSanPhamCustom ("
                    + "ct.id as id,"
                    + "ct.soLuongTon as soLuongTon,"
                    + "ct.hinhAnh as hinhAnh,"
                    + "ct.GiaNhap as GiaNhap,"
                    + "ct.GiaBan as GiaBan,"
                    + "ct.mau as mau,"
                    + "ct.sanPham as ten,"
                    + "ct.donVi as donViGoc,"
                    + "ct.namBaoHanh as namBaoHanh,"
                    + "ct.trangThai as trangThai, "
                    + "ct.size as size,"
                    + "ct.ngayTao as ngayTao"
                    + ") from domainModels.ChiTietSanPham ct WHERE ct.GiaBan = :GiaBan");
            q.setParameter("GiaBan", gia);
            sp = (TpQuanLyChiTietSanPhamCustom) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        return sp;
    }

    public List<TpQuanLyChiTietSanPhamCustom> findAllByGiaNhap(String giaNhap, MauConstant tt) {
        List<TpQuanLyChiTietSanPhamCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLyChiTietSanPhamCustom ("
                + "ct.id as id,"
                + "ct.soLuongTon as soLuongTon,"
                + "ct.hinhAnh as hinhAnh,"
                + "ct.GiaNhap as GiaNhap,"
                + "ct.GiaBan as GiaBan,"
                + "ct.mau as mau,"
                + "ct.sanPham as ten,"
                + "ct.donVi as donViGoc,"
                + "ct.namBaoHanh as namBaoHanh,"
                + "ct.trangThai as trangThai, "
                + "ct.size as size,"
                + "ct.ngayTao as ngayTao"
                + ") from domainModels.ChiTietSanPham ct WHERE ct.GiaNhap LIKE CONCAT('%',:GiaNhap,'%') AND ct.mau = :tt");
        q.setParameter("GiaNhap", giaNhap);
        q.setParameter("tt", tt);
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TpQuanLyChiTietSanPhamCustom> findAllByGiaBan(String giaBan, MauConstant tt) {
        List<TpQuanLyChiTietSanPhamCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLyChiTietSanPhamCustom ("
                + "ct.id as id,"
                + "ct.soLuongTon as soLuongTon,"
                + "ct.hinhAnh as hinhAnh,"
                + "ct.GiaNhap as GiaNhap,"
                + "ct.GiaBan as GiaBan,"
                + "ct.mau as mau,"
                + "ct.sanPham as ten,"
                + "ct.donVi as donViGoc,"
                + "ct.namBaoHanh as namBaoHanh,"
                + "ct.trangThai as trangThai, "
                + "ct.size as size,"
                + "ct.ngayTao as ngayTao"
                + ") from domainModels.ChiTietSanPham ct WHERE ct.GiaBan LIKE CONCAT('%',:GiaBan,'%') AND ct.mau = :tt ");
        q.setParameter("GiaBan", giaBan);
        q.setParameter("tt", tt);
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TpQuanLyChiTietSanPhamCustom> findAllByTenSanPham(String ten) {
        List<TpQuanLyChiTietSanPhamCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLyChiTietSanPhamCustom ("
                + "ct.id as id,"
                + "ct.soLuongTon as soLuongTon,"
                + "ct.hinhAnh as hinhAnh,"
                + "ct.GiaNhap as GiaNhap,"
                + "ct.GiaBan as GiaBan,"
                + "ct.mau as mau,"
                + "ct.sanPham as ten,"
                + "ct.donVi as donViGoc,"
                + "ct.namBaoHanh as namBaoHanh,"
                + "ct.trangThai as trangThai, "
                + "ct.size as size,"
                + "ct.ngayTao as ngayTao"
                + ") from domainModels.ChiTietSanPham ct WHERE ct.sanPham.ten LIKE CONCAT('%',:ten,'%') ");
        q.setParameter("ten", ten);
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TpQuanLyDonViCustom> getAllDonVi() {
        List<TpQuanLyDonViCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLyDonViCustom ("
                + "dv.id as id,"
                + "dv.donViGoc as donViGoc,"
                + "dv.donViQuyDoi as donViQuyDoi,"
                + "dv.soLuong as soLuong"
                + " )from domainModels.DonVi dv");
        list = q.getResultList();
        s.close();
        return list;

    }

    public List<TpQuanLyDonViCustom> getAllDonVi1() {
        List<TpQuanLyDonViCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLyDonViCustom ("
                + "dv.id as id,"
                + "dv.donViGoc as donViGoc,"
                + "dv.donViQuyDoi as donViQuyDoi,"
                + "dv.soLuong as soLuong"
                + " )FROM domainModels.DonVi dv");
        list = q.getResultList();
        s.close();
        return list;

    }

    public DonVi findIDDonVi(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        DonVi sp = s.find(DonVi.class, id);
        t.commit();
        s.close();
        return sp;
    }

    public List<TpQuanLySanPhamCustom> getAllSanPham() {
        List<TpQuanLySanPhamCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLySanPhamCustom ("
                + "sp.id as id,"
                + "sp.ma as ma,"
                + "sp.ten as ten"
                + ") from domainModels.SanPham sp");
        list = q.getResultList();
        s.close();
        return list;
    }

    public List<TpQuanLySanPhamCustom> getAllSanPham1() {
        List<TpQuanLySanPhamCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select new cores.truongPhongs.customModels.TpQuanLySanPhamCustom ("
                + "sp.id as id,"
                + "sp.ma as ma,"
                + "sp.ten as ten"
                + ") from domainModels.SanPham sp");
        list = q.getResultList();
        s.close();
        return list;
    }

    public SanPham findIDSanPham(UUID id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        SanPham sp = s.find(SanPham.class, id);
        t.commit();
        s.close();
        return sp;
    }
}
