package gui.main;

import bus.busnhanvien;


import gui.comp.Header;
import gui.comp.Menu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.form.formchamcong;
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
import gui.form.formnhacungcap;
import gui.form.formtaikhoan;
import gui.form.formthanhtoan;
import gui.form.formuudaivakhuyenmai;
import gui.swing.dashboard.MenuItem;
import gui.swing.dashboard.PopupMenu;
import gui.swing.icon.GoogleMaterialDesignIcons;
import gui.swing.icon.IconFontSwing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
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
    
    public Guimain(int manv) throws SQLException, ParseException {
        initComponents();
        init(manv);
    }

    private void init(int manv) throws SQLException, ParseException {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        
        busnhanvien busnv = new busnhanvien();
    
        int macv = busnv.getmachucvu(manv);
        
        menu = new Menu();
        header = new Header(manv, macv);
        
        main = new MainForm();
        
        menu.addEvent((int menuIndex, int subMenuIndex) -> {
             if (subMenuIndex == -1) {
            main.setVisible(true);
        } else {
//                 Danh mục Order
                 if ((menuIndex==0)&&(subMenuIndex==0)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     try {
                         main.showForm(new formmenu());
                     } catch (SQLException ex) {
                         Logger.getLogger(Guimain.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 if ((menuIndex==0)&&(subMenuIndex==1)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     main.showForm(new formhoadon());
                 }
                
//                 Danh mục Kho
                if ((menuIndex==1)&&(subMenuIndex==0)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     try {
                         main.showForm(new formsanpham());
                     } catch (SQLException ex) {
                         Logger.getLogger(Guimain.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                if ((menuIndex==1)&&(subMenuIndex==1)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     main.showForm(new formnhacungcap());
                 }
                if ((menuIndex==1)&&(subMenuIndex==2)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     main.showForm(new formphieunhap(manv));
                 }
                
//                 Danh mục Nhân viên
                 if ((menuIndex==2)&&(subMenuIndex==0)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     try {
                         main.showForm(new formnhanvien());
                     } catch (SQLException ex) {
                         Logger.getLogger(Guimain.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 if ((menuIndex==2)&&(subMenuIndex==1)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     try {
                        main.showForm(new formtaikhoan());
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                 }
                 if ((menuIndex==2)&&(subMenuIndex==2)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     try {
                         main.showForm(new formchucvu());
                     } catch (SQLException ex) {
                         Logger.getLogger(Guimain.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (ParseException ex) {
                         Logger.getLogger(Guimain.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
  
                 if ((menuIndex==2)&&(subMenuIndex==3)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     main.showForm(new formchamcong());
                 }
                 
//                  if ((menuIndex==2)&&(subMenuIndex==4)) {
//                     try {
//                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                     main.showForm(new formluong());
//                 }

                  if ((menuIndex==2)&&(subMenuIndex==5)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     try {
                         main.showForm(new formhopdong());
                     } catch (SQLException ex) {
                         Logger.getLogger(Guimain.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
  
                // Danh mục Khách hàng
                //Thông tin khách hàng
                if ((menuIndex == 3) && (subMenuIndex == 0)) { // menuIndex=3: Khách hàng, subMenuIndex=0: danh sách khách hàng
                    try {
                        UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        main.showForm(new formkhachhang());
                    } catch (Exception ex) { // Đổi từ SQLException thành Exception
                        Logger.getLogger(Guimain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //Thông tin ưu đãi, khuyến mãi
                if ((menuIndex == 3) && (subMenuIndex == 1)) { // menuIndex=3: Khách hàng, subMenuIndex=0: danh sách khách hàng
                                   try {
                                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                                   } catch (Exception e) {
                                       e.printStackTrace();
                                   }
                                   try {
                                       main.showForm(new formuudaivakhuyenmai());
                                   } catch (Exception ex) { // Đổi từ SQLException thành Exception
                                       Logger.getLogger(Guimain.class.getName()).log(Level.SEVERE, null, ex);
                                   }
                               }
//                 Danh mục Thống kê
                  if ((menuIndex==4)&&(subMenuIndex==0)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     main.showForm(new formthongke());
                 }   
//                 Danh mục Cài đặt
                 
                 
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
