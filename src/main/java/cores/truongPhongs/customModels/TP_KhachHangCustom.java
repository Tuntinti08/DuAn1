package cores.truongPhongs.customModels;

import infrastructures.constant.DanhGiaConstant;
import infrastructures.constant.GioiTinhConstant;
import infrastructures.constant.KhachHangConstant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TP_KhachHangCustom {

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
    private DanhGiaConstant danhGia;
    private KhachHangConstant trangThai;

    public TP_KhachHangCustom(KhachHangConstant trangThai) {
        this.trangThai = trangThai;
    }
    
    
}

