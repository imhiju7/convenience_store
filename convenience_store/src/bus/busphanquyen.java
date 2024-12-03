/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.daophanquyen;
import dto.dtophanquyen;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hieu PC
 */
public class busphanquyen {
    daophanquyen daopq = new daophanquyen();

    // Kiểm tra phân quyền
    public boolean checkphanquyen(int macv, int macn) {
        return daopq.checkphanquyen(macv, macn);
    }

    // Thêm phân quyền
    public boolean addPhanQuyen(dtophanquyen phanQuyen) {
        try {
            daopq.add(phanQuyen);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(busphanquyen.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    // Cập nhật phân quyền
    public boolean updatePhanQuyen(dtophanquyen phanQuyen) {
        try {
            daopq.update(phanQuyen);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(busphanquyen.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    // Xóa phân quyền theo mã chức vụ
    public boolean deletePhanQuyenByChucVu(int maChucVu) {
        try {
            daopq.deleteByChucVu(maChucVu);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(busphanquyen.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    // Lấy danh sách tất cả phân quyền
    public ArrayList<dtophanquyen> getAllPhanQuyen() {
        return daopq.getList();
    }

    // Lấy phân quyền theo mã
    public dtophanquyen getPhanQuyenById(int maPhanQuyen) {
        return daopq.getById(maPhanQuyen);
    }

    // Lấy tên chức vụ theo mã
    public String getTenChucVuById(int maChucVu) {
        return daopq.getTenChucVuById(maChucVu);
    }

    // Lấy tên chức năng theo mã
    public String getTenChucNangById(int maChucNang) {
        return daopq.getTenChucNangById(maChucNang);
    }

    // Lấy danh sách phân quyền theo chức vụ
    public List<dtophanquyen> getPhanQuyenByChucVu(int maChucVu,int maPhanQuyen) {
        try {
            return daopq.getByChucVu(maChucVu, maPhanQuyen);
        } catch (SQLException e) {
            Logger.getLogger(busphanquyen.class.getName()).log(Level.SEVERE, null, e);
        }
        return new ArrayList<>();
    }
}

