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
import bus.bushopdong;
import bus.busnhanvien;
import dao.daohopdong;
import dto.dtohoadon;
import dto.dtocthoadon;
import dto.dtohopdong;
import gui.modal.ModalDialog;
import gui.modal.component.SimpleModalBorder;

import net.miginfocom.swing.MigLayout;

import gui.table.TableHeaderAlignment;
import gui.modal.option.*;
import gui.simple.SimpleInputForms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;
import dto.dtonhanvien;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class formhopdong extends javax.swing.JPanel {
    private JTable generalTable;
    private Object columns[];
    private DefaultTableModel model;
    private bushopdong bushd = new bushopdong();
    private JTextField txtMaHD;
    private JDateChooser dateTuNgay;
    private JDateChooser dateDenNgay;
    private JTextField txtLuongCoBan;
    private JComboBox<String> comboMaNV;
    private JComboBox<String> comboTenNV;
    public formhopdong() throws SQLException {
        init();
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
    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 7 15 7 15", "[fill]", "[][fill,grow]"));
        Component createTable = createGeneralTable();
        add(createTable);
    }

    private Component createGeneralTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // create table model
        columns = new Object[]{"Mã HĐ", "Từ ngày", "Đến ngày", "Lương cơ bản", "Mã nhân viên"};
        model = setDataTable(columns, bushd.getlist());

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
        generalTable.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "background:$Table.background;");
        generalTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" +
                "height:30;" +
                "hoverBackground:null;" +
                "pressedBackground:null;" +
                "separatorColor:$TableHeader.background;");
        generalTable.putClientProperty(FlatClientProperties.STYLE, "" +
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

        // create title
        JLabel title = new JLabel("Danh sách hợp đồng", SwingConstants.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        panel.add(title, "align center, gapx 20");

        // create header
        panel.add(createHeaderGeneralTable());

        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");

        panel.add(scrollPane);

        return panel;
    }


        private Component createDetailTable() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // Create table model
        columns = new Object[]{"Mã HD", "Từ ngày", "Đến ngày", "Lương cơ bản", "Mã nhân viên"};
        model = new DefaultTableModel(columns, 0) {
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
        scrollPane.setPreferredSize(new Dimension(550, 250)); // Set preferred size
        scrollPane.setMinimumSize(new Dimension(550, 250));   // Set minimum size if needed

        // Table header alignment
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(generalTable) {
            protected int getAlignment() {
                return SwingConstants.CENTER;
            }
        });

        // Style settings for generalTable
        table.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "background:$Table.background;");
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
        return panel;
    }

    private Component createHeaderGeneralTable() {
        //JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[]10[fill,230]10[]push[]10[]10[]", "[]"));
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[]10[fill,100]10[]push[]10[]10[]", "[]"));
        // Tạo JComboBox cho các tùy chọn tìm kiếm
        String[] searchOptions = {"Mã hợp đồng", "Mã nhân viên"};
        JComboBox<String> cmbSearchOptions = new JComboBox<>(searchOptions);
        
        // Tạo các thành phần bên trái
        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("./source/image/icon/search.png", 0.4f));
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if("Mã hợp đồng".equals(cmbSearchOptions.getSelectedItem()+"")){
                    model.setRowCount(0);
                    model = setDataTable(columns, bushd.getlistConditon("mahopdong",txtSearch.getText()));
                    generalTable.setModel(model);
                }
                
                if("Mã nhân viên".equals(cmbSearchOptions.getSelectedItem()+"")){
                    model.setRowCount(0);
                    model = setDataTable(columns, bushd.getlistConditon("maNhanVien",txtSearch.getText()));
                    generalTable.setModel(model);
                }
                
                if(txtSearch.getText().equals("")){
                    model.setRowCount(0);
                    model = setDataTable(columns, bushd.getlist());
                    generalTable.setModel(model);
                }
            }
        });

       
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener((ActionEvent e) -> {
            // Xóa dữ liệu trong JTextField
            txtSearch.setText(""); // Giả sử bạn có một JTextField tên là txtSearch
            
            resetDataTable();
        });
        // Thêm các thành phần vào layout ở phía bên trái
        panel.add(lblTimKiem);
        panel.add(cmbSearchOptions, "width 120!"); // Đặt chiều rộng cho JComboBox
        panel.add(txtSearch, "width 200!"); // Đặt chiều rộng cho JTextField
        panel.add(btnReset);

        // Tạo các nút bên phải
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");

        // Thêm các nút vào layout ở phía bên phải
        panel.add(btnAdd, "gapleft push");
        panel.add(btnEdit);
        panel.add(btnDelete);

        // Thiết lập style cho panel
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        
        
        btnAdd.addActionListener(e -> {
            String title = "Thêm hợp đồng";
            JDialog dialog = new JDialog((JFrame) null,title , true);
            try {
                dialog.setContentPane(SimpleInputForms(title));
            } catch (SQLException ex) {
                Logger.getLogger(formhopdong.class.getName()).log(Level.SEVERE, null, ex);
            }
            dialog.pack();
            dialog.setLocationRelativeTo(generalTable);
            dialog.setVisible(true);
        });
        
        
        
        
        btnDelete.addActionListener(e->{
            int selectedRow = generalTable.getSelectedRow();
            if (selectedRow != -1) { // Kiểm tra nếu có hàng được chọn
                Object value = generalTable.getValueAt(selectedRow, 0);
                if(bushd.Deleted(Integer.parseInt(value+""))){
                    JOptionPane.showMessageDialog(null, "Xóa hợp đồng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    resetDataTable();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Bạn chưa chọn hàng để xóa!","Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnEdit.addActionListener(e->{
            
            int selectedRow = generalTable.getSelectedRow();
            if (selectedRow != -1) { // Kiểm tra nếu có hàng được chọn
                Object mahd = generalTable.getValueAt(selectedRow, 0);
                Object manv = generalTable.getValueAt(selectedRow, 4);
                Object luongcb = generalTable.getValueAt(selectedRow, 3);
                Object denngay = generalTable.getValueAt(selectedRow, 2);
                Object tungay = generalTable.getValueAt(selectedRow, 1);
                String title = "Sửa hợp đồng";
                JDialog dialog = new JDialog((JFrame) null, title, true);
                try {
                    dialog.setContentPane(SimpleInputForms(title));
                    txtMaHD.setText(mahd+"");
                    comboMaNV.setSelectedItem(manv+"");
                    txtLuongCoBan.setText(luongcb+"");
                    
                    
                    // Định dạng và đặt giá trị mặc định
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    
                    Date defaultDenNgay = sdf.parse(denngay+"");
                    dateDenNgay.setDate(defaultDenNgay);
                    
                    Date defaultTuNgay = sdf.parse(tungay+"");
                    dateTuNgay.setDate(defaultTuNgay);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(formhopdong.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(formhopdong.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.pack();
                dialog.setLocationRelativeTo(generalTable);
                dialog.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null,"Bạn chưa chọn hàng để sửa!","Lỗi", JOptionPane.ERROR_MESSAGE);
                
            }
            
            
            
        });
        return panel;
    }

    
   
    
    
    public DefaultTableModel setDataTable(Object columns1 [], ArrayList<dtohopdong> arr){
        DefaultTableModel model1 = new DefaultTableModel(columns1, 0);
        for (dtohopdong hd : arr) {
            Object[] row = {
                hd.getMaHopDong(),hd.getTuNgay(),hd.getDenNgay(),hd.getLuongCoBan(),hd.getMaNV()
            };
            model1.addRow(row); // Thêm hàng vào DefaultTableModel
        }
        return model1;
    }
    
    private JPanel SimpleInputForms(String checkForm) throws SQLException {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));

        // Tạo các trường nhập liệu
        txtMaHD = new JTextField();
        int maHDNew = bushd.getMaxMaHopDong()+1;
        txtMaHD.setText(maHDNew+"");
        txtMaHD.setEnabled(false);
        dateTuNgay = new JDateChooser();
        dateDenNgay = new JDateChooser();
        ((JTextField) dateTuNgay.getDateEditor().getUiComponent()).setEditable(false);
        ((JTextField) dateDenNgay.getDateEditor().getUiComponent()).setEditable(false);
        txtLuongCoBan = new JTextField();

        // ComboBox cho mã nhân viên với dữ liệu mẫu (thêm mã nhân viên thực tế nếu có)
        comboMaNV = new JComboBox<>();
        comboMaNV.addItem("");
        ArrayList<String> listMaNV = bushd.getListMaNV();
        for(String maNV : listMaNV){
            comboMaNV.addItem(maNV+"");
        }

        // Đặt định dạng cho trường ngày
        dateTuNgay.setDateFormatString("yyyy-MM-dd");
        dateDenNgay.setDateFormatString("yyyy-MM-dd");

        // Đặt placeholder text cho các trường nhập liệu
        txtMaHD.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mã hợp đồng");
        txtLuongCoBan.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập lương cơ bản");

        // Thêm các thành phần vào panel với label tương ứng
        panel.add(new JLabel("Mã hợp đồng"), "gapy 5 0");
        panel.add(txtMaHD);

        panel.add(new JLabel("Từ ngày"), "gapy 5 0");
        panel.add(dateTuNgay);

        panel.add(new JLabel("Đến ngày"), "gapy 5 0");
        panel.add(dateDenNgay);

        panel.add(new JLabel("Lương cơ bản"), "gapy 5 0");
        panel.add(txtLuongCoBan);

        panel.add(new JLabel("Mã nhân viên"), "gapy 5 0");
        panel.add(comboMaNV);
        
        busnhanvien busnv = new busnhanvien();
        comboTenNV = new JComboBox<>(); 
        comboTenNV.addItem("");
        for (dtonhanvien nv : busnv.list_nv){
            comboTenNV.addItem(nv.getTennhanvien());
        }
        panel.add(new JLabel("Tên nhân viên"), "gapy 5 0");
        panel.add(comboTenNV);
        
        comboTenNV.addActionListener(e->{
            String selectedTenNV = comboTenNV.getSelectedItem()+"";
            for (dtonhanvien nv : busnv.list_nv){
                if(selectedTenNV.equals(nv.getTennhanvien()+"")){
                    comboMaNV.setSelectedItem(nv.getManhanvien()+"");
                    break;
                }
            }
            if(selectedTenNV.equals("")) comboMaNV.setSelectedItem("");
        });
        
        comboMaNV.addActionListener(e->{
            String selectedMaNV = comboMaNV.getSelectedItem()+"";
            if(selectedMaNV.equals("")){
                comboTenNV.setSelectedItem("");
            }
            else{
                String selectedTenNV = busnv.gettennvbymanv(Integer.parseInt(selectedMaNV));
                comboTenNV.setSelectedItem(selectedTenNV+"");
            }

        });
        
        // Tạo nút xác nhận và hủy bỏ
        JButton btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.addActionListener(e->{
            if(e.getSource()==btnXacNhan){
                int maHD = Integer.parseInt(txtMaHD.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if(dateTuNgay.getDate() == null && dateDenNgay.getDate() == null && txtLuongCoBan.getText().equals("")||comboMaNV.getSelectedItem().equals("")||comboTenNV.getSelectedItem().equals("")){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (dateTuNgay.getDate() == null || dateDenNgay.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày từ và ngày đến!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String tuNgay = sdf.format(dateTuNgay.getDate());
                String denNgay = sdf.format(dateDenNgay.getDate());
                
   
                if(kiemTraThoiGian(tuNgay, denNgay)==false){
                    JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                if (txtLuongCoBan.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập lương cơ bản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if(kiemTraLuongCoBan(txtLuongCoBan.getText())==false){
                    JOptionPane.showMessageDialog(null, "Lương cơ bản không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                float luongcb = Float.parseFloat(txtLuongCoBan.getText());
               
                String tmp = comboMaNV.getSelectedItem()+"";
                int manv = Integer.parseInt(tmp);
                dtohopdong hd = new dtohopdong(maHD, tuNgay, denNgay, luongcb, manv, 0);
                
                if(checkForm.equals("Thêm hợp đồng")){
                    if(bushd.addHopDong(hd)){
                        JOptionPane.showMessageDialog(null, "Thêm hợp đồng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        txtMaHD.setText((bushd.getMaxMaHopDong() + 1) + "");
                        dateTuNgay.setDate(null);
                        dateDenNgay.setDate(null);
                        txtLuongCoBan.setText("");
                        comboMaNV.setSelectedIndex(0);
                        comboTenNV.setSelectedIndex(0);
                        resetDataTable();
                    }
                }
                
                if(checkForm.equals("Sửa hợp đồng")){
                    if(bushd.Update(hd)){
                        JOptionPane.showMessageDialog(null, "Sửa hợp đồng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        Window window = SwingUtilities.getWindowAncestor(btnXacNhan);
                        if (window instanceof JDialog jDialog) {
                            jDialog.dispose();
                        }
                        resetDataTable();
                    }
                }
                
            }
        });
        JButton btnHuyBo = new JButton("Hủy bỏ");
        btnHuyBo.addActionListener((var e) -> {
            if (e.getSource() == btnHuyBo) {
                int result = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc chắn muốn hủy bỏ?",
                    "Xác nhận hủy bỏ",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (result == JOptionPane.YES_OPTION) {
                    Window window = SwingUtilities.getWindowAncestor(btnHuyBo);
                    if (window instanceof JDialog jDialog) {
                        jDialog.dispose();
                    }
                }
            }
        });
        // Thêm các nút vào panel trên cùng một hàng
        panel.add(btnXacNhan, "split 2, align center, sizegroup btn");
        panel.add(btnHuyBo, "sizegroup btn");
        
        return panel;
    }

    private boolean kiemTraLuongCoBan(String luongCoBanStr) {
        try {
            // Chuyển đổi chuỗi nhập vào thành số
            double luongCoBan = Double.parseDouble(luongCoBanStr);

            // Kiểm tra xem lương cơ bản có phải là số dương không
            return luongCoBan > 0; // Lương cơ bản hợp lệ
            // Lương cơ bản không hợp lệ
        } catch (NumberFormatException e) {
            // Nếu không thể chuyển đổi chuỗi thành số, lương cơ bản không hợp lệ
            return false;
        }
    }
    
    private boolean kiemTraThoiGian(String tuNgayStr, String denNgayStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Đặt chế độ không linh hoạt để kiểm tra tính hợp lệ của ngày

        try {
            // Chuyển đổi chuỗi thành đối tượng Date
            Date tuNgay = sdf.parse(tuNgayStr);
            Date denNgay = sdf.parse(denNgayStr);

            // Kiểm tra xem từ ngày có trước hoặc bằng đến ngày không
            return !tuNgay.after(denNgay); // trả về true nếu từ ngày không lớn hơn đến ngày
        } catch (ParseException e) {
            // Nếu có lỗi khi chuyển đổi ngày, trả về false
            return false;
        }
    } 
    
    
    void resetDataTable(){
        model.setRowCount(0); // Xóa tất cả hàng hiện tại
        model = setDataTable(columns, bushd.getlist()); // Lấy lại dữ liệu gốc từ cơ sở dữ liệu
        generalTable.setModel(model); // Cập nhật lại JTable với mô hình mới
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formhopdong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            // Create a new JFrame
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Add the formhoadon panel to the frame
            formhopdong panel = null;
            try {
                panel = new formhopdong();
            } catch (SQLException ex) {
                Logger.getLogger(formhopdong.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.add(panel);
            
            // Set frame size and make it visible
            frame.setSize(1070, 741); // Adjust size as needed
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }
    
    
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
