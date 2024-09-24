/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtochucnang;

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
public class daochucnang {
    
    // get
    
    public ArrayList<dtochucnang> getlistchucnangbydanhmuc(int madanhmuc){
        Connection con = connect.connection();
        String sql = "SELECT * FROM chucnang WHERE maDanhMuc = ?";
        ArrayList<dtochucnang> list = new ArrayList<>();
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1,madanhmuc);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new dtochucnang(rs.getInt("maChucNang"),rs.getString("tenChucNang"),rs.getInt("maDanhMuc")));
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
