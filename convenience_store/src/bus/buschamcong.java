/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.daochamcong;
import dto.dtochamcong;
import java.util.ArrayList;

/**
 *
 * @author PHUONG ANH
 */
public class buschamcong {
    private daochamcong daoChamCong= new daochamcong();
    public ArrayList <dtochamcong> dscc;
    public buschamcong() {
        getlist() ;
    }

    // Get list of all cham cong records
    public void getlist() {
        this.dscc =  daoChamCong.getlist();
    }

    public void create(dtochamcong cc){
        daoChamCong.create(cc);
    }
    public dtochamcong get(int machamcong){
        for (dtochamcong cc: dscc){
            if(cc.getMachamcong() == (machamcong)){
                return cc;
            }
        }
        return null;
    }
    // Example of a method to calculate total work hours for an employee
    public int calculateTotalHours(int maNhanVien) {
        int totalHours = 0;
        ArrayList<dtochamcong> list = daoChamCong.getlist();
        for (dtochamcong chamCong : list) {
            if (chamCong.getManhanvien() == maNhanVien) {
                totalHours += chamCong.getSogiolamviec();
            }
        }
        return totalHours;
    }
}
