package cores.nhanVienQuanLy.customModels;

import domainModels.KhachHang;
import domainModels.NhanVien;
import infrastructures.constant.TrangThaiPhieuConstant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuXuatCustom {
    private UUID id;
    private String maPhieu;
    private Long ngayTao;
    private String ghiChu;
    private Long ngayThanhToan;
    private TrangThaiPhieuConstant trangThai;
    private NhanVien nhanVien;
    private KhachHang khachHang;
   
}
