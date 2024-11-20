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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.daokhachhang;
public class buskhachhang {
    private daokhachhang daoKhachHang;
    
    public buskhachhang() {
        this.daoKhachHang = new daokhachhang();
    }

//    public ArrayList<dtokhachhang> getAllKhachHang() {
//        try {
//            return daoKhachHang.getAllKhachHang();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public ArrayList<dtokhachhang> getAllKhachHang() {
    return daoKhachHang.getAllKhachHang(); // Gọi từ lớp daokhachhang
}

     public boolean addKhachHang(dtokhachhang khachHang) {
        return daoKhachHang.addKhachHang(khachHang);  // Gọi phương thức thêm từ DAO
    }
     
    public boolean checkSDTExist(String sdt) {
    return daoKhachHang.checkSDTExist(sdt);  // Check if the phone number exists
}


     
    public boolean updateKhachHang(dtokhachhang khachHang) {
        try {
            return daoKhachHang.updateKhachHang(khachHang);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
     public int getSoLuongKH() {
        return daoKhachHang.getSoLuongKH();
    }
    public int updatediemtichluy(dtokhachhang i){
        return dao.updatediemtichluy(i);
    }
    public dtokhachhang getkhbyphone(String phone){
        return dao.getkhbyphone(phone);
    }
    public boolean checkphone(String phone){
        return dao.checkphone(phone);
    } 
      public int getNextCustomerCode() {
        return daoKhachHang.getNextCustomerCode();
    }
    public dtokhachhang getKhachHangById(int maKhachHang) {
        return daoKhachHang.getKhachHangById(maKhachHang);
    }
    public boolean deleteKhachHang(int maKhachHang) {
        try {
            return daoKhachHang.deleteKhachHang(maKhachHang);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
