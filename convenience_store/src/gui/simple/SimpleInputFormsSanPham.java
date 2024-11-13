package gui.simple;

import bus.bussanpham;
import com.formdev.flatlaf.FlatClientProperties;
import dto.dtonhacungcap;
import dto.dtophanloai;
import dto.dtosanpham;
import net.miginfocom.swing.MigLayout;
import gui.modal.component.ModalBorderAction;
import gui.modal.component.SimpleModalBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JColorChooser.showDialog;

public class SimpleInputFormsSanPham extends JPanel {
    private JTextField txtMaSanPham = new JTextField();
    private JTextField txtTenSanPham = new JTextField();
    private JComboBox<String> comboBox = new JComboBox<>();
    private JComboBox<String> comboxBoxNCC = new JComboBox<>();
    private JTextField txtNhaCC = new JTextField();
    private String nameImg = "";
    private bussanpham bussp = new bussanpham();
    private ArrayList<dtophanloai> listpl = new ArrayList<>();
    private ArrayList<dtonhacungcap> listNCC = new ArrayList<>();
    private Map<String, Integer> categoryMaNCC;
    private File selectedFile;
    
    public SimpleInputFormsSanPham() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));

        txtTenSanPham.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTenSanPham.setPreferredSize(new Dimension(150, 30));
        
        txtMaSanPham.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã sản phẩm được tạo tự động");
        txtTenSanPham.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên sản phẩm");

        createTitle("Thông tin sản phẩm");
        add(new JLabel("Tên sản phẩm"), "gapy 5 0");
        add(txtTenSanPham);


        add(new JLabel("Phân loại"), "gapy 5 0");
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBox.setPreferredSize(new Dimension(150, 30));
        Map<String, Integer> categoryMapPL = new HashMap<>();
        try {
            listpl = bussp.listPhanloai();
            for(int i = 0 ; i < listpl.size(); i++){
                dtophanloai pl = listpl.get(i);
                String tenPhanLoai = pl.getTenPhanLoai();
                Integer maPhanLoai = pl.getMaPhanLoai();
                categoryMapPL.put(tenPhanLoai, maPhanLoai);
                comboBox.addItem(tenPhanLoai); 
            }
            add(comboBox);

        } catch (SQLException ex) {
            Logger.getLogger(SimpleInputFormsSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        add(comboBox);
        
        
        add(new JLabel("Nhà cung cấp"), "gapy 5 0");
        comboxBoxNCC.setFont(new Font("Arial" , Font.PLAIN, 16));
        comboxBoxNCC.setPreferredSize(new Dimension(150, 30));
        categoryMaNCC = new HashMap<>();
        try {
            listNCC = bussp.listNCC();
            System.out.println(listpl.size());
            for(int i = 0 ; i < listNCC.size(); i++){
                dtonhacungcap ncc = listNCC.get(i);
                String tenNCC = ncc.getTenNhaCungCap();
                Integer maNCC = ncc.getMaNhaCungCap();
                categoryMaNCC.put(tenNCC, maNCC);
                comboxBoxNCC.addItem(tenNCC); 
            }
            add(comboxBoxNCC);

        } catch (SQLException ex) {
            Logger.getLogger(SimpleInputFormsSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
    
    
    public File getSelectedFile(){
        return selectedFile;
    }
    
   
    
    public dtosanpham getSanPham() throws ParseException{
        bussanpham bus = new bussanpham();
        dtosanpham sp = new dtosanpham();
        Integer mapl = bus.getMaPL(String.valueOf(comboBox.getSelectedItem()));
        sp.setMaPhanLoai(mapl);
        sp.setMaSanPham(bus.getCountSanPham() + 1);
        sp.setTenSanPham(txtTenSanPham.getText().trim());
        sp.setNgayThem(null);
        sp.setImg(nameImg);
        sp.setSoLuong(0);
        sp.setHanSD("0");
        sp.setIshidden(0);
        Integer maNCC = categoryMaNCC.get(comboxBoxNCC.getSelectedItem());
        sp.setMaNCC(maNCC);
        return sp;
    }

    private void createTitle(String title) {
        JLabel lb = new JLabel(title);
        lb.putClientProperty(FlatClientProperties.STYLE, "font:+2");
        add(lb, "gapy 5 0");
        add(new JSeparator(), "height 2!,gapy 0 0");
    }

}
