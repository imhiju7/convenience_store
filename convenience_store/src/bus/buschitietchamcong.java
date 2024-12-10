/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.daochitietchamcong;
import dto.dtochitietchamcong;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PHUONG ANH
 */
public class buschitietchamcong {

    private daochitietchamcong daoChiTietChamCong = new daochitietchamcong();
    public ArrayList <dtochitietchamcong> dsctcc;
    
    public buschitietchamcong() {
        getlist() ;
    }

    // Retrieve all records through the DAO
    public void getlist() {
        dsctcc =  daoChiTietChamCong.getlist();
    }

    // Business logic method to add a new detail record
    public void create (dtochitietchamcong detail) {
        daoChiTietChamCong.create(detail);
    }
    public boolean update(dtochitietchamcong ctcc){
        return daoChiTietChamCong.updatectchamcong(ctcc);
    }
    public dtochitietchamcong getctcc(Date day,int macc){
        return daoChiTietChamCong.getctchamcong(day, macc);
    }
    public ArrayList<dtochitietchamcong> getctccdathuchien(Date day,int macc){
        return daoChiTietChamCong.getctchamcongdathuchien(day, macc);
    }
    public dtochitietchamcong getctccab(Date day,java.sql.Timestamp gioBatDau,java.sql.Timestamp gioKetThuc){
        return daoChiTietChamCong.getctccab(day, gioBatDau, gioKetThuc);
    }
    public static void main(String[] args) {
        // Create an instance of the BUS class
        buschitietchamcong bus = new buschitietchamcong();

        // Print each dtochitietchamcong object in the list
        for (dtochitietchamcong detail : bus.dsctcc) {
            System.out.println(detail);
        }
    }
}


