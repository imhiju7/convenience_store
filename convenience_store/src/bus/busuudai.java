/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daouudai;
import dto.dtouudai;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class busuudai {
    private daouudai daoUuDai = new daouudai();
    
    public ArrayList<dtouudai> getList(){
        return daoUuDai.getList();
    }
    
    public boolean addUuDai(dtouudai uudai) {
        return daoUuDai.addUuDai(uudai);
    }

    public Boolean Deleted(int maUuDai) {
        return daoUuDai.Deleted(maUuDai);
    }

    public Boolean Update(dtouudai uudai) {
        return daoUuDai.Update(uudai);
    }
    
   
    public ArrayList<dtouudai> getListByCondition(String value){
        return daoUuDai.getListByCondition(value);
    }
    
    public int getMaxMaUuDai(){
        return daoUuDai.getMaxMaUuDai();
    }
    public ArrayList<String> layDanhSachMaUuDai() {
    return daoUuDai.layDanhSachMaUuDai();
}

    public dtouudai getud(dtouudai i){
        return daoUuDai.getud(i);
    }
    public dtouudai setudbydiem(int diem){
        ArrayList<dtouudai> list = daoUuDai.getlist();
        dtouudai ud = new dtouudai();
        for(dtouudai i : list){
            if(diem > i.getMocUuDai()){
                ud = i;
            }
            else{
                break;
            }
        }
        return ud;
    }
}
