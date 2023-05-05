package cores.khachHangs.services;

import cores.khachHangs.customModels.KhXemThongTinCaNhanCustom;
import java.util.List;

/**
 *
 * @author window
 */
public interface KhXemThongTinCaNhanService {
    public List<KhXemThongTinCaNhanCustom> getAll();
    public void doiMatKhau(String pass);
    public boolean checkMatKhau(String matKhau);
}
