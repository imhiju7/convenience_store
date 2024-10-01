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
    private Date ngayBatDau;
    private Date ngayHetHan;
    private int soLuong;
    private int phanTram;
    private int ishidden;

    public int getSoLuongDaDung() {
        return soLuongDaDung;
    }

    public void setSoLuongDaDung(int soLuongDaDung) {
        this.soLuongDaDung = soLuongDaDung;
    }
    private int soLuongDaDung;

    public dtokhuyenmai(int maKhuyenMai, String tenKhuyenMai, Date ngayBatDau, Date ngayHetHan, int soLuong, int phanTram, int ishidden, int soLuongDaDung) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.soLuong = soLuong;
        this.phanTram = phanTram;
        this.ishidden = ishidden;
        this.soLuongDaDung = soLuongDaDung;
    }
    public dtokhuyenmai(){}

    public int getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(int maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(int phanTram) {
        this.phanTram = phanTram;
    }

    public int getIshidden() {
        return ishidden;
    }

    public void setIshidden(int ishidden) {
        this.ishidden = ishidden;
    }
}
