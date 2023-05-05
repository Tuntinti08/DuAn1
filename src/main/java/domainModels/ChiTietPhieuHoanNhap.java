package domainModels;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author window
 */
@Entity
@Getter
@Setter
@Table(name = "ChiTietPhieuHoanNhap")
@IdClass(ChiTietPhieuHoanNhapId.class)
public class ChiTietPhieuHoanNhap implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdPhieuHoanNhap", nullable = false)
    private PhieuHoanNhap idPhieuHoanNhap;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "IdChiTietSP", nullable = false)
    private ChiTietSanPham idChiTietSp;
    
    @Column(name = "SoLuong")
    private int soLuong;
    
    @Column(name = "LiDo")
    private String liDo;
    
}