package cores.truongPhongs.services;

import cores.truongPhongs.customModels.TpXemChiTietSanPhamCustom;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface TpXemChiTietSanPhamService {

    List<TpXemChiTietSanPhamCustom> listCtsp();

    TpXemChiTietSanPhamCustom addCTSanPham(TpXemChiTietSanPhamCustom custom);

    TpXemChiTietSanPhamCustom updateCTSanPham(TpXemChiTietSanPhamCustom custom);

    void updateCTSP(TpXemChiTietSanPhamCustom custom);

    TrangThaiSanPhamConstanst locTt(int a);

    List<TpXemChiTietSanPhamCustom> getListGiaNhap(BigDecimal giaBatDau, BigDecimal giaKetThuc);

    List<TpXemChiTietSanPhamCustom> phanTrang(List<TpXemChiTietSanPhamCustom> list, int offset, int limit);
    
     List<TpXemChiTietSanPhamCustom> findAllByKhAndNV(String ma, TrangThaiSanPhamConstanst tt, int rdo);
}
