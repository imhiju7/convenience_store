/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import bus.buskhachhang;
import bus.busnhanvien;
import java.sql.SQLException;
import java.sql.Timestamp;

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

    public Timestamp getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Timestamp ngayMua) {
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
    public dtohoadon(int maHoaDon, int maNhanVien, double tongTien, int maKhachHang, int maKhuyenMai, Timestamp ngayMua, String ghiChu, int maTichDiem) throws SQLException {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.tongTien = tongTien;
        this.maKhachHang = maKhachHang;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayMua = ngayMua;
        this.ghiChu = ghiChu;
        this.maTichDiem = maTichDiem;
        setTenkhachhang(this.maKhachHang);
        setTennhanvien(this.maNhanVien);
        
    }
    private int maHoaDon;
    private int maNhanVien;
    private double tongTien;
    private int maKhachHang;
    private int maKhuyenMai;
    private Timestamp ngayMua;
    private String ghiChu;
    private int maTichDiem;
    private String tenkhachhang;
    private String tennhanvien;
    
    @Override
    public String toString() {
        return "dtohoadon{" + "maHoaDon=" + maHoaDon + ", maNhanVien=" + maNhanVien + ", tongTien=" + tongTien + ", maKhachHang=" + maKhachHang + ", maKhuyenMai=" + maKhuyenMai + ", ngayMua=" + ngayMua + ", ghiChu=" + ghiChu + ", maTichDiem=" + maTichDiem + ", tenkhachhang=" + tenkhachhang + ", tennhanvien=" + tennhanvien + '}';
    }
    
    public Object[] toTableRow() {
        return new Object[]{maHoaDon , ngayMua, tenkhachhang, maKhuyenMai, tongTien, tennhanvien, ghiChu};
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTenkhachhang(int ma) {
        buskhachhang bus = new buskhachhang();
        if(bus.getkhachhangbyid(ma) == null) {
            this.tenkhachhang ="";
            return;
        }
        this.tenkhachhang = bus.getkhachhangbyid(ma).getTenKhachHang();
    }

    public void setTennhanvien(int manhanvien) throws SQLException {
        busnhanvien busnv = new busnhanvien();
        this.tennhanvien = busnv.gettennvbymanv(manhanvien);
    }
    
}
