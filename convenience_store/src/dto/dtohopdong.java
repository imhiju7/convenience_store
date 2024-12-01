/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class dtohopdong {
    public int maHopDong;
    public String tuNgay;
    public String denNgay;
    public double luongCoBan;
    public int maNV;
    public int isDelete;
    
    public dtohopdong(){};
    public dtohopdong(int maHopDong, String tuNgay, String denNgay, double luongCoBan, int maNV, int isDelete) {
        this.maHopDong = maHopDong;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.luongCoBan = luongCoBan;
        this.maNV = maNV;
        this.isDelete = isDelete;
    }
    
    public dtohopdong(int maHopDong, String tuNgay, String denNgay, double luongCoBan, int maNV) {
        this.maHopDong = maHopDong;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.luongCoBan = luongCoBan;
        this.maNV = maNV;
        this.isDelete = 0;
    }

    public int getMaHopDong() {
        return maHopDong;
    }

    public String getTuNgay() {
        return tuNgay;
    }

    public String getDenNgay() {
        return denNgay;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public void setTuNgay(String tuNgay) {
        this.tuNgay = tuNgay;
    }

    public void setDenNgay(String denNgay) {
        this.denNgay = denNgay;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "dtohopdong{" + "maHopDong=" + maHopDong + ", tuNgay=" + tuNgay + ", denNgay=" + denNgay + ", luongCoBan=" + luongCoBan + ", maNV=" + maNV + ", isDelete=" + isDelete + '}';
    }
    
}
