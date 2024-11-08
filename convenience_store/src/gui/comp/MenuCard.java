package gui.comp;

import com.formdev.flatlaf.FlatClientProperties;
import dto.dtosanpham;
import net.miginfocom.swing.MigLayout;
import helper.AvatarIcon;
import gui.model.ModelEmployee;

import javax.swing.*;
import java.util.function.Consumer;

public class MenuCard extends JPanel {

    private dtosanpham sp;
    private final Consumer<dtosanpham> event_sp;
    private boolean isSelected = false;
    private JPanel panelHeader;
    private JPanel panelBody;
    
    
    public MenuCard(dtosanpham sp, Consumer<dtosanpham> event_sp) {
        this.sp = sp;
        this.event_sp = event_sp;
        init();
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:30;" +
                "[light]background:darken($Panel.background,3%);" +
                "[dark]background:lighten($Panel.background,3%);");

        setLayout(new MigLayout("", "", "fill"));
        // create panel header
        panelHeader = createHeader();

        // create panel body
        panelBody = createBody();

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
     
     
     
     
    private Icon getProfileIcon(String name) {
        if(!name.equals("")){
            return new ImageIcon(getClass().getResource("/source/image/sanpham/" + name));
        }else{
            return new ImageIcon(getClass().getResource("/source/image/sanpham/Americano-nong-10oz-01-1-400x400.jpg"));
        }
    }
    
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new MigLayout("fill,insets 0", "[fill]", "[top]"));
        header.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JLabel label = new JLabel(new AvatarIcon(getProfileIcon(sp.getImg()), 130, 130, 20));
        header.add(label);
        return header;
    }

    private JPanel createBody() {
        JPanel body = new JPanel(new MigLayout("wrap", "[150]", "[][]push[]"));
        body.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JLabel title = new JLabel(sp.getTenSanPham());
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +1;");
        JTextPane description = new JTextPane();
        description.setEditable(false);
        description.setEnabled(false);
        description.setText("Giá tiền: " + sp.getGiaBan());
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;" +
                "[light]foreground:tint($Label.foreground,30%);" +
                "[dark]foreground:shade($Label.foreground,30%)");
        JTextPane soluong = new JTextPane();
        soluong.setText("Số lượng tồn kho: " + sp.getSoLuong());
        soluong.setEditable(false);
        soluong.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;" +
                "[light]foreground:tint($Label.foreground,30%);" +
                "[dark]foreground:shade($Label.foreground,30%)");
        JButton button = new JButton("View");
        button.addActionListener(e -> event_sp.accept(sp));
        button.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:999;" +
                "margin:3,25,3,25;" +
                "borderWidth:1;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null;");
        
        body.add(title);
        body.add(description);
        body.add(soluong);
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
    
    public String getSanPhamName() {
        return sp.getTenSanPham();
    }
    
    public Double getGiaTien(){
        return sp.getGiaBan();
    }
    
    public Integer getSoLuong(){
        return sp.getSoLuong();
    }
    
    public int getMaSanPham(){
        return sp.getMaSanPham();
    }
}
