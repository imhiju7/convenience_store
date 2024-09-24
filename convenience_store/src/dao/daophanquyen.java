/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtophanquyen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hieu PC
 */
public class daophanquyen {
    
    // get
    
    // check
    
    public boolean checkphanquyen(int macv,int macn){
        Connection con = connect.connection();
        String sql = "SELECT * FROM phanquyen WHERE maChucVu = ? and maChucNang = ?";
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, macv);
            pst.setInt(2, macn);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
           
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daophanquyen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
