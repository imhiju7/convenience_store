/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtotichdiem;
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
public class daotichdiem {
    public ArrayList<dtotichdiem> getlist(){
        Connection con = connect.connection();
        String sql = "SELECT * FROM tichdiem WHERE isDelete = 0";
        PreparedStatement pst;
        ArrayList<dtotichdiem> list = new ArrayList<>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                dtotichdiem td = new dtotichdiem();
                td.setMaTichDiem(rs.getInt("maTichDiem"));
                td.setTien(rs.getDouble("Tien"));
                td.setDiemTichLuy(rs.getInt("diemTichLuy"));
                list.add(td);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotichdiem.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(list, (dtotichdiem person1, dtotichdiem person2) -> Double.compare(person1.getTien(), person2.getTien()));
        return list;
    }
}
