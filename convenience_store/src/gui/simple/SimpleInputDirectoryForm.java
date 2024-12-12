package gui.simple;

import bus.busdanhmuc; // Import category bus class
import dto.dtodanhmuc; // Import category DTO class
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

public class SimpleInputDirectoryForm extends JPanel {
    public JTextField txtTenDanhMuc; // Field for directory name
    public JLabel lblIcon; // Label to display the selected icon image
    public File selectedIconFile; // File reference for the chosen icon image
    private busdanhmuc busDanhMuc; // Category bus layer

    public SimpleInputDirectoryForm() throws SQLException {
        busDanhMuc = new busdanhmuc(); // Initialize bus layer
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        txtTenDanhMuc = new JTextField();
        lblIcon = new JLabel("Icon", SwingConstants.CENTER);
        lblIcon.setPreferredSize(new Dimension(100, 100));
        lblIcon.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Button to select an icon image
        JButton btnSelectIcon = new JButton("Thêm ảnh");
        btnSelectIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectIconImage();
            }
        });

        // Add components to the panel
        createTitle("Thông tin danh mục");

        // Name input
        add(new JLabel("Tên danh mục"), "gapy 5 0");
        add(txtTenDanhMuc);
        
        // Icon selector
        add(new JLabel("Icon"), "gapy 5 0");
        add(lblIcon, "center");
        add(btnSelectIcon, "center");

        // Submit action on Enter
        txtTenDanhMuc.addActionListener(e -> addDanhmuc());
    }

    private void selectIconImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedIconFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(new ImageIcon(selectedIconFile.getAbsolutePath()).getImage()
                .getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            lblIcon.setIcon(icon);
        }
    }

    public void addDanhmuc() {
        try {
            String tenDanhMuc = txtTenDanhMuc.getText();

            if (tenDanhMuc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên danh mục không được để trống!");
                return;
            }
            if (busDanhMuc.isDanhMucExists(tenDanhMuc)) {
                JOptionPane.showMessageDialog(this, "Tên danh mục đã tồn tại, vui lòng nhập tên khác!");
                return;
            }


            // Kiểm tra nếu người dùng chọn biểu tượng
            String iconPath = null;
            if (selectedIconFile != null) {
                File iconsDir = new File("icons");
                if (!iconsDir.exists()) {
                    iconsDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
                }

                // Sao chép file vào thư mục icons
                File destFile = new File("icons/" + selectedIconFile.getName());
                Files.copy(selectedIconFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                iconPath = destFile.getAbsolutePath(); // Lưu đường dẫn ảnh
            } else {
                // Nếu không có icon, có thể lưu giá trị mặc định (hoặc null nếu bạn cho phép)
                iconPath = "icons/default_icon.png"; // Ví dụ: Lưu một icon mặc định
            }

            // Tạo đối tượng danh mục và thêm vào cơ sở dữ liệu
            dtodanhmuc danhMuc = new dtodanhmuc(0, tenDanhMuc, iconPath,0);
            busDanhMuc.add(danhMuc); // Gọi phương thức add để lưu danh mục

            JOptionPane.showMessageDialog(this, "Danh mục đã được thêm thành công!");
            txtTenDanhMuc.setText(""); // Xóa các trường nhập
            lblIcon.setIcon(null); // Reset biểu tượng
            lblIcon.setText("Chưa chọn biểu tượng");
            selectedIconFile = null;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        }
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
            JFrame frame = new JFrame("Quản lý danh mục");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                frame.setContentPane(new SimpleInputDirectoryForm());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
