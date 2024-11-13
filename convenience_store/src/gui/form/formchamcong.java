package gui.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

import com.formdev.flatlaf.FlatClientProperties;
import java.util.List;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import bus.buschamcong;
import bus.buschitietchamcong;
import bus.busnhanvien;
import dto.dtochamcong;
import dto.dtochitietchamcong;
import dto.dtonhanvien;
import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.MigLayout;

import gui.table.TableHeaderAlignment;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author PHUONG ANH
 */
public class formchamcong extends javax.swing.JPanel {
    private JTable generalTable;
    private buschamcong buscc = new buschamcong();
    private buschitietchamcong busctcc = new buschitietchamcong();
    private final busnhanvien busnv = new busnhanvien();

    public formchamcong() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 7 15 7 15", "[fill]", "[][fill,grow]"));
        add(createInfo("Bảng chấm công", "Chi tiết chấm công của toàn bộ nhân viên cửa hàng.", 1));
        add(createTab(), "gapx 7 7");

    }

    private JPanel createInfo(String title, String description, int level) {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap", "[fill]"));
        JLabel lbTitle = new JLabel(title);
        JTextArea text = new JTextArea();
        text.setText(description);
        text.setEditable(false);
        text.setBorder(BorderFactory.createEmptyBorder());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +" + (4 - level));
        panel.add(lbTitle);
        panel.add(text);
        return panel;
    }

    private Component createTab() {
        JTabbedPane tabb = new JTabbedPane();
        tabb.putClientProperty(FlatClientProperties.STYLE, ""
                + "tabType:card");
        tabb.addTab("Theo tháng", createBorder(createGeneralTable()));
        tabb.addTab("Theo ngày", createBorder(createDetailTable()));
        return tabb;
    }

    private Component createBorder(Component component) {
        JPanel panel = new JPanel(new MigLayout("fill,insets 7 0 7 0", "[fill]", "[fill]"));
        panel.add(component);
        return panel;
    }

    private Component createDetailTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // create table model
        Object columns[] = new Object[]{"Mã nhân viên", "Tên", "Ngày", "Loại hình làm việc", "Check-in", "Check-out", "Số giờ làm"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // create table
        JTable table = new JTable(model);

        // table scroll
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // alignment table header
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
            protected int getAlignment() {
                return SwingConstants.CENTER;
            }
        });

        // style
        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:$Table.background;");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;");
        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
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

        // create title
        JLabel title = new JLabel("Bảng chấm công theo ngày");
        title.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +2");
        panel.add(title, "gapx 20");

        // create header
        panel.add(createHeaderDetailTable());

        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, ""
                + "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");
        panel.add(scrollPane);

        busctcc.getlist();
        for (dtochitietchamcong cc: busctcc.dsctcc){
            model.addRow(cc.toTableRowDetail());
        }
        return panel;
    }

    private Component createGeneralTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // create table model
        Object columns[] = new Object[]{"Mã nhân viên", "Tên nhân viên", "Tháng", "Năm", "Số ngày nghỉ", "Số ngày trễ", "Giờ làm thêm", "Tổng giờ làm", "Tổng ngày làm", "Chi tiết"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // create table and assign to generalTable
        generalTable = new JTable(model);

        // table scroll
        JScrollPane scrollPane = new JScrollPane(generalTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // alignment table header
        generalTable.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(generalTable) {
            protected int getAlignment() {
                return SwingConstants.CENTER;
            }
        });

        // style
        generalTable.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20;" + "background:$Table.background;");
        generalTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" + "height:30;" + "hoverBackground:null;" + "pressedBackground:null;" + "separatorColor:$TableHeader.background;");
        generalTable.putClientProperty(FlatClientProperties.STYLE, "" + "rowHeight:30;" + "showHorizontalLines:true;" + "intercellSpacing:0,1;" + "cellFocusColor:$TableHeader.hoverBackground;" + "selectionBackground:$TableHeader.hoverBackground;" + "selectionInactiveBackground:$TableHeader.hoverBackground;" + "selectionForeground:$Table.foreground;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" + "trackArc:$ScrollBar.thumbArc;" + "trackInsets:3,3,3,3;" + "thumbInsets:3,3,3,3;" + "background:$Table.background;");

        // create title
        JLabel title = new JLabel("Bảng chấm công theo tháng");
        title.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +2");
        panel.add(title, "gapx 20");

        // create header
        panel.add(createHeaderGeneralTable());

        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "" + "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");

        panel.add(scrollPane);

        buscc.getlist();
        for (dtochamcong cc : buscc.dscc) {
            model.addRow(cc.toTableRowGeneral());
        }
        return panel;
    }

    private Component createHeaderGeneralTable() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,fill]push[][]")); 

        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(250, 28));
        txtSearch.setMaximumSize(new Dimension(260, 28));
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("../../source/image/icon/search.png", 0.4f));
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm tên NV, mã NV");

        JComboBox cbmonth = new JComboBox<>();
        cbmonth.addItem("Tháng");
        for (int i = 1; i <= 12; i++) {
            cbmonth.addItem("Tháng " + i);
        }

        JComboBox cbyear = new JComboBox<>();
        cbyear.addItem("Năm");
        ArrayList<Integer> years = buscc.listYears();
        for (Integer year : years) {
            cbyear.addItem("Năm " + year);
        }

        JButton cmdCreate = new JButton("Create");
        JButton btnSearch = new JButton("Search");

        cmdCreate.addActionListener(e -> {
            try {
                create();
            } catch (SQLException ex) {
                Logger.getLogger(formchamcong.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnSearch.addActionListener(e -> searching(txtSearch, cbmonth, cbyear));

        // Adding components with updated layout constraints
        panel.add(txtSearch, "split 4");  // Updated to allow text field to grow horizontally
        panel.add(cbmonth);
        panel.add(cbyear);
        panel.add(btnSearch);                    
        panel.add(cmdCreate, "wrap");            

        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        return panel;
    }

    private Component createHeaderDetailTable() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,fill]push[][]")); 

        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(250, 28));
        txtSearch.setMaximumSize(new Dimension(260, 28));
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("../../source/image/icon/search.png", 0.4f));
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm tên NV, mã NV");

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(e -> searching(txtSearch));
        // Adding components with updated layout constraints
        panel.add(txtSearch, "split 2");
        panel.add(btnSearch);                    

        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        return panel;
    }
    
    private void searching(JTextField txtSearch, JComboBox cbmonth, JComboBox cbyear) {
        String searchText = txtSearch.getText().trim();
        String selectedMonth = (String) cbmonth.getSelectedItem();
        String selectedYear = (String) cbyear.getSelectedItem();
        if (searchText.isEmpty() && selectedMonth.equals("Tháng") && selectedYear.equals("Năm")) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập/chọn thông tin tìm kiếm",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        TableRowSorter sorter = new TableRowSorter<>(generalTable.getModel());
        generalTable.setRowSorter(sorter);
        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        if(!searchText.isEmpty()){
            if(searchText.chars().allMatch(Character::isDigit))
                filters.add(RowFilter.regexFilter(searchText, 0));
            else filters.add(RowFilter.regexFilter("(?i)" + searchText, 1));
        } 
        if(!selectedMonth.equals("Tháng")){
            filters.add(RowFilter.regexFilter(selectedMonth.substring(selectedMonth.length() - 1), 2));
        }
        if(!selectedYear.equals("Năm")){
            filters.add(RowFilter.regexFilter(selectedYear.substring(selectedYear.length() - 1), 3));
        }
        if (!filters.isEmpty()) {
            sorter.setRowFilter(RowFilter.andFilter(filters));
        }
        if(generalTable.getRowCount() == 0)
            JOptionPane.showMessageDialog(null, "Không có dữ liệu ");
    }
    
    private void searching(JTextField txtSearch) {
        String searchText = txtSearch.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập/chọn thông tin tìm kiếm",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        TableRowSorter sorter = new TableRowSorter<>(generalTable.getModel());
        generalTable.setRowSorter(sorter);
        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        if(searchText.chars().allMatch(Character::isDigit))
            filters.add(RowFilter.regexFilter(searchText, 0));
        else filters.add(RowFilter.regexFilter("(?i)" + searchText, 1));

        if (!filters.isEmpty()) {
            sorter.setRowFilter(RowFilter.andFilter(filters));
        }
        List<SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING)); // Year column, assuming column index 3
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING)); // Month column, assuming column index 2
        sorter.setSortKeys(sortKeys);
        
        if(generalTable.getRowCount() == 0)
            JOptionPane.showMessageDialog(null, "Không có dữ liệu ");
    }


    private void create() throws SQLException {
        int currYear = Year.now().getValue();
        Calendar calendar = Calendar.getInstance();
        int currMonth = calendar.get(Calendar.MONTH) + 1;

        if (buscc.isexist(currMonth, currYear)) {
            JOptionPane.showMessageDialog(null, "Bảng chấm công tháng này đã tồn tại");
            return;
        }
        int count = buscc.countchamcong();
        System.out.print("dem ma cham cong"+ count);
            busnv.getlist();

        for (dtonhanvien nhanvien : busnv.list_nv) {
            dtochamcong cc = new dtochamcong(++count , nhanvien.getManhanvien(), 0, 0, 0, 0, 0, "", currMonth, currYear);
            System.out.print("ma cham cong tu dong"+ count);
            buscc.create(cc);
        }
        refreshGeneralTable((DefaultTableModel) generalTable.getModel());

        JOptionPane.showMessageDialog(null, "Bảng chấm công mới cập nhật thành công");
    }

    private void refreshGeneralTable(DefaultTableModel model) {
        // Clear existing rows
        model.setRowCount(0);

        // Repopulate with new data
        buscc.getlist();
        for (dtochamcong cc : buscc.dscc) {
            model.addRow(cc.toTableRowGeneral());
        }
    }   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Create a new JFrame
                JFrame frame = new JFrame("FormChamCong");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Add the formchamcong panel to the frame
                formchamcong panel = new formchamcong();
                frame.add(panel);

                // Set frame size and make it visible
                frame.setSize(1070, 741); // Adjust size as needed
                frame.setLocationRelativeTo(null); // Center the frame
                frame.setVisible(true);
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
