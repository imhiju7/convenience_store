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
import java.util.Date;
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
    public boolean updatectchamcong(dtochitietchamcong ctcc){
        Connection con = connect.connection();
        String query = "UPDATE chitietchamcong SET ngayChamCong = ?, loaiChamCong = ?, soGioLam = ?, maChamCong = ?,gioBatDau = ?, gioKetThuc =? WHERE maChiTietChamCong = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setDate(1, new java.sql.Date(ctcc.getNgaychamcong().getTime()));
            pst.setString(2, ctcc.getLoaichamcong());
            pst.setInt(3, ctcc.getSogiolam());
            pst.setInt(4, ctcc.getMachamcong());
            pst.setTimestamp(5, ctcc.getGiobatdau());
            pst.setTimestamp(6, ctcc.getGioketthuc());
            pst.setInt(7, ctcc.getMactchamcong());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(daochitietchamcong.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochitietchamcong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return false;
    }
    
    public dtochitietchamcong getctchamcong(Date day,int maChamCong){
        String sql = "SELECT * FROM chitietchamcong WHERE ngayChamCong = ? AND maChamCong = ? AND gioKetThuc IS NULL";
        dtochitietchamcong ctcc = new dtochitietchamcong();
        Connection con = connect.connection(); 
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1,  new java.sql.Date(day.getTime()));
            ps.setInt(2, maChamCong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ctcc.setMactchamcong(rs.getInt("maChiTietChamCong"));
                ctcc.setMachamcong(rs.getInt("maChamCong"));
                ctcc.setNgaychamcong(rs.getDate("ngayChamCong"));
                ctcc.setGiobatdau(rs.getTimestamp("gioBatDau"));
                ctcc.setLoaichamcong(rs.getString("loaiChamCong"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(daochitietchamcong.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ctcc;
    }
    public ArrayList<dtochitietchamcong> getctchamcongdathuchien(Date day,int maChamCong){
        String sql = "SELECT * FROM chitietchamcong WHERE ngayChamCong = ? AND maChamCong = ? AND gioKetThuc IS NOT NULL";
        ArrayList<dtochitietchamcong> listctcc = new ArrayList<>();
        Connection con = connect.connection(); 
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1,  new java.sql.Date(day.getTime()));
            ps.setInt(2, maChamCong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dtochitietchamcong ctcc = new dtochitietchamcong();
                ctcc.setMactchamcong(rs.getInt("maChiTietChamCong"));
                ctcc.setMachamcong(rs.getInt("maChamCong"));
                ctcc.setNgaychamcong(rs.getDate("ngayChamCong"));
                ctcc.setGiobatdau(rs.getTimestamp("gioBatDau"));
                ctcc.setGioketthuc(rs.getTimestamp("gioKetThuc"));
                ctcc.setLoaichamcong(rs.getString("loaiChamCong"));
                listctcc.add(ctcc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daochitietchamcong.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listctcc;
    }
    public dtochitietchamcong getctccab(Date day,java.sql.Timestamp gioBatDau,java.sql.Timestamp gioKetThuc){
        String sql = "SELECT * FROM chitietchamcong WHERE ngayChamCong = ? AND gioBatDau = ? AND gioKetThuc = ?";
        dtochitietchamcong ctcc = new dtochitietchamcong();
        Connection con = connect.connection(); 
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1,  new java.sql.Date(day.getTime()));
            ps.setTimestamp(2, gioBatDau);
            ps.setTimestamp(3, gioKetThuc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ctcc.setMactchamcong(rs.getInt("maChiTietChamCong"));
                ctcc.setMachamcong(rs.getInt("maChamCong"));
                ctcc.setNgaychamcong(rs.getDate("ngayChamCong"));
                ctcc.setGiobatdau(rs.getTimestamp("gioBatDau"));
                ctcc.setGioketthuc(rs.getTimestamp("gioKetThuc"));
                ctcc.setLoaichamcong(rs.getString("loaiChamCong"));
                ctcc.setSogiolam(rs.getInt("soGioLam"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(daochitietchamcong.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ctcc;
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
