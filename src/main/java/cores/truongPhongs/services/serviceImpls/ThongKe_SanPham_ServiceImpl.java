
package cores.truongPhongs.services.serviceImpls;

import cores.truongPhongs.customModels.ThongKe_SanPham_Custom;
import cores.truongPhongs.repositories.ThongKe_SanPham_Repository;
import cores.truongPhongs.services.ThongKe_SanPham_Service;
import java.util.List;

/**
 *
 * @author asus
 */
public class ThongKe_SanPham_ServiceImpl implements ThongKe_SanPham_Service{
    private ThongKe_SanPham_Repository rp = new ThongKe_SanPham_Repository();
    @Override
    public List<ThongKe_SanPham_Custom> getABC(Long ngaybd , Long ngaykt) {
          return rp.getABC(ngaybd,ngaykt);
    }
    
}
