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
    private int isDelete;

    
    public dtochucvu() {
    }

    public dtochucvu(int machucvu, String tenchucvu, int isDelete) {
        this.machucvu = machucvu;
        this.tenchucvu = tenchucvu;
        this.isDelete = isDelete;
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
    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
    public String getDropdownDisplay() {
        return tenchucvu;
    }
    
}
