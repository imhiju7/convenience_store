package gui.simple;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SimpleInputClassificationForm extends JPanel {

    public SimpleInputClassificationForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        JTextField txtMaPhanLoai = new JTextField();
        JTextField txtTenPhanLoai = new JTextField();
        JTextField txtMoTa = new JTextField();

        // Thêm các thành phần vào panel
        createTitle("Thông tin phân loại");
        
        add(new JLabel("Mã phân loại"), "gapy 5 0");
        add(txtMaPhanLoai);
        
        add(new JLabel("Tên phân loại"), "gapy 5 0");
        add(txtTenPhanLoai);
        

        // Listener cho phím Enter để gửi form
        txtMoTa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == 10) {
                    // Code để xử lý việc gửi form
                    System.out.println("Đã thêm phân loại");
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
