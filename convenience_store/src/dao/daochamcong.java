/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtochamcong;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHUONG ANH
 */
public class daochamcong {

    public daochamcong() {
    }
    
    public ArrayList<dtochamcong> getlist() {
        ArrayList<dtochamcong> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM chamcong "
                + "join nhanvien on chamcong.maNhanVien = nhanvien.maNhanVien "
                + "where nhanvien.isDelete = 0";
        
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtochamcong chamCong = new dtochamcong(
                    rs.getInt("maChamCong"),
                    rs.getInt("maNhanVien"),
                    rs.getInt("soGioLamViec"),
                    rs.getInt("soNgayLamViec"),
                    rs.getInt("soNgayNghi"),
                    rs.getInt("soNgayTre"),
                    rs.getInt("soGioLamThem"),
                    rs.getString("chiTiet"),
                    rs.getInt("thangChamCong"),
                    rs.getInt("namChamCong")
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
    
    public void create(dtochamcong chamCong) {
        String sql = "INSERT INTO chamcong (maNhanVien, soGioLamViec, soNgayLamViec, soNgayNghi, soNgayTre, " +
                     "soGioLamThem, chiTiet, thangChamCong, namChamCong) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, chamCong.getManhanvien());
            pst.setInt(2, chamCong.getSogiolamviec());
            pst.setInt(3, chamCong.getSongaylamviec());
            pst.setInt(4, chamCong.getSongaynghi());
            pst.setInt(5, chamCong.getSongaytre());
            pst.setInt(6, chamCong.getSogiolamthem());
            pst.setString(7, chamCong.getChitiet());
            pst.setInt(8, chamCong.getThangchamcong());
            pst.setInt(9, chamCong.getNamchamcong());

            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daochamcong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochamcong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    public int countchamcong() {
        int count = 0;
        java.sql.Connection con = connect.connection();
        String sql = "SELECT COUNT(*) FROM chamcong ";
    
    try {
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1); // Get the count from the first column
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
    return count;
}

    public static void main(String[] args) {
        // Create an instance of the DAO class
        daochamcong dao = new daochamcong();

        // Retrieve the list of details
        ArrayList<dtochamcong> list = dao.getlist();

        for (dtochamcong detail : list) {
            System.out.println(detail);
        }
    }
}
