/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cores.truongPhongs.customModels;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author MMC
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TpQuanLySanPhamCustom {

    private UUID id;

    private String ma;

    private String ten;

    private BigDecimal giaBanMax;
    
    private BigDecimal giaBanMin;
    
    private BigDecimal giaNhapMax;
    
    private BigDecimal giaNhapMin;
    
    private Integer soLuong;

    public TpQuanLySanPhamCustom(UUID id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }
    
    

}
