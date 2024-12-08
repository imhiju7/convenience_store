package bus;

import dao.daoluong;
import dto.dtochamcong;
import dto.dtoluong;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.Calendar;
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
    public boolean isExist(Date day) {
        ArrayList<dtoluong> list = getAllLuong();

        // Lấy tháng và năm từ đối tượng Date (ngày hiện tại)
        Calendar cal = Calendar.getInstance();
        cal.setTime(day); // Thiết lập ngày
        int currMonth = cal.get(Calendar.MONTH) + 1; // Tháng (0-based, cần +1 để đúng tháng thực tế)
        int currYear = cal.get(Calendar.YEAR); // Năm

        for (dtoluong luong : list) {
            Date dluong = luong.getNgayNhanLuong();

            // Lấy tháng và năm từ dluong
            Calendar calLuong = Calendar.getInstance();
            calLuong.setTime(dluong);
            int luongMonth = calLuong.get(Calendar.MONTH) + 1;
            int luongYear = calLuong.get(Calendar.YEAR);

            // So sánh tháng và năm
            if (luongMonth == currMonth && luongYear == currYear) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<dtoluong> getlistthang(int month,int year){
        ArrayList<dtoluong> list = getAllLuong();
        ArrayList<dtoluong> listluong = new ArrayList<>();
        for (dtoluong luong : list) {
            Date dluong = luong.getNgayNhanLuong();

            // Lấy tháng và năm từ dluong
            Calendar calLuong = Calendar.getInstance();
            calLuong.setTime(dluong);
            int luongMonth = calLuong.get(Calendar.MONTH) + 1;
            int luongYear = calLuong.get(Calendar.YEAR);

            // So sánh tháng và năm
            if (luongMonth == month && luongYear == year) {
                listluong.add(luong);
            }
        }
        return listluong;
    }

    // Xử lý nghiệp vụ tính lương thực lãnh (có thể áp dụng logic riêng)
    public double calculateLuong(double luongThucTe, double phuCap, double luongThuong, double khoanBaoHiem, double luongLamThem,double khoanTru) {
        return luongThucTe + luongLamThem + phuCap + luongThuong - khoanBaoHiem - khoanTru;
    }
    public double calculateThue(double luongThucTe, double phuCap, double luongThuong, double khoanBaoHiem, double luongLamThem,double khoanTru) {
        double Thuclanh = calculateLuong(luongThucTe, phuCap,luongThuong, khoanBaoHiem,luongLamThem,khoanTru);
        if(Thuclanh > 1000000){
            return Math.round(Thuclanh*10/100);
        }
        return 0;
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
