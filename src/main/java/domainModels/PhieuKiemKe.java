/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModels;

import infrastructures.constant.TrangThaiPhieuKiemConstant;
import infrastructures.constant.TrangThaiPhieuConstant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author window
 */
@Table(name = "PhieuKiemKe")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhieuKiemKe {
    @Id    
    @GeneratedValue
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
    private UUID id;
    
    @Column(name = "Ma", columnDefinition = "NVARCHAR(20)")
    private String ma;
    
    @Column(name = "NgayTao")
    private Long ngayTao;
    
    @Column(name = "NgayXacNhan")
    private Long ngayXacNhan;
    
    @Column(name = "GhiChu", columnDefinition = "NVARCHAR(255)")
    private String ghiChu;
    
    @Column(name = "TrangThai")
    private TrangThaiPhieuKiemConstant trangThai;
    
    @ManyToOne
    @JoinColumn(name = "IdNhanVien")
    private NhanVien nhanVien;

//    public PhieuKiemKe(String maPhieuKiem, Long ngayTao, TrangThaiPhieuKiem trangThaiPhieuKiem, NhanVien nv) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public PhieuKiemKe(String ma, Long ngayTao, TrangThaiPhieuKiemConstant trangThai, NhanVien nhanVien) {
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.nhanVien = nhanVien;
    }
    
}
