package dto;

/**
 *
 * @author giavi
 */
public class dtophanloai {

    private int maPhanLoai;
    private String tenPhanLoai;
    private int isDelete; 

    // Constructor với tham số isDelete
    public dtophanloai(int maPhanLoai, String tenPhanLoai, int isDelete) {
        this.maPhanLoai = maPhanLoai;
        this.tenPhanLoai = tenPhanLoai;
        this.isDelete = isDelete; 
    }

    // Constructor mặc định
    public dtophanloai() {}

    // Getter và Setter cho maPhanLoai
    public int getMaPhanLoai() {
        return maPhanLoai;
    }

    public void setMaPhanLoai(int maPhanLoai) {
        this.maPhanLoai = maPhanLoai;
    }

    // Getter và Setter cho tenPhanLoai
    public String getTenPhanLoai() {
        return tenPhanLoai;
    }

    public void setTenPhanLoai(String tenPhanLoai) {
        this.tenPhanLoai = tenPhanLoai;
    }

    // Getter và Setter cho isDelete
    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
