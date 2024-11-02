package gui.simple;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SimpleInputSupplierForm extends JPanel {

    public SimpleInputSupplierForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        JTextField txtMaNhaCungUng = new JTextField();
        JTextField txtTenNhaCungUng = new JTextField();
        JTextField txtDiaChi = new JTextField();
        JTextField txtSoDienThoai = new JTextField();

        // Add components to panel
        createTitle("Thông tin nhà cung ứng");
        
        add(new JLabel("Mã nhà cung ứng"), "gapy 5 0");
        add(txtMaNhaCungUng);
        
        add(new JLabel("Tên nhà cung ứng"), "gapy 5 0");
        add(txtTenNhaCungUng);
        
        add(new JLabel("Địa chỉ"), "gapy 5 0");
        add(txtDiaChi);
        
        add(new JLabel("Số điện thoại"), "gapy 5 0");
        add(txtSoDienThoai);

        // Listener for Enter key to submit form
        txtSoDienThoai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == 10) {
                    // Code to handle form submission
                    System.out.println("Đã thêm");
                }
            }
        });
    }

    private void createTitle(String title) {
        JLabel lb = new JLabel(title);
        lb.putClientProperty(FlatClientProperties.STYLE, "font:+2");
        add(lb, "gapy 5 0");
        add(new JSeparator(), "height 2!,gapy 0 0");
    }
}
