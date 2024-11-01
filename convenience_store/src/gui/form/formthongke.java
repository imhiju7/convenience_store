
package gui.form;



import bus.bushoadon;
import bus.busphieunhap;
import bus.busthongke;
import dto.thongke.ThongKeTungNgayTrongThangDTO;
import gui.model.ModelCard;
import gui.comp.chart.curveChart.CurveChart;
import gui.comp.chart.curveChart.ModelChart2;
import gui.comp.TableSorter;
import gui.swing.icon.GoogleMaterialDesignIcons;
import gui.swing.icon.IconFontSwing;
import helper.Formater;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author AD
 */
public class formthongke extends javax.swing.JPanel {
    private gui.comp.Card card1;
    private gui.comp.Card card2;
    private gui.comp.Card card3;
    private gui.comp.Card card4;   
    private JPanel jp_top, jp_center, pnlChart;
    private CurveChart chart;
    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;
    private double tongDoanhThu;
    private double tongChiphi;
    private double tongLuongNV;
    private double tongLoiNhuan;
    ArrayList<ThongKeTungNgayTrongThangDTO> dataset;
    bushoadon bushd;
    busthongke bustk;
    busphieunhap buspn;
    public formthongke() {
        this.bustk = new busthongke();
        this.bushd = new bushoadon();
        this.buspn = new busphieunhap();
        this.dataset = bustk.getThongKe8NgayGanNhat();
        this.tongDoanhThu = bushd.getTongDoanhThu();
        this.tongChiphi = buspn.getTongChiPhi();
        this.tongLoiNhuan = this.tongDoanhThu - this.tongChiphi;
        init();     
        setOpaque(false);
        initData();
        loadDataTable();
    }
     private void initData() {
        initCardData();
    }
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
    Image img = icon.getImage();
    Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
    }
   private void initCardData() {
        card1.setData(new ModelCard("Tổng Doanh Thu",this.tongDoanhThu, 20, resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/income.png")), 50, 50)));
        card2.setData(new ModelCard("Tổng Chi Phí", this.tongChiphi, 60, resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/low-price.png")), 50, 50)));
        card3.setData(new ModelCard("Lương Nhân Viên", this.tongLuongNV, 80, resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/pay.png")), 50, 50)));
        card4.setData(new ModelCard("Tổng Lợi Nhuận", this.tongLoiNhuan, 95, resizeIcon(new ImageIcon(getClass().getResource("/source/image/icon/profit.png")), 50, 50)));
    }
   private void loadDataChart(){
       for (ThongKeTungNgayTrongThangDTO i : dataset) {
            chart.addData(new ModelChart2(i.getNgay() + "", new double[]{i.getChiphi(), i.getDoanhthu(), i.getLoinhuan()}));
        }
   }
   private void loadDataTable(){
       tblModel.setRowCount(0);
        for (ThongKeTungNgayTrongThangDTO i : dataset) {
            tblModel.addRow(new Object[]{
                i.getNgay(), Formater.FormatVND(i.getChiphi()), Formater.FormatVND(i.getDoanhthu()), Formater.FormatVND(i.getLoinhuan())
            });
        }
   }

    
 private void init() {
    this.setLayout(new BorderLayout(10, 10));
    this.setOpaque(false);
    this.setBorder(new EmptyBorder(10, 10, 10, 10));

    // Panel cho các card (jp_top)
    jp_top = new JPanel(new GridLayout(1, 4, 10, 0));
    jp_top.setOpaque(false);
    jp_top.setBorder(new EmptyBorder(10, 10, 10, 10));
    jp_top.setPreferredSize(new Dimension(0, 120));
    
    card1 = new gui.comp.Card();
    card2 = new gui.comp.Card();
    card3 = new gui.comp.Card();
    card4 = new gui.comp.Card();

    card1.setColorGradient(new java.awt.Color(211, 28, 215));

    card2.setBackground(new java.awt.Color(10, 30, 214));
    card2.setColorGradient(new java.awt.Color(72, 111, 252));

    card3.setBackground(new java.awt.Color(194, 85, 1));
    card3.setColorGradient(new java.awt.Color(255, 212, 99));

    card4.setBackground(new java.awt.Color(60, 195, 0));
    card4.setColorGradient(new java.awt.Color(208, 255, 90));

    jp_top.add(card1);
    jp_top.add(card2);
    jp_top.add(card3);
    jp_top.add(card4);

     // Panel cho biểu đồ (chart)
        jp_center = new JPanel(new BorderLayout());
        jp_center.setBackground(Color.WHITE);

        // Tạo nhãn tiêu đề cho biểu đồ
        JPanel jp_center_top = new JPanel(new FlowLayout());
        jp_center_top.setBorder(new EmptyBorder(10, 0, 0, 10));
        jp_center_top.setOpaque(false);
        JLabel txtChartName = new JLabel("Thống kê doanh thu 8 ngày gần nhất");
        txtChartName.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        jp_center_top.add(txtChartName);

        // Tạo biểu đồ
        pnlChart = new JPanel();
        pnlChart.setOpaque(false);
        pnlChart.setLayout(new BorderLayout(0, 0));
        chart = new CurveChart();
        chart.addLegend("Chi phí", new Color(249, 84, 84), new Color(198, 46, 46));
        chart.addLegend("Doanh thu", new Color(13, 146, 244), new Color(119, 205, 255));  
        chart.addLegend("Lợi nhuận", new Color(114, 191, 120), new Color(160, 214, 131));
        loadDataChart();
        chart.start();
        pnlChart.add(chart, BorderLayout.CENTER);

        // Thêm tiêu đề và biểu đồ vào panel trung tâm
        jp_center.add(jp_center_top, BorderLayout.NORTH);
        jp_center.add(pnlChart, BorderLayout.CENTER);

        // Tạo bảng (table) cho dữ liệu
        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Ngày", "Chi phí", "Doanh thu", "Lợi nhuận"};
        tblModel.setColumnIdentifiers(header);
        tableThongKe.setModel(tblModel);
        tableThongKe.setAutoCreateRowSorter(true);
        tableThongKe.setDefaultEditor(Object.class, null);
        scrollTableThongKe.setViewportView(tableThongKe);

        // Định dạng căn giữa cho các cột trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableThongKe.setDefaultRenderer(Object.class, centerRenderer);
        tableThongKe.setFocusable(false);
        tableThongKe.setDragEnabled(false);
        tableThongKe.getTableHeader().setReorderingAllowed(false);
        scrollTableThongKe.setPreferredSize(new Dimension(0, 250));

        // Thiết lập sorter cho các cột của bảng
        TableSorter.configureTableColumnSorter(tableThongKe, 0, TableSorter.DATE_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 1, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 2, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 3, TableSorter.VND_CURRENCY_COMPARATOR);

        // Thêm các thành phần chính (jp_top, chart và table) vào JPanel chính
        this.add(jp_top, BorderLayout.NORTH);
        this.add(jp_center, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);
        
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
