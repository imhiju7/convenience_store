/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import dto.thongke.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
public class daothongke {
    public static daothongke getInstance() {
        return new daothongke();
    }
    public ArrayList<ThongKeTungNgayTrongThangDTO> doanhThu8NgayGanNhat () {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
         String sql = """
                     WITH RECURSIVE dates(date) AS (
                         SELECT DATE_SUB(CURDATE(), INTERVAL 7 DAY)
                         UNION ALL
                         SELECT DATE_ADD(date, INTERVAL 1 DAY)
                         FROM dates
                         WHERE date < CURDATE()
                     )
                     SELECT 
                         dates.date AS ngay,
                         
                         -- Tính tổng doanh thu từ bảng hoadon
                         (SELECT COALESCE(SUM(tongTien), 0) 
                          FROM hoadon 
                          WHERE DATE(ngayMua) = dates.date AND isDelete = 0) AS doanhthu,
                     
                         -- Tính tổng chi phí từ bảng phieunhap
                         (SELECT COALESCE(SUM(tongTien), 0) 
                          FROM phieunhap 
                          WHERE DATE(ngayNhap) = dates.date AND isDelete = 0) AS chiphi
                     FROM dates
                     ORDER BY dates.date;
                     """;
         try {
            Connection con = connect.connection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int doanhthu = rs.getInt("doanhthu");
                int chiphi = rs.getInt("chiphi");
                int loinhuan = doanhthu - chiphi;

                ThongKeTungNgayTrongThangDTO thongKe = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(thongKe);
            }
        } 
         catch (SQLException e) {
            
        }
        return result;
    }
}
