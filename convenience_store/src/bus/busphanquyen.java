/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dao.daophanquyen;
import dto.dtophanquyen;

import java.util.ArrayList;
/**
 *
 * @author Hieu PC
 */
public class busphanquyen {
    daophanquyen daopq = new daophanquyen();
    
    // get
    
    public boolean checkphanquyen(int macv,int macn){
        return daopq.checkphanquyen(macv, macn);
    }
}
