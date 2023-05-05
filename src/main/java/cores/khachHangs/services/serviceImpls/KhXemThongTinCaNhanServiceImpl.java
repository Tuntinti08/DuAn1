package cores.khachHangs.services.serviceImpls;

import cores.khachHangs.customModels.KhXemThongTinCaNhanCustom;
import cores.khachHangs.repositories.KhXemThongTinCaNhanRepository;
import cores.khachHangs.services.KhXemThongTinCaNhanService;
import java.util.List;

/**
 *
 * @author window
 */
public class KhXemThongTinCaNhanServiceImpl implements KhXemThongTinCaNhanService{

    private KhXemThongTinCaNhanRepository khXemThongTinCaNhanRepository;

    public KhXemThongTinCaNhanServiceImpl() {
        khXemThongTinCaNhanRepository = new KhXemThongTinCaNhanRepository();
    }
    
    @Override
    public List<KhXemThongTinCaNhanCustom> getAll() {
        return khXemThongTinCaNhanRepository.getAll();
    }

    @Override
    public void doiMatKhau(String pass) {
        khXemThongTinCaNhanRepository.doiMatKhau(pass);
    }

    @Override
    public boolean checkMatKhau(String matKhau) {
        return khXemThongTinCaNhanRepository.checkMatKhau(matKhau);
    }
    
}
