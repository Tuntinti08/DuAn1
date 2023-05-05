/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.nhanVienQuanLy.customModels;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author MMC
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThongKePhieuXuat_NhapCustom {
    private int soLuong;
    private BigDecimal donGia;

    @Override
    public String toString() {
        return "ThongKePhieuXuatCustom{" + "soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }
    
    
}
