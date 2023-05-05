package cores.truongPhongs.services;

import cores.truongPhongs.customModels.TP_ChucVuCustom;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;

public interface TP_ChucVuService {

    List<TP_ChucVuCustom> getListCV();

    TP_ChucVuCustom addChucVu(TP_ChucVuCustom custom);

    boolean updateChucVu(TP_ChucVuCustom custom);

    boolean deleteChucVu(UUID id);

    TP_ChucVuCustom findChucVuByMa(String ma);

    TP_ChucVuCustom checkValidate(TP_ChucVuCustom cv, JLabel erroMa, JLabel erroTen);

    List<TP_ChucVuCustom> findAllByRadio(String tk, int rdo);

    List<TP_ChucVuCustom> phanTrang(List<TP_ChucVuCustom> list, int offset, int limit);

}
