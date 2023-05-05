/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.Chart.service;

import cores.Chart.model.ModelChartPie;
import cores.Chart.model.ModelChartPie1;
import java.util.List;

/**
 *
 * @author window
 */
public interface PieService {
    List<ModelChartPie> getSoLuongSpTonNhieuNhat();
    List<ModelChartPie1> getSanPhamKhachHangMuaNhieuNhat();
}
