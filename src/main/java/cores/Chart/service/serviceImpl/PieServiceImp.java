/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.Chart.service.serviceImpl;

import cores.Chart.model.ModelChartPie;
import cores.Chart.model.ModelChartPie1;
import cores.Chart.repository.PieRepository;
import cores.Chart.service.PieService;
import java.util.List;

/**
 *
 * @author window
 */
public class PieServiceImp implements PieService {

    private PieRepository rp;

    public PieServiceImp() {
        rp = new PieRepository();
    }

    @Override
    public List<ModelChartPie> getSoLuongSpTonNhieuNhat() {
        return rp.getSoLuongSpTonNhieuNhat();
    }

    @Override
    public List<ModelChartPie1> getSanPhamKhachHangMuaNhieuNhat() {
        return rp.getSanPhamKhachHangMuaNhieuNhat();
    }

}
