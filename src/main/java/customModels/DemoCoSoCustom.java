/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModels;

import infrastructures.constant.CoSoConstant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author QUOC HUY
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoCoSoCustom {
    private UUID id;

    private String ma;

    private String ten;

    private String viTri;

    private CoSoConstant trangThai;
    
}
