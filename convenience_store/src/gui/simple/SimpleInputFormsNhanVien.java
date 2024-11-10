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
import java.util.Date;

public class SimpleInputFormsNhanVien extends JPanel {

    private JTextField tennv , sdt, diachi, email, luong;
    private JComboBox comboGioitinh, comboChucvu;
    private JDateChooser namsinh;
    
    private ArrayList<dtochucvu> list_cv;
    private busnhanvien bus_nv;
    private String nameImg = "";
    
    
    public SimpleInputFormsNhanVien() throws SQLException {
        init();
    }

    private void init() throws SQLException {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));
        
        tennv = new JTextField();
        sdt = new JTextField();
        diachi = new JTextField();
        email = new JTextField();
        luong = new JTextField();
        comboGioitinh = new JComboBox();
        comboChucvu = new JComboBox();
        namsinh = new JDateChooser();
        
        // style
        tennv.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên");
        sdt.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập số điện thoại");
        diachi.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập địa chỉ");
        email.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập email");
        luong.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập lương");
        

        add(new JLabel("Tên nhân viên: "), "gapy 5 0");
        add(tennv);
        add(new JLabel("Số điện loại: "), "gapy 5 0");
        add(sdt);
        add(new JLabel("Email: "), "gapy 5 0");
        add(email);
        add(new JLabel("Địa chỉ"), "gapy 5 0");
        add(diachi);
        add(new JLabel("Lương: "), "gapy 5 0");
        add(luong);
        
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
                File selectedFile = fileChooser.getSelectedFile();
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
    
    public boolean check_NV(dtonhanvien nv){
        String regexTenNV = "^[A-Za-zÀ-ỹ]+( [A-Za-zÀ-ỹ]+)*$";
        String regexSDT = "^0\\d{9}$";
        String regexLuong = "^[1-9]\\d*(\\.\\d+)?$";
        
        if (nv.getSdt() == null || nv.getSdt().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống");
            return false;
        }
        if(!nv.getSdt().matches(regexSDT)){
            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng số 0 và đủ 10 số");
            return false;
        }
        if (nv.getTennhanvien()== null || nv.getTennhanvien().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên nhân viên không để trống");
            return false;
        }
        if(!nv.getTennhanvien().matches(regexTenNV)){
            JOptionPane.showMessageDialog(null, "Tên nhân viên không chứa ký tự đặc biệt");
            return false;
        }
        if (!String.valueOf(nv.getLuongcoban()).matches(regexLuong)) {
            JOptionPane.showMessageDialog(null, "Lương không được âm và phải là số");
            return false;
        }

        return true;
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
        nv.setLuongcoban(Float.parseFloat(luong.getText()));
        nv.setMachucvu(bus_nv.getMaChucVuByName((String) comboChucvu.getSelectedItem()));
        nv.setMahopdong(0);
        
        nv.setNgaylamviec(null);
        nv.setNgayketthuc(null);

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