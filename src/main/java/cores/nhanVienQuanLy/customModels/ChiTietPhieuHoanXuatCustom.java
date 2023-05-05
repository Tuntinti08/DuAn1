package cores.nhanVienQuanLy.customModels;

import domainModels.ChiTietSanPham;
import domainModels.PhieuHoanXuat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietPhieuHoanXuatCustom {
    private PhieuHoanXuat idPhieuHoanXuat;
    private ChiTietSanPham idChiTietSp;
    private int soLuong;
    private String liDo;
}
