package cores.truongPhongs.customModels;

import domainModels.DonVi;
import domainModels.SanPham;
import infrastructures.constant.MauConstant;
import java.math.BigDecimal;
import java.util.UUID;
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
public class TP_HoanNhap_ctpCusTom {
    
    private UUID id;
  
    private int soLuongTon;

    private String hinhAnh;

    private BigDecimal GiaNhap;

    private BigDecimal GiaBan;

    private int namBaoHanh;

    private MauConstant mau;

    private SanPham sanPham;

    private DonVi donVi;

    private int soLuongHoan;
    
    private String lyDo;
}
