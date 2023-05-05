package cores.truongPhongs.services.serviceImpls;

import cores.truongPhongs.customModels.TP_ChucVuCustom;
import cores.truongPhongs.customModels.TpNhanVienCustom;
import domainModels.NhanVien;
import infrastructures.constant.TrangThaiNhanVienConstant;
import infrastructures.constant.ValidateConstant;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;
import cores.truongPhongs.repositories.TpQuanLyNhanVienRepository;
import utilities.Converter;
import utilities.palette.Combobox;
import cores.truongPhongs.services.TpQuanLyNhanVienSevice;
import domainModels.ChucVu;
import infrastructures.constant.GioiTinhConstant;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author LENOVO
 */
public class TpQuanLyNhanVienServiceImpl implements TpQuanLyNhanVienSevice {

    private TpQuanLyNhanVienRepository rp;

    private ConcurrentHashMap<String, String> map;

    public TpQuanLyNhanVienServiceImpl() {
        rp = new TpQuanLyNhanVienRepository();
        map = new ConcurrentHashMap<>();
    }

    @Override
    public List<TpNhanVienCustom> getListNhanVien() {
        List<TpNhanVienCustom> list = new ArrayList<>();
        list = rp.getList();
        list.parallelStream().forEach(el -> {
            map.put(el.getMa(), el.getEmail());
        });
        return list;
    }

    @Override
    public TpNhanVienCustom addNhanVien(TpNhanVienCustom ct) {
        NhanVien nv = new NhanVien();
        nv.setMa(ct.getMa());
        nv.setTen(ct.getTen());
        nv.setSdt(ct.getSdt());
        nv.setEmail(ct.getEmail());
        nv.setMatKhau(ct.getMatKhau());
        nv.setHinhAnh(ct.getHinhAnh());
        nv.setGioiTinh(ct.getGioiTinh());
        nv.setDiaChi(ct.getDiaChi());
        nv.setNgaySinh(ct.getNgaySinh());
        nv.setTrangThai(ct.getTrangThai());
        nv.setIdChucVu(ct.getIdChucVu());
        ct.setId(rp.addNhanVien(nv).getId());
        map.put(nv.getMa(), nv.getEmail());
        return ct;
    }

    @Override
    public boolean updateNhanVien(TpNhanVienCustom ct) {
        NhanVien nv = new NhanVien();
        nv.setMa(ct.getMa());
        nv.setTen(ct.getTen());
        nv.setSdt(ct.getSdt());
        nv.setEmail(ct.getEmail());
        nv.setMatKhau(ct.getMatKhau());
        nv.setHinhAnh(ct.getHinhAnh());
        nv.setGioiTinh(ct.getGioiTinh());
        nv.setDiaChi(ct.getDiaChi());
        nv.setNgaySinh(ct.getNgaySinh());
        nv.setTrangThai(ct.getTrangThai());
        nv.setId(ct.getId());
        nv.setIdChucVu(ct.getIdChucVu());
        map.put(nv.getMa(), nv.getEmail());
        return rp.updateNhanVien(nv);
    }

    @Override
    public boolean deleteNhanVien(UUID id) {
        return rp.deleteNhanVien(id);
    }

    @Override
    public void loadComboboxTT(Combobox cbb) {
        cbb.removeAll();
        cbb.addItem(Converter.trangThaiNhanVien(TrangThaiNhanVienConstant.CHUA_HOAT_DONG));
        cbb.addItem(Converter.trangThaiNhanVien(TrangThaiNhanVienConstant.DANG_HOAT_DONG));
        cbb.addItem(Converter.trangThaiNhanVien(TrangThaiNhanVienConstant.DA_NGHI_VIEC));
    }

    @Override
    public TrangThaiNhanVienConstant loc(int a) {
        switch (a) {
            case 0:
                return TrangThaiNhanVienConstant.CHUA_HOAT_DONG;
            case 1:
                return TrangThaiNhanVienConstant.DANG_HOAT_DONG;
            case 2:
                return TrangThaiNhanVienConstant.DA_NGHI_VIEC;
            default:
                return null;
        }
    }

    @Override
    public TpNhanVienCustom finNhanVienByMa(String ma) {
        return rp.findByMa(ma);
    }

    @Override
    public List<TpNhanVienCustom> findAllByRadio(String tk, TrangThaiNhanVienConstant tt, int rdo) {
        switch (rdo) {
            case 0:
                return rp.findAllByMa(tk, tt);
            case 1:
                return rp.findAllByTen(tk, tt);
            case 2:
                return rp.findAllByDiaChi(tk, tt);
            default:
                return null;
        }

    }

    @Override
    public GioiTinhConstant loc1(int b) {
        switch (b) {
            case 0:
                return GioiTinhConstant.NU;
            case 1:
                return GioiTinhConstant.KHAC;
            case 2:
                return GioiTinhConstant.NAM;
            default:
                return null;
        }
    }

    @Override
    public void loadComboboxGT(Combobox cbb) {
        cbb.addItem(Converter.trangThaiGioiTinh(GioiTinhConstant.NU));
        cbb.addItem(Converter.trangThaiGioiTinh(GioiTinhConstant.KHAC));
        cbb.addItem(Converter.trangThaiGioiTinh(GioiTinhConstant.NAM));
    }

    @Override
    public TpNhanVienCustom checkValidateCreate(TpNhanVienCustom nv, JLabel erroMa, JLabel erroTen, JLabel erroEmail, JLabel erroSDT, JLabel erroMatKhau, JLabel erroDiaChi, JLabel erroNgaySinh) {
        boolean check = true;
        if (nv.getMa().trim().length() == 0) {
            erroMa.setText("Mã không được để trống");
            check = false;
        } else if (map.containsKey(nv.getMa())) {
            erroMa.setText("Mã đã tồn tại");
            check = false;
        } else if (!nv.getMa().trim().matches(nv.getMa().toUpperCase())) {
            erroMa.setText("Mã phải viết hoa");
            check = false;
        } else {
            erroMa.setText("");
        }
        if (nv.getTen().trim().length() == 0) {
            erroTen.setText("Tên không được để trống");
            check = false;
        } else if (!nv.getTen().trim().matches("^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*")) {
            erroTen.setText("Tên sai định dạng");
            check = false;
        } else {
            erroTen.setText("");
        }

        if (nv.getEmail().trim().length() == 0) {
            erroEmail.setText("Email không được để trống");
            check = false;
        } else if (map.containsValue(nv.getEmail())) {
            erroEmail.setText("Email bị trùng");
            check = false;
        } else if (!nv.getEmail().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            erroEmail.setText("Email sai định dạng");
            check = false;
        } else {
            erroEmail.setText("");
        }
        if (nv.getSdt().length() == 0) {
            erroSDT.setText("SDT không được để trống");
            check = false;
        } else if (!nv.getSdt().trim().matches(ValidateConstant.REGEX_PHONE_NUMBER)) {
            erroSDT.setText("SĐT sai định dạng");
            check = false;
        } else {
            erroSDT.setText("");
        }

        if (nv.getMatKhau().trim().length() == 0) {
            erroMatKhau.setText("Mật Khẩu không được để trống");
            check = false;
        } //        else if (!nv.getMatKhau().trim().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})")) {
        //            erroMatKhau.setText("Mật khẩu sai định dạng");
        //            check = false;
        //
        //        }
        else {
            erroMatKhau.setText("");
        }
        if (nv.getDiaChi().trim().length() == 0) {
            erroDiaChi.setText("Địa chỉ không được để trống");
            check = false;
        } //        else if (!nv.getDiaChi().trim().matches("^[A-Z a-z 0-9]+$")) {
        //            erroDiaChi.setText("Địa chỉ sai định dạng");
        //            check = false;
        //        }
        else {
            erroDiaChi.setText("");
        }

        if (nv.getNgaySinh() == null) {
            erroNgaySinh.setText("Bạn phải chọn ngày sinh");
            check = false;
        } else if (nv.getNgaySinh() > new Date().getTime()) {
            erroNgaySinh.setText("Ngày sinh quá hiện tại ");
            check = false;
        } else {
            erroNgaySinh.setText("");
        }
//        if (nv.getDiaChi().trim().length() == 0) {
//            erroDiaChi.setText("Địa chỉ không được để trống");
//            check = false;
//        } else {
//            erroDiaChi.setText("");
//        }

        if (!check) {
            return null;
        }

        return nv;
    }

    @Override
    public TpNhanVienCustom checkValidateUpdate(TpNhanVienCustom nv, JLabel erroMa, JLabel erroTen, JLabel erroEmail, JLabel erroSDT, JLabel erroMatKhau, JLabel erroDiaChi, JLabel erroNgaySinh) {
        boolean check = true;

        if (nv.getTen().trim().length() == 0) {
            erroTen.setText("Tên không được để trống");
            check = false;
        } else if (!nv.getTen().trim().matches("^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*")) {
            erroTen.setText("Tên sai định dạng");
            check = false;
        } else {
            erroTen.setText("");
        }
        if (nv.getEmail().trim().length() == 0) {
            erroEmail.setText("Email không được để trống");
            check = false;
        } else if (map.containsValue(nv.getEmail())) {
            if (!map.containsKey(nv.getMa())) {
                erroEmail.setText("Email không được trùng");
                check = false;
            }

        } else if (!nv.getEmail().trim().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            erroEmail.setText("Email sai định dạng");
            check = false;
        } else {
            erroEmail.setText("");
        }
        if (nv.getSdt().length() == 0) {
            erroSDT.setText("SDT không được để trống");
            check = false;
        } else if (!nv.getSdt().trim().matches(ValidateConstant.REGEX_PHONE_NUMBER)) {
            erroSDT.setText("SĐT sai định dạng");
            check = false;
        } else {
            erroSDT.setText("");
        }

        if (nv.getMatKhau().trim().length() == 0) {
            erroMatKhau.setText("Mật Khẩu không được để trống");
            check = false;
        } //        else if (!nv.getMatKhau().trim().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})")) {
        //            erroMatKhau.setText("Mật khẩu sai định dạng");
        //            check = false;
        //
        //        }
        else {
            erroMatKhau.setText("");
        }
        if (nv.getDiaChi().trim().length() == 0) {
            erroDiaChi.setText("Địa chỉ không được để trống");
            check = false;
        } //        else if (!nv.getDiaChi().trim().matches("^[A-Z a-z 0-9]+$")) {
        //            erroDiaChi.setText("Địa chỉ sai định dạng");
        //            check = false;
        //        }
        else {
            erroDiaChi.setText("");
        }

        if (nv.getNgaySinh() == null) {
            erroNgaySinh.setText("Bạn phải chọn ngày sinh");
            check = false;
        } else if (nv.getNgaySinh() > new Date().getTime()) {
            erroNgaySinh.setText("Ngày sinh quá hiện tại ");
            check = false;
        } else {
            erroNgaySinh.setText("");
        }
//        if (nv.getDiaChi().trim().length() == 0) {
//            erroDiaChi.setText("Địa chỉ không được để trống");
//            check = false;
//        } else {
//            erroDiaChi.setText("");
//        }

        if (!check) {
            return null;
        }

        return nv;
    }

    @Override
    public List<TP_ChucVuCustom> getListCV() {
        return rp.getListCV();
    }

    @Override
    public ChucVu findIDCV(UUID id) {
        return rp.findIDCV(id);
    }

    @Override
    public List<TpNhanVienCustom> phanTrang(List<TpNhanVienCustom> list, int offset, int limit) {
        List<TpNhanVienCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            TpNhanVienCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

}
