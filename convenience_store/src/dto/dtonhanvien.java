package dto;

import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hieu PC
 */
public class dtonhanvien {
    private int maNhanVien;
    private String tenNhanVien;
    private int maChucVu;
    private int gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String Email;
    private String SDT;
    private int isdelete;
    private Date ngayTao;
    private String img;
    private int maHopDong;
    private float luongCoBan;
    private Date ngayLamViec;
    private Date ngayKetThuc;
    
    public dtonhanvien() {}
    
    public dtonhanvien(int maNhanVien, String tenNhanVien, int maChucVu, int gioiTinh, Date ngaySinh, String diaChi, String Email, String SDT, int isdelete, Date ngayTao, String img, int maHopDong, float luongCoBan, Date ngayLamViec, Date ngayKetThuc) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.maChucVu = maChucVu;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.Email = Email;
        this.SDT = SDT;
        this.isdelete = isdelete;
        this.ngayTao = ngayTao;
        this.img = img;
        this.maHopDong = maHopDong;
        this.luongCoBan = luongCoBan;
        this.ngayLamViec = ngayLamViec;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(int maChucVu) {
        this.maChucVu = maChucVu;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public float getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(float luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public Date getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(Date ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
    @Override
    public String toString() {
        return "dtonhanvien{" + "maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", maChucVu=" + maChucVu + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", Email=" + Email + ", SDT=" + SDT + ", isdelete=" + isdelete + ", ngayTao=" + ngayTao + ", img=" + img + ", maHopDong=" + maHopDong + ", luongCoBan=" + luongCoBan + ", ngayLamViec=" + ngayLamViec + ", ngayKetThuc=" + ngayKetThuc + '}';
    }
}
