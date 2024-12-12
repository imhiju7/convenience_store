/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daoctphieunhap;
import dto.dtoctphieunhap;

import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class busctphieunhap {
    private daoctphieunhap daoHD = new daoctphieunhap();
    public ArrayList <dtoctphieunhap> dsctpn;
    
    public busctphieunhap() {
        getlist() ;
    }
    public static void updateEXP(){
        daoctphieunhap.updateEXP();
    }
    public busctphieunhap(int maphieunhap) {
        getlist(maphieunhap) ;
    }
    
    public void getlist() {
        dsctpn =  daoHD.getlist();
    }
    public void getlist(int maphieunhap) {
        dsctpn =  daoHD.getlistpn(maphieunhap);
    }
    public int soluong(int masp){
        int total = 0;
        ArrayList<dtoctphieunhap> list = daoHD.getlistlo(masp);
        for(dtoctphieunhap i : list){
            total  += i.getSoluongtonkho();
        }
        return total;
    }
    
    // Business logic method to add a new HD record
    public void add (dtoctphieunhap HD) {
        daoHD.create(HD);
    }
    public void update(dtoctphieunhap ctpn){
        daoHD.updatectphieunhap(ctpn);
    }
    public dtoctphieunhap get(int mactphieunhap){
        for(dtoctphieunhap pn: dsctpn){
            if(pn.getMaPhieuNhap() == mactphieunhap){
                return pn;
            }
        }
        return null;
    }
    public int maxID(){
        return daoHD.maxID();
    }
    public dtoctphieunhap getspnhap(int masp){
        ArrayList<dtoctphieunhap> list = daoHD.getlistlo(masp);
        return list.get(0);
    }
    public void needToFillList(int maNCC) {
        daoHD = new daoctphieunhap();
        dsctpn =  daoHD.needToFillList(maNCC);
    }
    
    public void needToFillList() {
        daoHD = new daoctphieunhap();
        dsctpn =  daoHD.needToFillList();
    }

    public static void main(String[] args) {
        // Create an instance of the BUS class
        busctphieunhap bus = new busctphieunhap();
           bus.needToFillList();
           if(bus.dsctpn.isEmpty()) System.out.println("bus.busctphieunhap.main()");
        for (dtoctphieunhap HD : bus.dsctpn) {
            System.out.println(HD);
        }
    }

    public void create(dtoctphieunhap ct) {
        daoHD.create(ct);
    }
}
