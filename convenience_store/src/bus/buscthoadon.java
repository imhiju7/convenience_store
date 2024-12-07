/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daocthoadon;
import dao.daoctphieunhap;
import dto.dtocthoadon;
import dto.dtoctphieunhap;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author giavi
 */
public class buscthoadon {
    private daocthoadon daoCTHD = new daocthoadon();
    private daoctphieunhap daoCTPN = new daoctphieunhap();
    public ArrayList <dtocthoadon> dscthd;
    
    public buscthoadon() {
        getlist() ;
    }

    // Retrieve all records through the DAO
    public void getlist() {
        dscthd =  daoCTHD.getlist();
    }
    public void getlistbyhoadon(int mahd){
        dscthd = daoCTHD.getlistbyhoadon(mahd);
    }
    // Business logic method to add a new detail record
    public void add (dtocthoadon detail) {
        daoCTHD.add(detail);
    }
    public double gettongtien(ArrayList<dtocthoadon> spOrder){
        double tongtien = 0;
        for (dtocthoadon cthd : spOrder) {
            dtoctphieunhap ctpn = daoCTPN.getlistlo(cthd.getMaSanPham()).get(0);
            double giaban = ctpn.getGiaBan();
            tongtien+=giaban*cthd.getSoLuong();
        }
        return tongtien;
    }
}
