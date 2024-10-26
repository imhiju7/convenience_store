/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daohoadon;
import dto.dtohoadon;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class bushoadon {
    private daohoadon daoHD = new daohoadon();
    public ArrayList <dtohoadon> dshd;
    
    public bushoadon() {
        getlist() ;
    }

    // Retrieve all records through the DAO
    public void getlist() {
        dshd =  daoHD.getlist();
    }

    // Business logic method to add a new HD record
    public void add (dtohoadon HD) {
        daoHD.add(HD);
    }
    
    public dtohoadon get(int mahoadon){
        for(dtohoadon hd: dshd){
            if(hd.getMaHoaDon() == mahoadon){
                return hd;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        // Create an instance of the BUS class
        bushoadon bus = new bushoadon();
        System.out.println(bus.get(56));
        // Print each dtohoadon object in the list
        for (dtohoadon HD : bus.dshd) {
            System.out.println(HD);
        }
        
    }
}
