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
     public ArrayList<thongkedoanhthuDTO> getDoanhThuChiPhiSPbyPhanLoai(String tenPhanLoai, int nam) throws SQLException {
        String sql = "SELECT "
                    + "thang.thang, "
                    + "IFNULL(doanh_thu.doanh_thu, 0) AS doanh_thu, "
                    + "IFNULL(chi_phi.chi_phi, 0) AS chi_phi, "
                    + "(IFNULL(doanh_thu.doanh_thu, 0) - IFNULL(chi_phi.chi_phi, 0)) AS loi_nhuan "
                    + "FROM "
                    + "(SELECT 1 AS thang UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL "
                    + "SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 "
                    + "UNION ALL SELECT 12) AS thang "
                    + "LEFT JOIN "
                    + "(SELECT "
                    + "MONTH(h.ngayMua) AS thang, "
                    + "SUM(cth.soLuong * cth.donGia) AS doanh_thu "
                    + "FROM hoadon h "
                    + "JOIN chitiethoadon cth ON h.maHoaDon = cth.maHoaDon "
                    + "JOIN sanpham s ON cth.maSanPham = s.maSanPham "
                    + "JOIN phanloai pl ON s.maPhanLoai = pl.maPhanLoai "
                    + "WHERE pl.tenPhanLoai = ? AND YEAR(h.ngayMua) = ? AND pl.isDelete = 0 "
                    + "GROUP BY MONTH(h.ngayMua)) AS doanh_thu "
                    + "ON thang.thang = doanh_thu.thang "
                    + "LEFT JOIN "
                    + "(SELECT "
                    + "MONTH(p.ngayNhap) AS thang, "
                    + "SUM(ctpn.soLuong * ctpn.giaNhap) AS chi_phi "
                    + "FROM phieunhap p "
                    + "JOIN chitietphieunhap ctpn ON p.maPhieuNhap = ctpn.maPhieuNhap "
                    + "JOIN sanpham s ON ctpn.maSanPham = s.maSanPham "
                    + "JOIN phanloai pl ON s.maPhanLoai = pl.maPhanLoai "
                    + "WHERE pl.tenPhanLoai = ? AND YEAR(p.ngayNhap) = ? AND pl.isDelete = 0 "
                    + "GROUP BY MONTH(p.ngayNhap)) AS chi_phi "
                    + "ON thang.thang = chi_phi.thang "
                    + "ORDER BY thang.thang";

        try (
            Connection con = connect.connection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tenPhanLoai);
            stmt.setInt(2, nam);
            stmt.setString(3, tenPhanLoai);
            stmt.setInt(4, nam);

            ResultSet rs = stmt.executeQuery();
            ArrayList<thongkedoanhthuDTO> resultList = new ArrayList<>();

            while (rs.next()) {
                int thang = rs.getInt("thang");
                long doanhThu = rs.getLong("doanh_thu");
                long chiPhi = rs.getLong("chi_phi");
                long loiNhuan = rs.getLong("loi_nhuan");

                resultList.add(new thongkedoanhthuDTO(thang, doanhThu, chiPhi, loiNhuan));
            }

            return resultList;
        }}
     public int getOldestYear() {
        // Câu lệnh SQL để tìm năm cũ nhất từ bảng hoadon và phieunhap
        String sql = "SELECT MIN(year) AS min_nam " +
                     "FROM ( " +
                     "    SELECT YEAR(ngayMua) AS year FROM hoadon " +
                     "    UNION " +
                     "    SELECT YEAR(ngayNhap) AS year FROM phieunhap " +
                     ") AS years";

        int oldestYear = 0;
         try (
             Connection con = connect.connection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Nếu có kết quả trả về, lấy giá trị năm cũ nhất
            if (rs.next()) {
                oldestYear = rs.getInt("min_nam");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oldestYear;
    }
     public ArrayList<thongkedoanhthuDTO> getDoanhThuChiPhiTheoNam(String tenPhanLoai, int nam) throws SQLException {
    // Câu SQL không chia theo tháng
    String sql = "SELECT "
                + "IFNULL(doanh_thu.doanh_thu, 0) AS doanh_thu, "
                + "IFNULL(chi_phi.chi_phi, 0) AS chi_phi, "
                + "(IFNULL(doanh_thu.doanh_thu, 0) - IFNULL(chi_phi.chi_phi, 0)) AS loi_nhuan "
                + "FROM "
                + "(SELECT SUM(cth.soLuong * cth.donGia) AS doanh_thu "
                + "FROM hoadon h "
                + "JOIN chitiethoadon cth ON h.maHoaDon = cth.maHoaDon "
                + "JOIN sanpham s ON cth.maSanPham = s.maSanPham "
                + "JOIN phanloai pl ON s.maPhanLoai = pl.maPhanLoai "
                + "WHERE pl.tenPhanLoai = ? AND YEAR(h.ngayMua) = ? AND pl.isDelete = 0) AS doanh_thu "
                + "LEFT JOIN "
                + "(SELECT SUM(ctpn.soLuong * ctpn.giaNhap) AS chi_phi "
                + "FROM phieunhap p "
                + "JOIN chitietphieunhap ctpn ON p.maPhieuNhap = ctpn.maPhieuNhap "
                + "JOIN sanpham s ON ctpn.maSanPham = s.maSanPham "
                + "JOIN phanloai pl ON s.maPhanLoai = pl.maPhanLoai "
                + "WHERE pl.tenPhanLoai = ? AND YEAR(p.ngayNhap) = ? AND pl.isDelete = 0) AS chi_phi "
                + "ON 1=1";

    try (Connection con = connect.connection();
        PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, tenPhanLoai);
        stmt.setInt(2, nam);
        stmt.setString(3, tenPhanLoai);
        stmt.setInt(4, nam);

        ResultSet rs = stmt.executeQuery();
        ArrayList<thongkedoanhthuDTO> resultList = new ArrayList<>();

        if (rs.next()) {
            long doanhThu = rs.getLong("doanh_thu");
            long chiPhi = rs.getLong("chi_phi");
            long loiNhuan = rs.getLong("loi_nhuan");

            // Chỉ có một kết quả cho toàn bộ năm
            resultList.add(new thongkedoanhthuDTO(nam, doanhThu, chiPhi, loiNhuan));
        }

        return resultList;
    }
}

}
