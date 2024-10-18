/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import bus.busnhanvien;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author PHUONG ANH
 */
public class dtochamcong {
    private int machamcong;
    private int manhanvien;
    private int sogiolamviec;
    private int songaylamviec;
    private int songaynghi;
    private int songaytre;
    private int sogiolamthem;
    private String chitiet;
    private int thangchamcong;
    private int namchamcong;
    private String tennhanvien;
    
    public dtochamcong(int machamcong, int manhanvien, int sogiolamviec, int songaylamviec, int songaynghi, int songaytre, int sogiolamthem, String chitiet, int thangchamcong, int namchamcong) {
        this.machamcong = machamcong;
        this.manhanvien = manhanvien;
        this.sogiolamviec = sogiolamviec;
        this.songaylamviec = songaylamviec;
        this.songaynghi = songaynghi;
        this.songaytre = songaytre;
        this.sogiolamthem = sogiolamthem;
        this.chitiet = chitiet;
        this.thangchamcong = thangchamcong;
        this.namchamcong = namchamcong;
        setTennhanvien(this.manhanvien);
    }

    public dtochamcong() {
    }

    
    public int getMachamcong() {
        return machamcong;
    }

    public void setMachamcong(int machamcong) {
        this.machamcong = machamcong;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public int getSogiolamviec() {
        return sogiolamviec;
    }

    public void setSogiolamviec(int sogiolamviec) {
        this.sogiolamviec = sogiolamviec;
    }

    public int getSongaylamviec() {
        return songaylamviec;
    }

    public void setSongaylamviec(int songaylamviec) {
        this.songaylamviec = songaylamviec;
    }

    public int getSongaynghi() {
        return songaynghi;
    }

    public void setSongaynghi(int songaynghi) {
        this.songaynghi = songaynghi;
    }

    public int getSongaytre() {
        return songaytre;
    }

    public void setSongaytre(int songaytre) {
        this.songaytre = songaytre;
    }

    public int getSogiolamthem() {
        return sogiolamthem;
    }

    public void setSogiolamthem(int sogiolamthem) {
        this.sogiolamthem = sogiolamthem;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public int getThangchamcong() {
        return thangchamcong;
    }

    public void setThangchamcong(int thangchamcong) {
        this.thangchamcong = thangchamcong;
    }

    public int getNamchamcong() {
        return namchamcong;
    }

    public void setNamchamcong(int namchamcong) {
        this.namchamcong = namchamcong;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(int manhanvien) {
        busnhanvien busnv = new busnhanvien();
        this.tennhanvien = busnv.gettennvbymanv(manhanvien);
    }

    @Override
    public String toString() {
        return "dtochamcong{" + "machamcong=" + machamcong + ", manhanvien=" + manhanvien + ", sogiolamviec=" + sogiolamviec + ", songaylamviec=" + songaylamviec + ", songaynghi=" + songaynghi + ", songaytre=" + songaytre + ", sogiolamthem=" + sogiolamthem + ", chitiet=" + chitiet + ", thangchamcong=" + thangchamcong + ", namchamcong=" + namchamcong + '}';
    }
    
    public Object[] toTableRowGeneral() {
        //NumberFormat nf = new DecimalFormat("$ #,##0.##");
        return new Object[]{manhanvien , tennhanvien, thangchamcong, namchamcong, songaynghi, songaytre, sogiolamthem, sogiolamviec, songaylamviec, chitiet};
    }

}
