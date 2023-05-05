package cores.nhanVienQuanLy.services.serviceImpls;

import cores.nhanVienQuanLy.customModels.NvqlGetTenNhanVienCustom;
import cores.nhanVienQuanLy.repositories.NvqlGetTenNhanVienRepository;
import cores.nhanVienQuanLy.services.NvqlGetTenNvService;
import java.util.List;

/**
 *
 * @author Acer
 */
public class NvqlGetTenNvServiceImpl implements NvqlGetTenNvService{
    private NvqlGetTenNhanVienRepository repo;
    public NvqlGetTenNvServiceImpl() {
        repo = new NvqlGetTenNhanVienRepository();
    }
    
    
    @Override
    public List<NvqlGetTenNhanVienCustom> getList() {
        return repo.getListTen();
    }
    
    
}
