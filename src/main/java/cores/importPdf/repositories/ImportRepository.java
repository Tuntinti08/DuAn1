package cores.importPdf.repositories;

import cores.importPdf.customModels.ChiTietSanPhamCustom;
import domainModels.ChiTietPhieuHoanNhap;
import domainModels.ChiTietPhieuNhap;
import domainModels.ChiTietSanPham;
import domainModels.DonVi;
import domainModels.PhieuNhap;
import domainModels.SanPham;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.DateTimeUtil;
import utilities.HibernateUtil;

/**
 *
 * @author QUOC HUY
 */
public class ImportRepository {

    public List<DonVi> findDonViByDonViQuyDoi() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM domainModels.DonVi dv");
        List<DonVi> dv = new ArrayList<>();
        try {
            dv = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        s.close();
        return dv;
    }

    public List<SanPham> findSanPhamByTenSanPham() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM domainModels.SanPham sp");
        List<SanPham> sp = new ArrayList<>();
        try {
            sp = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        s.close();
        return sp;
    }

    public boolean insertChiTietSanPham(ChiTietSanPhamCustom sp) {
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tran = s.beginTransaction();
            s.saveOrUpdate(sp.getSanPham());
            s.saveOrUpdate(sp.getDonVi());
            ChiTietSanPham ctsp = new ChiTietSanPham();
            ctsp.setMau(sp.getMau());
            ctsp.setSoLuongTon(sp.getSoLuongTon());
            ctsp.setNamBaoHanh(sp.getNamBaoHanh());
            ctsp.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
            ctsp.setSanPham(sp.getSanPham());
            ctsp.setGiaNhap(sp.getGiaNhap());
            ctsp.setDonVi(sp.getDonVi());
            ctsp.setSize(sp.getSize());
            ctsp.setTrangThai(TrangThaiSanPhamConstanst.CHO_XAC_NHAN);
            s.save(ctsp);

            PhieuNhap pn = s.find(PhieuNhap.class, sp.getIdPhieuNhap());

            ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(pn, ctsp, sp.getSoLuongTon(), sp.getMaSpNcc());
            s.save(ctpn);
            tran.commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
