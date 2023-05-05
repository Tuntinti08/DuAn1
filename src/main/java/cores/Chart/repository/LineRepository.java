/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.Chart.repository;

import cores.Chart.model.ModelChartLine;
import cores.Chart.model.ModelChartPie;
import domainModels.ChiTietSanPham;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.hibernate.Session;
import javax.persistence.Query;
import utilities.HibernateUtil;

/**
 *
 * @author window
 */
public class LineRepository {

    public static List<ModelChartLine> getSanPhamBanNhieuNhat() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ModelChartLine> listModel = new ArrayList<>();
        List<Object[]> list = session.createNativeQuery("""
            select top 5 c.ten, sum(a.SoLuong * b.GiaBan) from chitietphieuxuat a 
            join ChiTietSanPham b on a.idchitietsp = b.id
            join sanpham c on b.idsanpham = c.id
            group by c.ten
            order by SUM(a.SoLuong * b.GiaBan) desc
            """).list();
        list.stream().forEach(el -> {
            String name = (String) el[0];
            BigDecimal teamId = (BigDecimal) el[1];
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            listModel.add(new ModelChartLine(name, teamId));
        });
        return listModel;
    }

}
