package cores.truongPhongs.services;

import cores.truongPhongs.customModels.NhaCungCapCustom;
import infrastructures.constant.DanhGiaConstant;
import infrastructures.constant.KhachHangConstant;
import java.util.List;
import java.util.UUID;
import utilities.palette.Combobox;

/**
 *
 * @author admin
 */
public interface TpQuanlyNhaCungCapService {

    List<NhaCungCapCustom> getListByMa(String ma);

    List<NhaCungCapCustom> getList();

    NhaCungCapCustom addNhaCungCap(NhaCungCapCustom nccct);

    boolean updateNhaCungCap(NhaCungCapCustom nccct);

    boolean deleteNhaCungCap(UUID id);

    void loadComboxDanhGia(Combobox cbbDanhGia);

    void loadComboxTrangThai(Combobox cbbTrangThai);

    DanhGiaConstant locDG(int a);

    KhachHangConstant locKH(int a);

    NhaCungCapCustom findNCCByMa(String ma);

    List<NhaCungCapCustom> phanTrang(List<NhaCungCapCustom> list, int offset, int limit);
    List<NhaCungCapCustom> findAllByKhAndNV(String ma, KhachHangConstant tt, int rdo);
}
