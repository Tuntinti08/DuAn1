package cores.truongPhongs.services.serviceImpls;

import cores.truongPhongs.customModels.TpXemPhieuXuatCustom;
import cores.truongPhongs.customModels.TpXemSanPham_PhieuXuat;
import cores.truongPhongs.repositories.TpXemPhieuXuatRepository;
import cores.truongPhongs.services.TpXemPhieuXuatService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author QUOC HUY
 */
public class TpXemPhieuXuatSerivceImpl implements TpXemPhieuXuatService{
    
    private TpXemPhieuXuatRepository rp;

    public TpXemPhieuXuatSerivceImpl() {
        rp = new TpXemPhieuXuatRepository();
    }
    
    @Override
    public List<TpXemPhieuXuatCustom> getListPhieuXuat(String maPhieu,
            String tenKh, String maKh, String diaChiKh, String emailKh, String sdtKh,
            String maSp, String tenSp,
            String maPn,
            String tenNcc, String maNcc, String diaChiNcc, String emailNcc, String sdtNcc, String maSpNcc) {
        return rp.getListPhieuXuat(maPhieu, tenKh, maKh, diaChiKh, emailKh, sdtKh, maSp, tenSp, maPn, tenNcc, maNcc, diaChiNcc, emailNcc, sdtNcc, maSpNcc);
    }
    @Override
    public List<TpXemPhieuXuatCustom> phanTrangPhieuXuat(List<TpXemPhieuXuatCustom> list, int offset, int limit) {
        List<TpXemPhieuXuatCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            TpXemPhieuXuatCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

    @Override
    public List<TpXemSanPham_PhieuXuat> getListSanPhamByPhieuXuat(UUID idPhieuXuat,
            String maSp, String tenSp,
            String maPn,
            String tenNcc, String maNcc, String diaChiNcc, String emailNcc, String sdtNcc, String maSpNcc) {
        return rp.getListSanPhamByPhieuXuat(idPhieuXuat, maSp, tenSp, maPn, tenNcc, maNcc, diaChiNcc, emailNcc, sdtNcc, maSpNcc);
    }
    
}
