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

    private int manhanvien;
    private String tennhanvien;
    private int machucvu;
    private int gioitinh;
    private Date ngaysinh;
    private String diachi;
    private String email;
    private String sdt;
    private int isdelete;
    private Date ngaytao;
    private String img;
    private int mahopdong;
    private float luongcoban;
    private Date ngaylamviec;
    private Date ngayketthuc;
    
    public dtonhanvien() {
    }

    public dtonhanvien(int manhanvien, String tennhanvien, int machucvu, int gioitinh, Date ngaysinh, String diachi, String email, String sdt, int isdelete, Date ngaytao, String img, int mahopdong, float luongcoban, Date ngaylamviec, Date ngayketthuc) {
        this.manhanvien = manhanvien;
        this.tennhanvien = tennhanvien;
        this.machucvu = machucvu;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
        this.isdelete = isdelete;
        this.ngaytao = ngaytao;
        this.img = img;
        this.mahopdong = mahopdong;
        this.luongcoban = luongcoban;
        this.ngaylamviec = ngaylamviec;
        this.ngayketthuc = ngayketthuc;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public int getMachucvu() {
        return machucvu;
    }

    public void setMachucvu(int machucvu) {
        this.machucvu = machucvu;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMahopdong() {
        return mahopdong;
    }

    public void setMahopdong(int mahopdong) {
        this.mahopdong = mahopdong;
    }

    public float getLuongcoban() {
        return luongcoban;
    }

    public void setLuongcoban(float luongcoban) {
        this.luongcoban = luongcoban;
    }

    public Date getNgaylamviec() {
        return ngaylamviec;
    }

    public void setNgaylamviec(Date ngaylamviec) {
        this.ngaylamviec = ngaylamviec;
    }

    public Date getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Date ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    @Override
    public String toString() {
        return "dtonhanvien{" + "manhanvien=" + manhanvien + ", tennhanvien=" + tennhanvien + ", machucvu=" + machucvu + ", gioitinh=" + gioitinh + ", ngaysinh=" + ngaysinh + ", diachi=" + diachi + ", email=" + email + ", sdt=" + sdt + ", isdelete=" + isdelete + ", ngaytao=" + ngaytao + ", img=" + img + ", mahopdong=" + mahopdong + ", luongcoban=" + luongcoban + ", ngaylamviec=" + ngaylamviec + ", ngayketthuc=" + ngayketthuc + '}';
    }
     public String getDropdownDisplay() {
        return tennhanvien;
    }
}
