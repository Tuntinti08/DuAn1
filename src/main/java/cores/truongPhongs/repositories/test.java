/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cores.truongPhongs.repositories;

import cores.truongPhongs.customModels.TpDonViCustom;
import cores.truongPhongs.customModels.TpNhanVienCustom;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import utilities.HibernateUtil;

/**
 *
 * @author LENOVO
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

              List<TpNhanVienCustom> list = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
     Query q = s.createQuery(" SELECT new cores.truongPhongs.customModels.TpNhanVienCustom ("
                + " nv.id as id, "
                + " nv.ma as ma, "
                + " nv.ten as ten, "
                + " nv.sdt as sdt, "
                + " nv.email as email, "
                + " nv.matKhau as matKhau, "
                + " nv.ngaySinh as ngaySinh, "
                + " nv.hinhAnh as hinhAnh, "
                + " nv.gioiTinh as gioiTinh, "
                + " nv.diaChi as diaChi, "
                + " nv.trangThai as trangThai, "
                + " nv.idChucVu as idChucVu "
                + " ) FROM domainModels.NhanVien nv"
        );
        list = q.getResultList();
        s.close();
        System.out.println(list.size());
    }

}
