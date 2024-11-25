/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daotichdiem;
import dto.dtotichdiem;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class bustichdiem {
    private daotichdiem daoTichDiem = new daotichdiem();
    
    public dtotichdiem gettdbytien(double tien){
        ArrayList<dtotichdiem> list = daoTichDiem.getlist();
        dtotichdiem td = new dtotichdiem();
        for(dtotichdiem i : list){
            if(tien > i.getTien()){
                td = i;
            }
            else{
                break;
            }
        }
        return td;
    }
    public dtotichdiem get(int id){
        return daoTichDiem.get(id);
    }

}
