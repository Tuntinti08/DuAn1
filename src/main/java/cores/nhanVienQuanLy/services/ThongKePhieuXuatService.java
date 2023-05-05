/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cores.nhanVienQuanLy.services;

import cores.nhanVienQuanLy.customModels.ThongKePhieuXuat_NhapCustom;
import java.util.List;

/**
 *
 * @author MMC
 */
public interface ThongKePhieuXuatService {
    List<ThongKePhieuXuat_NhapCustom> thongKeTienThanhToanMotNgay(Long ngayBD, Long ngayKT);
    List<ThongKePhieuXuat_NhapCustom> thongKeTienThanhToanMotNgayPhieuNhap(Long ngayBD, Long ngayKT);
}
