package cores.nhanVienQuanLy.services.serviceImpls;

import cores.logins.custom.NhanVienCustom;
import cores.nhanVienQuanLy.customModels.NvqlXemThongTinCaNhanCustom;
import cores.nhanVienQuanLy.repositories.NvqlXemThongTinCaNhanRepository;
import java.util.List;
import cores.nhanVienQuanLy.services.NvqlXemThongTinCaNhanService;
import domainModels.NhanVien;

/**
 *
 * @author window
 */
public class NvqlXemThongTinCaNhanServiceImpl implements NvqlXemThongTinCaNhanService{

    private NvqlXemThongTinCaNhanRepository nvghXemThongTinCaNhanRepository;

    public NvqlXemThongTinCaNhanServiceImpl() {
        nvghXemThongTinCaNhanRepository = new NvqlXemThongTinCaNhanRepository();
    }
    
    @Override
    public List<NvqlXemThongTinCaNhanCustom> getAll() {
        return nvghXemThongTinCaNhanRepository.getAll();
    }
    
    @Override
    public NvqlXemThongTinCaNhanCustom getMatKhauByEmail(String email) {
        return nvghXemThongTinCaNhanRepository.getMatKhauByEmail(email);
    }

    @Override
    public void doiMatKhau(NhanVienCustom nvc) {
        NhanVien nv = new NhanVien(nvc.getId(),
                nvc.getMa(), nvc.getTen(),
                nvc.getSdt(), nvc.getEmail(),
                nvc.getMatKhau(), nvc.getNgaySinh(),
                nvc.getHinhAnh(), nvc.getGioiTinh(),
                nvc.getDiaChi(), nvc.getTrangThai(), nvc.getChucVu());
        nvghXemThongTinCaNhanRepository.doiMatKhau(nv);
    }

    @Override
    public boolean checkMatKhau(String matKhau) {
        return nvghXemThongTinCaNhanRepository.checkMatKhau(matKhau);
    }
    
}
