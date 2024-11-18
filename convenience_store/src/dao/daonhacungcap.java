/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtonhacungcap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author giavi
 */
public class daonhacungcap {

    public daonhacungcap() {
    }
    
    public dtonhacungcap getById(int maNhaCungCap) {
        dtonhacungcap nhaCungCap = null;
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM nhacungcap WHERE maNhaCungCap = ? AND trangThai = 0";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maNhaCungCap);  // Set the supplier ID parameter
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nhaCungCap = new dtonhacungcap(
                    rs.getInt("maNhaCungCap"),
                    rs.getString("tenNhaCungCap"),
                    rs.getString("SDT"),
                    rs.getString("email"),
                    rs.getString("diaChi"),
                    rs.getInt("trangThai"),
                    rs.getTimestamp("ngayTao")
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return nhaCungCap;
    }
    
    public dtonhacungcap getByName(String tenNhaCungCap) {
        dtonhacungcap nhaCungCap = null;
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM nhacungcap WHERE tenNhaCungCap = ? AND trangThai = 0";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tenNhaCungCap);  // Set the supplier ID parameter
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nhaCungCap = new dtonhacungcap(
                    rs.getInt("maNhaCungCap"),
                    rs.getString("tenNhaCungCap"),
                    rs.getString("SDT"),
                    rs.getString("email"),
                    rs.getString("diaChi"),
                    rs.getInt("trangThai"),
                    rs.getTimestamp("ngayTao")
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return nhaCungCap;
    }
    
    public ArrayList<dtonhacungcap> getlist() {
        ArrayList<dtonhacungcap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM nhacungcap WHERE trangThai = 0";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                dtonhacungcap nhaCungCap = new dtonhacungcap(
                    rs.getInt("maNhaCungCap"),
                    rs.getString("tenNhaCungCap"),
                    rs.getString("SDT"),
                    rs.getString("email"),
                    rs.getString("diaChi"),
                    rs.getInt("trangThai"),
                    rs.getTimestamp("ngayTao")
                );
                list.add(nhaCungCap);
            }
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }

}
