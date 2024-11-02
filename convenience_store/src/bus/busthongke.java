/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dto.thongke.*;
import dao.daothongke;
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
}
