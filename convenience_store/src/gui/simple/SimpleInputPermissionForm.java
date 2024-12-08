package gui.simple;

import dao.daochucvu; // DAO để load danh sách chức vụ
import dao.daochucnang; // DAO để load danh sách chức năng
import dao.daophanquyen; // DAO để lưu thông tin phân quyền
import dto.dtophanquyen; // DTO cho phân quyền
import dto.dtochucnang; // DTO cho chức năng
import dto.dtochucvu; // DTO cho chức vụ
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleInputPermissionForm extends JPanel {
    public JComboBox<dtochucvu> cboChucVu; // ComboBox cho chức vụ
    public JPanel pnlChucNang; // Panel chứa các checkbox chức năng
    private daochucvu daoChucVu; // DAO cho chức vụ
    private daochucnang daoChucNang; // DAO cho chức năng
    private daophanquyen daoPhanQuyen; // DAO cho phân quyền

    public SimpleInputPermissionForm() throws SQLException {
        daoChucVu = new daochucvu(); // Khởi tạo DAO chức vụ
        daoChucNang = new daochucnang(); // Khởi tạo DAO chức năng
        daoPhanQuyen = new daophanquyen(); // Khởi tạo DAO phân quyền
        init();
    }

    private void init() throws SQLException {
        setLayout(new MigLayout("fillx,wrap,insets 5 35 5 35,width 400", "[fill]", ""));

        cboChucVu = new JComboBox<>();
        pnlChucNang = new JPanel(new MigLayout("wrap 2", "[fill]"));

        populateComboBoxes();
        populateChucNangPanel();

        // Thêm các thành phần vào panel
        createTitle("Thông tin phân quyền");

        // Tên chức vụ
        add(new JLabel("Chọn chức vụ"), "gapy 5 0");
        add(cboChucVu);

        // Danh sách chức năng
        add(new JLabel("Chọn chức năng"), "gapy 5 0");
        add(new JScrollPane(pnlChucNang), "h 200!");

        
        
    }

    private void populateComboBoxes() throws SQLException {
        // Lấy danh sách chức vụ từ DAO và thêm vào ComboBox
        List<dtochucvu> chucVuList = daoChucVu.getlist();
        for (dtochucvu chucVu : chucVuList) {
            cboChucVu.addItem(chucVu);
        }

        // Hiển thị chức vụ trong ComboBox
        cboChucVu.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel();
            if (value != null) {
                label.setText(value.getDropdownDisplay()); // Hiển thị tên chức vụ
            }
            return label;
        });
    }

    private void populateChucNangPanel() throws SQLException {
        // Lấy danh sách chức năng từ DAO và thêm checkbox vào panel
        List<dtochucnang> chucNangList = daoChucNang.getList();
        pnlChucNang.removeAll();
        for (dtochucnang chucNang : chucNangList) {
            JCheckBox chk = new JCheckBox(chucNang.getTenChucNang());
            chk.putClientProperty("maChucNang", chucNang.getMaChucNang()); // Gắn mã chức năng vào thuộc tính
            pnlChucNang.add(chk);
        }
        pnlChucNang.revalidate();
        pnlChucNang.repaint();
    }

    private void createTitle(String title) {
        JLabel lb = new JLabel(title);
        lb.putClientProperty(FlatClientProperties.STYLE, "font:+2");
        add(lb, "gapy 5 0");
        add(new JSeparator(), "height 2!,gapy 0 0");
    }
    public void updatePermission(int maChucVu) {
        try {
            // Lấy danh sách chức năng đã chọn
            List<dtophanquyen> permissions = new ArrayList<>();
            for (int i = 0; i < pnlChucNang.getComponentCount(); i++) {
                JCheckBox chk = (JCheckBox) pnlChucNang.getComponent(i);
                if (chk.isSelected()) {
                    int maChucNang = (int) chk.getClientProperty("maChucNang");
                    permissions.add(new dtophanquyen(0, maChucVu, maChucNang));
                }
            }

            // Xóa các quyền cũ và thêm quyền mới vào cơ sở dữ liệu
            daoPhanQuyen.deleteByChucVu(maChucVu); // Xóa tất cả quyền hiện có
            for (dtophanquyen permission : permissions) {
                daoPhanQuyen.add(permission); // Thêm quyền mới
            }

            JOptionPane.showMessageDialog(this, "Phân quyền đã được cập nhật thành công!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật phân quyền: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void savePermissions() {
        try {
            // Lấy chức vụ đã chọn
            dtochucvu selectedChucVu = (dtochucvu) cboChucVu.getSelectedItem();
            if (selectedChucVu == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ!");
                return;
            }

            int maChucVu = selectedChucVu.getMachucvu();

            // Lấy danh sách chức năng đã chọn
            List<dtophanquyen> permissions = new ArrayList<>();
            for (int i = 0; i < pnlChucNang.getComponentCount(); i++) {
                JCheckBox chk = (JCheckBox) pnlChucNang.getComponent(i);
                if (chk.isSelected()) {
                    int maChucNang = (int) chk.getClientProperty("maChucNang");
                    permissions.add(new dtophanquyen(0, maChucVu, maChucNang)); // Tạo đối tượng phân quyền
                }
            }

            // Xóa các quyền cũ và thêm quyền mới vào cơ sở dữ liệu
            daoPhanQuyen.deleteByChucVu(maChucVu); // Giả sử có hàm này để xóa quyền cũ
            for (dtophanquyen permission : permissions) {
                daoPhanQuyen.add(permission);
            }

            JOptionPane.showMessageDialog(this, "Phân quyền đã được lưu thành công!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Phân quyền chức năng");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                frame.setContentPane(new SimpleInputPermissionForm());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
