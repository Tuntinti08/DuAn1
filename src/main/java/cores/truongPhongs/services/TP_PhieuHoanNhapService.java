package cores.truongPhongs.services;

import cores.truongPhongs.customModels.TP_HoanNhap_PhieuNhapCustom;
import cores.truongPhongs.customModels.TP_HoanNhap_ctpCusTom;
import cores.truongPhongs.customModels.TP_HoanNhap_spCustom;
import cores.truongPhongs.customModels.TP_PhieuHoanNhapCustom;
import infrastructures.constant.TrangThaiPhieuHoanConstant;
import infrastructures.constant.TrangThaiPhieuNhapConstant;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author QUOC HUY
 */
public interface TP_PhieuHoanNhapService {

    List<TP_PhieuHoanNhapCustom> getListPhieuHoanNhap();

    List<TP_HoanNhap_PhieuNhapCustom> getListPhieuNhap();

    List<TP_HoanNhap_PhieuNhapCustom> findAllPnBy(String ma, int rdo);

    List<TP_HoanNhap_PhieuNhapCustom> getListByNgayTaoPhieuNhap(Long ngayBatDau, Long ngayKetThuc);

    List<TP_HoanNhap_spCustom> getListSpByPhieuNhap(UUID idPhieuNhap);

    List<TP_HoanNhap_spCustom> getListByMaSpAndTenSpByPhieuNhap(UUID idPhieuNhap, String ma, int rdo);

    List<TP_HoanNhap_ctpCusTom> getListSpByChiTietPhieuHoanNhap(UUID idPhieuHoan);

    List<TP_HoanNhap_ctpCusTom> getListSpByMaByChiTietPhieuHoanNhap(UUID idPhieuHoan, String ma, int rdo);

    boolean addPhieuNhap(TP_HoanNhap_PhieuNhapCustom pn, String ghiChu, String lyDo);

    boolean addSanPhamInPhieuHoan(UUID sp, UUID idPhieuNhapHoan, int soLuongNhap, String lyDo);

    boolean removeSanPhamInPhieuHoan(UUID sp, UUID idPhieuNhapHoan, int soLuongNhap);

    void updatePhieuHoanNhap(TP_PhieuHoanNhapCustom phnct);

    List<TP_PhieuHoanNhapCustom> phanTrang(List<TP_PhieuHoanNhapCustom> list, int offset, int limit);

    List<TP_PhieuHoanNhapCustom> findAllByKhAndNV(String ma, TrangThaiPhieuHoanConstant tt, int rdo);

    List<TP_PhieuHoanNhapCustom> getListByNgayTao(Long ngayBatDau, Long ngayKetThuc);

    List<TP_PhieuHoanNhapCustom> getListByNgayThanhToan(Long ngayBatDau, Long ngayKetThuc);

    TrangThaiPhieuHoanConstant loc(int a);

}
