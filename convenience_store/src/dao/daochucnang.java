package dao;

import dto.dtochucnang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daochucnang {

    // Thêm chức năng mới
    public void add(dtochucnang chucnang) throws SQLException {
        Connection con = connect.connection(); // Kết nối đến cơ sở dữ liệu
        String sql = "INSERT INTO chucnang (maChucNang, tenChucNang, maDanhMuc, isDelete) VALUES (?, ?, ?, 0)"; // isDelete mặc định là 0

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, chucnang.getMaChucNang());
            pst.setString(2, chucnang.getTenChucNang());
            pst.setInt(3, chucnang.getMaDanhMuc());
            pst.executeUpdate();
        } finally {
            if (con != null) {
                con.close(); // Đóng kết nối
            }
        }
    }

    // Cập nhật thông tin chức năng
    public void update(dtochucnang chucnang) throws SQLException {
        String sql = "UPDATE chucnang SET tenChucNang = ?, maDanhMuc = ? WHERE maChucNang = ? AND isDelete = 0";
        Connection con = connect.connection();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, chucnang.getTenChucNang()); // Gán giá trị cho tên chức năng
            pst.setInt(2, chucnang.getMaDanhMuc()); // Gán giá trị cho mã danh mục
            pst.setInt(3, chucnang.getMaChucNang()); // Gán giá trị cho mã chức năng

            int rowsAffected = pst.executeUpdate(); // Thực hiện câu lệnh cập nhật
            if (rowsAffected > 0) {
                System.out.println("Cập nhật chức năng thành công!");
            } else {
                System.out.println("Không tìm thấy chức năng với mã đã cho hoặc bản ghi đã bị xóa.");
            }
        } finally {
            if (con != null) {
                con.close(); // Đảm bảo đóng kết nối
            }
        }
    }

    // Xóa chức năng
    public void delete(int maChucNang) throws SQLException {
        String sql = "UPDATE chucnang SET isDelete = 1 WHERE maChucNang = ?";
        Connection con = connect.connection();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, maChucNang); // Gán giá trị cho mã chức năng

            int rowsAffected = pst.executeUpdate(); // Thực hiện câu lệnh xóa mềm
            if (rowsAffected > 0) {
                System.out.println("Xóa chức năng thành công!");
            } else {
                System.out.println("Không tìm thấy chức năng với mã đã cho.");
            }
        } finally {
            if (con != null) {
                con.close(); // Đảm bảo đóng kết nối
            }
        }
    }

    // Lấy danh sách chức năng
    public ArrayList<dtochucnang> getlist() {
        ArrayList<dtochucnang> list = new ArrayList<>();
        Connection con = connect.connection();
        String sql = "SELECT * FROM chucnang WHERE isDelete = 0"; // Lấy các bản ghi chưa bị xóa

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dtochucnang cn = new dtochucnang(
                        rs.getInt("maChucNang"),
                        rs.getString("tenChucNang"),
                        rs.getInt("maDanhMuc"),
                        rs.getInt("isDelete")
                );
                list.add(cn);
            }
        } catch (SQLException e) {
            Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (con != null) {
                    con.close(); // Đảm bảo đóng kết nối
                }
            } catch (SQLException ex) {
                Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    // Lấy tên chức năng theo mã chức năng
   public String getTenDanhMuc(int maDanhMuc) {
    String tenDanhMuc = null;
    Connection con = connect.connection(); // Kết nối cơ sở dữ liệu
    String sql = "SELECT tenDanhMuc FROM danhmuc WHERE maDanhMuc = ?"; // Câu truy vấn SQL

    try {
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maDanhMuc); // Gán giá trị mã danh mục cho tham số
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            tenDanhMuc = rs.getString("tenDanhMuc"); // Lấy tên danh mục từ kết quả
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Xử lý ngoại lệ
    } finally {
        try {
            if (con != null) {
                con.close(); // Đóng kết nối
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ khi đóng kết nối
        }
    }
    return tenDanhMuc; // Trả về tên danh mục
}
    public String getTenChucNang(int macn){
        Connection con = connect.connection();
        String sql = "SELECT tenChucNang FROM chucnang WHERE maChucNang = ?";
        String tenChucNang = null;
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, macn); // Gán giá trị mã danh mục cho tham số
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tenChucNang = rs.getString("tenChucNang"); // Lấy tên danh mục từ kết quả
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        } finally {
            try {
                if (con != null) {
                    con.close(); // Đóng kết nối
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Xử lý ngoại lệ khi đóng kết nối
            }
        }
        return tenChucNang; // Trả về tên danh mục
    }
    // Lấy số lượng chức năng
    public int getCountChucNang() {
        int count = 0;
        Connection con = connect.connection();
        String sql = "SELECT COUNT(*) FROM chucnang"; // Truy vấn SQL để đếm số lượng

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
        return count; // Trả về số lượng chức năng
    }

    // Lấy tên chức năng theo mã danh mục
    public ArrayList<dtochucnang> getlistChucNangByDanhMuc(int madm) throws SQLException {
        ArrayList<dtochucnang> listChucNang = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = connect.connection(); // Kết nối DB
            String sql = "SELECT c.maChucNang, c.tenChucNang, c.maDanhMuc, d.tenDanhMuc "
                       + "FROM chucnang c "
                       + "INNER JOIN danhmuc d ON c.maDanhMuc = d.maDanhMuc "
                       + "WHERE c.isDelete = 0 and c.maDanhMuc = "+madm;  // Lọc các chức năng chưa bị xóa

            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            // Lấy dữ liệu từ ResultSet và lưu vào danh sách
            while (rs.next()) {
                dtochucnang cn = new dtochucnang(
                    rs.getInt("maChucNang"),
                    rs.getString("tenChucNang"),
                    rs.getInt("maDanhMuc"), // Lấy mã danh mục
                    0 // isDelete không cần thiết nếu bạn không cần sử dụng
                );
                listChucNang.add(cn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng kết nối và các đối tượng sau khi sử dụng
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listChucNang;
    }



    // Hàm lấy tên danh mục từ mã danh mục
    public String getTenDanhMucByMa(int maDanhMuc) throws SQLException {
        String tenDanhMuc = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = connect.connection();
            String sql = "SELECT tenDanhMuc FROM danhmuc WHERE maDanhMuc = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, maDanhMuc); // Đặt mã danh mục vào câu truy vấn

            rs = pst.executeQuery();
            if (rs.next()) {
                tenDanhMuc = rs.getString("tenDanhMuc"); // Lấy tên danh mục từ ResultSet
            } else {
                System.out.println("Không tìm thấy tên danh mục với mã: " + maDanhMuc); // Debug nếu không có dữ liệu
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return tenDanhMuc; // Trả về tên danh mục
    }
}
