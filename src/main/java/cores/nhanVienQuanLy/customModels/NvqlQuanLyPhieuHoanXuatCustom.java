package cores.nhanVienQuanLy.customModels;

import domainModels.PhieuXuat;
import infrastructures.constant.TrangThaiPhieuHoanConstant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author window
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NvqlQuanLyPhieuHoanXuatCustom {

    private UUID id;
    private Long ngayTao;
    private Long ngayThanhToan;
    private String ghiChu;
    private String liDo;
    private TrangThaiPhieuHoanConstant trangThai;
    private PhieuXuat phieuXuat;
}
