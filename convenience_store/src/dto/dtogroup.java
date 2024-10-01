/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author giavi
 */
public class dtogroup {
    public dtogroup() {
        
    }

  
    public int getMaGroup() {
        return maGroup;
    }

    public void setMaGroup(int maGroup) {
        this.maGroup = maGroup;
    }

    public String getTenGroup() {
        return tenGroup;
    }

    public void setTenGroup(String tenGroup) {
        this.tenGroup = tenGroup;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public dtogroup(int maGroup, String tenGroup, String icon, int isdelete) {
        this.maGroup = maGroup;
        this.tenGroup = tenGroup;
        this.icon = icon;
        this.isdelete = isdelete;
    }
    private int maGroup;
    private String tenGroup;
    private String icon;
    private int isdelete;

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }
}
