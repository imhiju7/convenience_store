package gui.simple;

import bus.buschucnang; // Import lớp bus của chức năng
import bus.busdanhmuc; // Import lớp bus của danh mục
import com.formdev.flatlaf.FlatClientProperties;
import dto.dtochucnang; // Import lớp DTO của chức năng
import dto.dtodanhmuc; // Import lớp DTO của danh mục
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

public class SimpleInputFunctionForm extends JPanel {
    public JTextField txtTenChucNang; // Trường nhập tên chức năng
    public JComboBox<String> cmbDanhMuc; // ComboBox cho danh mục
    private buschucnang busChucNang; // Khai báo lớp bus chức năng
    private busdanhmuc busDanhMuc; // Khai báo lớp bus danh mục

    public SimpleInputFunctionForm() throws SQLException {
        busChucNang = new buschucnang(); // Khởi tạo bus chức năng
        busDanhMuc = new busdanhmuc(); // Khởi tạo bus danh mục
        init();
    }

    private void init() throws SQLException {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        txtTenChucNang = new JTextField();
        cmbDanhMuc = new JComboBox<>();

        // Add components to panel
        createTitle("Thông tin chức năng");

        // Mã chức năng sẽ được tạo tự động, không cần trường nhập
        add(new JLabel("Tên chức năng"), "gapy 5 0");
        add(txtTenChucNang);

        // Thêm combo box danh mục
        add(new JLabel("Chọn danh mục"), "gapy 5 0");
        loadDanhMuc(); // Load danh mục vào combo box
        add(cmbDanhMuc);

        // Listener cho phím Enter để submit form
        txtTenChucNang.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == 10) {
                    // Code để xử lý khi bấm tổ hợp phím
                    addChucNang();
                }
            }
        });
    }

    // Hàm để load danh mục vào ComboBox
    public void loadDanhMuc() throws SQLException {
        List<dtodanhmuc> danhMucList = busDanhMuc.getlist(); // Lấy danh sách danh mục
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        // Thêm tên danh mục vào combobox
        for (dtodanhmuc dm : danhMucList) {
            model.addElement(dm.getTenDanhMuc());
        }

        cmbDanhMuc.setModel(model); // Cập nhật combo box với danh sách danh mục
    }

    public void addChucNang() {
        try {
            String tenChucNang = txtTenChucNang.getText();
            if (tenChucNang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên chức năng không được để trống!");
                return;
            }

            // Lấy mã danh mục từ combo box
            int selectedIndex = cmbDanhMuc.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn danh mục!");
                return;
            }

            // Lấy mã danh mục từ danh sách danh mục
            List<dtodanhmuc> danhMucList = busDanhMuc.getlist();
            int maDanhMuc = danhMucList.get(selectedIndex).getMaDanhMuc();

            dtochucnang chucNang = new dtochucnang(0, tenChucNang, maDanhMuc, 0); // Mã chức năng sẽ được tạo tự động
            busChucNang.add(chucNang); // Gọi phương thức thêm từ bus

            JOptionPane.showMessageDialog(this, "Chức năng đã được thêm thành công!");
            txtTenChucNang.setText(""); // Xóa các trường nhập sau khi thêm

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi không xác định: " + e.getMessage());
            e.printStackTrace(); // In ra lỗi để kiểm tra
        }
    }

    public dtochucnang getChucNang() throws SQLException {
        buschucnang bus = new buschucnang(); // Khởi tạo bus chức năng
        busdanhmuc busDanhMuc = new busdanhmuc(); // Khởi tạo bus danh mục
        List<dtodanhmuc> danhMucList = busDanhMuc.getlist(); // Lấy danh sách danh mục từ bus

        // Lấy mã danh mục từ combo box
        int selectedIndex = cmbDanhMuc.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn danh mục!");
            return null; // Hoặc throw Exception nếu cần
        }

        // Gán mã danh mục từ danh sách danh mục
        int maDanhMuc = danhMucList.get(selectedIndex).getMaDanhMuc();

        // Gán mã chức năng tự động (lấy số lượng chức năng hiện tại và cộng thêm 1)
        int maChucNang = bus.getSoLuongChucNang() + 1;

        // Gán tên chức năng từ text field
        String tenChucNang = txtTenChucNang.getText().trim();
        if (tenChucNang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên chức năng không được để trống!");
            return null; // Hoặc throw Exception nếu cần
        }

        int isDelete = 0;  
        dtochucnang chucNang = new dtochucnang(maChucNang, tenChucNang, maDanhMuc, isDelete);

        return chucNang; // Trả về đối tượng dtochucnang
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

        // Tạo JFrame và thêm SimpleInputFunctionForm vào JFrame
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chức năng quản lý chức năng"); // Tiêu đề của cửa sổ
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng chương trình khi tắt cửa sổ

            try {
                frame.setContentPane(new SimpleInputFunctionForm()); // Thêm panel vào JFrame
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.pack(); // Điều chỉnh kích thước JFrame theo kích thước của các thành phần
            frame.setLocationRelativeTo(null); // Đặt JFrame ở giữa màn hình
            frame.setVisible(true); // Hiển thị JFrame
        });
    }
}
