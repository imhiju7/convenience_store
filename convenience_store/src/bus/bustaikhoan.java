package bus;

import dao.daotaikhoan;
import dto.dtotaikhoan;
import dao.daonhanvien;
import dto.dtonhanvien;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PHUONG ANH
 */
public class bustaikhoan {
    daotaikhoan daotk = new daotaikhoan();
    daonhanvien daonv = new daonhanvien();
    
    public boolean updatematkhau(String tendangnhap, String matkhau){
        return daotk.updatematkhau(tendangnhap, matkhau);
    }
    public boolean updateTaiKhoan(int manhanvien, String tendangnhap, String matkhau, int isblock) {
        daotaikhoan dao = new daotaikhoan();
        return dao.update(manhanvien, tendangnhap, matkhau, isblock);
    }

    // check

    public boolean checktendangnhap(String tendangnhap) {
        return daotk.checktendangnhap(tendangnhap);
    }

    public boolean checkmatkhau(String tendangnhap, String matkhau) {
        return daotk.checkmatkhau(tendangnhap, matkhau);
    }

    public boolean checktaikhoanbikhoa(String tendangnhap) {
        return daotk.checktaikhoanbikhoa(tendangnhap);
    }

    // get
    public String getTenDangNhap(int maNhanVien) {
    return daotk.getTenDangNhap(maNhanVien);
}
    public String getMatKhau(int maNhanVien) {
    return daotk.getMatKhau(maNhanVien);
}

public Date getNgayTao(int maNhanVien) {
    return daotk.getNgayTao(maNhanVien);
}

public int getIsBlock(int maNhanVien) {
    return daotk.getIsBlock(maNhanVien);
}
public boolean getIsBlockedByMaNhanVien(int manhanvien) {
    try {
        return daotk.getIsBlockedByMaNhanVien(manhanvien);  // Gọi phương thức trong DAO
    } catch (SQLException e) {
        e.printStackTrace();
        return false;  // Trả về false nếu có lỗi xảy ra trong quá trình truy vấn
    }
}

    public ArrayList<dtotaikhoan> getlist(){
        return daotk.getlist();
    }
    
    public String getemail(String tendangnhap) {
        return daonv.getemail(daotk.getmanhanvien(tendangnhap));
    }
    public boolean checkemailexist (String email) {
        return daonv.checkemailexist(email);
    }
    public String gettennv(String email) {
        return daonv.gettennvbyemail(email);
    }
    public int getmanhanvien(String tendangnhap) {
        return daotk.getmanhanvien(tendangnhap);
    }
}
