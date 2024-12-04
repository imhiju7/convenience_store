package bus;

import dao.daoluong;
import dto.dtoluong;
import java.util.ArrayList;
import java.util.Date;

public class busluong {
    private daoluong daoLuong;

    // Constructor
    public busluong() {
        this.daoLuong = new daoluong();
    }

    // Lấy danh sách tất cả bản ghi lương
    public ArrayList<dtoluong> getAllLuong() {
        return daoLuong.getList();
    }

    // Lấy thông tin lương theo ID
    public dtoluong getLuongById(int maLuong) {
        return daoLuong.getById(maLuong);
    }

    // Lấy danh sách lương theo khoảng thời gian
    public ArrayList<dtoluong> getLuongByTime(Date start, Date end) {
        return daoLuong.getByTime(start, end);
    }

    // Thêm một bản ghi lương mới
    public boolean addLuong(dtoluong luong) {
        try {
            daoLuong.add(luong);
            return true; // Thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Thêm thất bại
        }
    }

    // Cập nhật thông tin một bản ghi lương
    public boolean updateLuong(dtoluong luong) {
        try {
            daoLuong.update(luong);
            return true; // Cập nhật thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Cập nhật thất bại
        }
    }

    // Đếm số lượng bản ghi lương
    public int countLuong() {
        return daoLuong.countLuong();
    }

    // Xử lý nghiệp vụ tính lương thực lãnh (có thể áp dụng logic riêng)
    public double calculateThuclanh(double luongThucTe, double phuCap, double luongThuong, double khoanBaoHiem, double khoanThue) {
        return luongThucTe + phuCap + luongThuong - khoanBaoHiem - khoanThue;
    }

    // Kiểm tra hợp lệ trước khi thêm hoặc cập nhật lương
    public boolean validateLuong(dtoluong luong) {
        if (luong.getLuongThucTe() < 0 || luong.getPhuCap() < 0 || luong.getKhoanBaoHiem() < 0 || luong.getKhoanThue() < 0) {
            return false; // Các giá trị liên quan đến lương không được âm
        }
        if (luong.getMaNhanVien() <= 0) {
            return false; // Mã nhân viên không hợp lệ
        }
        return true;
    }

    // Xử lý thêm mới lương với kiểm tra hợp lệ
    public boolean addLuongWithValidation(dtoluong luong) {
        if (validateLuong(luong)) {
            return addLuong(luong);
        } else {
            System.out.println("Dữ liệu lương không hợp lệ!");
            return false;
        }
    }

    // Xử lý cập nhật lương với kiểm tra hợp lệ
    public boolean updateLuongWithValidation(dtoluong luong) {
        if (validateLuong(luong)) {
            return updateLuong(luong);
        } else {
            System.out.println("Dữ liệu lương không hợp lệ!");
            return false;
        }
    }

    // Tìm kiếm lương theo mã nhân viên
    public ArrayList<dtoluong> searchLuongByNhanVien(int maNhanVien) {
        ArrayList<dtoluong> allLuong = daoLuong.getList();
        ArrayList<dtoluong> result = new ArrayList<>();

        for (dtoluong luong : allLuong) {
            if (luong.getMaNhanVien() == maNhanVien) {
                result.add(luong);
            }
        }
        return result;
    }
}
