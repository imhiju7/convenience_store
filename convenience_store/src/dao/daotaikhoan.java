/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtotaikhoan;
import dao.connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giavi
 */
public class daotaikhoan {
   
    private connect conn = new connect();
    
    public boolean addtaikhoan(dtotaikhoan tk){
        Connection con = connect.connection();
        String sql = "INSERT INTO taikhoan(tenDangNhap,matKhau,ngayTao,isBlock,maNhanVien) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tk.getTendangnhap());
            pst.setString(2, tk.getMatkhau());
            pst.setTimestamp(3, new java.sql.Timestamp(tk.getNgaytao().getTime()));
            pst.setInt(4, tk.getIsblock());
            pst.setInt(5, tk.getManhanvien());
        } catch (SQLException e) {
            return false;
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean updatematkhau(String tendangnhap, String matkhau){
        Connection con = connect.connection();
        String sql = "UPDATE taikhoan set matKhau= ? WHERE tenDangNhap = ?";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, matkhau);
            pst.setString(2, tendangnhap);
            pst.executeUpdate();
        } catch (SQLException e) {
            return false;
        } 
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean updateblock(dtotaikhoan tk){
        Connection con = connect.connection();
        String sql = "UPDATE taikhoan set isBlock = ? WHERE tenDangNhap = ?";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, tk.getIsblock());
            pst.setString(2, tk.getTendangnhap());
            pst.executeUpdate();
        } catch (SQLException e) {
            return false;
        } 
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean deletetaikhoan(dtotaikhoan tk){
        Connection con = connect.connection();
        String sql = "DELETE FROM taikhoan  WHERE tenDangNhap= ?";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,tk.getTendangnhap());
            pst.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    // check
    
    public boolean checktendangnhap(String tendangnhap) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, tendangnhap);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkmatkhau(String tendangnhap, String matkhau) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and matKhau = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, tendangnhap);
            pst.setString(2, matkhau);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    public boolean checktaikhoanbikhoa(String tendangnhap) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and isBlock = 0 ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, tendangnhap);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // get
    
    public dtotaikhoan gettaikhoan(String tendangnhap, String matkhau) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and matKhau = ? and isBlock = 0 ";
        dtotaikhoan tk = new dtotaikhoan();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tendangnhap);
            pst.setString(2, matkhau);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    tk.setTendangnhap(rs.getString("tenDangNhap"));
                    tk.setMatkhau(rs.getString("matKhau"));
                    tk.setNgaytao(rs.getTimestamp("ngayTao"));
                    tk.setIsblock(rs.getInt("isBlock"));
                    tk.setManhanvien(rs.getInt("maNhanVien"));
                }
            }
        } catch (SQLException e) {
            return null;
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tk;
    }
    
    public ArrayList<dtotaikhoan> getdstk() {
        Connection con = connect.connection();
        String sql = "SELECT * FROM taikhoan";
        ArrayList<dtotaikhoan> dstk = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtotaikhoan tk = new dtotaikhoan();
                tk.setTendangnhap(rs.getString("tenDangNhap"));
                tk.setMatkhau(rs.getString("matKhau"));
                tk.setNgaytao(rs.getTimestamp("ngayTao"));
                tk.setIsblock(rs.getInt("isBlock"));
                tk.setManhanvien(rs.getInt("maNhanVien"));
                dstk.add(tk);
            }
        } catch (SQLException e) {
        }
        Collections.sort(dstk, (dtotaikhoan person1, dtotaikhoan person2) -> person2.getNgaytao().compareTo(person1.getNgaytao()));
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dstk;
    }
    
    public int getmanhanvien(String tendangnhap){
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and isBlock = 0";
        int manv = 0;
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tendangnhap);
        
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                manv = rs.getInt("maNhanVien");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manv;
    }
}
