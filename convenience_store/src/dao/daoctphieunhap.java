/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtoctphieunhap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author giavi
 */
public class daoctphieunhap {
    public daoctphieunhap() {
    }
    
    public ArrayList<dtoctphieunhap> getlist() {
        ArrayList<dtoctphieunhap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM chitietphieunhap ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtoctphieunhap ctphieunhap = new dtoctphieunhap(
                    rs.getInt("maCTPhieuNhap"),
                    rs.getInt("soLuong"),
                    rs.getDouble("giaNhap"),
                    rs.getInt("maPhieuNhap"),
                    rs.getInt("maSanPham"),
                    rs.getDate("ngayHetHan"),
                    rs.getInt("soLuongTonKho"),
                    rs.getString("ghiChu"),
                    rs.getDouble("giaBan")
                );
                list.add(ctphieunhap);
            }
        } catch (SQLException e) {
            Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }
    public ArrayList<dtoctphieunhap> getlist(int id) {
        ArrayList<dtoctphieunhap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM chitietphieunhap "
                + "where chitietphieunhap.maPhieuNhap = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtoctphieunhap ctphieunhap = new dtoctphieunhap(
                    id,
                    rs.getInt("soLuong"),
                    rs.getInt("giaNhap"),
                    rs.getInt("maPhieuNhap"),
                    rs.getInt("maSanPham"),
                    rs.getDate("ngayHetHan"),
                    rs.getInt("soLuongTonKho"),
                    rs.getString("ghiChu"),
                    rs.getDouble("giaBan")
                );
                list.add(ctphieunhap);
            }
        } catch (SQLException e) {
            Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }
    
    public void create(dtoctphieunhap ctphieunhap) {
        String sql = "INSERT INTO ctphieunhap (maCTPhieuNhap, soLuong, giaNhap, maPhieuNhap, maSanPham, ngayHetHan, ishidden, ghiChu, soLuongTonKho, giaBan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, ctphieunhap.getMaPhieuNhap());
            pst.setInt(2, ctphieunhap.getSoLuong());
            pst.setDouble(3, ctphieunhap.getGiaNhap());
            pst.setInt(4, ctphieunhap.getMaPhieuNhap());
            pst.setInt(5, ctphieunhap.getMaSanPham());
            pst.setDate(6, (java.sql.Date) ctphieunhap.getNgayhethan());
            pst.setInt(7, 0);
            pst.setString(8, ctphieunhap.getGhichu());
            pst.setInt(9, ctphieunhap.getSoluongtonkho());
            pst.setDouble(10, ctphieunhap.getGiaBan());

            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    public int maxID() {
        int count = 0;
        java.sql.Connection con = connect.connection();
        String sql = "SELECT MAX(maCTPhieuNhap) AS maxMaPhieuNhap FROM chitietphieunhap;";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("maxMaPhieuNhap");
            }
        } catch (SQLException e) {
            Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return count;
    }
        public static void main(String[] args) {
        daoctphieunhap dao = new daoctphieunhap();
        for (dtoctphieunhap pn:dao.getlist()){
            System.out.println(pn);
        }
    }
}
