/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author giavi
 */
public class dtodonhang {
    private String ten;
    private Integer ma;
    private Integer sl;
    private Double tt;

    public dtodonhang(){
    }
    
    public dtodonhang(String ten, Integer ma, Integer sl, Double tt) {
        this.ten = ten;
        this.ma = ma;
        this.sl = sl;
        this.tt = tt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public Double getTt() {
        return tt;
    }

    public void setTt(Double tt) {
        this.tt = tt;
    }
    
    
    
    
}
