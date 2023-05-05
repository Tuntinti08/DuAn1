package cores.truongPhongs.services;

import cores.truongPhongs.customModels.TP_ChucVuCustom;
import cores.truongPhongs.customModels.TpNhanVienCustom;
import domainModels.ChucVu;
import infrastructures.constant.GioiTinhConstant;
import infrastructures.constant.TrangThaiNhanVienConstant;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;
import utilities.palette.Combobox;

/**
 *
 * @author LENOVO
 */
public interface TpQuanLyNhanVienSevice {

    List<TpNhanVienCustom> getListNhanVien();

    TpNhanVienCustom addNhanVien(TpNhanVienCustom ct);

    boolean updateNhanVien(TpNhanVienCustom ct);

    boolean deleteNhanVien(UUID id);

    void loadComboboxTT(Combobox cbb);

    void loadComboboxGT(Combobox cbb);

    TpNhanVienCustom checkValidateCreate(TpNhanVienCustom nv, JLabel erroMa, JLabel erroTen,
            JLabel erroEmail, JLabel erroSDT, JLabel erroMatKhau,
            JLabel erroDiaChi, JLabel erroNgaySinh);

    TpNhanVienCustom checkValidateUpdate(TpNhanVienCustom nv, JLabel erroMa, JLabel erroTen,
            JLabel erroEmail, JLabel erroSDT, JLabel erroMatKhau,
            JLabel erroDiaChi, JLabel erroNgaySinh);

    TrangThaiNhanVienConstant loc(int a);

    GioiTinhConstant loc1(int b);

    TpNhanVienCustom finNhanVienByMa(String ma);

    List<TP_ChucVuCustom> getListCV();

    public ChucVu findIDCV(UUID id);

    List<TpNhanVienCustom> findAllByRadio(String ten, TrangThaiNhanVienConstant tt, int rdo);

    List<TpNhanVienCustom> phanTrang(List<TpNhanVienCustom> list, int offset, int limit);

}
