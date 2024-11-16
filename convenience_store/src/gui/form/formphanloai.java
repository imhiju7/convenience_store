/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.form;

import bus.busphanloai;
import gui.comp.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import dao.daophanloai;
import dto.dtophanloai;
import gui.modal.ModalDialog;
import gui.modal.component.SimpleModalBorder;
import gui.modal.option.Location;
import gui.modal.option.Option;
import gui.simple.SimpleInputCategoryForm;

import gui.table.CheckBoxTableHeaderRenderer;
import gui.table.TableHeaderAlignment;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.BoxLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author ASUS
 */
public class formphanloai extends JPanel {

    private JTabbedPane tabphanloai;
    private JTable table; // Biến instance cho table
    private DefaultTableModel model; // Biến instance cho model

    public formphanloai() {
        init();
    }

    private void init() {
        tabphanloai = new JTabbedPane();

        // Tạo bảng phân loại
        JPanel panelPhanloai = createPhanLoaiTable();
        tabphanloai.addTab("Phân Loại", panelPhanloai);

        // Thêm JTabbedPane vào GUI
        this.setLayout(new java.awt.BorderLayout());
        this.add(tabphanloai, java.awt.BorderLayout.CENTER);
    }

    private JPanel createPhanLoaiTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // Tạo mô hình bảng phân loại
        Object columns[] = new Object[]{"CHỌN", "MÃ PHÂN LOẠI", "TÊN PHÂN LOẠI"};
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
        table.getColumnModel().getColumn(1).setMinWidth(100); // Cột Mã phân loại
        table.getColumnModel().getColumn(2).setPreferredWidth(200); // Tên phân loại

        // Vô hiệu hóa việc thay đổi thứ tự cột
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // Thiết lập kiểu cho bảng và tiêu đề
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;background:$Table.background;");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30;hoverBackground:null;");
        table.putClientProperty(FlatClientProperties.STYLE, "rowHeight:70;showHorizontalLines:true;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "trackArc:$ScrollBar.thumbArc;");
     

        // Tạo tiêu đề
        JLabel title = new JLabel("Bảng Phân Loại");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20");

        // Thêm header và separator vào panel
        panel.add(createCategoryHeaderAction());
        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");
        panel.add(scrollPane, "span, grow");

        return panel;
    }

    private Component createCategoryHeaderAction() {
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
                showCategoryModal();
            } catch (SQLException ex) {
                Logger.getLogger(formphanloai.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        cmdEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một phân loại để chỉnh sửa.");
            } else {
                int maPhanLoai = (int) model.getValueAt(row, 1);
                String tenPhanLoai = (String) model.getValueAt(row, 2);
                showEditCategoryModal(maPhanLoai, tenPhanLoai, model, row);
            }
        });
        cmdDelete.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = table.getRowCount();

            // Xác nhận từ người dùng
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa các mục đã chọn không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Duyệt qua các hàng từ dưới lên để tránh lỗi khi xóa
                for (int i = rowCount - 1; i >= 0; i--) {
                    Boolean isChecked = (Boolean) model.getValueAt(i, 0);
                    if (isChecked != null && isChecked) {
                        // Lấy mã phân loại từ cột tương ứng (giả sử mã phân loại ở cột thứ 1 - chỉ mục 1)
                        int maPhanLoai = (int) model.getValueAt(i, 1); // Cột 1 chứa mã phân loại

                        // Gọi phương thức delete từ DAO để cập nhật trường isDelete
                        daophanloai dao = new daophanloai();
                        dao.delete(maPhanLoai); // Cập nhật isDelete = 1 trong cơ sở dữ liệu

                        model.removeRow(i); // Xóa hàng khỏi mô hình dữ liệu
                    }
                }

                // Thông báo sau khi xóa
                JOptionPane.showMessageDialog(this, "Đã xóa các mục được chọn.");
            }
        });

        cmdExportExcel.addActionListener(e -> {
        exportTableToExcel();
    });

        panel.add(txtSearch);
        panel.add(cmdCreate);
        panel.add(cmdEdit);
        panel.add(cmdDelete);
        panel.add(cmdExportExcel);
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");

        return panel;
    }
    private void exportTableToExcel() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Workbook workbook = new XSSFWorkbook();  // Tạo một workbook Excel mới
        Sheet sheet = workbook.createSheet("Phân Loại");  // Tạo một sheet mới

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
            fileChooser.setSelectedFile(new File("Danh sách phân loại.xlsx"));
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

        // Tạo bộ lọc cho cột mã và tên phân loại (cột 1 và cột 2 giả định là mã và tên phân loại)
        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + query, 1, 2);  // Lọc theo mã và tên
        sorter.setRowFilter(rf);
    }
    private void showEditCategoryModal(int maPhanLoai, String tenPhanLoai, DefaultTableModel model, int row) {
    try {
        // Tạo một form SimpleInputCategoryForm mới
        SimpleInputCategoryForm categoryForm = new SimpleInputCategoryForm();
        int currentIsDelete = 0;
        // Thiết lập tên phân loại ban đầu vào form
        categoryForm.txtTenPhanLoai.setText(tenPhanLoai);

        // Hiển thị form dưới dạng hộp thoại
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
            .setLocation(Location.TRAILING, Location.TOP)
            .setAnimateDistance(0.7f, 0);

        ModalDialog.showModal(this, new SimpleModalBorder(
            categoryForm, "Chỉnh sửa loại sản phẩm", SimpleModalBorder.YES_NO_OPTION,
            (controller, action) -> {
                if (action == SimpleModalBorder.YES_OPTION) {
                    // Cập nhật tên phân loại mới vào đối tượng
                    String newTenPhanLoai = categoryForm.txtTenPhanLoai.getText().trim();

                    if (!newTenPhanLoai.isEmpty()) {
                        // Cập nhật trong database và bảng
                        dtophanloai phanLoai = new dtophanloai(maPhanLoai, newTenPhanLoai, currentIsDelete );
                        try {
                            busphanloai bus = new busphanloai();
                            bus.updatePhanLoai(phanLoai); // Cập nhật vào cơ sở dữ liệu
                            model.setValueAt(newTenPhanLoai, row, 2); // Cập nhật dữ liệu trong bảng
                            JOptionPane.showMessageDialog(null, "Phân loại đã được cập nhật thành công!");
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi cập nhật phân loại: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Tên phân loại không được để trống!");
                    }
                }
                if (action == SimpleModalBorder.NO_OPTION) {
                    controller.close();
                }
            }
        ), option);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi mở form chỉnh sửa phân loại: " + e.getMessage());
        e.printStackTrace();
    }
}

    private void showCategoryModal() throws SQLException {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f).setLocation(Location.TRAILING, Location.TOP);
        SimpleInputCategoryForm categoryForm = new SimpleInputCategoryForm();

        ModalDialog.showModal(this, new SimpleModalBorder(
                 categoryForm, "Thêm loại sản phẩm", SimpleModalBorder.YES_NO_OPTION,
                 (controller, action) -> {
                     if (action == SimpleModalBorder.YES_OPTION) {
                         int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thêm phân loại này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                         if (result == JOptionPane.YES_OPTION) {
                             try {
                                 categoryForm.addPhanLoai();
                                 loadDataToTable(); // Tải lại dữ liệu vào bảng sau khi thêm mới
                             } catch (Exception e) {
                                 JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm phân loại: " + e.getMessage());
                                 e.printStackTrace();
                             }
                         }
                     }
                     if (action == SimpleModalBorder.NO_OPTION) {
                         controller.close();
                     }
                 }), option);
    }

    public void loadDataToTable() {
        model.setRowCount(0); // Xóa dữ liệu cũ
        busphanloai buspl = new busphanloai();
        for (dtophanloai pl : buspl.dsPL) {
            model.addRow(new Object[]{false, pl.getMaPhanLoai(), pl.getTenPhanLoai()});
        }
    }

    public static void createAndShowCategoryGUI() {
        JFrame frame = new JFrame("Quản lý Phân Loại");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formphanloai panel = new formphanloai();
        frame.add(panel);
        frame.setSize(1070, 741);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("sample.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(() -> createAndShowCategoryGUI());
    }
}
