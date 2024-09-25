package gui.comp;

import bus.bustaikhoan;
import gui.form.frmresetpwd;
import gui.swing.login.Button;
import gui.swing.login.MyPasswordField;
import gui.swing.login.MyTextField;
import helper.SendEmailSMTP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {
    private MyTextField txtUsername;
    private MyTextField txtUser;
    private MyTextField txtEmail;
    private MyPasswordField txtPass;
    private bustaikhoan bustk;
    private MyPasswordField txtCode;
    private String OTP;
    private String tenNV;
    private boolean isValidEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    return email != null && email.matches(emailRegex); // Kiểm tra khớp với biểu thức chính quy
}
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
        txtUser = new MyTextField();
        txtUser.setPrefixIcon(resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/user.png")), 20, 20));
        txtUser.setHint("Username");
        txtUser.setBounds(55, 0, 336, 40); // Xác định vị trí và kích thước cho txtPass
        userPanel.add(txtUser, "w 100%");
        JButton btnSearchEmail = new JButton();
        btnSearchEmail.setBackground(new Color(255,255,255));
        btnSearchEmail.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/find.png")), 20, 20)); // NOI18N
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
          String user = txtUser.getText();
          bustk = new bustaikhoan();
          String email = bustk.getemail(user);
                if (user.equals("")) {
                      JOptionPane.showMessageDialog(null, "Hãy nhập tên đăng nhập để tìm email!");
                }
                else if(email.equals("")) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy email!");
               }
                else txtEmail.setText(email);
                      
          }            
        });
        txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/mail.png")), 20, 20));
        txtEmail.setHint("Email");
        fgpwd.add(txtEmail, "w 60%");
        txtCode = new MyPasswordField();
        txtCode.setEchoChar('●');
        txtCode.setPrefixIcon(resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/verify.png")), 20, 20));
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
        sendCode.addActionListener(new ActionListener() {
        private Timer timer;
        private int countdown;

        @Override
        public void actionPerformed(ActionEvent e) {
            bustaikhoan bustk = new bustaikhoan();            
            String email = txtEmail.getText();
             tenNV = bustk.gettennv(email);
            if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "Hãy nhập email!");
            }
            else if(!isValidEmail(email)) {
                JOptionPane.showMessageDialog(null, "Email không hợp lệ!");
            }
            else if (!bustk.checkemailexist(email)) {
            JOptionPane.showMessageDialog(null, "Email không tồn tại trong hệ thống!");
             }
            else {
            OTP = SendEmailSMTP.getOTP();
                try {
                    SendEmailSMTP.sendOTP(tenNV, email, OTP);
                } catch (UnsupportedEncodingException ex) {
                    JOptionPane.showMessageDialog(null, "Có lỗi trong quá trình gửi mã xác nhận lên email!");;
                }
            sendCode.setEnabled(false);
            countdown = 30;
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    countdown--;
                    if (countdown <= 0) {
                        sendCode.setEnabled(true); 
                        sendCode.setText("SEND CODE"); 
                        timer.stop(); // Dừng bộ đếm
                    } else {
                        sendCode.setText("Resend Code (" + countdown + "s)");      
                    }
                }
            });
            timer.start(); // Bắt đầu đếm ngược
            JOptionPane.showMessageDialog(null, "Gửi mã xác nhận thành công!");
        }
        }
    });
        verify.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String enteredCode = txtCode.getText();
        String expectedCode = OTP;
        if (txtUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Hãy nhập tên đăng nhập!");
        }
        else if (enteredCode.equals("")) {
            JOptionPane.showMessageDialog(null, "Hãy nhập mã xác minh!");
        } else if (!enteredCode.equals(expectedCode)) {
            JOptionPane.showMessageDialog(null, "Mã xác minh không chính xác!");
        } else {
            // Nếu mã xác minh đúng
            JOptionPane.showMessageDialog(null, "Xác minh thành công!");
            OTP = SendEmailSMTP.getOTP();
            frmresetpwd resetDialog = new frmresetpwd(null,txtUser.getText());
            resetDialog.setVisible(true);
        }
    }
});
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);

        txtUsername = new MyTextField();
        txtUsername.setPrefixIcon(resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/user.png")), 20, 20));
        txtUsername.setHint("Username");
        login.add(txtUsername, "w 60%");
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(null); 
        passwordPanel.setPreferredSize(new Dimension(500, 40)); 
        txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/pass.png")), 20, 20));
        txtPass.setHint("Password");
        txtPass.setEchoChar('●');
        txtPass.setBounds(55, 0, 336, 35); 
        passwordPanel.add(txtPass, "w 100%");
        txtPass.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            dangNhap(); // Gọi hàm đăng nhập khi nhấn Enter
        }
    }
});
        // Thêm nút Show Password
        JButton btnShow = new JButton();
        btnShow.setForeground(new Color(100, 100, 100));
        btnShow.setBorder(null);
        btnShow.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/eye.png")), 20, 20));
        btnShow.setFont(new Font("sansserif", 1, 12));
        btnShow.setContentAreaFilled(false);
        btnShow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnShow.setBounds(405, 0, 30, 30); 
        passwordPanel.add(btnShow, "w 20%, h 40!");
        passwordPanel.setBackground(new Color(255,255,255));
        login.add(passwordPanel, "w 80%"); 

        btnShow.addActionListener(new ActionListener() {
        boolean isPasswordVisible = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isPasswordVisible) {
                txtPass.setEchoChar('●'); 
                btnShow.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/eye.png")), 20, 20));
            } else {
                txtPass.setEchoChar((char) 0); 
                btnShow.setIcon(resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/hidden.png")), 20, 20));

            }
            isPasswordVisible = !isPasswordVisible;
        }
    });
        JCheckBox btnReme = new JCheckBox("Stay Logged In");
        btnReme.setBackground(new Color(255,255,255));
        login.add(btnReme, " wrap, w 60%"); 

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40");
        
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangNhap();
            }
        });
    }
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
    Image img = icon.getImage();
    Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
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
    
    public void dangNhap() {
        String tendangnhap = txtUsername.getText();
        String matkhau = String.valueOf(txtPass.getPassword());
        if (tendangnhap.equals("") || matkhau.equals("")) {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không được để trống!");
            return;
        }
        if (tendangnhap.length() < 6 ) {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập tối thiểu 6 kí tự!");
            return;
        }
        if(matkhau.length() < 8){
            JOptionPane.showMessageDialog(null, "Mật khẩu tối thiểu 8 kí tự!");
            return;
        }
        if(!bustk.checktendangnhap(tendangnhap)){
            JOptionPane.showMessageDialog(null, "Không tìm thấy tên đăng nhập!");
            return;
        }
        if(!bustk.checkmatkhau(tendangnhap, matkhau)){
            JOptionPane.showMessageDialog(null, "Sai mật khẩu!");
            return;
        }
        if(!bustk.checktaikhoanbikhoa(tendangnhap)){
            JOptionPane.showMessageDialog(null, "Tài khoản của bạn bị khóa!");
            return;
        }
        int manv = bustk.getmanhanvien(tendangnhap);
        /*
        new guimain().setVisible(true);
        this.setVisible(false);
        */
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
