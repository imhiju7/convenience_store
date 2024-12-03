package gui.form;

import bus.buschucnang;
import bus.buschucvu;
import bus.busdanhmuc;
import bus.busphanquyen;
import gui.comp.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import dto.dtochucvu;
import dao.daochucvu;
import dao.daodanhmuc;
import dto.dtochucnang;
import dto.dtodanhmuc;
import dto.dtophanquyen;
import gui.modal.ModalDialog;
import gui.modal.component.SimpleModalBorder;
import gui.modal.option.Location;
import gui.modal.option.Option;
import gui.simple.SimpleInputDirectoryForm;
import gui.simple.SimpleInputFunctionForm;
import gui.simple.SimpleInputPermissionForm;
import gui.table.CheckBoxTableHeaderRenderer;
import gui.table.TableHeaderAlignment;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import net.miginfocom.swing.MigLayout;
import gui.simple.SimpleInputPositionForm;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class formchucvu extends javax.swing.JPanel {

    private JTabbedPane tabchucvu;
    private DefaultTableModel chucVuModel;
    private JTable chucVuTable;
    private DefaultTableModel phanQuyenModel;
    private JTable phanQuyenTable;
    private DefaultTableModel chucNangModel;
    private JTable chucNangTable;
    private DefaultTableModel danhMucModel;
    private JTable danhMucTable;
    

    public formchucvu() throws SQLException, ParseException {
        initComponents();
        init();
    }

    private void init() throws SQLException, ParseException {

        tabchucvu = new JTabbedPane();

        // Tạo bảng chức vụ mặc định ban đầu
        JPanel panelChucVu = createChucVuTable();
        tabchucvu.addTab("Chức vụ", panelChucVu);

        // Tạo bảng phân quyền và thêm vào tab thứ hai
        JPanel panelPhanQuyen = createPhanQuyenTable();
        tabchucvu.addTab("Phân quyền", panelPhanQuyen);
        
//        JPanel panelChucNang = createChucNangTable();
//        tabchucvu.addTab("Chức năng", panelChucNang);
        
        JPanel panelDanhMuc = createDanhMucTable();
        tabchucvu.addTab("Danh mục",panelDanhMuc);

        // Thêm JTabbedPane vào GUI
        this.setLayout(new java.awt.BorderLayout());
        this.add(tabchucvu, java.awt.BorderLayout.CENTER);
    }

    private JPanel createChucVuTable() throws SQLException {
        JPanel panel = new JPanel(new MigLayout("fillx, wrap, insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // Tạo tiêu đề
        JLabel title = new JLabel("Bảng Chức Vụ");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20, wrap");

        // Tạo header với các nút chức năng
        panel.add(createPositionHeaderAction(), "growx, wrap");

        // Tạo model cho bảng Chức Vụ
        Object[] columns = new Object[]{"CHỌN", "MÃ CHỨC VỤ", "TÊN CHỨC VỤ"};
        chucVuModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Cho phép chỉnh sửa cột checkbox
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Cột checkbox
                }
                return super.getColumnClass(columnIndex);
            }
        };

        // Thêm dữ liệu mẫu vào model
        buschucvu bus = new buschucvu();
        for (dtochucvu cv : bus.list_cv) {
            chucVuModel.addRow(new Object[]{false, cv.getMachucvu(), cv.getTenchucvu()});
        }

        // Tạo bảng với model
        chucVuTable = new JTable(chucVuModel);
        JScrollPane scrollPane = new JScrollPane(chucVuTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Cài đặt tùy chọn cho bảng
        chucVuTable.getColumnModel().getColumn(0).setMaxWidth(50);  // Cột checkbox
        chucVuTable.getColumnModel().getColumn(1).setMinWidth(100); // Cột Mã chức vụ
        chucVuTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Cột Tên chức vụ

        // Không cho phép thay đổi vị trí cột
        chucVuTable.getTableHeader().setReorderingAllowed(false);

        // Đặt renderer cho tiêu đề cột checkbox
        chucVuTable.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(chucVuTable, 0));

        // Canh chỉnh tiêu đề các cột
        chucVuTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(chucVuTable) {
            @Override
            protected int getAlignment(int column) {
                return column == 1 ? SwingConstants.CENTER : SwingConstants.LEADING;
            }
        });

        // Thiết lập giao diện
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20; background:$Table.background;");
        chucVuTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30; hoverBackground:null; pressedBackground:null; separatorColor:$TableHeader.background;");
        chucVuTable.putClientProperty(FlatClientProperties.STYLE, "rowHeight:70; showHorizontalLines:true; intercellSpacing:0,1; cellFocusColor:$TableHeader.hoverBackground; selectionBackground:$TableHeader.hoverBackground; selectionForeground:$Table.foreground;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "trackArc:$ScrollBar.thumbArc; trackInsets:3,3,3,3; thumbInsets:3,3,3,3; background:$Table.background;");

        // Thêm đường phân cách
        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");

        // Thêm bảng vào panel
        panel.add(scrollPane, "span, grow");

        return panel;
    }
    private Component createPositionHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("source/image/icon/search.svg", 0.4f));
        JButton cmdCreate = new JButton("Thêm");
        JButton cmdEdit = new JButton("Sửa");
        JButton cmdDelete = new JButton("Xóa");
        JButton cmdExportExcel = new JButton("Xuất Excel");

        // Thêm sự kiện cho nút "Tìm kiếm"
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterPositionTable(txtSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterPositionTable(txtSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterPositionTable(txtSearch.getText());
            }
        });

        // Thêm sự kiện cho nút "Thêm"
        cmdCreate.addActionListener(e -> {
            try {
                showPositionModal();  // Hiển thị modal thêm chức vụ
            } catch (SQLException ex) {
                Logger.getLogger(formchucvu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Thêm sự kiện cho nút "Sửa"
        cmdEdit.addActionListener(e -> {
            int row = chucVuTable.getSelectedRow();  // Lấy dòng được chọn
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một chức vụ để chỉnh sửa.");
            } else {
                // Lấy tên chức vụ từ cột thứ 3 (index 2)
                String tenChucVu = (String) chucVuModel.getValueAt(row, 2);

                // Kiểm tra nếu là "admin"
                if ("admin".equalsIgnoreCase(tenChucVu)) {
                    JOptionPane.showMessageDialog(this, "Chức vụ 'admin' không thể chỉnh sửa.");
                } else {
                    // Lấy mã chức vụ từ cột thứ 2 (index 1)
                    int maChucVu = (int) chucVuModel.getValueAt(row, 1);

                    // Hiển thị modal chỉnh sửa
                    showEditPositionModal(maChucVu, tenChucVu, chucVuModel, row);
                }
            }
        });


        // Thêm sự kiện cho nút "Xóa"
        cmdDelete.addActionListener(e -> {
            int rowCount = chucVuTable.getRowCount();  // Lấy tổng số dòng trong bảng
            boolean hasAdmin = false;  // Biến kiểm tra nếu có chức vụ "admin" được chọn
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa các mục đã chọn không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                for (int i = rowCount - 1; i >= 0; i--) {  // Duyệt từ dưới lên để tránh lỗi khi xóa
                    Boolean isChecked = (Boolean) chucVuModel.getValueAt(i, 0);  // Kiểm tra cột "Chọn"
                    String tenChucVu = (String) chucVuModel.getValueAt(i, 2);  // Lấy tên chức vụ từ cột thứ 3 (index 2)
                    int maChucVu = (int) chucVuModel.getValueAt(i, 1);  // Lấy mã chức vụ từ cột thứ 2 (index 1)

                    if (isChecked != null && isChecked) {
                        if ("admin".equalsIgnoreCase(tenChucVu)) {  // Kiểm tra nếu là "admin"
                            hasAdmin = true;  // Đánh dấu "admin" được chọn
                        } else {
                            try {
                                daochucvu dao = new daochucvu();  // Tạo đối tượng DAO
                                dao.delete(maChucVu);  // Xóa chức vụ bằng mã
                                chucVuModel.removeRow(i);  // Xóa hàng khỏi mô hình dữ liệu
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa chức vụ: " + ex.getMessage());
                                ex.printStackTrace();
                            }
                        }
                    }
                }

                // Thông báo nếu chức vụ "admin" được chọn
                if (hasAdmin) {
                    JOptionPane.showMessageDialog(this, "Chức vụ 'admin' không thể bị xóa.");
                } else {
                    JOptionPane.showMessageDialog(this, "Đã xóa các mục được chọn.");
                }
            }
        });



        // Thêm sự kiện cho nút "Xuất Excel"
        cmdExportExcel.addActionListener(e -> {
            exportPositionTableToExcel();  // Gọi phương thức xuất Excel
        });

        // Thêm các thành phần vào panel
        panel.add(txtSearch);
        panel.add(cmdCreate);
        panel.add(cmdEdit);
        panel.add(cmdDelete);
        panel.add(cmdExportExcel);  // Thêm nút xuất Excel vào panel
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");

        return panel;
    }
    private void exportPositionTableToExcel() {
    try {
        // Chọn file để lưu
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xls", "xlsx"));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Đảm bảo file có đuôi .xlsx
            if (!fileToSave.getName().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
            }
            // Sử dụng thư viện Apache POI để xuất Excel (thêm phụ thuộc vào project)
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Chức Vụ");

            // Thêm tiêu đề vào Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mã Chức Vụ");
            headerRow.createCell(1).setCellValue("Tên Chức Vụ");

            // Thêm dữ liệu vào Excel
            for (int i = 0; i < chucVuModel.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue((Integer) chucVuModel.getValueAt(i, 1));  // Mã Chức Vụ
                row.createCell(1).setCellValue((String) chucVuModel.getValueAt(i, 2));   // Tên Chức Vụ
            }

            // Lưu file Excel
            try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(this, "Đã xuất file Excel thành công!");
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + e.getMessage());
    }
}

    private void filterPositionTable(String keyword) {
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(chucVuModel);
        chucVuTable.setRowSorter(rowSorter);

        // Sử dụng regex để tìm kiếm không phân biệt chữ hoa chữ thường
        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword)); // (?i) để tìm kiếm không phân biệt hoa thường
    }
    
    private void showEditPositionModal(int maChucVu, String tenChucVu, DefaultTableModel model, int row) {
        try {
            // Tạo một form SimpleInputPositionForm mới
            SimpleInputPositionForm positionForm = new SimpleInputPositionForm();
            int currentIsDelete = 0;
            // Thiết lập tên chức vụ ban đầu vào form
            positionForm.txtTenChucVu.setText(tenChucVu);

            // Hiển thị form dưới dạng hộp thoại
            Option option = ModalDialog.createOption();
            option.getLayoutOption().setSize(-1, 1f)
                     .setLocation(Location.TRAILING, Location.TOP)
                     .setAnimateDistance(0.7f, 0);

            ModalDialog.showModal(this, new SimpleModalBorder(
                     positionForm, "Chỉnh sửa chức vụ", SimpleModalBorder.YES_NO_OPTION,
                     (controller, action) -> {
                         if (action == SimpleModalBorder.YES_OPTION) {
                             // Lấy tên chức vụ mới từ form
                             String newTenChucVu = positionForm.txtTenChucVu.getText().trim();

                             if (!newTenChucVu.isEmpty()) {
                                 // Cập nhật vào cơ sở dữ liệu
                                 dtochucvu chucVu = new dtochucvu(maChucVu, newTenChucVu,currentIsDelete);
                                 try {
                                     buschucvu bus = new buschucvu();
                                     bus.updateChucVu(chucVu);  // Cập nhật vào cơ sở dữ liệu
                                     model.setValueAt(newTenChucVu, row, 2); // Cập nhật dữ liệu trong bảng
                                     JOptionPane.showMessageDialog(null, "Chức vụ đã được cập nhật thành công!");
                                 } catch (SQLException ex) {
                                     JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi cập nhật chức vụ: " + ex.getMessage());
                                     ex.printStackTrace();
                                 }
                             } else {
                                 JOptionPane.showMessageDialog(null, "Tên chức vụ không được để trống!");
                             }
                         }
                         if (action == SimpleModalBorder.NO_OPTION) {
                             controller.close();
                         }
                     }
            ), option);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi mở form chỉnh sửa chức vụ: " + e.getMessage());
            e.printStackTrace();
        }
    }
   
    
    private void showPositionModal() throws SQLException {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                 .setLocation(Location.TRAILING, Location.TOP)
                 .setAnimateDistance(0.7f, 0);

        // Tạo đối tượng SimpleInputPositionForm
        SimpleInputPositionForm positionForm = new SimpleInputPositionForm();

        // Hiển thị ModalDialog với SimpleInputPositionForm
        ModalDialog.showModal(this, new SimpleModalBorder(
                 positionForm, "Thêm chức vụ", SimpleModalBorder.YES_NO_OPTION,
                 (controller, action) -> {
                     // Kiểm tra nếu người dùng nhấn nút "Yes"
                     if (action == SimpleModalBorder.YES_OPTION) {
                         // Hiển thị hộp thoại xác nhận trước khi thêm chức vụ
                         int result = JOptionPane.showConfirmDialog(null,
                                  "Bạn có chắc chắn thêm chức vụ này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                         if (result == JOptionPane.NO_OPTION) {
                             return; // Nếu người dùng chọn NO, không làm gì và thoát
                         }

                         // Nếu người dùng chọn YES, thực hiện thêm chức vụ
                         try {
                             // Gọi phương thức addChucVu để thêm chức vụ vào cơ sở dữ liệu
                             positionForm.addChucVu();
                             loadDataToPositionTable();
                         } catch (Exception e) {
                             JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm chức vụ: " + e.getMessage());
                             e.printStackTrace();
                         }
                     }

                     // Nếu người dùng nhấn "No", đóng modal
                     if (action == SimpleModalBorder.NO_OPTION) {
                         controller.close();
                     }
                 }), option);
    }
    public void loadDataToPositionTable() throws SQLException {
        chucVuModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        buschucvu buscv = new buschucvu(); // Tạo đối tượng xử lý logic chức vụ
        for (dtochucvu cv : buscv.list_cv) { // Duyệt qua danh sách chức vụ
            chucVuModel.addRow(new Object[]{false, cv.getMachucvu(), cv.getTenchucvu()}); // Thêm dữ liệu vào bảng
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
    private JPanel createPhanQuyenTable() throws SQLException {
        JPanel panel = new JPanel(new MigLayout("fillx, wrap, insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // Tạo tiêu đề
        JLabel title = new JLabel("Bảng Phân Quyền");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20, wrap");

        // Tạo header với các nút chức năng
        panel.add(createPermissionHeaderAction(), "growx, wrap");

        // Tạo model cho bảng Phân Quyền
        Object[] columns = new Object[]{"CHỌN", "MÃ PHÂN QUYỀN", "TÊN CHỨC VỤ", "TÊN CHỨC NĂNG"};
        phanQuyenModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Cho phép chỉnh sửa cột checkbox
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Cột checkbox
                }
                return super.getColumnClass(columnIndex);
            }
        };

        // Thêm dữ liệu mẫu vào model
//    busphanquyen bus = new busphanquyen();
//    for (dtophanquyen pq : bus.list_pq) {
//        phanQuyenModel.addRow(new Object[]{false, pq.getMaPhanQuyen(), pq.getTenChucVu(), pq.getTenChucNang()});
//    }
        // Tạo bảng với model
        phanQuyenTable = new JTable(phanQuyenModel);
        JScrollPane scrollPane = new JScrollPane(phanQuyenTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Cài đặt tùy chọn cho bảng
        phanQuyenTable.getColumnModel().getColumn(0).setMaxWidth(50);  // Cột checkbox
        phanQuyenTable.getColumnModel().getColumn(1).setMinWidth(100); // Cột Mã phân quyền
        phanQuyenTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Cột Tên chức vụ
        phanQuyenTable.getColumnModel().getColumn(3).setPreferredWidth(150); // Cột Tên chức năng

        // Không cho phép thay đổi vị trí cột
        phanQuyenTable.getTableHeader().setReorderingAllowed(false);

        // Đặt renderer cho tiêu đề cột checkbox
        phanQuyenTable.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(phanQuyenTable, 0));

        // Canh chỉnh tiêu đề các cột
        phanQuyenTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(phanQuyenTable) {
            @Override
            protected int getAlignment(int column) {
                return column == 1 ? SwingConstants.CENTER : SwingConstants.LEADING;
            }
        });

        // Thiết lập giao diện
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20; background:$Table.background;");
        phanQuyenTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30; hoverBackground:null; pressedBackground:null; separatorColor:$TableHeader.background;");
        phanQuyenTable.putClientProperty(FlatClientProperties.STYLE, "rowHeight:70; showHorizontalLines:true; intercellSpacing:0,1; cellFocusColor:$TableHeader.hoverBackground; selectionBackground:$TableHeader.hoverBackground; selectionForeground:$Table.foreground;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "trackArc:$ScrollBar.thumbArc; trackInsets:3,3,3,3; thumbInsets:3,3,3,3; background:$Table.background;");

        // Thêm đường phân cách
        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");

        // Thêm bảng vào panel
        panel.add(scrollPane, "span, grow");

        return panel;
    }
         
    private Component createPermissionHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("source/image/icon/search.svg", 0.4f));
        JButton cmdCreate = new JButton("Thêm");
        JButton cmdEdit = new JButton("Sửa");
        JButton cmdDelete = new JButton("Xóa");

        cmdCreate.addActionListener(e -> showPermissionModal());
        panel.add(txtSearch);
        panel.add(cmdCreate);
        panel.add(cmdEdit);
        panel.add(cmdDelete);

        panel.putClientProperty(FlatClientProperties.STYLE, ""
                 + "background:null;");
        return panel;
    }
    
    private void showPermissionModal() {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                 .setLocation(Location.TRAILING, Location.TOP)
                 .setAnimateDistance(0.7f, 0);
        ModalDialog.showModal(this, new SimpleModalBorder(
                 new SimpleInputPermissionForm(), "Thêm phân quyền", SimpleModalBorder.YES_NO_OPTION,
                 (controller, action) -> {
                     controller.close();
                 }), option);// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private JPanel createDanhMucTable() throws SQLException {
        JPanel panel = new JPanel(new MigLayout("fillx, wrap, insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // Tạo tiêu đề
        JLabel title = new JLabel("Bảng Danh Mục");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20, wrap");

        // Tạo header với các nút chức năng
        panel.add(createDirectoryHeaderAction(), "growx, wrap");

        // Tạo model cho bảng Danh Mục
        Object[] columns = new Object[]{"CHỌN", "MÃ DANH MỤC", "TÊN DANH MỤC"};
        danhMucModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Cho phép chỉnh sửa cột checkbox
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Cột checkbox
                }
                return super.getColumnClass(columnIndex);
            }
        };

        // Thêm dữ liệu mẫu vào model
        busdanhmuc bus = new busdanhmuc(); // Assuming busdanhmuc is the business logic class for Danh Mục
        for (dtodanhmuc dm : bus.dsDM) { // Assuming dsDM contains the list of Danh Mục objects
            danhMucModel.addRow(new Object[]{false, dm.getMaDanhMuc(), dm.getTenDanhMuc(), dm.getIcon()});
        }

        // Tạo bảng với model
        danhMucTable = new JTable(danhMucModel);
        JScrollPane scrollPane = new JScrollPane(danhMucTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Cài đặt tùy chọn cho bảng
        danhMucTable.getColumnModel().getColumn(0).setMaxWidth(50);  // Cột checkbox
        danhMucTable.getColumnModel().getColumn(1).setMinWidth(100); // Cột Mã Danh Mục
        danhMucTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Cột Tên Danh Mục

        // Không cho phép thay đổi vị trí cột
        danhMucTable.getTableHeader().setReorderingAllowed(false);

        // Đặt renderer cho tiêu đề cột checkbox
        danhMucTable.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(danhMucTable, 0));

        // Canh chỉnh tiêu đề các cột
        danhMucTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(danhMucTable) {
            @Override
            protected int getAlignment(int column) {
                return column == 1 ? SwingConstants.CENTER : SwingConstants.LEADING;
            }
        });

        // Thiết lập giao diện
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20; background:$Table.background;");
        danhMucTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30; hoverBackground:null; pressedBackground:null; separatorColor:$TableHeader.background;");
        danhMucTable.putClientProperty(FlatClientProperties.STYLE, "rowHeight:70; showHorizontalLines:true; intercellSpacing:0,1; cellFocusColor:$TableHeader.hoverBackground; selectionBackground:$TableHeader.hoverBackground; selectionForeground:$Table.foreground;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "trackArc:$ScrollBar.thumbArc; trackInsets:3,3,3,3; thumbInsets:3,3,3,3; background:$Table.background;");

        // Thêm đường phân cách
        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");

        // Thêm bảng vào panel
        panel.add(scrollPane, "span, grow");

        return panel;
    }

    private Component createDirectoryHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        // Tạo ô tìm kiếm
        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("source/image/icon/search.svg", 0.4f));

        // Tạo các nút hành động
        JButton cmdCreate = new JButton("Thêm");
        JButton cmdEdit = new JButton("Sửa");
        JButton cmdDelete = new JButton("Xóa");
        JButton cmdExportExcel = new JButton("Xuất Excel"); // Thêm nút xuất Excel

        // Xử lý Tìm kiếm
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterDirectoryTable(txtSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterDirectoryTable(txtSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterDirectoryTable(txtSearch.getText());
            }
        });

        // Xử lý Thêm
        cmdCreate.addActionListener(e -> {
            try {
                showDirectoryModal(); // Hiển thị form thêm mới
            } catch (SQLException ex) {
                Logger.getLogger(formchucvu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Xử lý Sửa
        cmdEdit.addActionListener(e -> {
            int row = danhMucTable.getSelectedRow(); // Lấy dòng được chọn
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một danh mục để chỉnh sửa.");
            } else {
                // Lấy dữ liệu từ hàng được chọn
                int maDanhMuc = (int) danhMucModel.getValueAt(row, 1);
                String tenDanhMuc = (String) danhMucModel.getValueAt(row, 2);
                String iconPath = ""; // Lấy đường dẫn biểu tượng (nếu có)

                // Hiển thị modal chỉnh sửa
                showEditDirectoryModal(maDanhMuc, tenDanhMuc, iconPath, danhMucModel, row);
            }
        });

        // Xử lý Xóa
        cmdDelete.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) danhMucTable.getModel();
            int rowCount = danhMucTable.getRowCount();
            daodanhmuc daoDanhMuc = new daodanhmuc(); // Tạo instance của DAO

            // Xác nhận từ người dùng
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa các mục đã chọn không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                for (int i = rowCount - 1; i >= 0; i--) { // Duyệt qua bảng từ dưới lên
                    Boolean isChecked = (Boolean) model.getValueAt(i, 0); // Lấy giá trị cột checkbox
                    if (isChecked != null && isChecked) {
                        int maDanhMuc = (int) model.getValueAt(i, 1); // Giả định cột 1 là "maDanhMuc"

                        // Gọi phương thức delete để đánh dấu xóa trong cơ sở dữ liệu
                        daoDanhMuc.delete(maDanhMuc);

                        // Xóa dòng khỏi bảng hiển thị
                        model.removeRow(i);
                    }
                }
                JOptionPane.showMessageDialog(this, "Đã xóa các mục được chọn.");
            }
        });


        // Xử lý Xuất Excel
        cmdExportExcel.addActionListener(e -> {
            exportDirectoryTableToExcel(); // Gọi hàm xuất Excel
        });

        // Thêm các thành phần vào panel
        panel.add(txtSearch);
        panel.add(cmdCreate);
        panel.add(cmdEdit);
        panel.add(cmdDelete);
        panel.add(cmdExportExcel); // Thêm nút xuất Excel
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");

        return panel;
    }

    private void filterDirectoryTable(String query) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(danhMucModel);
        danhMucTable.setRowSorter(sorter);

        if (query.trim().isEmpty()) {
            sorter.setRowFilter(null); // Hiển thị tất cả nếu không có tìm kiếm
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query)); // Lọc theo từ khóa
        }
    }

    private void exportDirectoryTableToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu danh mục dưới dạng Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx"; // Thêm đuôi .xlsx nếu chưa có
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Danh Mục");

                // Ghi header
                Row headerRow = sheet.createRow(0);
                for (int i = 1; i < danhMucTable.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(danhMucTable.getColumnName(i));
                }

                // Ghi dữ liệu
                for (int i = 0; i < danhMucTable.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 1; j < danhMucTable.getColumnCount(); j++) {
                        Object value = danhMucTable.getValueAt(i, j);
                        row.createCell(j).setCellValue(value != null ? value.toString() : "");
                    }
                }

                // Lưu file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }

                JOptionPane.showMessageDialog(this, "Xuất Excel thành công!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xuất file: " + ex.getMessage());
            }
        }
    }

    private void showDirectoryModal() throws SQLException {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f).setLocation(Location.TRAILING, Location.TOP);
        SimpleInputDirectoryForm directoryForm = new SimpleInputDirectoryForm();
        ModalDialog.showModal(this, new SimpleModalBorder(
                 directoryForm, "Thêm Danh Mục", SimpleModalBorder.YES_NO_OPTION,
                 (controller, action) -> {
                     if (action == SimpleModalBorder.YES_OPTION) {
                         try {
                             // Xử lý thêm danh mục mới
                             directoryForm.addDanhmuc();
                             loadDataToDirectoryTable(); // Tải lại dữ liệu vào bảng sau khi thêm mới
                         } catch (Exception e) {
                             JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm danh mục: " + e.getMessage());
                             e.printStackTrace();
                         }
                     }
                     if (action == SimpleModalBorder.NO_OPTION) {
                         controller.close();
                     }
                 }), option);
    }

    private void showEditDirectoryModal(int maDanhMuc, String tenDanhMuc, String oldIconPath, DefaultTableModel model, int row) {
        try {
            // Tạo một form SimpleInputDirectoryForm mới
            SimpleInputDirectoryForm directoryForm = new SimpleInputDirectoryForm();
            int currentIsDelete = 0;
            // Thiết lập dữ liệu ban đầu vào form
            directoryForm.txtTenDanhMuc.setText(tenDanhMuc);
            if (oldIconPath != null && !oldIconPath.isEmpty()) {
                File oldIconFile = new File(oldIconPath);
                if (oldIconFile.exists()) {
                    ImageIcon icon = new ImageIcon(new ImageIcon(oldIconPath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    directoryForm.lblIcon.setIcon(icon);
                    directoryForm.selectedIconFile = oldIconFile;
                }
            }

            // Hiển thị form dưới dạng hộp thoại
            Option option = ModalDialog.createOption();
            option.getLayoutOption().setSize(-1, 1f)
                     .setLocation(Location.TRAILING, Location.TOP)
                     .setAnimateDistance(0.7f, 0);

            ModalDialog.showModal(this, new SimpleModalBorder(
                     directoryForm, "Chỉnh sửa danh mục", SimpleModalBorder.YES_NO_OPTION,
                     (controller, action) -> {
                         if (action == SimpleModalBorder.YES_OPTION) {
                             // Lấy dữ liệu mới từ form
                             String newTenDanhMuc = directoryForm.txtTenDanhMuc.getText().trim();
                             File newIconFile = directoryForm.selectedIconFile;

                             // Kiểm tra tính hợp lệ của dữ liệu
                             if (newTenDanhMuc.isEmpty()) {
                                 JOptionPane.showMessageDialog(null, "Vui lòng điền tên danh mục!");
                                 return;
                             }

                             String newIconPath = null;
                             if (newIconFile != null) {
                                 try {
                                     // Copy biểu tượng mới vào thư mục "icons"
                                     File destFile = new File("icons/" + newIconFile.getName());
                                     Files.copy(newIconFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                     newIconPath = destFile.getAbsolutePath();
                                 } catch (Exception ex) {
                                     JOptionPane.showMessageDialog(null, "Lỗi khi lưu biểu tượng: " + ex.getMessage());
                                     ex.printStackTrace();
                                     return;
                                 }
                             }

                             // Cập nhật đối tượng và cơ sở dữ liệu
                             dtodanhmuc danhMuc = new dtodanhmuc(maDanhMuc, newTenDanhMuc, newIconPath, currentIsDelete );
                             busdanhmuc bus = new busdanhmuc();
                             try {
                                 bus.updateDanhMuc(danhMuc); // Cập nhật vào cơ sở dữ liệu
                                 // Cập nhật dữ liệu trong bảng
                                 model.setValueAt(newTenDanhMuc, row, 2);
                                
                                 JOptionPane.showMessageDialog(null, "Danh mục đã được cập nhật thành công!");
                             } catch (SQLException ex) {
                                 JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi cập nhật danh mục: " + ex.getMessage());
                                 ex.printStackTrace();
                             }
                         }
                         if (action == SimpleModalBorder.NO_OPTION) {
                             controller.close();
                         }
                     }
            ), option);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi mở form chỉnh sửa danh mục: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadDataToDirectoryTable() {
        danhMucModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        busdanhmuc busDM = new busdanhmuc();
        for (dtodanhmuc dm : busDM.dsDM) {
            danhMucModel.addRow(new Object[]{false, dm.getMaDanhMuc(), dm.getTenDanhMuc()});
        }
    }

   



  ///////////////////////////////////////////////// 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelchucvu = new javax.swing.JPanel();
        scrollchucvu = new javax.swing.JScrollPane();
        tablechucvu = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        lbchucvu = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        buttonAction1 = new gui.comp.ButtonAction();
        buttonAction3 = new gui.comp.ButtonAction();
        buttonAction4 = new gui.comp.ButtonAction();

        setPreferredSize(new java.awt.Dimension(1070, 741));

        scrollchucvu.setBorder(null);

        tablechucvu.setModel(new javax.swing.table.DefaultTableModel(
                 new Object[][]{},
                 new String[]{
                     "Chọn", "Mã chức vụ", "Tên chúc vụ", "Tên chức năng"
                 }
        ) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        scrollchucvu.setViewportView(tablechucvu);

        jSeparator1.setForeground(new java.awt.Color(242, 242, 242));
        jSeparator1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lbchucvu.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lbchucvu.setText("Phân quyền");

        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });

        buttonAction1.setText("Xóa");
        buttonAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction1ActionPerformed(evt);
            }
        });

        buttonAction3.setText("Sửa");
        buttonAction3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction3ActionPerformed(evt);
            }
        });

        buttonAction4.setText("Thêm");
        buttonAction4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelchucvuLayout = new javax.swing.GroupLayout(panelchucvu);
        panelchucvu.setLayout(panelchucvuLayout);
        panelchucvuLayout.setHorizontalGroup(
                 panelchucvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(panelchucvuLayout.createSequentialGroup()
                                   .addContainerGap()
                                   .addGroup(panelchucvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator1)
                                            .addGroup(panelchucvuLayout.createSequentialGroup()
                                                     .addGroup(panelchucvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                              .addComponent(lbchucvu)
                                                              .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                     .addComponent(buttonAction4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                     .addComponent(buttonAction3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                     .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                   .addContainerGap())
                          .addComponent(scrollchucvu, javax.swing.GroupLayout.DEFAULT_SIZE, 1045, Short.MAX_VALUE)
        );
        panelchucvuLayout.setVerticalGroup(
                 panelchucvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelchucvuLayout.createSequentialGroup()
                                   .addComponent(lbchucvu)
                                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addGroup(panelchucvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelchucvuLayout.createSequentialGroup()
                                                     .addGap(3, 3, 3)
                                                     .addGroup(panelchucvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                              .addComponent(buttonAction1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                              .addComponent(buttonAction3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                              .addComponent(buttonAction4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                   .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                   .addComponent(scrollchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(layout.createSequentialGroup()
                                   .addGap(17, 17, 17)
                                   .addComponent(panelchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(layout.createSequentialGroup()
                                   .addGap(64, 64, 64)
                                   .addComponent(panelchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addContainerGap(55, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void buttonAction1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void buttonAction3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void buttonAction4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void createAndShowGUI() throws SQLException, ParseException {
        // Tạo JFrame
        JFrame frame = new JFrame("Chức vụ Management");

        // Đảm bảo chương trình thoát khi đóng cửa sổ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một đối tượng của formchucvu và thêm vào JFrame
        formchucvu panel = new formchucvu();
        frame.add(panel);

        // Thiết lập kích thước của frame
        frame.setSize(1070, 741);  // Bạn có thể điều chỉnh kích thước nếu cần

        // Hiển thị frame
        frame.setVisible(true);
//         JPanel newPanel = panel.createChucVuPanel();
//        panel.createTab("Chức vụ Phòng Ban", newPanel);
    }

    public static void main(String[] args) {
        // Thiết lập FlatLaf trước khi khởi chạy GUI
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("sample.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();

        // Đảm bảo giao diện người dùng được tạo trong Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (SQLException ex) {
                    Logger.getLogger(formchucvu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(formchucvu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private gui.comp.ButtonAction buttonAction1;
    private gui.comp.ButtonAction buttonAction3;
    private gui.comp.ButtonAction buttonAction4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbchucvu;
    private javax.swing.JPanel panelchucvu;
    private javax.swing.JScrollPane scrollchucvu;
    private javax.swing.JTable tablechucvu;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration                   
}
