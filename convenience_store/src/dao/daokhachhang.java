/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtokhachhang;
import dto.dtokhachhang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author giavi
 */
import java.sql.*;
import java.util.ArrayList;

public class daokhachhang {

    public daokhachhang() {
    }

    public ArrayList<dtokhachhang> getAllKhachHang() {
    ArrayList<dtokhachhang> list = new ArrayList<>();
    String sql = "SELECT maKhachHang, SDT, TenKhachHang, diemTichLuy, maUuDai FROM khachhang"; // Cập nhật câu truy vấn phù hợp
    try (Connection conn = connect.connection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            dtokhachhang kh = new dtokhachhang();
            kh.setMaKhachHang(rs.getInt("MaKhachHang"));
            kh.setTenKhachHang(rs.getString("TenKhachHang"));
            kh.setSDT(rs.getString("SDT"));
            kh.setDiemTichLuy(rs.getInt("DiemTichLuy"));
            kh.setMaUudai(rs.getInt("MaUuDai"));
            list.add(kh);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


      // Phương thức thêm khách hàng mới
    public boolean addKhachHang(dtokhachhang khachHang) {
                        Connection con = connect.connection();
        String sql = "INSERT INTO khachhang (SDT, TenKhachHang, DiemTichLuy, MaUudai) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, khachHang.getSDT());
            stmt.setString(2, khachHang.getTenKhachHang());
            stmt.setInt(3, khachHang.getDiemTichLuy());
            stmt.setInt(4, khachHang.getMaUudai());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Nếu có dòng bị ảnh hưởng, coi như thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

public boolean checkSDTExist(String sdt) {
     Connection con = connect.connection();
    String query = "SELECT COUNT(*) FROM khachhang WHERE sdt = ?";
    try (PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setString(1, sdt);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;  // Nếu số điện thoại đã tồn tại
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;  // Nếu số điện thoại chưa tồn tại
}



    public boolean updateKhachHang(dtokhachhang khachHang) throws SQLException {
                Connection con = connect.connection();

        String query = "UPDATE khachhang SET SDT = ?, tenKhachHang = ?, diemTichLuy = ?, maUudai = ? WHERE maKhachHang = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, khachHang.getSDT());
            pstmt.setString(2, khachHang.getTenKhachHang());
            pstmt.setInt(3, khachHang.getDiemTichLuy());
            pstmt.setInt(4, khachHang.getMaUudai());
            pstmt.setInt(5, khachHang.getMaKhachHang());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteKhachHang(int maKhachHang) throws SQLException {
                Connection con = connect.connection();

        String query = "DELETE FROM khachhang WHERE maKhachHang = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, maKhachHang);
            return pstmt.executeUpdate() > 0;
        }
    }
    public int getNextCustomerCode() {
        int nextCode = 1;  // Giá trị mặc định nếu không tìm thấy khách hàng nào
        String query = "SELECT MAX(maKhachHang) FROM khachhang";  // Câu lệnh SQL để lấy mã khách hàng lớn nhất
                Connection con = connect.connection();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                nextCode = rs.getInt(1) + 1;  // Cộng thêm 1 vào mã khách hàng lớn nhất
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nextCode;  // Trả về mã khách hàng tiếp theo
    }
//public int getSoLuongKH() {
//        int soLuong = 0;
//                        Connection con = connect.connection();
//
//        String query = "SELECT COUNT(*) AS total FROM khachhang";
//        try (PreparedStatement pstmt = con.prepareStatement(query)) {
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                soLuong = rs.getInt("total");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return soLuong;
//    }
    

   public ArrayList<dtokhachhang> searchKhachHangByName(String name) {
    ArrayList<dtokhachhang> list = new ArrayList<>();
    String query = "SELECT * FROM khachhang WHERE tenKhachHang LIKE ?";
    try (Connection con = connect.connection();
         PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, "%" + name + "%");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            dtokhachhang kh = new dtokhachhang();
            kh.setMaKhachHang(rs.getInt("maKhachHang"));
            kh.setTenKhachHang(rs.getString("tenKhachHang"));
            kh.setSDT(rs.getString("SDT"));
            kh.setDiemTichLuy(rs.getInt("diemTichLuy"));
            kh.setMaUudai(rs.getInt("maUuDai"));
            list.add(kh);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

public ArrayList<dtokhachhang> searchKhachHangBySDT(String sdt) {
    ArrayList<dtokhachhang> list = new ArrayList<>();
    String query = "SELECT * FROM khachhang WHERE SDT LIKE ?";
    try (Connection con = connect.connection();
         PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, "%" + sdt + "%");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            dtokhachhang kh = new dtokhachhang();
            kh.setMaKhachHang(rs.getInt("maKhachHang"));
            kh.setTenKhachHang(rs.getString("tenKhachHang"));
            kh.setSDT(rs.getString("SDT"));
            kh.setDiemTichLuy(rs.getInt("diemTichLuy"));
            kh.setMaUudai(rs.getInt("maUuDai"));
            list.add(kh);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    public int updatediemtichluy(dtokhachhang kh){
        Connection con = connect.connection();
        String sql = "UPDATE khachhang set diemTichLuy= ?, maUuDai = ? WHERE maKhachHang= ?";
        PreparedStatement pst;
        int rowaffect = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, kh.getDiemTichLuy());
            pst.setInt(2, kh.getMaUudai());
            pst.setInt(3, kh.getMaKhachHang());
            rowaffect = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowaffect;
    }
    
    public int getSoLuongKH() {
        int result=0;
        String query = """
                SELECT COUNT(*) AS soLuongKhachHang FROM khachhang;
                """;
        
        try (Connection connection = connect.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
               result=resultSet.getInt("soLuongKhachHang");
            }
        } catch (Exception e) {
        }
        return result;
    }

public dtokhachhang getKhachHangById(int maKhachHang) {
        dtokhachhang khachHang = null;
        String query = "SELECT * FROM khachhang WHERE maKhachHang = ?";
                Connection con = connect.connection();

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, maKhachHang);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                khachHang = new dtokhachhang(
                    rs.getInt("maKhachHang"),
                    rs.getString("SDT"),
                    rs.getString("tenKhachHang"),
                    rs.getInt("diemTichLuy"),
                    rs.getInt("maUudai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }
    
    public dtokhachhang getkhbyphone(String phone){
        java.sql.Connection con = connect.connection();
        String sql = "SELECT * FROM khachhang WHERE SDT = ?";
        PreparedStatement pst;
        dtokhachhang kh = new dtokhachhang();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, phone);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                kh.setMaKhachHang(rs.getInt("maKhachHang"));
                kh.setTenKhachHang(rs.getString("tenKhachHang"));
                kh.setSDT(rs.getString("SDT"));
                kh.setDiemTichLuy(rs.getInt("diemTichLuy"));
                kh.setMaUudai(rs.getInt("maUuDai"));
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kh;
    }
    public static void main(String[] args) {
        try {
            // Kết nối tới cơ sở dữ liệu thử nghiệm

            daokhachhang dao = new daokhachhang();

            // Kiểm tra lấy danh sách khách hàng
            System.out.println("Danh sách khách hàng:");
            ArrayList<dtokhachhang> list = dao.getAllKhachHang();
            for (dtokhachhang kh : list) {
                System.out.println(kh.getMaKhachHang() + " - " + kh.getTenKhachHang());
            }

            // Kiểm tra thêm khách hàng
            System.out.println("\nThêm khách hàng:");
            dtokhachhang newKh = new dtokhachhang(4, "0912345678", "Nguyễn Văn A", 50, 3);
            if (dao.addKhachHang(newKh)) {
                System.out.println("Thêm thành công!");
            } else {
                System.out.println("Thêm thất bại!");
            }

            // Kiểm tra cập nhật khách hàng
            System.out.println("\nCập nhật khách hàng:");
            newKh.setDiemTichLuy(100);
            if (dao.updateKhachHang(newKh)) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Cập nhật thất bại!");
            }

            // Kiểm tra xóa khách hàng
            System.out.println("\nXóa khách hàng:");
            if (dao.deleteKhachHang(4)) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Xóa thất bại!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkphone(String phone){
        java.sql.Connection con = connect.connection();
        int i = 0;
        boolean key = false;
        String sql = "SELECT * FROM khachhang where SDT = ? ";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, phone);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(daokhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(i > 0 ){
            key = false;
        }
        else key = true;
        return key;
    }    
}
