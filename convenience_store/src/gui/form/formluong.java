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
import gui.simple.SimpleInputSupplierForm;
import gui.table.CheckBoxTableHeaderRenderer;
import gui.table.TableHeaderAlignment;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
/**
 *
 * @author ASUS
 */
public class formluong extends JPanel {

    private JTabbedPane tabLuong;
    private JTable table; // Biến instance cho table
    private DefaultTableModel model; // Biến instance cho model

    public formluong() {
        init();
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
        Object columns[] = new Object[]{"CHỌN", "MÃ LƯƠNG", "MÃ CHẤM CÔNG", "PHỤ CẤP", "LƯƠNG THỰC TẾ", 
                                         "LƯƠNG THƯỞNG", "KHOẢN BẢO HIỂM", "KHOẢN THUẾ", "THỰC LÃNH", 
                                         "LƯƠNG LÀM THÊM", "NGÀY NHẬN LƯƠNG", "MÃ NHÂN VIÊN"};
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
        table.getColumnModel().getColumn(1).setMinWidth(100); // Mã lương
        table.getColumnModel().getColumn(10).setPreferredWidth(150); // Ngày nhận lương

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
                showLuongModal();
            } catch (SQLException ex) {
                Logger.getLogger(formluong.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        cmdEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một bản ghi lương để chỉnh sửa.");
            } else {
                int maLuong = (int) model.getValueAt(row, 1);
                // Lấy thêm thông tin từ các cột khác nếu cần
                showEditLuongModal(maLuong);
            }
        });

        cmdDelete.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = table.getRowCount();

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa các mục đã chọn không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                for (int i = rowCount - 1; i >= 0; i--) {
                    Boolean isChecked = (Boolean) model.getValueAt(i, 0);
                    if (isChecked != null && isChecked) {
                        int maLuong = (int) model.getValueAt(i, 1);
                        daoluong dao = new daoluong();
                        dao.delete(maLuong); // Cập nhật isDelete = 1 trong cơ sở dữ liệu
                        model.removeRow(i);
                    }
                }
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

    private void loadDataToTable() {
        // Logic tải dữ liệu từ cơ sở dữ liệu vào bảng
        // Ví dụ: lấy danh sách từ DAO
        daoluong dao = new daoluong();
        List<dtoluong> list = dao.getList();

        model.setRowCount(0);
        for (dtoluong luong : list) {
            model.addRow(new Object[]{false, luong.getMaLuong(), luong.getMaChamCong(), luong.getPhuCap(), 
                                      luong.getLuongThucTe(), luong.getLuongThuong(), luong.getKhoanBaoHiem(),
                                      luong.getKhoanThue(), luong.getThuclanh(), luong.getLuongLamThem(),
                                      luong.getNgayNhanLuong(), luong.getMaNhanVien()});
        }
    }

    private void filterTable(String text) {
        // Logic lọc dữ liệu theo text tìm kiếm
    }

    private void showLuongModal() throws SQLException {
        // Hiển thị form thêm lương
    }

    private void showEditLuongModal(int maLuong) {
        // Hiển thị form chỉnh sửa lương
    }

    private void exportTableToExcel() {
        // Logic xuất dữ liệu bảng ra file Excel
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

