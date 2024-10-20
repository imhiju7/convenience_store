/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.form;


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
import gui.simple.SimpleInputClassificationForm;
import gui.simple.SimpleInputPermissionForm;
import gui.simple.SimpleInputSupplierForm;
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

/**
 *
 * @author ASUS
 */
public class formphanloai extends JPanel{
    private JTabbedPane tabphanloai;
    
    public formphanloai(){
        init();
    }
    private void init(){

        tabphanloai = new JTabbedPane();

        // Tạo bảng chức vụ mặc định ban đầu
        JPanel panelPhanloai = createPhanLoaiTable();
        tabphanloai.addTab("Nhà Cung Ứng", panelPhanloai);

        // Thêm JTabbedPane vào GUI
        this.setLayout(new java.awt.BorderLayout());
        this.add(tabphanloai, java.awt.BorderLayout.CENTER);
    }
    private JPanel createPhanLoaiTable() {
    JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

    // Tạo mô hình bảng phân loại
    Object columns[] = new Object[]{"CHỌN", "MÃ PHÂN LOẠI", "TÊN PHÂN LOẠI"};
    DefaultTableModel model = new DefaultTableModel(columns, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Cho phép chỉnh sửa tại cột 0 cho checkbox
            return column == 0;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            // Sử dụng kiểu boolean cho cột 0 để tạo checkbox
            if (columnIndex == 0) {
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex);
        }
    };

    // Dữ liệu mẫu cho bảng phân loại
    model.addRow(new Object[]{false, "PL01", "Phân loại A", "Mô tả phân loại A"});
    model.addRow(new Object[]{false, "PL02", "Phân loại B", "Mô tả phân loại B"});
    model.addRow(new Object[]{false, "PL03", "Phân loại C", "Mô tả phân loại C"});

    // Tạo bảng
    JTable table = new JTable(model);

    // Cuộn bảng
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());

    // Các tùy chọn cho bảng
    table.getColumnModel().getColumn(0).setMaxWidth(50);  // Cột checkbox
    table.getColumnModel().getColumn(1).setMinWidth(100); // Cột Mã phân loại
    table.getColumnModel().getColumn(2).setPreferredWidth(200); // Tên phân loại
    

    // Vô hiệu hóa việc thay đổi thứ tự cột
    table.getTableHeader().setReorderingAllowed(false);

    // Thiết lập renderer cho cột checkbox
    table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

    // Thiết lập căn lề cho tiêu đề cột
    table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
        @Override
        protected int getAlignment(int column) {
            return SwingConstants.LEADING; // Căn lề cho các cột
        }
    });

    // Thiết lập kiểu cho bảng
    panel.putClientProperty(FlatClientProperties.STYLE, ""
             + "arc:20;"
             + "background:$Table.background;");
    table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
             + "height:30;"
             + "hoverBackground:null;"
             + "pressedBackground:null;"
             + "separatorColor:$TableHeader.background;");
    table.putClientProperty(FlatClientProperties.STYLE, ""
             + "rowHeight:70;"
             + "showHorizontalLines:true;"
             + "intercellSpacing:0,1;"
             + "cellFocusColor:$TableHeader.hoverBackground;"
             + "selectionBackground:$TableHeader.hoverBackground;"
             + "selectionInactiveBackground:$TableHeader.hoverBackground;"
             + "selectionForeground:$Table.foreground;");
    scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
             + "trackArc:$ScrollBar.thumbArc;"
             + "trackInsets:3,3,3,3;"
             + "thumbInsets:3,3,3,3;"
             + "background:$Table.background;");

    // Tạo tiêu đề
    JLabel title = new JLabel("Bảng Phân Loại");
    title.putClientProperty(FlatClientProperties.STYLE, ""
             + "font:bold +2");
    panel.add(title, "gapx 20");

    // Thêm header
    panel.add(createClassificationHeaderAction());

    // Thêm separator và scrollpane vào panel
    JSeparator separator = new JSeparator();
    separator.putClientProperty(FlatClientProperties.STYLE, ""
             + "foreground:$Table.gridColor;");
    panel.add(separator, "height 2");
    panel.add(scrollPane, "span, grow");

    return panel;
}

private Component createClassificationHeaderAction() {
    JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

    JTextField txtSearch = new JTextField();
    txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm...");
    txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("source/image/icon/search.svg", 0.4f));
    JButton cmdCreate = new JButton("Thêm");
    JButton cmdEdit = new JButton("Sửa");
    JButton cmdDelete = new JButton("Xóa");

    cmdCreate.addActionListener(e -> showClassificationModal());
    panel.add(txtSearch);
    panel.add(cmdCreate);
    panel.add(cmdEdit);
    panel.add(cmdDelete);

    panel.putClientProperty(FlatClientProperties.STYLE, ""
             + "background:null;");
    return panel;
}

private void showClassificationModal() {
   Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                 .setLocation(Location.TRAILING, Location.TOP)
                 .setAnimateDistance(0.7f, 0);
        ModalDialog.showModal(this, new SimpleModalBorder(
                 new SimpleInputClassificationForm(), "Thêm phân loại", SimpleModalBorder.YES_NO_OPTION,
                 (controller, action) -> {
                     controller.close();
                 }), option);
}
    public static void createAndShowClassificationGUI() {
        // Tạo JFrame
        JFrame frame = new JFrame("Quản lý Phân Loại");

        // Đảm bảo chương trình thoát khi đóng cửa sổ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một đối tượng của formPhanLoai và thêm vào JFrame
        formphanloai panel = new formphanloai(); // Đảm bảo class này đã được định nghĩa và có constructor không tham số
        frame.add(panel);

        // Thiết lập kích thước của frame
        frame.setSize(1070, 741); // Bạn có thể điều chỉnh kích thước nếu cần

        // Hiển thị frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Thiết lập FlatLaf trước khi khởi chạy GUI
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("sample.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();

        // Đảm bảo giao diện người dùng được tạo trong Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> createAndShowClassificationGUI());
    }
}
