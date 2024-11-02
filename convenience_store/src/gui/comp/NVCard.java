package gui.comp;

import com.formdev.flatlaf.FlatClientProperties;
import dto.SampleData;
import dto.dtonhanvien;
import net.miginfocom.swing.MigLayout;
import helper.AvatarIcon;
import gui.model.ModelEmployee;

import javax.swing.*;
import java.util.function.Consumer;

public class NVCard extends JPanel {


    private boolean isSelected = false;
    
    private dtonhanvien nv;
    private Consumer<dtonhanvien> nv_event;
    private int s;
    
//    public NVCard(ModelEmployee employee, Consumer<ModelEmployee> event) {
//        this.employee = employee;
//        this.event = event;
//        init();
//    }
    public NVCard(dtonhanvien nv, Consumer<dtonhanvien> event , int s) {
        this.nv = nv;
        this.nv_event = event;
        this.s = s;
        init();
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:30;" +
                "[light]background:darken($Panel.background,3%);" +
                "[dark]background:lighten($Panel.background,3%);");

        setLayout(new MigLayout("", "", "fill"));
        // create panel header
        panelHeader = createHeader1();

        // create panel body
        panelBody = createBody1();

        add(panelHeader);
        add(panelBody);
        addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            isSelected = !isSelected;
            updateCardStyle();
        }
    });
    }
     private void updateCardStyle() {
        if (isSelected) {
            putClientProperty(FlatClientProperties.STYLE, "" +
                    "arc:30;" +
                    "[light]background:darken($Panel.background,50%);" +
                    "[dark]background:lighten($Panel.background,50%);");
        } else {
            putClientProperty(FlatClientProperties.STYLE, "" +
                    "arc:30;" +
                    "[light]background:darken($Panel.background,3%);" +
                    "[dark]background:lighten($Panel.background,3%);");
        }
        revalidate();
        repaint();
    }
    //Để lấy làm icon
    private Icon getProfileIcon1(String name, boolean defaultIcon) {
        if (defaultIcon) {
            System.out.println(name);
            return new ImageIcon(getClass().getResource("/source/image/NhanVien/" + name));
        } else {
            AvatarIcon avatarIcon = new AvatarIcon(getClass().getResource("/src/source/image/NhanVien/" + name), 55, 55, 3f);
            avatarIcon.setType(AvatarIcon.Type.MASK_SQUIRCLE);
            return avatarIcon;
        }
    }
    
    //Đây là chỗ của nhân viên
    private JPanel createHeader1() {
        JPanel header = new JPanel(new MigLayout("fill,insets 0", "[fill]", "[top]"));
        header.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JLabel label = new JLabel(new AvatarIcon(getProfileIcon1(nv.getImg(), true), 130, 130, 20));
        header.add(label);
        return header;
    }

    
    //Phần body của nv
    private JPanel createBody1() {
        JPanel body = new JPanel(new MigLayout("wrap", "[150]", "[][]push[]"));
        body.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JLabel title = new JLabel(nv.getTennhanvien());
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +1;");
        JTextPane description = new JTextPane();
        description.setEditable(false);
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;" +
                "[light]foreground:tint($Label.foreground,30%);" +
                "[dark]foreground:shade($Label.foreground,30%)");
        description.setText(nv.getDiachi());

        JButton button = new JButton("View");
        button.addActionListener(e -> nv_event.accept(nv));
        button.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:999;" +
                "margin:3,25,3,25;" +
                "borderWidth:1;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null;");
        
        body.add(title);
        body.add(description);
        body.add(button);
        return body;
    }
    public void setSelected(boolean selected) {
        this.isSelected = selected;
        updateCardStyle();
    }
    public boolean isSelected() {
        return this.isSelected;
    }
    public String getEmployeeName() {
        return nv.getTennhanvien();
    }
    public Integer getMaNhanVien() {
        return nv.getManhanvien();
    }
    private JPanel panelHeader;
    private JPanel panelBody;
}
