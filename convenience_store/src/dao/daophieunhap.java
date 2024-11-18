/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtophieunhap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author giavi
 */
public class daophieunhap {

    public daophieunhap() {
    }
    
    public ArrayList<dtophieunhap> getlist() {
        ArrayList<dtophieunhap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM phieunhap "
                + "where phieunhap.isDelete = 0";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtophieunhap phieunhap = new dtophieunhap(
                    rs.getInt("maPhieuNhap"),
                    rs.getTimestamp("ngayNhap"),
                    rs.getDouble("tongTien"),
                    rs.getInt("maNhaCungCap"),
                    rs.getInt("maNhanVien"),
                    rs.getString("ghiChu")
                );
                list.add(phieunhap);
            }
        } catch (SQLException e) {
            Logger.getLogger(daophieunhap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daophieunhap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }
    
    public void create(dtophieunhap phieunhap) {
        String sql = "INSERT INTO phieunhap (maPhieuNhap, ngayNhap, tongTien, maNhaCungCap, maNhanVien, ghiChu, isDelete) VALUES (?, ?, ?, ?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, phieunhap.getMaPhieuNhap());
            pst.setTimestamp(2, (Timestamp) phieunhap.getNgayNhap());
            pst.setDouble(3, phieunhap.getTongTien());
            pst.setInt(4, phieunhap.getMaNhaCungCap());
            pst.setInt(5, phieunhap.getMaNhanVien());
            pst.setString(6, phieunhap.getGhiChu());
            pst.setInt(7, 0);

            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daophieunhap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daophieunhap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public int maxID() {
        int count = 0;
        java.sql.Connection con = connect.connection();
        String sql = "SELECT MAX(maPhieuNhap) AS maxMaPhieuNhap FROM phieunhap;";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("maxMaPhieuNhap");
            }
        } catch (SQLException e) {
            Logger.getLogger(daophieunhap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daophieunhap.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return count;
    }
    public double getTongChiPhi() {
        double result=0;
        String query = """
                SELECT SUM(tongTien) AS tongChiPhi FROM phieunhap WHERE isDelete = 0;
                """;
        
        try (java.sql.Connection connection = connect.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
               result=resultSet.getInt("tongChiPhi");
            }
        } catch (Exception e) {
        }
        return result;
    }
    
}
