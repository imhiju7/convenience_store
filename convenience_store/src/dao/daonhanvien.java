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
    private connect conn = new connect();
    
    
    // add
    
    // update
    
    // delete
    
    // check
    
    // get
    public String getemail(int manv){
        Connection con = conn.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and maNhanVien = ?";
        String Email = "";
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, manv);
        

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Email = rs.getString("Email");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Email;
    }
}
