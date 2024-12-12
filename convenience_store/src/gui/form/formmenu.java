package gui.form;

import bus.busctphieunhap;
import bus.bussanpham;
import gui.comp.SPCard;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import gui.layout.ResponsiveLayout;
import gui.model.ModelEmployee;
import dto.SampleData;
import dto.dtocthoadon;
import dto.dtoctphieunhap;
import dto.dtodonhang;
import dto.dtonhacungcap;
import dto.dtophanloai;
import dto.dtophieunhap;
import dto.dtosanpham;
import gui.comp.MenuCard;
import gui.comp.RoundedBorder;
import gui.simple.SimpleInputForms;
import gui.simple.SimpleInputFormsSanPham;
import gui.swing.dashboard.Form;
import gui.swing.dashboard.SystemForm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;


@SystemForm(name = "Responsive Layout", description = "responsive layout user interface", tags = {"card"})
public class formmenu extends Form {

    private ArrayList<dtosanpham> list_Sp;
    private List<MenuCard> cards;
    private JPanel panelCard;
    private ResponsiveLayout responsiveLayout;
    private JDialog giohangDialog;
    private static ArrayList<dtodonhang> list_donhang = new ArrayList<>();
    private JLabel imageDisplayLabel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private bussanpham busSP;
    private DefaultTableModel model;
    private JTextField quantityField;
    private ArrayList<dtophieunhap> list_PN;
    private ArrayList<dtoctphieunhap> list_CTPN;
    private int manv;
    private ArrayList<dtosanpham> list_SP_has_money = new ArrayList<>();
    private busctphieunhap busctpn = new busctphieunhap();
    public formmenu(int ma_nv) throws SQLException {
        init();
        formInit();
        manv = ma_nv;
    }

    private void init() throws SQLException {
        busSP = new bussanpham();
        cards = new ArrayList<>();
        setLayout(new MigLayout("wrap,fill,insets 7 15 7 15", "[fill]", "[grow 0][fill]"));
        add(createHeaderAction());
        add(createExample());
       
    }

    @Override
    public void formInit() {
        busSP = new bussanpham();
        panelCard.removeAll();

        list_Sp = busSP.list();
        list_SP_has_money.clear();
        for (dtosanpham sp : list_Sp) {
            try {
                list_PN = busSP.listPN(sp.getMaNCC()); 
                
                boolean checkTwice = false;
                for (dtophieunhap pn : list_PN) {
                    Integer maPN = pn.getMaPhieuNhap();
                    list_CTPN = busSP.listCTPN(maPN);

                    boolean isCardAdded = false; 

                    for (dtoctphieunhap ctpn : list_CTPN) {
                        if (ctpn.getMaSanPham() == sp.getMaSanPham()) {
                            if(ctpn.getSoluongtonkho() != 0){
                                sp.setGiaBan(ctpn.getGiaBan());
                                sp.setSoLuong(ctpn.getSoluongtonkho());
                                list_SP_has_money.add(sp);
                                MenuCard card = new MenuCard(sp, createEventCard());
                                cards.add(card);
                                panelCard.add(card);
                                isCardAdded = true;
                                break;
                            }
                            
                        }
                    }

                    if (isCardAdded) {
                        checkTwice = true;
                        break; 
                    }
                }
//                if(!checkTwice){
//                    MenuCard card = new MenuCard(sp, createEventCard());
//                    cards.add(card);
//                    panelCard.add(card);
//                }
            } catch (SQLException ex) {
                Logger.getLogger(formmenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        panelCard.repaint();
        panelCard.revalidate();
    }


    
    private Consumer<dtosanpham> createEventCard() {
        return e -> {
            try {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int dialogWidth = (int) (screenSize.width * 0.7);
                int dialogHeight = (int) (screenSize.height * 0.7);
                
                JDialog showDialog = new JDialog();
                showDialog.setSize(dialogWidth, dialogHeight);
                showDialog.setUndecorated(true);
                showDialog.setShape(new RoundRectangle2D.Double(0, 0, dialogWidth, dialogHeight, 30, 30));
                showDialog.setLayout(new BorderLayout());
                showDialog.setModal(true);
                
                
                Color c1 = new Color(153,204,255);
                JPanel titleBar = new JPanel(new BorderLayout());
                titleBar.setBackground(c1);
                JLabel titleLabel = new JLabel("Thông tin sản phẩm", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
                titleLabel.setPreferredSize(new Dimension(dialogWidth, 50));
                
                JButton closeButton = new JButton("X");
                closeButton.setFocusPainted(false);
                closeButton.setBorderPainted(false);
                closeButton.setBackground(Color.RED);
                closeButton.setForeground(Color.WHITE);
                closeButton.setPreferredSize(new Dimension(45, 30));
                closeButton.addActionListener(event -> showDialog.dispose());
                
                titleBar.add(titleLabel, BorderLayout.WEST);
                titleBar.add(closeButton, BorderLayout.EAST);
                
                // Tạo panel chính có hai cột
                JPanel mainPanel = new JPanel(new GridBagLayout());
                mainPanel.setBackground(Color.WHITE);
                
                // Panel trái chứa các trường thông tin
                leftPanel = new JPanel(new GridBagLayout());
                leftPanel.setBackground(Color.WHITE);
                GridBagConstraints leftGbc = new GridBagConstraints();
                leftGbc.insets = new Insets(5, 5, 5, 5);
                leftGbc.fill = GridBagConstraints.HORIZONTAL;
                
                
                String tenpl = "";
                ArrayList<dtophanloai> listpl = new ArrayList<>();
                listpl = busSP.listPhanloai();
                for(dtophanloai pl : listpl){
                    if(pl.getMaPhanLoai() == e.getMaPhanLoai()){
                        tenpl = pl.getTenPhanLoai();
                    }
                }
                
                String tenncc = "";
                ArrayList<dtonhacungcap> listncc = new ArrayList<>();
                listncc = busSP.listNCC();
                for(dtonhacungcap ncc : listncc){
                    if(ncc.getMaNhaCungCap()== e.getMaNCC()){
                        tenncc = ncc.getTenNhaCungCap();
                    }
                }
                
                
                addField(leftPanel, leftGbc, "Tên sản phẩm:", 1, String.valueOf(e.getTenSanPham()));
                addField(leftPanel, leftGbc, "Giá bán:", 2, String.valueOf(e.getGiaBan()));
                addField(leftPanel, leftGbc, "Tồn kho :", 3, String.valueOf(e.getSoLuong()));
                addField(leftPanel, leftGbc, "Ngày thêm:", 4, String.valueOf(e.getNgayThem()));
                addField(leftPanel, leftGbc, "Phân loại", 5, tenpl);
                addField(leftPanel, leftGbc, "Nhà cung cấp", 7, tenncc);
                
                // Panel phải chứa ảnh và nút chọn ảnh
                rightPanel = new JPanel(new GridBagLayout());
                rightPanel.setBackground(Color.WHITE);
                GridBagConstraints rightGbc = new GridBagConstraints();
                rightGbc.insets = new Insets(4, 4, 4, 4);
                
                // Thêm trường ảnh vào cột phải
                addImageField(rightPanel, rightGbc, e.getImg(), showDialog);
                
                // Đặt leftPanel và rightPanel vào mainPanel
                GridBagConstraints mainGbc = new GridBagConstraints();
                mainGbc.insets = new Insets(10, 10, 10, 10);
                mainGbc.fill = GridBagConstraints.BOTH;
                mainGbc.gridx = 0;
                mainGbc.gridy = 0;
                mainGbc.weightx = 0.6;
                mainPanel.add(leftPanel, mainGbc);
                
                mainGbc.gridx = 1;
                mainGbc.weightx = 0.4;
                mainPanel.add(rightPanel, mainGbc);
                
                // Thêm các thành phần vào JDialog
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.setBackground(Color.WHITE);
                JButton saveButton = new JButton("Thêm giỏ hàng");
                saveButton.setPreferredSize(new Dimension(150, 30));
                saveButton.setBackground(Color.GREEN);
                saveButton.setForeground(Color.BLACK);
                saveButton.addActionListener( ev ->{
                    dtodonhang dh = new dtodonhang();
                    dh.setMa(e.getMaSanPham());
                    dh.setTen(e.getTenSanPham());
//                    dh.setTt(e.getGiaBan());
                    dh.setTt(e.getGiaBan());
                    dh.setSl(Integer.parseInt(quantityField.getText()));
                    addListGioHang(dh,e.getSoLuong());
                    showDialog.setVisible(false);
                });
                
                
                buttonPanel.add(saveButton);
                showDialog.add(titleBar, BorderLayout.NORTH);
                showDialog.add(mainPanel, BorderLayout.CENTER);
                showDialog.add(buttonPanel, BorderLayout.SOUTH);
                showDialog.setLocationRelativeTo(null);
                showDialog.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(formmenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        };   
    }

    public void addListGioHang(dtodonhang dh, Integer sl){
        if(sl == 0){
            JOptionPane.showMessageDialog(null, "Sản phẩm này đã hết hàng");
            return;
        }
        
        if(sl < dh.getSl()){
            JOptionPane.showMessageDialog(null, "Số lượng muốn thêm đã vượt quá số lượng trong kho");
            return;
        }
        for(dtodonhang dh1 : list_donhang){
            if(dh1.getMa() == dh.getMa()){
                if(sl < dh1.getSl() + dh.getSl()){
                    JOptionPane.showMessageDialog(null, "Số lượng hiện có trong giỏ hàng và số lượng muốn thêm đã vượt quá số lượng trong kho");
                    return;
                }
                dh1.setSl(dh1.getSl() + dh.getSl());
                JOptionPane.showMessageDialog(null, "Thêm vào giỏ hàng thành công");

                return;
            }
        }
        list_donhang.add(dh);
        JOptionPane.showMessageDialog(null, "Thêm vào giỏ hàng thành công");

    }
    

    private void addImageField(JPanel panel, GridBagConstraints gbc, String imgPath, JDialog showDialog) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel imgLabel = new JLabel("IMG:");
        imgLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        imgLabel.setPreferredSize(new Dimension(250, 20));
        panel.add(imgLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setPreferredSize(new Dimension(170, 210));
        if (!imgPath.isEmpty()) {
            ImageIcon curImg = new ImageIcon(System.getProperty("user.dir") + "/src/source/image/sanpham/" + imgPath);
            Image scaledImg = curImg.getImage().getScaledInstance(170, 230, Image.SCALE_SMOOTH);
            ImageIcon editImg = new ImageIcon(scaledImg);
            imageDisplayLabel.setIcon(editImg);
        }
        panel.add(imageDisplayLabel, gbc);

        // Thêm trường số lượng dưới ảnh
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        JLabel quantityLabel = new JLabel("Số lượng:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityLabel.setPreferredSize(new Dimension(150, 20));
        panel.add(quantityLabel, gbc);

        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel quantityPanel = new JPanel(new BorderLayout());
        quantityPanel.setPreferredSize(new Dimension(150, 30));

        quantityField = new JTextField("1");
        quantityField.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityField.setHorizontalAlignment(JTextField.CENTER);

        JButton decreaseButton = new JButton("-");
        decreaseButton.setPreferredSize(new Dimension(45, 30));
        decreaseButton.setFont(new Font("Arial", Font.BOLD, 14));

        JButton increaseButton = new JButton("+");
        increaseButton.setPreferredSize(new Dimension(45, 30));
        increaseButton.setFont(new Font("Arial", Font.BOLD, 14));

        decreaseButton.addActionListener(e -> {
            int currentQuantity = Integer.parseInt(quantityField.getText());
            if (currentQuantity > 1) {
                quantityField.setText(String.valueOf(currentQuantity - 1));
            }
        });

        increaseButton.addActionListener(e -> {
            int currentQuantity = Integer.parseInt(quantityField.getText());
            quantityField.setText(String.valueOf(currentQuantity + 1));
        });

        quantityPanel.add(decreaseButton, BorderLayout.WEST);
        quantityPanel.add(quantityField, BorderLayout.CENTER);
        quantityPanel.add(increaseButton, BorderLayout.EAST);

        panel.add(quantityPanel, gbc);

        // Tạo JLabel cho thông báo lỗi và thêm vào dưới quantityField
        gbc.gridy = 4;
        JLabel errorLabel = new JLabel("Vui lòng chỉ nhập số và không được để trống");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        errorLabel.setVisible(false);  // Ban đầu ẩn label này
        panel.add(errorLabel, gbc);

        // Thêm KeyListener cho quantityField
        quantityField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = quantityField.getText();

                // Kiểm tra nếu chuỗi rỗng hoặc chứa ký tự không phải số
                if (text.isEmpty() || !text.matches("\\d+")) {  // \\d+ yêu cầu ít nhất một ký tự số
                    errorLabel.setVisible(true);  // Hiện thông báo lỗi nếu chuỗi trống hoặc chứa ký tự không phải số
                } else {
                    errorLabel.setVisible(false);  // Ẩn thông báo nếu chuỗi hợp lệ
                }
            }
        });
    }




    
    
    private void addField(JPanel panel, GridBagConstraints gbc, String label, int row, String value) throws SQLException {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        jLabel.setPreferredSize(new Dimension(150, 40));
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setPreferredSize(new Dimension(150, 40));
        textField.setText(value);
        textField.setBorder(new RoundedBorder(10));
        textField.setEditable(false);
        panel.add(textField, gbc);
    }

    
    
    
    
    
    
    private Component createHeaderAction() throws SQLException {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230][fill,100][fill,100][fill,100]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm tên sản phẩm ...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/search.png")), 20, 20));
        
        
        JButton btnSearch = new JButton("Tìm kiếm");
        
        JComboBox comboMaPL = new JComboBox();
        comboMaPL.addItem("Mặc định");
        ArrayList<dtophanloai> list_pl = new ArrayList<>();
        list_pl = busSP.listPhanloai();
        for(dtophanloai pl : list_pl){
            comboMaPL.addItem(pl.getTenPhanLoai());
        }
        JButton btnReset = new JButton("Reset");
        
        
        btnSearch.addActionListener(e -> {
            panelCard.removeAll(); // Xóa các card cũ khỏi panelCard
            String searchText = txtSearch.getText().toLowerCase().trim(); // Lấy chuỗi tìm kiếm và loại bỏ khoảng trắng thừa
            String tenmpl = (String) comboMaPL.getSelectedItem();
            boolean found = false;

            list_Sp = busSP.list();
            if(!tenmpl.equals("Mặc định")){
                ArrayList<dtosanpham> list_sp_tmp = new ArrayList<>();
                for(dtosanpham sp : list_SP_has_money){
                    if(sp.getMaPhanLoai()== busSP.getMaPL(tenmpl)){
                        list_sp_tmp.add(sp);
                    }
                }
                if(searchText.equals("")){
                    for (dtosanpham sp : list_sp_tmp) {
                        MenuCard card = new MenuCard(sp, createEventCard());
                        cards.add(card);
                        panelCard.add(card);
                        found = true;
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm tương ứng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                    panelCard.repaint();
                    panelCard.revalidate();
                }else{
                    for (dtosanpham sp : list_sp_tmp) {
                        String tenSanPham = sp.getTenSanPham().toLowerCase();
                        if (tenSanPham.contains(searchText)) {
                            MenuCard card = new MenuCard(sp, createEventCard());
                            cards.add(card);
                            panelCard.add(card);
                            found = true;

                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm tương ứng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                    panelCard.repaint();
                    panelCard.revalidate();
                }
                
            }else{
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa để tìm kiếm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }


                for (dtosanpham sp : list_SP_has_money) {
                    String tenSanPham = sp.getTenSanPham().toLowerCase();
                    if (tenSanPham.contains(searchText)) {
                        MenuCard card = new MenuCard(sp, createEventCard());
                        cards.add(card);
                        panelCard.add(card);
                        found = true;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm tương ứng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                panelCard.repaint();
                panelCard.revalidate();
            }
        });

        txtSearch.addActionListener(e -> {
            btnSearch.doClick(); // 
        });

        
        btnReset.addActionListener( e -> {
            txtSearch.setText("");
            comboMaPL.setSelectedIndex(0);
//            list_donhang.clear();
            formInit();
        });
        
        JCheckBox selectAllCheckbox = new JCheckBox("Chọn tất cả");
        selectAllCheckbox.addActionListener(e -> selectAll(selectAllCheckbox.isSelected()));
        JButton cmdAddCart = new JButton("Thêm vào giỏ hàng");

        JButton cmdViewCart = new JButton("Xem giỏ hàng");
        cmdViewCart.addActionListener(e -> {
            viewCart();           
        });
        cmdAddCart.addActionListener(e -> {               
                if (selectAllCheckbox.isSelected()) {
                selectAllCheckbox.setSelected(false);
            }
                addCart();
        });
        
        panel.add(txtSearch);
        panel.add(comboMaPL);
        panel.add(btnSearch);
        panel.add(btnReset);
        panel.add(selectAllCheckbox);
        panel.add(cmdAddCart);
  
        panel.add(cmdViewCart);

        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;");
        return panel;
    }

     private void selectAll(boolean selected) {
        for (MenuCard card : cards) {
            card.setSelected(selected);
        }
    }
     
    private void addCart(){
        List<MenuCard> selectedCards = new ArrayList<>();
        StringBuilder addedNames = new StringBuilder("Đã thêm các sản phẩm: ");
        for (MenuCard card : cards) {
            if (card.isSelected()) { 
                selectedCards.add(card);
            }
        }

        if (selectedCards.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào được chọn để thêm vào giỏ hàng.");
            return;
        }

        for (MenuCard card : selectedCards) {
            addedNames.append(card.getSanPhamName()).append(", ");
            dtodonhang dh = new dtodonhang();
            dh.setMa(card.getMaSanPham());
            dh.setTen(card.getSanPhamName());
            dh.setTt(card.getGiaTien());
            dh.setSl(1);
            Integer soluongkho = card.getSoLuong();
            
            addListGioHang(dh, soluongkho);
            
            card.setSelected(false);
        }
        panelCard.revalidate();
        panelCard.repaint();
        
        
        JOptionPane.showMessageDialog(this, "Đã thêm " + selectedCards.size() + " sản phẩm vào giỏ hàng.");
        if (addedNames.length() > 0) {
            addedNames.setLength(addedNames.length() - 2); // Xóa dấu phẩy và khoảng trắng cuối cùng
        }
        JOptionPane.showMessageDialog(this, addedNames.toString());
    }  
    // Khai báo biến thành viên cho dialog



    
    public Double calculateTotal(){
        Double tt = 0.0;
        for(int i = 0; i < list_donhang.size() ; i++ ){
            dtodonhang dh = list_donhang.get(i);
            tt += dh.getSl()* dh.getTt();
        }
        return tt;
    }
    

    private void viewCart() {
            giohangDialog = new JDialog();
            giohangDialog.setModal(true);
            giohangDialog.setUndecorated(true);  // Ẩn tiêu đề của dialog
            giohangDialog.setLayout(new BorderLayout());
            giohangDialog.setUndecorated(true);
            giohangDialog.setShape(new RoundRectangle2D.Double(0, 0, 800, 600, 30, 30));

            JPanel titlePanel = new JPanel(new BorderLayout());
            Color c2 = new Color(102,178,255);
            titlePanel.setBackground(c2);
            titlePanel.setPreferredSize(new Dimension(800, 40));  // Đặt chiều cao lớn hơn
            
            
            JLabel titleLabel = new JLabel("Giỏ hàng hiện tại", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));  // Chữ in đậm và căn giữa
            
            
            JButton closeButton = new JButton("X");
            closeButton.setFont(new Font("Arial", Font.BOLD, 18));  // Font chữ to hơn
            closeButton.setForeground(Color.white);  // Màu chữ đỏ
            closeButton.setBorderPainted(false);
            closeButton.setBackground(Color.red);

            closeButton.addActionListener(e -> giohangDialog.dispose());

            // Thêm tiêu đề và nút đóng vào titlePanel
            titlePanel.add(titleLabel, BorderLayout.CENTER);
            titlePanel.add(closeButton, BorderLayout.EAST);

            giohangDialog.add(titlePanel, BorderLayout.NORTH);

            String[] columnNames = {"Mã sản phẩm","Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
            
            Object [][] rowData1 = new Object[list_donhang.size()][columnNames.length];
            
            Double tongtien = 0.0;
            for(int i = 0; i < list_donhang.size() ; i++ ){
                dtodonhang dh = list_donhang.get(i);
                rowData1[i][0] = dh.getMa();
                rowData1[i][1] = dh.getTen();
                rowData1[i][2] = dh.getSl();
                rowData1[i][3] = dh.getTt();
                rowData1[i][4] = dh.getSl() * dh.getTt();
                tongtien += dh.getSl()* dh.getTt();
            }
            
//          
            model = new DefaultTableModel(rowData1 , columnNames){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Disables editing
                }
            };
            JTable cartTable = new JTable();
            cartTable.setModel(model);
            cartTable.setAutoCreateRowSorter(true);
            cartTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            cartTable.setFillsViewportHeight(true);
            cartTable.getTableHeader().setReorderingAllowed(false);


//            cartTable.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    int row = cartTable.getSelectedRow();
//                }
//            });

            
            TableColumnModel columnModel = cartTable.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);  // Mã sản phẩm
            columnModel.getColumn(1).setPreferredWidth(270);  // Tên sản phẩm
            columnModel.getColumn(2).setPreferredWidth(40);   // Số lượng
            columnModel.getColumn(3).setPreferredWidth(100);  // Đơn giá
            columnModel.getColumn(4).setPreferredWidth(120);  // Thành tiền

            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            for (int i = 0; i < cartTable.getColumnCount(); i++) {
                cartTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            
            JScrollPane scrollPane = new JScrollPane(cartTable);
            giohangDialog.add(scrollPane, BorderLayout.CENTER);

            // Tạo JPanel dưới cùng chứa tổng tiền và các nút, đặt kích thước lớn hơn
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            bottomPanel.setPreferredSize(new Dimension(800, 40));  // Chiều cao lớn hơn
            
            JLabel totalLabel = new JLabel("Tổng tiền: "+ tongtien +" VND");
            totalLabel.setFont(new Font("Arial", Font.BOLD, 14));  // In đậm và kích cỡ chữ lớn hơn
            totalLabel.setForeground(Color.red);
            
            JButton btnDelete = new JButton("Xóa");
            btnDelete.addActionListener(e -> {
                int row = cartTable.getSelectedRow();  // Lấy hàng được chọn
                if(row == -1){
                    JOptionPane.showMessageDialog(scrollPane, "Chưa chọn sản phẩm cần xóa");
                } else {
                    model = (DefaultTableModel) cartTable.getModel();
                    model.removeRow(row);

                    list_donhang.remove(row);
                    totalLabel.setText("Tổng tiền: " + calculateTotal() + " VND");
                }
            });

            
            
            JButton btnDeleteAll = new JButton("Xóa toàn bộ");
            btnDeleteAll.addActionListener(e -> {
                // Xóa tất cả sản phẩm trong danh sách
                list_donhang.clear();

                // Cập nhật dữ liệu trong bảng
                Object[][] emptyData = {{"","", "Chưa có sản phẩm nào", "", ""}};
                model = new DefaultTableModel(emptyData, columnNames);
                cartTable.setModel(model);

                // Cập nhật lại label tổng tiền
                totalLabel.setText("Tổng tiền: " + calculateTotal() + " VND");
            });

            
            
            
            JButton btnCheckout = new JButton("Thanh toán");
            btnCheckout.addActionListener(e -> {
                if(!list_donhang.isEmpty()){
                    ArrayList<dtocthoadon> list = new ArrayList<>();
                    for(dtodonhang i : list_donhang){
                        dtocthoadon a = new dtocthoadon();
                        a.setMaSanPham(i.getMa());
                        a.setSoLuong(i.getSl());
                        a.setTensanpham(i.getMa());
                        list.add(a);
                    }
                    giohangDialog.dispose();
                    formthanhtoan formth =  new formthanhtoan(list,manv);
                    formth.setSize(510, 750);
                    formth.setResizable(false);
                    formth.setLocationRelativeTo(null);
                   list_donhang.clear();
                    formth.setVisible(true);
                    formth.toFront();
                    
                }
                else{
                    JOptionPane.showMessageDialog(this, "Giỏ hàng rỗng không thể thanh toán");
                    return;
                }
            });

            // Thêm các thành phần vào bottomPanel
            bottomPanel.add(totalLabel);
            bottomPanel.add(btnDelete);
            bottomPanel.add(btnDeleteAll);
            bottomPanel.add(btnCheckout);

            giohangDialog.add(bottomPanel, BorderLayout.SOUTH);

            giohangDialog.setSize(800, 600);
            giohangDialog.setLocationRelativeTo(this);
            giohangDialog.setVisible(true);
    }





    private Component createExample() {
        responsiveLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, new Dimension(-1, -1), 10, 10);
        panelCard = new JPanel(responsiveLayout);
        panelCard.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:10,10,10,10;");
        JScrollPane scrollPane = new JScrollPane(panelCard);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getHorizontalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:$ScrollBar.thumbArc;" +
                "thumbInsets:0,0,0,0;" +
                "width:7;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:$ScrollBar.thumbArc;" +
                "thumbInsets:0,0,0,0;" +
                "width:7;");
        scrollPane.setBorder(null);
        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(Box.createGlue());
        splitPane.setResizeWeight(1);
         splitPane.setDividerSize(0);
        return splitPane;
    }
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
    Image img = icon.getImage();
    Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
    }
  
    
       
}
