package com.raven.component;

import com.raven.swing.Button;
import com.raven.swing.MyPasswordField;
import com.raven.swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        fgpwd.setVisible(true);
    }

    private void initRegister() {
        fgpwd.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Reset Password");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        fgpwd.add(label);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(null);
        userPanel.setPreferredSize(new Dimension(500, 40)); // Đặt kích thước ưa thích
        userPanel.setBackground(new Color(255,255,255));
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
        txtUser.setHint("Username");
        txtUser.setBounds(55, 0, 336, 35); // Xác định vị trí và kích thước cho txtPass
        userPanel.add(txtUser, "w 100%");
        JButton btnSearchEmail = new JButton();
        btnSearchEmail.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        btnSearchEmail.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/mail.png"))); // NOI18N
        btnSearchEmail.setToolTipText("Check&Find Email");
        btnSearchEmail.setBorder(null);
        btnSearchEmail.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearchEmail.setBounds(405, 0, 30, 30); // Xác định vị trí và kích thước cho btnShow
        userPanel.add(btnSearchEmail, "w 20%, h 40!");
        fgpwd.add(userPanel,"w 80%");
        btnSearchEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchEmailActionPerformed(evt);
            }

            private void btnSearchEmailActionPerformed(ActionEvent evt) {
//                hàm search email
            }
        });
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/mail.png")));
        txtEmail.setHint("Email");
        txtEmail.setEditable(false);
        txtEmail.setFocusable(false);
        fgpwd.add(txtEmail, "w 60%");
        MyPasswordField txtCode = new MyPasswordField();
        txtCode.setEchoChar('●');
        txtCode.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
        txtCode.setHint("Verify Code");
        fgpwd.add(txtCode, "w 60%");
        Button sendCode = new Button();
        sendCode.setBackground(new Color(7, 164, 121));
        sendCode.setForeground(new Color(250, 250, 250));
        sendCode.setText("SEND CODE");
        Button verify = new Button();
        verify.setBackground(new Color(7, 164, 121));
        verify.setForeground(new Color(250, 250, 250));
        verify.setText("VERIFY CODE");
        fgpwd.add(sendCode, "w 30%, h 40,split 2");
        fgpwd.add(verify, "w 30%, h 40");
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);

        MyTextField txtUsername = new MyTextField();
        txtUsername.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
        txtUsername.setHint("Username");
        login.add(txtUsername, "w 60%");
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(null); // Thiết lập layout tuyệt đối
        passwordPanel.setPreferredSize(new Dimension(500, 40)); // Đặt kích thước ưa thích
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/pass.png")));
        txtPass.setHint("Password");
        txtPass.setEchoChar('●');
        txtPass.setBounds(55, 0, 336, 35); // Xác định vị trí và kích thước cho txtPass
        passwordPanel.add(txtPass, "w 100%");
//        login.add(txtPass, "w 60% ,h 40!");
        // Thêm nút Show Password
        JButton btnShow = new JButton();
        btnShow.setForeground(new Color(100, 100, 100));
        btnShow.setIcon(new ImageIcon((getClass().getResource("/com/raven/icon/pass.png"))));
        btnShow.setFont(new Font("sansserif", 1, 12));
        btnShow.setContentAreaFilled(false);
        btnShow.setCursor(new Cursor(Cursor.HAND_CURSOR));
         btnShow.setBounds(405, 0, 30, 30); // Xác định vị trí và kích thước cho btnShow
        passwordPanel.add(btnShow, "w 20%, h 40!");
//        login.add(btnShow, "w 10%, h 40!");
        passwordPanel.setBackground(new Color(255,255,255));
        login.add(passwordPanel, "w 80%"); // Đảm bảo JPanel cũng rộng 60%

         btnShow.addActionListener(new ActionListener() {
        boolean isPasswordVisible = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isPasswordVisible) {
                txtPass.setEchoChar('●'); // Đặt lại ký tự dấu chấm
            } else {
                txtPass.setEchoChar((char) 0); // Hiện mật khẩu

            }
            isPasswordVisible = !isPasswordVisible;
        }
    });
          // Thêm checkbox "Remember Password" và căn trái
        JCheckBox btnReme = new JCheckBox("Stay Logged In");
        login.add(btnReme, " wrap, w 60%"); // Căn trái và xuống dòng mới

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40");
    }

    public void showRegister(boolean show) {
        if (show) {
            fgpwd.setVisible(true);
            login.setVisible(false);
        } else {
            fgpwd.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        fgpwd = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        fgpwd.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout fgpwdLayout = new javax.swing.GroupLayout(fgpwd);
        fgpwd.setLayout(fgpwdLayout);
        fgpwdLayout.setHorizontalGroup(
            fgpwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        fgpwdLayout.setVerticalGroup(
            fgpwdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(fgpwd, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fgpwd;
    private javax.swing.JPanel login;
    // End of variables declaration//GEN-END:variables
}
