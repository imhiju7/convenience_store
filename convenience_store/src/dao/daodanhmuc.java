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
    public void delete(int maDanhMuc) throws SQLException {
        // Câu lệnh xóa mềm danh mục
        String softDeleteDanhMucSql = "UPDATE danhmuc SET isDelete = 1 WHERE maDanhMuc = ?";

        // Câu lệnh xóa tất cả chức năng liên quan đến danh mục
        String deleteChucNangSql = "DELETE FROM chucnang WHERE maDanhMuc = ?";

        // Kết nối cơ sở dữ liệu
        Connection con = connect.connection();

        try {
            con.setAutoCommit(false); // Bắt đầu transaction

            // Thực hiện xóa mềm danh mục
            try (PreparedStatement pstDanhMuc = con.prepareStatement(softDeleteDanhMucSql)) {
                pstDanhMuc.setInt(1, maDanhMuc);
                int rowsAffected = pstDanhMuc.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Xóa mềm danh mục thành công!");

                    // Xóa tất cả các chức năng liên quan đến danh mục đó
                    try (PreparedStatement pstChucNang = con.prepareStatement(deleteChucNangSql)) {
                        pstChucNang.setInt(1, maDanhMuc);
                        pstChucNang.executeUpdate();
                        System.out.println("Xóa các chức năng liên quan thành công!");
                    }
                } else {
                    System.out.println("Không tìm thấy danh mục với mã đã cho.");
                }
            }

            con.commit(); // Commit transaction
        } catch (SQLException e) {
            con.rollback(); // Rollback nếu xảy ra lỗi
            e.printStackTrace();
            throw e;
        } finally {
            if (con != null) {
                con.setAutoCommit(true); // Đặt lại trạng thái mặc định
                con.close(); // Đóng kết nối
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
    public boolean checkDanhMucExists(String tenDanhMuc) throws SQLException {
    Connection con = connect.connection();
    String query = "SELECT COUNT(*) FROM DanhMuc WHERE TenDanhMuc = ?";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, tenDanhMuc);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu tồn tại ít nhất một danh mục
            }
        }
    }
    return false;
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
