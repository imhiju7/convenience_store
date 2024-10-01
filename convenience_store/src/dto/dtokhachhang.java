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
    public dtokhachhang(){}
    public dtokhachhang(int maKhachHang, String tenKhachHang, String SDT, int diemTichLuy, int maUuDai) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.SDT = SDT;
        this.diemTichLuy = diemTichLuy;
        this.maUuDai = maUuDai;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public int getMaUuDai() {
        return maUuDai;
    }

    public void setMaUuDai(int maUuDai) {
        this.maUuDai = maUuDai;
    }
    
    private int maKhachHang;
    private String tenKhachHang;
    private String SDT;
    private int diemTichLuy;
    private int maUuDai;
}
