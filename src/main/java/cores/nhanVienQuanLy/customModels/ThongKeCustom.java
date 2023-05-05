package cores.nhanVienQuanLy.customModels;

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
public class ThongKeCustom {
     private int soLuongTon;

    @Override
    public String toString() {
        return "ThongKeCustom{" + "soLuongTon=" + soLuongTon + '}';
    }
     
}
