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
    
    public ArrayList<dtoctphieunhap> needToFillList(int maNCC) {
        ArrayList<dtoctphieunhap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT ctpn.*" +
                        "FROM chitietphieunhap ctpn" +
                        "JOIN sanpham sp ON ctpn.maSanPham = sp.maSanPham" +
                        "JOIN nhacungcap ncc ON sp.maNhaCungCap = ncc.maNhaCungCap" +
                        "JOIN (" +
                        "    SELECT maSanPham, SUM(soLuongTonKho) AS totalSoLuongTonKho" +
                        "    FROM chitietphieunhap" +
                        "    GROUP BY maSanPham" +
                        ") AS totalStock ON ctpn.maSanPham = totalStock.maSanPham" +
                        "WHERE ncc.maNhaCungCap = ?" +
                        "  AND totalSoLuongTonKho < 10" +
                        "  AND sp.isHidden = 0;";
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
             "FROM chitietphieunhap ctpn " +
             "JOIN sanpham sp ON ctpn.maSanPham = sp.maSanPham " +
             "JOIN nhacungcap ncc ON sp.maNhaCungCap = ncc.maNhaCungCap " +
             "JOIN ( " +
             "    SELECT maSanPham, SUM(soLuongTonKho) AS totalSoLuongTonKho " +
             "    FROM chitietphieunhap " +
             "    GROUP BY maSanPham " +
             ") AS totalStock ON ctpn.maSanPham = totalStock.maSanPham " +
             "WHERE totalSoLuongTonKho < 10 " +
             "  AND sp.isHidden = 0;";
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
            System.out.println(dao.maxID());
    }
}
