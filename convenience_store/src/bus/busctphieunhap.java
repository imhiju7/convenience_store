/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daoctphieunhap;
import dto.dtoctphieunhap;
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
    public static void main(String[] args) {
        // Create an instance of the BUS class
        busctphieunhap bus = new busctphieunhap();
            System.out.println(bus.maxID());
        // Print each dtoctphieunhap object in the list
        for (dtoctphieunhap HD : bus.dsctpn) {
            System.out.println(HD);
        }
    }
}
