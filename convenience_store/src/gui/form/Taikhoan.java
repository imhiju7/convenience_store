    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package gui.form;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.net.URL;
    import bus.busnhanvien;
    import bus.bustaikhoan;
    import dto.dtotaikhoan;
    import dto.dtonhanvien;
    import java.util.ArrayList;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    public class Taikhoan extends JFrame {
    private JPanel mainPanel;
    private JPanel detailPanel;
    private JPanel searchPanel; // Thêm panel tìm kiếm
    private Timer slideInTimer, slideOutTimer;
    private boolean detailPanelVisible = false;
    private String manv;
    private busnhanvien busnv;
    private bus.buschucvu busChucVu;
    private JTextField searchField; // Trường tìm kiếm
    private JComboBox<String> searchComboBox; // ComboBox tìm kiếm

    public Taikhoan() {
    busnv = new busnhanvien();  // Khởi tạo BUS để lấy dữ liệu từ DAO
    busChucVu = new bus.buschucvu();  // Khởi tạo BUS chức vụ
    setTitle("Quản lý tài khoản nhân viên");
    setSize(1070, 741);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

        // Tạo và thêm panel tìm kiếm
    searchPanel = new JPanel();
    searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Thay đổi thành CENTER để căn giữa
    searchPanel.setPreferredSize(new Dimension(1070, 40));
    searchPanel.setBackground(Color.DARK_GRAY);

    // Tìm kiếm
    JLabel searchLabel = new JLabel("Tìm kiếm:");
    searchLabel.setForeground(Color.WHITE);
    searchPanel.add(searchLabel);

    // Trường tìm kiếm
    searchField = new JTextField(20); // 20 ký tự
    searchPanel.add(searchField);

    // ComboBox tìm kiếm
    String[] searchOptions = {"Tên nhân viên", "Chức vụ", "Trạng thái"};
    searchComboBox = new JComboBox<>(searchOptions);
    searchPanel.add(searchComboBox);

    // Nút tìm kiếm
    JButton searchButton = new JButton("Tìm");
    searchButton.addActionListener(e -> searchEmployees());
    searchPanel.add(searchButton);

    // Nút reset
    JButton resetButton = new JButton("Reset");
    resetButton.addActionListener(e -> resetSearch());
    searchPanel.add(resetButton);

    // Thêm panel tìm kiếm vào đầu
    getContentPane().add(searchPanel, BorderLayout.NORTH); 
    searchField.setPreferredSize(new Dimension(200, 30)); // Chiều rộng 200, chiều cao 30
    searchComboBox.setPreferredSize(new Dimension(150, 30)); // Chiều rộng 150, chiều cao 30
    searchButton.setPreferredSize(new Dimension(80, 30)); // Chiều rộng 80, chiều cao 30
    resetButton.setPreferredSize(new Dimension(80, 30)); // Chiều rộng 80, chiều cao 30

    // Khởi tạo mainPanel với JScrollPane
    mainPanel = new JPanel(new GridLayout(0, 3, 10, 10));  // Bắt đầu với 3 cột
    JScrollPane scrollPane = new JScrollPane(mainPanel);
//    scrollPane.setPreferredSize(new Dimension(1070, 701)); // Đặt kích thước cho JScrollPane
//    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Luôn hiển thị thanh cuộn dọc
    getContentPane().add(scrollPane, BorderLayout.CENTER); // Thêm JScrollPane vào JFrame

    // Lấy danh sách nhân viên từ BUS
    ArrayList<dtonhanvien> employees = busnv.list_nv;

    // Duyệt qua danh sách nhân viên và thêm vào panel
    for (dtonhanvien nv : employees) {
        JPanel employeePanel = createEmployeePanel(nv.getTennhanvien(), nv.getMachucvu(), nv.getImg(), nv.getManhanvien());
        mainPanel.add(employeePanel);
    }

    // Khởi tạo panel chi tiết, ẩn nó đi và không thêm vào layout ngay
    detailPanel = new JPanel();
    detailPanel.setPreferredSize(new Dimension(0, getHeight()));
    detailPanel.setBackground(Color.LIGHT_GRAY);
    detailPanel.setLayout(null);
}

// Phương thức tìm kiếm nhân viên
private void searchEmployees() {
    String searchText = searchField.getText().trim();
    String selectedOption = (String) searchComboBox.getSelectedItem();

    // Clear main panel
    mainPanel.removeAll();

    // Lấy danh sách nhân viên từ BUS
    ArrayList<dtonhanvien> employees = busnv.list_nv;

    // Duyệt qua danh sách nhân viên và tìm kiếm
    for (dtonhanvien nv : employees) {
        boolean matches = false;

        // Lấy thông tin tài khoản từ manhanvien
        bustaikhoan bustk = new bustaikhoan();
        dtotaikhoan tk = bustk.getlist().stream()
                .filter(account -> account.getManhanvien() == nv.getManhanvien())
                .findFirst()
                .orElse(null);

        // Kiểm tra nếu tài khoản không null để lấy trạng thái
        String status = (tk != null && tk.getIsblock() == 1) ? "Đã khóa" : "Hoạt động";

        switch (selectedOption) {
            case "Tên nhân viên":
                matches = nv.getTennhanvien().toLowerCase().contains(searchText.toLowerCase());
                break;
            case "Chức vụ":
                String position = getPositionName(nv.getMachucvu());
                matches = position.toLowerCase().contains(searchText.toLowerCase());
                break;
            case "Trạng thái":
                matches = status.toLowerCase().contains(searchText.toLowerCase());
                break;
        }
        if (matches) {
            JPanel employeePanel = createEmployeePanel(nv.getTennhanvien(), nv.getMachucvu(), nv.getImg(), nv.getManhanvien());
            mainPanel.add(employeePanel);
        }
    }

    mainPanel.revalidate();
    mainPanel.repaint();
}

// Phương thức reset tìm kiếm
private void resetSearch() {
    searchField.setText("");
    searchComboBox.setSelectedIndex(0);
    mainPanel.removeAll();

    // Lấy danh sách nhân viên từ BUS
    ArrayList<dtonhanvien> employees = busnv.list_nv;

    // Duyệt qua danh sách nhân viên và thêm vào panel
    for (dtonhanvien nv : employees) {
        JPanel employeePanel = createEmployeePanel(nv.getTennhanvien(), nv.getMachucvu(), nv.getImg(), nv.getManhanvien());
        mainPanel.add(employeePanel);
    }

    mainPanel.revalidate();
    mainPanel.repaint();
}

// Chỉnh sửa phương thức để nhận thêm mã nhân viên
private JPanel createEmployeePanel(String name, int positionId, String imagePath, int manhanvien) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    panel.setPreferredSize(new Dimension(0, 200));
    panel.setBackground(Color.LIGHT_GRAY);

    URL imageUrl = getClass().getResource(imagePath);
    ImageIcon imageIcon = null;
    if (imageUrl != null) {
        imageIcon = new ImageIcon(imageUrl);
    }
    JLabel imageLabel = new JLabel(imageIcon);
    imageLabel.setPreferredSize(new Dimension(180, 180));
    panel.add(imageLabel, BorderLayout.WEST);

    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
    infoPanel.setBackground(Color.LIGHT_GRAY);

    // Sử dụng HTML để hiển thị tên nhân viên
    JLabel nameLabel = new JLabel("<html>" + name + "</html>");
    nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
    nameLabel.setForeground(Color.WHITE);
    nameLabel.setMaximumSize(new Dimension(300, 80)); // Đặt chiều cao tối đa
    infoPanel.add(nameLabel);

    // Lấy tên chức vụ dựa trên mã chức vụ
    String position = getPositionName(positionId);
    JLabel positionLabel = new JLabel(position);
    positionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    positionLabel.setForeground(Color.WHITE);
    positionLabel.setMaximumSize(new Dimension(300, 60));
    positionLabel.setFont(new Font("Arial", Font.BOLD, 16));
    infoPanel.add(positionLabel);

    JButton viewButton = new JButton("Xem");
    viewButton.addActionListener(e -> showDetailPanel(name, position, manhanvien)); // Truyền mã nhân viên
    infoPanel.add(viewButton);

    panel.add(infoPanel, BorderLayout.CENTER);
    // Thêm một khoảng trống nhỏ giữa tên và chức vụ
    infoPanel.add(Box.createVerticalStrut(5)); // Thay đổi chiều cao nếu cần
    return panel;
}

private String getPositionName(int positionId) {
    // Lấy tên chức vụ từ busChucVu dựa trên mã chức vụ
    return busChucVu.gettencvbymacv(positionId);
}

        private void showDetailPanel(String name, String position, int manhanvien) {
        bustaikhoan bustk = new bustaikhoan();
        dtotaikhoan taikhoan = bustk.getlist().stream()
                                    .filter(tk -> tk.getManhanvien() == manhanvien) // Tìm tài khoản dựa trên mã nhân viên
                                    .findFirst()
                                    .orElse(null);

        if (taikhoan != null) {
            if (!detailPanelVisible) {
                detailPanelVisible = true;
                getContentPane().add(detailPanel, BorderLayout.EAST); // Thêm panel chi tiết vào bên phải
                mainPanel.setLayout(new GridLayout(0, 2, 10, 10));  // Chuyển thành 2 cột
                getContentPane().revalidate();  // Cập nhật lại layout
                slideInDetailPanel(); // Hiệu ứng trượt vào
            }

            detailPanel.removeAll(); // Xóa các thành phần cũ trước khi thêm mới

            // Tiêu đề "Tên nhân viên"
            JLabel nameTitleLabel = new JLabel("Tên nhân viên:");
            nameTitleLabel.setBounds(10, 20, 280, 30);
            nameTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            nameTitleLabel.setForeground(Color.WHITE);
            detailPanel.add(nameTitleLabel);

            // Thông tin tên nhân viên
            JLabel nameLabel = new JLabel(name);
            nameLabel.setBounds(150, 20, 280, 30);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            nameLabel.setForeground(Color.BLACK);
            detailPanel.add(nameLabel);

            // Tiêu đề "Chức vụ"
            JLabel positionTitleLabel = new JLabel("Chức vụ:");
            positionTitleLabel.setBounds(10, 60, 280, 30);
            positionTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            positionTitleLabel.setForeground(Color.WHITE);
            detailPanel.add(positionTitleLabel);

            // Thông tin chức vụ
            JLabel positionLabel = new JLabel(position);
            positionLabel.setBounds(150, 60, 280, 30);
            positionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            positionLabel.setForeground(Color.BLACK);
            detailPanel.add(positionLabel);

            // Tiêu đề "Tên đăng nhập"
            JLabel usernameTitleLabel = new JLabel("Tên đăng nhập:");
            usernameTitleLabel.setBounds(10, 100, 280, 30);
            usernameTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            usernameTitleLabel.setForeground(Color.WHITE);
            detailPanel.add(usernameTitleLabel);

            // Thông tin tên đăng nhập
            JLabel usernameLabel = new JLabel(taikhoan.getTendangnhap());
            usernameLabel.setBounds(150, 100, 280, 30);
            usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            usernameLabel.setForeground(Color.BLACK);
            detailPanel.add(usernameLabel);

            // Tiêu đề "Mật khẩu"
            JLabel passwordTitleLabel = new JLabel("Mật khẩu:");
            passwordTitleLabel.setBounds(10, 140, 280, 30);
            passwordTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            passwordTitleLabel.setForeground(Color.WHITE);
            detailPanel.add(passwordTitleLabel);

            // Thông tin mật khẩu
            JLabel passwordLabel = new JLabel(taikhoan.getMatkhau());
            passwordLabel.setBounds(150, 140, 280, 30);
            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            passwordLabel.setForeground(Color.BLACK);
            detailPanel.add(passwordLabel);

            // Tiêu đề "Ngày tạo"
            JLabel createdDateTitleLabel = new JLabel("Ngày tạo:");
            createdDateTitleLabel.setBounds(10, 180, 280, 30);
            createdDateTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            createdDateTitleLabel.setForeground(Color.WHITE);
            detailPanel.add(createdDateTitleLabel);

            // Thông tin ngày tạo
            // Lấy giá trị ngày tạo từ đối tượng taikhoan
            // Lấy giá trị ngày tạo
            Date createdDate = taikhoan.getNgaytao(); 

            // Kiểm tra nếu giá trị không null
            if (createdDate != null) {
                // Định dạng ngày tháng theo định dạng bạn muốn
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Ví dụ: định dạng ngày tháng năm
                String createdDateString = sdf.format(createdDate); // Chuyển đổi Date thành String

                // Tạo JLabel để hiển thị ngày tạo
                JLabel createdDateLabel = new JLabel(createdDateString);
                createdDateLabel.setBounds(150, 180, 280, 30);
                createdDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                createdDateLabel.setForeground(Color.BLACK);
                detailPanel.add(createdDateLabel);
            } else {
                // Xử lý trường hợp giá trị createdDate là null
                System.out.println("Ngày tạo không hợp lệ!");
            }



            // Tiêu đề "Trạng thái"
            JLabel statusTitleLabel = new JLabel("Trạng thái:");
            statusTitleLabel.setBounds(10, 220, 280, 30);
            statusTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            statusTitleLabel.setForeground(Color.WHITE);
            detailPanel.add(statusTitleLabel);

            // Thông tin trạng thái
            String status = (taikhoan.getIsblock() == 1) ? "Đã khóa" : "Hoạt động"; // Giả sử bạn đã thêm phương thức isblock() trong dtotaikhoan
            JLabel statusLabel = new JLabel(status);
            statusLabel.setBounds(150, 220, 280, 30);
            statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            statusLabel.setForeground(Color.BLACK);
            detailPanel.add(statusLabel);

            // Nút "Sửa"
            JButton editButton = new JButton("Sửa TK");
            editButton.setBounds(100, 280, 80, 30);
            editButton.addActionListener(e -> {
                //Ngăn sửa admin
                if (position.equals("admin")) { // Thêm dấu ngoặc đơn đóng
                    JOptionPane.showMessageDialog(detailPanel, "Không được sửa thông tin admin", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Hiển thị các trường chỉnh sửa khi nhấn "Sửa"
                // Tiêu đề "Tên đăng nhập mới"
                JLabel newUsernameLabel = new JLabel("Tên ĐN mới:");
                newUsernameLabel.setBounds(10, 320, 280, 30);
                newUsernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
                newUsernameLabel.setForeground(Color.WHITE);
                detailPanel.add(newUsernameLabel);

                // Trường để nhập tên đăng nhập mới
                JTextField newUsernameField = new JTextField();
                newUsernameField.setBounds(150, 320, 140, 30);
                detailPanel.add(newUsernameField);

                // Tiêu đề "XN Tên đăng nhập mới"
                JLabel confirmnewUsernameLabel = new JLabel("Xác nhận TĐN:");
                confirmnewUsernameLabel.setBounds(10, 360, 280, 30);
                confirmnewUsernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
                confirmnewUsernameLabel.setForeground(Color.WHITE);
                detailPanel.add(confirmnewUsernameLabel);

                // Trường để nhập tên đăng nhập mới
                JTextField confirmnewUsernameField = new JTextField();
                confirmnewUsernameField.setBounds(150, 360, 140, 30);
                detailPanel.add(confirmnewUsernameField);

                // Tiêu đề "Mật khẩu mới"
                JLabel newPasswordLabel = new JLabel("Mật khẩu mới:");
                newPasswordLabel.setBounds(10, 400, 280, 30);
                newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 18));
                newPasswordLabel.setForeground(Color.WHITE);
                detailPanel.add(newPasswordLabel);

                // Trường để nhập mật khẩu mới
                JPasswordField newPasswordField = new JPasswordField();
                newPasswordField.setBounds(150, 400, 140, 30);
                detailPanel.add(newPasswordField);

                // Tiêu đề "Xác nhận mật khẩu mới"
                JLabel confirmPasswordLabel = new JLabel("Xác nhận MK:");
                confirmPasswordLabel.setBounds(10, 440, 280, 30);
                confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 18));
                confirmPasswordLabel.setForeground(Color.WHITE);
                detailPanel.add(confirmPasswordLabel);

                // Trường để xác nhận mật khẩu mới
                JPasswordField confirmPasswordField = new JPasswordField();
                confirmPasswordField.setBounds(150, 440, 140, 30);
                detailPanel.add(confirmPasswordField);

                // Tiêu đề "Trạng thái mới"
                JLabel newstatusLabel = new JLabel("Trạng thái mới:");
                newstatusLabel.setBounds(10, 480, 280, 30);
                newstatusLabel.setFont(new Font("Arial", Font.BOLD, 18));
                newstatusLabel.setForeground(Color.WHITE);
                detailPanel.add(newstatusLabel);

                // Tạo JComboBox cho trạng thái mới
                String[] statusOptions = {"0", "1"};
                JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
                statusComboBox.setBounds(150, 480, 140, 30);
                statusComboBox.setSelectedIndex(0); // Mặc định chọn "Hoạt động" (tương ứng với giá trị 0)
                detailPanel.add(statusComboBox);


                // Nút "OK" để xác nhận cập nhật
                JButton okButton = new JButton("OK");
                okButton.setBounds(100, 520, 80, 30);
                okButton.addActionListener(evt -> {
                String newUsername = newUsernameField.getText().trim();
                String confirmNewUsername = confirmnewUsernameField.getText().trim();
                String newPassword = new String(newPasswordField.getPassword()).trim();
                String confirmPassword = new String(confirmPasswordField.getPassword()).trim();
                int newStatus = statusComboBox.getSelectedIndex(); // 0: Hoạt động, 1: Đã khóa

                // Kiểm tra nếu các trường không được để trống
                if (newUsername.isEmpty() || confirmNewUsername.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(detailPanel, "Không được để trống các trường thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra nếu tên đăng nhập mới và xác nhận tên đăng nhập không giống nhau
                if (!newUsername.equals(confirmNewUsername)) {
                    JOptionPane.showMessageDialog(detailPanel, "Tên đăng nhập mới và xác nhận tên đăng nhập không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra nếu mật khẩu mới trùng với mật khẩu cũ
                if (newPassword.equals(taikhoan.getMatkhau())) {
                    JOptionPane.showMessageDialog(detailPanel, "Mật khẩu mới không được trùng với mật khẩu cũ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra nếu mật khẩu mới và xác nhận mật khẩu không giống nhau
                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(detailPanel, "Mật khẩu mới và xác nhận mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Nếu các điều kiện đều thỏa mãn, tiến hành cập nhật
                int confirm = JOptionPane.showConfirmDialog(detailPanel, "Bạn có chắc chắn muốn cập nhật thông tin?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Thực hiện cập nhật thông tin tài khoản
                    boolean success = bustk.updateTaiKhoan(manhanvien, newUsername, newPassword, newStatus);

                    if (success) {
                        // Cập nhật thông tin hiển thị trên các JLabel phía trên
                        usernameLabel.setText(newUsername); // Cập nhật tên đăng nhập
                        passwordLabel.setText(newPassword); // Cập nhật mật khẩu
                        statusLabel.setText(newStatus == 1 ? "Đã khóa" : "Hoạt động"); // Cập nhật trạng thái

                        // Hiển thị thông báo cập nhật thành công
                        JOptionPane.showMessageDialog(detailPanel, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        // Cập nhật lại giao diện của panel
                        detailPanel.revalidate();
                        detailPanel.repaint();
                    } else {
                        JOptionPane.showMessageDialog(detailPanel, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

                        detailPanel.add(okButton);
                        // Nút "Đóng" để hủy việc sửa thông tin
                        JButton closeEditButton = new JButton("0 sửa");
                        closeEditButton.setBounds(200, 520, 80, 30);
                        closeEditButton.addActionListener(evt -> {
                            // Xóa bỏ các trường nhập liệu và nút "OK" khi nhấn "Đóng"
                            detailPanel.remove(newUsernameLabel);
                            detailPanel.remove(newUsernameField);
                            detailPanel.remove(confirmnewUsernameLabel);
                            detailPanel.remove(confirmnewUsernameField);
                            detailPanel.remove(newPasswordLabel);
                            detailPanel.remove(newPasswordField);
                            detailPanel.remove(confirmPasswordLabel);
                            detailPanel.remove(confirmPasswordField);
                            detailPanel.remove(newstatusLabel);
                            detailPanel.remove(statusComboBox);
                            detailPanel.remove(okButton);
                            detailPanel.remove(closeEditButton);

                            // Cập nhật lại giao diện để phản ánh các thay đổi
                            detailPanel.revalidate();
                            detailPanel.repaint();
                        });
                        detailPanel.add(closeEditButton);

                        detailPanel.revalidate();
                        detailPanel.repaint();
                    });
                    detailPanel.add(editButton);

                    // Nút "Đóng"
                    JButton closeButton = new JButton("Đóng");
                    closeButton.setBounds(100, 580, 80, 30);
                    closeButton.addActionListener(e -> closeDetailPanel());
                    detailPanel.add(closeButton);

                    detailPanel.revalidate();
                    detailPanel.repaint();
                } else {
                    System.out.println("Không tìm thấy thông tin tài khoản.");
                }
            }


        private void slideInDetailPanel() {
            slideInTimer = new Timer(1, new ActionListener() {
                int width = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (width < 300) {
                        detailPanel.setPreferredSize(new Dimension(width, getHeight()));
                        getContentPane().revalidate();
                        width += 5;
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            slideInTimer.start();
        }

        private void closeDetailPanel() {
            slideOutTimer = new Timer(1, new ActionListener() {
                int width = 300;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (width > 0) {
                        detailPanel.setPreferredSize(new Dimension(width, getHeight()));
                        getContentPane().revalidate();
                        width -= 5;
                    } else {
                        ((Timer) e.getSource()).stop();
                        getContentPane().remove(detailPanel); // Xóa panel chi tiết khi trượt ra
                        mainPanel.setLayout(new GridLayout(0, 3, 10, 10));  // Trả về 3 cột
                        getContentPane().revalidate();  // Cập nhật lại layout
                        detailPanelVisible = false;
                    }
                }
            });
            slideOutTimer.start();
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                Taikhoan frame = new Taikhoan();
                frame.setVisible(true);
            });
        }
    }
