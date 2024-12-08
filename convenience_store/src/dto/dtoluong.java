package dto;

import bus.busnhanvien;
import java.util.Date;

public class dtoluong {
    private int maLuong;
    private int maChamCong;
    private double phuCap;
    private double luongThucTe;
    private double luongThuong;
    private double khoanBaoHiem;
    private double khoanThue;
    private double thuclanh;
    private double luongLamThem;
    private Date ngayNhanLuong;
    private int maNhanVien;

    // Constructor đầy đủ
    public dtoluong(int maLuong, int maChamCong, double phuCap, double luongThucTe, double luongThuong,
                    double khoanBaoHiem, double khoanThue, double thuclanh, double luongLamThem, 
                    Date ngayNhanLuong, int maNhanVien) {
        this.maLuong = maLuong;
        this.maChamCong = maChamCong;
        this.phuCap = phuCap;
        this.luongThucTe = luongThucTe;
        this.luongThuong = luongThuong;
        this.khoanBaoHiem = khoanBaoHiem;
        this.khoanThue = khoanThue;
        this.thuclanh = thuclanh;
        this.luongLamThem = luongLamThem;
        this.ngayNhanLuong = ngayNhanLuong;
        this.maNhanVien = maNhanVien;
    }

    // Getters và Setters
    public int getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(int maLuong) {
        this.maLuong = maLuong;
    }

    public int getMaChamCong() {
        return maChamCong;
    }

    public void setMaChamCong(int maChamCong) {
        this.maChamCong = maChamCong;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public double getLuongThucTe() {
        return luongThucTe;
    }

    public void setLuongThucTe(double luongThucTe) {
        this.luongThucTe = luongThucTe;
    }

    public double getLuongThuong() {
        return luongThuong;
    }

    public void setLuongThuong(double luongThuong) {
        this.luongThuong = luongThuong;
    }

    public double getKhoanBaoHiem() {
        return khoanBaoHiem;
    }

    public void setKhoanBaoHiem(double khoanBaoHiem) {
        this.khoanBaoHiem = khoanBaoHiem;
    }

    public double getKhoanThue() {
        return khoanThue;
    }

    public void setKhoanThue(double khoanThue) {
        this.khoanThue = khoanThue;
    }

    public double getThuclanh() {
        return thuclanh;
    }

    public void setThuclanh(double thuclanh) {
        this.thuclanh = thuclanh;
    }

    public double getLuongLamThem() {
        return luongLamThem;
    }

    public void setLuongLamThem(double luongLamThem) {
        this.luongLamThem = luongLamThem;
    }

    public Date getNgayNhanLuong() {
        return ngayNhanLuong;
    }

    public void setNgayNhanLuong(Date ngayNhanLuong) {
        this.ngayNhanLuong = ngayNhanLuong;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    // Override phương thức toString để dễ kiểm tra dữ liệu
    @Override
    public String toString() {
        return "dtoluong{" +
                "maLuong=" + maLuong +
                ", maChamCong=" + maChamCong +
                ", phuCap=" + phuCap +
                ", luongThucTe=" + luongThucTe +
                ", luongThuong=" + luongThuong +
                ", khoanBaoHiem=" + khoanBaoHiem +
                ", khoanThue=" + khoanThue +
                ", thuclanh=" + thuclanh +
                ", luongLamThem=" + luongLamThem +
                ", ngayNhanLuong='" + ngayNhanLuong + '\'' +
                ", maNhanVien=" + maNhanVien +
                '}';
    }
    public Object[] toTableRowGeneral() {
        //NumberFormat nf = new DecimalFormat("$ #,##0.##");
        busnhanvien busnv = new busnhanvien();
        String tennhanvien = busnv.gettennvbymanv(maNhanVien);
        return new Object[]{maLuong , tennhanvien, phuCap, luongThucTe, luongThuong, khoanBaoHiem, khoanThue, thuclanh, luongLamThem,ngayNhanLuong};
    }
}
