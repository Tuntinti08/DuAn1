package cores.nhanVienQuanLy.services.serviceImpls;

import cores.nhanVienQuanLy.customModels.ChiTietPhieuHoanXuatCustom;
import cores.nhanVienQuanLy.customModels.PhieuHoanXuatCustom;
import cores.nhanVienQuanLy.customModels.PhieuXuatCustom;
import cores.nhanVienQuanLy.repositories.Tai_LuongHoanXuat_Repository;
import cores.nhanVienQuanLy.services.Tai_LuongHoanXuatService;
import domainModels.ChiTietPhieuHoanXuat;
import domainModels.PhieuHoanXuat;
import domainModels.PhieuXuat;
import infrastructures.constant.TrangThaiPhieuHoanConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import utilities.DateTimeUtil;

/**
 *
 * @author admin
 */
public class Tai_LuongHoanXuatServiceImpl implements Tai_LuongHoanXuatService{
    private Tai_LuongHoanXuat_Repository rp ;

    public Tai_LuongHoanXuatServiceImpl() {
        rp =new Tai_LuongHoanXuat_Repository();
    }
    
    @Override
    public List<PhieuHoanXuatCustom> getListPHX() {
        return rp.getListPHX();
    }

    @Override
    public void updatePHX(PhieuHoanXuatCustom phxct) {
        PhieuHoanXuat phx = new PhieuHoanXuat();
        phx.setGhiChu(phxct.getGhiChu());
        phx.setId(phxct.getId());
        phx.setLiDo(phxct.getLiDo());
        phx.setNgayTao(phxct.getNgayTao());
        phx.setNgayThanhToan(phxct.getNgayThanhToan());
        phx.setPhieuXuat(phxct.getPhieuXuat());
        phx.setTrangThai(phxct.getTrangThai());
        rp.updatePHX(phx);
    }

    @Override
    public List<ChiTietPhieuHoanXuatCustom> getListCTphxByID(UUID id) {
        return rp.getListCTphxByID(id);
    }

    @Override
    public void updateCtPHX(ChiTietPhieuHoanXuatCustom ctphxct) {
        ChiTietPhieuHoanXuat ctphx = new ChiTietPhieuHoanXuat();
        ctphx.setIdChiTietSp(ctphxct.getIdChiTietSp());
        ctphx.setIdPhieuHoanXuat(ctphxct.getIdPhieuHoanXuat());
        ctphx.setSoLuong(ctphxct.getSoLuong());
        ctphx.setLiDo(ctphxct.getLiDo());
        rp.updateCtPHX(ctphx);
    }

    @Override
    public void addPhieuHoanXuatTuDong(PhieuXuatCustom pxct, String ghiChu, String liDo) {
        PhieuXuat px = new PhieuXuat();
        px.setGhiChu(pxct.getGhiChu());
        px.setId(pxct.getId());
        px.setKhachHang(pxct.getKhachHang());
        px.setNgayTao(pxct.getNgayTao());
        px.setNgayThanhToan(pxct.getNgayThanhToan());
        px.setNhanVien(pxct.getNhanVien());
        px.setTrangThai(pxct.getTrangThai());
        
        PhieuHoanXuat phx = new PhieuHoanXuat();
        phx.setGhiChu(ghiChu);
        phx.setLiDo(liDo);
        phx.setNgayTao(DateTimeUtil.convertDateToTimeStampSecond());
        phx.setNgayThanhToan(null);
        phx.setPhieuXuat(px);
        phx.setTrangThai(TrangThaiPhieuHoanConstant.CHO_XAC_NHAN);
        
        rp.addPHX(phx);
    }

    @Override
    public void addChiTietPhieuHoanXuat(ChiTietPhieuHoanXuatCustom ctphxct) {
        ChiTietPhieuHoanXuat ctphx = new ChiTietPhieuHoanXuat();
        ctphx.setIdChiTietSp(ctphxct.getIdChiTietSp());
        ctphx.setIdPhieuHoanXuat(ctphxct.getIdPhieuHoanXuat());
        ctphx.setSoLuong(ctphxct.getSoLuong());
        ctphx.setLiDo(ctphxct.getLiDo());
        rp.addChiTietPhieuHoanXuat(ctphx);
    }

    @Override
    public ConcurrentHashMap<UUID, String> getMapPhx() {
        ConcurrentHashMap<UUID, String> map = new ConcurrentHashMap<UUID, String>();
        getListPHX().parallelStream().forEach(el -> {
            map.put(el.getPhieuXuat().getId(), "hehe");
        });
        return map;
    }

    @Override
    public List<ChiTietPhieuHoanXuatCustom> getListCTphx() {
        return rp.getListCTphx();
    }

    @Override
    public List<PhieuHoanXuatCustom> phanTrang(List<PhieuHoanXuatCustom> list, int offset, int limit) {
        List<PhieuHoanXuatCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            PhieuHoanXuatCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

    @Override
    public List<ChiTietPhieuHoanXuatCustom> phanTrangCT(List<ChiTietPhieuHoanXuatCustom> list, int offset, int limit) {
        List<ChiTietPhieuHoanXuatCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            ChiTietPhieuHoanXuatCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }
    
}
