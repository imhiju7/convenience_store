package bus;

import dao.daoluong;
import dao.daonhanvien;
import dao.daochamcong;
import dao.daohopdong;
import dto.dtochamcong;
import dto.dtohopdong;
import dto.dtoluong;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class busluong {
    public ArrayList<dtoluong> dsLuong; // Danh sách lương
    private daoluong daoLuong = new daoluong();
    private daonhanvien daonv = new daonhanvien();
    private daochamcong daocc = new daochamcong();
    private daohopdong daohd = new daohopdong();
    // Lấy thông tin lương theo mã
    public dtoluong getById(int maLuong) {
        return daoLuong.getById(maLuong);
    }
    // Lấy tất cả các bản ghi lương theo khoảng thời gian
    public ArrayList<dtoluong> getlist(Date day1, Date day2) {
        ArrayList<dtoluong> listluong = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(day1);
        ArrayList<dtochamcong> list = daocc.getlisttime(cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
        if(!daoLuong.getByTime(day1, day2).isEmpty()){
            listluong = daoLuong.getByTime(day1, day2);
        }
        else{
            for(dtochamcong cc : list){
                dtoluong l = new dtoluong();
                dtohopdong hd = daohd.gethopdongnhanvien(cc.getManhanvien());
                
                double luongcoban = hd.getLuongCoBan();
                l.setMaChamCong(cc.getMachamcong());
                l.setMaNhanVien(cc.getManhanvien());
                l.setLuongThucTe(cc.getSogiolamviec()*luongcoban);
                l.setLuongLamThem(cc.getSogiolamthem()*luongcoban);
                l.setLuongThuong(0);
                l.setPhuCap(0);
                
                double luongchuathue = cc.getSogiolamviec()*luongcoban + cc.getSogiolamthem()*luongcoban;
                double thuethunhap = 0;

                l.setKhoanThue(thuethunhap);
                l.setKhoanBaoHiem(0);
                l.setThuclanh(luongchuathue - thuethunhap);
                l.setNgayNhanLuong(new Date().toString());
                l.setKhoanTru((cc.getSongaynghi()*luongcoban+cc.getSongaytre()*0.5*luongcoban));

                daoLuong.add(l);
                listluong.add(l);
            }
        }
        return listluong;
    }

    // Constructor khởi tạo busluong và lấy danh sách lương
    public busluong() {
        getlist(); // Lấy danh sách khi khởi tạo
    }

    // Lấy danh sách lương từ DAO
    public void getlist() {
        dsLuong = daoLuong.getList();
    }

    // Phương thức thêm mới thông tin lương
    public void add(dtoluong luong) {
        daoLuong.add(luong); // Thêm thông tin lương qua DAO
        getlist(); // Cập nhật danh sách sau khi thêm
    }

    // Phương thức cập nhật thông tin lương
    public void update(dtoluong luong) {
        daoLuong.update(luong); // Cập nhật thông tin qua DAO
        getlist(); // Cập nhật lại danh sách sau khi cập nhật
    }



    // Phương thức đếm tổng số bản ghi lương
    public int getSoLuongLuong() throws SQLException {
        return daoLuong.countLuong(); // Gọi phương thức đếm từ DAO
    }

    // Phương thức lấy lương theo mã từ danh sách đã tải
    public dtoluong get(int maLuong) {
        for (dtoluong luong : dsLuong) {
            if (luong.getMaLuong() == maLuong) {
                return luong; // Trả về thông tin lương nếu tìm thấy
            }
        }
        return null; // Không tìm thấy
    }

    // Phương thức main để kiểm tra chức năng
    public static void main(String[] args) {
        // Tạo một instance của busluong
        busluong bus = new busluong();

        // In ra danh sách lương
        System.out.println("Danh sách lương:");
        for (dtoluong luong : bus.dsLuong) {
            System.out.println(luong);
        }

        // Thêm một bản ghi lương mới (ví dụ dữ liệu minh họa)
        dtoluong newLuong = new dtoluong(0, 2, 500000, 15000000, 2000000, 500000, 1000000, 13500000, 5, "2024-11-28", 1);
        bus.add(newLuong);

        // In lại danh sách sau khi thêm
        System.out.println("Danh sách lương sau khi thêm:");
        for (dtoluong luong : bus.dsLuong) {
            System.out.println(luong);
        }

        // Lấy thông tin lương theo mã
        int maLuong = 1; // Ví dụ mã cần tìm
        dtoluong luongTimThay = bus.getById(maLuong);
        if (luongTimThay != null) {
            System.out.println("Thông tin lương có mã " + maLuong + ":");
            System.out.println(luongTimThay);
        } else {
            System.out.println("Không tìm thấy lương có mã " + maLuong);
        }
    }
}
