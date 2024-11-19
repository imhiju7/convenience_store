/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtoctphieunhap;
import java.sql.Connection;
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
        public int updatectphieunhap(dtoctphieunhap ctpn){
        Connection con = connect.connection();
        String sql = "UPDATE chitietphieunhap set maPhieuNhap= ?,maSanPham = ? ,soLuong= ?, giaNhap= ?, ngayHetHan = ?, ishidden=?,ghiChu = ?,soLuongTonKho = ?,giaBan = ? WHERE maCTPhieuNhap= ?";
        PreparedStatement pst;
        int rowaffect = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, ctpn.getMaPhieuNhap());
            pst.setInt(2, ctpn.getMaSanPham());
            pst.setInt(3, ctpn.getSoLuong());
            pst.setDouble(4, ctpn.getGiaNhap());
            pst.setTimestamp(5, new java.sql.Timestamp (ctpn.getNgayhethan().getTime()));
            pst.setInt(6,ctpn.getIshidden());
            pst.setString(7, ctpn.getGhichu());
            pst.setInt(8, ctpn.getSoluongtonkho());
            pst.setDouble(9,ctpn.getGiaBan());
            pst.setInt(10,ctpn.getMaCTPhieuNhap());
            rowaffect = pst.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowaffect;
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
    public ArrayList<dtoctphieunhap> getalllist(){
        Connection con = connect.connection();
        String sql = "SELECT * FROM chitietphieunhap WHERE ngayHetHan < NOW() ";
        PreparedStatement pst;
        ArrayList<dtoctphieunhap> list = new ArrayList<>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                dtoctphieunhap ctpn = new dtoctphieunhap();
                ctpn.setMaCTPhieuNhap(rs.getInt("maCTPhieuNhap"));
                ctpn.setMaSanPham(rs.getInt("maSanPham"));
                ctpn.setMaPhieuNhap(rs.getInt("maPhieuNhap"));
                ctpn.setGiaNhap(rs.getInt("giaNhap"));
                ctpn.setGiaBan(rs.getInt("giaBan"));
                ctpn.setSoLuong(rs.getInt("soLuong"));
                ctpn.setGhichu(rs.getString("ghiChu"));
                ctpn.setNgayhethan(rs.getDate("ngayHetHan"));
                ctpn.setSoluongtonkho(rs.getInt("soLuongTonKho"));
                list.add(ctpn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
