package gui.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import bus.buskhuyenmai;
import bus.busuudai;
import com.toedter.calendar.JDateChooser;
import dto.dtokhuyenmai;
import dto.dtouudai;
import net.miginfocom.swing.MigLayout;

import gui.table.TableHeaderAlignment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PHUONG ANH
 */
public class formuudaivakhuyenmai extends javax.swing.JPanel {
    private JTable generalTable;
    private buskhuyenmai buskm = new buskhuyenmai();
    private busuudai busuudai = new busuudai();
    private Object columnsKM [];
    private DefaultTableModel modelKM;
    private JTable generalTableKM;
    private JTextField txtMaKM;
    private JTextField txtTenKM;
    private JDateChooser dateNgayBD;
    private JDateChooser dateNgayHetHan;
    private JTextField txtSoLuong;
    private JTextField txtPhanTramGiam;
    private JComboBox<String> cbPhanLoai;
    private Object[] columns;
    private DefaultTableModel model;
    private JTextField txtMaUuDai;
    private JTextField txtMocUuDai;
    private JTextField txtTiLeGiam;
    public formuudaivakhuyenmai() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 7 15 7 15", "[fill]", "[][fill,grow]"));
        add(createInfo("Ưu đãi và khuyến mãi"));
        add(createTab(), "gapx 7 7");

    }

    private JPanel createInfo(String title) {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap"));
        JLabel lbTitle = new JLabel(title);
        panel.add(lbTitle);
        return panel;
    }

    private Component createTab() {
        JTabbedPane tabb = new JTabbedPane();
        tabb.putClientProperty(FlatClientProperties.STYLE, ""
                + "tabType:card");
        tabb.addTab("Khuyến mãi", createBorder(createTableKhuyenMai()));
        tabb.addTab("Ưu đãi", createBorder(createTableUuDai()));
        return tabb;
    }

    private Component createBorder(Component component) {
        JPanel panel = new JPanel(new MigLayout("fill,insets 7 0 7 0", "[fill]", "[fill]"));
        panel.add(component);
        return panel;
    }

    private Component createTableUuDai() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // create table model
        columns = new Object[]{"Mã ưu đãi", "Móc ưu đãi", "Tỉ lệ giảm"};
        model = setDataTableUuDai(columns, busuudai.getList());

        // create table
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
        generalTable.putClientProperty(FlatClientProperties.STYLE, ""               
                + "background:$Table.background;");
        generalTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;");
        generalTable.putClientProperty(FlatClientProperties.STYLE, ""
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

        
        // create header
        panel.add(createHeaderDetailTable());

        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, ""
                + "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");
        panel.add(scrollPane);

        return panel;
    }

    private Component createTableKhuyenMai() {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 10 0 10 0", "[fill]", "[][][]0[fill,grow]"));

        // create table model
        columnsKM = new Object[]{"Mã khuyến mãi", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày hết hạn", "Số lượng", "Phần trăm giảm", "Số lượng đã dùng"};
        modelKM = setDataTableKhuyenMai(columnsKM, buskm.getlist());

        // create table and assign to generalTable
        generalTableKM = new JTable(modelKM);

        // table scroll
        JScrollPane scrollPane = new JScrollPane(generalTableKM);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // alignment table header
        generalTableKM.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(generalTableKM) {
            protected int getAlignment() {
                return SwingConstants.CENTER;
            }
        });

        // style
        generalTableKM.putClientProperty(FlatClientProperties.STYLE, "" + "" + "background:$Table.background;");
        generalTableKM.getTableHeader().putClientProperty(FlatClientProperties.STYLE, "" + "height:30;" + "hoverBackground:null;" + "pressedBackground:null;" + "separatorColor:$TableHeader.background;");
        generalTableKM.putClientProperty(FlatClientProperties.STYLE, "" + "rowHeight:30;" + "showHorizontalLines:true;" + "intercellSpacing:0,1;" + "cellFocusColor:$TableHeader.hoverBackground;" + "selectionBackground:$TableHeader.hoverBackground;" + "selectionInactiveBackground:$TableHeader.hoverBackground;" + "selectionForeground:$Table.foreground;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" + "trackArc:$ScrollBar.thumbArc;" + "trackInsets:3,3,3,3;" + "thumbInsets:3,3,3,3;" + "background:$Table.background;");

        
        // create header
        panel.add(createHeaderGeneralTable());

        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "" + "foreground:$Table.gridColor;");
        panel.add(separator, "height 2");

        panel.add(scrollPane);

       
        return panel;
    }

    private Component createHeaderGeneralTable() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,fill]push[][]")); 

        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(250, 28));
        txtSearch.setMaximumSize(new Dimension(260, 28));
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("source/image/icon/search.svg", 0.4f));
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if("Mã khuyến mãi".equals(cbPhanLoai.getSelectedItem()+"")){
                    modelKM.setRowCount(0);
                    modelKM = setDataTableKhuyenMai(columnsKM, buskm.getListByInt("maKhuyenMai",Integer.parseInt(txtSearch.getText())));
                    generalTableKM.setModel(modelKM);
                }
                
                if("Tên khuyến mãi".equals(cbPhanLoai.getSelectedItem()+"")){
                    modelKM.setRowCount(0);
                    modelKM = setDataTableKhuyenMai(columnsKM, buskm.getListByString("tenKhuyenMai",txtSearch.getText()));
                    generalTableKM.setModel(modelKM);
                }
               
                if("Ngày".equals(cbPhanLoai.getSelectedItem()+"")){
                    modelKM.setRowCount(0);
                    modelKM = setDataTableKhuyenMai(columnsKM, buskm.getListByDate(txtSearch.getText()));
                    generalTableKM.setModel(modelKM);
                }
                
                if(txtSearch.getText().equals("")){
                    modelKM.setRowCount(0);
                    modelKM = setDataTableKhuyenMai(columnsKM, buskm.getlist());
                    generalTableKM.setModel(modelKM);
                }
            }
        });
 

        

        JButton btncreate = new JButton("Thêm");
        btncreate.addActionListener(e -> {
            String title = "Thêm khuyến mãi";
            JDialog dialog = new JDialog((JFrame) null,title , true);
            try {
                dialog.setContentPane(SimpleInputForms(title));
            } catch (SQLException ex) {
                Logger.getLogger(formhopdong.class.getName()).log(Level.SEVERE, null, ex);
            }
            dialog.pack();
            dialog.setLocationRelativeTo(generalTableKM);
            dialog.setVisible(true);
        });
        
        
        cbPhanLoai = new JComboBox<>(new String[] {
            "Mã khuyến mãi", 
            "Tên khuyến mãi",
            "Ngày"
        });
        
        
        
        
        
        JButton btndelete = new JButton("Xóa");
        btndelete.addActionListener(e->{
            if(e.getSource()==btndelete){
                int selectedRow = generalTableKM.getSelectedRow();
                if (selectedRow != -1) { // Kiểm tra nếu có hàng được chọn
                    Object value = generalTableKM.getValueAt(selectedRow, 0);
                    if(buskm.Deleted(Integer.parseInt(value+""))){
                        resetDataTableKhuyenMai();
                        JOptionPane.showMessageDialog( null, "Xóa khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn khuyến mãi để xóa!","Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        JButton btnedit = new JButton("Sửa");
        btnedit.addActionListener(e->{
            
            int selectedRow = generalTableKM.getSelectedRow();
            if (selectedRow != -1) { // Kiểm tra nếu có hàng được chọn
                Object makm = generalTableKM.getValueAt(selectedRow, 0);
                Object tenkm = generalTableKM.getValueAt(selectedRow, 1);
                Object ngaybd = generalTableKM.getValueAt(selectedRow, 2);
                Object ngayhh = generalTableKM.getValueAt(selectedRow, 3);
                Object soluong = generalTableKM.getValueAt(selectedRow,4);
                Object phantram = generalTableKM.getValueAt(selectedRow,5);
                String title = "Sửa khuyến mãi";
                JDialog dialog = new JDialog((JFrame) null, title, true);
                try {
                    dialog.setContentPane(SimpleInputForms(title));
                    txtMaKM.setText(makm+"");
                    txtTenKM.setText(tenkm+"");
                    txtSoLuong.setText(soluong+"");
                    txtPhanTramGiam.setText(phantram+"");
                    
                    // Định dạng và đặt giá trị mặc định
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    
                    Date defaultDenNgay = sdf.parse(ngaybd+"");
                    dateNgayBD.setDate(defaultDenNgay);
                    
                    Date defaultNgayHH = sdf.parse(ngayhh+"");
                    dateNgayHetHan.setDate(defaultNgayHH);
                    
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(formhopdong.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.pack();
                dialog.setLocationRelativeTo(generalTable);
                dialog.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null,"Bạn chưa chọn khuyến mãi để sửa!","Lỗi", JOptionPane.ERROR_MESSAGE);
                
            }
            
            
            
        });
        
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e->{
            modelKM.setRowCount(0);
            modelKM = setDataTableKhuyenMai(columnsKM, buskm.getlist());
            generalTableKM.setModel(modelKM);
            txtSearch.setText("");
        });
        
        // Adding components with updated layout constraints
        panel.add(new JLabel("Tìm kiếm theo: "),"split 4");
        panel.add(cbPhanLoai);
        panel.add(txtSearch); 
        panel.add(btnReset);
        panel.add(btncreate);
        panel.add(btnedit);
        panel.add(btndelete, "wrap");
        

        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        return panel;
    }

    private Component createHeaderDetailTable() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,fill]push[][]")); 
        
        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(250, 28));
        txtSearch.setMaximumSize(new Dimension(260, 28));
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("../../source/image/icon/search.png", 0.4f));
        
        JButton btnAdd = new JButton("Thêm");
        btnAdd.addActionListener(e -> {
            String title = "Thêm ưu đãi";
            JDialog dialog = new JDialog((JFrame) null,title , true);
            try {
                dialog.setContentPane(SimpleInputFormUuDai(title));
            } catch (SQLException ex) {
                Logger.getLogger(formhopdong.class.getName()).log(Level.SEVERE, null, ex);
            }
            dialog.pack();
            dialog.setLocationRelativeTo(generalTable);
            dialog.setVisible(true);
        });
        
        JButton btnEdit = new JButton("Sửa");
        
        btnEdit.addActionListener(e->{
            
            int selectedRow = generalTable.getSelectedRow();
            if (selectedRow != -1) { // Kiểm tra nếu có hàng được chọn
                Object mauudai = generalTable.getValueAt(selectedRow, 0);
                Object mocuudai = generalTable.getValueAt(selectedRow, 1);
                Object tilegiam = generalTable.getValueAt(selectedRow, 2);
               
                String title = "Sửa ưu đãi";
                JDialog dialog = new JDialog((JFrame) null, title, true);
                try {
                    dialog.setContentPane(SimpleInputFormUuDai(title));
                    
                    txtMaUuDai.setText(mauudai+"");
                    txtMocUuDai.setText(mocuudai+"");
                    txtTiLeGiam.setText(tilegiam+"");
                } catch (SQLException ex) {
                    Logger.getLogger(formhopdong.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.pack();
                dialog.setLocationRelativeTo(generalTable);
                dialog.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null,"Bạn chưa chọn ưu đãi để sửa!","Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            
        });
        
        
        JButton btnDelete = new JButton("Xóa");
        btnDelete.addActionListener(e->{
            if(e.getSource()==btnDelete){
                int selectedRow = generalTable.getSelectedRow();
                if (selectedRow != -1) { // Kiểm tra nếu có hàng được chọn
                    Object value = generalTable.getValueAt(selectedRow, 0);
                    if(busuudai.Deleted(Integer.parseInt(value+""))){
                        resetDataTableUuDai();
                        JOptionPane.showMessageDialog( null, "Xóa ưu đãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn ưu đãi để xóa!","Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e->{
            txtSearch.setText("");
            resetDataTableUuDai();
        });
        
        
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                
                model.setRowCount(0);
                model = setDataTableUuDai(columns, busuudai.getListByCondition(txtSearch.getText()));
                generalTable.setModel(model);
                
                if(txtSearch.getText().equals("")){
                    model.setRowCount(0);
                    model = setDataTableUuDai(columns, busuudai.getList());
                    generalTable.setModel(model);
                }
            }
        });
        
        // Adding components with updated layout constraints
        panel.add(new JLabel("Tìm kiếm: "),"split 3");
        panel.add(txtSearch, "split 2");              
        panel.add(btnReset);
        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnDelete);
        
        
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
        return panel;
    }
    
    



   public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            // Create a new JFrame
            JFrame frame = new JFrame("FormChamCong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Add the formchamcong panel to the frame
            formuudaivakhuyenmai panel = new formuudaivakhuyenmai();
            frame.add(panel);
            
            // Set frame size and make it visible
            frame.setSize(1070, 741); // Adjust size as needed
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }
   
    public DefaultTableModel setDataTableKhuyenMai(Object columns [], ArrayList<dtokhuyenmai> arr){
        DefaultTableModel model1 = new  DefaultTableModel(columns,0);
        for(dtokhuyenmai km : arr){
            Object[] row = {
                km.getMaKhuyenMai(),km.getTenKhuyenMai(),km.getNgayBatDau(),km.getNgayHetHan(),km.getSoLuong(),km.getPhanTram(),km.getSoLuongDaDung()
            };
            model1.addRow(row); // Thêm hàng vào DefaultTableModel
        }
        return model1;
    }
    
    
    public DefaultTableModel setDataTableUuDai(Object columns [], ArrayList<dtouudai> arr){
        DefaultTableModel model1 = new  DefaultTableModel(columns,0);
        for(dtouudai ud : arr){
            Object[] row = {
                ud.getMaUuDai(),ud.getMocUuDai(),ud.getTiLeGiam()
            };
            model1.addRow(row); // Thêm hàng vào DefaultTableModel
        }
        return model1;
    }
    
    
    private JPanel SimpleInputForms(String checkForm) throws SQLException {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));

        // Tạo các trường nhập liệu
        txtMaKM = new JTextField();
        txtMaKM.setEnabled(false);
        txtTenKM = new JTextField();
        dateNgayBD = new JDateChooser();
        dateNgayHetHan = new JDateChooser();
        ((JTextField) dateNgayBD.getDateEditor().getUiComponent()).setEditable(false);
        ((JTextField) dateNgayHetHan.getDateEditor().getUiComponent()).setEditable(false);

        txtSoLuong = new JTextField(); // Thêm trường số lượng
        txtPhanTramGiam = new JTextField(); // Thêm trường phần trăm giảm

        // Đặt định dạng cho trường ngày
        dateNgayBD.setDateFormatString("yyyy-MM-dd");
        dateNgayHetHan.setDateFormatString("yyyy-MM-dd");

        // Đặt placeholder text cho các trường nhập liệu
        txtMaKM.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mã hợp đồng");
        txtSoLuong.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập số lượng");
        txtPhanTramGiam.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập phần trăm giảm");

        // Thêm các thành phần vào panel với label tương ứng
        panel.add(new JLabel("Mã hợp đồng"), "gapy 5 0");
        panel.add(txtMaKM);

        panel.add(new JLabel("Tên khuyến mãi"), "gapy 5 0");
        panel.add(txtTenKM);

        panel.add(new JLabel("Ngày bắt đầu"), "gapy 5 0");
        panel.add(dateNgayBD);

        panel.add(new JLabel("Ngày hết hạn"), "gapy 5 0");
        panel.add(dateNgayHetHan);

        panel.add(new JLabel("Số lượng"), "gapy 5 0"); // Label và trường số lượng
        panel.add(txtSoLuong);

        panel.add(new JLabel("Phần trăm giảm"), "gapy 5 0"); // Label và trường phần trăm giảm
        panel.add(txtPhanTramGiam);

        
        
        txtMaKM.setText((buskm.getMaxMaKhuyenMai()+1)+"");
        // Tạo nút xác nhận và hủy bỏ
        JButton btnXacNhan = new JButton("Xác nhận");
        JButton btnHuyBo = new JButton("Hủy bỏ");

        // Thêm các nút vào panel trên cùng một hàng
        panel.add(btnXacNhan, "split 2, align center, sizegroup btn");
        panel.add(btnHuyBo, "sizegroup btn");
        
        
        btnXacNhan.addActionListener(e -> {
            if (e.getSource() == btnXacNhan) {
                // Kiểm tra nếu tên khuyến mãi rỗng
                String ten = txtTenKM.getText().trim();
                if (ten.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }else if(checkForm.equals("Thêm khuyến mãi")){
                    for(dtokhuyenmai tmp : buskm.getlist()){
                        if(tmp.getTenKhuyenMai().equals(txtTenKM.getText())){
                            JOptionPane.showMessageDialog(null, "Tên khuyến mãi đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                } 

                // Kiểm tra nếu ngày bắt đầu và ngày hết hạn không được chọn
                if (dateNgayBD.getDate() == null || dateNgayHetHan.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ ngày bắt đầu và ngày hết hạn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra nếu ngày bắt đầu lớn hơn hoặc bằng ngày hết hạn
                if (dateNgayBD.getDate().after(dateNgayHetHan.getDate())) {
                    JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn ngày hết hạn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra nếu số lượng là một số nguyên và không nhỏ hơn hoặc bằng 0
                int soLuong = 0;
                try {
                    soLuong = Integer.parseInt(txtSoLuong.getText().trim());
                    if (soLuong <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số lượng phải là một số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra nếu phần trăm giảm là một số hợp lệ (0 <= phần trăm <= 100)
                double phanTram = 0;
                try {
                    phanTram = Double.parseDouble(txtPhanTramGiam.getText().trim());
                    if (phanTram <= 0 || phanTram > 100) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Phần trăm giảm phải là số từ 1 đến 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Nếu tất cả dữ liệu hợp lệ, tiếp tục thêm khuyến mãi
                int ma = Integer.parseInt(txtMaKM.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date ngayBD = dateNgayBD.getDate();
                Date ngayHH = dateNgayHetHan.getDate();

                // Tạo đối tượng khuyến mãi
                dtokhuyenmai km = new dtokhuyenmai(ma, ten, ngayBD, ngayHH, soLuong, phanTram);
                if(checkForm.equals("Sửa khuyến mãi")){
                    for(dtokhuyenmai tmp : buskm.getlist()){
                        if(km.getTenKhuyenMai().equals(tmp.getTenKhuyenMai()) && km.getMaKhuyenMai()!=tmp.getMaKhuyenMai() ){
                            JOptionPane.showMessageDialog(null, "Tên khuyến mãi đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                // Thêm khuyến mãi vào cơ sở dữ liệu
                
                
                if(checkForm.equals("Thêm khuyến mãi")){
                    if (buskm.addKhuyenMai(km)) {
                        JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        // Reset form sau khi thêm thành công
                        txtMaKM.setText((buskm.getMaxMaKhuyenMai() + 1) + "");
                        txtPhanTramGiam.setText("");
                        txtSoLuong.setText("");
                        txtTenKM.setText("");
                        dateNgayBD.setDate(null);
                        dateNgayHetHan.setDate(null);

                        // Cập nhật bảng dữ liệu khuyến mãi
                        resetDataTableKhuyenMai();
                    }
                }
                
                if(checkForm.equals("Sửa khuyến mãi")){
                    if (buskm.Update(km)) {
                        JOptionPane.showMessageDialog(null, "Sửa khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        Window window = SwingUtilities.getWindowAncestor(btnXacNhan);
                        if (window instanceof JDialog jDialog) {
                            jDialog.dispose();
                        }
                        resetDataTableKhuyenMai();
                    }
                }
            }
        });
        
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
        
        return panel;
    }

    void resetDataTableKhuyenMai(){
        modelKM.setRowCount(0); // Xóa tất cả hàng hiện tại
        modelKM = setDataTableKhuyenMai(columnsKM, buskm.getlist()); // Lấy lại dữ liệu gốc từ cơ sở dữ liệu
        generalTableKM.setModel(modelKM); // Cập nhật lại JTable với mô hình mới
    }
    
    void resetDataTableUuDai(){
        model.setRowCount(0); // Xóa tất cả hàng hiện tại
        model = setDataTableUuDai(columns, busuudai.getList()); // Lấy lại dữ liệu gốc từ cơ sở dữ liệu
        generalTable.setModel(model); // Cập nhật lại JTable với mô hình mới
    }
    
    private JPanel SimpleInputFormUuDai(String checkForm) throws SQLException {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));

        // Tạo các trường nhập liệu
        txtMaUuDai = new JTextField();
        txtMaUuDai.setEnabled(false);
        txtMocUuDai = new JTextField();
        txtTiLeGiam = new JTextField(); // Thêm trường số lượng
       


        // Thêm các thành phần vào panel với label tương ứng
        panel.add(new JLabel("Mã ưu đãi"), "gapy 5 0");
        panel.add(txtMaUuDai);

        panel.add(new JLabel("Móc ưu đãi"), "gapy 5 0");
        panel.add(txtMocUuDai);

        panel.add(new JLabel("Tỉ lệ giảm"), "gapy 5 0");
        panel.add(txtTiLeGiam);

        JButton btnXacNhan = new JButton("Xác nhận");
        JButton btnHuyBo = new JButton("Hủy bỏ");

        // Thêm các nút vào panel trên cùng một hàng
        panel.add(btnXacNhan, "split 2, align center, sizegroup btn");
        panel.add(btnHuyBo, "sizegroup btn");
        
        
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
        
        txtMaUuDai.setText((busuudai.getMaxMaUuDai()+1)+"");
        btnXacNhan.addActionListener(e -> {
            try {
                // Kiểm tra trường Mã Ưu Đãi
                if (txtMaUuDai.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mã ưu đãi không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int maUuDai = Integer.parseInt(txtMaUuDai.getText().trim());
                if (maUuDai <= 0) {
                    JOptionPane.showMessageDialog(null, "Mã ưu đãi phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (maUuDai > Integer.MAX_VALUE) {
                    JOptionPane.showMessageDialog(null, "Mã ưu đãi vượt quá giới hạn tối đa của kiểu số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra trường Mốc Ưu Đãi
                if (txtMocUuDai.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mốc ưu đãi không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int mocUuDai = Integer.parseInt(txtMocUuDai.getText().trim());
                if (mocUuDai < 0) {
                    JOptionPane.showMessageDialog(null, "Mốc ưu đãi phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (mocUuDai > Integer.MAX_VALUE) {
                    JOptionPane.showMessageDialog(null, "Mốc ưu đãi vượt quá giới hạn tối đa của kiểu số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Kiểm tra trường Tỉ Lệ Giảm
                if (txtTiLeGiam.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tỉ lệ giảm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int tiLeGiam = Integer.parseInt(txtTiLeGiam.getText().trim());
                if (tiLeGiam <= 0 || tiLeGiam > 100) {
                    JOptionPane.showMessageDialog(null, "Tỉ lệ giảm phải nằm trong khoảng từ 1 đến 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (tiLeGiam > Integer.MAX_VALUE) {
                    JOptionPane.showMessageDialog(null, "Tỉ lệ giảm vượt quá giới hạn tối đa của kiểu số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Nếu tất cả đều hợp lệ, tạo đối tượng DTO
                dtouudai ud = new dtouudai(maUuDai, mocUuDai, tiLeGiam, 0);
                
                if(checkForm.equals("Thêm ưu đãi")){
                    if (busuudai.addUuDai(ud)) {
                        JOptionPane.showMessageDialog(null, "Thêm khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        // Reset form sau khi thêm thành công
                        txtMaUuDai.setText((busuudai.getMaxMaUuDai()+ 1) + "");
                        txtMocUuDai.setText("");
                        txtTiLeGiam.setText("");
                       
                        // Cập nhật bảng dữ liệu khuyến mãi
                        resetDataTableUuDai();
                    }
                }
                
                if(checkForm.equals("Sửa ưu đãi")){
                    if (busuudai.Update(ud)) {
                        JOptionPane.showMessageDialog(null, "Sửa ưu đãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        Window window = SwingUtilities.getWindowAncestor(btnHuyBo);
                        if (window instanceof JDialog jDialog) {
                            jDialog.dispose();
                        }
                        resetDataTableUuDai();
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        

        return panel;
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
