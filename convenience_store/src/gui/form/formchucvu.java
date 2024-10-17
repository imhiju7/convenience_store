/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;
import gui.comp.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
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
public class formchucvu extends javax.swing.JPanel {

   
    private JTabbedPane tabchucvu;
    public formchucvu() {
        initComponents();
        init();
    }
     public void createTab(String title, JPanel panel) {
        tabchucvu.addTab("Chức vụ", createChucVuTable());
    }
    private void init(){

        tabchucvu = new JTabbedPane();

        // Tạo bảng chức vụ mặc định ban đầu
        JPanel panelChucVu = createChucVuTable();
        tabchucvu.addTab("Chức vụ", panelChucVu);

        // Tạo bảng phân quyền và thêm vào tab thứ hai
        JPanel panelPhanQuyen = createPhanQuyenTable();
        tabchucvu.addTab("Phân quyền", panelPhanQuyen);

        // Thêm JTabbedPane vào GUI
        this.setLayout(new java.awt.BorderLayout());
        this.add(tabchucvu, java.awt.BorderLayout.CENTER);
    }
   
    
//    private JPanel createChucVuTable() {
//    JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));
//
//    // create table model
//    Object columns[] = new Object[]{"CHỌN", "MÃ CHỨC VỤ", "TÊN CHỨC VỤ", "TÊN CHỨC NĂNG"};
//    DefaultTableModel model = new DefaultTableModel(columns, 0) {
//        @Override
//        public boolean isCellEditable(int row, int column) {
//            // allow cell editable at column 0 for checkbox
//            return column == 0;
//        }
//
//        @Override
//        public Class<?> getColumnClass(int columnIndex) {
//            // use boolean type at column 0 for checkbox
//            if (columnIndex == 0) {
//                return Boolean.class;
//            }
//            return super.getColumnClass(columnIndex);
//        }
//    };
//
//    // Sample data for the Chức vụ table
//    model.addRow(new Object[]{false, "CV01", "Quản trị viên", "Quản lý hệ thống"});
//    model.addRow(new Object[]{false, "CV02", "Nhân viên", "Thực hiện công việc hàng ngày"});
//    model.addRow(new Object[]{false, "CV03", "Giám đốc", "Quản lý nhân sự và chiến lược"});
//    model.addRow(new Object[]{false, "CV04", "Kế toán", "Quản lý tài chính và chi tiêu"});
//    model.addRow(new Object[]{false, "CV05", "Thủ quỹ", "Quản lý tiền mặt và giao dịch"});
//
//    // create table
//    JTable table = new JTable(model);
//
//    // table scroll
//    JScrollPane scrollPane = new JScrollPane(table);
//    scrollPane.setBorder(BorderFactory.createEmptyBorder());
//
//    // table option
//    table.getColumnModel().getColumn(0).setMaxWidth(50);  // Cột checkbox
//    table.getColumnModel().getColumn(1).setMaxWidth(100); // Cột Mã chức vụ
//    table.getColumnModel().getColumn(2).setPreferredWidth(150); // Tên chức vụ
//    table.getColumnModel().getColumn(3).setPreferredWidth(250); // Tên chức năng
//
//    // disable reordering table column
//    table.getTableHeader().setReorderingAllowed(false);
//
//    // apply checkbox custom to table header
//    table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));
//
//    // alignment table header
//    table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
//        @Override
//        protected int getAlignment(int column) {
//            if (column == 1) {
//                return SwingConstants.CENTER;
//            }
//            return SwingConstants.LEADING;
//        }
//    });
//
//    // style
//    panel.putClientProperty(FlatClientProperties.STYLE, "" +
//            "arc:20;" +
//            "background:$Table.background;");
//    table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" +
//            "height:30;" +
//            "hoverBackground:null;" +
//            "pressedBackground:null;" +
//            "separatorColor:$TableHeader.background;");
//    table.putClientProperty(FlatClientProperties.STYLE, "" +
//            "rowHeight:70;" +
//            "showHorizontalLines:true;" +
//            "intercellSpacing:0,1;" +
//            "cellFocusColor:$TableHeader.hoverBackground;" +
//            "selectionBackground:$TableHeader.hoverBackground;" +
//            "selectionInactiveBackground:$TableHeader.hoverBackground;" +
//            "selectionForeground:$Table.foreground;");
//    scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
//            "trackArc:$ScrollBar.thumbArc;" +
//            "trackInsets:3,3,3,3;" +
//            "thumbInsets:3,3,3,3;" +
//            "background:$Table.background;");
//
//    // create title
//    JLabel title = new JLabel("Bảng Chức Vụ");
//    title.putClientProperty(FlatClientProperties.STYLE, "" +
//            "font:bold +2");
//    panel.add(title, "gapx 20");
//
//    // create header
//    panel.add(createHeaderAction());
//
//    JSeparator separator = new JSeparator();
//    separator.putClientProperty(FlatClientProperties.STYLE, "" +
//            "foreground:$Table.gridColor;");
//    panel.add(separator, "height 2");
//    panel.add(scrollPane, "span, grow");
//    panel.add(new JScrollPane(table), java.awt.BorderLayout.CENTER);
//    return panel;
//}
private JPanel createChucVuTable() {
        JPanel panel = new JPanel(new MigLayout("fillx, wrap, insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // create title
        JLabel title = new JLabel("Bảng Chức Vụ");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "gapx 20, wrap");

        // create header action with search and buttons
        panel.add(createHeaderAction(), "growx, wrap");

        // create table model for Chức Vụ
        Object columns[] = new Object[]{"CHỌN", "MÃ CHỨC VỤ", "TÊN CHỨC VỤ", "TÊN CHỨC NĂNG"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // allow editing in the checkbox column
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // checkbox column
                }
                return super.getColumnClass(columnIndex);
            }
        };

        // Sample data for the Chức Vụ table
        model.addRow(new Object[]{false, "CV01", "Quản trị viên", "Quản lý hệ thống"});
        model.addRow(new Object[]{false, "CV02", "Nhân viên", "Thực hiện công việc hàng ngày"});
        model.addRow(new Object[]{false, "CV03", "Giám đốc", "Quản lý nhân sự và chiến lược"});
        model.addRow(new Object[]{false, "CV04", "Kế toán", "Quản lý tài chính và chi tiêu"});
        model.addRow(new Object[]{false, "CV05", "Thủ quỹ", "Quản lý tiền mặt và giao dịch"});

        // create table
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // table options
        table.getColumnModel().getColumn(0).setMaxWidth(50);  // Cột checkbox
        table.getColumnModel().getColumn(1).setMaxWidth(100); // Cột Mã chức vụ
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Tên chức vụ
        table.getColumnModel().getColumn(3).setPreferredWidth(250); // Tên chức năng

        // disable reordering table column
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

        // alignment table header
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
            @Override
            protected int getAlignment(int column) {
                return column == 1 ? SwingConstants.CENTER : SwingConstants.LEADING;
            }
        });

        // style
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20; background:$Table.background;");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "height:30; hoverBackground:null; pressedBackground:null; separatorColor:$TableHeader.background;");
        table.putClientProperty(FlatClientProperties.STYLE, "rowHeight:70; showHorizontalLines:true; intercellSpacing:0,1; cellFocusColor:$TableHeader.hoverBackground; selectionBackground:$TableHeader.hoverBackground; selectionForeground:$Table.foreground;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "trackArc:$ScrollBar.thumbArc; trackInsets:3,3,3,3; thumbInsets:3,3,3,3; background:$Table.background;");

        // separator
        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");

        panel.add(scrollPane, "span, grow");

        return panel;
    }

    private JPanel createPhanQuyenTable() {
    JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

    // create table model for Phân Quyền
    Object columns[] = new Object[]{"CHỌN", "MÃ PHÂN QUYỀN", "TÊN PHÂN QUYỀN", "MÔ TẢ"};
    DefaultTableModel model = new DefaultTableModel(columns, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            // allow cell editable at column 0 for checkbox
            return column == 0;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            // use boolean type at column 0 for checkbox
            if (columnIndex == 0) {
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex);
        }
    };

    // Sample data for the Phân Quyền table
    model.addRow(new Object[]{false, "PQ01", "Quản lý người dùng", "Quyền quản lý toàn bộ người dùng trong hệ thống"});
    model.addRow(new Object[]{false, "PQ02", "Quản lý sản phẩm", "Quyền quản lý toàn bộ sản phẩm"});
    model.addRow(new Object[]{false, "PQ03", "Xem báo cáo", "Quyền truy cập và xem báo cáo tài chính"});
    model.addRow(new Object[]{false, "PQ04", "Cấp quyền", "Quyền cấp quyền cho người dùng khác"});
    model.addRow(new Object[]{false, "PQ05", "Xóa dữ liệu", "Quyền xóa dữ liệu hệ thống"});

    // create table
    JTable table = new JTable(model);

    // table scroll
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());

    // table options
    table.getColumnModel().getColumn(0).setMaxWidth(50);  // Cột checkbox
    table.getColumnModel().getColumn(1).setMaxWidth(150); // Cột Mã phân quyền
    table.getColumnModel().getColumn(2).setPreferredWidth(200); // Tên phân quyền
    table.getColumnModel().getColumn(3).setPreferredWidth(300); // Mô tả

    // disable reordering table column
    table.getTableHeader().setReorderingAllowed(false);

    // apply checkbox custom to table header
    table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));

    // alignment table header
    table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
        @Override
        protected int getAlignment(int column) {
            if (column == 1) {
                return SwingConstants.CENTER;
            }
            return SwingConstants.LEADING;
        }
    });

    // style
    panel.putClientProperty(FlatClientProperties.STYLE, "" +
            "arc:20;" +
            "background:$Table.background;");
    table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" +
            "height:30;" +
            "hoverBackground:null;" +
            "pressedBackground:null;" +
            "separatorColor:$TableHeader.background;");
    table.putClientProperty(FlatClientProperties.STYLE, "" +
            "rowHeight:70;" +
            "showHorizontalLines:true;" +
            "intercellSpacing:0,1;" +
            "cellFocusColor:$TableHeader.hoverBackground;" +
            "selectionBackground:$TableHeader.hoverBackground;" +
            "selectionInactiveBackground:$TableHeader.hoverBackground;" +
            "selectionForeground:$Table.foreground;");
    scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
            "trackArc:$ScrollBar.thumbArc;" +
            "trackInsets:3,3,3,3;" +
            "thumbInsets:3,3,3,3;" +
            "background:$Table.background;");

    // create title
    JLabel title = new JLabel("Bảng Phân Quyền");
    title.putClientProperty(FlatClientProperties.STYLE, "" +
            "font:bold +2");
    panel.add(title, "gapx 20");
  // create header
        panel.add(createHeaderAction());
    // add separator and scrollpane to panel
    JSeparator separator = new JSeparator();
    separator.putClientProperty(FlatClientProperties.STYLE, "" +
            "foreground:$Table.gridColor;");
    panel.add(separator, "height 2");
    panel.add(scrollPane, "span, grow");

    return panel;
}
 private Component createHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("raven/modal/demo/icons/search.svg", 0.4f));
        JButton cmdCreate = new JButton("Create");
        JButton cmdEdit = new JButton("Edit");
        JButton cmdDelete = new JButton("Delete");

        cmdCreate.addActionListener(e -> showModal());
        panel.add(txtSearch);
        panel.add(cmdCreate);
        panel.add(cmdEdit);
        panel.add(cmdDelete);

        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;");
        return panel;
    }
    

      
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
            new Object [][] {

            },
            new String [] {
                "Chọn", "Mã chức vụ", "Tên chúc vụ", "Tên chức năng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
    public static void createAndShowGUI() {
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
                createAndShowGUI();
            }
        });
    }
//     public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                // Tạo GUI và hiển thị
//                javax.swing.JFrame frame = new javax.swing.JFrame("Quản lý chức vụ");
//                formchucvu gui = new formchucvu();
//                frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//                frame.add(gui);
//                frame.setSize(1070, 741);
//                frame.setVisible(true);
//                
//                // Tạo thêm một tab mới gọi là "Chức vụ Phòng Ban"
//                JPanel newPanel = gui.createChucVuPanel(); // Sử dụng lại hàm tạo panel chức vụ
//                gui.createTab("Chức vụ Phòng Ban", newPanel); // Thêm tab mới vào
//            }
//        });
//    }
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

    private void showModal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
