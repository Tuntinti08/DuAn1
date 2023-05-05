package cores.nhanVienQuanLy.customModels;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Acer
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NvqlGetTenNhanVienCustom {
    private UUID id;
    private String ten;
}
