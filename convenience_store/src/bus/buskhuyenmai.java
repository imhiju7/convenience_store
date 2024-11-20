/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daokhuyenmai;
import dto.dtokhuyenmai;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author giavi
 */
public class buskhuyenmai {
    private daokhuyenmai daoKM = new daokhuyenmai();
    public dtokhuyenmai getkmbyname(String name){
        return daoKM.getkmbyname(name);
    }
    public int updatekhuyenmai(dtokhuyenmai km){
        return daoKM.updateKhuyenMai(km);
    }
    public ArrayList<dtokhuyenmai> getlist(){
        return daoKM.getlist();
    }
    public ArrayList<dtokhuyenmai> getkhuyenmaitoday(){
        ArrayList<dtokhuyenmai> list = getlist();
        ArrayList<dtokhuyenmai> result = new ArrayList<>();
        Date day = new Date();
        for(dtokhuyenmai i: list){
            if(i.getNgayBatDau().before(day) && i.getNgayHetHan().after(day)){
                result.add(i);
            }
        }
        return result;
    }
}
