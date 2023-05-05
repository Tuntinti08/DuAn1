/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.Chart.repository;

import cores.Chart.model.ModelChartPie;
import cores.Chart.model.ModelChartPie1;
import domainModels.ChiTietSanPham;
import java.awt.Color;
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
public class PieRepository {

    public static List<ModelChartPie> getSoLuongSpTonNhieuNhat() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ModelChartPie> listModel = new ArrayList<>();
        List<Object[]> list = session.createNativeQuery("""
            select top 5 b.Ten, SUM(a.Soluongton) as soluong from ChiTietSanPham a join SanPham b on a.IdSanPham = b.Id
            group by b.Ten
            order by SUM(a.Soluongton) desc
            """).list();
        list.stream().forEach(el -> {
            String name = (String) el[0];
            int teamId = (int) el[1];
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            listModel.add(new ModelChartPie(name, teamId, new Color(r, g, b)));
        });
        return listModel;
    }

    public static List<ModelChartPie1> getSanPhamKhachHangMuaNhieuNhat() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ModelChartPie1> listModel = new ArrayList<>();
        List<Object[]> list = session.createNativeQuery("""
            select top 5 b.Ten, SUM(c.SoLuong) as soluong from ChiTietSanPham a 
            join SanPham b on a.IdSanPham = b.Id
            join ChiTietPhieuXuat c on a.Id = c.IdChiTietSP
            group by b.Ten
            order by SUM(c.SoLuong) desc
            """).list();
        list.stream().forEach(el -> {
            String name = (String) el[0];
            int teamId = (int) el[1];
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            listModel.add(new ModelChartPie1(name, teamId, new Color(r, g, b)));
        });
        return listModel;
    }
}
