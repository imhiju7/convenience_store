package gui.comp;

import bus.buschucvu;
import bus.busnhanvien;
import gui.form.frmlogin;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Header extends javax.swing.JPanel {

    public Header(int manv,int macv) throws SQLException {
        initComponents();
        
        busnhanvien busnv = new busnhanvien();
        buschucvu buscv = new buschucvu();
        
        String tennv = busnv.gettennvbymanv(manv);
        String tencv = buscv.gettencvbymacv(macv);
        
        lbrole.setText(tencv);
        lbname.setText(tennv);
        
        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/source/image/nhanvien/nv1.png")));
    }
    private void closeCurrentFrame() {
        // Lấy frame chứa panel hiện tại
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        // Kiểm tra nếu là frame
        if (currentFrame != null && currentFrame instanceof JFrame) {
            // Đóng frame
            currentFrame.dispose();
        } else {
            System.out.println("Không tìm thấy frame chứa panel.");
        }
    }
    public void addMenuEvent(ActionListener event) {
        cmdMenu.addActionListener(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMenu = new gui.swing.dashboard.Button();
        cmdExit = new gui.swing.dashboard.Button();
        pic = new gui.swing.dashboard.ImageAvatar();
        lbname = new javax.swing.JLabel();
        lbrole = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));

        cmdMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/source/image/icon/menu.png"))); // NOI18N
        cmdMenu.setFocusable(false);
        cmdMenu.setMargin(new java.awt.Insets(0, 14, 0, 14));
        cmdMenu.setMaximumSize(new java.awt.Dimension(20, 20));
        cmdMenu.setMinimumSize(new java.awt.Dimension(20, 20));
        cmdMenu.setPreferredSize(new java.awt.Dimension(20, 20));
        cmdMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMenuActionPerformed(evt);
            }
        });

        cmdExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/source/image/icon/logout.png"))); // NOI18N
        cmdExit.setFocusable(false);
        cmdExit.setMargin(new java.awt.Insets(0, 14, 0, 14));
        cmdExit.setMaximumSize(new java.awt.Dimension(34, 34));
        cmdExit.setMinimumSize(new java.awt.Dimension(34, 34));
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/source/image/nhanvien/nv1.png"))); // NOI18N

        lbname.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbname.setText("user name");
        lbname.setToolTipText("");

        lbrole.setText("user role");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 897, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbname)
                    .addComponent(lbrole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdExit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbrole, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmdExit, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(pic, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdMenuActionPerformed

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        // TODO add your handling code here:
        closeCurrentFrame();
        new frmlogin().setVisible(true);
    }//GEN-LAST:event_cmdExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.dashboard.Button cmdExit;
    private gui.swing.dashboard.Button cmdMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbname;
    private javax.swing.JLabel lbrole;
    private gui.swing.dashboard.ImageAvatar pic;
    // End of variables declaration//GEN-END:variables
}
