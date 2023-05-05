package cores.nhanVienQuanLy.services;

import cores.nhanVienQuanLy.customModels.NvqlQuanLyPhieuHoanXuatCustom;
import domainModels.PhieuXuat;
import infrastructures.constant.TrangThaiPhieuHoanConstant;
import java.util.List;
import java.util.UUID;
import utilities.palette.Combobox;

/**
 *
 * @author admin
 */
public interface NvqlQuanLyPhieuHoanXuatService {
    List<NvqlQuanLyPhieuHoanXuatCustom> getList();
    List<NvqlQuanLyPhieuHoanXuatCustom> getListByNgayTao(Long ngayTao, Long ngayKetThuc);
    NvqlQuanLyPhieuHoanXuatCustom addPhieuHoanXuat(NvqlQuanLyPhieuHoanXuatCustom phncs);
    boolean updatePhieuHoanXuat(NvqlQuanLyPhieuHoanXuatCustom phncs);
    boolean deletePhieuHoanXuat(UUID id);
    NvqlQuanLyPhieuHoanXuatCustom findByID(UUID id);
    void loadComBoBoxPx(Combobox cbbPX);
    List<PhieuXuat> getListMaPhieuXuat();
    PhieuXuat chonPX(int chon);
    TrangThaiPhieuHoanConstant loc(int a);
    List<NvqlQuanLyPhieuHoanXuatCustom> getListByNgayThanhToan(Long ngayBatDau, Long ngayKetThuc);
}
