/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daocthoadon;
import dto.dtocthoadon;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class buscthoadon {
    private daocthoadon daoCTHD = new daocthoadon();
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
    
    public static void main(String[] args) {
        // Create an instance of the BUS class
        buscthoadon bus = new buscthoadon();

        // Print each dtocthoadon object in the list
        for (dtocthoadon detail : bus.dscthd) {
            System.out.println(detail);
        }
    }
}
