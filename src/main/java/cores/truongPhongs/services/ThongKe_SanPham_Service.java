/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cores.truongPhongs.services;

import cores.truongPhongs.customModels.ThongKe_SanPham_Custom;
import domainModels.SanPham;
import java.util.List;


public interface ThongKe_SanPham_Service {
    public  List<ThongKe_SanPham_Custom> getABC(Long ngaybd , Long ngaykt);
}
