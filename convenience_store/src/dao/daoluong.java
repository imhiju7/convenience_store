package dao;

import dto.dtoluong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daoluong {

    // Lấy thông tin lương theo ID
    public dtoluong getById(int maLuong) {
        dtoluong luong = null;
        Connection con = connect.connection();
        String sql = "SELECT * FROM luong WHERE maLuong = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maLuong);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                luong = new dtoluong(
                        rs.getInt("maLuong"),
                        rs.getInt("maChamCong"),
                        rs.getDouble("phuCap"),
                        rs.getDouble("luongThucTe"),
                        rs.getDouble("luongThuong"),
                        rs.getDouble("khoanBaoHiem"),
                        rs.getDouble("khoanThue"),
                        rs.getDouble("thuclanh"),
                        rs.getInt("luongLamThem"),
                        rs.getString("ngayNhanLuong"),
                        rs.getInt("maNhanVien")
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return luong;
    }

    // Lấy danh sách tất cả các bản ghi lương
    public ArrayList<dtoluong> getList() {
        ArrayList<dtoluong> list = new ArrayList<>();
        Connection con = connect.connection();
        String sql = "SELECT * FROM luong";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                dtoluong luong = new dtoluong(
                        rs.getInt("maLuong"),
                        rs.getInt("maChamCong"),
                        rs.getDouble("phuCap"),
                        rs.getDouble("luongThucTe"),
                        rs.getDouble("luongThuong"),
                        rs.getDouble("khoanBaoHiem"),
                        rs.getDouble("khoanThue"),
                        rs.getDouble("thuclanh"),
                        rs.getInt("luongLamThem"),
                        rs.getString("ngayNhanLuong"),
                        rs.getInt("maNhanVien")
                );
                list.add(luong);
            }
        } catch (SQLException e) {
            Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }

    // Thêm mới thông tin lương
    public void add(dtoluong luong) {
        String sql = "INSERT INTO luong (maChamCong, phuCap, luongThucTe, luongThuong, khoanBaoHiem, khoanThue, thuclanh, luongLamThem, ngayNhanLuong, maNhanVien) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, luong.getMaChamCong());
            pst.setDouble(2, luong.getPhuCap());
            pst.setDouble(3, luong.getLuongThucTe());
            pst.setDouble(4, luong.getLuongThuong());
            pst.setDouble(5, luong.getKhoanBaoHiem());
            pst.setDouble(6, luong.getKhoanThue());
            pst.setDouble(7, luong.getThuclanh());
            pst.setInt(8, luong.getLuongLamThem());
            pst.setString(9, luong.getNgayNhanLuong());
            pst.setInt(10, luong.getMaNhanVien());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Cập nhật thông tin lương
    public void update(dtoluong luong) {
        String sql = "UPDATE luong SET maChamCong = ?, phuCap = ?, luongThucTe = ?, luongThuong = ?, khoanBaoHiem = ?, "
                + "khoanThue = ?, thuclanh = ?, luongLamThem = ?, ngayNhanLuong = ?, maNhanVien = ? WHERE maLuong = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, luong.getMaChamCong());
            pst.setDouble(2, luong.getPhuCap());
            pst.setDouble(3, luong.getLuongThucTe());
            pst.setDouble(4, luong.getLuongThuong());
            pst.setDouble(5, luong.getKhoanBaoHiem());
            pst.setDouble(6, luong.getKhoanThue());
            pst.setDouble(7, luong.getThuclanh());
            pst.setInt(8, luong.getLuongLamThem());
            pst.setString(9, luong.getNgayNhanLuong());
            pst.setInt(10, luong.getMaNhanVien());
            pst.setInt(11, luong.getMaLuong());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Xóa thông tin lương (cứng, nếu cần xóa mềm, thêm cột isDelete)
    public void delete(int maLuong) {
        String sql = "DELETE FROM luong WHERE maLuong = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maLuong);
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Đếm số lượng bản ghi lương
    public int countLuong() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM luong";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return count;
    }
}
