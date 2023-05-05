/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities.palette;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author admin
 */
public class lable extends JLabel{

    public lable() {
        this.setFont(new Font("Constantia", Font.BOLD, 28));
        this.setForeground(Color.RED);
        this.setBackground(Color.white);
        this.setSize(350, 50);
    }
    
}
