package gui.modal.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import gui.modal.listener.ModalCallback;
import gui.modal.listener.ModalController;
import gui.modal.option.ModalBorderOption;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * This class contain with title and close button
 * And two action button, ok and close
 *
 * @author Raven
 */
public class SimpleModalBorder extends Modal implements ModalBorderAction {

    private final Component component;
    private final ModalBorderOption option;
    private final String title;
    private final int optionType;
    private Option[] optionsType;
    private final ModalCallback callback;
    private JComponent header;

    // options
    public static final int DEFAULT_OPTION = -1;
    public static final int YES_NO_OPTION = 0;
    public static final int YES_NO_CANCEL_OPTION = 1;
    public static final int OK_CANCEL_OPTION = 2;

    // return options
    public static final int YES_OPTION = 0;
    public static final int NO_OPTION = 1;
    public static final int CANCEL_OPTION = 2;
    public static final int OK_OPTION = 0;
    public static final int CLOSE_OPTION = -1;

    public SimpleModalBorder(Component component, String title) {
        this(component, title, new ModalBorderOption());
    }

    public SimpleModalBorder(Component component, String title, ModalBorderOption option) {
        this(component, title, option, DEFAULT_OPTION, null);
    }

    public SimpleModalBorder(Component component, String title, int optionType, ModalCallback callback) {
        this(component, title, new ModalBorderOption(), optionType, callback);
    }

    public SimpleModalBorder(Component component, String title, Option[] optionsType, ModalCallback callback) {
        this(component, title, new ModalBorderOption(), -1, optionsType, callback);
    }

    public SimpleModalBorder(Component component, String title, ModalBorderOption option, int optionType, ModalCallback callback) {
        this(component, title, option, optionType, null, callback);
    }

    public SimpleModalBorder(Component component, String title, ModalBorderOption option, Option[] optionsType, ModalCallback callback) {
        this(component, title, option, -1, optionsType, callback);
    }

    private SimpleModalBorder(Component component, String title, ModalBorderOption option, int optionType, Option[] optionsType, ModalCallback callback) {
        this.component = component;
        this.option = option;
        this.title = title;
        this.optionType = optionType;
        this.callback = callback;
        if (optionType == -1) {
            this.optionsType = optionsType;
        } else {
            this.optionsType = createOptions(optionType);
        }
    }

    /**
     * This method work when show modal dialog
     * To be able custom new model border with new constructor argument and override the other method
     */
    @Override
    public void installComponent() {
        setLayout(new MigLayout("wrap,fillx,insets 10 0 10 0", "[fill]", "[][fill,grow][]"));
        header = createHeader();
        add(header);
        if (option.isUseScroll()) {
            JScrollPane scrollPane = createContentScroll();
            scrollPane.setViewportView(component);
            add(scrollPane);
        } else {
            add(component);
        }
        Component optionButton = createOptionButton(optionsType);
        if (optionButton != null) {
            add(optionButton);
        }
        setOpaque(false);
    }

    protected JScrollPane createContentScroll() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackInsets:0,3,0,3;" +
                "thumbInsets:0,3,0,3;" +
                "trackArc:$ScrollBar.thumbArc");
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

    protected JComponent createHeader() {
        JPanel panel = new JPanel(new MigLayout("fill,insets 2 35 2 35"));
        panel.add(createTitleComponent(title), "push");
        panel.add(createActionTitleComponent());
        return panel;
    }

    protected JComponent createTitleComponent(String title) {
        JLabel label = new JLabel(title);
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:+4");
        return label;
    }

    protected JComponent createActionTitleComponent() {
        JButton buttonClose = new JButton(new FlatSVGIcon("source/image/icon/close.svg", 0.4f));
        buttonClose.addActionListener(e -> {
            doAction(CLOSE_OPTION);
        });
        buttonClose.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:999;" +
                "margin:5,5,5,5;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null;");
        return buttonClose;
    }

    protected JComponent createOptionButton(Option[] optionsType) {
        if (optionsType == null || optionsType.length == 0) {
            return null;
        }
        JPanel panel = new JPanel(new MigLayout("insets 2 35 2 35,al trailing"));
        for (Option option : optionsType) {
            panel.add(createButtonOption(option));
        }
        return panel;
    }

    protected Option[] createOptions(int optionType) {
        checkOptionType(optionType);
        Option[] options = null;
        if (optionType == YES_NO_OPTION) {
            options = new Option[]{new Option("Yes", YES_OPTION), new Option("No", NO_OPTION)};
        } else if (optionType == YES_NO_CANCEL_OPTION) {
            options = new Option[]{new Option("Yes", YES_OPTION), new Option("No", NO_OPTION), new Option("Cancel", CANCEL_OPTION)};
        } else if (optionType == OK_CANCEL_OPTION) {
            options = new Option[]{new Option("Ok", OK_OPTION), new Option("Cancel", CANCEL_OPTION)};
        }
        return options;
    }

    protected JButton createButtonOption(Option option) {
        JButton button = new JButton(option.text) {
            @Override
            public boolean isDefaultButton() {
                if (option.type == 0) {
                    return true;
                }
                return super.isDefaultButton();
            }
        };
        button.addActionListener(e -> {
            doAction(option.type);
        });
        return button;
    }

    public void createBackButton(Consumer onBack) {
        if (header != null) {
            JButton buttonClose = new JButton(new FlatSVGIcon("gui/modal/icon/back.svg", 0.4f));
            buttonClose.addActionListener(e -> onBack.accept(null));
            buttonClose.putClientProperty(FlatClientProperties.STYLE, "" +
                    "arc:999;" +
                    "margin:5,5,5,5;" +
                    "borderWidth:0;" +
                    "focusWidth:0;" +
                    "innerFocusWidth:0;" +
                    "background:null;");
            header.add(buttonClose, 0);
        }
    }

    private void checkOptionType(int type) {
        if (type != DEFAULT_OPTION && type != YES_NO_OPTION && type != YES_NO_CANCEL_OPTION && type != OK_CANCEL_OPTION) {
            throw new RuntimeException("SimpleModalBorder: option type must be one of" +
                    " SimpleModalBorder.DEFAULT_OPTION," +
                    " SimpleModalBorder.YES_NO_OPTION," +
                    " SimpleModalBorder.YES_NO_CANCEL_OPTION" +
                    " or SimpleModalBorder.OK_CANCEL_OPTION");
        }
    }

    private ModalController createController() {
        return new ModalController(this) {
            @Override
            public void close() {
                getController().getModalContainer().closeModal();
            }
        };
    }

    @Override
    public void doAction(int action) {
        if (callback == null) {
            getController().getModalContainer().closeModal();
        } else {
            ModalController controller = createController();
            callback.action(controller, action);
            if (!controller.getConsume()) {
                getController().getModalContainer().closeModal();
            }
        }
    }

    public static class Option {

        public String getText() {
            return text;
        }

        public int getType() {
            return type;
        }

        private final String text;
        private final int type;

        public Option(String text, int type) {
            this.text = text;
            this.type = type;
        }
    }
}
