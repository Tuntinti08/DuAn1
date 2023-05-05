package cores.truongPhongs.services;

import cores.truongPhongs.customModels.TP_KhachHangCustom;
import infrastructures.constant.DanhGiaConstant;
import infrastructures.constant.GioiTinhConstant;
import infrastructures.constant.KhachHangConstant;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;
import utilities.palette.Combobox;

public interface TP_KhachHangService {

    List<TP_KhachHangCustom> getListKH();
    List<TP_KhachHangCustom> getListKHByTrangThai();
    TP_KhachHangCustom addKH(TP_KhachHangCustom custom);

    boolean updateKH(TP_KhachHangCustom custom);

//    boolean deleteKH(UUID id);

//    boolean deleteKH(TP_KhachHangCustom custom);
    void loadCbbTT(Combobox cbb);

    void loadCbbGT(Combobox cbb);

    void loadCbbDG(Combobox cbb);

    TP_KhachHangCustom checkValidateCreate(TP_KhachHangCustom kh, JLabel erroMa, JLabel erroTen, JLabel erroSDT, JLabel erroEmail, JLabel erroDiaChi, JLabel erroMatKhau, JLabel erroNgaySinh);

    TP_KhachHangCustom checkValidateUpdate(TP_KhachHangCustom kh, JLabel erroMa, JLabel erroTen, JLabel erroSDT, JLabel erroEmail, JLabel erroDiaChi, JLabel erroMatKhau, JLabel erroNgaySinh);

    KhachHangConstant loc(int a);

    DanhGiaConstant loc1(int b);

    GioiTinhConstant loc2(int c);

    TP_KhachHangCustom findKHByMa(String ma);

    List<TP_KhachHangCustom> findAllByRadio(String tk, KhachHangConstant tt, int rdo);

    List<TP_KhachHangCustom> phanTrang(List<TP_KhachHangCustom> list, int offset, int limit);

}
