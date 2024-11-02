/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.model;

/**
 *
 * @author ASUS
 */
public class ModelPositions {
     public int getPositionsId() {
        return positionsId;
    }

    public void setPositionsId(int positionsId) {
        this.positionsId = positionsId;
    }

    public String getPositionsName() {
        return positionsName;
    }

    public void setPositionsName(String positionsName) {
        this.positionsName = positionsName;
    }

    public ModelPositions(int positionsId, String positionsName) {
        this.positionsId = positionsId;
        this.positionsName = positionsName;
    }

    public ModelPositions() {
    }

    private int positionsId;
    private String positionsName;

    @Override
    public String toString() {
        return positionsName;
    }
}
