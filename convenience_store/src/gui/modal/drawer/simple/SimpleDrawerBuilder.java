package gui.modal.drawer.simple;

import com.formdev.flatlaf.FlatClientProperties;
import gui.modal.drawer.DrawerBuilder;
import gui.modal.drawer.DrawerPanel;
import gui.modal.drawer.menu.DrawerMenu;
import gui.modal.drawer.menu.MenuOption;
import gui.modal.drawer.simple.footer.SimpleFooter;
import gui.modal.drawer.simple.footer.SimpleFooterData;
import gui.modal.drawer.simple.header.SimpleHeader;
import gui.modal.drawer.simple.header.SimpleHeaderData;
import gui.modal.option.Location;
import gui.modal.option.Option;
import gui.modal.utils.FlatLafStyleUtils;

import javax.swing.*;
import java.awt.*;

/**
 * @author Raven
 */
public abstract class SimpleDrawerBuilder implements DrawerBuilder {

    protected SimpleHeader header;
    protected JSeparator headerSeparator;
    protected JScrollPane menuScroll;
    protected DrawerMenu menu;
    protected SimpleFooter footer;
    protected Option option;

    public SimpleDrawerBuilder() {
        init();
    }

    private void init() {
        header = new SimpleHeader(getSimpleHeaderData());
        headerSeparator = new JSeparator();
        MenuOption simpleMenuOption = getSimpleMenuOption();
        menu = new DrawerMenu(simpleMenuOption);
        menuScroll = createScroll(menu);
        footer = new SimpleFooter(getSimpleFooterData());
        option = new Option()
                .setRound(0)
                .setDuration(500);
        option.getLayoutOption()
                .setMargin(0)
                .setSize(getDrawerWidth(), 1f)
                .setAnimateDistance(-0.7f, 01)
                .setLocation(Location.LEADING, Location.TOP);
    }

    protected JScrollPane createScroll(JComponent component) {
        JScrollPane scroll = new JScrollPane(component);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        String background = FlatLafStyleUtils.getStyleValue(component, "background", "null");
        scroll.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:" + background);
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getHorizontalScrollBar().setUnitIncrement(10);
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "width:9;" +
                "trackArc:999;" +
                "thumbInsets:0,3,0,3;" +
                "trackInsets:0,3,0,3;" +
                "background:" + background);
        if (!background.equals("null")) {
            FlatLafStyleUtils.appendStyleIfAbsent(scroll.getVerticalScrollBar(), "" +
                    "track:" + background);
        }
        scroll.setBorder(BorderFactory.createEmptyBorder());
        return scroll;
    }

    @Override
    public Component getHeader() {
        return header;
    }

    @Override
    public Component getHeaderSeparator() {
        return headerSeparator;
    }

    @Override
    public Component getMenu() {
        return menuScroll;
    }

    @Override
    public Component getFooter() {
        return footer;
    }

    @Override
    public Option getOption() {
        return option;
    }

    @Override
    public int getDrawerWidth() {
        return -1;
    }

    @Override
    public int getOpenDrawerAt() {
        return -1;
    }

    @Override
    public boolean openDrawerAtScale() {
        return true;
    }

    public void build(DrawerPanel drawerPanel) {
    }

    public void rebuildMenu() {
        if (menu != null) {
            menu.rebuildMenu();
        }
    }

    public DrawerMenu getDrawerMenu() {
        return menu;
    }

    public abstract MenuOption getSimpleMenuOption();

    public abstract SimpleHeaderData getSimpleHeaderData();

    public abstract SimpleFooterData getSimpleFooterData();
}
