package bus;

import dao.daophanloai;
import dto.dtophanloai;
import java.sql.SQLException;
import java.util.ArrayList;

public class busphanloai {

    private daophanloai daoPL = new daophanloai();
    public ArrayList<dtophanloai> dsPL;

    public busphanloai() {
        getlist();
    }

    // Lấy danh sách tất cả phân loại
    public ArrayList<dtophanloai> getlist() {
        dsPL = daoPL.getlist();
        return dsPL;
    }

    // Thêm mới phân loại
    public void add(dtophanloai pl) {
        daoPL.add(pl);
        getlist(); // Cập nhật lại danh sách sau khi thêm
    }

    // Cập nhật phân loại
    public void updatePhanLoai(dtophanloai pl) throws SQLException {
        daoPL.update(pl); // Gọi phương thức cập nhật từ DAO
        getlist(); // Cập nhật lại danh sách sau khi cập nhật
    }

    // Xóa phân loại
    public void deletePhanLoai(int maPhanLoai) throws SQLException {
        daoPL.delete(maPhanLoai); // Gọi phương thức xóa từ DAO
        getlist(); // Cập nhật lại danh sách sau khi xóa
    }

    // Lấy tên phân loại theo mã
    public String getTenPhanLoai(int maPhanLoai) throws SQLException {
        return daoPL.getTenPhanLoai(maPhanLoai); // Gọi phương thức từ DAO để lấy tên phân loại
    }

    // Đếm số lượng phân loại
    public int getSoLuongPhanLoai() throws SQLException {
        return daoPL.getCountPhanLoai(); // Gọi phương thức từ DAO để đếm số lượng phân loại
    }

    public boolean checkTenPhanLoaiExists(String tenPhanLoai) throws SQLException {
        return daoPL.checkTenPhanLoaiExists(tenPhanLoai); // Gọi phương thức trong DAO để kiểm tra tên phân loại
    }

    // Main method để kiểm tra
    public static void main(String[] args) {
        // Tạo một instance của lớp BUS
        busphanloai bus = new busphanloai();

        // In ra danh sách phân loại
        for (dtophanloai pl : bus.getlist()) {
            System.out.println(pl);
        }

    }
}
