package gui.simple;

import bus.busluong;
import dao.daoluong; // Thay thế busluong bằng daoluong
import dao.daochamcong;
import dao.daonhanvien;
import dto.dtoluong;
import dto.dtochamcong;
import dto.dtonhanvien;
import com.formdev.flatlaf.FlatClientProperties;
import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SimpleInputSalaryForm extends JPanel {
    private JComboBox<Integer> cboMaChamCong;
    private JComboBox<dtonhanvien> cboTenNhanVien;
    private JTextField txtPhuCap;
    private JTextField txtLuongThucTe;
    private JTextField txtLuongThuong;
    private JTextField txtKhoanBaoHiem;
    private JTextField txtKhoanThue;
    private JTextField txtLuongLamThem;
    private JDateChooser dateChooserNgayNhanLuong;
    private daoluong daoLuong;
    private daochamcong daoChamCong;
    private daonhanvien daoNhanVien;

    public SimpleInputSalaryForm() throws SQLException {
        daoLuong = new daoluong(); 
        daoChamCong = new daochamcong();
        daoNhanVien = new daonhanvien();
        init();
    }

    private void init() throws SQLException {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));

        cboMaChamCong = new JComboBox<>();
        cboTenNhanVien = new JComboBox<>();
        txtPhuCap = new JTextField();
        txtLuongThucTe = new JTextField();
        txtLuongThuong = new JTextField();
        txtKhoanBaoHiem = new JTextField();
        txtKhoanThue = new JTextField();
        txtLuongLamThem = new JTextField();
        dateChooserNgayNhanLuong = new JDateChooser();
        dateChooserNgayNhanLuong.setDateFormatString("dd/MM/yyyy");

        populateComboBoxes();

        createTitle("Thông tin lương");

        add(new JLabel("Mã chấm công"), "gapy 5 0");
        add(cboMaChamCong);

        add(new JLabel("Tên nhân viên"), "gapy 5 0");
        add(cboTenNhanVien);

        add(new JLabel("Phụ cấp"), "gapy 5 0");
        add(txtPhuCap);

        add(new JLabel("Lương thực tế"), "gapy 5 0");
        add(txtLuongThucTe);

        add(new JLabel("Lương thưởng"), "gapy 5 0");
        add(txtLuongThuong);

        add(new JLabel("Khoản bảo hiểm"), "gapy 5 0");
        add(txtKhoanBaoHiem);

        add(new JLabel("Khoản thuế"), "gapy 5 0");
        add(txtKhoanThue);

        add(new JLabel("Lương làm thêm"), "gapy 5 0");
        add(txtLuongLamThem);

        add(new JLabel("Ngày nhận lương"), "gapy 5 0");
        add(dateChooserNgayNhanLuong);
    }

    private void populateComboBoxes() throws SQLException {
        List<dtochamcong> chamCongList = daoChamCong.getAllChamCong();
        for (dtochamcong chamCong : chamCongList) {
            cboMaChamCong.addItem(chamCong.getMachamcong());
        }

        List<dtonhanvien> nhanVienList = daoNhanVien.getNhanVienList();
        for (dtonhanvien nhanVien : nhanVienList) {
            cboTenNhanVien.addItem(nhanVien);
        }
        cboTenNhanVien.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel();
            if (value != null) {
                label.setText(value.getDropdownDisplay());
            }
            return label;
        });
    }

    public void addLuong() {
        try {
            // Regex patterns
            String numberPattern = "^\\d+(\\.\\d+)?$"; // Số thực
            String integerPattern = "^\\d+$"; // Số nguyên

            // Kiểm tra giá trị phụ cấp
            String phuCapText = txtPhuCap.getText().trim();
            if (!phuCapText.isEmpty() && !phuCapText.matches(numberPattern)) {
                JOptionPane.showMessageDialog(this, "Phụ cấp phải là số hợp lệ!");
                return;
            }
            double phuCap = Double.parseDouble(phuCapText.isEmpty() ? "0" : phuCapText);

            // Kiểm tra giá trị lương thực tế
            String luongThucTeText = txtLuongThucTe.getText().trim();
            if (!luongThucTeText.isEmpty() && !luongThucTeText.matches(numberPattern)) {
                JOptionPane.showMessageDialog(this, "Lương thực tế phải là số hợp lệ!");
                return;
            }
            double luongThucTe = Double.parseDouble(luongThucTeText.isEmpty() ? "0" : luongThucTeText);

            // Kiểm tra giá trị lương thưởng
            String luongThuongText = txtLuongThuong.getText().trim();
            if (!luongThuongText.isEmpty() && !luongThuongText.matches(numberPattern)) {
                JOptionPane.showMessageDialog(this, "Lương thưởng phải là số hợp lệ!");
                return;
            }
            double luongThuong = Double.parseDouble(luongThuongText.isEmpty() ? "0" : luongThuongText);

            // Kiểm tra các khoản bảo hiểm
            String khoanBaoHiemText = txtKhoanBaoHiem.getText().trim();
            if (!khoanBaoHiemText.isEmpty() && !khoanBaoHiemText.matches(numberPattern)) {
                JOptionPane.showMessageDialog(this, "Khoản bảo hiểm phải là số hợp lệ!");
                return;
            }
            double khoanBaoHiem = Double.parseDouble(khoanBaoHiemText.isEmpty() ? "0" : khoanBaoHiemText);

            // Kiểm tra khoản thuế
            String khoanThueText = txtKhoanThue.getText().trim();
            if (!khoanThueText.isEmpty() && !khoanThueText.matches(numberPattern)) {
                JOptionPane.showMessageDialog(this, "Khoản thuế phải là số hợp lệ!");
                return;
            }
            double khoanThue = Double.parseDouble(khoanThueText.isEmpty() ? "0" : khoanThueText);

            // Kiểm tra lương làm thêm
            String luongLamThemText = txtLuongLamThem.getText().trim();
            if (!luongLamThemText.isEmpty() && !luongLamThemText.matches(integerPattern)) {
                JOptionPane.showMessageDialog(this, "Lương làm thêm phải là số nguyên hợp lệ!");
                return;
            }
            int luongLamThem = Integer.parseInt(luongLamThemText.isEmpty() ? "0" : luongLamThemText);

            // Kiểm tra ngày nhận lương
            Date ngayNhanLuongDate = dateChooserNgayNhanLuong.getDate();
            if (ngayNhanLuongDate == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày nhận lương!");
                return;
            }
            // Lấy thông tin nhân viên
            dtonhanvien selectedNhanVien = (dtonhanvien) cboTenNhanVien.getSelectedItem();
            int maNhanVien = selectedNhanVien != null ? selectedNhanVien.getManhanvien() : 0;

            // Lấy mã chấm công
            int maChamCong = (int) cboMaChamCong.getSelectedItem();

            // Tạo DTO và lưu vào cơ sở dữ liệu
            dtoluong luong = new dtoluong(0, maChamCong, phuCap, luongThucTe, luongThuong, khoanBaoHiem, khoanThue, 0, luongLamThem, ngayNhanLuongDate, maNhanVien);
            daoLuong.add(luong);

            JOptionPane.showMessageDialog(this, "Thông tin lương đã được thêm thành công!");
            resetFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập không hợp lệ!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void setDefaultValues(dtoluong luong) {
        cboMaChamCong.setSelectedItem(luong.getMaChamCong());

        // Tìm nhân viên từ danh sách
        for (int i = 0; i < cboTenNhanVien.getItemCount(); i++) {
            dtonhanvien nhanVien = cboTenNhanVien.getItemAt(i);
            if (nhanVien.getManhanvien() == luong.getMaNhanVien()) {
                cboTenNhanVien.setSelectedItem(nhanVien);
                break;
            }
        }

        txtPhuCap.setText(String.valueOf(luong.getPhuCap()));
        txtLuongThucTe.setText(String.valueOf(luong.getLuongThucTe()));
        txtLuongThuong.setText(String.valueOf(luong.getLuongThuong()));
        txtKhoanBaoHiem.setText(String.valueOf(luong.getKhoanBaoHiem()));
        txtKhoanThue.setText(String.valueOf(luong.getKhoanThue()));
        txtLuongLamThem.setText(String.valueOf(luong.getLuongLamThem()));
        try {
            Date ngayNhanLuong = new SimpleDateFormat("yyyy-MM-dd").parse(luong.getNgayNhanLuong().toString());
            dateChooserNgayNhanLuong.setDate(ngayNhanLuong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateLuong(int maLuong) {
    try {
        // Lấy giá trị từ các trường nhập liệu
        int maChamCong = (int) cboMaChamCong.getSelectedItem();
        dtonhanvien selectedNhanVien = (dtonhanvien) cboTenNhanVien.getSelectedItem();
        int maNhanVien = selectedNhanVien != null ? selectedNhanVien.getManhanvien() : 0;
        double phuCap = Double.parseDouble(txtPhuCap.getText().isEmpty() ? "0" : txtPhuCap.getText());
        double luongThucTe = Double.parseDouble(txtLuongThucTe.getText().isEmpty() ? "0" : txtLuongThucTe.getText());
        double luongThuong = Double.parseDouble(txtLuongThuong.getText().isEmpty() ? "0" : txtLuongThuong.getText());
        double khoanBaoHiem = Double.parseDouble(txtKhoanBaoHiem.getText().isEmpty() ? "0" : txtKhoanBaoHiem.getText());
        double khoanThue = Double.parseDouble(txtKhoanThue.getText().isEmpty() ? "0" : txtKhoanThue.getText());
        int luongLamThem = Integer.parseInt(txtLuongLamThem.getText().isEmpty() ? "0" : txtLuongLamThem.getText());

        // Lấy ngày từ JDateChooser
        Date ngayNhanLuongDate = dateChooserNgayNhanLuong.getDate();
        if (ngayNhanLuongDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày nhận lương!");
            return;
        }

        // Tạo đối tượng lương
        dtoluong luong = new dtoluong(maLuong, maChamCong, phuCap, luongThucTe, luongThuong, khoanBaoHiem, khoanThue, 0, luongLamThem, ngayNhanLuongDate, maNhanVien);

        // Cập nhật lương qua bus
        busluong bus = new busluong();
        bus.updateLuong(luong); // Giả sử busLuong có hàm này

        JOptionPane.showMessageDialog(this, "Thông tin lương đã được cập nhật thành công!");
        resetFields(); // Xóa các trường nhập liệu
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ! Vui lòng kiểm tra lại.");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi không xác định: " + ex.getMessage());
        ex.printStackTrace();
    }
}


    private void resetFields() {
        cboMaChamCong.setSelectedIndex(0);
        cboTenNhanVien.setSelectedIndex(0);
        txtPhuCap.setText("");
        txtLuongThucTe.setText("");
        txtLuongThuong.setText("");
        txtKhoanBaoHiem.setText("");
        txtKhoanThue.setText("");
        txtLuongLamThem.setText("");
        dateChooserNgayNhanLuong.setDate(null);
    }

    private void createTitle(String title) {
        JLabel lb = new JLabel(title);
        lb.putClientProperty(FlatClientProperties.STYLE, "font:+2");
        add(lb, "gapy 5 0");
        add(new JSeparator(), "height 2!,gapy 0 0");
    }
    
     public static void main(String[] args) {
        // Thiết lập giao diện FlatLaf
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Chạy ứng dụng trong luồng Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Form Nhập Lương");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                // Tạo nội dung là form nhập lương
                SimpleInputSalaryForm form = new SimpleInputSalaryForm();
                frame.setContentPane(form);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
                return;
            }

            // Thiết lập kích thước và hiển thị cửa sổ
            frame.pack();
            frame.setLocationRelativeTo(null); // Hiển thị giữa màn hình
            frame.setVisible(true);
        });
    }
}
