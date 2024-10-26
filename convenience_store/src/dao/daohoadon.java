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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author giavi
 */
public class daohoadon {
    public daohoadon() {
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



    public static void main(String[] args) {
        // Create an instance of the DAO class
        daohoadon dao = new daohoadon();

        // Retrieve the list of details
        ArrayList<dtohoadon> list = dao.getlist();
        for (dtohoadon detail : list) {
            System.out.println(detail);
        }
    }
}
