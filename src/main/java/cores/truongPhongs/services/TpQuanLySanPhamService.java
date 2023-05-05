package cores.truongPhongs.services;

import cores.truongPhongs.customModels.TpQuanLySanPhamCustom;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;

/**
 *
 * @author MMC
 */
public interface TpQuanLySanPhamService {

    List<TpQuanLySanPhamCustom> getAll(String ten);

    List<TpQuanLySanPhamCustom> getByMaSpNcc(String ten);

    TpQuanLySanPhamCustom addSanPham(TpQuanLySanPhamCustom custom);

    boolean updateSanPham(TpQuanLySanPhamCustom custom);

    boolean deleteSanPham(UUID id);

    TpQuanLySanPhamCustom checkValidate(TpQuanLySanPhamCustom sp, JLabel erroMa, JLabel erroTen);

    List<TpQuanLySanPhamCustom> phanTrang(List<TpQuanLySanPhamCustom> list, int offset, int limit);

}
