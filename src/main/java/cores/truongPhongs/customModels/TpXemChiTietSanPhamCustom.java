/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.truongPhongs.customModels;

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
 * @author Acer
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TpXemChiTietSanPhamCustom {

    private UUID id;

    private String hinhAnh;

    private BigDecimal GiaNhap;

    private BigDecimal GiaBan;

    private int namBaoHanh;

    private MauConstant mau;
    
    private int size;

    private SanPham sanPham;

    private DonVi donVi;
    
    private TrangThaiSanPhamConstanst trangThai;
    
    private int soLuongTon;
    
    private Long ngayTao;

    public TpXemChiTietSanPhamCustom(String hinhAnh, BigDecimal GiaNhap, BigDecimal GiaBan, int namBaoHanh, MauConstant mau, SanPham sanPham, DonVi donVi, TrangThaiSanPhamConstanst trangThai, int soLuongTon) {
        this.hinhAnh = hinhAnh;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.namBaoHanh = namBaoHanh;
        this.mau = mau;
        this.sanPham = sanPham;
        this.donVi = donVi;
        this.trangThai = trangThai;
        this.soLuongTon = soLuongTon;
    }
    
    
}
