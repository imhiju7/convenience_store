/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtocthoadon;
import dto.dtohoadon;
import dto.dtohoadon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author AD
 */
public class daohoadon {
     
    
    public daohoadon() {
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
    public ArrayList<dtohoadon> getlist() {
        ArrayList<dtohoadon> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM hoadon "
                + "where hoadon.isDelete = 0";
        
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtohoadon hoadon = new dtohoadon(
                    rs.getInt("maHoaDon"),
                    rs.getInt("maNhanVien"),
                    rs.getDouble("tongTien"),
                    rs.getInt("maKhachHang"),
                    rs.getInt("maKhuyenMai"),
                    rs.getDate("ngayMua"),
                    rs.getString("ghiChu"),
                    rs.getInt("maTichDiem")
                );
                list.add(hoadon);
            }
        } catch (SQLException e) {
            Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }
    
    public void add(dtohoadon hoadon) {
        String sql = "INSERT INTO hoadon (maHoaDon, maNhanVien, tongTien, maKhachHang, maKhuyenMai, ngayMua, " +
                     "ghiChu, maTichDiem, isDelete) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(2, hoadon.getMaNhanVien());
            pst.setInt(1, hoadon.getMaHoaDon());
            pst.setDouble(3, hoadon.getTongTien());
            pst.setInt(4, hoadon.getMaKhachHang());
            pst.setInt(5, hoadon.getMaKhuyenMai());
            pst.setDate(6, (Date) hoadon.getNgayMua());
            pst.setString(7, hoadon.getGhiChu());
            pst.setInt(8, hoadon.getMaTichDiem());
            pst.setInt(9, 0);

            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    }
    
