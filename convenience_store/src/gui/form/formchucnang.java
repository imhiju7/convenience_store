package gui.form;

import bus.buschucnang;
import dto.dtochucnang;
import dao.daochucnang;
import gui.comp.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import gui.modal.ModalDialog;
import gui.modal.component.SimpleModalBorder;
import gui.modal.option.Location;
import gui.modal.option.Option;
import gui.simple.SimpleInputFunctionForm;
import gui.table.CheckBoxTableHeaderRenderer;
import gui.table.TableHeaderAlignment;
import java.awt.BorderLayout;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class formchucnang extends JPanel {
    private JTabbedPane tabChucNang;
    private JTable table;
    private DefaultTableModel model;
    private buschucnang busChucNang;

    public formchucnang() {
        init();
        busChucNang = new buschucnang();
    }

    private void init() {
        tabChucNang = new JTabbedPane();

        // Tạo bảng chức năng
        JPanel panelChucNang = createChucNangTable();
        tabChucNang.addTab("Danh sách chức năng", panelChucNang);

        // Thêm JTabbedPane vào GUI
        this.setLayout(new BorderLayout());
        this.add(tabChucNang, BorderLayout.CENTER);
    }

    private JPanel createChucNangTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // Tạo mô hình bảng chức năng
        Object columns[] = new Object[]{"CHỌN", "MÃ CHỨC NĂNG", "TÊN CHỨC NĂNG", "TÊN DANH MỤC", "MÃ DANH MỤC", "IS DELETE"};
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

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        

    
        // Thiết lập kích thước cột
        table.getColumnModel().getColumn(0).setMaxWidth(50); // Cột checkbox
        table.getColumnModel().getColumn(1).setMinWidth(50); // Mã chức năng
        table.getColumnModel().getColumn(4).setMaxWidth(0);  // Ẩn cột
        table.getColumnModel().getColumn(4).setMinWidth(0);  // Ẩn cột
        table.getColumnModel().getColumn(4).setPreferredWidth(0);
        table.getColumnModel().getColumn(5).setMinWidth(0); // Ẩn cột isDelete
        table.getColumnModel().getColumn(5).setMaxWidth(0); // Ẩn cột isDelete
        table.getColumnModel().getColumn(5).setPreferredWidth(0);
        

        // Vô hiệu hóa việc thay đổi thứ tự cột
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;background:$Table.background;");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30;");
        table.putClientProperty(FlatClientProperties.STYLE, "rowHeight:70;showHorizontalLines:true;");

        // Tạo tiêu đề
        JLabel title = new JLabel("Danh sách chức năng");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20");

        // Thêm header và separator vào panel
        panel.add(createChucNangHeaderAction());
        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");
        panel.add(scrollPane, "span, grow");

        return panel;
    }

    private Component createChucNangHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        // Tìm kiếm
        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,
                new FlatSVGIcon("/source/image/icon/search.svg", 0.4f));

        // Nút chức năng
        JButton cmdCreate = new JButton("Thêm");
        JButton cmdEdit = new JButton("Sửa");
        JButton cmdDelete = new JButton("Xóa");
        JButton cmdExportExcel = new JButton("Xuất Excel");

        // Thêm sự kiện cho các nút (Thêm, Sửa, Xuất Excel)
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
                showChucNangModal();
            } catch (SQLException ex) {
                Logger.getLogger(formchucnang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cmdEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một bản ghi chức năng để chỉnh sửa.");
            } else {
                int maChucNang = (int) model.getValueAt(row, 1);
                try {
                    showEditChucNangModal(maChucNang);
                } catch (SQLException ex) {
                    Logger.getLogger(formchucnang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cmdDelete.addActionListener(e -> {
            int row = table.getSelectedRow(); // Lấy hàng được chọn
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một bản ghi chức năng để xóa.");
            } else {
                int maChucNang = (int) model.getValueAt(row, 1); // Lấy mã chức năng từ cột 1
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa chức năng này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        if (busChucNang.delete(maChucNang)) { // Gọi phương thức delete() để xóa chức năng
                            JOptionPane.showMessageDialog(this, "Xóa chức năng thành công!");
                            loadDataToTable(); // Tải lại dữ liệu vào bảng sau khi xóa
                        } else {
                            JOptionPane.showMessageDialog(this, "Không tìm thấy chức năng với mã: " + maChucNang);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa chức năng: " + ex.getMessage());
                        Logger.getLogger(formchucnang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

//        cmdDelete.addActionListener(e ->);
        cmdExportExcel.addActionListener(e -> exportTableToExcel());

        // Thêm các thành phần vào panel
        panel.add(txtSearch);
        panel.add(cmdCreate);
        panel.add(cmdEdit);
        panel.add(cmdDelete);
        panel.add(cmdExportExcel);
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");

        return panel;
    }
    private void filterTable(String searchText) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        RowFilter<TableModel, Object> rf = RowFilter.regexFilter("(?i)" + searchText);  // Lọc không phân biệt chữ hoa/thường
        sorter.setRowFilter(rf);
    }

    private void loadDataToTable() {
        model.setRowCount(0); // Xóa dữ liệu cũ
        daochucnang dao = new daochucnang();
        ArrayList<dtochucnang> list = dao.getList();

        for (dtochucnang chucnang : list) {
            String tenDanhMuc = dao.getTenDanhMucById(chucnang.getMaDanhMuc());
        Object[] row = {
            false, // Checkbox
            chucnang.getMaChucNang(),
            chucnang.getTenChucNang(),
            tenDanhMuc, // Tên danh mục
            chucnang.getMaDanhMuc(),
            chucnang.getIsDelete()// Mã danh mục (ẩn)
        };
        model.addRow(row);
    }
    }

    private void showChucNangModal() throws SQLException {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f).setLocation(Location.TRAILING, Location.TOP);

        SimpleInputFunctionForm functionForm = new SimpleInputFunctionForm(); // Form nhập thông tin chức năng
        ModalDialog.showModal(this, new SimpleModalBorder(
                 functionForm, "Thêm Chức Năng", SimpleModalBorder.YES_NO_OPTION,
                 (controller, action) -> {
                     if (action == SimpleModalBorder.YES_OPTION) {
                         try {
                             // Lấy đối tượng chức năng từ form và gọi hàm addChucNang() để thêm chức năng
                             functionForm.addChucNang();
                             loadDataToTable(); // Tải lại dữ liệu vào bảng sau khi thêm mới
                         } catch (Exception e) {
                             JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm chức năng: " + e.getMessage());
                             e.printStackTrace();
                         }
                     }
                     if (action == SimpleModalBorder.NO_OPTION) {
                         controller.close();
                     }
                 }), option);
    }

    private void showEditChucNangModal(int maChucNang) throws SQLException {
    // Lấy thông tin chức năng từ cơ sở dữ liệu dựa trên maChucNang
    dtochucnang chucNang = busChucNang.getById(maChucNang); // Giả sử có phương thức getChucNangById() trong busChucNang

    if (chucNang == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin chức năng với mã " + maChucNang);
        return;
    }

    // Tạo đối tượng Option cho ModalDialog
    Option option = ModalDialog.createOption();
    option.getLayoutOption().setSize(-1, 1f).setLocation(Location.TRAILING, Location.TOP);

    // Tạo form nhập liệu chức năng và điền sẵn các giá trị của chức năng hiện tại
    SimpleInputFunctionForm inputFunctionForm = new SimpleInputFunctionForm();
    inputFunctionForm.setDefaultValues(chucNang); // Điền thông tin chức năng vào form

    // Hiển thị modal với form
    ModalDialog.showModal(this, new SimpleModalBorder(
            inputFunctionForm, "Chỉnh sửa thông tin chức năng", SimpleModalBorder.YES_NO_OPTION,
            (controller, action) -> {
                // Xử lý hành động của người dùng
                if (action == SimpleModalBorder.YES_OPTION) {
                    // Hiển thị thông báo xác nhận trước khi cập nhật thông tin chức năng
                    int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn chỉnh sửa thông tin chức năng này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            // Cập nhật thông tin chức năng
                            inputFunctionForm.updateChucNang(maChucNang); // Phương thức updateChucNang sẽ thực hiện cập nhật vào cơ sở dữ liệu
                            loadDataToTable(); // Tải lại dữ liệu vào bảng sau khi cập nhật
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi chỉnh sửa thông tin chức năng: " + e.getMessage());
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
            Sheet sheet = workbook.createSheet("Chức năng");
            Row headerRow = sheet.createRow(0);

            // Lưu các cột cần hiển thị
            List<Integer> visibleColumns = new ArrayList<>();
            for (int col = 0; col < table.getColumnCount(); col++) {
                String columnName = table.getColumnName(col);
                if (!columnName.equalsIgnoreCase("Chọn") &&
                    !columnName.equalsIgnoreCase("Mã danh mục") &&
                    !columnName.equalsIgnoreCase("IS DELETE")) {
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
        }
    }
}

     public static void main(String[] args) {
        // Chạy giao diện trong luồng sự kiện của Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tạo cửa sổ chính
                JFrame frame = new JFrame("Quản lý Chức Năng");

                // Đặt hình thức cửa sổ
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);

                // Tạo instance của formchucnang và thêm vào JFrame
                formchucnang form = new formchucnang();
                frame.add(form);

                // Hiển thị cửa sổ
                frame.setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
                frame.setVisible(true);
            }
        });
    }
}
