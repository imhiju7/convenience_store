/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.simple;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 *
 * @author ASUS
 */
public class SimpleInputPositionForm extends JPanel{
    public SimpleInputPositionForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        JTextField txtMaChucVu = new JTextField();
        JTextField txtTenChucVu = new JTextField();
        JTextField txtTenChucNang = new JTextField();

        // Add components to panel
        createTitle("Thông tin chức vụ");
        
        add(new JLabel("Mã chức vụ"), "gapy 5 0");
        add(txtMaChucVu);
        
        add(new JLabel("Tên chức vụ"), "gapy 5 0");
        add(txtTenChucVu);
        
        add(new JLabel("Tên chức năng"), "gapy 5 0");
        add(txtTenChucNang);

        // Listener for Enter key to submit form
        txtTenChucNang.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == 10) {
                    // Code to handle form submission
                    System.out.println("Form submitted");
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
