package cores.nhanVienQuanLy.services;

import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCustom;
import infrastructures.constant.TrangThaiPhieuKiemConstant;
import java.util.List;

/**
 *
 * @author window
 */
public interface NvqlLuongKiemKeService {

    public List<NvqlLuongKiemKeCustom> getAll();

    void Insert(NvqlLuongKiemKeCustom phieuKiemKe);

    boolean UpdateTrangThai(NvqlLuongKiemKeCustom phieuKiemKe);

    TrangThaiPhieuKiemConstant loc(int a);

    List<NvqlLuongKiemKeCustom> getListByNgayTao(Long ngayBatDau, Long ngayKetThuc);

    List<NvqlLuongKiemKeCustom> phanTrang(List<NvqlLuongKiemKeCustom> list, int offset, int limit);
    
    List<NvqlLuongKiemKeCustom> findAllByKhAndNV(String ma, TrangThaiPhieuKiemConstant tt, int rdo);
}
