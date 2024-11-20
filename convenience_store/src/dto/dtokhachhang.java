/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author giavi
 */
public class dtokhachhang {
    private int maKhachHang;
    private String SDT;
    private String tenKhachHang;
    private int diemTichLuy;
    private int maUudai;

    public dtokhachhang() {}

    public dtokhachhang(int maKhachHang, String SDT, String tenKhachHang, int diemTichLuy, int maUudai) {
        this.maKhachHang = maKhachHang;
        this.SDT = SDT;
        this.tenKhachHang = tenKhachHang;
        this.diemTichLuy = diemTichLuy;
        this.maUudai = maUudai;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public int getMaUudai() {
        return maUudai;
    }

    public void setMaUudai(int maUudai) {
        this.maUudai = maUudai;
    }
}
