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
    private ArrayList<dtonhanvien> list_nv;
    
    // get
    
    public int getmachucvu(int manv){
        return daonv.getmachucvu(manv);
    }
    
    public String gettennvbymanv(int manv){
        return daonv.gettennvbymanv(manv);
    }
    
    
    
    public void list() throws SQLException{
        daonhanvien daonv = new daonhanvien();
        daonv.list();
        list_nv = daonv.getlist();
    }
    
    public ArrayList<dtonhanvien> getList(){
        return list_nv;
    }
    
    
}
