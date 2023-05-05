package cores.truongPhongs.customModels;

import infrastructures.constant.DanhGiaConstant;
import infrastructures.constant.GioiTinhConstant;
import infrastructures.constant.TrangThaiPhieuConstant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author QUOC HUY
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TpXemPhieuXuatCustom {

    private UUID id;
    private String ghiChu;
    private String maPhieu;
    private Long ngayTao;
    private Long ngayThanhToan;
    private int trangThai;
    private String maKhachHang;
    private String TenKhachHang;
    private String sdtKh;
    private String emailKh;
    private String matKhauKh;
    private Long ngaySinhKh;
    private String hinhAnhKh;
    private int gioiTinhKh;
    private String diaChiKh;
    private int danhGiaKh;
    
    public TrangThaiPhieuConstant convertTrangThai() {
        switch (trangThai) {
            case 0: return TrangThaiPhieuConstant.CHO_THANH_TOAN;
            case 1: return TrangThaiPhieuConstant.DA_HUY;
            default:return TrangThaiPhieuConstant.CHO_THANH_TOAN;
        }
    }
    
    public GioiTinhConstant convertGioiTinh() {
        switch (gioiTinhKh) {
            case 0:return GioiTinhConstant.NAM;
            case 1:return GioiTinhConstant.NU;
            default:return GioiTinhConstant.KHAC;
        }
    }
    
    public DanhGiaConstant convertDanhGia() {
        switch (danhGiaKh) {
            case 0:return DanhGiaConstant.TOT;
            case 1:return DanhGiaConstant.TAM_ON;
            case 2:return DanhGiaConstant.BAT_ON;
            default:return DanhGiaConstant.XAU;
        }
    }
    
}
