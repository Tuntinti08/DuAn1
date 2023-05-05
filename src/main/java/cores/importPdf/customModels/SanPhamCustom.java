package cores.importPdf.customModels;

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
 * @author QUOC HUY
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamCustom {
    private String stt;
    
    private String ma;

    private String ten;
    
    private int soLuongTon;

    private BigDecimal GiaNhap;

    private int namBaoHanh;

    private int size;
    
    private String mau;
    
    private String donVi;
}
