package cores.nhanVienQuanLy.services.serviceImpls;

import cores.nhanVienQuanLy.customModels.NvqlGetTenNccCustom;
import cores.nhanVienQuanLy.repositories.NvqlGetTenNccRepository;
import cores.nhanVienQuanLy.services.NvqlGetTenNccService;
import java.util.List;

/**
 *
 * @author Acer
 */
public class NvqlGetTenNccServiceImpl implements NvqlGetTenNccService {

    private NvqlGetTenNccRepository repo;

    public NvqlGetTenNccServiceImpl() {
        repo = new NvqlGetTenNccRepository();
    }

    @Override
    public List<NvqlGetTenNccCustom> getList() {
        return repo.getListTen();
    }

}
