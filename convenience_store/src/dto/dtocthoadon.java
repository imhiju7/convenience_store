/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import bus.bussanpham;

/**
 *
 * @author giavi
 */
public class dtocthoadon {
    public dtocthoadon(){}
    public dtocthoadon(int maSanPham, int maHoaDon, int soLuong, double donGia, int maCTHoaDon) {
        this.maCTHoaDon = maCTHoaDon;
        this.maSanPham = maSanPham;
        this.maHoaDon = maHoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
        setTensanpham(this.maSanPham);
    }

    public int getMaCTHoaDon() {
        return maCTHoaDon;
    }

    public void setMaCTHoaDon(int maCTHoaDon) {
        this.maCTHoaDon = maCTHoaDon;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getthanhtien(){
        return (double)this.soLuong*this.donGia;
    }
    
    private int maCTHoaDon;
    private int maSanPham;
    private int maHoaDon;
    private int soLuong;
    private double donGia;
    private String tensanpham;

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(int masanpham) {
        bussanpham sp = new bussanpham();
        this.tensanpham = sp.getById(masanpham).getTenSanPham();
    }

    @Override
    public String toString() {
        return "dtocthoadon{" + "maCTHoaDon=" + maCTHoaDon + ", maSanPham=" + maSanPham + ", maHoaDon=" + maHoaDon + ", soLuong=" + soLuong + ", donGia=" + donGia + ", tensanpham=" + tensanpham + '}';
    }

    public Object[] toTableRow() {
        return new Object[]{maSanPham , tensanpham, donGia, soLuong, getthanhtien()};
    }

}
    
    


