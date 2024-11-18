/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daophieunhap;
import dao.daophieunhap;
import dto.dtophieunhap;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class busphieunhap {
    private daophieunhap dao = new daophieunhap();
    public ArrayList <dtophieunhap> dspn;
    
    public busphieunhap() {
        getlist() ;
    }

    // Retrieve all records through the DAO
    public void getlist() {
        dspn =  dao.getlist();
    }

    // Business logic method to add a new HD record
    public void add (dtophieunhap HD) {
        dao.create(HD);
    }
    
    public dtophieunhap get(int maphieunhap){
        for(dtophieunhap pn: dspn){
            if(pn.getMaPhieuNhap() == maphieunhap){
                return pn;
            }
        }
        return null;
    }

    public double getTongChiPhi(){
        return dao.getTongChiPhi();
    }
    public int maxID(){
        return dao.maxID();
    }

    public static void main(String[] args) {
        // Create an instance of the BUS class
        busphieunhap bus = new busphieunhap();
        System.out.println(bus.maxID());
        // Print each dtophieunhap object in the list
        for (dtophieunhap HD : bus.dspn) {
            System.out.println(HD);
        }
        
    }

    public void create(dtophieunhap pn) {
        dao.create(pn);
    }
}
