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
    private ArrayList<dtonhanvien> list_nv;
    private ArrayList<dtochucvu> list_cv;

    public busnhanvien() {
    }
    
    // get
    
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
        daonv.list();
        list_nv = daonv.getlist();
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
    
}
