/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import bus.busnhanvien;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

/**
 *
 * @author PHUONG ANH
 */
public class dtochitietchamcong {
    private int mactchamcong;
    private Date ngaychamcong;
    private String loaichamcong;
    private int sogiolam;
    private int machamcong;
    private java.sql.Timestamp giobatdau;
    private java.sql.Timestamp gioketthuc;
    private String tennhanvien;
    private int manhanvien;
    public dtochitietchamcong() {
    }

    public dtochitietchamcong(int mactchamcong, Date ngaychamcong, String loaichamcong, int sogiolam, int machamcong, Timestamp giobatdau, Timestamp gioketthuc) {
        this.mactchamcong = mactchamcong;
        this.ngaychamcong = ngaychamcong;
        this.loaichamcong = loaichamcong;
        this.sogiolam = sogiolam;
        this.machamcong = machamcong;
        this.giobatdau = giobatdau;
        this.gioketthuc = gioketthuc;
        setTennhanvien(this.machamcong);
        setManhanvien(this.machamcong);
    }

    public int getMactchamcong() {
        return mactchamcong;
    }

    public void setMactchamcong(int mactchamcong) {
        this.mactchamcong = mactchamcong;
    }

    public Date getNgaychamcong() {
        return ngaychamcong;
    }

    public void setNgaychamcong(Date ngaychamcong) {
        this.ngaychamcong = ngaychamcong;
    }

    public String getLoaichamcong() {
        return loaichamcong;
    }

    public void setLoaichamcong(String loaichamcong) {
        this.loaichamcong = loaichamcong;
    }

    public int getSogiolam() {
        return sogiolam;
    }

    public void setSogiolam(int sogiolam) {
        this.sogiolam = sogiolam;
    }

    public int getMachamcong() {
        return machamcong;
    }

    public void setMachamcong(int machamcong) {
        this.machamcong = machamcong;
    }

    public Timestamp getGiobatdau() {
        return giobatdau;
    }

    public void setGiobatdau(Timestamp giobatdau) {
        this.giobatdau = giobatdau;
    }

    public Timestamp getGioketthuc() {
        return gioketthuc;
    }

    public void setGioketthuc(Timestamp gioketthuc) {
        this.gioketthuc = gioketthuc;
    }
    public void setTennhanvien(int manhanvien) {
        busnhanvien busnv= new busnhanvien();
        this.tennhanvien = busnv.gettennvbymanv(manhanvien);
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        busnhanvien busnv= new busnhanvien();
        this.tennhanvien = busnv.gettennvbymanv(manhanvien);
    }
    
    @Override
    public String toString() {
        return "chitietchamcong{" + "mactchamcong=" + mactchamcong + ", ngaychamcong=" + ngaychamcong + ", loaichamcong=" + loaichamcong + ", sogiolam=" + sogiolam + ", machamcong=" + machamcong + ", giobatdau=" + giobatdau + ", gioketthuc=" + gioketthuc + '}';
    }
    
    public Object[] toTableRowDetail() {
        //NumberFormat nf = new DecimalFormat("$ #,##0.##");
        return new Object[]{manhanvien , tennhanvien, ngaychamcong, loaichamcong, giobatdau, gioketthuc, sogiolam};
    }
    
}
