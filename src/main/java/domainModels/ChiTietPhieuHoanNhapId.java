/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author window
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietPhieuHoanNhapId implements Serializable {

    private PhieuHoanNhap idPhieuHoanNhap;

    private ChiTietSanPham idChiTietSp;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idPhieuHoanNhap);
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
        final ChiTietPhieuHoanNhapId other = (ChiTietPhieuHoanNhapId) obj;
        if (!Objects.equals(this.idPhieuHoanNhap, other.idPhieuHoanNhap)) {
            return false;
        }
        return Objects.equals(this.idChiTietSp, other.idChiTietSp);
    }

}
