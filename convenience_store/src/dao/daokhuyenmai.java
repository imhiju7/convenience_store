/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtokhuyenmai;
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
public class daokhuyenmai {
    public dtokhuyenmai getkmbyname(String name){
        Connection con = connect.connection();
        String sql = "SELECT * FROM khuyenmai WHERE tenKhuyenMai = ? and ngayHetHan > NOW()";
        PreparedStatement pst;
        dtokhuyenmai km = new dtokhuyenmai();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                km.setMaKhuyenMai(rs.getInt("maKhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayHetHan(rs.getDate("ngayHetHan"));
                km.setPhanTram(rs.getInt("phanTram"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setSoLuongDaDung(rs.getInt("soLuongDaDung"));
                km.setIshidden(rs.getInt("ishidden"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daokhuyenmai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return km;
    }
    public ArrayList<dtokhuyenmai> getlist(){
        Connection con = connect.connection();
        String sql = "SELECT * FROM khuyenmai WHERE ishidden = 0 and soLuong > soLuongDaDung";
        PreparedStatement pst;
        ArrayList<dtokhuyenmai> list = new ArrayList<>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                dtokhuyenmai km = new dtokhuyenmai();
                km.setMaKhuyenMai(rs.getInt("maKhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayHetHan(rs.getDate("ngayHetHan"));
                km.setPhanTram(rs.getInt("phanTram"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setSoLuongDaDung(rs.getInt("soLuongDaDung"));
                km.setIshidden(rs.getInt("ishidden"));
                list.add(km);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daokhuyenmai.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daokhuyenmai.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(list, (dtokhuyenmai person1, dtokhuyenmai person2) -> person2.getNgayBatDau().compareTo(person1.getNgayBatDau()));
        return list;
    }
    public int updateKhuyenMai(dtokhuyenmai km){
        Connection con = connect.connection();
        String sql = "UPDATE khuyenmai set tenKhuyenMai = ?,ngayBatDau = ?,ngayHetHan = ?,phanTram = ?,soLuong=?,ishidden = ?,soLuongDaDung = ? WHERE maKhuyenMai= ?";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, km.getTenKhuyenMai());
            pst.setDate(2, new java.sql.Date(km.getNgayBatDau().getTime()));
            pst.setDate(3,  new java.sql.Date(km.getNgayHetHan().getTime()));
            pst.setInt(4, km.getPhanTram());
            pst.setInt(5, km.getSoLuong());
            pst.setInt(6, km.getIshidden());
            pst.setInt(7, km.getSoLuongDaDung());
            pst.setInt(8, km.getMaKhuyenMai());
            int rowaffect = pst.executeUpdate();
            return rowaffect;
        } catch (SQLException ex) {
            Logger.getLogger(daokhuyenmai.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daokhuyenmai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
