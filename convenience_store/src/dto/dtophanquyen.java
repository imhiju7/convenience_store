/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Hieu PC
 */
public class dtophanquyen {

    private int maphanquyen;
    private int machucvu;
    private int machucnang;
    
    public dtophanquyen() {
    }

    public dtophanquyen(int maphanquyen, int machucvu, int machucnang) {
        this.maphanquyen = maphanquyen;
        this.machucvu = machucvu;
        this.machucnang = machucnang;
    }

    public int getMaphanquyen() {
        return maphanquyen;
    }

    public void setMaphanquyen(int maphanquyen) {
        this.maphanquyen = maphanquyen;
    }

    public int getMachucvu() {
        return machucvu;
    }

    public void setMachucvu(int machucvu) {
        this.machucvu = machucvu;
    }

    public int getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(int machucnang) {
        this.machucnang = machucnang;
    }

    @Override
    public String toString() {
        return "dtophanquyen{" + "maphanquyen=" + maphanquyen + ", machucvu=" + machucvu + ", machucnang=" + machucnang + '}';
    }
}
