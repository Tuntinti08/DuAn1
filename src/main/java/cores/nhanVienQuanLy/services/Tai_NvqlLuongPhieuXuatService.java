package cores.nhanVienQuanLy.services;

import cores.nhanVienQuanLy.customModels.LuongBanHang_ChiTietSanPhamCustom;
import cores.nhanVienQuanLy.customModels.Luong_ChiTietPhieuXuatCustom;
import cores.nhanVienQuanLy.customModels.PhieuXuatCustom;
import cores.nhanVienQuanLy.customModels.Tai_SanPhamCustom;
import domainModels.ChiTietPhieuNhap;
import domainModels.NhanVien;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author admin
 */
public interface Tai_NvqlLuongPhieuXuatService {

    public List<LuongBanHang_ChiTietSanPhamCustom> getListCTSanPhamBanHang();
    public NhanVien getNhanVienByMa(String ma);
    List<Luong_ChiTietPhieuXuatCustom> getListCTPhieuXuatByID(UUID idPX);
    public List<LuongBanHang_ChiTietSanPhamCustom> getListCTSanPham();
    public List<LuongBanHang_ChiTietSanPhamCustom> getListCTSanPhamByID(UUID id);
    public void addCTPX(Luong_ChiTietPhieuXuatCustom ctpxct);
    public void updateCTSP(LuongBanHang_ChiTietSanPhamCustom ctspct);
    void updateCTPX(Luong_ChiTietPhieuXuatCustom ctpxct);
    List<Luong_ChiTietPhieuXuatCustom> getListCTPhieuXuat();
    List<LuongBanHang_ChiTietSanPhamCustom> phanTrang(List<LuongBanHang_ChiTietSanPhamCustom> list, int offset, int limit);
    public ChiTietPhieuNhap findCTpnByID(UUID idCTSP);
    public List<Tai_SanPhamCustom> getListSP();
    public List<Tai_SanPhamCustom> getListSPByMa(String ma,int rdo);
    List<Tai_SanPhamCustom> phanTrangSP(List<Tai_SanPhamCustom> list, int offset, int limit);
}
