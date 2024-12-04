package bus;

import dao.daochucnang;
import dto.dtochucnang;
import java.util.ArrayList;

public class buschucnang {

    private final daochucnang dao;

    public buschucnang() {
        dao = new daochucnang();
    }

    // Lấy danh sách các chức năng chưa bị xóa
    public ArrayList<dtochucnang> getList() {
        return dao.getList();
    }
    public ArrayList<dtochucnang> getlistChucNangByDanhMuc(int maDanhMuc)  {
        return dao.getListChucNangByDanhMuc(maDanhMuc); // Gọi phương thức từ DAO để lấy danh sách theo mã danh mục
    }

    // Lấy thông tin chức năng theo mã chức năng
    public dtochucnang getById(int maChucNang) {
        return dao.getById(maChucNang);
    }

    // Thêm mới một chức năng
    public boolean add(dtochucnang chucNang) {
        if (chucNang.getTenChucNang().isEmpty()) {
            System.out.println("Tên chức năng không được để trống.");
            return false;
        }
        dao.add(chucNang);
        return true;
    }

    // Cập nhật thông tin chức năng
    public boolean update(dtochucnang chucNang) {
        if (chucNang.getTenChucNang().isEmpty()) {
            System.out.println("Tên chức năng không được để trống.");
            return false;
        }
        dao.update(chucNang);
        return true;
    }

    // Xóa mềm một chức năng bằng cách đặt isDelete = 1
    public boolean delete(int maChucNang) {
        dtochucnang chucNang = dao.getById(maChucNang);
        if (chucNang == null) {
            System.out.println("Không tìm thấy chức năng với mã: " + maChucNang);
            return false;
        }
        dao.delete(maChucNang);
        return true;
    }

    // Tìm kiếm chức năng theo tên
    public ArrayList<dtochucnang> searchByName(String tenChucNang) {
        ArrayList<dtochucnang> result = new ArrayList<>();
        for (dtochucnang chucNang : dao.getList()) {
            if (chucNang.getTenChucNang().toLowerCase().contains(tenChucNang.toLowerCase())) {
                result.add(chucNang);
            }
        }
        return result;
    }
}
