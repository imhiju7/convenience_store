/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtokhachhang;
import dto.dtokhachhang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author giavi
 */
public class daokhachhang {

    public daokhachhang() {
    }

    public ArrayList<dtokhachhang> getlist() {
        ArrayList<dtokhachhang> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM khachhang";
        
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtokhachhang chamCong = new dtokhachhang(
                    rs.getInt("maKhachHang"),
                    rs.getString("tenKhachHang"),
                    rs.getString("SDT"),
                    rs.getInt("diemTichLuy"),
                    rs.getInt("maUuDai")
                );
                list.add(chamCong);
            }
        } catch (SQLException e) {
            Logger.getLogger(daochamcong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochamcong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }
    
    public int updatediemtichluy(dtokhachhang kh){
        Connection con = connect.connection();
        String sql = "UPDATE khachhang set diemTichLuy= ?, maUuDai = ? WHERE maKhachHang= ?";
        PreparedStatement pst;
        int rowaffect = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, kh.getDiemTichLuy());
            pst.setInt(2, kh.getMaUuDai());
            pst.setInt(3, kh.getMaKhachHang());
            rowaffect = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowaffect;
    }
    
    public int getSoLuongKH() {
        int result=0;
        String query = """
                SELECT COUNT(*) AS soLuongKhachHang FROM khachhang;
                """;
        
        try (Connection connection = connect.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
               result=resultSet.getInt("soLuongKhachHang");
            }
        } catch (Exception e) {
        }
        return result;
    }
    
    public dtokhachhang getkhbyphone(String phone){
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM khachhang WHERE SDT = ?";
        PreparedStatement pst;
        dtokhachhang kh = new dtokhachhang();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, phone);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                kh.setMaKhachHang(rs.getInt("maKhachHang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                kh.setSDT(rs.getString("SDT"));
                kh.setDiemTichLuy(rs.getInt("diemTichLuy"));
                kh.setMaUuDai(rs.getInt("maUuDai"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kh;
    }
    
    public boolean checkphone(String phone){
        java.sql.Connection con = connect.connection();
        int i = 0;
        boolean key = false;
        String sql = "SELECT * FROM khachhang where SDT = ? ";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, phone);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i > 0 ){
            key = false;
        }
        else key = true;
        return key;
    }    
}
