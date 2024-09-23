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
    private int maGroup;
    private String tenGroup;
    private String icon;
    
    public dtodanhmuc() {}
    
    public dtodanhmuc(int maGroup, String tenGroup, String icon) {
        this.maGroup = maGroup;
        this.tenGroup = tenGroup;
        this.icon = icon;
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

    @Override
    public String toString() {
        return "dtodanhmuc{" + "maGroup=" + maGroup + ", tenGroup=" + tenGroup + ", icon=" + icon + '}';
    }
    
}
