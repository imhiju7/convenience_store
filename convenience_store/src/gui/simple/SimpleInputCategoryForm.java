package gui.simple;

import bus.busphanloai; // Import lớp bus của phân loại
import com.formdev.flatlaf.FlatClientProperties;
import dto.dtophanloai; // Import lớp DTO của phân loại
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class SimpleInputCategoryForm extends JPanel {
    public JTextField txtTenPhanLoai; // Trường nhập tên phân loại
    private busphanloai busPhanLoai; // Khai báo lớp bus phân loại

    public SimpleInputCategoryForm() throws SQLException {
        busPhanLoai = new busphanloai(); // Khởi tạo bus phân loại
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        txtTenPhanLoai = new JTextField();

        // Add components to panel
        createTitle("Thông tin phân loại");
        
        // Mã phân loại sẽ được tạo tự động, không cần trường nhập
        add(new JLabel("Tên phân loại"), "gapy 5 0");
        add(txtTenPhanLoai);

        // Listener cho phím Enter để submit form
        txtTenPhanLoai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == 10) {
                    // Code để xử lý khi bấm tổ hợp phím
                    addPhanLoai();
                }
            }
        });
    }

    public void addPhanLoai() {
        try {
            String tenPhanLoai = txtTenPhanLoai.getText();
            if (tenPhanLoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên phân loại không được để trống!");
                return;
            }

            // Kiểm tra tên phân loại có bị trùng không
            if (busPhanLoai.checkTenPhanLoaiExists(tenPhanLoai)) {
                JOptionPane.showMessageDialog(this, "Tên phân loại đã tồn tại!");
                return;
            }

            dtophanloai phanloai = new dtophanloai(0, tenPhanLoai, 0); // Mã phân loại sẽ được tạo tự động
            busPhanLoai.add(phanloai); // Gọi phương thức thêm từ bus

            JOptionPane.showMessageDialog(this, "Phân loại đã được thêm thành công!");
            txtTenPhanLoai.setText(""); // Xóa các trường nhập sau khi thêm

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi không xác định: " + e.getMessage());
            e.printStackTrace(); // In ra lỗi để kiểm tra
        }
    }


    public dtophanloai getPhanLoai() throws SQLException {
        busphanloai bus = new busphanloai(); // Khởi tạo bus phân loại
        dtophanloai phanloai = new dtophanloai();

        // Gán mã phân loại tự động
        phanloai.setMaPhanLoai(bus.getSoLuongPhanLoai() + 1); // Tăng tự động
        phanloai.setTenPhanLoai(txtTenPhanLoai.getText().trim()); // Gán tên phân loại

        return phanloai; // Trả về đối tượng dtophanloai
    }

    private void createTitle(String title) {
        JLabel lb = new JLabel(title);
        lb.putClientProperty(FlatClientProperties.STYLE, "font:+2");
        add(lb, "gapy 5 0");
        add(new JSeparator(), "height 2!,gapy 0 0");
    }
    
    public static void main(String[] args) {
        // Thiết lập Look and Feel cho giao diện (FlatLaf)
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tạo JFrame và thêm SimpleInputCategoryForm vào JFrame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chức năng quản lý phân loại"); // Tiêu đề của cửa sổ
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng chương trình khi tắt cửa sổ

            try {
                frame.setContentPane(new SimpleInputCategoryForm()); // Thêm panel vào JFrame
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.pack(); // Điều chỉnh kích thước JFrame theo kích thước của các thành phần
            frame.setLocationRelativeTo(null); // Đặt JFrame ở giữa màn hình
            frame.setVisible(true); // Hiển thị JFrame
        });
    }
}
