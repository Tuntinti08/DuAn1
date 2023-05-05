package cores.nhanVienQuanLy.services.serviceImpls;

import cores.nhanVienQuanLy.customModels.LuongBanHang_ChiTietSanPhamCustom;
import cores.nhanVienQuanLy.customModels.Luong_ChiTietPhieuXuatCustom;
import cores.nhanVienQuanLy.customModels.PhieuXuatCustom;
import cores.nhanVienQuanLy.customModels.Tai_SanPhamCustom;
import cores.nhanVienQuanLy.repositories.Tai_NvqlLuongPhieuXuatRepository;
import cores.nhanVienQuanLy.services.Tai_NvqlLuongPhieuXuatService;
import domainModels.ChiTietPhieuNhap;
import domainModels.ChiTietPhieuXuat;
import domainModels.ChiTietSanPham;
import domainModels.NhanVien;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author admin
 */
public class Tai_NvqlLuongPhieuXuatServiceImpl implements Tai_NvqlLuongPhieuXuatService {

    private Tai_NvqlLuongPhieuXuatRepository rp;

    public Tai_NvqlLuongPhieuXuatServiceImpl() {
        rp = new Tai_NvqlLuongPhieuXuatRepository();
    }

    @Override
    public List<LuongBanHang_ChiTietSanPhamCustom> getListCTSanPhamBanHang() {
        return rp.getListCTSanPhamBanHang();
    }

    @Override
    public List<Luong_ChiTietPhieuXuatCustom> getListCTPhieuXuatByID(UUID idPX) {
        return rp.getListCTPhieuXuat(idPX);
    }

    @Override
    public List<LuongBanHang_ChiTietSanPhamCustom> getListCTSanPham() {
        return rp.getListCTSanPham();
    }

    @Override
    public void addCTPX(Luong_ChiTietPhieuXuatCustom ctpxct) {
        ChiTietPhieuXuat ctpx = new ChiTietPhieuXuat();
        ctpx.setIdChiTietSp(ctpxct.getIdChiTietSp());
        ctpx.setIdPhieuXuat(ctpxct.getIdPhieuXuat());
        ctpx.setSoLuong(ctpxct.getSoLuong());
        rp.addCTPX(ctpx);
    }

    @Override
    public void updateCTSP(LuongBanHang_ChiTietSanPhamCustom ctspct) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setDonVi(ctspct.getDonVi());
        ctsp.setGiaBan(ctspct.getGiaBan());
        ctsp.setGiaNhap(ctspct.getGiaNhap());
        ctsp.setHinhAnh(ctspct.getHinhAnh());
        ctsp.setMau(ctspct.getMau());
        ctsp.setNamBaoHanh(ctspct.getNamBaoHanh());
        ctsp.setSanPham(ctspct.getSanPham());
        ctsp.setSoLuongTon(ctspct.getSoLuongTon());
        ctsp.setTrangThai(ctspct.getTrangThai());
        ctsp.setId(ctspct.getId());
        rp.updateCTSP(ctsp);
    }

    @Override
    public void updateCTPX(Luong_ChiTietPhieuXuatCustom ctpxct) {
        ChiTietPhieuXuat ctpx = new ChiTietPhieuXuat();
        ctpx.setIdChiTietSp(ctpxct.getIdChiTietSp());
        ctpx.setIdPhieuXuat(ctpxct.getIdPhieuXuat());
        ctpx.setSoLuong(ctpxct.getSoLuong());
        rp.updateCTPX(ctpx);
    }

    @Override
    public List<Luong_ChiTietPhieuXuatCustom> getListCTPhieuXuat() {
        return rp.getListCTPhieuXuat();
    }

    @Override
    public NhanVien getNhanVienByMa(String ma) {
        return rp.getNhanVienByMa(ma);
    }

    @Override
    public List<LuongBanHang_ChiTietSanPhamCustom> phanTrang(List<LuongBanHang_ChiTietSanPhamCustom> list, int offset, int limit) {
        List<LuongBanHang_ChiTietSanPhamCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            LuongBanHang_ChiTietSanPhamCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

    @Override
    public List<LuongBanHang_ChiTietSanPhamCustom> getListCTSanPhamByID(UUID id) {
        return rp.getListCTSanPhamByID(id);
    }

    @Override
    public ChiTietPhieuNhap findCTpnByID(UUID idCTSP) {
        return rp.findCTpnByID(idCTSP);
    }

    @Override
    public List<Tai_SanPhamCustom> getListSP() {
        return rp.getListSP();
    }

    @Override
    public List<Tai_SanPhamCustom> getListSPByMa(String ma, int rdo) {
        switch (rdo) {
            case 0:
                return rp.getListSPByMa(ma);
            case 1:
                return rp.getListSPByTen(ma);
            default:
                return null;
        }
    }

    @Override
    public List<Tai_SanPhamCustom> phanTrangSP(List<Tai_SanPhamCustom> list, int offset, int limit) {
        List<Tai_SanPhamCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            Tai_SanPhamCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

}
