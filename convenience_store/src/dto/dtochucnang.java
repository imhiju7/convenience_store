/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Hieu PC
 */
public class dtochucnang {

    private int maChucNang;
    private String tenChucNang;
    private int maGroup;
    
    public dtochucnang() {}

    public dtochucnang(int maChucNang, String tenChucNang, int maGroup) {
        this.maChucNang = maChucNang;
        this.tenChucNang = tenChucNang;
        this.maGroup = maGroup;
    }
    
    public int getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(int maChucNang) {
        this.maChucNang = maChucNang;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public int getMaGroup() {
        return maGroup;
    }

    public void setMaGroup(int maGroup) {
        this.maGroup = maGroup;
    }

    @Override
    public String toString() {
        return "dtochucnang{" + "maChucNang=" + maChucNang + ", tenChucNang=" + tenChucNang + ", maGroup=" + maGroup + '}';
    }
}
