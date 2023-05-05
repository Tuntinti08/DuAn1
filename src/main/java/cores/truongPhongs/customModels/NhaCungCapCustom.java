package cores.truongPhongs.customModels;

import infrastructures.constant.DanhGiaConstant;
import infrastructures.constant.KhachHangConstant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCapCustom {
    private UUID id;
    private String ma;
    private String ten;
    private String diaChi;
    private String email;
    private String sdt;
    private DanhGiaConstant danhGia;
    private KhachHangConstant trangThai;
}
