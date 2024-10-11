package gui.comp;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import helper.AvatarIcon;
import gui.model.ModelEmployee;

import javax.swing.*;
import java.util.function.Consumer;

public class SPCard extends JPanel {

    private final ModelEmployee employee;
    private final Consumer<ModelEmployee> event;
    private boolean isSelected = false;
    public SPCard(ModelEmployee employee, Consumer<ModelEmployee> event) {
        this.employee = employee;
        this.event = event;
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
    private JPanel createHeader() {
        JPanel header = new JPanel(new MigLayout("fill,insets 0", "[fill]", "[top]"));
        header.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JLabel label = new JLabel(new AvatarIcon(employee.getProfile().getIcon(), 130, 130, 20));
        header.add(label);
        return header;
    }

    private JPanel createBody() {
        JPanel body = new JPanel(new MigLayout("wrap", "[150]", "[][]push[]"));
        body.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JLabel title = new JLabel(employee.getProfile().getName());
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +1;");
        JTextPane description = new JTextPane();
        description.setEditable(false);
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;" +
                "[light]foreground:tint($Label.foreground,30%);" +
                "[dark]foreground:shade($Label.foreground,30%)");
        description.setText(employee.getDescription());

        
        
        
        body.add(title);
        body.add(description);
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
    return employee.getProfile().getName();
}
    private JPanel panelHeader;
    private JPanel panelBody;
}
