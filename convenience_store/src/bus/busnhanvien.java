/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daonhanvien;
import dao.daosanpham;
import dto.dtochucvu;
import dto.dtohopdong;
import dto.dtonhanvien;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Hieu PC
 */
public class busnhanvien {
    public ArrayList<dtonhanvien> list_nv;
    private ArrayList<dtochucvu> list_cv;
    daonhanvien daonv = new daonhanvien();

    public busnhanvien(){
        getlist();
    }
    
    // get
    public ArrayList<dtonhanvien> getNhanVienList() {
        return daonv.getNhanVienList();
    }
    
    public int getmachucvu(int manv){
        daonv = new daonhanvien();
        return daonv.getmachucvu(manv);
    }
    
    public String gettennvbymanv(int manv){
        daonv = new daonhanvien();
        return daonv.gettennvbymanv(manv);
    }

    public void list() throws SQLException{
        daonv = new daonhanvien();
        list_nv = daonv.list();
    }
    
    public void AddNhanVien(dtonhanvien nv) throws SQLException{
        daonv = new daonhanvien();
        daonv.AddNhanVien(nv);
    }
    
    public void updateNhanVien(dtonhanvien nv) throws SQLException{
        daonv = new daonhanvien();
        daonv.updateNhanVien(nv);
    }
    
    public void deleteNhanVien(Integer ma) throws SQLException{
        daonv = new daonhanvien();
        daonv.deleteNhanVien(ma);
    }

    public ArrayList<dtonhanvien> getList(){
        return list_nv;
    }
    
    public ArrayList<dtochucvu> listChucVu() throws SQLException{
        daonv = new daonhanvien();
        list_cv = daonv.listChucVu();
        return list_cv;
    }
    
    public String getTenChucVu(Integer ma) throws SQLException{
        daonv = new daonhanvien();
        return daonv.getTenChucVu(ma);
    }
    
      // Phương thức gọi DAO để lấy chức vụ của nhân viên theo mã nhân viên
    public String getChucVuByMaNhanVien(int manhanvien) throws SQLException {
        return daonv.getChucVuByMaNhanVien(manhanvien);
    }

    // Phương thức cập nhật chức vụ cho nhân viên
    public boolean updateChucVuByMaNhanVien(int manhanvien, int machucvu) throws SQLException {
        return daonv.updateChucVuByMaNhanVien(manhanvien, machucvu);
    }
    public Integer getSoLuongNV() throws SQLException{
        daonv = new daonhanvien();
        return daonv.getSoLuongNV();
    }
    
    public Integer getMaChucVuByName(String tencv) throws SQLException{
        daonv = new daonhanvien();
        return daonv.getMaChucVuByName(tencv);
    }
    
    public void getlist() {
        daonv = new daonhanvien();
        this.list_nv = daonv.list();
    }

    
    public ArrayList<dtohopdong> list_HD() throws SQLException{
        daonv = new daonhanvien();
        return daonv.list_HD();
    }
    
    
    public boolean checkExistSdt(String sdt) throws SQLException{
        daonv = new daonhanvien();
        return daonv.checkExistSdt(sdt);
    }
    
    
    public boolean checkExistEmail(String email) throws SQLException{
        daonv = new daonhanvien();
        return daonv.checkemailexist(email);
    }
    
    
    
    
    
    public static void main(String args[]) throws SQLException {
        busnhanvien bus = new busnhanvien();
        for(dtonhanvien nv:bus.list_nv){
            System.out.print(nv);
        }
    }
    
    
    
    
}
