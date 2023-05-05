package cores.truongPhongs.services.serviceImpls;

import cores.truongPhongs.customModels.TP_ChucVuCustom;
import cores.truongPhongs.repositories.TP_ChucVuRepository;
import cores.truongPhongs.services.TP_ChucVuService;
import domainModels.ChucVu;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;

public class TP_ChucVuServiceImpl implements TP_ChucVuService {

    private TP_ChucVuRepository rp = new TP_ChucVuRepository();

    @Override
    public List<TP_ChucVuCustom> getListCV() {
        return rp.getList();
    }

    @Override
    public TP_ChucVuCustom addChucVu(TP_ChucVuCustom custom) {
        ChucVu cv = new ChucVu();
        cv.setMa(custom.getMa());
        cv.setTen(custom.getTen());
        custom.setId(rp.addChucVu(cv).getId());
        return custom;
    }

    @Override
    public boolean updateChucVu(TP_ChucVuCustom custom) {
        ChucVu cv = new ChucVu();
        cv.setMa(custom.getMa());
        cv.setTen(custom.getTen());
        cv.setId(custom.getId());
        return rp.updateChucVu(cv);
    }

    @Override
    public boolean deleteChucVu(UUID id) {
        return rp.deleteChucVu(id);
    }

    @Override
    public TP_ChucVuCustom findChucVuByMa(String ma) {
        return rp.findByMa(ma);
    }

    @Override
    public TP_ChucVuCustom checkValidate(TP_ChucVuCustom cv, JLabel erroMa, JLabel erroTen) {
        boolean check = true;

        if (cv.getMa() != null) {
            if (cv.getMa().trim().length() == 0) {
                erroMa.setText("Mã không được để trống");
                check = false;

            } else if (!cv.getMa().trim().matches(cv.getMa().toUpperCase())) {
                erroMa.setText("Mã phải viết hoa");
                check = false;
            } else if (findChucVuByMa(cv.getMa().trim()) != null) {
                erroMa.setText("Mã đã tồn tại");
                check = false;
            } else {
                erroMa.setText("");
            }
        }

        if (cv.getTen().trim().length() == 0) {
            erroTen.setText("Tên không được để trống");
            check = false;
        } else if (cv.getTen().trim().matches("^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*")) {
            erroTen.setText("Tên sai định dạng");
            check = false;
        } else {
            erroTen.setText("");
        }

        if (!check) {
            return null;
        }
        return cv;
    }

    @Override
    public List<TP_ChucVuCustom> findAllByRadio(String tk, int rdo) {
        switch (rdo) {
            case 0:
                return rp.findAllByMa(tk);
            case 1:
                return rp.findAllByTen(tk);
            default:
                return null;
        }
    }

    @Override
    public List<TP_ChucVuCustom> phanTrang(List<TP_ChucVuCustom> list, int offset, int limit) {
        List<TP_ChucVuCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            TP_ChucVuCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }

}
