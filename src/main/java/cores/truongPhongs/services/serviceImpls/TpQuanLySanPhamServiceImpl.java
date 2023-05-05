package cores.truongPhongs.services.serviceImpls;

import cores.truongPhongs.customModels.TpQuanLySanPhamCustom;
import cores.truongPhongs.customModels.TpThemSanPhamCustom;
import cores.truongPhongs.repositories.TpQuanLySanPhamRepository;
import cores.truongPhongs.services.TpQuanLySanPhamService;
import domainModels.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;

/**
 *
 * @author MMC
 */
public class TpQuanLySanPhamServiceImpl implements TpQuanLySanPhamService {

    private TpQuanLySanPhamRepository rp;

    public TpQuanLySanPhamServiceImpl() {
        rp = new TpQuanLySanPhamRepository();
    }
    
    @Override
    public List<TpQuanLySanPhamCustom> getAll(String ten) {
        return rp.getAll(ten);
    }

    @Override
    public TpQuanLySanPhamCustom addSanPham(TpQuanLySanPhamCustom custom) {
        SanPham sp = new SanPham();
        sp.setMa(custom.getMa());
        sp.setTen(custom.getTen());
        custom.setId(rp.addSanPham(sp).getId());
        return custom;
    }

    @Override
    public boolean updateSanPham(TpQuanLySanPhamCustom custom) {
        SanPham sp = new SanPham();
        sp.setMa(custom.getMa());
        sp.setTen(custom.getTen());
        sp.setId(custom.getId());
        return rp.updateSanPham(sp);
    }

    @Override
    public boolean deleteSanPham(UUID id) {
        return rp.deleteSanPham(id);
    }

    @Override
    public TpQuanLySanPhamCustom checkValidate(TpQuanLySanPhamCustom sp, JLabel erroMa, JLabel erroTen) {
        boolean check = true;

        if (sp.getTen().trim().length() == 0) {
            erroTen.setText("Tên không được để trống");
            check = false;
        } else {
            erroTen.setText("");
        }

        if (!check) {
            return null;
        }

        return sp;
    }

    @Override
    public List<TpQuanLySanPhamCustom> phanTrang(List<TpQuanLySanPhamCustom> list, int offset, int limit) {
        List<TpQuanLySanPhamCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            TpQuanLySanPhamCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

    @Override
    public List<TpQuanLySanPhamCustom> getByMaSpNcc(String ten) {
        return rp.getFindByMaSpNcc(ten);
    }

}
