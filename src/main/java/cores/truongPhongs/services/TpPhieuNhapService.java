package cores.truongPhongs.services;

import cores.truongPhongs.customModels.TpPhieuNhapCustom;
import domainModels.NhaCungCap;
import domainModels.NhanVien;
import infrastructures.constant.TrangThaiPhieuConstant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;

/**
 *
 * @author Acer
 */
public interface TpPhieuNhapService {
     List<TpPhieuNhapCustom> getListPnById(String ghiChu);
    List<TpPhieuNhapCustom> getListPn();
    TpPhieuNhapCustom addPn(TpPhieuNhapCustom p);
    boolean updatePn(TpPhieuNhapCustom p);
    boolean deletePn(UUID id);
    TpPhieuNhapCustom checkValidate(String ghiChu,Date ngayThanhToan,JLabel errNgayThanhToan,JLabel errGhiChu);
    TpPhieuNhapCustom findPhieuNhapById(UUID id);
    TrangThaiPhieuConstant loc(int a);
    List<TpPhieuNhapCustom> getListByNgayThanhToan(Long ngayBatDau, Long ngayKetThuc);
    List<TpPhieuNhapCustom> getListByNgayTao(Long ngayBatDau, Long ngayKetThuc);
    public NhanVien getNhanVienByMa(String ma);
     List<TpPhieuNhapCustom> findAllByKhAndNV(String ma, TrangThaiPhieuConstant tt, int rdo);
    List<TpPhieuNhapCustom> phanTrang(List<TpPhieuNhapCustom> list, int offset, int limit);

}

    