package cores.logins.service.ServiceImpl;

import cores.logins.custom.NhanVienCustom;
import cores.logins.repository.UserRepository;
import cores.logins.service.UserService;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author QUOC HUY
 */
public class UserServiceImpl implements UserService{

    @Override
    public ConcurrentHashMap<String, NhanVienCustom> mapUserKeyEmail() {
        ConcurrentHashMap<String, NhanVienCustom> map = new ConcurrentHashMap<>();
        List<NhanVienCustom> list = new UserRepository().getAllUser();
        list.parallelStream().forEach(el -> {
            map.put(el.getEmail(), el);
        });
        return map;
    }
    
}
