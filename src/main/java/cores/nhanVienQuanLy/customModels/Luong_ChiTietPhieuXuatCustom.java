package cores.nhanVienQuanLy.customModels;

import domainModels.ChiTietSanPham;
import domainModels.PhieuXuat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Luong_ChiTietPhieuXuatCustom {
    private PhieuXuat idPhieuXuat;
    private ChiTietSanPham idChiTietSp;
    private int soLuong;
}
