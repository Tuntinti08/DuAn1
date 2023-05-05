package cores.nhanVienQuanLy.services;

import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtpkCustom;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author window
 */
public interface NvqlLuongKiemKeCtpkService {
    public List<NvqlLuongKiemKeCtpkCustom> getAll(UUID id);
    List<NvqlLuongKiemKeCtpkCustom> phanTrang(List<NvqlLuongKiemKeCtpkCustom> list, int offset, int limit);
    public void addCTPK(NvqlLuongKiemKeCtpkCustom nvqlLuongKiemKeCtpkCustom);
    void updateCTPKK(NvqlLuongKiemKeCtpkCustom ctpkCustom);
    boolean updateSoLuongTon(UUID ctsp, int soLuong);
    
}
