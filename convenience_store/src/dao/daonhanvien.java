/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtonhanvien;
import dao.connect;
import dto.dtochucvu;
import dto.dtoctphieunhap;
import dto.dtohopdong;

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
    
    
    public ArrayList<dtonhanvien> list() {
        ArrayList<dtonhanvien> list_nv = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = connect.connection();
            String sql = "SELECT * FROM nhanvien WHERE isDelete = 0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
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
                nv.setImg(rs.getString("img"));
                list_nv.add(nv);
            }
        } catch (SQLException e) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list_nv;
    }
    
    public dtonhanvien getnv(dtonhanvien i){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where isDelete = 0 and maNhanVien = ?";
        PreparedStatement pst;
        dtonhanvien nv = new dtonhanvien();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, i.getManhanvien());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                nv.setManhanvien(rs.getInt("maNhanVien"));
                nv.setTennhanvien(rs.getString("tenNhanVien"));
                nv.setNgaysinh(rs.getDate("ngaySinh"));
                nv.setEmail(rs.getString("Email"));
                nv.setSdt(rs.getString("soDienThoai"));
                nv.setDiachi(rs.getString("diaChi"));
                nv.setGioitinh(rs.getInt("gioiTinh"));
                nv.setMachucvu(rs.getInt("maChucVu"));
                nv.setIsdelete(rs.getInt("isDelete"));
                nv.setImg(rs.getString("img"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nv;
    }
    
    // add
    public void AddNhanVien(dtonhanvien nv) throws SQLException {
        Connection con = connect.connection();

        String sql = "INSERT INTO nhanvien (maNhanVien, tenNhanVien, maChucVu, gioiTinh, ngaySinh, diaChi, email, " +
                     "soDienThoai, isDelete, ngayTao, img) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, nv.getManhanvien());
            pst.setString(2, nv.getTennhanvien());
            pst.setInt(3, nv.getMachucvu());
            pst.setInt(4, nv.getGioitinh());
            pst.setDate(5, nv.getNgaysinh() != null ? new java.sql.Date(nv.getNgaysinh().getTime()) : null);
            pst.setString(6, nv.getDiachi());
            pst.setString(7, nv.getEmail());
            pst.setString(8, nv.getSdt());
            pst.setInt(9, nv.getIsdelete());
            pst.setTimestamp(10, nv.getNgaytao() != null ? new java.sql.Timestamp(nv.getNgaytao().getTime()) : null);
            pst.setString(11, nv.getImg());

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
    
    
     public boolean checkExistSdt(String sdt) throws SQLException{
        String sql = "SELECT * FROM nhanvien WHERE soDienThoai = ? ";
        Connection con = connect.connection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, sdt);
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println("Đã tồn tại số điện thoại này");
            return true;
        }
        return false;
    }
    
    
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
    public String getChucVuByMaNhanVien(int manhanvien) throws SQLException {
        String sql = "SELECT cv.tenchucvu FROM nhanvien nv JOIN chucvu cv ON nv.machucvu = cv.machucvu WHERE nv.manhanvien = ?";
        
        try (Connection con = connect.connection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, manhanvien);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("tenchucvu");  // Trả về tên chức vụ
            }
            return null;  // Nếu không tìm thấy, trả về null
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateChucVuByMaNhanVien(int manhanvien, int machucvu) throws SQLException {
        String sql = "UPDATE nhanvien SET machucvu = ? WHERE manhanvien = ?";
        
        try (Connection con = connect.connection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, machucvu);  // Cập nhật mã chức vụ mới
            ps.setInt(2, manhanvien); // Dựa trên mã nhân viên

            int rowsAffected = ps.executeUpdate(); // Thực hiện cập nhật
            return rowsAffected > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
    
    public String gettennvbymanv2(int manv){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where maNhanVien = ?";
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
    
    public int getMaNhanVienByTenNV(String ten){
        Connection con = connect.connection();
        String sql = "SELECT * FROM nhanvien where tenNhanVien = ?";
        int ma = 0;
        try{
            PreparedStatement pst =  con.prepareStatement(sql);
            pst.setString(1, ten);
        

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                ma = rs.getInt("maNhanVien");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ma;
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
    
    
    public ArrayList<dtonhanvien> getNhanVienList() {
        ArrayList<dtonhanvien> list_nv = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = connect.connection(); // Kết nối DB
            String sql = "SELECT * FROM nhanvien WHERE isDelete = 0";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                dtonhanvien nv = new dtonhanvien();
                nv.setManhanvien(rs.getInt("maNhanVien"));
                nv.setTennhanvien(rs.getString("tenNhanVien"));
                nv.setMachucvu(rs.getInt("maChucVu"));
                nv.setImg(rs.getString("img"));
                nv.setDiachi(rs.getString("diaChi"));
                nv.setEmail(rs.getString("email"));
                nv.setGioitinh(rs.getInt("gioiTinh"));
                nv.setNgaysinh(rs.getDate("ngaySinh"));
                nv.setSdt(rs.getString("soDienThoai"));
                nv.setIsdelete(rs.getInt("isDelete"));
                nv.setNgaytao(rs.getDate("ngayTao"));
                list_nv.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list_nv;
    }
    
    
    
    public ArrayList<dtohopdong> list_HD() throws SQLException{
        String sql = "SELECT * FROM hopdonglaodong WHERE 1";
        Connection con = connect.connection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        ArrayList<dtohopdong> list_HD = new ArrayList<>();

        while(rs.next()){
            dtohopdong hd = new dtohopdong();
            hd.setMaNV(rs.getInt("maNhanVien"));
            hd.setLuongCoBan(rs.getFloat("luongcoban"));
            list_HD.add(hd);
        }
        return list_HD;
    }
    
    
    
    
    public static void main(String args[]) throws SQLException {
        // daonhanvien bus = new daonhanvien();
        // for(dtonhanvien nv:bus.getlist()){
        //     System.out.print(nv);
        // }
    }

   
}
