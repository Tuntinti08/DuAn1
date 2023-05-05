package cores.truongPhongs.customModels;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TP_NamBaoHanhCustom {

    private UUID id;
    private String ma;
    private String ten;

}
