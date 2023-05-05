package domainModels;

import infrastructures.constant.TrangThaiPhieuConstant;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author QUOC HUY
 */
@Table(name = "PhieuXuat")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PhieuXuat implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private UUID id;
    
    @Column(name = "MaPhieu")
    private String maPhieu;

    @Column(name = "NgayTao")
    private Long ngayTao;

    @Column(name = "GhiChu", columnDefinition = "NVARCHAR(255)")
    private String ghiChu;

    @Column(name = "NgayThanhToan")
    private Long ngayThanhToan;

    @Column(name = "TrangThai")
    private TrangThaiPhieuConstant trangThai;

    @ManyToOne
    @JoinColumn(name = "IdNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "IdKhachHang")
    private KhachHang khachHang;

}
