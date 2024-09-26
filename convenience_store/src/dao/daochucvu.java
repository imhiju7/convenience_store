/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtochucvu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hieu PC
 */
public class daochucvu {
    
    
    // get 
    
    public String gettencvbymacv(int macv){
        Connection con = connect.connection();
        String sql = "SELECT * FROM chucvu where  maChucVu = ?";
        String tencv = "";
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, macv);
        
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                tencv = rs.getString("tenChucVu");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tencv;
    }
}
