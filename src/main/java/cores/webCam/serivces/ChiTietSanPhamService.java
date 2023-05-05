/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.webCam.serivces;

import domainModels.ChiTietSanPham;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author QUOC HUY
 */
public interface ChiTietSanPhamService {
    ConcurrentHashMap<UUID, ChiTietSanPham> getMapChiTietSanPham();
    boolean addChiTietPhieuXuat(UUID idPhieuXuat, UUID idChiTietSanPham, int soLuong);
}
