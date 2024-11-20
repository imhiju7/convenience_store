/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daoctphieunhap;
import dto.dtoctphieunhap;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class busctphieunhap {
    private daoctphieunhap daoHD = new daoctphieunhap();
    public ArrayList <dtoctphieunhap> dsctpn;
    
    public busctphieunhap() {
        getlist() ;
    }
        
    public busctphieunhap(int maphieunhap) {
        getlist(maphieunhap) ;
    }
    
    public void getlist() {
        dsctpn =  daoHD.getlist();
    }
    public void getlist(int maphieunhap) {
        dsctpn =  daoHD.getlist(maphieunhap);
    }
    
    // Business logic method to add a new HD record
    public void add (dtoctphieunhap HD) {
        daoHD.create(HD);
    }
    public void update(dtoctphieunhap ctpn){
        daoHD.updatectphieunhap(ctpn);
    }
    public dtoctphieunhap get(int mactphieunhap){
        for(dtoctphieunhap pn: dsctpn){
            if(pn.getMaPhieuNhap() == mactphieunhap){
                return pn;
            }
        }
        return null;
    }
    public int maxID(){
        return daoHD.maxID();
    }
    public dtoctphieunhap getspganhh(int masp){
        ArrayList<dtoctphieunhap> list = daoHD.getalllist();
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
    public void needToFillList(int maNCC) {
        daoHD = new daoctphieunhap();
        dsctpn =  daoHD.needToFillList(maNCC);
    }
    
    public static void main(String[] args) {
        // Create an instance of the BUS class
        busctphieunhap bus = new busctphieunhap();
            System.out.println(bus.maxID());
           bus.needToFillList();
        // Print each dtoctphieunhap object in the list
        for (dtoctphieunhap HD : bus.dsctpn) {
            System.out.println(HD);
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

    public void needToFillList() {
        daoHD = new daoctphieunhap();
        dsctpn =  daoHD.needToFillList();
    }

    public void create(dtoctphieunhap ct) {
        daoHD.create(ct);
    }
}
