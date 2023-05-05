package cores.logins.custom;

import domainModels.ChucVu;
import infrastructures.constant.GioiTinhConstant;
import infrastructures.constant.TrangThaiNhanVienConstant;
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
public class NhanVienCustom {

    private UUID id;
    private String ma;
    private String ten;
    private String sdt;
    private String email;
    private String matKhau;
    private Long ngaySinh;
    private String hinhAnh;
    private GioiTinhConstant gioiTinh;
    private String diaChi;
    private TrangThaiNhanVienConstant trangThai;
    private ChucVu chucVu;
    
    
}
