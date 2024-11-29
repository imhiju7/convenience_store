/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dto.thongke.*;
import dao.daothongke;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author AD
 */
public class busthongke {
    daothongke daotk = new daothongke();
   public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe8NgayGanNhat(){
       return daotk.doanhThu8NgayGanNhat();
   }
   public ArrayList<thongkedoanhthuDTO> getDoanhThuChiPhiSPbyPhanLoai(String tenPhanLoai, int nam) throws SQLException{
       return daotk.getDoanhThuChiPhiSPbyPhanLoai(tenPhanLoai, nam);
   }
   public int getOldestYear() {
       return daotk.getOldestYear();
   }
   public ArrayList<thongkedoanhthuDTO> getDoanhThuChiPhiTheoNam(String tenPhanLoai, int nam) throws SQLException {
       return daotk.getDoanhThuChiPhiTheoNam(tenPhanLoai, nam);
   }
}
