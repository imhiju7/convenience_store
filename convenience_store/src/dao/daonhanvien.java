/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtonhanvien;
import dao.connect;
import dto.dtochucvu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hieu PC
 */
public class daonhanvien {
    
    
    private ArrayList<dtonhanvien> list_nv = new ArrayList();
    private ArrayList<dtochucvu> list_cv = new ArrayList();
    
    
    public ArrayList<dtonhanvien> list() throws SQLException{
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete = 0";
        PreparedStatement pst =  con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            dtonhanvien nv = new dtonhanvien();
            nv.setManhanvien(rs.getInt("maNhanVien"));
            nv.setTennhanvien(rs.getString("tenNhanVien"));
            nv.setNgaysinh(rs.getDate("ngaySinh"));
            nv.setEmail(rs.getString("Email"));
            nv.setSdt(rs.getString("soDienThoai"));
            nv.setDiachi(rs.getString("diaChi"));
            nv.setGioitinh(rs.getInt("gioiTinh"));
            nv.setMachucvu(rs.getInt("maChucVu"));
            nv.setIsdelete(rs.getInt("isDelete"));
            nv.setLuongcoban(rs.getFloat("luongCoBan"));
            nv.setImg(rs.getString("img"));
            System.out.println("đã vào");
            list_nv.add(nv);
        }
        con.close();
        return list_nv;
    }
    
    public ArrayList<dtonhanvien> getlist(){
        return list_nv;
    }
    
    // add
    public void AddNhanVien(dtonhanvien nv) throws SQLException {
    Connection con = connect.connection();

    String sql = "INSERT INTO nhanvien (maNhanVien, tenNhanVien, maChucVu, gioiTinh, ngaySinh, diaChi, email, " +
                 "soDienThoai, isDelete, ngayTao, img, maHopDong, luongCoBan, ngayLamViec, ngayKetThuc) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, nv.getManhanvien());
        pst.setString(2, nv.getTennhanvien());
        pst.setInt(3, nv.getMachucvu());
        pst.setString(4, nv.getGioitinh() == 1 ? "Nam" : "Nữ");
        pst.setDate(5, nv.getNgaysinh() != null ? new java.sql.Date(nv.getNgaysinh().getTime()) : null);
        pst.setString(6, nv.getDiachi());
        pst.setString(7, nv.getEmail());
        pst.setString(8, nv.getSdt());
        pst.setInt(9, nv.getIsdelete());
        pst.setTimestamp(10, nv.getNgaytao() != null ? new java.sql.Timestamp(nv.getNgaytao().getTime()) : null);
        pst.setString(11, nv.getImg());
        pst.setObject(12, nv.getMahopdong() == 0 ? nv.getMahopdong() : null, java.sql.Types.INTEGER);
        pst.setFloat(13, nv.getLuongcoban());
        pst.setDate(14, nv.getNgaylamviec() != null ? new java.sql.Date(nv.getNgaylamviec().getTime()) : null);
        pst.setDate(15, nv.getNgayketthuc() != null ? new java.sql.Date(nv.getNgayketthuc().getTime()) : null);

        pst.executeUpdate();
    } finally {
        if (con != null) {
            con.close();
        }
    }
}

    
    // update
    public void updateNhanVien(dtonhanvien nv) throws SQLException{
        String sql = "update nhanvien set tenNhanvien = ?, maChucVu = ?, GioiTinh = ?, ngaySinh = ?, diaChi = ?, email = ?, soDienThoai = ?, img = ? where maNhanVien = ? ";
        Connection con = connect.connection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, nv.getTennhanvien());
        pst.setInt(2, nv.getMachucvu());
        pst.setInt(3, nv.getGioitinh());
        pst.setDate(4, new java.sql.Date(nv.getNgaysinh().getTime()));
        pst.setString(5, nv.getDiachi());
        pst.setString(6, nv.getEmail());
        pst.setString(7, nv.getSdt());
        pst.setString(8, nv.getImg());
        pst.setInt(9, nv.getManhanvien());
        int rowsAffected = pst.executeUpdate(); // Thực hiện câu lệnh cập nhật
        if (rowsAffected > 0) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Không cập nhật được nhân viên với mã đã cho.");
        }
        con.close();
        
    }
    
    // delete
    public void deleteNhanVien(Integer ma) throws SQLException{
        String sql = "update nhanvien set isDelete = 1 where maNhanVien = ? ";
        Connection con = connect.connection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ma);
        int rowsAffected = pst.executeUpdate(); // Thực hiện câu lệnh cập nhật
        if (rowsAffected > 0) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Không xóa được nhân viên với mã đã cho.");
        }
        con.close();
    }
    // check
    
    public boolean checkemailexist(String mail){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and email = ?";
        boolean flag = false;
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setString(1, mail);
        
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
              flag = true;
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    // get
    public int getmachucvu(int manv){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and maNhanVien = ?";
        int macv = 0;
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, manv);
        
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                macv = rs.getInt("maChucVu");
            }
        } catch (SQLException e) {
            
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return macv;
    }
    
    public String getemail(int manv){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and maNhanVien = ?";
        String email = "";
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, manv);
        

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                email = rs.getString("Email");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }
    public String gettennvbymanv(int manv){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and maNhanVien = ?";
        String tennv = "";
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setInt(1, manv);
        

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                tennv = rs.getString("tenNhanVien");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tennv;
    }
    public String gettennvbyemail(String email){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete= 0 and email = ?";
        String tennv = "";
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setString(1, email);
        

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                tennv = rs.getString("tenNhanVien");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tennv;
    }
    
    
    public ArrayList<dtochucvu> listChucVu() throws SQLException{
        Connection con = connect.connection();
        String sql = "SELECT * FROM chucvu where 1";
        PreparedStatement pst =  con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            dtochucvu cv = new dtochucvu();
            cv.setMachucvu(rs.getInt("maChucVu"));
            cv.setTenchucvu(rs.getString("tenChucVu"));
            list_cv.add(cv);
        }
        con.close();
        return list_cv;
    }
    
    public String getTenChucVu(Integer ma) throws SQLException{
        Connection con = connect.connection();
        String sql = "select tenChucVu from chucvu where maChucVu = ?";
        String ten = "";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, ma);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           ten = rs.getString("tenChucVu");
        }
        return ten;
    }
    
    public Integer getMaChucVuByName(String tencv) throws SQLException{
        Connection con = connect.connection();
        String sql = "select maChucVu from chucvu where tenChucVu = ?";
        Integer ma = 0;
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, tencv);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
           ma = rs.getInt("maChucVu");
        }
        return ma;
    }
    
    
    public Integer getSoLuongNV() throws SQLException{
        int soluong = 0;
        String sql = "select count(*) from nhanvien where 1";
        Connection con = connect.connection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            soluong = rs.getInt(1);
        }
        return soluong;
    }
    
    
    public static void main(String[] args) throws SQLException {
        ArrayList<dtonhanvien> list = new ArrayList();
        daonhanvien d = new daonhanvien();
        list = d.list();
        System.out.println(list);
    }
    
}
