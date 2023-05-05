package domainModels;

import infrastructures.constant.TrangThaiPhieuConstant;
import infrastructures.constant.TrangThaiPhieuHoanConstant;
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
@Table(name = "PhieuHoanXuat")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuHoanXuat implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "NgayTao")
    private Long ngayTao;

    @Column(name = "NgayThanhToan")
    private Long ngayThanhToan;

    @Column(name = "GhiChu", columnDefinition = "NVARCHAR(255)")
    private String ghiChu;

    @Column(name = "LiDo", columnDefinition = "NVARCHAR(255)")
    private String liDo;

    @Column(name = "TrangThai")
    private TrangThaiPhieuHoanConstant trangThai;

    @ManyToOne
    @JoinColumn(name = "idPhieuXuat")
    private PhieuXuat phieuXuat;

}
