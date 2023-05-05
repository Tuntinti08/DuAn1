/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author QUOC HUY
 */
public class MaTuSinh {

    public static String gen(String ma) {
        int last = (int) Math.floor((Math.random()) * 999999);
        if (last < 100000 && last >= 10000) {
            return ma + "0" + last;
        }
        if (last < 10000 && last >= 1000) {
            return ma + "00" + last;
        }
        if (last < 1000 && last >= 100) {
            return ma + "000" + last;
        }
        if (last < 100 && last >= 10) {
            return ma + "0000" + last;
        }
        if (last < 10) {
            return ma + "00000" + last;
        }

        return ma + last;

    }

    public static void main(String[] args) {
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));
        System.out.println(gen("HD"));

    }
}
