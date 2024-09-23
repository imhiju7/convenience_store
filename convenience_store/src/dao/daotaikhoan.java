/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtotaikhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.connect;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author giavi
 */
public class daotaikhoan {
   
    public connect connect = new connect();
    
    public ArrayList<dtotaikhoan> getList() throws SQLException, ParseException {
        Connection con = connect.connection();
        String sql = "SELECT * FROM taikhoan";
        ArrayList<dtotaikhoan> dstk = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtotaikhoan tk = new dtotaikhoan();
                tk.setTenDangNhap(rs.getString("tenDangNhap"));
                tk.setMatKhau(rs.getString("matKhau"));
                tk.setNgayTao(rs.getTimestamp("ngayTao"));
                tk.setIsblock(rs.getInt("isBlock"));
                tk.setMaNhanVien(rs.getInt("maNhanVien"));
                dstk.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(dstk, new Comparator<dtotaikhoan>() {
            @Override
            public int compare(dtotaikhoan person1, dtotaikhoan person2) {
                return person2.getNgayTao().compareTo(person1.getNgayTao());
            }
        });
        return dstk;
    }
    
    public dtotaikhoan kiemTraTaiKhoan(String tenDangNhap, String matKhau) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and matKhau = ? and isBlock = 0 ";
        dtotaikhoan tk = new dtotaikhoan();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tenDangNhap);
            pst.setString(2, matKhau);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    tk.setTenDangNhap(rs.getString("tenDangNhap"));
                    tk.setMatKhau(rs.getString("matKhau"));
                    tk.setNgayTao(rs.getTimestamp("ngayTao"));
                    tk.setIsblock(rs.getInt("isBlock"));
                    tk.setMaNhanVien(rs.getInt("maNhanVien"));
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return tk;
    }
    
    
    public boolean checkTenDangNhap(String tenDangNhap) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, tenDangNhap);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        return false;
    }

    //Kiểm tra mật khẩu
    public boolean checkMatKhau(String tenDangNhap, String matKhau) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and matKhau = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, tenDangNhap);
            pst.setString(2, matKhau);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        return false;
    }


    public boolean checkKhoaTaiKhoan(String tenDangNhap) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and isBlock = 1 ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, tenDangNhap);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        return false;
    }
    
    public String getEmail(String tendn){
        String email="";
        String manv = "";
        Connection con = connect.connection();
        String sql1 = "select maNhanVien from taikhoan where maNhanVien = ? and isBlock = 1 ";
        try {
            PreparedStatement pst1 = con.prepareStatement(sql1);
            pst1.setString(1, tendn); 
            ResultSet rs = pst1.executeQuery();
            if (rs.next()) {
                manv = rs.getString("maNhanVien"); 
        }
        } catch (SQLException e) {
        }
        
        String sql2 = "select email from nhanvien where maNhanVien = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql2);
            pst.setString(1, sql2);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                email = rs.getString("email");
            }
        } catch (SQLException e) {
        }
        return email;
    }
    
    public static void main(String[] args) {
        daotaikhoan tk = new daotaikhoan();
        System.out.print(tk.checkMatKhau("admin", "admin"));
    }
}
