/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daokhuyenmai;
import dto.dtokhuyenmai;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class buskhuyenmai {
    public buskhuyenmai(){};
    
    private daokhuyenmai daokm = new daokhuyenmai();
    
    public ArrayList<dtokhuyenmai> getList(){
        return daokm.getList();
    }
    
    public int getMaxMaKhuyenMai(){
        return daokm.getMaxMaKhuyenMai();
    }
    
    public boolean addKhuyenMai(dtokhuyenmai km){
        return daokm.addKhuyenMai(km);
    }
    
    public boolean Deleted(int maKhuyenMai){
        return daokm.Deleted(maKhuyenMai);
    }
    
    public boolean Update(dtokhuyenmai km){
        return daokm.Update(km);
    }
    
    public ArrayList<dtokhuyenmai> getListByString(String column, String condition){
        return daokm.getListByString(column, condition);
    }
    
    public ArrayList<dtokhuyenmai> getListByInt(String column, int condition){
        return daokm.getListByInt(column, condition);
    }
    
    public ArrayList<dtokhuyenmai> getListByCondition(String column, String condition){
        return daokm.getListByCondition(column, condition);
    }
    
    public ArrayList<dtokhuyenmai> getListByDate(String date){
        return daokm.getListByDate(date);
    }
}
