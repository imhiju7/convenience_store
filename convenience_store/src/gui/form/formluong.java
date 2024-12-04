/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.form;

import bus.busluong;
import gui.comp.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import dao.daoluong;
import dto.dtoluong;
import gui.modal.ModalDialog;
import gui.modal.component.SimpleModalBorder;
import gui.modal.option.Location;
import gui.modal.option.Option;
import gui.simple.SimpleInputSalaryForm;
import gui.simple.SimpleInputSupplierForm;
import gui.table.CheckBoxTableHeaderRenderer;
import gui.table.TableHeaderAlignment;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class formluong extends JPanel {

    private JTabbedPane tabLuong;
    private JTable table; // Biến instance cho table
    private DefaultTableModel model; // Biến instance cho model
    private busluong busLuong;

    public formluong() {
        init();
        busLuong = new busluong();
    }

    private void init() {
        tabLuong = new JTabbedPane();

        // Tạo bảng lương
        JPanel panelLuong = createLuongTable();
        tabLuong.addTab("Bảng Lương", panelLuong);

        // Thêm JTabbedPane vào GUI
        this.setLayout(new java.awt.BorderLayout());
        this.add(tabLuong, java.awt.BorderLayout.CENTER);
    }

    private JPanel createLuongTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // Tạo mô hình bảng lương
        Object columns[] = new Object[]{"CHỌN", "MÃ LƯƠNG", "TÊN NHÂN VIÊN", "PHỤ CẤP",
            "LƯƠNG THỰC TẾ", "LƯƠNG THƯỞNG", "KHOẢN BẢO HIỂM",
            "KHOẢN THUẾ", "THỰC LÃNH", "LƯƠNG LÀM THÊM",
            "NGÀY NHẬN LƯƠNG", "MÃ CHẤM CÔNG", "MÃ NHÂN VIÊN"};

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Cho phép chỉnh sửa tại cột 0 cho checkbox
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };

        // Tạo bảng và gán model cho bảng
        table = new JTable(model);
        loadDataToTable(); // Tải dữ liệu vào bảng

        // Cuộn bảng
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Thiết lập kích thước cột
        table.getColumnModel().getColumn(0).setMaxWidth(50); // Cột checkbox
        table.getColumnModel().getColumn(1).setMinWidth(50); // Mã lương
        table.getColumnModel().getColumn(10).setPreferredWidth(150);
        table.getColumnModel().getColumn(11).setMinWidth(0);
        table.getColumnModel().getColumn(11).setMaxWidth(0);
        table.getColumnModel().getColumn(11).setPreferredWidth(0);
        table.getColumnModel().getColumn(12).setMaxWidth(0);
        table.getColumnModel().getColumn(12).setMinWidth(0);
        table.getColumnModel().getColumn(12).setPreferredWidth(0);

        // Vô hiệu hóa việc thay đổi thứ tự cột
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // Thiết lập kiểu cho bảng và tiêu đề
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;background:$Table.background;");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30;hoverBackground:null;");
        table.putClientProperty(FlatClientProperties.STYLE, "rowHeight:70;showHorizontalLines:true;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "trackArc:$ScrollBar.thumbArc;");

        // Tạo tiêu đề
        JLabel title = new JLabel("Bảng Lương");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20");

        // Thêm header và separator vào panel
        panel.add(createLuongHeaderAction());
        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");
        panel.add(scrollPane, "span, grow");

        return panel;
    }

    private Component createLuongHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        // Tìm kiếm
        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,
                 new FlatSVGIcon("source/image/icon/search.svg", 0.4f));

        // Chọn tháng
        JComboBox<String> cboMonth = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            cboMonth.addItem(String.format("Tháng %02d", i));
        }

        // Chọn năm
        JComboBox<Integer> cboYear = new JComboBox<>();
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = currentYear - 10; i <= currentYear + 1; i++) {
            cboYear.addItem(i);
        }

        // Nút chức năng
        JButton cmdCreate = new JButton("Thêm");
        JButton cmdEdit = new JButton("Sửa");

        JButton cmdExportExcel = new JButton("Xuất Excel");

        // Tạo bộ lọc khi thay đổi tháng hoặc năm
        cboMonth.addActionListener(e -> filterTableByMonthAndYear((String) cboMonth.getSelectedItem(), (Integer) cboYear.getSelectedItem()));
        cboYear.addActionListener(e -> filterTableByMonthAndYear((String) cboMonth.getSelectedItem(), (Integer) cboYear.getSelectedItem()));

        // Thêm sự kiện tìm kiếm (nếu cần)
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(txtSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(txtSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(txtSearch.getText());
            }
        });

        // Thêm sự kiện cho các nút (Thêm, Sửa, Xóa, Xuất Excel)
        cmdCreate.addActionListener(e -> {
            try {
                showLuongModal();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi mở form thêm lương: " + ex.getMessage());
            }
        });

        cmdEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một bản ghi lương để chỉnh sửa.");
            } else {
                int maLuong = (int) model.getValueAt(row, 1);
                try {
                    showEditLuongModal(maLuong);
                } catch (SQLException ex) {
                    Logger.getLogger(formluong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        cmdExportExcel.addActionListener(e -> exportTableToExcel());

        // Thêm các thành phần vào panel
        panel.add(txtSearch);
        panel.add(cboMonth, "gapx 10");
        panel.add(cboYear, "gapx 10");
        panel.add(cmdCreate);
        panel.add(cmdEdit);
        panel.add(cmdExportExcel);
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");

        return panel;
    }

    private void loadDataToTable() {
        model.setRowCount(0); // Xóa dữ liệu cũ
        daoluong dao = new daoluong();
        ArrayList<dtoluong> list = dao.getList();

        for (dtoluong luong : list) {
            // Lấy tên nhân viên từ mã nhân viên
            String tenNhanVien = dao.getTenNhanVienById(luong.getMaNhanVien());

            Object[] row = {
                false, // Checkbox
                luong.getMaLuong(),
                tenNhanVien, // Đưa tên nhân viên vào sau mã lương
                luong.getPhuCap(),
                luong.getLuongThucTe(),
                luong.getLuongThuong(),
                luong.getKhoanBaoHiem(),
                luong.getKhoanThue(),
                luong.getThuclanh(),
                luong.getLuongLamThem(),
                luong.getNgayNhanLuong(),
                luong.getMaChamCong(), // Mã chấm công (ẩn)
                luong.getMaNhanVien() // Mã nhân viên (ẩn)
            };
            model.addRow(row);
        }
    }

    private void filterTableByMonthAndYear(String selectedMonth, Integer selectedYear) {
        if (selectedMonth == null || selectedYear == null) {
            return;
        }

        // Lấy tháng từ chuỗi như "Tháng 01", "Tháng 02",...
        int month;
        try {
            month = Integer.parseInt(selectedMonth.split(" ")[1]); // "Tháng 01" -> 1
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tháng không hợp lệ!");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Tạo bộ lọc dựa trên tháng và năm
        RowFilter<DefaultTableModel, Object> filter = new RowFilter<>() {
            @Override
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String ngayNhanLuong = (String) entry.getValue(10); // Giả sử cột 10 là ngày nhận lương
                if (ngayNhanLuong == null || ngayNhanLuong.isEmpty()) {
                    return false; // Ẩn nếu giá trị ngày nhận lương rỗng
                }
                try {
                    // Phân tích ngày nhận lương thành các giá trị tháng và năm
                    String[] parts = ngayNhanLuong.split("-");
                    int rowYear = Integer.parseInt(parts[0]);   // YYYY-MM-DD -> Năm
                    int rowMonth = Integer.parseInt(parts[1]); // YYYY-MM-DD -> Tháng
                    return rowYear == selectedYear && rowMonth == month;
                } catch (Exception e) {
                    return false; // Ẩn nếu định dạng ngày không đúng
                }
            }
        };

        sorter.setRowFilter(filter);
    }


    private void filterTable(String query) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Chỉ định các cột cần áp dụng bộ lọc
        // Giả sử cột 1 là "Mã chấm công", cột 2 là "Tên nhân viên"
        int[] filterColumns = {1, 2};

        // Tạo RowFilter áp dụng regex cho tất cả cột trong filterColumns
        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        for (int col : filterColumns) {
            filters.add(RowFilter.regexFilter("(?i)" + query, col)); // Lọc không phân biệt hoa thường
        }

        // Kết hợp các RowFilter bằng OR logic
        RowFilter<Object, Object> combinedFilter = RowFilter.orFilter(filters);
        sorter.setRowFilter(combinedFilter);
    }

    private void showLuongModal() throws SQLException {
        // Tạo đối tượng Option cho ModalDialog (tuỳ chỉnh thêm nếu cần)
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f).setLocation(Location.TRAILING, Location.TOP);

        // Tạo form nhập liệu lương mới
        SimpleInputSalaryForm inputSalaryForm = new SimpleInputSalaryForm();

        // Hiển thị modal với form
        ModalDialog.showModal(this, new SimpleModalBorder(
                 inputSalaryForm, "Thêm thông tin lương", SimpleModalBorder.YES_NO_OPTION,
                 (controller, action) -> {
                     // Xử lý hành động của người dùng
                     if (action == SimpleModalBorder.YES_OPTION) {
                         // Hiển thị thông báo xác nhận trước khi thêm mới lương
                         int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thêm thông tin lương này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                         if (result == JOptionPane.YES_OPTION) {
                             try {
                                 // Thêm thông tin lương
                                 inputSalaryForm.addLuong();
                                 loadDataToTable(); // Tải lại dữ liệu vào bảng sau khi thêm mới
                             } catch (Exception e) {
                                 JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm thông tin lương: " + e.getMessage());
                                 e.printStackTrace();
                             }
                         }
                     }
                     if (action == SimpleModalBorder.NO_OPTION) {
                         // Đóng dialog khi nhấn No
                         controller.close();
                     }
                 }), option);
    }

    private void showEditLuongModal(int maLuong) throws SQLException {
        // Lấy thông tin lương từ cơ sở dữ liệu dựa trên maLuong
        dtoluong luong = busLuong.getLuongById(maLuong); // Giả sử có phương thức getLuongById() trong busLuong

        if (luong == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin lương với mã lương " + maLuong);
            return;
        }

        // Tạo đối tượng Option cho ModalDialog
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f).setLocation(Location.TRAILING, Location.TOP);

        // Tạo form nhập liệu lương và điền sẵn các giá trị của lương hiện tại
        SimpleInputSalaryForm inputSalaryForm = new SimpleInputSalaryForm();
        inputSalaryForm.setDefaultValues(luong); // Điền thông tin lương vào form

        // Hiển thị modal với form
        ModalDialog.showModal(this, new SimpleModalBorder(
                 inputSalaryForm, "Chỉnh sửa thông tin lương", SimpleModalBorder.YES_NO_OPTION,
                 (controller, action) -> {
                     // Xử lý hành động của người dùng
                     if (action == SimpleModalBorder.YES_OPTION) {
                         // Hiển thị thông báo xác nhận trước khi cập nhật thông tin lương
                         int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn chỉnh sửa thông tin lương này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                         if (result == JOptionPane.YES_OPTION) {
                             try {
                                 // Cập nhật thông tin lương
                                 inputSalaryForm.updateLuong(maLuong); // Phương thức updateLuong sẽ thực hiện cập nhật vào cơ sở dữ liệu
                                 loadDataToTable(); // Tải lại dữ liệu vào bảng sau khi cập nhật
                             } catch (Exception e) {
                                 JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi chỉnh sửa thông tin lương: " + e.getMessage());
                                 e.printStackTrace();
                             }
                         }
                     }
                     if (action == SimpleModalBorder.NO_OPTION) {
                         // Đóng dialog khi nhấn No
                         controller.close();
                     }
                 }), option);
    }

    private void exportTableToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Bảng lương");
                Row headerRow = sheet.createRow(0);

                // Lưu các cột cần hiển thị
                List<Integer> visibleColumns = new ArrayList<>();
                for (int col = 0; col < table.getColumnCount(); col++) {
                    String columnName = table.getColumnName(col);
                    if (!columnName.equalsIgnoreCase("Mã chấm công")
                             && !columnName.equalsIgnoreCase("Mã nhân viên")
                             && !columnName.equalsIgnoreCase("Chọn")) {
                        visibleColumns.add(col);
                    }
                }

                // Thêm tiêu đề cột
                int excelCol = 0;
                for (int col : visibleColumns) {
                    Cell cell = headerRow.createCell(excelCol++);
                    cell.setCellValue(table.getColumnName(col));
                }

                // Thêm dữ liệu bảng
                for (int row = 0; row < table.getRowCount(); row++) {
                    Row excelRow = sheet.createRow(row + 1);
                    excelCol = 0;
                    for (int col : visibleColumns) {
                        Object value = table.getValueAt(row, col);
                        Cell cell = excelRow.createCell(excelCol++);
                        if (value != null) {
                            if (value instanceof Number) {
                                cell.setCellValue(((Number) value).doubleValue());
                            } else {
                                cell.setCellValue(value.toString());
                            }
                        }
                    }
                }

                // Tự động chỉnh kích thước cột
                for (int col = 0; col < visibleColumns.size(); col++) {
                    sheet.autoSizeColumn(col);
                }

                // Ghi dữ liệu ra file
                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    workbook.write(fos);
                }

                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công: " + filePath);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Thiết lập Look and Feel
        try {
            // Sử dụng FlatLaf cho giao diện hiện đại
            com.formdev.flatlaf.FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("Không thể thiết lập giao diện FlatLaf: " + ex.getMessage());
        }

        // Khởi chạy GUI trong luồng Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Tạo cửa sổ chính
            JFrame frame = new JFrame("Quản lý lương");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800); // Thiết lập kích thước
            frame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình

            // Thêm panel quản lý lương
            formluong panelLuong = new formluong();
            frame.add(panelLuong);

            // Hiển thị cửa sổ
            frame.setVisible(true);
        });
    }
}
