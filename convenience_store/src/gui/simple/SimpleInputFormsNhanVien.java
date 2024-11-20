package gui.simple;

import bus.busnhanvien;
import com.formdev.flatlaf.FlatClientProperties;
import com.toedter.calendar.JDateChooser;
import dto.dtochucvu;
import dto.dtonhanvien;
import net.miginfocom.swing.MigLayout;
import gui.modal.component.ModalBorderAction;
import gui.modal.component.SimpleModalBorder;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;


import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SimpleInputFormsNhanVien extends JPanel {

    private JTextField tennv , sdt, diachi, email;
//    , luong;
    private JComboBox comboGioitinh, comboChucvu;
    private JDateChooser namsinh;
    
    private ArrayList<dtochucvu> list_cv;
    private busnhanvien bus_nv;
    private String nameImg = "";
    private File selectedFile;
    
    
    public SimpleInputFormsNhanVien() throws SQLException {
        init();
    }

    private void init() throws SQLException {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        tennv = new JTextField();
        sdt = new JTextField();
        diachi = new JTextField();
        email = new JTextField();
        comboGioitinh = new JComboBox();
        comboChucvu = new JComboBox();
        namsinh = new JDateChooser();
        namsinh.setDateFormatString("dd-MM-yyyy");
        ((JTextField) namsinh.getDateEditor().getUiComponent()).setEditable(false);

        
        // style
        tennv.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên");
        sdt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập số điện thoại");
        diachi.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập địa chỉ");
        email.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập email");
        

        add(new JLabel("Tên nhân viên: "), "gapy 5 0");
        add(tennv);
        add(new JLabel("Số điện loại: "), "gapy 5 0");
        add(sdt);
        add(new JLabel("Email: "), "gapy 5 0");
        add(email);
        add(new JLabel("Địa chỉ"), "gapy 5 0");
        add(diachi);
        
        add(new JLabel("Ngày Sinh"), "Split 2");
        add(new JLabel("Giới tính: "));

        add(namsinh , "split 2");
        comboGioitinh.addItem("Nữ");
        comboGioitinh.addItem("Nam");
        add(comboGioitinh);
        
        add(new JLabel("Chức vụ: ") , "gapy 5 0");
        bus_nv = new busnhanvien();
        list_cv = bus_nv.listChucVu();
        for(dtochucvu cv : list_cv){
            comboChucvu.addItem(cv.getTenchucvu());
        }
        add(comboChucvu);
        
        add(new JLabel("Hình ảnh"), "gapy 5 0");

        JLabel labelImg = new JLabel();
        labelImg.setPreferredSize(new Dimension(170, 230));
        add(labelImg, "gapy 5 0");
        
        JButton fileChooserButton = new JButton("Chọn ảnh");
        fileChooserButton.setPreferredSize(new Dimension(100, 30));
        fileChooserButton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setDialogTitle("Chọn ảnh");
            Component showDialog = null;

            int result = fileChooser.showOpenDialog(showDialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                nameImg = selectedFile.getName();
                updateImageLabel(labelImg, selectedFile.getPath());
            }
        });
        add(fileChooserButton , "gapy 5 0");
      
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
    
    public boolean check_NV(dtonhanvien nv) throws SQLException{
        String regexTenNV = "^[A-Za-zÀ-ỹ]+( [A-Za-zÀ-ỹ]+)*$";
        String regexSDT = "^0\\d{9}$";
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        
        if (nv.getTennhanvien()== null || nv.getTennhanvien().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống");
            return false;
        }
        if(!nv.getTennhanvien().matches(regexTenNV)){
            JOptionPane.showMessageDialog(null, "Tên nhân viên không chứa ký tự đặc biệt");
            return false;
        }
        
        if (nv.getSdt() == null || nv.getSdt().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống");
            return false;
        }
        
        if(bus_nv.checkExistSdt(nv.getSdt())) {
            JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại");
            return false;
        }
        
        if(!nv.getSdt().matches(regexSDT)){
            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0 và đủ 10 số");
            return false;
        }
        
        if (nv.getEmail() == null || !nv.getEmail().matches(regexEmail)) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ");
            return false;
        }
        
        if(bus_nv.checkExistEmail(nv.getEmail())) {
            JOptionPane.showMessageDialog(null, "Email này đã tồn tại");
            return false;
        }
        
        

        
        
        Date ngaysinh = nv.getNgaysinh();
        if (ngaysinh != null) {
            Calendar calNgaySinh = Calendar.getInstance();
            calNgaySinh.setTime(ngaysinh);

            int namSinh = calNgaySinh.get(Calendar.YEAR);
            int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
            int tuoi = namHienTai - namSinh;

            // Kiểm tra tuổi, nếu nhỏ hơn 18 tuổi thì báo lỗi
            if (tuoi < 18 || (tuoi == 18 && calNgaySinh.after(Calendar.getInstance()))) {
                JOptionPane.showMessageDialog(null, "Nhân viên phải lớn hơn 18 tuổi");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không được để trống năm sinh nhân viên");
            return false;
        }

        if(nv.getImg().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hình ảnh cần thêm");
            return false;
        }

        
        
        return true;
    }
    
    
    public File getSelectedFile(){
        return selectedFile;
    }

    public dtonhanvien getNhanVien() throws ParseException, SQLException {
        bus_nv = new busnhanvien();
        dtonhanvien nv = new dtonhanvien();

        nv.setManhanvien(bus_nv.getSoLuongNV() + 1);
        nv.setTennhanvien(tennv.getText());
        nv.setDiachi(diachi.getText());
        nv.setEmail(email.getText());
        nv.setGioitinh(comboGioitinh.getSelectedIndex());
        nv.setImg(nameImg);
        nv.setIsdelete(0);
//        nv.setLuongcoban(Float.parseFloat(luong.getText()));
        nv.setLuongcoban(0);
        nv.setMachucvu(bus_nv.getMaChucVuByName((String) comboChucvu.getSelectedItem()));
        nv.setMahopdong(0);
        
//        nv.setNgaylamviec(null);
//        nv.setNgayketthuc(null);
        // Gán ngày sinh nếu người dùng đã chọn ngày
        if (namsinh.getDate() != null) {
            nv.setNgaysinh(namsinh.getDate());
        } else {
            nv.setNgaysinh(null);
        }

        // Lấy ngày và giờ hiện tại cho ngày tạo
        nv.setNgaytao(new Date());

        nv.setSdt(sdt.getText());

        return nv;
    }


    

}