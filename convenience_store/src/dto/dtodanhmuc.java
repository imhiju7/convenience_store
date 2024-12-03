package dto;


public class dtodanhmuc {
    private int maDanhMuc;
    private String tenDanhMuc;
    private String icon;
    private int isDelete;

   

    
    public dtodanhmuc(int maDanhMuc, String tenDanhMuc, String icon, int isDelete) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.icon = icon;
        this.isDelete = isDelete;
    }

   
    public dtodanhmuc() {}


    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    
    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
     public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
     public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
     public String getDropdownDisplay() {
        return tenDanhMuc;
    }
   
}
