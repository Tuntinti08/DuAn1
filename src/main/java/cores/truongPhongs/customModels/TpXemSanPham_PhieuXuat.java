package cores.truongPhongs.customModels;

import infrastructures.constant.MauConstant;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author QUOC HUY
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TpXemSanPham_PhieuXuat {
    
    private UUID id;
    private String maSp;
    private String tenSp;
    private int soLuongBan;
    private String hinhAnh;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private int mau;
    private int namBaoHanh;
    private int size;
    private Long ngayNhap;
    private String maPhieuNhap;
    private Long ngayTao;
    private String ghiChu;
    private Long ngayThanhToan;
    private String maNcc;
    private String tenNcc;
    private String diaChiNcc;
    private String emailNcc;
    private String sdtNcc;
    private String maSpNcc;
    
    public MauConstant convertMau() {
        switch (mau) {
            case 0:
                return MauConstant.VANG;
            case 1:
                return MauConstant.XANH_LA;
            case 2:
                return MauConstant.DO;
            case 3:
                return MauConstant.XANH_DUONG;
            case 4:
                return MauConstant.HONG;
            case 5:
                return MauConstant.CAM;
            case 6:
                return MauConstant.DEN;
            case 7:
                return MauConstant.TRANG;
            default:
                return MauConstant.KHAC;
        }
    }
    
}
