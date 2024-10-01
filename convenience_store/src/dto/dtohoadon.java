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
public class dtohoadon {
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(int maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getMaTichDiem() {
        return maTichDiem;
    }

    public void setMaTichDiem(int maTichDiem) {
        this.maTichDiem = maTichDiem;
    }
    public dtohoadon(){}
    public dtohoadon(int maHoaDon, int maNhanVien, double tongTien, int maKhachHang, int maKhuyenMai, Date ngayMua, String ghiChu, int maTichDiem,int isHidden) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.tongTien = tongTien;
        this.maKhachHang = maKhachHang;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayMua = ngayMua;
        this.ghiChu = ghiChu;
        this.maTichDiem = maTichDiem;
        this.isHidden = isHidden;
    }
    private int maHoaDon;
    private int maNhanVien;
    private double tongTien;
    private int maKhachHang;
    private int maKhuyenMai;
    private Date ngayMua;
    private String ghiChu;
    private int maTichDiem;
    private int isHidden;

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }
    
}
