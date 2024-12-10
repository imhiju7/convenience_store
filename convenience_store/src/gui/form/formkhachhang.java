package gui.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import dto.dtokhachhang;
import bus.buskhachhang;
import bus.busuudai;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class formkhachhang extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaKH, txtSDT, txtTenKH, txtDiemTichLuy, txtMaUuDai;
    private JButton btnThem, btnSua, btnHuy;
    private JTextField txtTimKiem;
    private JComboBox<String> cbTimKiem;
    // Khai báo JComboBox bên ngoài để có thể truy cập trong các phương thức khác
    private JComboBox<String> comboBoxMaUuDai;
    private busuudai busUuDai = new busuudai();

    public formkhachhang() {
        initUI();
        loadDataToTable();
    }

    private void initUI() {
        // Table setup
        String[] columns = {"Mã KH", "SĐT", "Tên KH", "Điểm tích lũy", "Mã ưu đãi"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table.setGridColor(Color.GRAY);
        table.setShowGrid(true);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel for displaying data details
        JPanel panelDetails = new JPanel(new GridLayout(5, 2, 10, 10));
        panelDetails.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        panelDetails.add(new JLabel("Mã Khách Hàng:"));
        txtMaKH = new JTextField();
        txtMaKH.setEnabled(false);  // Không cho chỉnh sửa mã khách hàng
        panelDetails.add(txtMaKH);
        panelDetails.add(new JLabel("SĐT:"));
        txtSDT = new JTextField();
        panelDetails.add(txtSDT);
        panelDetails.add(new JLabel("Tên Khách Hàng:"));
        txtTenKH = new JTextField();
        panelDetails.add(txtTenKH);
        panelDetails.add(new JLabel("Điểm tích lũy:"));
        txtDiemTichLuy = new JTextField();
        txtDiemTichLuy.setEnabled(false);  // Không chỉnh sửa điểm tích lũy
        panelDetails.add(txtDiemTichLuy);
        panelDetails.add(new JLabel("Mã ưu đãi:"));
        comboBoxMaUuDai = new JComboBox<>();
        comboBoxMaUuDai.setPreferredSize(new Dimension(200, 25));
        panelDetails.add(comboBoxMaUuDai);
        // Tải danh sách mã ưu đãi
        loadDanhSachMaUuDai();

        // Panel for buttons and search
        JPanel panelTop = createTopPanel();

        // Set layout to BorderLayout
        setLayout(new BorderLayout());

        // Add panel at the top
        add(panelTop, BorderLayout.NORTH);

        // Add table and details panels
        add(scrollPane, BorderLayout.CENTER);
        add(panelDetails, BorderLayout.SOUTH);
    }
        private void loadDanhSachMaUuDai() {
            // Lấy danh sách mã ưu đãi từ BUS
            ArrayList<String> danhSachMaUuDai = busUuDai.layDanhSachMaUuDai();

            // Thêm "0" làm giá trị mặc định
            comboBoxMaUuDai.addItem("1");

            // Thêm các mã ưu đãi vào JComboBox
            for (String maUuDai : danhSachMaUuDai) {
                comboBoxMaUuDai.addItem(maUuDai);
            }
        }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // Search field and combo box
        txtTimKiem = new JTextField(20);
        cbTimKiem = new JComboBox<>(new String[]{"Mã khách hàng", "Tên khách hàng", "Số điện thoại"});
        JButton btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.addActionListener(e -> onSearchCustomer());

        // Action buttons
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnHuy = new JButton("Reset");

        // Set button size
        btnThem.setPreferredSize(new Dimension(70, 30));
        btnSua.setPreferredSize(new Dimension(70, 30));
        btnHuy.setPreferredSize(new Dimension(70, 30));

        // Add components to panel
        panel.add(new JLabel("Tìm kiếm:"));
        panel.add(txtTimKiem);
        panel.add(cbTimKiem);
        panel.add(btnTimKiem);
        panel.add(btnThem);
        panel.add(btnSua);
        panel.add(btnHuy);

        addEventHandlers();  // Attach event handlers

        return panel;
    }

    private void onSearchCustomer() {
        String keyword = txtTimKiem.getText().trim();
        String criteria = cbTimKiem.getSelectedItem().toString();

        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
            return;
        }

        buskhachhang busKH = new buskhachhang();
        ArrayList<dtokhachhang> searchResults = new ArrayList<>();

        switch (criteria) {
            case "Mã KH":
                try {
                    int maKH = Integer.parseInt(keyword);
                    dtokhachhang kh = busKH.getKhachHangById(maKH);
                    if (kh != null) {
                        searchResults.add(kh);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số!");
                    return;
                }
                break;

            case "Tên KH":
                searchResults = busKH.searchKhachHangByName(keyword);
                break;

            case "SĐT":
                searchResults = busKH.searchKhachHangBySDT(keyword);
                break;
        }

        // Hiển thị kết quả tìm kiếm
        model.setRowCount(0);
        for (dtokhachhang kh : searchResults) {
            model.addRow(new Object[]{
                kh.getMaKhachHang(),
                kh.getSDT(),
                kh.getTenKhachHang(),
                kh.getDiemTichLuy(),
                kh.getMaUudai()
            });
        }

        if (searchResults.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!");
        }
    }

    private void loadDataToTable() {
        model.setRowCount(0);
        buskhachhang busKH = new buskhachhang();
        ArrayList<dtokhachhang> listKH = busKH.getAllKhachHang();

        for (dtokhachhang kh : listKH) {
            model.addRow(new Object[]{
                kh.getMaKhachHang(),
                kh.getSDT(),
                kh.getTenKhachHang(),
                kh.getDiemTichLuy(),
                kh.getMaUudai()
            });
        }
    }

    private void addEventHandlers() {
    // Table row selection handler
    table.getSelectionModel().addListSelectionListener(e -> {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            txtMaKH.setText(model.getValueAt(selectedRow, 0).toString());
            txtSDT.setText(model.getValueAt(selectedRow, 1).toString());
            txtTenKH.setText(model.getValueAt(selectedRow, 2).toString());
            txtDiemTichLuy.setText(model.getValueAt(selectedRow, 3).toString());
            comboBoxMaUuDai.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
        }
    });

    // Button handlers
    btnThem.addActionListener(e -> onAddCustomer());
    btnSua.addActionListener(e -> {
        try {
            onEditCustomer();
        } catch (SQLException ex) {
            Logger.getLogger(formkhachhang.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    btnHuy.addActionListener(e -> onReset());  // Gọi onReset khi nhấn Hủy
}


  private void onAddCustomer() {
    // Lấy thông tin từ các trường nhập liệu
    String sdt = txtSDT.getText();
    String tenKH = txtTenKH.getText();
    String diemTichLuyStr = "0";
    String maUuDaiStr = comboBoxMaUuDai.getSelectedItem().toString();
        buskhachhang busKH = new buskhachhang();

    // Kiểm tra nếu các trường dữ liệu trống
    if (sdt.isEmpty() || tenKH.isEmpty() || maUuDaiStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    // Kiểm tra số điện thoại phải là số và có đúng 10 chữ số
    if (!sdt.matches("\\d{10}")) {
        JOptionPane.showMessageDialog(this, "Số điện thoại phải là 10 chữ số!");
        return;
    }
    if (busKH.checkSDTExist(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại trong hệ thống!");
            return;
        }
    // Kiểm tra tên khách hàng chỉ chứa chữ cái và khoảng trắng
    if (!tenKH.matches("[\\p{L}\\s]+")) {
        JOptionPane.showMessageDialog(this, "Tên khách hàng chỉ chứa chữ cái và khoảng trắng!");
        return;
    }

    // Hỏi người dùng xác nhận trước khi thêm khách hàng
    int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm khách hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirmation == JOptionPane.NO_OPTION) {
        return; // Nếu người dùng chọn "No", không thực hiện thêm khách hàng
    }

    try {
//        buskhachhang busKH = new buskhachhang();
        int diemTichLuy = Integer.parseInt(diemTichLuyStr);
        int maUuDai = Integer.parseInt(maUuDaiStr);
        int maKH = busKH.getSoLuongKH() + 1;

        // Tạo đối tượng khách hàng mới
        dtokhachhang newKhachHang = new dtokhachhang(maKH ,sdt, tenKH, diemTichLuy, maUuDai);

        // Thêm khách hàng vào cơ sở dữ liệu
        boolean result = busKH.addKhachHang(newKhachHang);

        if (result) {
            loadDataToTable();  // Tải lại dữ liệu vào bảng
            clearForm();  // Xóa dữ liệu trên form
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm khách hàng!");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Điểm tích lũy và Mã ưu đãi phải là số!");
    }
}
private void onEditCustomer() throws SQLException {
    try {
        // Lấy thông tin từ các trường nhập liệu
        int maKH = Integer.parseInt(txtMaKH.getText());
        String sdt = txtSDT.getText();
        String tenKH = txtTenKH.getText();
        String diemTichLuyStr = txtDiemTichLuy.getText();
        String maUuDaiStr = comboBoxMaUuDai.getSelectedItem().toString();
        buskhachhang busKH = new buskhachhang();

        // Kiểm tra nếu các trường dữ liệu trống
        if (sdt.isEmpty() || tenKH.isEmpty() || diemTichLuyStr.isEmpty() || maUuDaiStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        // Kiểm tra số điện thoại phải là số và có đúng 10 chữ số
        if (!sdt.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là 10 chữ số!");
            return;
        }
       // Kiểm tra nếu số điện thoại mới đã tồn tại nhưng không phải là số điện thoại hiện tại của khách hàng
        dtokhachhang existingCustomer = busKH.getKhachHangById(maKH);
        if (existingCustomer != null && !existingCustomer.getSDT().equals(sdt) && busKH.checkSDTExist(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại trong hệ thống!");
            return;
        }
        // Kiểm tra tên khách hàng chỉ chứa chữ hoa, thường và khoảng trắng
        if (!tenKH.matches("[\\p{L}\\s]+")) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng chỉ chứa chữ cái và khoảng trắng!");
            return;
        }

        // Hỏi người dùng xác nhận trước khi lưu thông tin
        int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu thông tin khách hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.NO_OPTION) {
            return; // Nếu người dùng chọn "No", không thực hiện cập nhật
        }

        // Chuyển đổi điểm tích lũy và mã ưu đãi thành số nguyên
        int diemTichLuy = Integer.parseInt(diemTichLuyStr);
        int maUuDai = Integer.parseInt(maUuDaiStr);

        // Tạo đối tượng khách hàng mới với thông tin đã chỉnh sửa
        dtokhachhang customer = new dtokhachhang(maKH, sdt, tenKH, diemTichLuy, maUuDai);

        // Cập nhật thông tin khách hàng vào cơ sở dữ liệu
//        buskhachhang busKH = new buskhachhang();
        boolean result = busKH.updateKhachHang(customer);

        if (result) {
            loadDataToTable();
            JOptionPane.showMessageDialog(this, "Lưu thông tin khách hàng thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Lưu thông tin khách hàng thất bại!");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho các trường Mã KH, Điểm tích lũy, Mã ưu đãi!");
    }
}


//    private void onDeleteCustomer() {
//        int selectedRow = table.getSelectedRow();
//        if (selectedRow >= 0) {
//            int confirmation = JOptionPane.showConfirmDialog(this,
//                    "Bạn chắc chắn muốn xóa khách hàng này?", 
//                    "Xác nhận xóa", 
//                    JOptionPane.YES_NO_OPTION);
//
//            if (confirmation == JOptionPane.YES_OPTION) {
//                int maKH = Integer.parseInt(txtMaKH.getText());
//
//                buskhachhang busKH = new buskhachhang();
//                boolean result = busKH.deleteKhachHang(maKH);
//
//                if (result) {
//                    model.removeRow(selectedRow);
//                    JOptionPane.showMessageDialog(this, "Xóa khách hàng thành công!");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại!");
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần xóa!");
//        }
//    }

    private int generateNewCustomerCode() {
        buskhachhang busKH = new buskhachhang();
        int newCustomerCode = busKH.getNextCustomerCode();
        return newCustomerCode;
    }

   // Thêm vào hàm clearForm() để reset các trường tìm kiếm và bảng
private void clearForm() {
    txtMaKH.setText("");
    txtSDT.setText("");
    txtTenKH.setText("");
    txtDiemTichLuy.setText("");
    comboBoxMaUuDai.setSelectedItem("1");
    txtTimKiem.setText("");  // Reset ô tìm kiếm
    cbTimKiem.setSelectedIndex(0);  // Đặt lại combo box về giá trị mặc định (Mã KH)
    table.clearSelection();  // Xóa lựa chọn bảng
    loadDataToTable();  // Hiển thị lại toàn bộ dữ liệu trong bảng
}

// Thêm một hàm xử lý sự kiện cho nút Reset
private void onReset() {
    clearForm();  // Gọi clearForm để reset các trường và bảng
}


    private void enableEditing(boolean enable) {
        txtSDT.setEnabled(enable);
        txtTenKH.setEnabled(enable);
        comboBoxMaUuDai.setEnabled(enable);
    }
}
