package cores.nhanVienQuanLy.services.serviceImpls;

import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCustom;
import cores.nhanVienQuanLy.repositories.NvqlLuongKiemKeRepository;
import cores.nhanVienQuanLy.services.NvqlLuongKiemKeService;
import cores.truongPhongs.customModels.TpPhieuNhapCustom;
import domainModels.NhanVien;
import domainModels.PhieuKiemKe;
import infrastructures.constant.TrangThaiPhieuConstant;
import infrastructures.constant.TrangThaiPhieuKiemConstant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author window
 */
public class NvqlLuongKiemKeServiceImpl implements NvqlLuongKiemKeService {

    private NvqlLuongKiemKeRepository rp;

    public NvqlLuongKiemKeServiceImpl() {
        rp = new NvqlLuongKiemKeRepository();
    }

    @Override
    public List<NvqlLuongKiemKeCustom> getAll() {
        return rp.getAll();
    }

    @Override
    public void Insert(NvqlLuongKiemKeCustom phieuKiemKe) {
        NhanVien nv = phieuKiemKe.getIdNV();
        PhieuKiemKe a = new PhieuKiemKe(phieuKiemKe.getMaPhieuKiem(), phieuKiemKe.getNgayTao(), TrangThaiPhieuKiemConstant.MOI_TAO, nv);
        rp.them(a);
    }

    @Override
    public boolean UpdateTrangThai(NvqlLuongKiemKeCustom phieuKiemKe) {
        PhieuKiemKe p = new PhieuKiemKe();
        p.setGhiChu(phieuKiemKe.getGhiChu());
        p.setId(phieuKiemKe.getId());
        p.setMa(phieuKiemKe.getMaPhieuKiem());
        p.setNgayTao(phieuKiemKe.getNgayTao());
        p.setNgayXacNhan(new Date().getTime());
        p.setNhanVien(phieuKiemKe.getIdNV());
        p.setTrangThai(phieuKiemKe.getTrangThai());
        return rp.updateTrangThai(p);
    }

    @Override
    public TrangThaiPhieuKiemConstant loc(int a) {
        switch (a) {
            case 0:
                return TrangThaiPhieuKiemConstant.MOI_TAO;
            case 1:
                return TrangThaiPhieuKiemConstant.CHUA_XAC_NHAN;
            case 2:
                return TrangThaiPhieuKiemConstant.DA_XAC_NHAN;
            default:
                return null;
        }
    }

    @Override
    public List<NvqlLuongKiemKeCustom> getListByNgayTao(Long ngayBatDau, Long ngayKetThuc) {
        return rp.getListByNgayTao(ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<NvqlLuongKiemKeCustom> phanTrang(List<NvqlLuongKiemKeCustom> list, int offset, int limit) {
        List<NvqlLuongKiemKeCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            NvqlLuongKiemKeCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

    @Override
    public List<NvqlLuongKiemKeCustom> findAllByKhAndNV(String ma, TrangThaiPhieuKiemConstant tt, int rdo) {
                    switch (rdo) {
            case 0:
                return rp.getListByMa(ma, tt);
            case 1:
                return rp.getListByTenNv(ma, tt);
            default:
                return rp.getListByTenNv("", tt);
        }
    }

}
