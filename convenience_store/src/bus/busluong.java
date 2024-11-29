package bus;

import dao.daoluong;
import dto.dtoluong;
import java.sql.SQLException;
import java.util.ArrayList;


public class busluong {
    public ArrayList<dtoluong> dsLuong; // Danh sách lương
    private daoluong daoLuong = new daoluong();

    // Lấy thông tin lương theo mã
    public dtoluong getById(int maLuong) {
        return daoLuong.getById(maLuong);
    }

    // Lấy tất cả các bản ghi lương thông qua DAO
    public ArrayList<dtoluong> list() {
        return daoLuong.getList();
    }

    // Constructor khởi tạo busluong và lấy danh sách lương
    public busluong() {
        getlist(); // Lấy danh sách khi khởi tạo
    }

    // Lấy danh sách lương từ DAO
    public void getlist() {
        dsLuong = daoLuong.getList();
    }

    // Phương thức thêm mới thông tin lương
    public void add(dtoluong luong) {
        daoLuong.add(luong); // Thêm thông tin lương qua DAO
        getlist(); // Cập nhật danh sách sau khi thêm
    }

    // Phương thức cập nhật thông tin lương
    public void update(dtoluong luong) {
        daoLuong.update(luong); // Cập nhật thông tin qua DAO
        getlist(); // Cập nhật lại danh sách sau khi cập nhật
    }

    // Phương thức xóa thông tin lương theo mã
    public void delete(int maLuong) {
        daoLuong.delete(maLuong); // Xóa bản ghi thông qua DAO
        getlist(); // Cập nhật danh sách sau khi xóa
    }

    // Phương thức đếm tổng số bản ghi lương
    public int getSoLuongLuong() throws SQLException {
        return daoLuong.countLuong(); // Gọi phương thức đếm từ DAO
    }

    // Phương thức lấy lương theo mã từ danh sách đã tải
    public dtoluong get(int maLuong) {
        for (dtoluong luong : dsLuong) {
            if (luong.getMaLuong() == maLuong) {
                return luong; // Trả về thông tin lương nếu tìm thấy
            }
        }
        return null; // Không tìm thấy
    }

    // Phương thức main để kiểm tra chức năng
    public static void main(String[] args) {
        // Tạo một instance của busluong
        busluong bus = new busluong();

        // In ra danh sách lương
        System.out.println("Danh sách lương:");
        for (dtoluong luong : bus.dsLuong) {
            System.out.println(luong);
        }

        // Thêm một bản ghi lương mới (ví dụ dữ liệu minh họa)
        dtoluong newLuong = new dtoluong(0, 2, 500000, 15000000, 2000000, 500000, 1000000, 13500000, 5, "2024-11-28", 1);
        bus.add(newLuong);

        // In lại danh sách sau khi thêm
        System.out.println("Danh sách lương sau khi thêm:");
        for (dtoluong luong : bus.dsLuong) {
            System.out.println(luong);
        }

        // Lấy thông tin lương theo mã
        int maLuong = 1; // Ví dụ mã cần tìm
        dtoluong luongTimThay = bus.getById(maLuong);
        if (luongTimThay != null) {
            System.out.println("Thông tin lương có mã " + maLuong + ":");
            System.out.println(luongTimThay);
        } else {
            System.out.println("Không tìm thấy lương có mã " + maLuong);
        }
    }
}
