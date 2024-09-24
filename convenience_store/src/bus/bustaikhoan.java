package bus;

import dao.daotaikhoan;
import dto.dtotaikhoan;
import dao.daonhanvien;
import dto.dtonhanvien;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

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
    
    public ArrayList<dtotaikhoan> getdstk(){
        return daotk.getdstk();
    }
    
    public String getemail(String tendangnhap) {
        return daonv.getemail(daotk.getmanhanvien(tendangnhap));
    }
    public boolean emailExist (String email) {
        return daonv.emailExist(email);
    }
    public String getTenNV(String email) {
        return daonv.getTenNV(email);
    }
    public int getmanhanvien(String tendangnhap) {
        return daotk.getmanhanvien(tendangnhap);
    }
}
