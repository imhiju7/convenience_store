/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

/**
 *
 * @author giavi
 */
public class dtosanpham {
    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIshidden() {
        return ishidden;
    }
    public void setIshidden(int hidden){
        this.ishidden = hidden;
    }
    public void setautoishidden() {
        if(this.getSoLuong() == 0) this.ishidden = 1;
        else this.ishidden = 0;
    }

    public dtosanpham(){}
    public dtosanpham(int maSanPham, String tenSanPham, double giaBan, int soLuong, Date ngayThem, String img, int ishidden,int maphanloai,double giaNhap) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.ngayThem = ngayThem;
        this.img = img;
        this.ishidden = ishidden;
        this.maPhanLoai = maphanloai;
        this.giaNhap = giaNhap;
    }

    public int getMaPhanLoai() {
        return maPhanLoai;
    }

    public void setMaPhanLoai(int maPhanLoai) {
        this.maPhanLoai = maPhanLoai;
    }
    private int maPhanLoai;
    private int maSanPham;
    private String tenSanPham;
    private double giaBan;
    private int soLuong;
    private Date ngayThem;
    private String img;
    private int ishidden;

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }
    private double giaNhap;
}
