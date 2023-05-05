package cores.truongPhongs.services.serviceImpls;

import cores.truongPhongs.customModels.TpDonViCustom;
import cores.truongPhongs.repositories.TpDonViRepository;
import cores.truongPhongs.services.TpDonViService;
import domainModels.DonVi;
import infrastructures.constant.ValidateConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;

/**
 *
 * @author LENOVO
 */
public class TpDonViServiceImpl implements TpDonViService {

    private TpDonViRepository rp;

    public TpDonViServiceImpl() {
        rp = new TpDonViRepository();
    }

    @Override
    public TpDonViCustom addDonVi(TpDonViCustom custom) {
        DonVi dv = new DonVi();
        dv.setDonViGoc(custom.getDonViGoc());
        dv.setDonViQuyDoi(custom.getDonViQuyDoi());
        dv.setSoLuong(custom.getSoLuong());
        custom.setId(rp.addDonVi(dv).getId());
        return custom;
    }

    @Override
    public boolean updateDonVi(TpDonViCustom custom) {
        DonVi dv = new DonVi();
        dv.setDonViGoc(custom.getDonViGoc());
        dv.setDonViQuyDoi(custom.getDonViQuyDoi());
        dv.setSoLuong(custom.getSoLuong());
        dv.setId(custom.getId());
        return rp.updateDonVi(dv);
    }

    @Override
    public boolean deleteDonVi(UUID id) {
        return rp.deleteDonVi(id);
    }
//

//    @Override
//    public TpDonViCustom checkValidate(TpDonViCustom dv, JLabel erroDonViGoc, JLabel erroDonViQuyDoi, JLabel erroSoLuong) {
//
//        boolean check = true;
////        if (dv.getDonViGoc() != null) {
//
//        if (dv.getDonViGoc().trim().length() == 0) {
//            erroDonViGoc.setText("Đơn Vị Gốc không được để trống");
//            check = false;
//        } else if (!dv.getDonViGoc().matches(ValidateConstant.REGEX_CHU_KHONG_CO_KHOANG_TRANG)) {
//            erroDonViGoc.setText("Đơn Vị Gốc không được có khoảng trắng");
//            check = false;
//        } else if (findDonViByDonViQuyDoi(dv.getDonViGoc()) != null) {
//            erroDonViGoc.setText("Đơn Vị Gốc Đã Tồn Tại");
//            check = false;
//        } else {
//            erroDonViGoc.setText("");
//        }
////        }
////     if (dv.getDonViQuyDoi() != null) {
//        if (dv.getDonViQuyDoi().trim().length() == 0) {
//            erroDonViQuyDoi.setText("Đơn Vị Quy Đổi không được để trống");
//            check = false;
//        } else if (!dv.getDonViQuyDoi().matches(ValidateConstant.REGEX_CHU_KHONG_CO_KHOANG_TRANG)) {
//            erroDonViQuyDoi.setText("Đơn Vị Quy Đổi không được có khoảng trắng");
//            check = false;
//        } else if (findDonViByDonViQuyDoi(dv.getDonViQuyDoi()) != null) {
//            erroDonViQuyDoi.setText("Đơn Vị Quy Đổi đã tồn tại");
//            check = false;
//        } else {
//            erroDonViQuyDoi.setText("");
//        }
//
//        try {
//            String sl = String.valueOf(dv.getSoLuong());
//            if (sl.equalsIgnoreCase("")) {
//                erroSoLuong.setText(" Số Lượng Không Được Để Trống");
//                check = false;
//            }
//            if (dv.getSoLuong() <= 0) {
//                erroSoLuong.setText(" Số Lượng Phải Là Số Nguyên Dương");
//                check = false;
//            } else {
//                erroSoLuong.setText("");
//            }
//        } catch (NumberFormatException e) {
//            erroSoLuong.setText(" Số Lượng Phải Là Số ");
//            check = false;
//        }
//
//        if (!check) {
//            return null;
//        }
//        return dv;
//    }
////   
    @Override
    public List<TpDonViCustom> getListDonVi() {
        return rp.getList();
    }

    @Override
    public TpDonViCustom findDonViByDonViQuyDoi(String donViQuyDoi) {
        return rp.findByDonViGoc(donViQuyDoi);
    }

    @Override
    public List<TpDonViCustom> findAllByRadio(String donViGoc, int rdo) {
        switch (rdo) {
            case 0:
                return rp.findAllByDonViGoc(donViGoc);
            case 1:
                return rp.findAllByDonViQuyDoi(donViGoc);
            default:
                return null;
        }
    }

    @Override
    public TpDonViCustom checkValidate(String DonViGoc,
            String DonViQuyDoi,
            String SoLuong,
            JLabel erroDonViGoc,
            JLabel erroDonViQuyDoi,
            JLabel erroSoLuong) {

        boolean check = true;
        if (DonViGoc.trim().length() == 0) {
            erroDonViGoc.setText("Đơn Vị Gốc không được để trống");
            check = false;
        }
//        else if (!DonViGoc.matches(ValidateConstant.REGEX_CHU_KHONG_CO_KHOANG_TRANG)) {
//            erroDonViGoc.setText("Đơn Vị Gốc không được có khoảng trắng");
//            check = false;
//        } else if (findDonViByDonViQuyDoi(DonViGoc) != null) {
//            erroDonViGoc.setText("Đơn Vị Gốc Đã Tồn Tại");
//            check = false;
//        }
        else {
            erroDonViGoc.setText("");
        }

        if (DonViQuyDoi.trim().length() == 0) {
            erroDonViQuyDoi.setText("Đơn Vị Quy Đổi không được để trống");
            check = false;
        }
//        else if (!DonViQuyDoi.matches(ValidateConstant.REGEX_CHU_KHONG_CO_KHOANG_TRANG)) {
//            erroDonViQuyDoi.setText("Đơn Vị Quy Đổi không được có khoảng trắng");
//            check = false;
//        } else if (findDonViByDonViQuyDoi(DonViQuyDoi) != null) {
//            erroDonViQuyDoi.setText("Đơn Vị Quy Đổi đã tồn tại");
//            check = false;
//        }
        else {
            erroDonViQuyDoi.setText("");
        }

        String sl = String.valueOf(SoLuong);
        if (sl.equalsIgnoreCase("")) {
            erroSoLuong.setText(" Số Lượng Không Được Để Trống");
            check = false;
        } else if (!SoLuong.matches(ValidateConstant.REGEX_SO)) {
            erroSoLuong.setText("Số Lượng Phải Là Số");
            check = false;
        } else {
            erroSoLuong.setText("");
        }

        if (!check) {
            return null;
        }
        TpDonViCustom td = new TpDonViCustom();
        td.setDonViGoc(DonViGoc);
        td.setDonViQuyDoi(DonViQuyDoi);
        td.setSoLuong(Integer.parseInt(sl));

        return td;
    }

    @Override
    public List<TpDonViCustom> phanTrang(List<TpDonViCustom> list, int offset, int limit) {
        List<TpDonViCustom> listPhanTrang = new ArrayList<>();
        int sum = limit + offset;
        if (list.size() <= sum) {
            sum = list.size();
        }
        for (int i = offset; i < sum; i++) {
            if (list.get(i) == null) {
                break;
            }
            TpDonViCustom el = list.get(i);
            listPhanTrang.add(el);
        }
        return listPhanTrang;
    }
}
