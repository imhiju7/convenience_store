package gui.form;

import bus.busnhanvien;
import bus.bustaikhoan;
import gui.comp.AccountCard;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;
import gui.layout.ResponsiveLayout;
import gui.model.ModelEmployee;
import dto.SampleData;
import dto.dtochucvu;
import dto.dtonhanvien;
import dto.dtotaikhoan;
import gui.comp.RoundedBorder;
import gui.simple.SimpleInputForms;
import gui.simple.SimpleInputFormsNhanVien;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;


@SystemForm(name = "Responsive Layout", description = "responsive layout user interface", tags = {"card"})
public class formtaikhoan extends Form {
    
    
    private busnhanvien busNV;
    private bustaikhoan busTK;
    private ArrayList<dtonhanvien> list_NV;
    private ArrayList<dtotaikhoan> list_TK;
    private ArrayList<dtochucvu> list_CV;
    private JLabel imageDisplayLabel;
    private File selectedFile;
    private JComboBox<Object> comboxNCC;
    private JComboBox<Object> comboxCV;
    private JTextField[] textField = new JTextField[20];
    private JComboBox<String> genderComboBox;
    private JDateChooser dateChooser;
    
    
    public formtaikhoan() throws SQLException {
        init();
        formInit();
        
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
        panelCard.removeAll();
        try {
            busNV = new busnhanvien();
            busNV.list();
            list_NV = busNV.getList();
            for(int i = 0 ; i < list_NV.size() ; i++){
                dtonhanvien nv = list_NV.get(i);
                AccountCard card1 = new AccountCard(nv, createEventCard1() , 1);
                cards.add(card1);
                panelCard.add(card1);
            }
            
            panelCard.repaint();
            panelCard.revalidate();
        } catch (SQLException ex) {
        }
    }
    
    private Consumer<dtonhanvien> createEventCard1() {
        return e -> {
            try {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int dialogWidth = (int) (screenSize.width * 0.7);
                int dialogHeight = (int) (screenSize.height * 0.7);

                JDialog showDialog = new JDialog();
                showDialog.setSize(dialogWidth, dialogHeight);
                showDialog.setUndecorated(true); // Bỏ khung mặc định của JDialog
                showDialog.setShape(new RoundRectangle2D.Double(0, 0, dialogWidth, dialogHeight, 30, 30)); // Bo góc
                showDialog.setLayout(new BorderLayout());
                showDialog.setModal(true);

                JPanel titleBar = new JPanel();
                titleBar.setLayout(new BorderLayout());
                titleBar.setBackground(Color.LIGHT_GRAY);

                JLabel titleLabel = new JLabel("Thông tin tài khoản nhân viên");
                titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                titleLabel.setPreferredSize(new Dimension(dialogWidth, 50));

                JButton closeButton = new JButton("X");
                closeButton.setFocusPainted(false);
                closeButton.setBorderPainted(false);
                closeButton.setBackground(Color.RED);
                closeButton.setForeground(Color.WHITE);
                closeButton.setPreferredSize(new Dimension(45, 30));

                // Sự kiện đóng cửa sổ khi nhấn nút X
                closeButton.addActionListener(event -> showDialog.dispose());

                titleBar.add(titleLabel, BorderLayout.WEST);
                titleBar.add(closeButton, BorderLayout.EAST);

                // Tạo JPanel chứa thông tin nhân viên và ảnh
                JPanel contentPanel = new JPanel(new GridBagLayout());
                contentPanel.setBackground(Color.WHITE);

                // Panel bên trái chứa thông tin nhân viên

                JPanel nv_panel = new JPanel(new GridBagLayout());
                nv_panel.setBackground(Color.WHITE); // Đặt nền trắng cho panel
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5); // Đặt khoảng cách giữa các thành phần
                gbc.fill = GridBagConstraints.HORIZONTAL;
                bustaikhoan busTk = new bustaikhoan();

                int maNhanVien = e.getManhanvien();
                String tenDangNhap = busTk.getTenDangNhap(maNhanVien);
                String matKhau = busTk.getMatKhau(maNhanVien);
                Date ngayTao = busTk.getNgayTao(maNhanVien);
                int isBlock = busTk.getIsBlock(maNhanVien);
               // Tạo panel bên trái
                addField(nv_panel, gbc, "Mã nhân viên:", 0, String.valueOf(maNhanVien));
                addField(nv_panel, gbc, "Tên nhân viên:", 1, String.valueOf(e.getTennhanvien()));
                addField(nv_panel, gbc, "Tên đăng nhập:", 2, tenDangNhap);
                addField(nv_panel, gbc, "Mật khẩu:", 3, matKhau);
                addField(nv_panel, gbc, "Ngày tạo:", 4, ngayTao != null ? ngayTao.toString() : "N/A");
                addField(nv_panel, gbc, "Trạng thái khóa:", 5, isBlock == 1 ? "Đã khóa" : "Hoạt động");
                addField(nv_panel, gbc, "Tên chức vụ:", 6,busNV.getTenChucVu(e.getMachucvu()));


                // Tạo JPanel bên phải cho hình ảnh
                JPanel imagePanel = new JPanel(new GridBagLayout());
                imagePanel.setBackground(Color.WHITE);
                GridBagConstraints rightGbc = new GridBagConstraints();
                rightGbc.insets = new Insets(5, 5, 5, 5);
                addImageField(imagePanel,rightGbc, e.getImg(), showDialog);

                GridBagConstraints mainGbc = new GridBagConstraints();
                mainGbc.insets = new Insets(10, 10, 10, 10);
                mainGbc.fill = GridBagConstraints.BOTH;
                mainGbc.gridx = 0;
                mainGbc.gridy = 0;
                mainGbc.weightx = 0.6;
                contentPanel.add(nv_panel, mainGbc);

                mainGbc.gridx = 1;
                mainGbc.weightx = 0.4;
                contentPanel.add(imagePanel, mainGbc);



                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Đặt vị trí nút về phía phải
                buttonPanel.setBackground(Color.WHITE);

                JButton saveButton = new JButton("Lưu");
                saveButton.setPreferredSize(new Dimension(100, 30));
                saveButton.setBackground(Color.GREEN); // Đặt màu nền xanh lá
                saveButton.setForeground(Color.WHITE); // Đặt màu chữ trắng

                saveButton.addActionListener(event -> {
    try {
        dtonhanvien nv = new dtonhanvien();
        dtotaikhoan tk = new dtotaikhoan();

        // Lấy giá trị từ giao diện người dùng
        nv.setManhanvien(Integer.parseInt(textField[0].getText()));  // Mã nhân viên
        nv.setTennhanvien(textField[1].getText());  // Tên nhân viên

        // Kiểm tra chức vụ hiện tại, nếu là admin thì không cho phép sửa
        String currentChucVu = busNV.getChucVuByMaNhanVien(nv.getManhanvien()); // Lấy chức vụ hiện tại của nhân viên
        String newChucVu = (String) comboxCV.getSelectedItem(); // Chức vụ mới từ ComboBox

        if (currentChucVu.equals("admin") && !newChucVu.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Không thể sửa chức vụ của nhân viên 'admin'.");
            comboxCV.setSelectedItem(currentChucVu); // Quay lại chức vụ hiện tại
            return;  // Dừng lại nếu chức vụ là admin và cố gắng thay đổi chức vụ
        }

        if (!currentChucVu.equals("admin") && newChucVu.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Không thể sửa chức vụ của nhân viên thành 'admin'.");
            comboxCV.setSelectedItem(currentChucVu); // Quay lại chức vụ hiện tại
            return;  // Dừng lại nếu chức vụ không phải admin và cố gắng chuyển thành admin
        }

        // Set lại thông tin tài khoản từ các component tương ứng
        tk.setTendangnhap(textField[2].getText());  // Tên đăng nhập
        tk.setMatkhau(textField[3].getText());  // Mật khẩu

        // Trạng thái khóa - lấy từ JComboBox
        String trangThai = (String) genderComboBox.getSelectedItem(); // Hoạt động hay đã khóa
        tk.setIsblock(trangThai.equals("Hoạt động") ? 0 : 1);  // 0 là hoạt động, 1 là khóa

        // Cập nhật chức vụ từ ComboBox
        nv.setMachucvu(busNV.getMaChucVuByName(newChucVu));  // Cập nhật chức vụ mới

        // Nếu có ảnh mới, lưu ảnh vào thư mục
        if (selectedFile != null) {
            String destinationDir = "/source/image/nhanvien/";
            saveImageToDirectory(destinationDir);  // Lưu ảnh vào thư mục
            nv.setImg(selectedFile.getName());  // Cập nhật tên ảnh vào đối tượng
        } else {
            nv.setImg(e.getImg());  // Nếu không có ảnh mới, giữ ảnh cũ
        }

        // Khởi tạo đối tượng busTK nếu chưa có, hoặc dùng đối tượng đã khởi tạo
        bustaikhoan busTK = new bustaikhoan();  // Tạo đối tượng BusNhanVien

        // Hiển thị thông báo xác nhận cập nhật
        int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc sửa thông tin nhân viên này không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Cập nhật thông tin tài khoản
            boolean isUpdated = busTK.updateTaiKhoan(nv.getManhanvien(), tk.getTendangnhap(), tk.getMatkhau(), tk.getIsblock());
            
            // Cập nhật chức vụ nhân viên
            if (isUpdated) {
                // Cập nhật chức vụ nhân viên
                busnhanvien busNV = new busnhanvien(); // Tạo đối tượng BusNhanVien
                boolean isChucVuUpdated = busNV.updateChucVuByMaNhanVien(nv.getManhanvien(), nv.getMachucvu());

                if (isChucVuUpdated) {
                    // Cập nhật giao diện và thông báo thành công
                    formInit();  // Cập nhật lại giao diện
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    showDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật chức vụ không thành công");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật tài khoản không thành công");
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(formtaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra trong quá trình cập nhật");
    }
});




                buttonPanel.add(saveButton);
                showDialog.add(titleBar, BorderLayout.NORTH);
                showDialog.add(contentPanel, BorderLayout.CENTER);
                showDialog.add(buttonPanel, BorderLayout.SOUTH); // Thêm nút lưu vào phía dưới
                showDialog.setLocationRelativeTo(null); // Đặt JDialog ở giữa màn hình
                showDialog.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(formtaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
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
            ImageIcon curImg = new ImageIcon(getClass().getResource("/source/image/nhanvien/" + imgPath));
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
//        panel.add(fileChooserButton, gbc);
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
    
    
    
    private void addField(JPanel panel, GridBagConstraints gbc, String label, int row, String value) throws SQLException {
    // Thêm label với kích thước lớn hơn
    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.weightx = 0; // Không chiếm thêm không gian ngang cho label
    gbc.fill = GridBagConstraints.NONE; // Label sẽ có kích thước tự nhiên
    JLabel jLabel = new JLabel(label);
    jLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Thiết lập kích thước phông chữ lớn hơn
    jLabel.setPreferredSize(new Dimension(150, 30)); // Thiết lập kích thước cố định cho label
    panel.add(jLabel, gbc);

    gbc.gridx = 1;
    gbc.weightx = 1.0; // Chiếm toàn bộ không gian ngang còn lại
    gbc.fill = GridBagConstraints.HORIZONTAL; // TextField sẽ lấp đầy không gian ngang

        if (label.equals("Trạng thái khóa:")) {
    // Khởi tạo JComboBox nếu chưa được khởi tạo
    if (genderComboBox == null) {
        genderComboBox = new JComboBox<>(new String[]{"Hoạt động", "Đã khóa"});
        genderComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        genderComboBox.setPreferredSize(new Dimension(150, 40));
        // Thiết lập giá trị mặc định dựa trên giá trị của `value`
        genderComboBox.setSelectedIndex(value.equals("1") ? 1 : 0);
    }
    panel.add(genderComboBox, gbc);
}


        else if (label.equals("Ngày tạo:")) {
    // Nếu là trường "Ngày tạo", tạo JTextField và đặt ở chế độ chỉ đọc
    JTextField ngayTaoField = new JTextField(20);
    ngayTaoField.setFont(new Font("Arial", Font.PLAIN, 16));
    ngayTaoField.setPreferredSize(new Dimension(150, 40));
    ngayTaoField.setBorder(new RoundedBorder(10));
    ngayTaoField.setText(value); // Thiết lập giá trị ban đầu từ chuỗi value
    ngayTaoField.setEditable(false); // Đặt JTextField ở trạng thái chỉ đọc
    panel.add(ngayTaoField, gbc);
}



     else if (label.equals("Tên chức vụ:")) {
        // Nếu là trường chức vụ, tạo JComboBox
        comboxCV = new JComboBox<>();
        comboxCV.setFont(new Font("Arial", Font.PLAIN, 16));
        comboxCV.setPreferredSize(new Dimension(150, 40));
        comboxCV.setBorder(new RoundedBorder(10));

        list_CV = busNV.listChucVu();
        for (dtochucvu cv : list_CV) {
            comboxCV.addItem(cv.getTenchucvu());
        }
        comboxCV.setSelectedItem(value);
        panel.add(comboxCV, gbc);

    } else {
        // Nếu không phải, tạo JTextField bình thường
        textField[row] = new JTextField(20);
        textField[row].setFont(new Font("Arial", Font.PLAIN, 16));
        textField[row].setPreferredSize(new Dimension(150, 40));
        textField[row].setMinimumSize(new Dimension(150, 40));
        textField[row].setText(value);
        textField[row].setBorder(new RoundedBorder(10));

        // Kiểm tra các nhãn và đặt các trường cần không thể chỉnh sửa
        if (label.equals("Mã nhân viên:") || label.equals("Tên nhân viên:") || label.equals("Ngày tạo:")) {
            textField[row].setEditable(false);
        }
        panel.add(textField[row], gbc);
    }
}




     private Component createHeaderAction() throws SQLException {
    JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230][fill,100][fill,100][fill,100]push[][]"));

    JTextField txtSearch = new JTextField();
    txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm tài khoản...");
    txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/search.png")), 20, 20));

    JButton btnSearch = new JButton("Tìm kiếm");

    // Chức vụ
    JComboBox comboMacv = new JComboBox();
    comboMacv.addItem("Chức vụ");
    busNV = new busnhanvien();
    list_CV = busNV.listChucVu();
    for (dtochucvu cv : list_CV) {
        comboMacv.addItem(cv.getTenchucvu());
    }

    // Trạng thái
    JComboBox combotrangthai = new JComboBox();
    combotrangthai.addItem("Trạng thái");
    combotrangthai.addItem("Đã khóa");
    combotrangthai.addItem("Kích hoạt");

    JButton btnReset = new JButton("Reset");

    JCheckBox selectAllCheckbox = new JCheckBox("Chọn tất cả");
    selectAllCheckbox.addActionListener(e -> selectAll(selectAllCheckbox.isSelected()));

    JButton cmdCreate = new JButton("Thêm");
    JButton cmdDelete = new JButton("Khóa");

    btnSearch.addActionListener(e -> {
        panelCard.removeAll(); // Xóa các card cũ khỏi panelCard
        String searchText = txtSearch.getText().toLowerCase().trim(); // Lấy chuỗi tìm kiếm và loại bỏ khoảng trắng thừa
        String tencv = (String) comboMacv.getSelectedItem();
        String trangThai = (String) combotrangthai.getSelectedItem();

        try {
            busNV.list(); // Lấy danh sách nhân viên
            list_NV = busNV.getList();
        } catch (SQLException ex) {
            Logger.getLogger(formtaikhoan.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<dtonhanvien> list_nv_tmp = new ArrayList<>();
        // Lọc theo chức vụ
        if (!tencv.equals("Chức vụ")) {
            try {
                int machucvu = busNV.getMaChucVuByName(tencv);
                for (dtonhanvien nv : list_NV) {
                    if (nv.getMachucvu() == machucvu) {
                        list_nv_tmp.add(nv);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(formtaikhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            list_nv_tmp = list_NV;  // Không lọc theo chức vụ nếu không chọn chức vụ
        }

        // Lọc theo trạng thái
        if (!trangThai.equals("Trạng thái")) {
            ArrayList<dtonhanvien> list_nv_trangthai_tmp = new ArrayList<>();
            for (dtonhanvien nv : list_nv_tmp) {
                bustaikhoan busTK = new bustaikhoan();
                boolean     isBlocked = busTK.getIsBlockedByMaNhanVien(nv.getManhanvien());
                if ((trangThai.equals("Đã khóa") && isBlocked) || (trangThai.equals("Kích hoạt") && !isBlocked)) {
                    list_nv_trangthai_tmp.add(nv);
                }
            }
            list_nv_tmp = list_nv_trangthai_tmp;
        }

        // Tìm kiếm theo tên nhân viên
        if (searchText.isEmpty()) {
            addCardsToPanel(list_nv_tmp);
        } else {
            ArrayList<dtonhanvien> foundNv = new ArrayList<>();
            for (dtonhanvien nv : list_nv_tmp) {
                if (nv.getTennhanvien().toLowerCase().contains(searchText)) {
                    foundNv.add(nv);
                }
            }
            if (foundNv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản nhân viên tương ứng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            addCardsToPanel(foundNv);
        }
    });

    btnReset.addActionListener(e -> {
        txtSearch.setText("");
        comboMacv.setSelectedIndex(0);
        combotrangthai.setSelectedIndex(0);
        formInit();  // Reset lại các form, nếu cần
    });

    panel.add(txtSearch, "span 1 1"); // Mở rộng không gian của trường tìm kiếm
    panel.add(comboMacv);
    panel.add(combotrangthai);
    panel.add(btnSearch);
    panel.add(btnReset);

    panel.putClientProperty(FlatClientProperties.STYLE, "background:null;");
    return panel;
}

// Thêm các card vào panelCard
private void addCardsToPanel(ArrayList<dtonhanvien> list_nv) {
    for (dtonhanvien nv : list_nv) {
        AccountCard card = new AccountCard(nv, createEventCard1(), 1);
        cards.add(card);
        panelCard.add(card);
    }
    panelCard.repaint();
    panelCard.revalidate();
}


     private void selectAll(boolean selected) {
        for (AccountCard card : cards) {
            card.setSelected(selected);
        }
    }
    private void deleteSelectedCards() throws SQLException {         
        List<AccountCard> selectedCards = new ArrayList<>();
        StringBuilder deletedNames = new StringBuilder("Đã xóa các nhân viên: ");
        for (AccountCard card : cards) {
            if (card.isSelected()) { 
                selectedCards.add(card);
            }
        }
        if (selectedCards.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có nhân viên nào được chọn để xóa.");
            return;
        }

        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa các nhân viên vừa chọn không ?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.NO_OPTION){
            return;
        }
        busNV = new busnhanvien();
        for (AccountCard card : selectedCards) {
            deletedNames.append(card.getEmployeeName()).append(", ");
            busNV.deleteNhanVien(card.getMaNhanVien());
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
     private void showModal() throws SQLException {
        Option option = ModalDialog.createOption();
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.TRAILING, Location.TOP)
                .setAnimateDistance(0.7f, 0);
        SimpleInputFormsNhanVien form = new SimpleInputFormsNhanVien();
        ModalDialog.showModal(this, new SimpleModalBorder(
                 form, "Create", SimpleModalBorder.YES_NO_OPTION,
                (controller, action) -> {
                    if(action == SimpleModalBorder.YES_OPTION){
                        int result =  JOptionPane.showConfirmDialog(null, "Bạn có chắc thêm nhân viên này không","Xác nhận" ,JOptionPane.YES_NO_OPTION);
                        if(result == JOptionPane.NO_OPTION){
                            return;
                        }
                        dtonhanvien nv = new dtonhanvien();
                        try {
                            busNV = new busnhanvien();
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        try {
                            nv = form.getNhanVien();
                            form.check_NV(nv);
                            busNV.AddNhanVien(nv);
                        } catch (ParseException ex) {
                            Logger.getLogger(formtaikhoan.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(formtaikhoan.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if(action == SimpleModalBorder.NO_OPTION){
                        controller.close();
                    }
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
    private List<AccountCard> cards;
    private JPanel panelCard;
    private ResponsiveLayout responsiveLayout;
    
       
}
