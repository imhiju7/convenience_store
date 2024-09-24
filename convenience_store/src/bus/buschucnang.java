/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daochucnang;
import dto.dtochucnang;

import java.util.ArrayList;
/**
 *
 * @author Hieu PC
 */
public class buschucnang {
    daochucnang daocn = new daochucnang();
    
    // get
    
    public ArrayList<dtochucnang> getlistchucnangbydanhmuc(int madanhmuc){
        return daocn.getlistchucnangbydanhmuc(madanhmuc);
    }
}
