package cores.truongPhongs.services.serviceImpls;

import cores.truongPhongs.customModels.TpPhieuNhapChiTietCustom;
import cores.truongPhongs.repositories.TpPhieuNhapChiTietRepository;
import cores.truongPhongs.services.TpPhieuNhapChiTietService;
import domainModels.ChiTietPhieuNhap;
import domainModels.ChiTietSanPham;
import domainModels.PhieuNhap;
import infrastructures.constant.MauConstant;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Acer
 */
public class TpPhieuNhapChiTietServiceImpl implements TpPhieuNhapChiTietService {

    private TpPhieuNhapChiTietRepository repo;
    
    public TpPhieuNhapChiTietServiceImpl() {
        repo = new TpPhieuNhapChiTietRepository();
    }
    
    @Override
    public TpPhieuNhapChiTietCustom addPhieuNhap(TpPhieuNhapChiTietCustom pnct) {
        PhieuNhap pn = repo.findPnById(pnct.getIdPhieuNhap().getId());
        ChiTietSanPham sp = repo.findSpById(pnct.getIdSanPham().getId());
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
        ctpn.setSoLuong(pnct.getSoLuong());
        ctpn.setIdChiTietSp(sp);
        ctpn.setIdPhieuNhap(pn);
        repo.addPhieuNhap(ctpn);
        return pnct;
    }
    
    @Override
    public List<TpPhieuNhapChiTietCustom> getListCTPhieuNhapByID(UUID idPX) {
        return repo.getListCTPhieuNhapByID(idPX);
    }
    
    @Override
    public boolean addCTPN(TpPhieuNhapChiTietCustom ctpxct) {
        ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
        ct.setIdChiTietSp(ctpxct.getIdSanPham());
        ct.setIdPhieuNhap(ctpxct.getIdPhieuNhap());
        ct.setSoLuong(ctpxct.getSoLuong());
        return repo.addCTPN(ct);
    }
    
    @Override
    public MauConstant loc(int a) {
        switch (a) {
            case 0:
                return MauConstant.VANG;
            case 1:
                return MauConstant.XANH_LA;
            case 2:
                return MauConstant.DO;
            case 3:
                return MauConstant.XANH_DUONG;
            case 4:
                return MauConstant.HONG;
            case 5:
                return MauConstant.CAM;
            case 6:
                return MauConstant.DEN;
            case 7:
                return MauConstant.TRANG;
            default:
                return null;
        }
    }
    
    @Override
    public boolean upDateCTPN(TpPhieuNhapChiTietCustom ctpxct) {
        ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
        ct.setIdChiTietSp(ctpxct.getIdSanPham());
        ct.setIdPhieuNhap(ctpxct.getIdPhieuNhap());
        ct.setSoLuong(ctpxct.getSoLuong());
        
        return repo.upDatePhieuNhap(ct);
    }
    
    @Override
    public void upDatePN(TpPhieuNhapChiTietCustom ctpxct) {
        ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
        ct.setIdChiTietSp(ctpxct.getIdSanPham());
        ct.setIdPhieuNhap(ctpxct.getIdPhieuNhap());
        ct.setSoLuong(ctpxct.getSoLuong());
        repo.upDatePN(ct);
    }
    
}
