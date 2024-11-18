package dto;

public class dtonhacungcap {
    private int maNhaCungCap;
    private String tenNhaCungCap;
    private String SDT;
    private String Email;
    private String diaChi;
    private int isDelete; 

    
    public dtonhacungcap() {}


    public dtonhacungcap(int maNhaCungCap, String tenNhaCungCap, String SDT, String Email, String diaChi, int isDelete) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.SDT = SDT;
        this.Email = Email;
        this.diaChi = diaChi;
        this.isDelete = isDelete;
    }

   
    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
