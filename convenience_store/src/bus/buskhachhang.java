/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daokhachhang;
import dto.dtokhachhang;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class buskhachhang {
    private daokhachhang dao = new daokhachhang();
    public ArrayList <dtokhachhang> dskh;
    
    public buskhachhang() {
        getlist() ;
    }

    // Retrieve all records through the DAO
    public void getlist() {
        dskh =  dao.getlist();
    }

    public dtokhachhang getkhachhangbyid(int makhachhang) {
        for(dtokhachhang kh:dskh){
            if(kh.getMaKhachHang() == makhachhang){
                return kh;
            }
        }
        return null;
    }
    public int getSoLuongKH () {
        return dao.getSoLuongKH();
    }
    public static void main(String[] args) {
        // Create an instance of the BUS class
        buskhachhang bus = new buskhachhang();
        System.out.print(bus.getkhachhangbyid(4));
    }
    
}
