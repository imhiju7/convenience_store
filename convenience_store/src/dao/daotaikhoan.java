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
    
    public ArrayList<dtotaikhoan> getdstk() {
        Connection con = conn.connection();
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
            e.printStackTrace();
        }
        Collections.sort(dstk, new Comparator<dtotaikhoan>() {
            @Override
            public int compare(dtotaikhoan person1, dtotaikhoan person2) {
                return person2.getNgaytao().compareTo(person1.getNgaytao());
            }
        });
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dstk;
    }
    
    public dtotaikhoan checktaikhoan(String tendangnhap, String matkhau) {
        Connection con = conn.connection();
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
    
    public boolean checktendangnhap(String tendangnhap) {
        Connection con = conn.connection();
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

    //Kiểm tra mật khẩu
    public boolean checkmatkhau(String tendangnhap, String matkhau) {
        Connection con = conn.connection();
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
        Connection con = conn.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and isBlock = 1 ";
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
    public int getmanhanvien(String tendangnhap){
        Connection con = conn.connection();
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
