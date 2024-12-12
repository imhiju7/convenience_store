package gui.simple;

import bus.busnhacungcap; // Import lớp bus của nhà cung cấp
import com.formdev.flatlaf.FlatClientProperties;
import dto.dtonhacungcap; // Import lớp DTO của nhà cung cấp
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class SimpleInputSupplierForm extends JPanel {
    public JTextField txtTenNhaCungCap; // Trường nhập tên nhà cung cấp
    public JTextField txtSDT;           // Trường nhập số điện thoại nhà cung cấp
    public JTextField txtEmail;         // Trường nhập email nhà cung cấp
    public JTextField txtDiaChi;        // Trường nhập địa chỉ nhà cung cấp
    private busnhacungcap busNhaCungCap; // Khai báo lớp bus nhà cung cấp

    public SimpleInputSupplierForm() throws SQLException {
        busNhaCungCap = new busnhacungcap(); // Khởi tạo bus nhà cung cấp
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        txtTenNhaCungCap = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();
        txtDiaChi = new JTextField();

        // Add components to panel
        createTitle("Thông tin nhà cung cấp");
        
        add(new JLabel("Tên nhà cung cấp"), "gapy 5 0");
        add(txtTenNhaCungCap);
        
        add(new JLabel("Số điện thoại"), "gapy 5 0");
        add(txtSDT);
        
        add(new JLabel("Email"), "gapy 5 0");
        add(txtEmail);
        
        add(new JLabel("Địa chỉ"), "gapy 5 0");
        add(txtDiaChi);

        // Listener cho phím Enter để submit form
        txtDiaChi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == 10) {
                    // Xử lý khi bấm tổ hợp phím
                    addNhaCungCap();
                }
            }
        });
    }

    public void addNhaCungCap() {
        try {
            String tenNhaCungCap = txtTenNhaCungCap.getText().trim();
            String SDT = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            String diaChi = txtDiaChi.getText().trim();

            // Kiểm tra các trường thông tin có trống không
            if (tenNhaCungCap.isEmpty() || SDT.isEmpty() || email.isEmpty() || diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                return;
            }

            // Kiểm tra định dạng số điện thoại (chỉ chứa 10 chữ số)
            if (!SDT.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! Vui lòng nhập đúng 10 chữ số.");
                txtSDT.requestFocus();
                return;
            }

            // Kiểm tra định dạng email
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ! Vui lòng nhập đúng định dạng (ví dụ: example@mail.com).");
                txtEmail.requestFocus();
                return;
            }

            // Kiểm tra xem nhà cung cấp đã tồn tại chưa
            if (busNhaCungCap.isNhaCungCapExists(tenNhaCungCap, SDT, email)) {
                JOptionPane.showMessageDialog(this, "Nhà cung cấp đã tồn tại (tên, số điện thoại hoặc email bị trùng)!");
                return;
            }

            // Tạo đối tượng nhà cung cấp và thêm vào cơ sở dữ liệu
            dtonhacungcap nhaCungCap = new dtonhacungcap(0, tenNhaCungCap, SDT, email, diaChi, 0); // Mã nhà cung cấp sẽ được tạo tự động
            busNhaCungCap.add(nhaCungCap);

            JOptionPane.showMessageDialog(this, "Nhà cung cấp đã được thêm thành công!");

            // Xóa dữ liệu trên form sau khi thêm thành công
            txtTenNhaCungCap.setText("");
            txtSDT.setText("");
            txtEmail.setText("");
            txtDiaChi.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi không xác định: " + e.getMessage());
            e.printStackTrace();
        }
    }




    public dtonhacungcap getNhaCungCap() throws SQLException {
        busnhacungcap bus = new busnhacungcap(); // Khởi tạo bus nhà cung cấp
        dtonhacungcap nhaCungCap = new dtonhacungcap();

        // Gán mã nhà cung cấp tự động
        nhaCungCap.setMaNhaCungCap(bus.getSoLuongNhaCungCap() + 1); // Tăng tự động
        nhaCungCap.setTenNhaCungCap(txtTenNhaCungCap.getText().trim());
        nhaCungCap.setSDT(txtSDT.getText().trim());
        nhaCungCap.setEmail(txtEmail.getText().trim());
        nhaCungCap.setDiaChi(txtDiaChi.getText().trim());

        return nhaCungCap; // Trả về đối tượng dtonhacungcap
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
            JFrame frame = new JFrame("Chức năng quản lý nhà cung cấp");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                frame.setContentPane(new SimpleInputSupplierForm());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
