/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author giavi
 */
public class dtouudai {
   private int maUuDai;
   private int mocUuDai;
   private int tiLeGiam;
   private int isDelete;

    public dtouudai() {
    }

    public dtouudai(int maUuDai, int mocUuDai, int tiLeGiam, int isDelete) {
        this.maUuDai = maUuDai;
        this.mocUuDai = mocUuDai;
        this.tiLeGiam = tiLeGiam;
        this.isDelete = isDelete;
    }

    public int getMaUuDai() {
        return maUuDai;
    }

    public int getMocUuDai() {
        return mocUuDai;
    }

    public int getTiLeGiam() {
        return tiLeGiam;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setMaUuDai(int maUuDai) {
        this.maUuDai = maUuDai;
    }

    public void setMocUuDai(int mocUuDai) {
        this.mocUuDai = mocUuDai;
    }

    public void setTiLeGiam(int tiLeGiam) {
        this.tiLeGiam = tiLeGiam;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "dtouudai{" + "maUuDai=" + maUuDai + ", mocUuDai=" + mocUuDai + ", tiLeGiam=" + tiLeGiam + ", isDelete=" + isDelete + '}';
    }
   
    
}
