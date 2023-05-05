package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.NvqlGetTenNhanVienCustom;
import domainModels.NhanVien;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.HibernateUtil;

/**
 *
 * @author Acer
 */
public class NvqlGetTenNhanVienRepository {
      public List<NvqlGetTenNhanVienCustom> getListTen(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("Select new cores.nhanVienQuanLy.customModels.NvqlGetTenNhanVienCustom( "
                + "a.id as id,"
                + "a.ten as ten "
                + ") from NhanVien a");
        List<NvqlGetTenNhanVienCustom> listNv = q.getResultList();
        s.close();
        return listNv;
    }
      public NhanVien getNhanVienById(UUID id){
          Session s = HibernateUtil.getSessionFactory().openSession();
          NhanVien nv = s.find(NhanVien.class, id);
          s.close();
          return nv;
      }
      
}
