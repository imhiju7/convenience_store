/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.comp;

/**
 *
 * @author AD
 */
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HintTextArea extends JTextArea {
    private String hint = "Nhập văn bản...";

    public HintTextArea() {
        super();
    }

    // Getter và Setter cho hint
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        repaint(); // Vẽ lại khi hint thay đổi
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Hiển thị hint khi không có nội dung nào
        if (getText().isEmpty() && !isFocusOwner()) {
            g.setColor(Color.GRAY); // Màu của hint
            g.setFont(getFont().deriveFont(Font.ITALIC)); // Kiểu chữ nghiêng cho hint
            g.drawString(hint, getInsets().left + 5, getFontMetrics(getFont()).getHeight() + getInsets().top);
        }
    }
}
