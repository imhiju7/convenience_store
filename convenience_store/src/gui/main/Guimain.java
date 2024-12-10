package gui.main;

import bus.busnhanvien;
import dto.dtonhanvien;


import gui.comp.Header;
import gui.comp.Menu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.form.formchamcong;
import gui.form.formchamconghangngay;
import gui.form.formchucnang;
import gui.form.formchucvu;
import gui.form.formnhanvien;
import gui.form.formmenu;
import gui.form.formsanpham;
import gui.form.formthongke;
import gui.form.formhoadon;
import gui.form.formhopdong;
import gui.form.formphieunhap;
import gui.form.frmlogin;
import gui.form.formkhachhang;
import gui.form.formluong;
import gui.form.formnhacungcap;
import gui.form.formphanloai;
import gui.form.formtaikhoan;
import gui.form.formthanhtoan;
import gui.form.formthongkesp;
import gui.form.formuudaivakhuyenmai;
import gui.swing.dashboard.MenuItem;
import gui.swing.dashboard.PopupMenu;
import gui.swing.icon.GoogleMaterialDesignIcons;
import gui.swing.icon.IconFontSwing;
import helper.UnicodeUtils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Guimain extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private dtonhanvien nv;
    private String submenuText;
    public Guimain(int manv) throws SQLException, ParseException {
        initComponents();
        init(manv);
    }
    
    private void init(int manv) throws SQLException, ParseException {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        
        busnhanvien busnv = new busnhanvien();
        ArrayList<dtonhanvien> nvlist = busnv.getNhanVienList();
        for (dtonhanvien i : nvlist) {
            if (i.getManhanvien()==manv) {
                nv = i;
            }
        }
        int macv = busnv.getmachucvu(manv);
        
        menu = new Menu();
        header = new Header(manv, macv);
        
        main = new MainForm();
        
        menu.addEvent(new EventMenuSelected() {
    @Override
    public void menuSelected(int menuIndex, int subMenuIndex) {
        
    }

    @Override
    public void menuSelected(String submenuName) {
        submenuText = UnicodeUtils.removeAccent(submenuName);
        try {
            navigateForm(submenuText);
        } catch (SQLException ex) {
            Logger.getLogger(Guimain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}); 
        
        menu.addEventShowPopup((Component com) -> {
            MenuItem item = (MenuItem) com;
            PopupMenu popup = new PopupMenu(Guimain.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
            int x1 = Guimain.this.getX() + 52;
            int y1 = Guimain.this.getY() + com.getY() + 86;
            popup.setLocation(x1, y1);
            popup.setVisible(true);
        });
        
        menu.initMenuItem(macv);
        
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
    }
    public void navigateForm(String submenuName) throws SQLException {
    try {
        UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
    } catch (Exception e) {
        e.printStackTrace();
    }

    switch (submenuName) {
         case "Menu":
        main.showForm(new formmenu(nv.getManhanvien()));
        break;
    case "Hoa don": // Thay "Hóa đơn" thành "Hoa don"
        main.showForm(new formhoadon());
        break;
    case "San pham": // Thay "Sản phẩm" thành "San pham"
        main.showForm(new formsanpham());
        break;
    case "Nha cung cap": // Thay "Nhà cung cấp" thành "Nha cung cap"
        main.showForm(new formnhacungcap());
        break;
    case "Phieu nhap": // Thay "Phiếu nhập" thành "Phieu nhap"
        main.showForm(new formphieunhap(nv.getManhanvien()));
        break;
    case "Thong tin nhan vien": // Thay "Thông tin nhân viên" thành "Thong tin nhan vien"
        main.showForm(new formnhanvien());
        break;
    case "Tai khoan": // Thay "Tài khoản" thành "Tai khoan"
        main.showForm(new formtaikhoan());
        break;
    case "Cham cong": // Thay "Chấm công" thành "Cham cong"
        main.showForm(new formchamcong());
        break;
    case "Luong": // Thay "Lương" thành "Luong"
        main.showForm(new formluong());
        break;
    case "Hop dong lao dong": // Thay "Hợp đồng lao động" thành "Hop dong lao dong"
        main.showForm(new formhopdong());
        break;
    case "Thong tin khach hang": // Thay "Thông tin khách hàng" thành "Thong tin khach hang"
        main.showForm(new formkhachhang());
        break;
    case "Uu dai & khuyen mai": // Thay "Ưu đãi & khuyến mãi" thành "Uu dai & khuyen mai"
        main.showForm(new formuudaivakhuyenmai());
        break;
    case "Tong quan": // Thay "Tổng quan" thành "Tong quan"
        main.showForm(new formthongke());
        break;
    case "Thong ke san pham": // Thay "Thống kê sản phẩm" thành "Thong ke san pham"
        main.showForm(new formthongkesp());
        break;
    case "Chuc nang": // Thay "Chức năng" thành "Chuc nang"
        main.showForm(new formchucnang());
        break;
    case "Phan loai": // Thay "Phân loại" thành "Phan loai"
        main.showForm(new formphanloai());
        break;
    case "Cham cong hang ngay": // Thay "Chấm công hằng ngày" thành "Cham cong hang ngay"
        main.showForm(new formchamconghangngay(nv));
        break;
    default:
        System.out.println("Khong tim thay form cho submenu: " + submenuName);
        break;
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
