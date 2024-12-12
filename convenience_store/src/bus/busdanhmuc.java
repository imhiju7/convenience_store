package bus;

import dao.daodanhmuc;
import dto.dtodanhmuc;
import java.sql.SQLException;
import java.util.ArrayList;

public class busdanhmuc {
    private daodanhmuc daoDM = new daodanhmuc();
    public ArrayList<dtodanhmuc> dsDM;

    public busdanhmuc() {
        getlist();
    }

    // Lấy danh sách tất cả danh mục
    public ArrayList<dtodanhmuc> getlist() {
        dsDM = daoDM.getlist();
        return dsDM;
    }

    // Thêm mới danh mục
    public void add(dtodanhmuc dm) {
        daoDM.add(dm);
        getlist(); // Cập nhật lại danh sách sau khi thêm
    }

    // Cập nhật danh mục
    public void updateDanhMuc(dtodanhmuc dm) throws SQLException {
        daoDM.update(dm); // Gọi phương thức cập nhật từ DAO
        getlist(); // Cập nhật lại danh sách sau khi cập nhật
    }

    // Xóa danh mục
    public void deleteDanhMuc(int maDanhMuc) throws SQLException {
        daoDM.delete(maDanhMuc); // Gọi phương thức xóa từ DAO
        getlist(); // Cập nhật lại danh sách sau khi xóa
    }

    // Lấy tên danh mục theo mã
    public String getTenDanhMuc(int maDanhMuc) throws SQLException {
        return daoDM.getTenDanhMuc(maDanhMuc); // Gọi phương thức từ DAO để lấy tên danh mục
    }

    // Đếm số lượng danh mục
    public int getSoLuongDanhMuc() throws SQLException {
        return daoDM.getCountDanhMuc(); // Gọi phương thức từ DAO để đếm số lượng danh mục
    }
    public boolean isDanhMucExists(String tenDanhMuc) throws SQLException {
    // Gọi lớp DAO để kiểm tra trong cơ sở dữ liệu
    return daoDM.checkDanhMucExists(tenDanhMuc);
}

    // Main method để kiểm tra
    public static void main(String[] args) {
        // Tạo một instance của lớp BUS
        busdanhmuc bus = new busdanhmuc();

        // In ra danh sách danh mục
        for (dtodanhmuc dm : bus.getlist()) {
            System.out.println(dm);
        }

      
    }
}
