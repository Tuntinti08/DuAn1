package cores.truongPhongs.services;

import cores.truongPhongs.customModels.TpXemPhieuXuatCustom;
import cores.truongPhongs.customModels.TpXemSanPham_PhieuXuat;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author QUOC HUY
 */
public interface TpXemPhieuXuatService {
    List<TpXemPhieuXuatCustom> getListPhieuXuat(String maPhieu,
            String tenKh, String maKh, String diaChiKh, String emailKh, String sdtKh,
            String maSp, String tenSp,
            String maPn,
            String tenNcc, String maNcc, String diaChiNcc, String emailNcc, String sdtNcc, String maSpNcc);
    List<TpXemPhieuXuatCustom> phanTrangPhieuXuat(List<TpXemPhieuXuatCustom> list, int offset, int limit);
    List<TpXemSanPham_PhieuXuat> getListSanPhamByPhieuXuat(UUID idPhieuXuat,
            String maSp, String tenSp,
            String maPn,
            String tenNcc, String maNcc, String diaChiNcc, String emailNcc, String sdtNcc, String maSpNcc);
}
