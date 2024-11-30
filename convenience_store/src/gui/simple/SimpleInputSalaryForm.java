package gui.simple;

import bus.buschamcong;
import bus.busluong;
import bus.busnhanvien;
import com.formdev.flatlaf.FlatClientProperties;
import com.toedter.calendar.JDateChooser;
import dto.dtoluong;
import dto.dtochamcong;
import dto.dtonhanvien;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SimpleInputSalaryForm extends JPanel {
    private JComboBox<Integer> cboMaChamCong; // Dropdown cho mã chấm công
    private JComboBox<dtonhanvien> cboTenNhanVien; // Dropdown cho tên nhân viên (kèm mã)
    private JTextField txtPhuCap;
    private JTextField txtLuongThucTe;
    private JTextField txtLuongThuong;
    private JTextField txtKhoanBaoHiem;
    private JTextField txtKhoanThue;
    private JTextField txtLuongLamThem;
    private JDateChooser dateChooserNgayNhanLuong; // Bảng chọn ngày
    private busluong busLuong;
    private buschamcong busChamCong;
    private busnhanvien busNhanVien;

    public SimpleInputSalaryForm() throws SQLException {
        busLuong = new busluong();
        busChamCong = new buschamcong();
        busNhanVien = new busnhanvien();
        init();
    }

    private void init() throws SQLException {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));

        // Tạo các trường nhập liệu
        cboMaChamCong = new JComboBox<>();
        cboTenNhanVien = new JComboBox<>();
        txtPhuCap = new JTextField();
        txtLuongThucTe = new JTextField();
        txtLuongThuong = new JTextField();
        txtKhoanBaoHiem = new JTextField();
        txtKhoanThue = new JTextField();
        txtLuongLamThem = new JTextField();
        dateChooserNgayNhanLuong = new JDateChooser();
        dateChooserNgayNhanLuong.setDateFormatString("dd/MM/yyyy"); // Định dạng ngày

        // Đổ dữ liệu vào dropdown
        populateComboBoxes();

        // Add components to panel
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
        // Lấy danh sách mã chấm công từ bảng chamcong
        List<dtochamcong> chamCongList = busChamCong.getAllChamCong();
        for (dtochamcong chamCong : chamCongList) {
            cboMaChamCong.addItem(chamCong.getMachamcong());
        }

        // Lấy danh sách nhân viên từ bảng nhanvien
        List<dtonhanvien> nhanVienList = busNhanVien.getList();
        for (dtonhanvien nhanVien : nhanVienList) {
            cboTenNhanVien.addItem(nhanVien); // Thêm đối tượng vào dropdown
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
            String ngayNhanLuong = new SimpleDateFormat("dd/MM/yyyy").format(ngayNhanLuongDate);

            // Tạo đối tượng lương
            dtoluong luong = new dtoluong(0, maChamCong, phuCap, luongThucTe, luongThuong, khoanBaoHiem, khoanThue, 0, luongLamThem, ngayNhanLuong, maNhanVien);

            // Thêm lương qua bus
            busLuong.add(luong);

            JOptionPane.showMessageDialog(this, "Thông tin lương đã được thêm thành công!");
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
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chức năng quản lý lương");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                frame.setContentPane(new SimpleInputSalaryForm());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
