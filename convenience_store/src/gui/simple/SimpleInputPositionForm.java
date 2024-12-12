package gui.simple;

import bus.buschucvu; // Thêm import cho bus
import com.formdev.flatlaf.FlatClientProperties;
import dto.dtochucvu; // Thêm import cho dto
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class SimpleInputPositionForm extends JPanel {
    public JTextField txtTenChucVu; // Chỉ giữ lại trường tên chức vụ
    private buschucvu busChucVu; // Khai báo bus

    public SimpleInputPositionForm() throws SQLException {
        busChucVu = new buschucvu(); // Khởi tạo bus
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        txtTenChucVu = new JTextField();

        // Add components to panel
        createTitle("Thông tin chức vụ");
        
        // Mã chức vụ sẽ được tạo tự động, không cần trường nhập
        add(new JLabel("Tên chức vụ"), "gapy 5 0");
        add(txtTenChucVu);

        // Listener for Enter key to submit form
        txtTenChucVu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == 10) {
                    // Code to handle form submission
                    addChucVu();
                }
            }
        });
    }

    public void addChucVu() {
    try {
        String tenChucVu = txtTenChucVu.getText().trim();
        
        if (tenChucVu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên chức vụ không được để trống!");
            return;
        }

        // Kiểm tra tên chức vụ đã tồn tại hay chưa
        if (busChucVu.isTenChucVuExists(tenChucVu)) {
            JOptionPane.showMessageDialog(this, "Tên chức vụ đã tồn tại, vui lòng nhập tên khác!");
            return;
        }

        // Tạo đối tượng dtochucvu
        dtochucvu chucvu = new dtochucvu(0, tenChucVu, 0); // Mã chức vụ sẽ được tạo tự động
        busChucVu.addchucvu(chucvu); // Gọi phương thức thêm từ bus

        JOptionPane.showMessageDialog(this, "Chức vụ đã được thêm thành công!");
        txtTenChucVu.setText(""); // Xóa trường nhập sau khi thêm thành công

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi không xác định: " + e.getMessage());
        e.printStackTrace();
    }
}


    public dtochucvu getChucVu() throws SQLException {
        buschucvu bus = new buschucvu(); // Khởi tạo bus
        dtochucvu chucvu = new dtochucvu();

        // Gán mã chức vụ tự động
        chucvu.setMachucvu(bus.getSoLuongChucVu() + 1); // Tăng tự động
        chucvu.setTenchucvu(txtTenChucVu.getText().trim()); // Gán tên chức vụ

        // Đối với tên chức năng, bỏ qua nếu không cần thiết
        // chucvu.setTenChucNang(txtTenChucNang.getText().trim()); // Nếu bạn có trường tên chức năng
        return chucvu; // Trả về đối tượng dtochucvu
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

        // Tạo JFrame và thêm SimpleInputPositionForm vào JFrame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chức năng quản lý chức vụ"); // Tiêu đề của cửa sổ
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng chương trình khi tắt cửa sổ

            try {
                frame.setContentPane(new SimpleInputPositionForm()); // Thêm panel vào JFrame
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.pack(); // Điều chỉnh kích thước JFrame theo kích thước của các thành phần
            frame.setLocationRelativeTo(null); // Đặt JFrame ở giữa màn hình
            frame.setVisible(true); // Hiển thị JFrame
        });
    }
}
