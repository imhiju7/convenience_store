package dao;

import dto.dtophanloai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daophanloai {
    // Lấy danh sách tất cả phân loại
    public ArrayList<dtophanloai> getlist() {
        ArrayList<dtophanloai> list = new ArrayList<>();
        Connection con = connect.connection(); // Kết nối đến cơ sở dữ liệu
        String sql = "SELECT * FROM phanloai WHERE isDelete = 0"; // Lọc theo isDelete = 0

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtophanloai phanLoai = new dtophanloai(
                         rs.getInt("maPhanLoai"),
                         rs.getString("tenPhanLoai"),
                         rs.getInt("isDelete")
                         
                );
                list.add(phanLoai);
            }
        } catch (SQLException e) {
            Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (con != null) {
                    con.close(); // Đảm bảo đóng kết nối
                }
            } catch (SQLException e) {
                Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }


    // Thêm phân loại mới
    public void add(dtophanloai phanLoai) {
        String sql = "INSERT INTO phanloai (tenPhanLoai, isDelete) VALUES (?, ?)";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, phanLoai.getTenPhanLoai());
            pst.setInt(2, 0); // set giá trị isDelete = 0 mặc định khi thêm mới
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    // Cập nhật phân loại
    public void update(dtophanloai phanLoai) {
        String sql = "UPDATE phanloai SET tenPhanLoai = ? WHERE maPhanLoai = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, phanLoai.getTenPhanLoai());
            pst.setInt(2, phanLoai.getMaPhanLoai());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Xóa phân loại
    public void delete(int maPhanLoai) {
    String sql = "UPDATE phanloai SET isDelete = 1 WHERE maPhanLoai = ?";
    Connection con = connect.connection();

    try {
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maPhanLoai);
        pst.executeUpdate();
    } catch (SQLException e) {
        Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}


    // Lấy tên phân loại theo mã
    public String getTenPhanLoai(int maPhanLoai) {
        String tenPhanLoai = null;
        String sql = "SELECT tenPhanLoai FROM phanloai WHERE maPhanLoai = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maPhanLoai);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tenPhanLoai = rs.getString("tenPhanLoai");
            }
        } catch (SQLException e) {
            Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return tenPhanLoai;
    }

    // Đếm số lượng phân loại
    public int getCountPhanLoai() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM phanloai";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daophanloai.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return count;
    }
    public boolean checkTenPhanLoaiExists(String tenPhanLoai) throws SQLException {
        Connection con = connect.connection();
        String query = "SELECT COUNT(*) FROM phanloai WHERE tenPhanLoai = ?";

        try (
                 PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, tenPhanLoai);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Nếu có kết quả thì tên phân loại đã tồn tại
            }
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi kiểm tra tên phân loại", e);
        }
        return false;
    }

    // Main method để kiểm tra
    public static void main(String[] args) {
        daophanloai dao = new daophanloai();

        // Lấy danh sách phân loại
        ArrayList<dtophanloai> list = dao.getlist();
        for (dtophanloai pl : list) {
            System.out.println(pl);
        }

        // Thêm một phân loại mới (ví dụ)
     
    }
}
