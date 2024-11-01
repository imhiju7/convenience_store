/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtohoadon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AD
 */
public class daohoadon {
    public List<dtohoadon> getAll () {
         List<dtohoadon> hoaDonList = new ArrayList<>();
        String query = """
                SELECT * FROM hoadon      
                """;
        
        try (Connection connection = connect.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                dtohoadon hoaDon = new dtohoadon();
                hoaDon.setMaHoaDon(resultSet.getInt("maHoaDon"));
                hoaDon.setMaNhanVien(resultSet.getInt("maNhanVien"));
                hoaDon.setTongTien(resultSet.getDouble("tongTien"));
                hoaDon.setMaKhachHang(resultSet.getInt("maKhachHang"));
                hoaDon.setMaKhuyenMai(resultSet.getInt("maKhuyenMai"));
                hoaDon.setNgayMua(resultSet.getTimestamp("ngayMua"));
                hoaDon.setGhiChu(resultSet.getString("ghiChu"));
                hoaDon.setMaTichDiem(resultSet.getInt("maTichDiem"));
                hoaDon.setIsHidden(resultSet.getInt("isDelete"));
                
                hoaDonList.add(hoaDon);
            }
        } catch (Exception e) {
        }
        return hoaDonList;
    }
    public double getTongDoanhThu() {
        double result=0;
        String query = """
                SELECT SUM(tongTien) AS tongDoanhThu FROM hoadon WHERE isDelete = 0;
                """;
        
        try (Connection connection = connect.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
               result=resultSet.getInt("tongDoanhThu");
            }
        } catch (Exception e) {
        }
        return result;
    }
}
