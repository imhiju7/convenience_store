package bus;

import dao.daochucnang;
import dto.dtochucnang;
import java.sql.SQLException;
import java.util.ArrayList;

public class buschucnang {

    private daochucnang daoCN = new daochucnang();
    public ArrayList<dtochucnang> dsCN;

    public buschucnang() {
        dsCN = getList(); // Khởi tạo danh sách khi tạo đối tượng buschucnang
    }

    // Lấy danh sách tất cả chức năng
    public ArrayList<dtochucnang> getList() {
        dsCN = daoCN.getlist(); // Lấy danh sách từ DAO
        return dsCN;
    }

    // Lấy danh sách chức năng theo mã danh mục và hiển thị tên danh mục
    public ArrayList<dtochucnang> getlistChucNangByDanhMuc(int maDanhMuc) throws SQLException {
        return daoCN.getlistChucNangByDanhMuc(); // Gọi phương thức từ DAO để lấy danh sách theo mã danh mục
    }

    // Thêm mới chức năng
    public void add(dtochucnang cn) throws SQLException {
        daoCN.add(cn); // Thêm chức năng mới
        getList(); // Cập nhật lại danh sách sau khi thêm
    }

    // Cập nhật chức năng
    public void updateChucNang(dtochucnang cn) throws SQLException {
        daoCN.update(cn); // Cập nhật chức năng
        getList(); // Cập nhật lại danh sách sau khi cập nhật
    }

    // Xóa chức năng
    public void deleteChucNang(int maChucNang) throws SQLException {
        daoCN.delete(maChucNang); // Xóa chức năng
        getList(); // Cập nhật lại danh sách sau khi xóa
    }

    // Lấy tên chức năng theo mã
    public String getTenChucNang(int maChucNang) throws SQLException {
        return daoCN.getTenChucNang(maChucNang); // Gọi phương thức từ DAO để lấy tên chức năng
    }

    // Đếm số lượng chức năng
    public int getSoLuongChucNang() throws SQLException {
        return daoCN.getCountChucNang(); // Gọi phương thức từ DAO để đếm số lượng chức năng
    }

    // Main method để kiểm tra
    public static void main(String[] args) throws SQLException {
        // Tạo một instance của lớp BUS
        buschucnang bus = new buschucnang();

        // In ra danh sách chức năng
        System.out.println("Danh sách chức năng:");
        for (dtochucnang cn : bus.getList()) {
            System.out.println("Mã: " + cn.getMaChucNang() + " - Tên: " + cn.getTenChucNang());
        }

        // Ví dụ: Lấy danh sách chức năng theo mã danh mục
        int maDanhMuc = 2; // Ví dụ mã danh mục
        System.out.println("\nDanh sách chức năng theo mã danh mục " + maDanhMuc + ":");
        for (dtochucnang cn : bus.getlistChucNangByDanhMuc(maDanhMuc)) {
            System.out.println("Mã: " + cn.getMaChucNang() + " - Tên: " + cn.getTenChucNang() + " - Danh mục: " + cn.getMaDanhMuc());
        }

        // In ra danh sách sau khi thêm
        System.out.println("\nSau khi thêm:");
        for (dtochucnang cn : bus.getList()) {
            System.out.println("Mã: " + cn.getMaChucNang() + " - Tên: " + cn.getTenChucNang());
        }
    }
}
