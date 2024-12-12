/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import bus.bushoadon;
import bus.buscthoadon;
import bus.buskhuyenmai;
import bus.bustichdiem;
import dto.dtohoadon;
import dto.dtocthoadon;


import net.miginfocom.swing.MigLayout;

import gui.table.TableHeaderAlignment;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author PHUONG ANH
 */
public class formhoadon extends javax.swing.JPanel {
    private JTable generalTable;
    private bushoadon bushd = new bushoadon();
    private buscthoadon buscthd = new buscthoadon();
    private JButton btnDetail;
    
    /**
     * Creates new form formhoadon
     */
    public formhoadon() {
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 7 15 7 15", "[fill]", "[][fill,grow]"));
        Component createTable = createGeneralTable();
        createTable.setPreferredSize(new Dimension(1070, 700));
        add(createTable);
    }

    private Component createGeneralTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // create table model
        Object columns[] = new Object[]{"Mã HĐ","Ngày mua",  "Tên khách hàng", "Mã khuyến mãi", "Tổng tiền", "Nhân viên thanh toán", "Ghi chú"};
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

        generalTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" + "height:30;" + "hoverBackground:null;" + "pressedBackground:null;" + "separatorColor:$TableHeader.background;");
        generalTable.putClientProperty(FlatClientProperties.STYLE, "" + "rowHeight:30;" + "showHorizontalLines:true;" + "intercellSpacing:0,1;" + "cellFocusColor:$TableHeader.hoverBackground;" + "selectionBackground:$TableHeader.hoverBackground;" + "selectionInactiveBackground:$TableHeader.hoverBackground;" + "selectionForeground:$Table.foreground;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" + "trackArc:$ScrollBar.thumbArc;" + "trackInsets:3,3,3,3;" + "thumbInsets:3,3,3,3;" + "background:$Table.background;");

        // create title
        JLabel title = new JLabel("Danh sách hóa đơn");
        title.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +2");
        panel.add(title, "gapx 20");

        // create header
        panel.add(createHeaderGeneralTable());

        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "" + "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");

        panel.add(scrollPane);

        bushd.getlist();
        for (dtohoadon hd : bushd.dshd) {
            model.addRow(hd.toTableRow());
        }
            // Mouse listener for table
    generalTable.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableMouseClicked(evt);
        }
    });

        return panel;
    }
    private Component createDetailTable(int hdIDOnClick) {
    JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

    // Create table model
    Object[] columns = {"Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền"};
    DefaultTableModel model = new DefaultTableModel(columns, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    // Create table and assign to generalTable
    JTable table = new JTable(model);

    // Table scroll with size adjustments
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    scrollPane.setPreferredSize(new Dimension(400, 250)); // Set preferred size
    scrollPane.setMinimumSize(new Dimension(400, 250));   // Set minimum size if needed

    // Table header alignment
    table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table) {
        protected int getAlignment() {
            return SwingConstants.CENTER;
        }
    });

    table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" +
            "height:30;" +
            "hoverBackground:null;" +
            "pressedBackground:null;" +
            "separatorColor:$TableHeader.background;");
    table.putClientProperty(FlatClientProperties.STYLE, "" +
            "rowHeight:30;" +
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

    // Adding separator
    JSeparator separator = new JSeparator();
    separator.putClientProperty(FlatClientProperties.STYLE, "" + "foreground:$Table.gridColor;");
    panel.add(separator, "height 2");

    // Add scrollPane to the panel
    panel.add(scrollPane, "growx"); // Adjust with growx to expand width

    // Fill table data
    buscthd.getlistbyhoadon(hdIDOnClick);
    for (dtocthoadon hd : buscthd.dscthd) {
        model.addRow(hd.toTableRow());
    }
    return panel;
}

    private Component createHeaderGeneralTable() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,fill]push[][]")); 

        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(250, 28));
        txtSearch.setMaximumSize(new Dimension(260, 28));
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("source/image/icon/search.svg", 0.4f));
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm mã HĐ ,tên NV, tên KH ");

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(e -> searching(txtSearch));

        btnDetail = new JButton("Xem chi tiết");
        btnDetail.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Hãy chọn vào 1 hóa đơn để xem chi tiết");
            }
        );
        
        JButton btnReload = new JButton("Reload");
        btnReload.addActionListener(e -> {
            txtSearch.setText("");
            // Clear the row sorter to display all rows
            generalTable.setRowSorter(null);
            DefaultTableModel model = (DefaultTableModel) generalTable.getModel();
            model.setRowCount(0); // Clear existing rows
            
            // Refresh the data source
            bushd.getlist(); 
            // Reload the data into the table
            for (dtohoadon hd : bushd.dshd) {
                model.addRow(hd.toTableRow());
            }
            // Notify table model of data changes
            model.fireTableDataChanged();
        });

        panel.add(txtSearch, "split 2");
        panel.add(btnSearch);                    

        panel.add(btnReload, "split 2");
        panel.add(btnDetail);                    

        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        return panel;
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

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(generalTable.getModel());
        generalTable.setRowSorter(sorter);

        RowFilter<TableModel, Object> filter;
        if (searchText.chars().allMatch(Character::isDigit)) {
            filter = RowFilter.regexFilter(searchText, 0); // Column index 0 for numeric search
        } else {
            // Combine filters for columns 2 and 5
            java.util.List<RowFilter<TableModel, Object>> filters = new ArrayList<>();
            filters.add(RowFilter.regexFilter("(?i)" + searchText, 2,5)); // Case-insensitive search in column 2
            filter = RowFilter.andFilter(filters);
        }
        sorter.setRowFilter(filter);

        java.util.List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);

        if (generalTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu");
        }
    }

    private JPanel detailForms(int hdIDOnClick){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        JTextField txtHDid = new JTextField();
        txtHDid.setEditable(false);

        JTextField txtDate = new JTextField();
        txtDate.setEditable(false);

        JTextField txtKHid = new JTextField();
        txtKHid.setEditable(false);

        JTextField txtKHname = new JTextField();
        txtKHname.setEditable(false);

        JTextField txtNVid = new JTextField();
        txtNVid.setEditable(false);

        JTextField txtNVname = new JTextField();
        txtNVname.setEditable(false);

        JTextField txtKMid = new JTextField();
        txtKMid.setEditable(false);

        JTextField txtKMname = new JTextField();
        txtKMname.setEditable(false);

        JTextField txtTDid = new JTextField();
        txtTDid.setEditable(false);

        JTextField txtScore = new JTextField();
        txtScore.setEditable(false);

        JTextArea txtNote = new JTextArea();
        txtNote.setWrapStyleWord(true);
        txtNote.setLineWrap(true);
        txtNote.setEditable(false);
        txtNote.setWrapStyleWord(true);
        txtNote.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(txtNote);

        panel.add(new JLabel("Hóa đơn"), "gapy 5 0");
        panel.add(txtHDid, "split 2, growx 0.5, wmin 100"); // Half width
        panel.add(txtDate, "growx"); // Full width
        txtHDid.setText(String.valueOf(hdIDOnClick));
        txtDate.setText(String.valueOf(bushd.get(hdIDOnClick).getNgayMua()));

        panel.add(new JLabel("Thông tin khách hàng"), "gapy 5 0");
        panel.add(txtKHid, "split 2, growx 0.5, wmin 100"); // Half width
        panel.add(txtKHname, "growx"); // Full width
        txtKHid.setText(String.valueOf(bushd.get(hdIDOnClick).getMaKhachHang()));
        txtKHname.setText(String.valueOf(bushd.get(hdIDOnClick).getTenkhachhang()));

        panel.add(new JLabel("Nhân viên thanh toán"), "gapy 5 0");
        panel.add(txtNVid, "split 2, growx 0.5, wmin 100"); // Half width
        panel.add(txtNVname, "growx"); // Full width
        txtNVid.setText(String.valueOf(bushd.get(hdIDOnClick).getMaNhanVien()));
        txtNVname.setText(String.valueOf(bushd.get(hdIDOnClick).getTennhanvien()));

        panel.add(new JLabel("Chương trình khuyến mãi"), "gapy 5 0");
        panel.add(txtKMid, "split 2, growx 0.5, wmin 100"); // Half width
        panel.add(txtKMname, "growx"); // Full width
        int kmid = bushd.get(hdIDOnClick).getMaKhuyenMai();
        txtKMid.setText(String.valueOf(kmid));
        if(kmid == 0 ){
            txtKMname.setText("");
        }
        else{
            buskhuyenmai km = new buskhuyenmai();
            txtKMname.setText(km.getkmbyid(kmid).getTenKhuyenMai());
        }
        
        
        panel.add(new JLabel("Mã tích điểm"), "split 2, growx 0.5, wmin 100");
        panel.add(new JLabel("Điểm tích lũy"));
        panel.add(txtTDid, "split 2"); 
        panel.add(txtScore); // Full width
        int tdid = bushd.get(hdIDOnClick).getMaTichDiem();
        txtTDid.setText(String.valueOf(tdid));
        if(tdid == 0 ){
            txtScore.setText("");
        }
        else{
            bustichdiem td = new bustichdiem();
            txtScore.setText(String.valueOf(td.get(tdid).getDiemTichLuy()));
        }
        

        panel.add(new JLabel("Ghi chú"), "gapy 5 0");
        panel.add(scroll, "height 50,grow,pushy");
        txtNote.setText(String.valueOf(bushd.get(hdIDOnClick).getGhiChu()));

        panel.add(createDetailTable(hdIDOnClick));
        return panel;
    }
    
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {
        for (ActionListener listener : btnDetail.getActionListeners()){
        btnDetail.removeActionListener(listener);
        }

        int row = generalTable.getSelectedRow();
        int hdIDOnClick = (int) generalTable.getValueAt(row,0);
        
        btnDetail.addActionListener(e -> {
            JDialog dialog = new JDialog((JFrame) null, "Chi tiết hóa đơn", true);
            dialog.setContentPane(detailForms(hdIDOnClick));
            dialog.setLocation(600,100);
            dialog.pack();
            dialog.setVisible(true);
        });
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formhoadon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formhoadon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formhoadon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formhoadon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Create a new JFrame
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Add the formhoadon panel to the frame
                formhoadon panel = new formhoadon();
                frame.add(panel);

                // Set frame size and make it visible
                frame.setSize(1070, 741); // Adjust size as needed
                frame.setLocationRelativeTo(null); // Center the frame
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
