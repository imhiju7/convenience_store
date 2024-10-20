package gui.simple;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SimpleInputPermissionForm extends JPanel {

    public SimpleInputPermissionForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        JTextField txtMaPhanQuyen = new JTextField();
        JTextField txtTenPhanQuyen = new JTextField();
        JTextField txtMoTa = new JTextField();

        // Add components to panel
        createTitle("Thông tin phân quyền");
        
        add(new JLabel("Mã phân quyền"), "gapy 5 0");
        add(txtMaPhanQuyen);
        
        add(new JLabel("Tên phân quyền"), "gapy 5 0");
        add(txtTenPhanQuyen);
        
        add(new JLabel("Mô tả"), "gapy 5 0");
        add(txtMoTa);

        // Listener for Enter key to submit form
        txtMoTa.addKeyListener(new KeyAdapter() {
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
