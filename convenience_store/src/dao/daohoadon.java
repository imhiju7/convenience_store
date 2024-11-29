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
        
        try (java.sql.Connection connection = connect.connection();
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
                    rs.getTimestamp("ngayMua"),
                    rs.getString("ghiChu"),
                    rs.getInt("maTichDiem"),
                    rs.getInt("isDelete")
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
        String sql = "INSERT INTO hoadon ( maNhanVien, tongTien, maKhachHang, maKhuyenMai, ngayMua, " +
                     "ghiChu, maTichDiem, isDelete) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, hoadon.getMaNhanVien());
            pst.setDouble(2, hoadon.getTongTien());
            pst.setInt(3, hoadon.getMaKhachHang());
            pst.setInt(4, hoadon.getMaKhuyenMai());
            pst.setTimestamp(5,hoadon.getNgayMua());
            pst.setString(6, hoadon.getGhiChu());
            pst.setInt(7, hoadon.getMaTichDiem());
            pst.setInt(8, 0);

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
        public int addhdnokhkm(dtohoadon hd){
        Connection con = connect.connection();
        String sql = "INSERT INTO hoadon(maNhanVien,ngayMua,tongTien,ghiChu,isDelete) VALUES(?,?,?,?,?)";
        PreparedStatement pst;
        int rowaffect = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, hd.getMaNhanVien());
            pst.setTimestamp(2, new java.sql.Timestamp(hd.getNgayMua().getTime()));
            pst.setDouble(3, hd.getTongTien());
            pst.setString(4, hd.getGhiChu());
            pst.setInt(5, hd.getIsHidden());
            rowaffect = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowaffect;
    }
    public int addhdnokh(dtohoadon hd){
        Connection con = connect.connection();
        String sql = "INSERT INTO hoadon(maNhanVien,maKhuyenMai,ngayMua,tongTien,ghiChu,isDelete) VALUES(?,?,?,?,?,?)";
        PreparedStatement pst;
        int rowaffect = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, hd.getMaNhanVien());
            pst.setInt(2, hd.getMaKhuyenMai());
            pst.setTimestamp(3, new java.sql.Timestamp(hd.getNgayMua().getTime()));
            pst.setDouble(4, hd.getTongTien());
            pst.setString(5, hd.getGhiChu());
            pst.setInt(6, hd.getIsHidden());
            rowaffect = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowaffect;
    }
    public int addhdnokm(dtohoadon hd){
        Connection con = connect.connection();
        String sql = "INSERT INTO hoadon(maKhachHang,maNhanVien,maTichDiem,ngayMua,tongTien,ghiChu,isDelete) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pst;
        int rowaffect = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, hd.getMaKhachHang());
            pst.setInt(2, hd.getMaNhanVien());
            pst.setInt(3, hd.getMaTichDiem());
            pst.setTimestamp(4, new java.sql.Timestamp(hd.getNgayMua().getTime()));
            pst.setDouble(5, hd.getTongTien());
            pst.setString(6, hd.getGhiChu());
            pst.setInt(7, hd.getIsHidden());
            rowaffect = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daohoadon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowaffect;
    }
}
