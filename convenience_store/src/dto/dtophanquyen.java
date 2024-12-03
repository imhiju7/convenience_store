/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

public class dtophanquyen {
    private int maPhanQuyen;
    private int maChucVu;
    private int maChucNang;

    // Constructor không tham số
    public dtophanquyen() {
    }

    // Constructor có tham số
    public dtophanquyen(int maPhanQuyen, int maChucVu, int maChucNang) {
        this.maPhanQuyen = maPhanQuyen;
        this.maChucVu = maChucVu;
        this.maChucNang = maChucNang;
    }

    // Getter và Setter cho maPhanQuyen
    public int getMaPhanQuyen() {
        return maPhanQuyen;
    }

    public void setMaPhanQuyen(int maPhanQuyen) {
        this.maPhanQuyen = maPhanQuyen;
    }

    // Getter và Setter cho maChucVu
    public int getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(int maChucVu) {
        this.maChucVu = maChucVu;
    }

    // Getter và Setter cho maChucNang
    public int getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(int maChucNang) {
        this.maChucNang = maChucNang;
    }

    // Phương thức toString() để in thông tin phân quyền
    @Override
    public String toString() {
        return "dtophanquyen{" +
                "maPhanQuyen=" + maPhanQuyen +
                ", maChucVu=" + maChucVu +
                ", maChucNang=" + maChucNang +
                '}';
    }
}

