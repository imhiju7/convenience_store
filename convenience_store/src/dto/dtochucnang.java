package dto;

public class dtochucnang {
    private int maChucNang;
    private String tenChucNang;
    private int maDanhMuc;
    private int isDelete;
    


    public dtochucnang(int maChucNang, String tenChucNang, int maDanhMuc,int isDelete) {
        this.maChucNang = maChucNang;
        this.tenChucNang = tenChucNang;
        this.maDanhMuc = maDanhMuc;
        this.isDelete = isDelete;
       
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(int maChucNang) {
        this.maChucNang = maChucNang;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

   

   
}
