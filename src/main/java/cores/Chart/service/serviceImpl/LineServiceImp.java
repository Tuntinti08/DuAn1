/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.Chart.service.serviceImpl;

import cores.Chart.model.ModelChartLine;
import cores.Chart.repository.LineRepository;
import cores.Chart.repository.PieRepository;
import cores.Chart.service.LineService;
import java.util.List;

/**
 *
 * @author window
 */
public class LineServiceImp implements LineService {

    private LineRepository rp;

    public LineServiceImp() {
        rp = new LineRepository();
    }

    @Override
    public List<ModelChartLine> getSanPhamBanNhieuNhat() {
        return rp.getSanPhamBanNhieuNhat();
    }

}
