package cores.truongPhongs.customModels;

import domainModels.NhaCungCap;
import domainModels.NhanVien;
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
public class TP_HoanNhap_PhieuNhapCustom {

    private UUID id;
    private String maPhieu;
    private Long ngayTao;

    private String ghiChu;

    private Long ngayThanhToan;

    private TrangThaiPhieuConstant trangThai;

    private NhanVien nhanVien;

    private NhaCungCap nhaCungCap;
}
