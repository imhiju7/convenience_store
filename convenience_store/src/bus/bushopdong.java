/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daohopdong;
import dto.dtohopdong;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class bushopdong {
    public bushopdong(){};
    
    public dtohopdong selectHopDong(int maHD){
        return new daohopdong().selectHopDong(maHD);
    }
    
    
    public ArrayList<dtohopdong> getlist(){
        return new daohopdong().getlist();
    }
    
    
    public ArrayList<dtohopdong> getlistConditon(String columnName, String conditonValue){
        return new daohopdong().getlistConditon(columnName, conditonValue);
    }
    
    public int getMaxMaHopDong() {
        return new daohopdong().getMaxMaHopDong();
    }
     public dtohopdong gethdnhanvien(int manv) {
        return new daohopdong().gethopdongnhanvien(manv);
    }
    
    
    
    public ArrayList<String> getListMaNV(){
        return new daohopdong().getListMaNV();
    }
    
    public boolean addHopDong(dtohopdong hd) {
       return new daohopdong().addHopDong(hd);
    }
    
    public Boolean Deleted(int maHopDong) {
        return new daohopdong().Deleted(maHopDong);
    }
    
    public Boolean Update(dtohopdong hd) {
        return new daohopdong().Update(hd);
    }
}
