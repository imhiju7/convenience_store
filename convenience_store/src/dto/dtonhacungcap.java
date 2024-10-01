/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;

/**
 *
 * @author giavi
 */
public class dtonhacungcap {
    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    public String trangthaistr(){
        if(this.trangThai == 1) return "Dừng cung ứng";
        else return "Đang cung ứng";
    }
    public void settrangthaistr(String tt){
        if(tt.equals("Dừng cung ứng")) this.trangThai = 1;
        else this.trangThai = 0;
    }
    public dtonhacungcap(){}
    public dtonhacungcap(int maNhaCungCap, String tenNhaCungCap, String SDT, String Email, String diaChi, int trangThai, Date ngayTao) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.SDT = SDT;
        this.Email = Email;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }
    private int maNhaCungCap;
    private String tenNhaCungCap;
    private String SDT;
    private String Email;
    private String diaChi;
    private int trangThai;
    private Date ngayTao;
}
