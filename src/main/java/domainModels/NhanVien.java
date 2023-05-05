package domainModels;

import infrastructures.constant.GioiTinhConstant;
import infrastructures.constant.TrangThaiNhanVienConstant;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "NhanVien")
@Entity
public class NhanVien implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten", columnDefinition = "NVARCHAR(255)")
    private String ten;
    
    @Column(name = "Sdt")
    private String sdt;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "MatKhau", columnDefinition = "NVARCHAR(255)")
    private String matKhau;
    
    @Column(name = "NgaySinh")
    private Long ngaySinh;
    
    @Column(name = "HinhAnh")
    private String hinhAnh;
    
    @Column(name = "GioiTinh")
    private GioiTinhConstant gioiTinh;
    
    @Column(name = "DiaChi", columnDefinition = "NVARCHAR(255)")
    private String diaChi;
    
    @Column(name = "TrangThai")
    private TrangThaiNhanVienConstant trangThai;
    
    @ManyToOne
    @JoinColumn(name = "idChucVu")
    private ChucVu idChucVu;
}
