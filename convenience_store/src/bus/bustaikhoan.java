package bus;

import dao.daotaikhoan;
import dto.dtotaikhoan;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author PHUONG ANH
 */
public class bustaikhoan {
    daotaikhoan DAO = new daotaikhoan();

    public ArrayList<dtotaikhoan> getlist() throws SQLException, ParseException {
        return DAO.getList();
    }

    public dtotaikhoan kiemTraTaiKhoan(String tenDangNhap, String matKhau) {
        return DAO.kiemTraTaiKhoan(tenDangNhap, matKhau);
    }

    public boolean checkTenDangNhap(String tenDangNhap) {
        return DAO.checkTenDangNhap(tenDangNhap);
    }

    public boolean checkMatKhau(String tenDangNhap, String matKhau) {
        return DAO.checkMatKhau(tenDangNhap, matKhau);
    }

    public boolean checkKhoaTaiKhoan(String tenDangNhap) {
        return DAO.checkKhoaTaiKhoan(tenDangNhap);
    }

    public String getEmail(String tenDangNhap) {
        return DAO.getEmail(tenDangNhap);
    }

    
}
