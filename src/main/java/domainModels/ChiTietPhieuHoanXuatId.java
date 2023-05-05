package domainModels;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author window
 */
public class ChiTietPhieuHoanXuatId implements Serializable {

    private PhieuHoanXuat idPhieuHoanXuat;

    private ChiTietSanPham idChiTietSp;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idPhieuHoanXuat);
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
        final ChiTietPhieuHoanXuatId other = (ChiTietPhieuHoanXuatId) obj;
        if (!Objects.equals(this.idPhieuHoanXuat, other.idPhieuHoanXuat)) {
            return false;
        }
        return Objects.equals(this.idChiTietSp, other.idChiTietSp);
    }

}
