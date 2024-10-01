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
    public int getMaUuDai() {
        return maUuDai;
    }

    public void setMaUuDai(int maUuDai) {
        this.maUuDai = maUuDai;
    }

    public int getMocUuDai() {
        return mocUuDai;
    }

    public void setMocUuDai(int mocUuDai) {
        this.mocUuDai = mocUuDai;
    }

    public int getTiLeGiam() {
        return tiLeGiam;
    }

    public void setTiLeGiam(int tiLeGiam) {
        this.tiLeGiam = tiLeGiam;
    }
    public dtouudai(){}
    public dtouudai(int maUuDai, int mocUuDai, int tiLeGiam,int isHidden) {
        this.maUuDai = maUuDai;
        this.mocUuDai = mocUuDai;
        this.tiLeGiam = tiLeGiam;
    }
    private int maUuDai;
    private int mocUuDai;
    private int tiLeGiam;

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }
    private int isHidden;
}
