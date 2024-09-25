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
    public String gettennv(String email){
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
    
}
