package cores.nhanVienQuanLy.services;

import cores.logins.custom.NhanVienCustom;
import cores.nhanVienQuanLy.customModels.NvqlXemThongTinCaNhanCustom;
import java.util.List;

/**
 *
 * @author window
 */
public interface NvqlXemThongTinCaNhanService {
    public List<NvqlXemThongTinCaNhanCustom> getAll();
    public void doiMatKhau(NhanVienCustom nvc);
    public boolean checkMatKhau(String matKhau);
    public NvqlXemThongTinCaNhanCustom getMatKhauByEmail(String email);
}
