/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.comp;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.util.function.Consumer;

public class AccountCard extends JPanel {
    private String username;
    private String role;
    private Consumer<String> onViewClick;
    private boolean isSelected = false;

    public AccountCard(String username, String role, Consumer<String> onViewClick) {
        this.username = username;
        this.role = role;
        this.onViewClick = onViewClick;
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill, insets 0", "[fill]", "[top]"));
        setPreferredSize(new Dimension(250, 150));
        putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:30;" +
                "[light]background:darken($Panel.background,3%);" +
                "[dark]background:lighten($Panel.background,3%);");

        // Header with profile icon (thay thế bằng hình ảnh đại diện của tài khoản nếu có)
        JLabel profileIcon = new JLabel(new ImageIcon(getClass().getResource("/source/image/nhanvien/nv1.png")));
        add(profileIcon, "wrap");

        // Thông tin tài khoản
        JLabel lblUsername = new JLabel("Username: " + username);
        lblUsername.putClientProperty(FlatClientProperties.STYLE, "font:bold +1;");
        JLabel lblRole = new JLabel("Role: " + role);

        JButton btnView = new JButton("View");
        btnView.addActionListener(e -> onViewClick.accept(username));
        btnView.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:999;" +
                "margin:3,25,3,25;" +
                "borderWidth:1;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null;");

        add(lblUsername, "wrap");
        add(lblRole, "wrap");
        add(btnView);
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        updateCardStyle();
    }

    private void updateCardStyle() {
        if (isSelected) {
            putClientProperty(FlatClientProperties.STYLE, "" +
                    "arc:30;" +
                    "[light]background:darken($Panel.background,50%);" +
                    "[dark]background:lighten($Panel.background,50%);");
        } else {
            putClientProperty(FlatClientProperties.STYLE, "" +
                    "arc:30;" +
                    "[light]background:darken($Panel.background,3%);" +
                    "[dark]background:lighten($Panel.background,3%);");
        }
        revalidate();
        repaint();
    }
}
