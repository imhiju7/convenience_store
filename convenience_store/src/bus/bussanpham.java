/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daosanpham;
import dto.dtonhacungcap;
import dto.dtophanloai;
import dto.dtosanpham;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class bussanpham {
    private daosanpham daoSanPham;
    private ArrayList<dtosanpham> list_sp = new ArrayList();
    private ArrayList<dtophanloai> listpl = new ArrayList();
    private ArrayList<dtonhacungcap> list_NCC = new ArrayList();

    
    public ArrayList<dtosanpham> list () throws SQLException{
        daoSanPham = new daosanpham();
        list_sp = daoSanPham.list();
        return list_sp;
    }
    
    
    public ArrayList<dtosanpham> getList(){
        return list_sp;
    }
    
    
    public boolean addSanPham(dtosanpham sp){
        daoSanPham = new daosanpham();
        return daoSanPham.addSanpham(sp);
    }
    
    public void updateSanPham(dtosanpham sp) throws SQLException{
        daoSanPham = new daosanpham();
        daoSanPham.updateSanPham(sp);
    }
    
    public void deleteSanPham(Integer masp){
        daoSanPham = new daosanpham();
        daoSanPham.deleteSanPham(masp);
    }
    
    public Integer getCountSanPham(){
        daoSanPham = new daosanpham();
        Integer count = daoSanPham.getCountSanPham();
        return count;
    }
    
    public Integer getMaPL(String tenpl){
        daoSanPham = new daosanpham();
        return daoSanPham.getMaPhanLoai(tenpl);
    }
    
    
    
    public ArrayList<dtophanloai> listPhanloai() throws SQLException{
        daoSanPham = new daosanpham();
        listpl = daoSanPham.listPhanloai();
        return listpl;
    }
    
    
    public ArrayList<dtonhacungcap> listNCC() throws SQLException{
        daoSanPham = new daosanpham();
        list_NCC = daoSanPham.listNCC();
        return list_NCC;
    }
}
