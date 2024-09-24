/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Hieu PC
 */
public class dtodanhmuc {

    private int madanhmuc;
    private String tendanhmuc;
    private String icon;
    
    public dtodanhmuc() {
    }

    public dtodanhmuc(int madanhmuc, String tendanhmuc, String icon) {
        this.madanhmuc = madanhmuc;
        this.tendanhmuc = tendanhmuc;
        this.icon = icon;
    }

    public int getMadanhmuc() {
        return madanhmuc;
    }

    public void setMadanhmuc(int madanhmuc) {
        this.madanhmuc = madanhmuc;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "dtodanhmuc{" + "madanhmuc=" + madanhmuc + ", tendanhmuc=" + tendanhmuc + ", icon=" + icon + '}';
    }
}
