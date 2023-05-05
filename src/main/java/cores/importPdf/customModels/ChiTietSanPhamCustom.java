/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.importPdf.customModels;

import domainModels.DonVi;
import domainModels.SanPham;
import infrastructures.constant.MauConstant;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author QUOC HUY
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietSanPhamCustom {

    private UUID id;

    private int soLuongTon;

    private String hinhAnh;

    private BigDecimal GiaNhap;

    private BigDecimal GiaBan;

    private int namBaoHanh;

    private MauConstant mau;
    
    private TrangThaiSanPhamConstanst trangThai;
    
    private int size;
    
    private Long ngayTao;

    private SanPham sanPham;

    private DonVi donVi;
    
    private String maSpNcc;
    
    private UUID idPhieuNhap;
    
}
