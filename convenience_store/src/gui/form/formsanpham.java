package gui.form;

import bus.busnhanvien;
import bus.bussanpham;
import gui.comp.NVCard;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.toedter.calendar.JDateChooser;
import dto.dtochucvu;
import dto.dtoctphieunhap;
import dto.dtonhacungcap;
import net.miginfocom.swing.MigLayout;
import gui.layout.ResponsiveLayout;
import dto.dtonhanvien;
import dto.dtophanloai;
import dto.dtophieunhap;
import dto.dtosanpham;
import gui.comp.MenuCard;
import gui.comp.RoundedBorder;
import gui.comp.SPCard;
import gui.simple.SimpleInputForms;
import gui.simple.SimpleInputFormsSanPham;
import gui.swing.dashboard.Form;
import gui.swing.dashboard.SystemForm;
import helper.AvatarIcon;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.Clock.system;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;


@SystemForm(name = "Responsive Layout", description = "responsive layout user interface", tags = {"card"})
public class formsanpham extends Form {
    
    
    private bussanpham busSP = new bussanpham();
    private ArrayList<dtosanpham> list_SP;
    
    private List<SPCard> cards;
    private JPanel panelCard;
    private ResponsiveLayout responsiveLayout;
    private File selectedFile;
    private JComboBox<String> comboxBoxNCC = new JComboBox<>();
    private Map<String, Integer> categoryMaNCC;
    private Map<String, Integer> categoryMap;
    private ArrayList<dtonhacungcap> listNCC;
    private Map<String, JTextField> textFieldMap = new HashMap<>();
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JComboBox<String> comboBox;
    private JLabel imageDisplayLabel;
    private String tenspUpdate;
    private ArrayList<dtophieunhap> list_PN;
    private ArrayList<dtoctphieunhap> list_CTPN;
    private JTextField[] textField = new JTextField[20];
    public formsanpham() throws SQLException {
        init();
        formInit();
        
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Create a new JFrame
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Add the formsanpham panel to the frame
                formsanpham panel = null;
                try {
                    panel = new formsanpham();
                } catch (SQLException ex) {
                    Logger.getLogger(formsanpham.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.add(panel);

                // Set frame size and make it visible
                frame.setSize(1070, 741); // Adjust size as needed
                frame.setLocationRelativeTo(null); // Center the frame
                frame.setVisible(true);
            }
        });
    }
    private void init() throws SQLException {
        cards = new ArrayList<>();
        setLayout(new MigLayout("wrap,fill,insets 7 15 7 15", "[fill]", "[grow 0][fill]"));
        add(createHeaderAction());
        add(createExample());
       
    }

    
    // Đây là chỗ để tạo các card nhân viên
    @Override
    public void formInit() {
        busSP = new bussanpham();
        panelCard.removeAll();

        try {
            list_SP = busSP.list(); // Fetch list of products
        } catch (SQLException ex) {
            Logger.getLogger(formmenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (dtosanpham sp : list_SP) {
            try {
                list_PN = busSP.listPN(sp.getMaNCC()); // Get list of purchase orders for each product's supplier
                
                boolean checkTwice = false;
                for (dtophieunhap pn : list_PN) {
                    Integer maPN = pn.getMaPhieuNhap();
                    list_CTPN = busSP.listCTPN(maPN); // Get details of each purchase order

                    boolean isCardAdded = false; 

                    for (dtoctphieunhap ctpn : list_CTPN) {
                        if (ctpn.getMaSanPham() == sp.getMaSanPham()) {
                            if(ctpn.getSoluongtonkho() != 0){
                                sp.setGiaBan(ctpn.getGiaBan());
                                sp.setSoLuong(ctpn.getSoluongtonkho());
//                                System.out.println("Giá bán : " + sp.getGiaBan());
                                SPCard card = new SPCard(sp, createEventCard1());
                                cards.add(card);
                                panelCard.add(card);
                                isCardAdded = true; // Card is added
                                break;
                            }
                            
                        }
                    }

                    if (isCardAdded) {
                        checkTwice = true;
                        break; // Exit purchase order loop if card has been added
                    }
                }
                if(!checkTwice){
                    SPCard card = new SPCard(sp, createEventCard1());
                    cards.add(card);
                    panelCard.add(card);
                }
            } catch (SQLException ex) {
                Logger.getLogger(formmenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        panelCard.repaint();
        panelCard.revalidate();
    }
    
    
    
    private Consumer<dtosanpham> createEventCard1() {
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
                
                JPanel titleBar = new JPanel(new BorderLayout());
                titleBar.setBackground(Color.ORANGE);
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
                
                // Thêm các trường thông tin vào cột trái
                addField(leftPanel, leftGbc, "Mã sản phẩm:", 0, String.valueOf(e.getMaSanPham()));
                addField(leftPanel, leftGbc, "Tên sản phẩm:", 1, String.valueOf(e.getTenSanPham()));
                addField(leftPanel, leftGbc, "Giá bán:", 2, String.valueOf(e.getGiaBan()));
                addField(leftPanel, leftGbc, "Số lượng:", 3, String.valueOf(e.getSoLuong()));
                addField(leftPanel, leftGbc, "Ngày thêm:", 4, String.valueOf(e.getNgayThem()));
                addField(leftPanel, leftGbc, "Mã phân loại", 5, String.valueOf(e.getMaPhanLoai()));
                addField(leftPanel, leftGbc, "Giá nhập", 6, String.valueOf(e.getGiaBan()));
                addField(leftPanel, leftGbc, "Nhà cung cấp", 7, String.valueOf(e.getMaNCC()));
                addField(leftPanel, leftGbc, "Hạn sử dụng", 8, String.valueOf(e.getHanSD()));
                
                // Panel phải chứa ảnh và nút chọn ảnh
                rightPanel = new JPanel(new GridBagLayout());
                rightPanel.setBackground(Color.WHITE);
                GridBagConstraints rightGbc = new GridBagConstraints();
                rightGbc.insets = new Insets(5, 5, 5, 5);
                
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
                JButton saveButton = new JButton("Lưu");
                saveButton.setPreferredSize(new Dimension(100, 30));
                saveButton.setBackground(Color.GREEN);
                saveButton.setForeground(Color.WHITE);
                saveButton.addActionListener(event -> {
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc lưu thay đổi không?",
                            "Cập nhật sản phẩm",
                            JOptionPane.YES_NO_OPTION
                    );
                    
                    if (result == JOptionPane.YES_OPTION) {
                        try {
                            busSP = new bussanpham();
                            dtosanpham sp = new dtosanpham();
//                            String tenSanPham = ((JTextField) leftPanel.getComponent(1)).getText();
                            String tenSanPham = textField[1].getText();
                            Integer maNCC = (Integer) categoryMaNCC.get(comboxBoxNCC.getSelectedItem());
                            Integer maPhanLoai = busSP.getMaPL((String) comboBox.getSelectedItem());
                            String imgPath = selectedFile != null ? selectedFile.getPath() : e.getImg();
                            String imgName = selectedFile != null ? selectedFile.getName() : e.getImg();
                            sp.setTenSanPham(tenSanPham);
                            sp.setMaNCC(maNCC);
                            sp.setMaPhanLoai((maPhanLoai));
                            sp.setImg(imgName);
                            sp.setMaSanPham(e.getMaSanPham());

                            System.out.println("Cap nhat san pham");
                            System.out.println("ma phan loai : " + maPhanLoai);
                            System.out.println("ma nha cung cap : " + maNCC);
                            System.out.println("duong dan img : " + imgPath);
                            System.out.println("---------------------------------------------------");
                            
                            
                            saveProductChanges(sp);
                            if(selectedFile != null) {
                                String root_dir = System.getProperty("user.dir") +  "/src/source/image/sanpham/" ;
                                saveImageToDirectory(root_dir);
                                formInit();
                            }
                            JOptionPane.showMessageDialog(null, "Lưu thay đổi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            showDialog.dispose();
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(formsanpham.class.getName()).log(Level.SEVERE, null, ex);
                        }
//                        formInit();
                    }
                });
                
                buttonPanel.add(saveButton);
                
                showDialog.add(titleBar, BorderLayout.NORTH);
                showDialog.add(mainPanel, BorderLayout.CENTER);
                showDialog.add(buttonPanel, BorderLayout.SOUTH);
                showDialog.setLocationRelativeTo(null);
                showDialog.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(formsanpham.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
    }

    private void saveProductChanges(dtosanpham sp) throws SQLException{
        busSP.updateSanPham(sp);
    }
    
    private void addImageField(JPanel panel, GridBagConstraints gbc, String imgPath, JDialog showDialog) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel imgLabel = new JLabel("IMG:");
        imgLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        imgLabel.setPreferredSize(new Dimension(150, 20));
        panel.add(imgLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setPreferredSize(new Dimension(170, 210));
        if(!imgPath.isEmpty()){
            ImageIcon curImg = new ImageIcon(getClass().getResource("/source/image/sanpham/" + imgPath));
            Image scaledImg = curImg.getImage().getScaledInstance(170, 230, Image.SCALE_SMOOTH);
            ImageIcon editImg = new ImageIcon(scaledImg);
            imageDisplayLabel.setIcon(editImg);

        }
        panel.add(imageDisplayLabel, gbc);

        gbc.gridy = 2;
        JButton fileChooserButton = new JButton("Chọn ảnh");
        fileChooserButton.setPreferredSize(new Dimension(100, 30));
        fileChooserButton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setDialogTitle("Chọn ảnh");

            int result = fileChooser.showOpenDialog(showDialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                updateImageLabel(imageDisplayLabel, selectedFile.getPath());
            }
        });
        panel.add(fileChooserButton, gbc);
    }

    private void updateImageLabel(JLabel label, String imgPath) {
        if (imgPath != null && !imgPath.isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(170, 
                    230, Image.SCALE_SMOOTH));
            label.setIcon(imageIcon);
        } else {
            label.setIcon(null);
        }
    }
    
    
    private void saveImageToDirectory(String destinationDir) {
        try {
            File destinationDirFile = new File(destinationDir);

            if (!destinationDirFile.exists()) {
                destinationDirFile.mkdirs(); // Tạo thư mục nếu không tồn tại
            }

            Path destinationPath = Paths.get(destinationDir, selectedFile.getName());

            Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image saved to: " + destinationPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    // Phương thức thêm cặp Label và TextField vào panel
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
        
        
       if (label.equals("Nhà cung cấp")) {
            comboxBoxNCC.setFont(new Font("Arial", Font.PLAIN, 16));
            comboxBoxNCC.setPreferredSize(new Dimension(150, 40));
            comboxBoxNCC.setBorder(new RoundedBorder(10));
            categoryMaNCC = new HashMap<>();

            try {
                listNCC = busSP.listNCC();
                for (int i = 0; i < listNCC.size(); i++) {
                    dtonhacungcap ncc = listNCC.get(i);
                    String tenNCC = ncc.getTenNhaCungCap();
                    Integer maNCC = ncc.getMaNhaCungCap();

                    categoryMaNCC.put(tenNCC, maNCC);
                    comboxBoxNCC.addItem(tenNCC);
                }

                Integer valueAsInteger = Integer.parseInt(value);
                for (Map.Entry<String, Integer> entry : categoryMaNCC.entrySet()) {
                    if (entry.getValue().equals(valueAsInteger)) {
                        comboxBoxNCC.setSelectedItem(entry.getKey()); // Set comboBox selection by nhà cung cấp name
                        break;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SimpleInputFormsSanPham.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value format: " + value);
            }
            
            
            
            panel.add(comboxBoxNCC, gbc);
        } else if (label.equals("Mã phân loại")) {
            comboBox = new JComboBox<>();
            comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
            comboBox.setPreferredSize(new Dimension(150, 40));
            comboBox.setBorder(new RoundedBorder(10));

            busSP = new bussanpham();
            ArrayList<dtophanloai> listpl = busSP.listPhanloai();
            for(dtophanloai pl : listpl){
                comboBox.addItem(pl.getTenPhanLoai());
                if(String.valueOf(pl.getMaPhanLoai()).equals(value)){
                    comboBox.setSelectedItem(pl.getTenPhanLoai());
                }
            }
            
            
            panel.add(comboBox, gbc);
        } else {
            textField[row] = new JTextField(20);
            textField[row].setFont(new Font("Arial", Font.PLAIN, 16));
            textField[row].setPreferredSize(new Dimension(150, 40));
            textField[row].setText(value);
            textField[row].setBorder(new RoundedBorder(10));
            textField[row].setEditable(false);
            if(row == 1){
                textField[row].setEditable(true);
            }
//            tenspUpdate = textField[row].getText();
//            textField[row].addKeyListener(new KeyAdapter() {
//                @Override
//                public void keyReleased(KeyEvent e) {
//                    tenspUpdate = textField[row].getText();
//                }
//            });
            panel.add(textField[row], gbc);
        }
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
        
        
        
        JCheckBox selectAllCheckbox = new JCheckBox("Chọn tất cả");
        selectAllCheckbox.addActionListener(e -> selectAll(selectAllCheckbox.isSelected()));
        JButton cmdCreate = new JButton("Thêm");

        JButton cmdDelete = new JButton("Xoá");
        
        
        
        
        
        btnSearch.addActionListener(e -> {
            panelCard.removeAll(); // Xóa các card cũ khỏi panelCard
            String searchText = txtSearch.getText().toLowerCase().trim(); // Lấy chuỗi tìm kiếm và loại bỏ khoảng trắng thừa
            String tenmpl = (String) comboMaPL.getSelectedItem();
            try {
                list_SP = busSP.list();
            } catch (SQLException ex) {
                Logger.getLogger(formnhanvien.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!tenmpl.equals("Mặc định")){
                ArrayList<dtosanpham> list_sp_tmp = new ArrayList<>();
                for(dtosanpham sp : list_SP){
                    if(sp.getMaPhanLoai()== busSP.getMaPL(tenmpl)){
                        list_sp_tmp.add(sp);
                    }
                }
                if(searchText.equals("")){
                    for (dtosanpham sp : list_sp_tmp) {
                        SPCard card = new SPCard(sp, createEventCard1());
                        cards.add(card);
                        panelCard.add(card);
                    }
                    panelCard.repaint();
                    panelCard.revalidate();
                }else{
                    for (dtosanpham sp : list_sp_tmp) {
                        String tenSanPham = sp.getTenSanPham().toLowerCase();
                        if (tenSanPham.contains(searchText)) {
                            SPCard card = new SPCard(sp, createEventCard1());
                            cards.add(card);
                            panelCard.add(card);
                        }
                    }
                    panelCard.repaint();
                    panelCard.revalidate();
                }
                
            }else{
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa để tìm kiếm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                boolean found = false; 

                for (dtosanpham sp : list_SP) {
                    String tenSanPham = sp.getTenSanPham().toLowerCase();
                    if (tenSanPham.contains(searchText)) {
                        SPCard card = new SPCard(sp, createEventCard1());
                        cards.add(card);
                        panelCard.add(card);
                        found = true;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên tương ứng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                panelCard.repaint();
                panelCard.revalidate();
            }
        });


        
        btnReset.addActionListener( e -> {
            txtSearch.setText("");
            comboMaPL.setSelectedIndex(0);
            formInit();
        });
        
        
        
        
        
        cmdDelete.addActionListener(e -> {
             if (selectAllCheckbox.isSelected()) {
                selectAllCheckbox.setSelected(false);
            }
            deleteSelectedCards();
                });
        cmdCreate.addActionListener(e -> showModal());
        panel.add(txtSearch);
        panel.add(comboMaPL);
        panel.add(btnSearch);
        panel.add(btnReset);
        panel.add(selectAllCheckbox);
        panel.add(cmdCreate);
        panel.add(cmdDelete);

        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;");
        return panel;
    }
     private void selectAll(boolean selected) {
        for (SPCard card : cards) {
            card.setSelected(selected);
        }
    }
    private void deleteSelectedCards() {         
        List<SPCard> selectedCards = new ArrayList<>();
        StringBuilder deletedNames = new StringBuilder("Đã xóa các sản phẩm: ");
        for (SPCard card : cards) {
            if (card.isSelected()) { 
                selectedCards.add(card);
            }
        }

        if (selectedCards.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào được chọn để xóa.");
            return;
        }
        int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa các sản phẩm vừa chọn không ?", "Xóa sản phẩm", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            for (SPCard card : selectedCards) {
                busSP = new bussanpham();
                deletedNames.append(card.getSanPhamName()).append(", ");
    //            busSP.deleteSanPham(card.getMaSanPham());
                panelCard.remove(card);
                cards.remove(card);
            }
            panelCard.revalidate();
            panelCard.repaint();
            JOptionPane.showMessageDialog(this, "Đã xóa " + selectedCards.size() + " sản phẩm.");
            if (deletedNames.length() > 0) {
                deletedNames.setLength(deletedNames.length() - 2); // Xóa dấu phẩy và khoảng trắng cuối cùng
            }
            JOptionPane.showMessageDialog(this, deletedNames.toString());
        }
    }
    private void showModal() {
        busSP = new bussanpham();
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.TRAILING, Location.TOP)
                .setAnimateDistance(0.7f, 0);

        SimpleInputFormsSanPham form = new SimpleInputFormsSanPham(); // Khởi tạo form bên ngoài
        ModalDialog.showModal(this, new SimpleModalBorder(
                form, "Thêm sản phẩm", SimpleModalBorder.YES_NO_OPTION,
                (controller, action) -> {
                    if (action == SimpleModalBorder.YES_OPTION) {
                        dtosanpham sp;
                        try {
                            sp = form.getSanPham();
                            selectedFile = form.getSelectedFile();
                            
                            if (!check(sp.getImg(), sp.getTenSanPham())) {
                                return; 
                            }
                            
                            
                            
                            if (busSP.addSanPham(sp)) {
                                if(selectedFile != null) {
                                    System.out.println(selectedFile.getName());
                                    String root_dir = System.getProperty("user.dir") +  "/src/source/image/sanpham/" ;
                                    saveImageToDirectory(root_dir);
                                }
                                JOptionPane.showMessageDialog(panelCard, "Thêm sản phẩm thành công");
                                formInit();
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(formsanpham.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm sản phẩm.");
                        }
                    }
                    controller.close();
                }), option);
    }

    public boolean check(String img, String tensp) {
        if (img == null || img.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy chọn ảnh để thêm");
            return false;
        }
        if (tensp == null || tensp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống");
            return false;
        }
        return true; 
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
