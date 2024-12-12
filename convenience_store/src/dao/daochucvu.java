/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtochucvu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hieu PC
 */
public class daochucvu {

    public void add(dtochucvu chucvu) throws SQLException {
        Connection con = connect.connection(); // Kết nối đến cơ sở dữ liệu
        String sql = "INSERT INTO chucvu (maChucVu, tenChucVu, isDelete) VALUES (?, ?, 0)"; // Thêm isDelete mặc định là 0

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, chucvu.getMachucvu());
            pst.setString(2, chucvu.getTenchucvu());
            pst.executeUpdate();
        } finally {
            if (con != null) {
                con.close(); // Đóng kết nối
            }
        }
}


    // Cập nhật thông tin chức vụ
    public void update(dtochucvu chucvu) throws SQLException {
        String sql = "UPDATE chucvu SET tenChucVu = ? WHERE maChucVu = ? AND isDelete = 0";
        Connection con = connect.connection();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, chucvu.getTenchucvu()); // Gán giá trị cho tên chức vụ
            pst.setInt(2, chucvu.getMachucvu()); // Gán giá trị cho mã chức vụ

            int rowsAffected = pst.executeUpdate(); // Thực hiện câu lệnh cập nhật
            if (rowsAffected > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy chức vụ với mã đã cho hoặc bản ghi đã bị xóa.");
            }
        } finally {
            if (con != null) {
                con.close(); // Đảm bảo đóng kết nối
            }
        }
    }


    // Xóa chức vụ
    public void delete(int maChucVu) throws SQLException {
    String softDeleteChucVuSql = "UPDATE chucvu SET isDelete = 1 WHERE maChucVu = ?";
    String deletePhanQuyenSql = "DELETE FROM phanquyen WHERE maChucVu = ?";
    Connection con = connect.connection();

    try {
        con.setAutoCommit(false); // Bắt đầu transaction

        // Thực hiện xóa mềm chức vụ
        try (PreparedStatement pstChucVu = con.prepareStatement(softDeleteChucVuSql)) {
            pstChucVu.setInt(1, maChucVu);
            int rowsAffected = pstChucVu.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Xóa mềm chức vụ thành công!");

                // Xóa tất cả các phân quyền liên quan đến chức vụ đó
                try (PreparedStatement pstPhanQuyen = con.prepareStatement(deletePhanQuyenSql)) {
                    pstPhanQuyen.setInt(1, maChucVu);
                    pstPhanQuyen.executeUpdate();
                    System.out.println("Xóa phân quyền liên quan thành công!");
                }
            } else {
                System.out.println("Không tìm thấy chức vụ với mã đã cho.");
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



    public ArrayList<dtochucvu> getlist() {
        ArrayList<dtochucvu> list = new ArrayList<>();
        Connection con = connect.connection();
        String sql = "SELECT * FROM chucvu WHERE isDelete = 0"; // Lấy các bản ghi chưa bị xóa

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtochucvu cv = new dtochucvu(
                         rs.getInt("maChucVu"),
                         rs.getString("tenChucVu"),
                         rs.getInt("isDelete")
                );
                list.add(cv);
            }
        } catch (SQLException e) {
            Logger.getLogger(daochucvu.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (con != null) {
                    con.close(); // Đảm bảo đóng kết nối
                }
            } catch (SQLException ex) {
                Logger.getLogger(daochucvu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }


    public String getTenChucVu(int maChucVu) {
        String tenChucVu = null;
        Connection con = connect.connection();
        String sql = "SELECT tenChucVu FROM chucvu WHERE maChucVu = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maChucVu); // Đặt giá trị cho tham số
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tenChucVu = rs.getString("tenChucVu"); // Lấy tên chức vụ
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close(); // Đảm bảo đóng kết nối
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tenChucVu; // Trả về tên chức vụ
    }
    // Lấy danh sách chức vụ

    public int getCountChucVu() {
        int count = 0;
        Connection con = connect.connection();
        String sql = "SELECT COUNT(*) FROM chucvu"; // Truy vấn SQL để đếm số lượng
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1); // Lấy giá trị đếm từ kết quả truy vấn
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        } finally {
            try {
                if (con != null) {
                    con.close(); // Đảm bảo đóng kết nối
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count; // Trả về số lượng chức vụ
    }

    public String gettencvbymacv(int macv) {
        Connection con = connect.connection();
        String sql = "SELECT * FROM chucvu where  maChucVu = ?";
        String tencv = "";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, macv);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tencv = rs.getString("tenChucVu");
            }
        } catch (SQLException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daonhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tencv;
    }
    public boolean isTenChucVuExists(String tenChucVu) throws SQLException {
        Connection con = connect.connection();
        String query = "SELECT COUNT(*) FROM ChucVu WHERE TenChucVu = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, tenChucVu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

}
