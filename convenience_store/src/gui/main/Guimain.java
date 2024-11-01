package gui.main;

import bus.busnhanvien;


import gui.comp.Header;
import gui.comp.Menu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.form.formchamcong;
import gui.form.formchucvu;
import gui.form.formnhanvien;
import gui.form.formsanpham;
import gui.form.formthongke;
import gui.form.frmlogin;

import gui.swing.dashboard.MenuItem;
import gui.swing.dashboard.PopupMenu;
import gui.swing.icon.GoogleMaterialDesignIcons;
import gui.swing.icon.IconFontSwing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public Guimain(int manv) {
        initComponents();
        init(manv);
    }

    private void init(int manv) {
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
                     main.showForm(new formsanpham());
                 }
//                 Danh mục Kho
//                 Danh mục Nhân viên
                 if ((menuIndex==2)&&(subMenuIndex==0)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     main.showForm(new formnhanvien());
                 }
                 if ((menuIndex==2)&&(subMenuIndex==2)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     main.showForm(new formchucvu());
                 }
                 if ((menuIndex==2)&&(subMenuIndex==3)) {
                     try {
                       UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                     main.showForm(new formchamcong());
                 }
//                 Danh mục Khách hàng
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
                 if ((menuIndex==5)&&(subMenuIndex==0)) {
                    dispose();
                    new frmlogin().setVisible(true);
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
