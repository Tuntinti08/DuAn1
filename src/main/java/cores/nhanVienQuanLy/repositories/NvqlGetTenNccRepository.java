package cores.nhanVienQuanLy.repositories;

import cores.nhanVienQuanLy.customModels.NvqlGetTenNccCustom;
import domainModels.NhaCungCap;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import utilities.HibernateUtil;


/**
 *
 * @author Acer
 */
public class NvqlGetTenNccRepository {
    public List<NvqlGetTenNccCustom> getListTen(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("Select new cores.nhanVienQuanLy.customModels.NvqlGetTenNccCustom ( "
                + "a.id as id,"
                + "a.ten as ten"
                + ") from domainModels.NhaCungCap a");
        List<NvqlGetTenNccCustom> listNcc = q.getResultList();
        s.close();
        return listNcc;
    }
    public NhaCungCap getNccById(UUID id){
          Session s = HibernateUtil.getSessionFactory().openSession();
          NhaCungCap ncc = s.find(NhaCungCap.class, id);
          s.close();
          return ncc;
      }
}
