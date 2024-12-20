/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtotaikhoan;
import dao.connect;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
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
public class daotaikhoan {
    
                public boolean addtaikhoan(dtotaikhoan tk) {
                Connection con = connect.connection();
                String sql = "INSERT INTO taikhoan(tenDangNhap, matKhau, ngayTao, isBlock, maNhanVien) VALUES(?,?,?,?,?)";
                try {
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, tk.getTendangnhap());
                    pst.setString(2, tk.getMatkhau());
                    pst.setTimestamp(3, new java.sql.Timestamp(tk.getNgaytao().getTime()));
                    pst.setInt(4, tk.getIsblock());
                    pst.setInt(5, tk.getManhanvien());
                    int rowsAffected = pst.executeUpdate();
                    return rowsAffected > 0; // Nếu thêm thành công, trả về true
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false; // Lỗi SQL, trả về false
                } finally {
                    try {
                        if (con != null) con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

    
        public boolean update(int manhanvien, String tendangnhap, String matkhau, int isblock) {
                    Connection con = connect.connection();
        try {
            String sql = "UPDATE taikhoan SET tendangnhap = ?, matkhau = ?, isblock = ? WHERE manhanvien = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tendangnhap); // Cập nhật tên đăng nhập
            ps.setString(2, matkhau);     // Cập nhật mật khẩu
            ps.setInt(3, isblock);        // Cập nhật trạng thái (1: Đã khóa, 0: Hoạt động)
            ps.setInt(4, manhanvien);     // Xác định mã nhân viên
            int rowsAffected = ps.executeUpdate(); // Thực hiện cập nhật
            return rowsAffected > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatematkhau(String tendangnhap, String matkhau){
        Connection con = connect.connection();
        String sql = "UPDATE taikhoan set matKhau= ? WHERE tenDangNhap = ?";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, matkhau);
            pst.setString(2, tendangnhap);
            pst.executeUpdate();
        } catch (SQLException e) {
            return false;
        } 
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean updateblock(dtotaikhoan tk){
        Connection con = connect.connection();
        String sql = "UPDATE taikhoan set isBlock = ? WHERE tenDangNhap = ?";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, tk.getIsblock());
            pst.setString(2, tk.getTendangnhap());
            pst.executeUpdate();
        } catch (SQLException e) {
            return false;
        } 
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean deletetaikhoan(dtotaikhoan tk){
        Connection con = connect.connection();
        String sql = "DELETE FROM taikhoan  WHERE tenDangNhap= ?";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,tk.getTendangnhap());
            pst.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    // check
    
    public boolean checktendangnhap(String tendangnhap) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, tendangnhap);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkmatkhau(String tendangnhap, String matkhau) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and matKhau = ? ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, tendangnhap);
            pst.setString(2, matkhau);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    public boolean checktaikhoanbikhoa(String tendangnhap) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and isBlock = 0 ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, tendangnhap);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // get
    
    public dtotaikhoan gettaikhoan(String tendangnhap, String matkhau) {
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and matKhau = ? and isBlock = 0 ";
        dtotaikhoan tk = new dtotaikhoan();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tendangnhap);
            pst.setString(2, matkhau);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    tk.setTendangnhap(rs.getString("tenDangNhap"));
                    tk.setMatkhau(rs.getString("matKhau"));
                    tk.setNgaytao(rs.getTimestamp("ngayTao"));
                    tk.setIsblock(rs.getInt("isBlock"));
                    tk.setManhanvien(rs.getInt("maNhanVien"));
                }
            }
        } catch (SQLException e) {
            return null;
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tk;
    }
    
    public ArrayList<dtotaikhoan> getlist() {
        Connection con = connect.connection();
        String sql = "SELECT * FROM taikhoan";
        ArrayList<dtotaikhoan> list = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtotaikhoan tk = new dtotaikhoan();
                tk.setTendangnhap(rs.getString("tenDangNhap"));
                tk.setMatkhau(rs.getString("matKhau"));
                tk.setNgaytao(rs.getTimestamp("ngayTao"));
                tk.setIsblock(rs.getInt("isBlock"));
                tk.setManhanvien(rs.getInt("maNhanVien"));
                list.add(tk);
            }
        } catch (SQLException e) {
        }
        Collections.sort(list, (dtotaikhoan person1, dtotaikhoan person2) -> person2.getNgaytao().compareTo(person1.getNgaytao()));
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public String getTenDangNhap(int maNhanVien) {
    Connection con = connect.connection();
    String sql = "SELECT tenDangNhap FROM taikhoan WHERE maNhanVien = ?";
    String tenDangNhap = null;
    try {
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maNhanVien);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            tenDangNhap = rs.getString("tenDangNhap");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return tenDangNhap;
}
public String getMatKhau(int maNhanVien) {
    Connection con = connect.connection();
    String sql = "SELECT matKhau FROM taikhoan WHERE maNhanVien = ?";
    String matKhau = null;
    try {
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maNhanVien);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            matKhau = rs.getString("matKhau");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return matKhau;
}

public Date getNgayTao(int maNhanVien) {
    Connection con = connect.connection();
    String sql = "SELECT ngayTao FROM taikhoan WHERE maNhanVien = ?";
    Date ngayTao = null;
    try {
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maNhanVien);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            ngayTao = rs.getTimestamp("ngayTao");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return ngayTao;
}

public int getIsBlock(int maNhanVien) {
    Connection con = connect.connection();
    String sql = "SELECT isBlock FROM taikhoan WHERE maNhanVien = ?";
    int isBlock = 0;
    try {
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maNhanVien);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            isBlock = rs.getInt("isBlock");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daotaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return isBlock;
}

    public int getmanhanvien(String tendangnhap){
        Connection con = connect.connection();
        String sql = "select * from taikhoan where tenDangNhap = ? and isBlock = 0";
        int manv = 0;
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tendangnhap);
        
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                manv = rs.getInt("maNhanVien");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manv;
    }
    public boolean getIsBlockedByMaNhanVien(int manhanvien) throws SQLException {
    String sql = "SELECT isblock FROM taikhoan WHERE manhanvien = ?";
    
    try (Connection con = connect.connection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        ps.setInt(1, manhanvien);  // Thiết lập mã nhân viên
        ResultSet rs = ps.executeQuery();  // Thực hiện truy vấn
        
        if (rs.next()) {
            return rs.getInt("isblock") == 1;  // Trả về true nếu tài khoản bị khóa (isblock = 1)
        }
    }
    
    return false;  // Trả về false nếu không tìm thấy hoặc tài khoản không bị khóa
}


}
