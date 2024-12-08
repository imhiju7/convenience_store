/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtohopdong;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class daohopdong {
    public daohopdong(){
    };
    
    public dtohopdong selectHopDong(int maHD){
        dtohopdong hd = new dtohopdong();
        try (java.sql.Connection con = connect.connection()) {
            String sql = "select * from hopdonglaodong where mahopdong = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,maHD);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int mahd = rs.getInt("mahopdong");
                String tungay = rs.getDate("tungay")+"";
                String denngay = rs.getDate("denngay")+"";
                Double luongcb = rs.getDouble("luongcoban");
                int manv = rs.getInt("maNhanVien");
                int isDelete = rs.getInt("isDelete");
                hd = new dtohopdong(mahd, tungay, denngay, luongcb, manv, isDelete);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        if(hd.getIsDelete()==0){
            return hd;
        }
        return null;
    }
    public dtohopdong gethopdongnhanvien(int ma_nv){
            dtohopdong hd = new dtohopdong();
            try (java.sql.Connection con = connect.connection()) {
            String sql = "select * from hopdonglaodong where maNhanVien = ? ";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,ma_nv);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int mahd = rs.getInt("mahopdong");
                String tungay = rs.getDate("tungay")+"";
                String denngay = rs.getDate("denngay")+"";
                double luongcb = rs.getFloat("luongcoban");
                int manv = rs.getInt("maNhanVien");
                int isDelete = rs.getInt("isDelete");
                hd = new dtohopdong(mahd, tungay, denngay, luongcb, manv, isDelete);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        if(hd.getIsDelete()==0){
            return hd;
        }
        return null;
    }
    
    
        public ArrayList<dtohopdong> getlist(){
            ArrayList<dtohopdong> list = new ArrayList<>();
            try (java.sql.Connection con = connect.connection()) {
                String sql = "select * from hopdonglaodong where isDelete=0 ";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    int mahd = rs.getInt("mahopdong");
                    String tungay = rs.getDate("tungay")+"";
                    String denngay = rs.getDate("denngay")+"";
                    double luongcb = rs.getFloat("luongcoban");
                    int manv = rs.getInt("maNhanVien");
                    int isDelete = rs.getInt("isDelete");
                    dtohopdong hd = new dtohopdong(mahd, tungay, denngay, luongcb, manv, isDelete);
                    list.add(hd);
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return list;
        }


    public ArrayList<dtohopdong> getlistConditon(String columnName, String conditonValue){
        ArrayList<dtohopdong> list = new ArrayList<>();
        try (java.sql.Connection con = connect.connection()) {
            String sql = "select * from hopdonglaodong where isDelete=0 and "+ columnName + " = " + conditonValue;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int mahd = rs.getInt("mahopdong");
                String tungay = rs.getDate("tungay")+"";
                String denngay = rs.getDate("denngay")+"";
                double luongcb = rs.getFloat("luongcoban");
                int manv = rs.getInt("maNhanVien");
                int isDelete = rs.getInt("isDelete");
                dtohopdong hd = new dtohopdong(mahd, tungay, denngay, luongcb, manv, isDelete);
                list.add(hd);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
    public int getMaxMaHopDong() {
        int maxMaHopDong = 0;
        try (java.sql.Connection con = connect.connection()) {
            String sql = "SELECT MAX(mahopdong) AS max_mahopdong FROM hopdonglaodong";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                maxMaHopDong = rs.getInt("max_mahopdong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxMaHopDong;
    }
    
    
    public ArrayList<String> getListMaNV(){
        ArrayList<String> listMaNV = new ArrayList<>();
        try (java.sql.Connection con = connect.connection()) {
            String sql = "select maNhanVien from nhanvien where isDelete=0  ";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                listMaNV.add(rs.getString("maNhanVien"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return listMaNV;
    }
    
    public boolean addHopDong(dtohopdong hd) {
        boolean isSuccess = false;
        try (java.sql.Connection con = connect.connection()) {
            String sql = "INSERT INTO hopdonglaodong (mahopdong, tungay, denngay, luongcoban, maNhanVien, isDelete) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            // Gán giá trị cho các tham số
            pst.setInt(1, hd.getMaHopDong());
            pst.setDate(2, java.sql.Date.valueOf(hd.getTuNgay())); // Chuyển đổi từ String sang Date
            pst.setDate(3, java.sql.Date.valueOf(hd.getDenNgay())); // Chuyển đổi từ String sang Date
            pst.setFloat(4,(float)hd.getLuongCoBan());
            pst.setInt(5, hd.getMaNV());
            pst.setInt(6,0);
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
    
    public Boolean Deleted(int maHopDong) {
        boolean isSuccess = false;
        String sql = "UPDATE hopdonglaodong SET isDelete = 1 WHERE mahopdong = ?";

        try (java.sql.Connection con = connect.connection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, maHopDong);
            int rowsAffected = pst.executeUpdate();

            // Kiểm tra nếu có ít nhất 1 hàng bị cập nhật
            isSuccess = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public Boolean Update(dtohopdong hd) {
        boolean isSuccess = false;
        String sql = "UPDATE hopdonglaodong SET tungay=?, denngay=?, luongcoban=?, maNhanVien=?  WHERE mahopdong = ?";

        try (java.sql.Connection con = connect.connection();
            PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, hd.getTuNgay());
            pst.setString(2, hd.getDenNgay());
            pst.setFloat(3,(float)hd.getLuongCoBan());
            pst.setInt(4,hd.getMaNV());
            pst.setInt(5,hd.getMaHopDong());
            int rowsAffected = pst.executeUpdate();

            // Kiểm tra nếu có ít nhất 1 hàng bị cập nhật 
            isSuccess = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
    
    
    public static void main(String[] args){
        
        ArrayList<dtohopdong> list = new daohopdong().getlistConditon("tungay", "2024-01-09 ");
        for(dtohopdong hd : list){
            System.out.println(hd.toString());
        }
    }
}
