/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.dtophanquyen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hieu PC
 */
public class daophanquyen {

    // get
    // check
    public boolean checkphanquyen(int macv, int macn) {
        Connection con = connect.connection();
        String sql = "SELECT * FROM phanquyen WHERE maChucVu = ? and maChucNang = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, macv);
            pst.setInt(2, macn);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {

        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daophanquyen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    


    public void add(dtophanquyen phanQuyen) throws SQLException {
        Connection con = connect.connection();
        String sql = "INSERT INTO PhanQuyen(maChucVu, maChucNang) VALUES(?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, phanQuyen.getMaChucVu());
            stmt.setInt(2, phanQuyen.getMaChucNang());
            stmt.executeUpdate();
        }
    }

    public void deleteByChucVu(int maChucVu) throws SQLException {
        Connection con = connect.connection();
        String sql = "DELETE FROM PhanQuyen WHERE maChucVu = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, maChucVu);
            stmt.executeUpdate();
        }
    }

    public void update(dtophanquyen phanQuyen) throws SQLException {
        Connection con = connect.connection();
        String sql = "UPDATE PhanQuyen SET maChucVu = ?, maChucNang = ? WHERE maPhanQuyen = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, phanQuyen.getMaChucVu());
            stmt.setInt(2, phanQuyen.getMaChucNang());
            stmt.setInt(3, phanQuyen.getMaPhanQuyen());
            stmt.executeUpdate();
        }
    }

    public dtophanquyen getById(int maPhanQuyen) {
        Connection con = connect.connection();
        dtophanquyen phanQuyen = null;
        String sql = "SELECT * FROM PhanQuyen WHERE maPhanQuyen = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, maPhanQuyen);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                phanQuyen = new dtophanquyen(
                         rs.getInt("maPhanQuyen"),
                         rs.getInt("maChucVu"),
                         rs.getInt("maChucNang")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phanQuyen;
    }

    public String getTenChucVuById(int maChucVu) {
        String tenChucVu = "";
        String sql = "SELECT tenChucVu FROM chucvu WHERE maChucVu = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maChucVu);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tenChucVu = rs.getString("tenChucVu");
            }
        } catch (SQLException e) {
            Logger.getLogger(daochucvu.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daochucvu.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return tenChucVu;
    }

    public String getTenChucNangById(int maChucNang) {
        String tenChucNang = "";
        String sql = "SELECT tenChucNang FROM chucnang WHERE maChucNang = ?";
        Connection con = connect.connection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maChucNang);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tenChucNang = rs.getString("tenChucNang");
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
        return tenChucNang;
    }

    // Lấy phân quyền theo chức vụ
    public List<dtophanquyen> getByChucVu(int maChucVu, int maPhanQuyen) throws SQLException {
        Connection con = connect.connection();
        List<dtophanquyen> phanQuyenList = new ArrayList<>();
        String sql = "SELECT * FROM PhanQuyen WHERE maChucVu = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, maChucVu);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dtophanquyen phanQuyen = new dtophanquyen(
                             rs.getInt("maPhanQuyen"),
                             rs.getInt("maChucVu"),
                             rs.getInt("maChucNang")
                    );
                    phanQuyenList.add(phanQuyen);
                }
            }
        }
        return phanQuyenList;
    }

    public ArrayList<dtophanquyen> getList() {
        ArrayList<dtophanquyen> list = new ArrayList<>();
        Connection con = connect.connection();
        String sql = "SELECT * FROM phanquyen";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Tạo đối tượng dtophanquyen từ kết quả truy vấn
                dtophanquyen phanQuyen = new dtophanquyen(
                         rs.getInt("maPhanQuyen"), // Mã phân quyền
                         rs.getInt("maChucVu"), // Mã chức vụ
                         rs.getInt("maChucNang") // Mã chức năng
                );
                list.add(phanQuyen);
            }
        } catch (SQLException e) {
            Logger.getLogger(daophanquyen.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(daophanquyen.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return list;
    }

}
