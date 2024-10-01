/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author giavi
 */
public class dtotichdiem {
    public dtotichdiem(){}
    public dtotichdiem(int maTichDiem, double tien, int diemTichLuy,int isHidden) {
        this.maTichDiem = maTichDiem;
        this.tien = tien;
        this.diemTichLuy = diemTichLuy;
        this.isHidden = isHidden;
    }

    public int getMaTichDiem() {
        return maTichDiem;
    }

    public void setMaTichDiem(int maTichDiem) {
        this.maTichDiem = maTichDiem;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }
    private int maTichDiem;
    private double tien;
    private int diemTichLuy;

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }
    private int isHidden;
}
