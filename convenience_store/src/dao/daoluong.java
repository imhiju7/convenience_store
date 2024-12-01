package dao;

import dto.dtoluong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    public ArrayList<dtoluong> getByTime(Date day1, Date day2) {
       ArrayList<dtoluong> list = new ArrayList<>();
        Connection con = connect.connection();
        String sql = "SELECT * FROM luong WHERE ngayNhanLuong BETWEEN ? AND ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, (java.sql.Date) day1);
            pst.setDate(2, (java.sql.Date) day2);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
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
            pst.setDouble(8, luong.getLuongLamThem());
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
            pst.setDouble(8, luong.getLuongLamThem());
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
    
    public static void main(String[] args) {
    daoluong daoLuong = new daoluong();

    // 1. Thử thêm một bản ghi mới
    dtoluong newLuong = new dtoluong(
            0, // maLuong (Auto Increment trong DB)
            1, // maChamCong
            500.0, // phuCap
            10000.0, // luongThucTe
            2000.0, // luongThuong
            1000.0, // khoanBaoHiem
            500.0, // khoanThue
            11500.0, // thuclanh
            2, // luongLamThem
            "2024-11-29", // ngayNhanLuong
            1 // maNhanVien
    );
    daoLuong.add(newLuong);
    System.out.println("Thêm mới thành công!");

    // 2. Thử lấy danh sách lương
    ArrayList<dtoluong> listLuong = daoLuong.getList();
    System.out.println("Danh sách lương:");
    for (dtoluong luong : listLuong) {
        System.out.println("Mã lương: " + luong.getMaLuong() + ", Mã nhân viên: " + luong.getMaNhanVien()
                + ", Lương thực tế: " + luong.getLuongThucTe());
    }

    // 3. Thử lấy thông tin chi tiết lương bằng ID
    int testId = 1; // Giả sử ID lương cần lấy là 1
    dtoluong luongById = daoLuong.getById(testId);
    if (luongById != null) {
        System.out.println("Chi tiết lương ID " + testId + ": " + luongById.getLuongThucTe());
    } else {
        System.out.println("Không tìm thấy lương với ID " + testId);
    }

    // 4. Đếm số lượng bản ghi
    int count = daoLuong.countLuong();
    System.out.println("Số lượng bản ghi lương: " + count);
}
}
