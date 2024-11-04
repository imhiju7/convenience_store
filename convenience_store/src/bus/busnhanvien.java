/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daonhanvien;
import dao.daosanpham;
import dto.dtochucvu;
import dto.dtonhanvien;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Hieu PC
 */
public class busnhanvien {
    private daonhanvien daonv;
    public ArrayList<dtonhanvien> list_nv;
    private ArrayList<dtochucvu> list_cv;
    // daonhanvien daonv = new daonhanvien();
    // public ArrayList<dtonhanvien> list_nv;

    public busnhanvien() throws SQLException {
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
    
    
    public Integer getSoLuongNV() throws SQLException{
        daonv = new daonhanvien();
        return daonv.getSoLuongNV();
    }
    
    public Integer getMaChucVuByName(String tencv) throws SQLException{
        daonv = new daonhanvien();
        return daonv.getMaChucVuByName(tencv);
    }
    
    public void getlist() throws SQLException {
        daonv = new daonhanvien();
        this.list_nv = daonv.list();
    }

    public static void main(String args[]) throws SQLException {
        busnhanvien bus = new busnhanvien();
        for(dtonhanvien nv:bus.list_nv){
            System.out.print(nv);
        }
    }
}
