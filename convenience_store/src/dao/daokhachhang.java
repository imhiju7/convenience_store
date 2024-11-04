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
    public static void main(String[] args) {
        // Create an instance of the DAO class
        daokhachhang dao = new daokhachhang();

        // Retrieve the list of details
        ArrayList<dtokhachhang> list = dao.getlist();

        // Print each dtokhachhang object in the list
        for (dtokhachhang detail : list) {
            System.out.println(detail);
        }
    }
}
