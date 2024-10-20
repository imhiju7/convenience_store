package gui.form;

import gui.comp.SPCard;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import gui.layout.ResponsiveLayout;
import gui.model.ModelEmployee;
import dto.SampleData;
import gui.simple.SimpleInputForms;
import gui.swing.dashboard.Form;
import gui.swing.dashboard.SystemForm;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.function.Consumer;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;
import raven.modal.option.Location;
import raven.modal.option.Option;


@SystemForm(name = "Responsive Layout", description = "responsive layout user interface", tags = {"card"})
public class formsanpham extends Form {

    public formsanpham() {
        init();
        formInit();
        
    }

    private void init() {
        cards = new ArrayList<>();
        setLayout(new MigLayout("wrap,fill,insets 7 15 7 15", "[fill]", "[grow 0][fill]"));
        add(createHeaderAction());
        add(createExample());
       
    }

    @Override
    public void formInit() {
        // add sample data
        panelCard.removeAll();
        for (ModelEmployee employee : SampleData.getSampleEmployeeData(true)) {
            SPCard card = new SPCard(employee, createEventCard());
            cards.add(card);
            panelCard.add(card);
        }
        panelCard.repaint();
        panelCard.revalidate();
    }

    private Consumer<ModelEmployee> createEventCard() {
        return e -> {
              JOptionPane.showMessageDialog(this,"Đã thêm "+e.getProfile().getName()+" vào giỏ hàng");
        };
    }


     private Component createHeaderAction() {
        JPanel panel = new JPanel(new MigLayout("insets 5 20 5 20", "[fill,230]push[][]"));

        JTextField txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm kiếm...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/search.png")), 20, 20));
        JCheckBox selectAllCheckbox = new JCheckBox("Chọn tất cả");
        selectAllCheckbox.addActionListener(e -> selectAll(selectAllCheckbox.isSelected()));
        JButton cmdAddCart = new JButton("Thêm vào giỏ hàng");

        JButton cmdViewCart = new JButton("Xem giỏ hàng");
        cmdViewCart.addActionListener(e -> {
            viewCart();           
        });
        cmdAddCart.addActionListener(e -> {               
                if (selectAllCheckbox.isSelected()) {
                selectAllCheckbox.setSelected(false);
            }
                addCart();
        });
        panel.add(txtSearch);
        panel.add(selectAllCheckbox);
        panel.add(cmdAddCart);
  
        panel.add(cmdViewCart);

        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;");
        return panel;
    }
     private void selectAll(boolean selected) {
        for (SPCard card : cards) {
            card.setSelected(selected);
        }
    }
     
    private void addCart(){
        List<SPCard> selectedCards = new ArrayList<>();
        StringBuilder addedNames = new StringBuilder("Đã thêm các sản phẩm: ");
        for (SPCard card : cards) {
            if (card.isSelected()) { 
                selectedCards.add(card);
            }
        }

        if (selectedCards.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm nào được chọn để thêm vào giỏ hàng.");
            return;
        }

        for (SPCard card : selectedCards) {
            addedNames.append(card.getEmployeeName()).append(", ");
            card.setSelected(false);
        }
        panelCard.revalidate();
        panelCard.repaint();
        
        
        JOptionPane.showMessageDialog(this, "Đã thêm " + selectedCards.size() + " sản phẩm vào giỏ hàng.");
        if (addedNames.length() > 0) {
        addedNames.setLength(addedNames.length() - 2); // Xóa dấu phẩy và khoảng trắng cuối cùng
    }
        JOptionPane.showMessageDialog(this, addedNames.toString());
    }  
    private void viewCart() {
         JOptionPane.showMessageDialog(this, "Hiển thị giỏ hàng");
    }
    private Component createExample() {
        responsiveLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, new Dimension(-1, -1), 10, 10);
        panelCard = new JPanel(responsiveLayout);
        panelCard.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:10,10,10,10;");
        JScrollPane scrollPane = new JScrollPane(panelCard);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getHorizontalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:$ScrollBar.thumbArc;" +
                "thumbInsets:0,0,0,0;" +
                "width:7;");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:$ScrollBar.thumbArc;" +
                "thumbInsets:0,0,0,0;" +
                "width:7;");
        scrollPane.setBorder(null);
        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(Box.createGlue());
        splitPane.setResizeWeight(1);
         splitPane.setDividerSize(0);
        return splitPane;
    }
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
    Image img = icon.getImage();
    Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
    }
    private List<SPCard> cards;
    private JPanel panelCard;
    private ResponsiveLayout responsiveLayout;
    
       
}
