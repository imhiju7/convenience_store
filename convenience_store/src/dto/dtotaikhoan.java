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
public class dtotaikhoan {

    private String tendangnhap;
    private String matkhau;
    private Date ngaytao;
    private int isblock;
    private int manhanvien;
    
    public dtotaikhoan() {
    }

    public dtotaikhoan(String tendangnhap, String matkhau, Date ngaytao, int isblock, int manhanvien) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.ngaytao = ngaytao;
        this.isblock = isblock;
        this.manhanvien = manhanvien;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getIsblock() {
        return isblock;
    }

    public void setIsblock(int isblock) {
        this.isblock = isblock;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    @Override
    public String toString() {
        return "dtotaikhoan{" + "tendangnhap=" + tendangnhap + ", matkhau=" + matkhau + ", ngaytao=" + ngaytao + ", isblock=" + isblock + ", manhanvien=" + manhanvien + '}';
    }
}
