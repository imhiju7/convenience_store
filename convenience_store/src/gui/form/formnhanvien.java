package gui.form;

import bus.busnhanvien;
import gui.comp.NVCard;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import gui.layout.ResponsiveLayout;
import gui.model.ModelEmployee;
import dto.SampleData;
import dto.dtonhanvien;
import gui.simple.SimpleInputForms;
import gui.swing.dashboard.Form;
import gui.swing.dashboard.SystemForm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;


@SystemForm(name = "Responsive Layout", description = "responsive layout user interface", tags = {"card"})
public class formnhanvien extends Form {
    
    
    private busnhanvien busNV;
    private ArrayList<dtonhanvien> list_NV;
    
    
    public formnhanvien() {
        init();
        formInit();
        
    }

    private void init() {
        cards = new ArrayList<>();
        setLayout(new MigLayout("wrap,fill,insets 7 15 7 15", "[fill]", "[grow 0][fill]"));
        add(createHeaderAction());
        add(createExample());
       
    }

    
    // Đây là chỗ để tạo các card nhân viên
    @Override
    public void formInit() {
        // add sample data
        panelCard.removeAll();
        for (ModelEmployee employee : SampleData.getSampleEmployeeData(true)) {
            NVCard card = new NVCard(employee, createEventCard());
            cards.add(card);
            panelCard.add(card);
        }
        panelCard.repaint();
        panelCard.revalidate();
        
       
        
//        try {
//            busNV = new busnhanvien();
//            busNV.list();
//            list_NV = busNV.getList();
//            for(int i = 0 ; i < list_NV.size() ; i++){
//                dtonhanvien nv = list_NV.get(i);
//                NVCard card1 = new NVCard(nv, createEventCard1() , 1);
//                cards.add(card1);
//                panelCard.add(card1);
//            }
//            
//            panelCard.repaint();
//            panelCard.revalidate();
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(formnhanvien.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
    }

    
    
    // Đây là chỗ để hiển thị đầy đủ thông tin nhân viên
    private Consumer<ModelEmployee> createEventCard() {
        return e -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int dialogWidth = (int) (screenSize.width * 0.5);
            int dialogHeight = (int) (screenSize.height * 0.5);

            // Tạo JDialog với thiết lập không có khung
            JDialog showDialog = new JDialog();
            showDialog.setSize(dialogWidth, dialogHeight);
            showDialog.setUndecorated(true); // Bỏ khung mặc định của JDialog
            showDialog.setShape(new RoundRectangle2D.Double(0, 0, dialogWidth, dialogHeight, 30, 30)); // Bo góc
            showDialog.setLayout(new BorderLayout());
            showDialog.setModal(true);

            // Tạo thanh tiêu đề tùy chỉnh với nút đóng
            JPanel titleBar = new JPanel();
            titleBar.setLayout(new BorderLayout());
            titleBar.setBackground(Color.LIGHT_GRAY);

            
            JLabel titleLabel = new JLabel("Thông tin nhân viên");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setPreferredSize(new Dimension(dialogWidth , 50));

            JButton closeButton = new JButton("X");
            closeButton.setFocusPainted(false);
            closeButton.setBorderPainted(false);
            closeButton.setBackground(Color.RED);
            closeButton.setForeground(Color.WHITE);
            closeButton.setPreferredSize(new Dimension(45, 30));

            // Sự kiện đóng cửa sổ khi nhấn nút X
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    showDialog.dispose(); // Đóng dialog
                }
            });
            
            titleBar.add(titleLabel, BorderLayout.WEST);
            titleBar.add(closeButton, BorderLayout.EAST);

            
            // Tạo JPanel và sử dụng GridBagLayout cho form
            JPanel nv_panel = new JPanel(new GridBagLayout());
            nv_panel.setBackground(Color.WHITE); // Đặt nền trắng cho panel

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5); // Đặt khoảng cách giữa các thành phần
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Thêm các trường thông tin vào nv_panel
            addField(nv_panel, gbc, "Mã nhân viên:", 0);
            addField(nv_panel, gbc, "Tên nhân viên:", 1);
            addField(nv_panel, gbc, "Giới tính:", 2);
            addField(nv_panel, gbc, "Năm sinh:", 3);
            addField(nv_panel, gbc, "Số điện thoại:", 4);
            addField(nv_panel, gbc, "Địa chỉ:", 5);
            addField(nv_panel, gbc, "Email:", 6);
            addField(nv_panel, gbc, "Lương:", 7);
            addField(nv_panel, gbc, "Mã chức vụ:", 8);

            showDialog.add(titleBar, BorderLayout.NORTH);
            showDialog.add(nv_panel, BorderLayout.CENTER);
            showDialog.setLocationRelativeTo(null); // Hoặc có thể đặt relative tới component chính
            showDialog.setVisible(true);
        };
    }
    
    private Consumer<dtonhanvien> createEventCard1() {
        return e -> {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int dialogWidth = (int) (screenSize.width * 0.5);
            int dialogHeight = (int) (screenSize.height * 0.5);

            // Tạo JDialog với thiết lập không có khung
            JDialog showDialog = new JDialog();
            showDialog.setSize(dialogWidth, dialogHeight);
            showDialog.setUndecorated(true); // Bỏ khung mặc định của JDialog
            showDialog.setShape(new RoundRectangle2D.Double(0, 0, dialogWidth, dialogHeight, 30, 30)); // Bo góc
            showDialog.setLayout(new BorderLayout());
            showDialog.setModal(true);

            // Tạo thanh tiêu đề tùy chỉnh với nút đóng
            JPanel titleBar = new JPanel();
            titleBar.setLayout(new BorderLayout());
            titleBar.setBackground(Color.LIGHT_GRAY);

            
            JLabel titleLabel = new JLabel("Thông tin nhân viên");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setPreferredSize(new Dimension(dialogWidth , 50));

            JButton closeButton = new JButton("X");
            closeButton.setFocusPainted(false);
            closeButton.setBorderPainted(false);
            closeButton.setBackground(Color.RED);
            closeButton.setForeground(Color.WHITE);
            closeButton.setPreferredSize(new Dimension(45, 30));

            // Sự kiện đóng cửa sổ khi nhấn nút X
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    showDialog.dispose(); // Đóng dialog
                }
            });
            
            titleBar.add(titleLabel, BorderLayout.WEST);
            titleBar.add(closeButton, BorderLayout.EAST);

            
            // Tạo JPanel và sử dụng GridBagLayout cho form
            JPanel nv_panel = new JPanel(new GridBagLayout());
            nv_panel.setBackground(Color.WHITE); // Đặt nền trắng cho panel

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5); // Đặt khoảng cách giữa các thành phần
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Thêm các trường thông tin vào nv_panel
            addField(nv_panel, gbc, "Mã nhân viên:", 0);
            addField(nv_panel, gbc, "Tên nhân viên:", 1);
            addField(nv_panel, gbc, "Giới tính:", 2);
            addField(nv_panel, gbc, "Năm sinh:", 3);
            addField(nv_panel, gbc, "Số điện thoại:", 4);
            addField(nv_panel, gbc, "Địa chỉ:", 5);
            addField(nv_panel, gbc, "Email:", 6);
            addField(nv_panel, gbc, "Lương:", 7);
            addField(nv_panel, gbc, "Mã chức vụ:", 8);

            showDialog.add(titleBar, BorderLayout.NORTH);
            showDialog.add(nv_panel, BorderLayout.CENTER);
            showDialog.setLocationRelativeTo(null); // Hoặc có thể đặt relative tới component chính
            showDialog.setVisible(true);
        };
    }

    // Phương thức thêm cặp Label và TextField vào panel
    private void addField(JPanel panel, GridBagConstraints gbc, String label, int row) {
        // Thêm label
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);

        // Thêm text field
        gbc.gridx = 1;
        JTextField textField = new JTextField(20);
        panel.add(textField, gbc);
    }

     private Component createHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm tên nhân viên...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/search.png")), 20, 20));
        JCheckBox selectAllCheckbox = new JCheckBox("Chọn tất cả");
        selectAllCheckbox.addActionListener(e -> selectAll(selectAllCheckbox.isSelected()));
        JButton cmdCreate = new JButton("Thêm");

        JButton cmdDelete = new JButton("Xoá");
        cmdDelete.addActionListener(e -> {
             if (selectAllCheckbox.isSelected()) {
                selectAllCheckbox.setSelected(false);
            }
            deleteSelectedCards();
                });
        cmdCreate.addActionListener(e -> showModal());
        panel.add(txtSearch);
        panel.add(selectAllCheckbox);
        panel.add(cmdCreate);
        panel.add(cmdDelete);

        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;");
        return panel;
    }
     private void selectAll(boolean selected) {
        for (NVCard card : cards) {
            card.setSelected(selected);
        }
    }
      private void deleteSelectedCards() {         
        List<NVCard> selectedCards = new ArrayList<>();
        StringBuilder deletedNames = new StringBuilder("Đã xóa các nhân viên: ");
        for (NVCard card : cards) {
            if (card.isSelected()) { 
                selectedCards.add(card);
            }
        }

        if (selectedCards.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có nhân viên nào được chọn để xóa.");
            return;
        }

        for (NVCard card : selectedCards) {
            deletedNames.append(card.getEmployeeName()).append(", ");
            panelCard.remove(card);
            cards.remove(card);
        }
        panelCard.revalidate();
        panelCard.repaint();
        
        
        JOptionPane.showMessageDialog(this, "Đã xóa " + selectedCards.size() + " nhân viên.");
        if (deletedNames.length() > 0) {
        deletedNames.setLength(deletedNames.length() - 2); // Xóa dấu phẩy và khoảng trắng cuối cùng
    }
        JOptionPane.showMessageDialog(this, deletedNames.toString());
    }
     private void showModal() {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.TRAILING, Location.TOP)
                .setAnimateDistance(0.7f, 0);
        ModalDialog.showModal(this, new SimpleModalBorder(
                new SimpleInputForms(), "Create", SimpleModalBorder.YES_NO_OPTION,
                (controller, action) -> {
                    controller.close();
                }), option);
        
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
    private List<NVCard> cards;
    private JPanel panelCard;
    private ResponsiveLayout responsiveLayout;
    
       
}
