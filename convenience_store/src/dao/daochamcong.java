/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtochamcong;
import java.sql.Connection;
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
    public ArrayList<dtochamcong> getAllChamCong() {
    ArrayList<dtochamcong> list = new ArrayList<>();
    java.sql.Connection con = connect.connection();
    String sql = "SELECT * FROM chamcong "
               + "JOIN nhanvien ON chamcong.maNhanVien = nhanvien.maNhanVien "
               + "WHERE nhanvien.isDelete = 0";

    try {
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            dtochamcong chamCong = new dtochamcong(
                rs.getInt("maChamCong"),
                rs.getInt("maNhanVien"),
                rs.getInt("soGioLamViec"),
                rs.getInt("soNgayLamViec"),
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
        String sql = "INSERT INTO chamcong (maNhanVien, soGioLamViec, soNgayLamViec, " +
                     "soGioLamThem, chiTiet, thangChamCong, namChamCong) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, chamCong.getManhanvien());
            pst.setInt(2, chamCong.getSogiolamviec());
            pst.setInt(3, chamCong.getSongaylamviec());
            pst.setInt(4, chamCong.getSogiolamthem());
            pst.setString(5, chamCong.getChitiet());
            pst.setInt(6, chamCong.getThangchamcong());
            pst.setInt(7, chamCong.getNamchamcong());

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
    public void update(dtochamcong cc){
        String sql = "update chamcong set maNhanVien = ?, soGioLamViec = ?, soNgayLamViec = ?, soGioLamThem = ?, chiTiet = ?, thangChamCong = ?, namChamCong = ? where maChamCong = ? ";
        Connection con = connect.connection();
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, cc.getManhanvien());
            pst.setInt(2, cc.getSogiolamviec());
            pst.setInt(3, cc.getSongaylamviec());
            pst.setInt(4, cc.getSogiolamthem());
            pst.setString(5, cc.getChitiet());
            pst.setInt(6, cc.getThangchamcong());
            pst.setInt(7, cc.getNamchamcong());
            pst.setInt(8, cc.getMachamcong());
            int rowsAffected = pst.executeUpdate(); // Thực hiện câu lệnh cập nhật
            if (rowsAffected > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không cập nhật được nhân viên với mã đã cho.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(daochamcong.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daochamcong.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Integer> getListYears() {
        ArrayList<Integer> years = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT DISTINCT namChamCong FROM chamcong ORDER BY namChamCong ASC";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                years.add(rs.getInt("namChamCong"));
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
        return years;
    }
    public ArrayList<dtochamcong> getlisttime(int Month,int Year) {
        ArrayList<dtochamcong> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM chamcong WHERE thangChamCong = ? AND namChamCong = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Month);
            pst.setInt(2, Year);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtochamcong chamCong = new dtochamcong(
                    rs.getInt("maChamCong"),
                    rs.getInt("maNhanVien"),
                    rs.getInt("soGioLamViec"),
                    rs.getInt("soNgayLamViec"),
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
    public static void main(String[] args) {
        // Create an instance of the DAO class
        daochamcong dao = new daochamcong();

        // Retrieve the list of details
        ArrayList<Integer> list = dao.getListYears();

        for (Integer detail : list) {
            System.out.println(detail);
        }
    }
}
