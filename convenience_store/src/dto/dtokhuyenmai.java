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
public class dtokhuyenmai {
    private int maKhuyenMai;
    private String tenKhuyenMai;
    private String ngayBatDau;
    private String ngayHetHan;
    private int soLuong;
    private double phanTram;
    private int ishidden;
    private int soLuongDaDung;
    
    public dtokhuyenmai() {
    }

    public dtokhuyenmai(int maKhuyenMai, String tenKhuyenMai, String ngayBatDau, String ngayHetHan, int soLuong, double phanTram) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.soLuong = soLuong;
        this.phanTram = phanTram;
        this.ishidden = 0;
        this.soLuongDaDung = 0;
    }

    
    
    public dtokhuyenmai(int maKhuyenMai, String tenKhuyenMai, String ngayBatDau, String ngayHetHan, int soLuong, double phanTram, int ishidden, int soLuongDaDung) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.soLuong = soLuong;
        this.phanTram = phanTram;
        this.ishidden = ishidden;
        this.soLuongDaDung = soLuongDaDung;
    }

    public int getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public String getNgayHetHan() {
        return ngayHetHan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getPhanTram() {
        return phanTram;
    }

    public int getIshidden() {
        return ishidden;
    }

    public int getSoLuongDaDung() {
        return soLuongDaDung;
    }

    public void setMaKhuyenMai(int maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayHetHan(String ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setPhanTram(double phanTram) {
        this.phanTram = phanTram;
    }

    public void setIshidden(int ishidden) {
        this.ishidden = ishidden;
    }

    public void setSoLuongDaDung(int soLuongDaDung) {
        this.soLuongDaDung = soLuongDaDung;
    }

    @Override
    public String toString() {
        return "dtokhuyenmai{" + "maKhuyenMai=" + maKhuyenMai + ", tenKhuyenMai=" + tenKhuyenMai + ", ngayBatDau=" + ngayBatDau + ", ngayHetHan=" + ngayHetHan + ", soLuong=" + soLuong + ", phanTram=" + phanTram + ", ishidden=" + ishidden + ", soLuongDaDung=" + soLuongDaDung + '}';
    }
    
    
}
