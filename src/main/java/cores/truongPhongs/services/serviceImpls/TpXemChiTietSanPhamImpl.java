package cores.truongPhongs.services.serviceImpls;

import cores.truongPhongs.customModels.TpQuanLyChiTietSanPhamCustom;
import cores.truongPhongs.customModels.TpXemChiTietSanPhamCustom;
import cores.truongPhongs.repositories.TpXemChiTietSanPhamRepository;
import cores.truongPhongs.services.TpXemChiTietSanPhamService;
import domainModels.ChiTietSanPham;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class TpXemChiTietSanPhamImpl implements TpXemChiTietSanPhamService {

    private TpXemChiTietSanPhamRepository repo;

    public TpXemChiTietSanPhamImpl() {
        repo = new TpXemChiTietSanPhamRepository();
    }

    @Override
    public List<TpXemChiTietSanPhamCustom> listCtsp() {
        return repo.getAll();
    }

    @Override
    public TpXemChiTietSanPhamCustom addCTSanPham(TpXemChiTietSanPhamCustom custom) {
        ChiTietSanPham sp = new ChiTietSanPham();
        sp.setGiaBan(custom.getGiaBan());
        sp.setGiaNhap(custom.getGiaNhap());
        sp.setHinhAnh(custom.getHinhAnh());
        sp.setSoLuongTon(custom.getSoLuongTon());
        sp.setMau(custom.getMau());
        sp.setDonVi(custom.getDonVi());
        sp.setSize(custom.getSize());
        sp.setTrangThai(custom.getTrangThai());
        sp.setNamBaoHanh(custom.getNamBaoHanh());
        sp.setSanPham(custom.getSanPham());
        sp.setNgayTao(custom.getNgayTao());
        custom.setId(repo.addCTSanPham(sp).getId());
        return custom;
    }

    @Override
    public TrangThaiSanPhamConstanst locTt(int a) {
        switch (a) {
            case 0:
                return TrangThaiSanPhamConstanst.CHO_XAC_NHAN;
            case 1:
                return TrangThaiSanPhamConstanst.DA_MO_BAN;
            default:
                return null;
        }

    }

    @Override
    public List<TpXemChiTietSanPhamCustom> getListGiaNhap(BigDecimal giaBatDau, BigDecimal giaKetThuc) {
        return repo.getListByGiaNhap(giaBatDau, giaKetThuc);
    }

    @Override
    public TpXemChiTietSanPhamCustom updateCTSanPham(TpXemChiTietSanPhamCustom custom) {
        ChiTietSanPham sp = new ChiTietSanPham();
        sp.setGiaBan(custom.getGiaBan());
        sp.setGiaNhap(custom.getGiaNhap());
        sp.setHinhAnh(custom.getHinhAnh());
        sp.setSoLuongTon(custom.getSoLuongTon());
        sp.setMau(custom.getMau());
        sp.setDonVi(custom.getDonVi());
        sp.setSize(custom.getSize());
        sp.setTrangThai(custom.getTrangThai());
        sp.setNamBaoHanh(custom.getNamBaoHanh());
        sp.setSanPham(custom.getSanPham());
        sp.setNgayTao(custom.getNgayTao());
        custom.setId(repo.updateCTSanPham(sp).getId());

        return custom;

    }

    @Override
    public void updateCTSP(TpXemChiTietSanPhamCustom custom) {
        ChiTietSanPham sp = new ChiTietSanPham();
        sp.setGiaBan(custom.getGiaBan());
        sp.setGiaNhap(custom.getGiaNhap());
        sp.setHinhAnh(custom.getHinhAnh());
        sp.setSoLuongTon(custom.getSoLuongTon());
        sp.setMau(custom.getMau());
        sp.setDonVi(custom.getDonVi());
        sp.setSize(custom.getSize());
        sp.setTrangThai(custom.getTrangThai());
        sp.setNamBaoHanh(custom.getNamBaoHanh());
        sp.setSanPham(custom.getSanPham());
        sp.setNgayTao(custom.getNgayTao());
        sp.setId(custom.getId());
        repo.updateCTSP(sp);
    }

    @Override
    public List<TpXemChiTietSanPhamCustom> phanTrang(List<TpXemChiTietSanPhamCustom> list, int offset, int limit) {
        List<TpXemChiTietSanPhamCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            TpXemChiTietSanPhamCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

    @Override
    public List<TpXemChiTietSanPhamCustom> findAllByKhAndNV(String ma, TrangThaiSanPhamConstanst tt, int rdo) {
                    switch (rdo) {
            case 0:
                return repo.getListByMaSp(ma, tt);
            case 1:
                return repo.getListByTenSp(ma, tt);
            default:
                return repo.getListByTenSp("", tt);
        }
    }

}
