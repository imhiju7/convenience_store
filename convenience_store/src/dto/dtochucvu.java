/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Hieu PC
 */
public class dtochucvu {

    private int machucvu;
    private String tenchucvu;
    
    public dtochucvu() {
    }

    public dtochucvu(int machucvu, String tenchucvu) {
        this.machucvu = machucvu;
        this.tenchucvu = tenchucvu;
    }

    public int getMachucvu() {
        return machucvu;
    }

    public void setMachucvu(int machucvu) {
        this.machucvu = machucvu;
    }

    public String getTenchucvu() {
        return tenchucvu;
    }

    public void setTenchucvu(String tenchucvu) {
        this.tenchucvu = tenchucvu;
    }

    @Override
    public String toString() {
        return "dtochucvu{" + "machucvu=" + machucvu + ", tenchucvu=" + tenchucvu + '}';
    }    
}
