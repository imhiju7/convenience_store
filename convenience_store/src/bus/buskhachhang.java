package bus;

import dao.daokhachhang;
import dto.dtokhachhang;
import java.sql.SQLException;
import java.util.ArrayList;

public class buskhachhang {
    private daokhachhang daoKhachHang;

    public buskhachhang() {
        this.daoKhachHang = new daokhachhang();
    }

    // Phương thức lấy tất cả khách hàng
    public ArrayList<dtokhachhang> getAllKhachHang() {
        return daoKhachHang.getAllKhachHang(); // Gọi từ lớp daoKhachHang
    }

    // Phương thức thêm khách hàng
    public boolean addKhachHang(dtokhachhang khachHang) {
        return daoKhachHang.addKhachHang(khachHang);  // Gọi phương thức thêm từ DAO
    }

    // Kiểm tra số điện thoại có tồn tại không
    public boolean checkSDTExist(String sdt) {
        return daoKhachHang.checkSDTExist(sdt);  // Kiểm tra nếu số điện thoại đã tồn tại
    }

    // Cập nhật thông tin khách hàng
    public boolean updateKhachHang(dtokhachhang khachHang) throws SQLException {
        return daoKhachHang.updateKhachHang(khachHang);  // Gọi phương thức cập nhật từ DAO
    }

    // Lấy số lượng khách hàng
    public int getSoLuongKH() {
        return daoKhachHang.getSoLuongKH();
    }

    // Cập nhật điểm tích lũy
    public int updatediemtichluy(dtokhachhang khachHang) {
        return daoKhachHang.updatediemtichluy(khachHang);  // Gọi phương thức cập nhật điểm tích lũy
    }

    // Lấy khách hàng theo số điện thoại
    public dtokhachhang getkhbyphone(String phone) {
        return daoKhachHang.getkhbyphone(phone);  // Lấy khách hàng theo số điện thoại
    }

    // Kiểm tra số điện thoại hợp lệ
    public boolean checkphone(String phone) {
        return daoKhachHang.checkphone(phone);  // Kiểm tra số điện thoại
    }

    // Lấy mã khách hàng tiếp theo
    public int getNextCustomerCode() {
        return daoKhachHang.getNextCustomerCode();  // Lấy mã khách hàng tiếp theo
    }

    // Lấy khách hàng theo ID
    public dtokhachhang getKhachHangById(int maKhachHang) {
        return daoKhachHang.getKhachHangById(maKhachHang);  // Lấy khách hàng theo ID
    }
public ArrayList<dtokhachhang> searchKhachHangByName(String name) {
    return daoKhachHang.searchKhachHangByName(name);
}
public ArrayList<dtokhachhang> searchKhachHangBySDT(String sdt) {
    return daoKhachHang.searchKhachHangBySDT(sdt);
}

//    // Xóa khách hàng
//    public boolean deleteKhachHang(int maKhachHang) {
//        return daoKhachHang.deleteKhachHang(maKhachHang);  // Gọi phương thức xóa từ DAO
//    }
}
