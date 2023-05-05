package cores.logins.service;

import cores.logins.custom.NhanVienCustom;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author QUOC HUY
 */
public interface UserService {
    ConcurrentHashMap<String , NhanVienCustom> mapUserKeyEmail();
}
