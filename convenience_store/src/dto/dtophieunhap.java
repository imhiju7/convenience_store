/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import bus.busnhacungcap;
import bus.busnhanvien;
import java.sql.Timestamp;

/**
 *
 * @author giavi
 */
public class dtophieunhap {
    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Timestamp getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Timestamp ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    public dtophieunhap(){}
    public dtophieunhap(int maPhieuNhap, Timestamp ngayNhap, double tongTien, int maNhaCungCap, int maNhanVien, String ghiChu) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.tongTien = tongTien;
        this.maNhaCungCap = maNhaCungCap;
        this.maNhanVien = maNhanVien;
        this.ghiChu = ghiChu;
    }

    public String getTenNCC(int maNCC){
        busnhacungcap ncc = new busnhacungcap();
        return ncc.getById(maNCC).getTenNhaCungCap();
    }

    public String getTenNV(int maNV){
        busnhanvien busnv = new busnhanvien();
        return busnv.gettennvbymanv(maNV);
    }
    @Override
    public String toString() {
        return "dtophieunhap{" + "maPhieuNhap=" + maPhieuNhap + ", ngayNhap=" + ngayNhap + ", tongTien=" + tongTien + ", maNhaCungCap=" + maNhaCungCap + ", maNhanVien=" + maNhanVien + ", ghiChu=" + ghiChu + '}';
    }
    
    public Object[] toTableRow() {
        return new Object[]{maPhieuNhap , ngayNhap, getTenNCC(this.maNhaCungCap), tongTien, getTenNV(this.maNhanVien), ghiChu};
    }

    private int maPhieuNhap;
    private Timestamp ngayNhap;
    private double tongTien;
    private int maNhaCungCap;
    private int maNhanVien;
    private String ghiChu;

}
