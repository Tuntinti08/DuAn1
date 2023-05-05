/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import infrastructures.constant.TrangThaiPhieuHoanConstant;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author QUOC HUY
 */
@Table(name = "PhieuHoanNhap")
@Entity
@Getter
@Setter
public class PhieuHoanNhap implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private UUID id;

    @Column(name = "NgayTao")
    private Long ngayTao;

    @Column(name = "GhiChu", columnDefinition = "NVARCHAR(255)")
    private String ghiChu;
    
    @Column(name = "LiDo", columnDefinition = "NVARCHAR(255)")
    private String liDo;

    @Column(name = "NgayThanhToan")
    private Long ngayThanhToan;

    @Column(name = "TrangThai")
    private TrangThaiPhieuHoanConstant trangThai;

    @ManyToOne
    @JoinColumn(name = "idPhieuNhap")
    private PhieuNhap phieuNhap;
}
