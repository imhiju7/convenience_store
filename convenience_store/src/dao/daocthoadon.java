/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtocthoadon;
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

public class daocthoadon {

    public daocthoadon() {
        
    }

    // Method to retrieve all records from the chitietchamcong table
    public ArrayList<dtocthoadon> getlist() {
        ArrayList<dtocthoadon> list = new ArrayList<>();
        Connection con = connect.connection(); 
        String sql = "SELECT * FROM chitiethoadon";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtocthoadon detail = new dtocthoadon(
                    rs.getInt("maSanPham"),
                    rs.getInt("maHoaDon"),
                    rs.getInt("soLuong"),
                    rs.getDouble("donGia"),
                    rs.getInt("maCTHoaDon")
                );
                list.add(detail);
            }
        } catch (SQLException e) {
            Logger.getLogger(daocthoadon.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daocthoadon.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }
    public ArrayList<dtocthoadon> getlistbyhoadon(int mahd) {
        ArrayList<dtocthoadon> list = new ArrayList<>();
        Connection con = connect.connection(); 
        String sql = "SELECT * FROM chitiethoadon WHERE maHoaDon = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, mahd);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtocthoadon detail = new dtocthoadon(
                    rs.getInt("maSanPham"),
                    rs.getInt("maHoaDon"),
                    rs.getInt("soLuong"),
                    rs.getDouble("donGia"),
                    rs.getInt("maCTHoaDon")
                );
                list.add(detail);
            }
        } catch (SQLException e) {
            Logger.getLogger(daocthoadon.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daocthoadon.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }

    // Method to add a new chitietchamcong record
    public void add(dtocthoadon detail) {
        String sql = "INSERT INTO chitiethoadon(maSanPham, soLuong, donGia, maHoaDon)"
                   + "VALUES (?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, detail.getMaSanPham());
            pst.setInt(2, detail.getSoLuong());
            pst.setDouble(3, detail.getDonGia());
            pst.setInt(4, detail.getMaHoaDon());

            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daocthoadon.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daocthoadon.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    public static void main(String[] args) {
        // Create an instance of the DAO class
        daocthoadon dao = new daocthoadon();

        // Retrieve the list of details
        ArrayList<dtocthoadon> list = dao.getlist();

        // Print each dtocthoadon object in the list
        for (dtocthoadon detail : list) {
            System.out.println(detail);
        }
    }
}
