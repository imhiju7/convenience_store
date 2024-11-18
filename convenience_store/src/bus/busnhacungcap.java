/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daonhacungcap;
import dto.dtonhacungcap;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class busnhacungcap {
    private daonhacungcap daoNCC = new daonhacungcap();
    
    public dtonhacungcap getById(int id){
        return daoNCC.getById(id);
    }
    public dtonhacungcap getByName(String name){
        return daoNCC.getByName(name);
    }
    
    public ArrayList <dtonhacungcap> getlist(){
        return daoNCC.getlist();
    }
}
