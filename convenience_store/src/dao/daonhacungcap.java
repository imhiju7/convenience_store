package dao;

import dto.dtonhacungcap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daonhacungcap {

    public dtonhacungcap getById(int maNhaCungCap) {
        dtonhacungcap nhaCungCap = null;
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM nhacungcap WHERE maNhaCungCap = ? AND isDelete = 0";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maNhaCungCap);  // Set the supplier ID parameter
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nhaCungCap = new dtonhacungcap(
                    rs.getInt("maNhaCungCap"),
                    rs.getString("tenNhaCungCap"),
                    rs.getString("SDT"),
                    rs.getString("email"),
                    rs.getString("diaChi"),
                    rs.getInt("isDelete") // Lấy giá trị isDelete
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return nhaCungCap;
    }
    
    public dtonhacungcap getByName(String tenNhaCungCap) {
        dtonhacungcap nhaCungCap = null;
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM nhacungcap WHERE tenNhaCungCap = ? AND isDelete = 0";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tenNhaCungCap);  // Set the supplier ID parameter
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nhaCungCap = new dtonhacungcap(
                    rs.getInt("maNhaCungCap"),
                    rs.getString("tenNhaCungCap"),
                    rs.getString("SDT"),
                    rs.getString("email"),
                    rs.getString("diaChi"),
                    rs.getInt("isDelete") // Lấy giá trị isDelete
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return nhaCungCap;
    }

    // Lấy danh sách tất cả nhà cung cấp (không bị xóa)
    public ArrayList<dtonhacungcap> getlist() {
        ArrayList<dtonhacungcap> list = new ArrayList<>();
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM nhacungcap WHERE isDelete = 0"; // Chỉ lấy nhà cung cấp không bị xóa
        
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtonhacungcap ncc = new dtonhacungcap(
                    rs.getInt("maNhaCungCap"),
                    rs.getString("tenNhaCungCap"),
                    rs.getString("SDT"),
                    rs.getString("email"),
                    rs.getString("diaChi"),
                    rs.getInt("isDelete") // Lấy giá trị isDelete
                );
                list.add(ncc);
            }
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (con != null) {
                    con.close(); // Đảm bảo đóng kết nối
                }
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }

    // Thêm nhà cung cấp mới
    public void add(dtonhacungcap ncc) {
        String sql = "INSERT INTO nhacungcap (tenNhaCungCap, SDT, email, diaChi, isDelete) VALUES (?, ?, ?, ?, ?)";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ncc.getTenNhaCungCap());
            pst.setString(2, ncc.getSDT());
            pst.setString(3, ncc.getEmail());
            pst.setString(4, ncc.getDiaChi());
            pst.setInt(5, 0); // isDelete = 0 mặc định khi thêm mới
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Cập nhật nhà cung cấp
    public void update(dtonhacungcap ncc) {
        String sql = "UPDATE nhacungcap SET tenNhaCungCap = ?, SDT = ?, email = ?, diaChi = ? WHERE maNhaCungCap = ?";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ncc.getTenNhaCungCap());
            pst.setString(2, ncc.getSDT());
            pst.setString(3, ncc.getEmail());
            pst.setString(4, ncc.getDiaChi());
            pst.setInt(5, ncc.getMaNhaCungCap());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Xóa nhà cung cấp (đánh dấu isDelete = 1)
    public void delete(int maNhaCungCap) {
        String sql = "UPDATE nhacungcap SET isDelete = 1 WHERE maNhaCungCap = ?";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maNhaCungCap);
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Lấy tên nhà cung cấp theo mã
    public String getTenNhaCungCap(int maNhaCungCap) {
        String tenNhaCungCap = null;
        String sql = "SELECT tenNhaCungCap FROM nhacungcap WHERE maNhaCungCap = ? AND isDelete = 0";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maNhaCungCap);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tenNhaCungCap = rs.getString("tenNhaCungCap");
            }
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return tenNhaCungCap;
    }

    // Đếm số lượng nhà cung cấp
    public int getCountNhaCungCap() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM nhacungcap WHERE isDelete = 0";
        java.sql.Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daonhacungcap.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return count;
    }
    public boolean isNhaCungCapExists(String tenNhaCungCap, String SDT, String email) throws SQLException {
        java.sql.Connection con = connect.connection();
        String query = "SELECT COUNT(*) FROM nhacungcap WHERE TenNhaCungCap = ? OR SDT = ? OR Email = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, tenNhaCungCap);
            ps.setString(2, SDT);
            ps.setString(3, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Kiểm tra nếu số lượng lớn hơn 0
                }
            }
        }
        return false; // Không tìm thấy bản ghi trùng lặp
    }


    // Main method để kiểm tra
    public static void main(String[] args) {
        daonhacungcap dao = new daonhacungcap();

        System.out.println(dao.getById(1));

    }
}
