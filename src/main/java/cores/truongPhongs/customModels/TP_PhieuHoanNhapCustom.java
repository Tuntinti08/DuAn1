package cores.truongPhongs.customModels;

import domainModels.PhieuNhap;
import infrastructures.constant.TrangThaiPhieuHoanConstant;
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
public class TP_PhieuHoanNhapCustom {
    
    private UUID id;

    private Long ngayTao;

    private String ghiChu;
    
    private String liDo;

    private Long ngayThanhToan;

    private TrangThaiPhieuHoanConstant trangThai;

    private PhieuNhap phieuNhap;
}
