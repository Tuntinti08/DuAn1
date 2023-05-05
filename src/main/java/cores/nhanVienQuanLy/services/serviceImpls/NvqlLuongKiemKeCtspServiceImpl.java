package cores.nhanVienQuanLy.services.serviceImpls;

import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtspCustom;
import cores.nhanVienQuanLy.repositories.NvqlLuongKiemKeCtspRepository;
import cores.nhanVienQuanLy.services.NvqlLuongKiemKeCtspService;
import domainModels.ChiTietSanPham;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author window
 */
public class NvqlLuongKiemKeCtspServiceImpl implements NvqlLuongKiemKeCtspService {

    private NvqlLuongKiemKeCtspRepository rp;

    public NvqlLuongKiemKeCtspServiceImpl() {
        rp = new NvqlLuongKiemKeCtspRepository();
    }

    @Override
    public List<NvqlLuongKiemKeCtspCustom> getAll() {
        return rp.getAll();
    }

    @Override
    public void updateSoLuong(NvqlLuongKiemKeCtspCustom a) {
        ChiTietSanPham ctsp = new ChiTietSanPham(
                a.getId(),
                a.getSoLuongTon(),
                a.getHinhAnh(),
                a.getGiaNhap(),
                a.getGiaBan(),
                a.getNamBaoHanh(),
                a.getMau(),
                a.getTrangThai(),
                a.getSize(),
                a.getNgayTao(),
                a.getSanPham(),
                a.getDonVi()
        );
        rp.updateSoLuong(ctsp);
    }

    @Override
    public List<NvqlLuongKiemKeCtspCustom> phanTrang(List<NvqlLuongKiemKeCtspCustom> list, int offset, int limit) {
        List<NvqlLuongKiemKeCtspCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            NvqlLuongKiemKeCtspCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

    @Override
    public void updateTrangThaiSp(NvqlLuongKiemKeCtspCustom a) {
        ChiTietSanPham ctsp = new ChiTietSanPham(
                a.getId(),
                a.getSoLuongTon(),
                a.getHinhAnh(),
                a.getGiaNhap(),
                a.getGiaBan(),
                a.getNamBaoHanh(),
                a.getMau(),
                a.getTrangThai(),
                a.getSize(),
                a.getNgayTao(),
                a.getSanPham(),
                a.getDonVi()
        );
        rp.updateTrangThaiSp(ctsp);
    }

    @Override
    public List<NvqlLuongKiemKeCtspCustom> findAllByKhAndNV(String ma, int rdo) {
        switch (rdo) {
            case 0:
                return rp.getListByMaSp(ma);
            case 1:
                return rp.getListByTenSp(ma);
            default:
                return null;
        }
    }

    @Override
    public List<NvqlLuongKiemKeCtspCustom> getListGiaNhap(BigDecimal giaBatDau, BigDecimal giaKetThuc) {
        return rp.getListByGiaBan(giaBatDau, giaKetThuc);
    }
}
