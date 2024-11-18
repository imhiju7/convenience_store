/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daosanpham;
import dto.dtochitietchamcong;
import dto.dtoctphieunhap;
import dto.dtonhacungcap;
import dto.dtophanloai;
import dto.dtophieunhap;
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
    private ArrayList<dtophieunhap> list_PN = new ArrayList<>();
    private ArrayList<dtoctphieunhap> list_CTPN = new ArrayList<>();

    
    public ArrayList<dtosanpham> list (){
        daoSanPham = new daosanpham();
        list_sp = daoSanPham.list();
        return list_sp;
    }
    public ArrayList<dtosanpham> listByNhaCungCapID (int maNCC){
        daoSanPham = new daosanpham();
        list_sp = daoSanPham.listByNhaCungCapID(maNCC);
        return list_sp;
    }

    public ArrayList<dtosanpham> getList(){
        return list_sp;
    }
    
    public dtosanpham getById(int maSp){
        list();
        for (dtosanpham sp: list_sp){
            if(sp.getMaSanPham() == maSp) 
                return sp;
        }
        return null;
    }

    public dtosanpham getByName(String tensp){
        list();
        for (dtosanpham sp: list_sp){
            if(sp.getTenSanPham().equals(tensp))
                return sp;
        }
        return null;
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
    
    public ArrayList<dtophieunhap> listPN(Integer mancc) throws SQLException{
        daoSanPham = new daosanpham();
        list_PN = daoSanPham.listPN(mancc);
        return list_PN;
    }
    
    public ArrayList<dtoctphieunhap> listCTPN(Integer mapn) throws SQLException{
        daoSanPham = new daosanpham();
        list_CTPN = daoSanPham.listCTPN(mapn);
        return list_CTPN;
    }
    
    
    
}
