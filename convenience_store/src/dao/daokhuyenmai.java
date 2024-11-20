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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 *
 * @author giavi
 */
public class daokhuyenmai {

    public daokhuyenmai() {
    }

    ;
    
    public ArrayList<dtokhuyenmai> getList() {
        ArrayList<dtokhuyenmai> list = new ArrayList<>();
        try (java.sql.Connection con = connect.connection()) {
            String sql = "select * from khuyenmai where ishidden=0 ";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maKM = rs.getInt("maKhuyenMai");
                String tenKM = rs.getString("tenKhuyenMai");
                String ngayBD = rs.getDate("ngayBatDau") + "";
                String ngayHH = rs.getDate("ngayHetHan") + "";
                int soLuong = rs.getInt("soLuong");
                double phanTram = rs.getDouble("phanTram");
                int ishidden = rs.getInt("ishidden");
                int soLuongDaDung = rs.getInt("soLuongDaDung");
                dtokhuyenmai km = new dtokhuyenmai(maKM, tenKM, ngayBD, ngayHH, soLuong, phanTram, ishidden, soLuongDaDung);
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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

            // Gán giá trị cho các tham số
            pst.setInt(1, km.getMaKhuyenMai());
            pst.setString(2, km.getTenKhuyenMai());
            pst.setDate(3, java.sql.Date.valueOf(km.getNgayBatDau())); // Chuyển đổi từ String sang Date
            pst.setDate(4, java.sql.Date.valueOf(km.getNgayHetHan())); // Chuyển đổi từ String sang Date
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
        String sql = "UPDATE khuyenmai SET tenKhuyenMai=?, ngayBatDau=?, ngayHetHan=?, SoLuong=?, phanTram=?, soLuongDaDung=0 WHERE maKhuyenMai = ?";

        try (java.sql.Connection con = connect.connection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, km.getTenKhuyenMai());
            pst.setString(2, km.getNgayBatDau());
            pst.setString(3, km.getNgayHetHan());
            pst.setInt(4, km.getSoLuong());
            pst.setDouble(5, km.getPhanTram());
            pst.setInt(6, km.getMaKhuyenMai());
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
                int maKM = rs.getInt("maKhuyenMai");
                String tenKM = rs.getString("tenKhuyenMai");
                String ngayBD = rs.getDate("ngayBatDau") + "";
                String ngayHH = rs.getDate("ngayHetHan") + "";
                int soLuong = rs.getInt("soLuong");
                double phanTram = rs.getDouble("phanTram");
                int ishidden = rs.getInt("ishidden");
                int soLuongDaDung = rs.getInt("soLuongDaDung");
                System.out.println("dao.daokhuyenmai.getlistConditon()");
                dtokhuyenmai km = new dtokhuyenmai(maKM, tenKM, ngayBD, ngayHH, soLuong, phanTram, ishidden, soLuongDaDung);
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
                int maKM = rs.getInt("maKhuyenMai");
                String tenKM = rs.getString("tenKhuyenMai");
                String ngayBD = rs.getDate("ngayBatDau") + "";
                String ngayHH = rs.getDate("ngayHetHan") + "";
                int soLuong = rs.getInt("soLuong");
                double phanTram = rs.getDouble("phanTram");
                int ishidden = rs.getInt("ishidden");
                int soLuongDaDung = rs.getInt("soLuongDaDung");
                System.out.println("dao.daokhuyenmai.getlistConditon()");
                dtokhuyenmai km = new dtokhuyenmai(maKM, tenKM, ngayBD, ngayHH, soLuong, phanTram, ishidden, soLuongDaDung);
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
                int maKM = rs.getInt("maKhuyenMai");
                String tenKM = rs.getString("tenKhuyenMai");
                String ngayBD = rs.getDate("ngayBatDau") + "";
                String ngayHH = rs.getDate("ngayHetHan") + "";
                int soLuong = rs.getInt("soLuong");
                double phanTram = rs.getDouble("phanTram");
                int ishidden = rs.getInt("ishidden");
                int soLuongDaDung = rs.getInt("soLuongDaDung");
                System.out.println("dao.daokhuyenmai.getlistConditon()");
                dtokhuyenmai km = new dtokhuyenmai(maKM, tenKM, ngayBD, ngayHH, soLuong, phanTram, ishidden, soLuongDaDung);
                list.add(km);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<dtokhuyenmai> getListByDate(String date){
        ArrayList<dtokhuyenmai> list = new ArrayList<>();
        ArrayList<dtokhuyenmai> list2 = new daokhuyenmai().getList();
        for(dtokhuyenmai km : list2){
            if(km.getNgayBatDau().equals(date) || km.getNgayHetHan().equals(date)){
                list.add(km);
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
        ArrayList<dtokhuyenmai> arr = new daokhuyenmai().getListByInt("maKhuyenMai", 14);
        //ArrayList<dtokhuyenmai> arr = new daokhuyenmai().getList();
        for (dtokhuyenmai km : arr) {
            System.out.println(km.toString());
        }
    }
}
