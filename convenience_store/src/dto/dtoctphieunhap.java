/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import bus.bussanpham;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class dtoctphieunhap {

    public dtoctphieunhap(){}

    public dtoctphieunhap(int maCTPhieuNhap, int soLuong, double giaNhap, int maPhieuNhap, int maSanPham, Date ngayhethan, int soluongtonkho, String ghichu, double giaBan) {
        this.maCTPhieuNhap = maCTPhieuNhap;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.maPhieuNhap = maPhieuNhap;
        this.maSanPham = maSanPham;
        this.ngayhethan = ngayhethan;
        this.soluongtonkho = soluongtonkho;
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

    public int getSoluongtonkho() {
        return soluongtonkho;
    }

    public void setSoluongtonkho(int soluongtonkho) {
        this.soluongtonkho = soluongtonkho;
    }

    public int getIshidden() {
        return ishidden;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
    
    public double getthanhtien(){
        return (double)this.soLuong*this.giaNhap;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public Date getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(Date ngayhethan) {
        this.ngayhethan = ngayhethan;
    }


    private int maCTPhieuNhap;
    private int soLuong;
    private double giaNhap;
    private double giaBan;
    private int maPhieuNhap;
    private int maSanPham;
    private Date ngayhethan;
    private int soluongtonkho;
    private int ishidden;
    private String ghichu;

    public String getTenSP(int masp){
        bussanpham sp = new bussanpham();
        return sp.getById(masp).getTenSanPham();
    }
    public int getMaNCC(int masp){
        bussanpham sp = new bussanpham();
        return sp.getById(masp).getMaNCC();
    }
    
    @Override
    public String toString() {
        return "dtoctphieunhap{" + "maCTPhieuNhap=" + maCTPhieuNhap + ", soLuong=" + soLuong + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", maPhieuNhap=" + maPhieuNhap + ", maSanPham=" + maSanPham + ", ngayhethan=" + ngayhethan + ", soluongtonkho=" + soluongtonkho + ", ishidden=" + ishidden + ", ghichu=" + ghichu + '}';
    }

    public Object[] toTableRow(int index) {
        return new Object[]{index, maSanPham , getTenSP(maSanPham), giaNhap, soLuong, ngayhethan, giaBan, soluongtonkho};
    }
    
    public Object[] toAdditionalTableRow() {
        return new Object[]{getMaNCC(maSanPham), maSanPham , getTenSP(maSanPham), ngayhethan, soluongtonkho};
    }
}
