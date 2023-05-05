package cores.truongPhongs.services.serviceImpls;

import cores.truongPhongs.customModels.TP_HoanNhap_PhieuNhapCustom;
import cores.truongPhongs.customModels.TP_HoanNhap_ctpCusTom;
import cores.truongPhongs.customModels.TP_HoanNhap_spCustom;
import cores.truongPhongs.customModels.TP_PhieuHoanNhapCustom;
import cores.truongPhongs.repositories.TP_PhieuHoanNhapRepository;
import cores.truongPhongs.services.TP_PhieuHoanNhapService;
import domainModels.PhieuHoanNhap;
import domainModels.PhieuNhap;
import infrastructures.constant.TrangThaiPhieuHoanConstant;
import infrastructures.constant.TrangThaiPhieuNhapConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import utilities.DateTimeUtil;

/**
 *
 * @author QUOC HUY
 */
public class TP_PhieuHoanNhapServiceImpl implements TP_PhieuHoanNhapService {

    private TP_PhieuHoanNhapRepository rp;

    public TP_PhieuHoanNhapServiceImpl() {
        rp = new TP_PhieuHoanNhapRepository();
    }

    @Override
    public List<TP_PhieuHoanNhapCustom> getListPhieuHoanNhap() {
        return rp.getListPhieuHoanNhap();
    }

    @Override
    public List<TP_HoanNhap_PhieuNhapCustom> getListPhieuNhap() {
        return rp.getListPhieuNhap();
    }

    @Override
    public boolean addPhieuNhap(TP_HoanNhap_PhieuNhapCustom pn, String ghiChu, String lyDo) {
        PhieuNhap phieuNhap = new PhieuNhap(pn.getId(), pn.getMaPhieu(), pn.getNgayTao(), pn.getGhiChu(), pn.getNgayThanhToan(), pn.getTrangThai(), pn.getNhanVien(), pn.getNhaCungCap());
        PhieuHoanNhap phn = new PhieuHoanNhap();
        phn.setGhiChu(ghiChu);
        phn.setLiDo(lyDo);
        phn.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        phn.setPhieuNhap(phieuNhap);
        phn.setTrangThai(TrangThaiPhieuHoanConstant.CHO_XAC_NHAN);

        return rp.addPhieuHoanNhap(phn);
    }

    @Override
    public List<TP_HoanNhap_spCustom> getListSpByPhieuNhap(UUID idPhieuNhap) {
        return rp.getListSpByPhieuNhap(idPhieuNhap);
    }

    @Override
    public boolean addSanPhamInPhieuHoan(UUID sp, UUID idPhieuNhapHoan, int soLuongNhap,String lyDo) {
        return rp.addChiTietPhieuHoanNhap(sp, idPhieuNhapHoan, soLuongNhap, lyDo);
    }

    @Override
    public List<TP_HoanNhap_ctpCusTom> getListSpByChiTietPhieuHoanNhap(UUID idPhieuHoan) {
        return rp.getListCtpByPhieuHoanNhap(idPhieuHoan);
    }

    @Override
    public boolean removeSanPhamInPhieuHoan(UUID sp, UUID idPhieuNhapHoan, int soLuongNhap) {
        return rp.removeChiTietPhieuHoanNhap(sp, idPhieuNhapHoan, soLuongNhap);
    }

    @Override
    public void updatePhieuHoanNhap(TP_PhieuHoanNhapCustom phnct) {
        PhieuHoanNhap phn = new PhieuHoanNhap();
        phn.setGhiChu(phnct.getGhiChu());
        phn.setId(phnct.getId());
        phn.setLiDo(phnct.getLiDo());
        phn.setNgayTao(phnct.getNgayTao());
        phn.setNgayThanhToan(DateTimeUtil.convertDateToTimeStampSecond());
        phn.setPhieuNhap(phnct.getPhieuNhap());
        phn.setTrangThai(phnct.getTrangThai());
        rp.updatePhieuHoanNhap(phn);
    }

    @Override
    public List<TP_PhieuHoanNhapCustom> phanTrang(List<TP_PhieuHoanNhapCustom> list, int offset, int limit) {
        List<TP_PhieuHoanNhapCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            TP_PhieuHoanNhapCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

    @Override
    public List<TP_PhieuHoanNhapCustom> findAllByKhAndNV(String ma, TrangThaiPhieuHoanConstant tt, int rdo) {
        switch (rdo) {
            case 0:
                return rp.getListByTenNcc(ma, tt);
            case 1:
                return rp.getListByTenNv(ma, tt);
            default:
                return null;
        }
    }

    @Override
    public List<TP_PhieuHoanNhapCustom> getListByNgayTao(Long ngayBatDau, Long ngayKetThuc) {
        return rp.getListByNgayTao(ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<TP_PhieuHoanNhapCustom> getListByNgayThanhToan(Long ngayBatDau, Long ngayKetThuc) {
        return rp.getListByNgayThanhToan(ngayBatDau, ngayKetThuc);
    }

    @Override
    public TrangThaiPhieuHoanConstant loc(int a) {
        switch (a) {
            case 0:
                return TrangThaiPhieuHoanConstant.CHO_XAC_NHAN;
            case 1:
                return TrangThaiPhieuHoanConstant.DA_HUY;
            case 2:
                return TrangThaiPhieuHoanConstant.HOAN_THANH_CONG;
            default:
                return null;
        }
    }

    @Override
    public List<TP_HoanNhap_spCustom> getListByMaSpAndTenSpByPhieuNhap(UUID idPhieuNhap, String ma, int rdo) {
        switch (rdo) {
            case 0:
                return rp.getListByMaSpByPhieuNhap(idPhieuNhap, ma);
            case 1:
                return rp.getListByTenSpByPhieuNhap(idPhieuNhap, ma);
            default:
                return null;
        }
    }

    @Override
    public List<TP_HoanNhap_PhieuNhapCustom> findAllPnBy(String ma, int rdo) {
        switch (rdo) {
            case 0:
                return rp.findAllPnByMaPhieu(ma);
            case 1:
                return rp.findAllPnByNcc(ma);
            case 2:
                return rp.findAllPnByNv(ma);
            default:
                return null;
        }
    }

    @Override
    public List<TP_HoanNhap_PhieuNhapCustom> getListByNgayTaoPhieuNhap(Long ngayBatDau, Long ngayKetThuc) {
        return rp.findAllPnNgayTao(ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<TP_HoanNhap_ctpCusTom> getListSpByMaByChiTietPhieuHoanNhap(UUID idPhieuHoan, String ma, int rdo) {
        switch (rdo) {
            case 0:
             return rp.getListSpByMaByChiTietPhieuHoanNhap(idPhieuHoan, ma);
            case 1:
                return rp.getListSpByMaByChiTietPhieuHoanNhap(idPhieuHoan, ma);
            default:
                return null;
        }
    }

}
