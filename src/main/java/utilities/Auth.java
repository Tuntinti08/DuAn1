package utilities;

import cores.logins.custom.NhanVienCustom;

public class Auth {

    public static NhanVienCustom nhanVien;

    public static void clear() {
        Auth.nhanVien = null;
    }

    public static boolean isLogin() {
        return Auth.nhanVien != null;
    }

    public static boolean isManager() {
        return Auth.isLogin();
    }
}
