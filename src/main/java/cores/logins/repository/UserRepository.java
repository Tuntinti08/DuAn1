package cores.logins.repository;

import cores.logins.custom.NhanVienCustom;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.HibernateUtil;

/**
 *
 * @author QUOC HUY
 */
public class UserRepository {
    public List<NhanVienCustom> getAllUser() {
        List<NhanVienCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("SELECT new cores.logins.custom.NhanVienCustom( "
                + " nv.id as id,"
                + " nv.ma as ma,"
                + " nv.ten as ten,"
                + " nv.sdt as sdt,"
                + " nv.email as email,"
                + " nv.matKhau as matKhau, "
                + " nv.ngaySinh as ngaySinh, "
                + " nv.hinhAnh as hinhAnh, "
                + " nv.gioiTinh as gioiTinh, "
                + " nv.diaChi as diaChi,"
                + " nv.trangThai as trangThai,"
                + " nv.idChucVu as chucVu"
                + " ) FROM domainModels.NhanVien nv");
        list = q.getResultList();
        s.close();
        return list;
    } 

}
