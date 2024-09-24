
package gui.form;

/**
 *
 * @author AD
 */
import bus.bustaikhoan;
import gui.swing.login.MyPasswordField;
import gui.swing.login.Button;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
public class frmresetpwd extends JDialog{
    private MyPasswordField newPasswordField;
    private MyPasswordField confirmPasswordField;
    private Button submitButton;
     public frmresetpwd(Frame parent, String user) {
        super(parent, "Nhập Mật Khẩu Mới", true);
        setLayout(new MigLayout("wrap 1", "[grow, center]", "push[]25[]10[]10[]25[]push")); 
        newPasswordField = new MyPasswordField();
        confirmPasswordField = new MyPasswordField();
        submitButton = new Button();
        submitButton.setText("Đặt Mật Khẩu");
        newPasswordField.setBackground(new Color(204, 255, 229));
        confirmPasswordField.setBackground(new Color(204, 255, 229));
        newPasswordField.setPrefixIcon(new ImageIcon(getClass().getResource("/source/image/icon/pass.png")));
        confirmPasswordField.setPrefixIcon(new ImageIcon(getClass().getResource("/source/image/icon/pass.png")));
        newPasswordField.setEchoChar('●');
        confirmPasswordField.setEchoChar('●');
        getContentPane().setBackground(new Color(255, 255, 255));
        newPasswordField.setHint("Mật khẩu mới");
        add(newPasswordField, "wrap, w 60% ,h 40!"); 
        confirmPasswordField.setHint("Xác nhận mật khẩu");
        add(confirmPasswordField, "wrap ,w 60%, h 40!");
        submitButton.setBackground(new Color(0,102,51));
        submitButton.setForeground(new Color(255,255,255));
        add(submitButton, "w 60%, h 40!, align center");
        submitButton.addActionListener(e -> handleSubmit(user));
        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
     private void handleSubmit(String user) {
         bustaikhoan bustk = new bustaikhoan();
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        if (newPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy nhập mật khẩu mới!");
        } else if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp!");
        } else if (!(newPassword.length()>=8)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu tối thiểu 8 kí tự!");
        } else {   
            bustk.updatematkhau(user,newPassword);
            JOptionPane.showMessageDialog(this, "Mật khẩu đã được cập nhật thành công!");
            dispose();
        }
    }
}
