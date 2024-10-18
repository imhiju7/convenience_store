/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daonhanvien;
import dto.dtonhanvien;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Hieu PC
 */
public class busnhanvien {
    daonhanvien daonv = new daonhanvien();
    public ArrayList<dtonhanvien> list_nv;

    public busnhanvien() {
        getlist();
    }
    
    // get
    
    public int getmachucvu(int manv){
        return daonv.getmachucvu(manv);
    }
    
    public String gettennvbymanv(int manv){
        return daonv.gettennvbymanv(manv);
    }
    
    public void getlist() {
        this.list_nv = daonv.getlist();
    }

    public static void main(String args[]) {
        busnhanvien bus = new busnhanvien();
        for(dtonhanvien nv:bus.list_nv){
            System.out.print(nv);
        }
    }
}
