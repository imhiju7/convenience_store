/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtodanhmuc;

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
public class daodanhmuc {
    
    
    // get
   
    public ArrayList<dtodanhmuc> getlist(){
        Connection con = connect.connection();
        String sql = "SELECT * FROM danhmuc";
        ArrayList<dtodanhmuc> list = new ArrayList<>();
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new dtodanhmuc(rs.getInt("maDanhMuc"),rs.getString("tenDanhMuc"),rs.getString("icon")));
            }
        } catch (SQLException e) {
           
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daophanquyen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
