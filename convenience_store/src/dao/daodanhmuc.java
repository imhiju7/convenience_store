package dao;

import dto.dtodanhmuc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daodanhmuc {
    // Lấy danh sách tất cả danh mục
    public ArrayList<dtodanhmuc> getlist() {
        ArrayList<dtodanhmuc> list = new ArrayList<>();
        Connection con = connect.connection(); // Kết nối đến cơ sở dữ liệu
        String sql = "SELECT * FROM danhmuc WHERE isDelete = 0"; // Lấy danh mục chưa bị xóa

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtodanhmuc danhMuc = new dtodanhmuc(
                         rs.getInt("maDanhMuc"),
                         rs.getString("tenDanhMuc"),
                         rs.getString("icon"),
                         rs.getInt("isDelete")
                );
                list.add(danhMuc);
            }
        } catch (SQLException e) {
            Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (con != null) {
                    con.close(); // Đảm bảo đóng kết nối
                }
            } catch (SQLException e) {
                Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }


    // Thêm danh mục mới
    public void add(dtodanhmuc danhMuc) {
        String sql = "INSERT INTO danhmuc (tenDanhMuc, icon, isDelete) VALUES (?, ?, 0)"; // Mặc định isDelete = 0
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, danhMuc.getTenDanhMuc());
            pst.setString(2, danhMuc.getIcon());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    // Cập nhật danh mục
    public void update(dtodanhmuc danhMuc) {
        String sql = "UPDATE danhmuc SET tenDanhMuc = ?, icon = ? WHERE maDanhMuc = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, danhMuc.getTenDanhMuc());
            pst.setString(2, danhMuc.getIcon());
            pst.setInt(3, danhMuc.getMaDanhMuc());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    // Xóa danh mục
    public void delete(int maDanhMuc) {
        String sql = "UPDATE danhmuc SET isDelete = 1 WHERE maDanhMuc = ?"; // Chuyển isDelete thành 1
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maDanhMuc);
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    // Lấy tên danh mục theo mã
    public String getTenDanhMuc(int maDanhMuc) {
        String tenDanhMuc = null;
        String sql = "SELECT tenDanhMuc FROM danhmuc WHERE maDanhMuc = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maDanhMuc);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tenDanhMuc = rs.getString("tenDanhMuc");
            }
        } catch (SQLException e) {
            Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return tenDanhMuc;
    }

    // Đếm số lượng danh mục
    public int getCountDanhMuc() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM danhmuc";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daodanhmuc.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return count;
    }

    // Main method để kiểm tra
    public static void main(String[] args) {
        daodanhmuc dao = new daodanhmuc();

        // Lấy danh sách danh mục
        ArrayList<dtodanhmuc> list = dao.getlist();
        for (dtodanhmuc dm : list) {
            System.out.println(dm);
        }

    }
}
