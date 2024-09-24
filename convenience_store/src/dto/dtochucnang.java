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

    private int machucnang;
    private String tenchucnang;
    private int madanhmuc;
    
    public dtochucnang() {
    }

    public dtochucnang(int machucnang, String tenchucnang, int madanhmuc) {
        this.machucnang = machucnang;
        this.tenchucnang = tenchucnang;
        this.madanhmuc = madanhmuc;
    }

    public int getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(int machucnang) {
        this.machucnang = machucnang;
    }

    public String getTenchucnang() {
        return tenchucnang;
    }

    public void setTenchucnang(String tenchucnang) {
        this.tenchucnang = tenchucnang;
    }

    public int getMadanhmuc() {
        return madanhmuc;
    }

    public void setMadanhmuc(int madanhmuc) {
        this.madanhmuc = madanhmuc;
    }

    @Override
    public String toString() {
        return "dtochucnang{" + "machucnang=" + machucnang + ", tenchucnang=" + tenchucnang + ", madanhmuc=" + madanhmuc + '}';
    }  
}
