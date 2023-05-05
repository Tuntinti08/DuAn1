package cores.nhanVienQuanLy.services.serviceImpls;

import cores.nhanVienQuanLy.customModels.NvqlLuongKiemKeCtpkCustom;
import cores.nhanVienQuanLy.repositories.NvqlLuongKiemKeCtpkRepository;
import cores.nhanVienQuanLy.services.NvqlLuongKiemKeCtpkService;
import domainModels.ChiTietPhieuKiemKe;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author window
 */
public class NvqlLuongKiemKeCtpkServiceImpl implements NvqlLuongKiemKeCtpkService {

    private NvqlLuongKiemKeCtpkRepository rp;

    public NvqlLuongKiemKeCtpkServiceImpl() {
        rp = new NvqlLuongKiemKeCtpkRepository();
    }

    @Override
    public List<NvqlLuongKiemKeCtpkCustom> getAll(UUID id) {
        return rp.getAll(id);
    }

    @Override
    public void addCTPK(NvqlLuongKiemKeCtpkCustom b) {
        ChiTietPhieuKiemKe a = new ChiTietPhieuKiemKe(b.getIdPhieuKiem(), b.getIdChiTietSanPham(), b.getSoLuongTon(), b.getSoLuongThucTon(), b.getLiDo());
        rp.addCTPK(a);
    }

    @Override
    public List<NvqlLuongKiemKeCtpkCustom> phanTrang(List<NvqlLuongKiemKeCtpkCustom> list, int offset, int limit) {
        List<NvqlLuongKiemKeCtpkCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            NvqlLuongKiemKeCtpkCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

    @Override
    public void updateCTPKK(NvqlLuongKiemKeCtpkCustom ctpkCustom) {
        ChiTietPhieuKiemKe ctpkk = new ChiTietPhieuKiemKe();
        ctpkk.setIdPhieuKiemKe(ctpkCustom.getIdPhieuKiem());
        ctpkk.setIdChiTietSp(ctpkCustom.getIdChiTietSanPham());
        ctpkk.setSoLuongTon(ctpkCustom.getSoLuongTon());
        ctpkk.setSoLuongThucTon(ctpkCustom.getSoLuongThucTon());
        ctpkk.setLiDo(ctpkCustom.getLiDo());
        rp.updateCTPKK(ctpkk);
    }

    @Override
    public boolean updateSoLuongTon(UUID ctsp, int soLuong) {
        return rp.updateSoLuongTon(ctsp, soLuong);
    }

}
