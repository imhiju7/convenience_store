/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.daophieunhap;
/**
 *
 * @author giavi
 */
public class busphieunhap {
    private daophieunhap daoPhieuNhap = new daophieunhap();
    public double getTongChiPhi(){
        return daoPhieuNhap.getTongChiPhi();
    }
}
