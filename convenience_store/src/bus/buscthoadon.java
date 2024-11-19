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
    public void getlist(int id) {
        dscthd =  daoCTHD.getlist();
    }
    // Business logic method to add a new detail record
    public void add (dtocthoadon detail) {
        daoCTHD.add(detail);
    }
    public dtoctphieunhap getspganhh(int masp){
        ArrayList<dtoctphieunhap> list = daoCTPN.getalllist();
        dtoctphieunhap sp= new dtoctphieunhap();
        int day = 100000;
        for(dtoctphieunhap i : list){
            if(i.getMaSanPham() == masp){
                int han = isganhh(i.getNgayhethan().toString());
                if(han < day){
                    day = han;
                    sp = i;
                }
            }
        }
        return sp;
    }
        public int isganhh(String birthday) {
        // Định dạng ngày tháng từ chuỗi
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthday, formatter);
        // Tính tuổi hiện tại
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(currentDate,birthDate);
        return age.getDays();
    }
    public double gettongtien(ArrayList<dtocthoadon> spOrder){
        double tongtien = 0;
        for (dtocthoadon cthd : spOrder) {
            dtoctphieunhap ctpn = getspganhh(cthd.getMaSanPham());
            double giaban = ctpn.getGiaBan();
            tongtien+=giaban*cthd.getSoLuong();
        }
        return tongtien;
    }
}
