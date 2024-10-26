/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtochitietchamcong;
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

public class daochitietchamcong {

    public daochitietchamcong() {
        
    }

    // Method to retrieve all records from the chitietchamcong table
    public ArrayList<dtochitietchamcong> getlist() {
        ArrayList<dtochitietchamcong> list = new ArrayList<>();
        Connection con = connect.connection(); 
        String sql = "SELECT * FROM chitietchamcong "
                + "join chamcong on chamcong.maChamCong = chitietchamcong.maChamCong "
                + "join nhanvien on chamcong.maNhanVien = nhanvien.maNhanVien "
                + "where nhanvien.isDelete = 0";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtochitietchamcong detail = new dtochitietchamcong(
                    rs.getInt("maChiTietChamCong"),
                    rs.getDate("ngayChamCong"),
                    rs.getString("loaiChamCong"),
                    rs.getInt("soGioLam"),
                    rs.getInt("maChamCong"),
                    rs.getTimestamp("gioBatDau"),
                    rs.getTimestamp("gioKetThuc")
                );
                list.add(detail);
            }
        } catch (SQLException e) {
            Logger.getLogger(daochitietchamcong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochitietchamcong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }

    // Method to add a new chitietchamcong record
    public void create(dtochitietchamcong detail) {
        String sql = "INSERT INTO chitietchamcong (ngayChamCong, loaiChamCong, soGioLam, maChamCong, gioBatDau, gioKetThuc) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, new java.sql.Date(detail.getNgaychamcong().getTime()));
            pst.setString(2, detail.getLoaichamcong());
            pst.setInt(3, detail.getSogiolam());
            pst.setInt(4, detail.getMachamcong());
            pst.setTimestamp(5, detail.getGiobatdau());
            pst.setTimestamp(6, detail.getGioketthuc());

            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daochitietchamcong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochitietchamcong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    public static void main(String[] args) {
        // Create an instance of the DAO class
        daochitietchamcong dao = new daochitietchamcong();

        // Retrieve the list of details
        ArrayList<dtochitietchamcong> list = dao.getlist();

        // Print each dtochitietchamcong object in the list
        for (dtochitietchamcong detail : list) {
            System.out.println(detail);
        }
    }
}
