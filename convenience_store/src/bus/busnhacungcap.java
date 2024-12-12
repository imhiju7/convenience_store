/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daonhacungcap;
import dto.dtonhacungcap;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class busnhacungcap {
    public ArrayList<dtonhacungcap> dsncc; // Danh sách nhà cung cấp
    private daonhacungcap daoNCC = new daonhacungcap();
    
    public dtonhacungcap getById(int id){
        return daoNCC.getById(id);
    }
    public dtonhacungcap getByName(String name){
        return daoNCC.getByName(name);
    }
    
    public ArrayList <dtonhacungcap> list(){
        return daoNCC.getlist();
    }
    public busnhacungcap() {
        getlist(); // Lấy danh sách khi khởi tạo
    }

    // Lấy tất cả các nhà cung cấp thông qua DAO
    public void getlist() {
        dsncc = daoNCC.getlist();
    }

    // Phương thức để thêm một nhà cung cấp mới
    public void add(dtonhacungcap ncc) {
        daoNCC.add(ncc); // Thêm nhà cung cấp vào cơ sở dữ liệu thông qua DAO
        getlist(); // Cập nhật danh sách sau khi thêm
    }

    // Phương thức để cập nhật thông tin của nhà cung cấp
    public void update(dtonhacungcap ncc) {
        daoNCC.update(ncc); // Cập nhật nhà cung cấp thông qua DAO
        getlist(); // Cập nhật lại danh sách sau khi cập nhật
    }

    // Phương thức để xóa nhà cung cấp theo mã
    public void delete(int maNhaCungCap) {
        daoNCC.delete(maNhaCungCap); // Xóa nhà cung cấp theo mã qua DAO
        getlist(); // Cập nhật lại danh sách sau khi xóa
    }
    public int getSoLuongNhaCungCap() throws SQLException {
        return daoNCC.getCountNhaCungCap(); // Gọi phương thức từ DAO để đếm số lượng phân loại
    }

    // Phương thức lấy nhà cung cấp theo mã
    public dtonhacungcap get(int maNhaCungCap) {
        for (dtonhacungcap ncc : dsncc) {
            if (ncc.getMaNhaCungCap() == maNhaCungCap) {
                return ncc; // Trả về nhà cung cấp nếu tìm thấy
            }
        }
        return null; // Không tìm thấy nhà cung cấp với mã đó
    }
    public boolean isNhaCungCapExists(String tenNhaCungCap, String SDT, String email) throws SQLException {
        return daoNCC.isNhaCungCapExists(tenNhaCungCap, SDT, email);
    }


    public static void main(String[] args) {
        // Tạo một instance của lớp BUS
        busnhacungcap bus = new busnhacungcap();
        
        // In ra danh sách các nhà cung cấp
        for (dtonhacungcap ncc : bus.dsncc) {
            System.out.println(ncc);
        }
        
        // Thêm một nhà cung cấp mới
       
        
        // In ra danh sách sau khi thêm
        System.out.println("Sau khi thêm:");
        for (dtonhacungcap ncc : bus.dsncc) {
            System.out.println(ncc);
        }
        
        
                
        System.out.println(bus.getById(1));
    }
}


   
   
