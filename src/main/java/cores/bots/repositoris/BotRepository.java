package cores.bots.repositoris;

import domainModels.ChiTietPhieuXuat;
import domainModels.PhieuXuat;
import infrastructures.constant.TrangThaiPhieuConstant;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.DateTimeUtil;
import utilities.HibernateUtil;

/**
 *
 * @author QUOC HUY
 */
public class BotRepository {

    public boolean updateTrangThai() {
        Transaction tran = null;
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            tran = s.beginTransaction();
            Query q = s.createQuery("FROM ChiTietPhieuXuat ct"
                    + " WHERE ct.idPhieuXuat.ngayThanhToan IS NULL AND ct.idPhieuXuat.ngayTao <= :ngayHienTai AND ct.idPhieuXuat.trangThai != :trangThai");
            q.setParameter("ngayHienTai", DateTimeUtil.convertDateToTimeStampSecond() - 172804492);
            q.setParameter("trangThai", TrangThaiPhieuConstant.DA_HUY);
            List<ChiTietPhieuXuat> list = q.getResultList();
            list.parallelStream().forEach(el -> {
                el.getIdPhieuXuat().setTrangThai(TrangThaiPhieuConstant.DA_HUY);
                el.getIdChiTietSp().setSoLuongTon(el.getIdChiTietSp().getSoLuongTon() + el.getSoLuong());
                s.update(el.getIdPhieuXuat());
                s.update(el.getIdChiTietSp());
            });
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            return false;
        }
    }

}
