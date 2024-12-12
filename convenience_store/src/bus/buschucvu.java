/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.daochucvu;
import dto.dtochucvu;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hieu PC
 */
public class buschucvu {

    daochucvu daoCV = new daochucvu();
    public ArrayList<dtochucvu> list_cv;

    public buschucvu() throws SQLException {
        this.daoCV = new daochucvu();
        getList();
    }

    // Lấy danh sách tất cả các chức vụ
    public ArrayList<dtochucvu> getList() throws SQLException {
        list_cv = daoCV.getlist();
        return list_cv;
    }

    // Thêm một chức vụ mới
    public void addchucvu(dtochucvu cv) throws SQLException {
        daoCV.add(cv);
        getList();
    }

    // Cập nhật một chức vụ
    public void updateChucVu(dtochucvu cv) throws SQLException {
        daoCV.update(cv); // Gọi phương thức cập nhật từ DAO
        getList(); // Cập nhật lại danh sách sau khi cập nhật
    }

    // Xóa một chức vụ
    public void deleteChucVu(int maChucVu) throws SQLException {
        daoCV.delete(maChucVu); // Gọi phương thức xóa từ DAO
        getList(); // Cập nhật lại danh sách sau khi xóa
    }

    // Lấy tên chức vụ theo mã
    public String getTenChucVu(int maChucVu) throws SQLException {
        return daoCV.getTenChucVu(maChucVu); // Gọi phương thức trong DAO để lấy tên chức vụ
    }

    // Lấy danh sách chức vụ
    public ArrayList<dtochucvu> listChucVu() throws SQLException {
        return daoCV.getlist(); // Gọi phương thức trong DAO để lấy danh sách
    }

    // Main method để kiểm tra
    public static void main(String[] args) throws SQLException {
        buschucvu bus = new buschucvu();
        for (dtochucvu cv : bus.getList()) {
            System.out.println(cv); // In ra danh sách chức vụ
        }
    }

    public int getSoLuongChucVu() throws SQLException {
        // Giả sử bạn có một phương thức trong dao để đếm số lượng chức vụ
        return daoCV.getCountChucVu(); // Gọi phương thức để lấy số lượng chức vụ từ dao
    }

    public String gettencvbymacv(int macv) {
        return daoCV.gettencvbymacv(macv);
    }
    // Kiểm tra tên chức vụ đã tồn tại hay chưa

    public boolean isTenChucVuExists(String tenChucVu) throws SQLException {
        for (dtochucvu cv : list_cv) {
            if (cv.getTenchucvu().equalsIgnoreCase(tenChucVu)) {
                return true;
            }
        }
        return false;
    }

}
