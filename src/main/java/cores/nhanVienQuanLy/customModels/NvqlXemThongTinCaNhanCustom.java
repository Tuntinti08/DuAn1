package cores.nhanVienQuanLy.customModels;

import infrastructures.constant.GioiTinhConstant;
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
public class NvqlXemThongTinCaNhanCustom {

    private UUID id;
    private String ma;
    private String ten;
    private String sdt;
    private String email;
    private String matKhau;
    private Long ngaySinh;
    private GioiTinhConstant gioiTinh;
    private String diaChi;

}
