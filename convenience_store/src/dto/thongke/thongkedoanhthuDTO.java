/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.thongke;

/**
 *
 * @author AD
 */
public class thongkedoanhthuDTO {
    private int thoigian;
    private long doanhthu;
    private long chiphi;
    private long loinhuan;

    public thongkedoanhthuDTO(int thoigian, long doanhthu, long chiphi, long loinhuan) {
        this.thoigian = thoigian;
        this.doanhthu = doanhthu;
        this.chiphi = chiphi;
        this.loinhuan = loinhuan;
    }

    public int getThoigian() {
        return thoigian;
    }

    public void setThoigian(int thoigian) {
        this.thoigian = thoigian;
    }

    public long getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(long doanhthu) {
        this.doanhthu = doanhthu;
    }

    public long getChiphi() {
        return chiphi;
    }

    public void setChiphi(long chiphi) {
        this.chiphi = chiphi;
    }

    public long getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(long loinhuan) {
        this.loinhuan = loinhuan;
    }
    
}
