/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtouudai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class daouudai {
    public dtouudai getud(dtouudai i){
        Connection con = connect.connection();
        String sql = "SELECT * FROM uudai WHERE maUuDai = ?";
        PreparedStatement pst;
        dtouudai ud = new dtouudai();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, i.getMaUuDai());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ud.setMaUuDai(rs.getInt("maUuDai"));
                ud.setMocUuDai(rs.getInt("mocUuDai"));
                ud.setTiLeGiam(rs.getInt("tiLeGiam"));
                ud.setIsHidden(rs.getInt("isDelete"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daouudai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ud;
    }
    public ArrayList<dtouudai> getlist(){
        Connection con = connect.connection();
        String sql = "SELECT * FROM uudai WHERE isDelete = 0";
        PreparedStatement pst;
        ArrayList<dtouudai> list = new ArrayList<>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                dtouudai ud = new dtouudai();
                ud.setMaUuDai(rs.getInt("maUuDai"));
                ud.setMocUuDai(rs.getInt("mocUuDai"));
                ud.setTiLeGiam(rs.getInt("tiLeGiam"));
                ud.setIsHidden(rs.getInt("isDelete"));
                list.add(ud);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daouudai.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(list, (dtouudai person1, dtouudai person2) -> Integer.compare(person1.getMocUuDai(), person2.getMocUuDai()));
        return list;
    }
}
