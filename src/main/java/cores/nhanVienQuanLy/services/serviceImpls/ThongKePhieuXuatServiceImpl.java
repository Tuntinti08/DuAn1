/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.nhanVienQuanLy.services.serviceImpls;

import cores.nhanVienQuanLy.customModels.ThongKePhieuXuat_NhapCustom;
import cores.nhanVienQuanLy.repositories.ThongKePhieuXuatRepository;
import cores.nhanVienQuanLy.services.ThongKePhieuXuatService;
import java.util.List;

/**
 *
 * @author MMC
 */
public class ThongKePhieuXuatServiceImpl implements ThongKePhieuXuatService{
    private ThongKePhieuXuatRepository rp = new ThongKePhieuXuatRepository();

    @Override
    public List<ThongKePhieuXuat_NhapCustom> thongKeTienThanhToanMotNgay(Long ngayBD, Long ngayKT) {
        return rp.thongKeTienThanhToanMotNgay(ngayBD, ngayKT);
    }

    @Override
    public List<ThongKePhieuXuat_NhapCustom> thongKeTienThanhToanMotNgayPhieuNhap(Long ngayBD, Long ngayKT) {
        return rp.thongKeTienThanhToanMotNgayPhieuNhap(ngayBD, ngayKT);
    }
    
    
}
