/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daonhanvien;
/**
 *
 * @author Hieu PC
 */
public class busnhanvien {
    daonhanvien daonv = new daonhanvien();
    
    
    // get
    
    public int getmachucvu(int manv){
        return daonv.getmachucvu(manv);
    }
}