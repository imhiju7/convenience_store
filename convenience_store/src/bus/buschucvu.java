/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.daochucvu;
import dto.dtochucvu;
/**
 *
 * @author Hieu PC
 */
public class buschucvu {
    daochucvu daocv = new daochucvu();
    
    // get
    
    public String gettencvbymacv(int macv){
        return daocv.gettencvbymacv(macv);
    }
}
