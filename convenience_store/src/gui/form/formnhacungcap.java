package gui.form;

import bus.busnhacungcap;
import gui.comp.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import dto.dtonhacungcap;
import gui.modal.ModalDialog;
import gui.modal.component.SimpleModalBorder;
import gui.modal.option.Location;
import gui.modal.option.Option;
import gui.simple.SimpleInputSupplierForm;
import gui.table.CheckBoxTableHeaderRenderer;
import gui.table.TableHeaderAlignment;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class formnhacungcap extends JPanel {

    private JTabbedPane tabnhacungcap;
    private JTable table;
    private DefaultTableModel model;

    public formnhacungcap() {
        init();
    }

    private void init() {
        tabnhacungcap = new JTabbedPane();

        // Tạo bảng nhà cung cấp
        JPanel panelNhaCungCap = createNhaCungCapTable();
        tabnhacungcap.addTab("Nhà Cung Cấp", panelNhaCungCap);

        // Thêm JTabbedPane vào GUI
        this.setLayout(new java.awt.BorderLayout());
        this.add(tabnhacungcap, java.awt.BorderLayout.CENTER);
    }

    private JPanel createNhaCungCapTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // Tạo mô hình bảng nhà cung cấp
        Object columns[] = new Object[]{"CHỌN", "MÃ NHÀ CUNG CẤP", "TÊN NHÀ CUNG CẤP", "SỐ ĐIỆN THOẠI", "EMAIL", "ĐỊA CHỈ"};
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

        // Các thiết lập khác cho bảng
        table.getColumnModel().getColumn(0).setMaxWidth(50); // Cột checkbox
        table.getColumnModel().getColumn(1).setMinWidth(100); // Cột Mã nhà cung cấp
        table.getColumnModel().getColumn(2).setPreferredWidth(200); // Tên nhà cung cấp
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Số điện thoại
        table.getColumnModel().getColumn(4).setPreferredWidth(150); // Email
        table.getColumnModel().getColumn(5).setPreferredWidth(200); // Địa chỉ

        // Vô hiệu hóa việc thay đổi thứ tự cột
        table.getTableHeader().setReorderingAllowed(false);
    

        // Thiết lập renderer cho cột checkbox
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // Thiết lập căn lề cho tiêu đề cột
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
            @Override
            protected int getAlignment(int column) {
                if (column == 1) {
                    return SwingConstants.CENTER;
                }
                return SwingConstants.LEADING;
            }
        });

        // Thiết lập kiểu cho bảng
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;background:$Table.background;");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30;hoverBackground:null;");
        table.putClientProperty(FlatClientProperties.STYLE, "rowHeight:70;showHorizontalLines:true;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "trackArc:$ScrollBar.thumbArc;");

        // Tạo tiêu đề
        JLabel title = new JLabel("Bảng Nhà Cung Cấp");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20");

        // Thêm header
        panel.add(createSupplierHeaderAction());

        // Thêm separator và scrollpane vào panel
        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");
        panel.add(scrollPane, "span, grow");

        return panel;
    }

    private Component createSupplierHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("source/image/icon/search.svg", 0.4f));
        JButton cmdCreate = new JButton("Thêm");
        JButton cmdEdit = new JButton("Sửa");
        JButton cmdDelete = new JButton("Xóa");
        JButton cmdExportExcel = new JButton("Xuất Excel");

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
        cmdCreate.addActionListener(e -> {
            try {
                showSupplierModal();
            } catch (SQLException ex) {
                Logger.getLogger(formnhacungcap.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cmdEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhà cung cấp để chỉnh sửa.");
            } else {
                // Lấy dữ liệu từ hàng đã chọn
                int maNhaCungCap = (int) model.getValueAt(row, 1);
                String tenNhaCungCap = (String) model.getValueAt(row, 2);
                String sdt = (String) model.getValueAt(row, 3);
                String email = (String) model.getValueAt(row, 4);
                String diaChi = (String) model.getValueAt(row, 5);

                // Truyền các tham số cần thiết vào showEditSupplierModal
                showEditSupplierModal(maNhaCungCap, tenNhaCungCap, sdt, email, diaChi, model, row);
            }
        });

        cmdDelete.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = table.getRowCount();

            // Xác nhận từ người dùng
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa các mục đã chọn không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                busnhacungcap bus = new busnhacungcap(); // Tạo đối tượng BUS
                boolean hasError = false; // Cờ kiểm tra lỗi

                // Duyệt qua các hàng từ dưới lên để tránh lỗi khi xóa
                for (int i = rowCount - 1; i >= 0; i--) {
                    Boolean isChecked = (Boolean) model.getValueAt(i, 0); // Cột checkbox
                    if (isChecked != null && isChecked) {
                        try {
                            int maNhaCungCap = (int) model.getValueAt(i, 1); // Cột chứa mã nhà cung cấp
                            bus.delete(maNhaCungCap); // Đánh dấu isDelete trong cơ sở dữ liệu
                            model.removeRow(i); // Xóa khỏi bảng
                        } catch (Exception ex) {
                            hasError = true;
                            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa nhà cung cấp: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                }

                // Thông báo sau khi xóa
                if (!hasError) {
                    JOptionPane.showMessageDialog(this, "Đã xóa các mục được chọn thành công.");
                }
            }
        });

        cmdExportExcel.addActionListener(e -> {
            exportSupplierTableToExcel();  // Gọi phương thức xuất Excel
        });

        panel.add(txtSearch);
        panel.add(cmdCreate);
        panel.add(cmdEdit);
        panel.add(cmdDelete);
        panel.add(cmdExportExcel);
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");

        return panel;
    }

    private void exportSupplierTableToExcel() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Workbook workbook = new XSSFWorkbook();  // Tạo một workbook Excel mới
        Sheet sheet = workbook.createSheet("Nhà Cung Cấp");  // Tạo một sheet mới

        // Tạo dòng tiêu đề (header row)
        Row headerRow = sheet.createRow(0);
        // Bỏ qua cột "Chọn" (cột 0), chỉ lấy cột bắt đầu từ 1
        for (int i = 1; i < model.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i - 1);  // Đặt tên cột từ vị trí thứ 2 (cột 1)
            cell.setCellValue(model.getColumnName(i));  // Đặt giá trị của các cột từ tên cột
        }

        // Duyệt qua các hàng và ghi dữ liệu vào file Excel
        for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);  // Dòng đầu tiên là tiêu đề, bắt đầu từ dòng thứ 2
            for (int colIndex = 1; colIndex < model.getColumnCount(); colIndex++) {
                Cell cell = row.createCell(colIndex - 1);  // Ghi dữ liệu từ cột thứ 2
                Object value = model.getValueAt(rowIndex, colIndex);
                if (value != null) {
                    cell.setCellValue(value.toString());  // Đặt giá trị cho ô
                }
            }
        }

        // Lưu file Excel
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Lưu file Excel");
            fileChooser.setSelectedFile(new File("Danh_sach_nha_cung_cap.xlsx"));
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                FileOutputStream fileOut = new FileOutputStream(fileToSave);
                workbook.write(fileOut);  // Ghi workbook vào file
                fileOut.close();
                workbook.close();
                JOptionPane.showMessageDialog(this, "Đã xuất file Excel thành công!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + ex.getMessage());
        }
    }

    private void filterTable(String query) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Tạo bộ lọc cho cột tên và mã (cột 1 và cột 2 giả định là mã và tên nhà cung cấp)
        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + query, 1, 2);  // Lọc theo tên và mã
        sorter.setRowFilter(rf);
    }

    private void showSupplierModal() throws SQLException {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f).setLocation(Location.TRAILING, Location.TOP);
        SimpleInputSupplierForm supplierForm = new SimpleInputSupplierForm();

        ModalDialog.showModal(this, new SimpleModalBorder(
                 supplierForm, "Thêm nhà cung cấp", SimpleModalBorder.YES_NO_OPTION,
                 (controller, action) -> {
                     if (action == SimpleModalBorder.YES_OPTION) {
                         int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thêm nhà cung cấp này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                         if (result == JOptionPane.YES_OPTION) {
                             try {
                                 supplierForm.addNhaCungCap();
                                 loadDataToTable(); // Tải lại dữ liệu sau khi thêm
                             } catch (Exception e) {
                                 JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm nhà cung cấp: " + e.getMessage());
                                 e.printStackTrace();
                             }
                         }
                     }
                     if (action == SimpleModalBorder.NO_OPTION) {
                         controller.close();
                     }
                 }), option);
    }


 private void showEditSupplierModal(int maNhaCungCap, String tenNhaCungCap, String SDT, String email, String diaChi, DefaultTableModel model, int row) {
    try {
        // Tạo một form SimpleInputSupplierForm mới
        SimpleInputSupplierForm supplierForm = new SimpleInputSupplierForm();
        int currentIsDelete = 0;

        // Thiết lập dữ liệu ban đầu vào form
        supplierForm.txtTenNhaCungCap.setText(tenNhaCungCap);
        supplierForm.txtSDT.setText(SDT);
        supplierForm.txtEmail.setText(email);
        supplierForm.txtDiaChi.setText(diaChi);

        // Hiển thị form dưới dạng hộp thoại
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
            .setLocation(Location.TRAILING, Location.TOP)
            .setAnimateDistance(0.7f, 0);

        ModalDialog.showModal(this, new SimpleModalBorder(
            supplierForm, "Chỉnh sửa nhà cung cấp", SimpleModalBorder.YES_NO_OPTION,
            (controller, action) -> {
                if (action == SimpleModalBorder.YES_OPTION) {
                    // Lấy dữ liệu mới từ form
                    String newTenNhaCungCap = supplierForm.txtTenNhaCungCap.getText().trim();
                    String newSDT = supplierForm.txtSDT.getText().trim();
                    String newEmail = supplierForm.txtEmail.getText().trim();
                    String newDiaChi = supplierForm.txtDiaChi.getText().trim();

                    // Kiểm tra tính hợp lệ của dữ liệu
                    if (!newTenNhaCungCap.isEmpty() && !newSDT.isEmpty() && !newEmail.isEmpty() && !newDiaChi.isEmpty()) {
                        // Cập nhật đối tượng và cơ sở dữ liệu
                        dtonhacungcap nhaCungCap = new dtonhacungcap(maNhaCungCap, newTenNhaCungCap, newSDT, newEmail, newDiaChi, currentIsDelete);
                        busnhacungcap bus = new busnhacungcap();
                        bus.update(nhaCungCap); // Cập nhật vào cơ sở dữ liệu
                        model.setValueAt(newTenNhaCungCap, row, 2);
                        model.setValueAt(newSDT, row, 3);
                        model.setValueAt(newEmail, row, 4);
                        model.setValueAt(newDiaChi, row, 5);
                        JOptionPane.showMessageDialog(null, "Nhà cung cấp đã được cập nhật thành công!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                    }
                }
                if (action == SimpleModalBorder.NO_OPTION) {
                    controller.close();
                }
            }
        ), option);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi mở form chỉnh sửa nhà cung cấp: " + e.getMessage());
        e.printStackTrace();
    }
}


    private void loadDataToTable() {
        // Cập nhật dữ liệu trong bảng từ busnhacungcap
        busnhacungcap busNCC = new busnhacungcap();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        for (dtonhacungcap ncc : busNCC.dsncc) {
            model.addRow(new Object[]{false, ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSDT(), ncc.getEmail(), ncc.getDiaChi()});
        }
    }

    public static void createAndShowSupplierGUI() {
        JFrame frame = new JFrame("Quản lý Nhà Cung Cấp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new formnhacungcap();
        frame.add(panel);
        frame.setSize(1070, 741);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("sample.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();

        SwingUtilities.invokeLater(formnhacungcap::createAndShowSupplierGUI);
    }
}
