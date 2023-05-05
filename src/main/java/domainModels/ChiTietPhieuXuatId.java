package domainModels;

import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author QUOC HUY
 */
@Getter
@Setter
public class ChiTietPhieuXuatId implements Serializable{

    private PhieuXuat idPhieuXuat;

    private ChiTietSanPham idChiTietSp;
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idPhieuXuat);
        hash = 71 * hash + Objects.hashCode(this.idChiTietSp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietPhieuXuatId other = (ChiTietPhieuXuatId) obj;
        if (!Objects.equals(this.idPhieuXuat, other.idPhieuXuat)) {
            return false;
        }
        return Objects.equals(this.idChiTietSp, other.idChiTietSp);
    }
    
}
