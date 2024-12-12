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
    public ArrayList<dtoctphieunhap> getlistlo(int masp) {
        ArrayList<dtoctphieunhap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM chitietphieunhap WHERE maSanPham = ? AND ngayHetHan > NOW()";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, masp);
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
    public ArrayList<dtoctphieunhap> getlistpn(int mapn) {
        ArrayList<dtoctphieunhap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM chitietphieunhap WHERE maPhieuNhap = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, mapn);
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
    public ArrayList<dtoctphieunhap> needToFillList(int maNCC) {
        ArrayList<dtoctphieunhap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT ctpn.* " +
                    "FROM chitietphieunhap AS ctpn " +
                    "JOIN sanpham AS sp ON ctpn.maSanPham = sp.maSanPham " +
                    "WHERE sp.maNhaCungCap = ?" +
                    " AND sp.isHidden = 0" +
                    " AND ctpn.isHidden = 0" +
                    " AND (" +
                    " SELECT SUM(soLuongTonKho)" +
                    " FROM chitietphieunhap" +
                    " WHERE maSanPham = ctpn.maSanPham" +
                    " ) < 10;";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maNCC);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtoctphieunhap phieunhap = new dtoctphieunhap(
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
    
    public ArrayList<dtoctphieunhap> needToFillList() {
        ArrayList<dtoctphieunhap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT ctpn.* " +
                    "FROM chitietphieunhap AS ctpn " +
                    "JOIN sanpham AS sp ON ctpn.maSanPham = sp.maSanPham " +
                    "WHERE sp.isHidden = 0" +
                    " AND ctpn.isHidden = 0" +
                    " AND (" +
                    " SELECT SUM(soLuongTonKho)" +
                    " FROM chitietphieunhap" +
                    " WHERE maSanPham = ctpn.maSanPham" +
                    " ) < 10;";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtoctphieunhap phieunhap = new dtoctphieunhap(
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
    public ArrayList<dtoctphieunhap> getlist(int phieunhapid) {
        ArrayList<dtoctphieunhap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM chitietphieunhap "
                + "where chitietphieunhap.maPhieuNhap = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, phieunhapid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtoctphieunhap ctphieunhap = new dtoctphieunhap(
                    phieunhapid,
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
        String sql = "INSERT INTO chitietphieunhap ( soLuong, giaNhap, maPhieuNhap, maSanPham, ngayHetHan, ishidden, ghiChu, soLuongTonKho, giaBan) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, ctphieunhap.getSoLuong());
            pst.setDouble(2, ctphieunhap.getGiaNhap());
            pst.setInt(3, ctphieunhap.getMaPhieuNhap());
            pst.setInt(4, ctphieunhap.getMaSanPham());
            pst.setDate(5, (java.sql.Date) ctphieunhap.getNgayhethan());
            pst.setInt(6, 0);
            pst.setString(7, ctphieunhap.getGhichu());
            pst.setInt(8, ctphieunhap.getSoluongtonkho());
            pst.setDouble(9, ctphieunhap.getGiaBan());

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
    public static void updateEXP() {
        Connection con = connect.connection();
        String sql = "UPDATE chitietphieunhap SET ishidden = 1 WHERE ngayHetHan < CURRENT_DATE;" ;
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daoctphieunhap.class.getName()).log(Level.SEVERE, null, ex);
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
            pst.setDate(5, (java.sql.Date) ctpn.getNgayhethan());
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
        String sql = "SELECT * FROM chitietphieunhap WHERE ngayHetHan > NOW() ";
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
