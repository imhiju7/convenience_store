/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtonhanvien;
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
 * @author Hieu PC
 */
public class daonhanvien {
    
    
    
        public ArrayList<dtonhanvien> getlist() {
        ArrayList<dtonhanvien> list_nv = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = connect.connection(); // Establish connection
            String sql = "SELECT * FROM nhanvien where isDelete= 0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                dtonhanvien nv = new dtonhanvien();
                nv.setManhanvien(rs.getInt("maNhanVien"));
                nv.setTennhanvien(rs.getString("tenNhanVien"));
                nv.setNgaysinh(rs.getDate("ngaySinh"));
                nv.setEmail(rs.getString("Email"));
                nv.setSdt(rs.getString("soDienThoai"));
                nv.setDiachi(rs.getString("diaChi"));
                nv.setGioitinh(rs.getInt("gioiTinh"));
                nv.setMachucvu(rs.getInt("maChucVu"));
                nv.setIsdelete(rs.getInt("isDelete"));
                nv.setLuongcoban(rs.getFloat("luongCoBan"));
                nv.setImg(rs.getString("img"));
                list_nv.add(nv); // Add to the list
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print error details
        } finally {
            try {
                if (rs != null) rs.close(); // Close ResultSet
                if (pst != null) pst.close(); // Close PreparedStatement
                if (con != null) con.close(); // Close connection
            } catch (SQLException e) {
                e.printStackTrace(); // Handle closing exceptions
            }
        }
        return list_nv; // Return the list
    }


    
    // add
    
    // update
    
    // delete
    
    // check
    
    public boolean checkemailexist(String mail){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and email = ?";
        boolean flag = false;
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setString(1, mail);
        
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
              flag = true;
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    // get
    public int getmachucvu(int manv){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and maNhanVien = ?";
        int macv = 0;
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, manv);
        
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                macv = rs.getInt("maChucVu");
            }
        } catch (SQLException e) {
            
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return macv;
    }
    
    public String getemail(int manv){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and maNhanVien = ?";
        String email = "";
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, manv);
        

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                email = rs.getString("Email");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }
    public String gettennvbymanv(int manv){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and maNhanVien = ?";
        String tennv = "";
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, manv);
        

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                tennv = rs.getString("tenNhanVien");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tennv;
    }
    public String gettennvbyemail(String email){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and email = ?";
        String tennv = "";
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setString(1, email);
        

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                tennv = rs.getString("tenNhanVien");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tennv;
    }
    public static void main(String args[]) throws SQLException {
        daonhanvien bus = new daonhanvien();
        for(dtonhanvien nv:bus.getlist()){
            System.out.print(nv);

        }
    }
}
