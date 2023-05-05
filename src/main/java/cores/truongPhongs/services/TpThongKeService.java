/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cores.truongPhongs.services;

import cores.truongPhongs.customModels.TpThongKeSpCustom;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface TpThongKeService {

    public List<Integer> getList();

    public List<Integer> getSoDonHoanNhap();

    public List<Integer> getSoDonHoanXuat();

    public List<Integer> getSoSanPhamDaNhap();

    public List<Integer> getSoSanPhamDaXuat();

    public List<TpThongKeSpCustom> getListSp(Long ngayBatDau, Long ngayKetThuc, String txt);

    public List<Integer> getSoLuongSpHoanNhap();

    public List<Integer> getSoLuongSpHoanXuat();
    
    List<TpThongKeSpCustom> phanTrang(List<TpThongKeSpCustom> list, int offset, int limit);
    
}
