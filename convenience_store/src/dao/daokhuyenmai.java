/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtokhuyenmai;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author giavi
 */
public class daokhuyenmai {
    public daokhuyenmai() {
    }

    public int getMaxMaKhuyenMai() {
        int maxMaKhuyenMai = 0;
        try (java.sql.Connection con = connect.connection()) {
            String sql = "SELECT MAX(maKhuyenMai) AS max_makhuyenmai FROM khuyenmai";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                maxMaKhuyenMai = rs.getInt("max_makhuyenmai");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxMaKhuyenMai;
    }

    public boolean addKhuyenMai(dtokhuyenmai km) {
        boolean isSuccess = false;
        try (java.sql.Connection con = connect.connection()) {
            String sql = "INSERT INTO khuyenmai (maKhuyenMai,tenKhuyenMai, ngayBatDau, ngayHetHan,soLuong,phanTram,ishidden,soLuongDaDung) VALUES (?, ?, ?, ?, ?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            java.sql.Date sqlNgayBatDau = new java.sql.Date(km.getNgayBatDau().getTime());
            java.sql.Date sqlNgayHetHan = new java.sql.Date(km.getNgayHetHan().getTime());
            pst.setInt(1, km.getMaKhuyenMai());
            pst.setString(2, km.getTenKhuyenMai());
            pst.setDate(3, sqlNgayBatDau);
            pst.setDate(4, sqlNgayHetHan); 
            pst.setInt(5, km.getSoLuong());
            pst.setDouble(6, km.getPhanTram());
            pst.setInt(7, 0);
            pst.setInt(8, 0);
            // Thực thi câu lệnh
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true; // Nếu có dòng bị ảnh hưởng, tức là thêm thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess; // Trả về true nếu thêm thành công, ngược lại trả về false
    }

    public Boolean Deleted(int maKhuyenMai) {
        boolean isSuccess = false;
        String sql = "UPDATE khuyenmai SET ishidden = 1 WHERE maKhuyenMai = ?";

        try (java.sql.Connection con = connect.connection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, maKhuyenMai);
            int rowsAffected = pst.executeUpdate();

            // Kiểm tra nếu có ít nhất 1 hàng bị cập nhật
            isSuccess = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public Boolean Update(dtokhuyenmai km) {
        boolean isSuccess = false;
        String sql = "UPDATE khuyenmai SET tenKhuyenMai=?, ngayBatDau=?, ngayHetHan=?, SoLuong=?, phanTram=?, soLuongDaDung=? WHERE maKhuyenMai = ?";

        try (java.sql.Connection con = connect.connection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, km.getTenKhuyenMai());
            pst.setDate(2, new java.sql.Date(km.getNgayBatDau().getTime()));
            pst.setDate(3,  new java.sql.Date(km.getNgayHetHan().getTime()));
            pst.setInt(4, km.getSoLuong());
            pst.setDouble(5, km.getPhanTram());
            pst.setInt(6, km.getSoLuongDaDung());
            pst.setInt(7, km.getMaKhuyenMai());
            
            int rowsAffected = pst.executeUpdate();

            // Kiểm tra nếu có ít nhất 1 hàng bị cập nhật 
            isSuccess = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
    
    public ArrayList<dtokhuyenmai> getListByCondition(String columnName, String conditonValue) {
        ArrayList<dtokhuyenmai> list = new ArrayList<>();
        try (java.sql.Connection con = connect.connection()) {
            String sql = "select * from khuyenmai where ishidden=0 and " + columnName + "=" +conditonValue;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtokhuyenmai km = new dtokhuyenmai();
                km.setMaKhuyenMai(rs.getInt("maKhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayHetHan(rs.getDate("ngayHetHan"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setPhanTram(rs.getDouble("phanTram"));
                km.setIshidden(rs.getInt("ishidden"));
                km.setSoLuongDaDung(rs.getInt("soLuongDaDung"));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<dtokhuyenmai> getListByString(String columnName, String conditonValue) {
        ArrayList<dtokhuyenmai> list = new ArrayList<>();
        try (java.sql.Connection con = connect.connection()) {
            String sql = "select * from khuyenmai where ishidden=0 and " + columnName + "=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, conditonValue);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtokhuyenmai km = new dtokhuyenmai();
                km.setMaKhuyenMai(rs.getInt("maKhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayHetHan(rs.getDate("ngayHetHan"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setPhanTram(rs.getDouble("phanTram"));
                km.setIshidden(rs.getInt("ishidden"));
                km.setSoLuongDaDung(rs.getInt("soLuongDaDung"));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<dtokhuyenmai> getListByInt(String columnName, int conditonValue) {
        ArrayList<dtokhuyenmai> list = new ArrayList<>();
        try (java.sql.Connection con = connect.connection()) {
            String sql = "select * from khuyenmai where ishidden=0 and " + columnName + "=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, conditonValue);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtokhuyenmai km = new dtokhuyenmai();
                km.setMaKhuyenMai(rs.getInt("maKhuyenMai"));
                km.setTenKhuyenMai(rs.getString("tenKhuyenMai"));
                km.setNgayBatDau(rs.getDate("ngayBatDau"));
                km.setNgayHetHan(rs.getDate("ngayHetHan"));
                km.setSoLuong(rs.getInt("soLuong"));
                km.setPhanTram(rs.getDouble("phanTram"));
                km.setIshidden(rs.getInt("ishidden"));
                km.setSoLuongDaDung(rs.getInt("soLuongDaDung"));
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<dtokhuyenmai> getListByDate(String date){
        
        ArrayList<dtokhuyenmai> list = new ArrayList<>();
        ArrayList<dtokhuyenmai> list2 = new daokhuyenmai().getlist();
        for(dtokhuyenmai km : list2){
            if(km.getNgayBatDau().equals(java.sql.Date.valueOf(date)) || km.getNgayHetHan().equals(java.sql.Date.valueOf(date))){
                list.add(km);
            }
        }
        return list;
    }
    
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
    public dtokhuyenmai getkmbyid(int id){
        Connection con = connect.connection();
        String sql = "SELECT * FROM khuyenmai WHERE maKhuyenMai = ? ";
        PreparedStatement pst;
        dtokhuyenmai km = new dtokhuyenmai();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
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
            pst.setDouble(4, km.getPhanTram());
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
    
    
    
    public static void main(String[] args) {
        ArrayList<dtokhuyenmai> list = new daokhuyenmai().getListByDate("2025-10-10");
        for(dtokhuyenmai km : list){
            System.out.println(km.toString());
        }
    }
}
