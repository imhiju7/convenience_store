/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author giavi
 */
public class dtocthoadon {
    public dtocthoadon(){}
    public dtocthoadon(int maCTHoaDon, int maSanPham, int maHoaDon, int soLuong, double donGia) {
        this.maCTHoaDon = maCTHoaDon;
        this.maSanPham = maSanPham;
        this.maHoaDon = maHoaDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
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
    
}
