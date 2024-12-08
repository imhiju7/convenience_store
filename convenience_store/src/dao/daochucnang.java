package dao;

import dto.dtochucnang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daochucnang {
    public ArrayList<dtochucnang> getListChucNangByDanhMuc(int maDanhMuc) {
    ArrayList<dtochucnang> chucNangList = new ArrayList<>();
    // Cập nhật câu truy vấn để chỉ lấy những chức năng chưa bị xóa (isDelete = 0)
    String sql = "SELECT maChucNang, tenChucNang, maDanhMuc FROM chucnang WHERE maDanhMuc = ? AND isDelete = 0";
    Connection con = connect.connection();

    try {
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maDanhMuc);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int maChucNang = rs.getInt("maChucNang");
            String tenChucNang = rs.getString("tenChucNang");
            int maDanhMucResult = rs.getInt("maDanhMuc");
            int isDelete = 0;
            // Tạo đối tượng DtoChucNang và thêm vào danh sách
            dtochucnang chucNang = new dtochucnang(maChucNang, tenChucNang, maDanhMucResult, isDelete);
            chucNangList.add(chucNang);
        }
    } catch (SQLException e) {
        Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    return chucNangList;
}

    // Lấy thông tin chức năng theo ID (chỉ lấy bản ghi chưa bị xóa)
    public dtochucnang getById(int maChucNang) {
        dtochucnang chucNang = null;
        Connection con = connect.connection();
        String sql = "SELECT * FROM chucnang WHERE maChucNang = ? AND isDelete = 0";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maChucNang);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                chucNang = new dtochucnang(
                        rs.getInt("maChucNang"),
                        rs.getString("tenChucNang"),
                        rs.getInt("maDanhMuc"),
                        rs.getInt("isDelete") // Sử dụng kiểu int
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return chucNang;
    }

    
    // Thêm mới chức năng
    public void add(dtochucnang chucNang) {
        String sql = "INSERT INTO chucnang (tenChucNang, maDanhMuc, isDelete) VALUES (?, ?, 0)";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, chucNang.getTenChucNang());
            pst.setInt(2, chucNang.getMaDanhMuc());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Cập nhật chức năng
    public void update(dtochucnang chucNang) {
        String sql = "UPDATE chucnang SET tenChucNang = ?, maDanhMuc = ?, isDelete = ? WHERE maChucNang = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, chucNang.getTenChucNang());
            pst.setInt(2, chucNang.getMaDanhMuc());
            pst.setInt(3, chucNang.getIsDelete()); // Thay boolean bằng int
            pst.setInt(4, chucNang.getMaChucNang());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Đánh dấu xóa chức năng (soft delete)
    public void delete(int maChucNang) {
        String sql = "UPDATE chucnang SET isDelete = 1 WHERE maChucNang = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maChucNang);
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    public String getTenDanhMucById(int maDanhMuc) {
        String tenDanhMuc = "";
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
            Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daoluong.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return tenDanhMuc;
    }

    // Lấy danh sách chưa bị xóa
    public ArrayList<dtochucnang> getList() {
        ArrayList<dtochucnang> list = new ArrayList<>();
        Connection con = connect.connection();
        String sql = "SELECT * FROM chucnang WHERE isDelete = 0";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                dtochucnang chucNang = new dtochucnang(
                        rs.getInt("maChucNang"),
                        rs.getString("tenChucNang"),
                        rs.getInt("maDanhMuc"),
                        rs.getInt("isDelete") // Sử dụng kiểu int
                );
                list.add(chucNang);
            }
        } catch (SQLException e) {
            Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochucnang.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }
}
