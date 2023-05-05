package cores.nhanVienQuanLy.customModels;

import domainModels.DonVi;
import domainModels.NhaCungCap;
import domainModels.SanPham;
import infrastructures.constant.MauConstant;
import infrastructures.constant.TrangThaiSanPhamConstanst;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LuongBanHang_ChiTietSanPhamCustom {
    private UUID id;
    private int soLuongTon;
    private String hinhAnh;
    private BigDecimal GiaNhap;
    private BigDecimal GiaBan;
    private int namBaoHanh;
    private MauConstant mau;
    private TrangThaiSanPhamConstanst trangThai;
    private SanPham sanPham;
    private DonVi donVi;
    private int size;
    private Long ngayTao;
    private NhaCungCap ncc;
}
