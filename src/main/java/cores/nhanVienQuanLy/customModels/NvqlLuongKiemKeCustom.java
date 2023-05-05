package cores.nhanVienQuanLy.customModels;

import domainModels.NhanVien;
import infrastructures.constant.TrangThaiPhieuKiemConstant;
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
public class NvqlLuongKiemKeCustom {

    private UUID id;
    private String maPhieuKiem;
    private Long ngayTao;
    private NhanVien idNV;
    private TrangThaiPhieuKiemConstant trangThai;
    private String ghiChu;

}
