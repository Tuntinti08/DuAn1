/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author window
 */
public class ChiTietPhieuKiemKeId implements Serializable{
    private PhieuKiemKe idPhieuKiemKe;

    private ChiTietSanPham idChiTietSp;
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idPhieuKiemKe);
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
        final ChiTietPhieuKiemKeId other = (ChiTietPhieuKiemKeId) obj;
        if (!Objects.equals(this.idPhieuKiemKe, other.idPhieuKiemKe)) {
            return false;
        }
        return Objects.equals(this.idChiTietSp, other.idChiTietSp);
    }
}
