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
    private daochamcong dao= new daochamcong();
    public ArrayList <dtochamcong> dscc;
    public buschamcong() {
        getlist() ;
    }

    // Get list of all cham cong records
    public void getlist() {
        this.dscc =  dao.getlist();
    }
    public ArrayList <Integer> listYears() {
        return dao.getListYears();
    }
    public ArrayList<dtochamcong> getAllChamCong() {
        return dao.getAllChamCong();
    }


    public void create(dtochamcong cc){
        dao.create(cc);
    }
    public void update(dtochamcong cc){
        dao.update(cc);
    }
    public dtochamcong get(dtochamcong chamcong){
        for (dtochamcong cc: dscc){
            if(cc.getThangchamcong() == chamcong.getThangchamcong() && cc.getNamchamcong() == chamcong.getNamchamcong()){
                return cc;
            }
        }
        return null;
    }
    public ArrayList<dtochamcong> getlistthang(int currMonth,int currYear){
        ArrayList<dtochamcong> list = dao.getlist();
        ArrayList<dtochamcong> listluong = new ArrayList<>();
        for (dtochamcong cc: list){
            if(cc.getThangchamcong() == currMonth && cc.getNamchamcong() == currYear){
                listluong.add(cc);
            }
        }
        return listluong;
    }
    
    public int countchamcong(){
        return dao.countchamcong();
    }
    
    public boolean isexist(int currmonth, int curryear){
        for (dtochamcong cc: dscc){
            if(cc.getThangchamcong() == currmonth && cc.getNamchamcong() == curryear){
                return true;
            }
        }
        return false;
    }
    
    // Example of a method to calculate total work hours for an employee
    public int calculateTotalHours(int maNhanVien) {
        int totalHours = 0;
        ArrayList<dtochamcong> list = dao.getlist();
        for (dtochamcong chamCong : list) {
            if (chamCong.getManhanvien() == maNhanVien) {
                totalHours += chamCong.getSogiolamviec();
            }
        }
        return totalHours;
    }
}
