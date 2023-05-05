package cores.truongPhongs.customModels;

import domainModels.DonVi;
import domainModels.SanPham;
import infrastructures.constant.MauConstant;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author MMC
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TpQuanLyChiTietSanPhamCustom {

    private UUID id;

    private int soLuongTon;

    private String hinhAnh;

    private BigDecimal giaNhap;

    private BigDecimal giaBan;

    private int mau;

    private String doViQuyDoi;

    private int namBaoHanh;

    private int trangThai;

    private int size;

    private String tenNcc;

    private String maSpNcc;
    
    private Long ngayNhap;
    
    private UUID idDv;

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
    
    public TrangThaiSanPhamConstanst convertTrangThai() {
        switch (trangThai) {
            case 0:return TrangThaiSanPhamConstanst.CHO_XAC_NHAN;
            case 1:return TrangThaiSanPhamConstanst.DA_MO_BAN;
            default:return TrangThaiSanPhamConstanst.DUNG_BAN;
        }
    }
}
