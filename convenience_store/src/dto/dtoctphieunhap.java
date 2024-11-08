/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class dtoctphieunhap {
    
      
    private int maCTPhieuNhap;
    private int soLuong;
    private double donGia;
    private int maPhieuNhap;
    private int maSanPham;
    private Date ngayhethan;
    private int soluongtonkho;
    private int ishidden;
    private String ghichu;
    private Double giaBan;
    
    
    
    
    public dtoctphieunhap(){}
    public dtoctphieunhap(int maCTPhieuNhap, int soLuong, float donGia, int maPhieuNhap, int maSanPham, Date ngayhethan, int soluongtonkho, int ishidden, String ghichu) {
        this.maCTPhieuNhap = maCTPhieuNhap;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.maPhieuNhap = maPhieuNhap;
        this.maSanPham = maSanPham;
        this.ngayhethan = ngayhethan;
        this.soluongtonkho = soluongtonkho;
        this.ishidden = ishidden;
        this.ghichu = ghichu;
    }

    public int getMaCTPhieuNhap() {
        return maCTPhieuNhap;
    }

    public void setMaCTPhieuNhap(int maCTPhieuNhap) {
        this.maCTPhieuNhap = maCTPhieuNhap;
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

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Date getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(Date ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public int getSoluongtonkho() {
        return soluongtonkho;
    }

    public void setSoluongtonkho(int soluongtonkho) {
        this.soluongtonkho = soluongtonkho;
    }

    public int getIshidden() {
        return ishidden;
    }

    public void setIshidden(int hidden) {
        if(hidden == 0){
            if(this.soluongtonkho == 0 || this.ngayhethan.before(new Date())) this.ishidden = 1;
            else this.ishidden = 0;
        }
        else this.ishidden = hidden;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    
    public double getthanhtien(){
        return (double)this.soLuong*this.donGia;
    }
    
  

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }
    
    
}
