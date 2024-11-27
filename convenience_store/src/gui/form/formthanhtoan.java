
package gui.form;

/**
 *
 * @author AD
 */
import bus.*;
import dto.*;
import gui.comp.Combobox;
import gui.comp.TableSorter;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class formthanhtoan extends javax.swing.JFrame {

    /**
     * Creates new form formthanhtoan
     */
    static ArrayList<dtosanpham> list = new ArrayList<>();
    static int manv =0;
    int mnv;
    buskhuyenmai khuyenmai = new buskhuyenmai();
    bushoadon hoadon = new bushoadon();
    bussanpham sanpham = new bussanpham();
    busuudai uudai = new busuudai();
    bustichdiem tichdiem = new bustichdiem();
    buskhachhang khachhang = new buskhachhang();
    busnhanvien nhanvien = new busnhanvien();
    buscthoadon cthoadon = new buscthoadon();
    busctphieunhap ctphieunhap = new busctphieunhap();
    ArrayList<dtocthoadon> limenu;
    
    dtokhachhang kh = new dtokhachhang();
    dtouudai ud = new dtouudai();
    dtokhuyenmai km = new dtokhuyenmai();
    dtotichdiem td = new dtotichdiem();
    dtonhanvien nv = new dtonhanvien();
    dtohoadon hd = new dtohoadon();
    
    double tongtien;
    double tongtienkm;
    double tongtienud;
    double tongtienfi;
    int diem;
    public void arr_jt (JTable jTable, ArrayList<dtocthoadon> spOrder) {
        DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
        int i = 0;
        for (dtocthoadon cthd : spOrder) {
            i++;
            dtosanpham sp = new dtosanpham();
            sp.setMaSanPham(cthd.getMaSanPham());
            sp = sanpham.getsp(sp);
            
            int soluong = cthd.getSoLuong();
            dtoctphieunhap ctpn = ctphieunhap.getspganhh(cthd.getMaSanPham());
            double giaban = ctpn.getGiaBan();
            double tonggia = soluong*giaban;
            model.addRow(new Object[]{Integer.toString(i), sp.getTenSanPham(),Integer.toString(soluong),Double.toString(giaban), Double.toString(tonggia)});
        }
        jTable.setModel(model);
        jTable.revalidate();
        jTable.repaint();
    }
    public void cbimport(JComboBox jcb,ArrayList<dtokhuyenmai> list){
        jcb.removeAllItems();
        jcb.addItem("Khuyến mãi");
        for(dtokhuyenmai i: list){
            jcb.addItem(i.getTenKhuyenMai());
        }
    }
    public formthanhtoan(ArrayList<dtocthoadon> list,int ma_nv) {
<<<<<<< Updated upstream
        System.out.print("dfds");
=======

>>>>>>> Stashed changes
        init();
        // loading
        nv.setManhanvien(ma_nv);
        nv = nhanvien.getnv(nv);

        double total = cthoadon.gettongtien(list);
        discountValue.setText(Double.toString(total));
        
        arr_jt(paymentTable, list);
        cbimport(khuyenMai,khuyenmai.getkhuyenmaitoday());
        tongtien = total;
        totalValue.setText(Double.toString(total));
        
        tongtienfi = total;
        hd.setMaNhanVien(ma_nv);
        hd.setTennhanvien(ma_nv);
        
        limenu =  list;
    }

    public void init() {
        jSplitPane1 = new javax.swing.JSplitPane();
        optionLayoutUtils1 = new gui.modal.layout.OptionLayoutUtils();
        jPanel2 = new javax.swing.JPanel();
        phonejLabel = new javax.swing.JLabel();
        namejLabel = new javax.swing.JLabel();
        phoneTextField = new gui.swing.login.MyTextField();
        nameTextField = new gui.swing.login.MyTextField();
        checkPhoneBtn = new gui.swing.login.Button();
        newCustomerBtn = new gui.swing.login.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        DefaultTableModel model = new DefaultTableModel();
        paymentTable = new JTable(model);
        khuyenMai = new gui.comp.Combobox();
        jScrollPane3 = new javax.swing.JScrollPane();
        note = new gui.comp.HintTextArea();
        discountValue = new gui.swing.login.MyTextField();
        creditPoint = new javax.swing.JLabel();
        sale = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        creditPointValue = new gui.swing.login.MyTextField();
        totalValue = new gui.swing.login.MyTextField();
        saleValue = new gui.swing.login.MyTextField();
        thanhToanBtn = new gui.swing.login.Button();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        phonejLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        phonejLabel.setText("Số điện thoại");

        namejLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        namejLabel.setText("Tên khách hàng");

        phoneTextField.setToolTipText("");
        phoneTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        phoneTextField.setHint("Nhập số điện thoại khách hàng");
        phoneTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTextFieldActionPerformed(evt);
            }
        });

        nameTextField.setToolTipText("");
        nameTextField.setEnabled(false);
        nameTextField.setFocusable(false);
        nameTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nameTextField.setHint("Tên khách hàng");
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        checkPhoneBtn.setBackground(new java.awt.Color(0, 204, 204));
        checkPhoneBtn.setText("Kiểm tra");
        checkPhoneBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        checkPhoneBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPhoneBtnActionPerformed(evt);
            }
        });

        newCustomerBtn.setBackground(new java.awt.Color(0, 204, 204));
        newCustomerBtn.setText("Tạo mới");
        newCustomerBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        newCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCustomerBtnActionPerformed(evt);
            }
        });

        paymentTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableSorter.configureTableColumnSorter(paymentTable, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(paymentTable, 1, TableSorter.STRING_COMPARATOR);
        TableSorter.configureTableColumnSorter(paymentTable, 2, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(paymentTable, 3, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(paymentTable, 4, TableSorter.VND_CURRENCY_COMPARATOR);
        paymentTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(paymentTable);

        khuyenMai.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        khuyenMai.setLabeText("Khuyến mãi");
        khuyenMai.setLineColor(new java.awt.Color(0, 204, 204));
        khuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khuyenMaiActionPerformed(evt);
            }
        });

        note.setColumns(20);
        note.setRows(5);
        note.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        note.setHint("Nhập ghi chú...");
        jScrollPane3.setViewportView(note);

        discountValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discountValue.setToolTipText("");
        discountValue.setEnabled(false);
        discountValue.setFocusable(false);
        discountValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        discountValue.setHint("0");
        discountValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountValueActionPerformed(evt);
            }
        });

        creditPoint.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        creditPoint.setText("Điểm tích luỹ");

        sale.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sale.setText("Ưu đãi");

        total.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        total.setText("Tổng tiền");

        creditPointValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        creditPointValue.setToolTipText("");
        creditPointValue.setEnabled(false);
        creditPointValue.setFocusable(false);
        creditPointValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        creditPointValue.setHint("0");
        creditPointValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditPointValueActionPerformed(evt);
            }
        });

        totalValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalValue.setToolTipText("");
        totalValue.setEnabled(false);
        totalValue.setFocusable(false);
        totalValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        totalValue.setHint("0");
        totalValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalValueActionPerformed(evt);
            }
        });

        saleValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saleValue.setToolTipText("");
        saleValue.setEnabled(false);
        saleValue.setFocusable(false);
        saleValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        saleValue.setHint("0");
        saleValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleValueActionPerformed(evt);
            }
        });

        thanhToanBtn.setBackground(new java.awt.Color(0, 204, 204));
        thanhToanBtn.setText("Thanh toán");
        thanhToanBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        thanhToanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thanhToanBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thanhToanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(phonejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(namejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkPhoneBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(newCustomerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(khuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(discountValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(creditPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(sale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(creditPointValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saleValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phonejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkPhoneBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newCustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(khuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(discountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(sale, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(creditPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(saleValue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(creditPointValue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(thanhToanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );  
    }
    double bHeight = 0.0;
    
    public PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();
        
        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 5.0;
        double width = cm_to_pp(14.5);
        double height = cm_to_pp(14.8);
        paper.setSize(width, height);
        paper.setImageableArea(0, 5, width, height);
        
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);
        return pf;
    }
    
    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }
    
    protected static double toPPI(double inch) {
        return inch* 72d;
    }
    
    public class BillPrintable implements Printable {
           
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int r = 10;
            int result = NO_SUCH_PAGE;
            if(pageIndex == 0) {
                Graphics2D g2d = (Graphics2D) graphics;
                double width = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());
                
                try{
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    int a = 125;
                    int stt=0;
                    g2d.setFont(new Font("Times New Roman",Font.PLAIN,9));
//                    g2d.drawImage(s);
                    g2d.drawString("-------------------------------------------------------------", a, y);y+=yShift;
                    g2d.drawString("            CỬA HÀNG TIỆN LỢI Mimi                           ", a, y);y+=yShift;
                    g2d.drawString("       ĐỊA CHỈ: xxxxxxxxxxxxxxxxxx                          ", a, y);y+=yShift;
                    g2d.drawString("       SĐT: xxxxxxxxxxxxxxxxxx                              ", a, y);y+=yShift;
                    g2d.drawString("       Ngày mua: "+hd.getNgayMua()+"                         ", a, y);y+=yShift;
                    g2d.drawString("       Mã hóa đơn: "+hd.getMaHoaDon()+"                         ", a, y);y+=yShift;
                    g2d.drawString("       Tên Nhân viên: "+nv.getTennhanvien()+"                       ", a, y);y+=yShift;
                    g2d.drawString("       Tên khách hàng: "+kh.getTenKhachHang()+"                  ", a, y);y+=yShift;
                    g2d.drawString("-------------------------------------------------------------", a, y);y+=headerRectHeight;
                    
                    g2d.drawString("   Tên sản phẩm                              Tổng tiền(VND)   ", a, y);y+=yShift;
                    g2d.drawString("-------------------------------------------------------------", a, y);y+=headerRectHeight;

                    for (dtocthoadon i : limenu) {
                        stt++;
                        dtoctphieunhap ctpn = ctphieunhap.getspganhh(i.getMaSanPham());
                        g2d.drawString("    "+stt+" "+i.getTensanpham()+"                                        ", a, y);y+=headerRectHeight;
                        g2d.drawString("        "+ctpn.getGiaBan()+"      *"+i.getSoLuong()+"                            "+i.getSoLuong()*ctpn.getGiaBan(), a, y);y+=yShift;
                        
                    }
                    g2d.drawString("-------------------------------------------------------------", a, y);y+=yShift;
                    g2d.drawString("    Tổng tiền mua:                                     "+tongtien, a, y);y+=yShift;
                    g2d.drawString("-------------------------------------------------------------", a, y);y+=yShift;
                    g2d.drawString("    Áp dụng Khuyến mãi:                                -"+tongtienkm, a, y);y+=yShift;
                    g2d.drawString("-------------------------------------------------------------", a, y);y+=yShift;
                    g2d.drawString("    Ưu đãi khách hàng:                                   -"+tongtienud, a, y);y+=yShift;
                    g2d.drawString("-------------------------------------------------------------", a, y);y+=yShift;
                    g2d.drawString("    Thành tiền:                                           "+tongtienfi, a, y);y+=yShift;
                    
                    g2d.drawString("*******************************************", a, y);y+=yShift;
                    g2d.drawString("                       CẢM ƠN QUÝ KHÁCH                      ", a, y);y+=yShift;
                    g2d.drawString("*******************************************", a, y);y+=yShift;
                    
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                result = PAGE_EXISTS;
            }
            return result;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        optionLayoutUtils1 = new gui.modal.layout.OptionLayoutUtils();
        jPanel2 = new javax.swing.JPanel();
        phonejLabel = new javax.swing.JLabel();
        namejLabel = new javax.swing.JLabel();
        phoneTextField = new gui.swing.login.MyTextField();
        nameTextField = new gui.swing.login.MyTextField();
        checkPhoneBtn = new gui.swing.login.Button();
        newCustomerBtn = new gui.swing.login.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        khuyenMai = new gui.comp.Combobox();
        jScrollPane3 = new javax.swing.JScrollPane();
        note = new gui.comp.HintTextArea();
        discountValue = new gui.swing.login.MyTextField();
        creditPoint = new javax.swing.JLabel();
        sale = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        creditPointValue = new gui.swing.login.MyTextField();
        totalValue = new gui.swing.login.MyTextField();
        saleValue = new gui.swing.login.MyTextField();
        thanhToanBtn = new gui.swing.login.Button();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        phonejLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        phonejLabel.setText("Số điện thoại");

        namejLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        namejLabel.setText("Tên khách hàng");

        phoneTextField.setToolTipText("");
        phoneTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        phoneTextField.setHint("Nhập số điện thoại khách hàng");
        phoneTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTextFieldActionPerformed(evt);
            }
        });

        nameTextField.setToolTipText("");
        nameTextField.setEnabled(false);
        nameTextField.setFocusable(false);
        nameTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nameTextField.setHint("Tên khách hàng");
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        checkPhoneBtn.setBackground(new java.awt.Color(0, 204, 204));
        checkPhoneBtn.setText("Kiểm tra");
        checkPhoneBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        checkPhoneBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPhoneBtnActionPerformed(evt);
            }
        });

        newCustomerBtn.setBackground(new java.awt.Color(0, 204, 204));
        newCustomerBtn.setText("Tạo mới");
        newCustomerBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        newCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCustomerBtnActionPerformed(evt);
            }
        });

        paymentTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(paymentTable);
        if (paymentTable.getColumnModel().getColumnCount() > 0) {
            paymentTable.getColumnModel().getColumn(0).setResizable(false);
            paymentTable.getColumnModel().getColumn(1).setResizable(false);
            paymentTable.getColumnModel().getColumn(2).setResizable(false);
            paymentTable.getColumnModel().getColumn(3).setResizable(false);
            paymentTable.getColumnModel().getColumn(4).setResizable(false);
        }

        khuyenMai.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        khuyenMai.setLabeText("Khuyến mãi");
        khuyenMai.setLineColor(new java.awt.Color(0, 204, 204));
        khuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khuyenMaiActionPerformed(evt);
            }
        });

        note.setColumns(20);
        note.setRows(5);
        note.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        note.setHint("Nhập ghi chú...");
        jScrollPane3.setViewportView(note);

        discountValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discountValue.setToolTipText("");
        discountValue.setEnabled(false);
        discountValue.setFocusable(false);
        discountValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        discountValue.setHint("0");
        discountValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountValueActionPerformed(evt);
            }
        });

        creditPoint.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        creditPoint.setText("Điểm tích luỹ");

        sale.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sale.setText("Ưu đãi");

        total.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        total.setText("Tổng tiền");

        creditPointValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        creditPointValue.setToolTipText("");
        creditPointValue.setEnabled(false);
        creditPointValue.setFocusable(false);
        creditPointValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        creditPointValue.setHint("0");
        creditPointValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditPointValueActionPerformed(evt);
            }
        });

        totalValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalValue.setToolTipText("");
        totalValue.setEnabled(false);
        totalValue.setFocusable(false);
        totalValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        totalValue.setHint("0");
        totalValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalValueActionPerformed(evt);
            }
        });

        saleValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        saleValue.setToolTipText("");
        saleValue.setEnabled(false);
        saleValue.setFocusable(false);
        saleValue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        saleValue.setHint("0");
        saleValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleValueActionPerformed(evt);
            }
        });

        thanhToanBtn.setBackground(new java.awt.Color(0, 204, 204));
        thanhToanBtn.setText("Thanh toán");
        thanhToanBtn.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        thanhToanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thanhToanBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thanhToanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(phonejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(namejLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkPhoneBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(newCustomerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(khuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(discountValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(creditPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(sale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(creditPointValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saleValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkPhoneBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(phonejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newCustomerBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(namejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(khuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(discountValue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(sale, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(creditPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(saleValue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(creditPointValue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(thanhToanBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCustomerBtnActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_newCustomerBtnActionPerformed

    private void checkPhoneBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPhoneBtnActionPerformed
        // TODO add your handling code here:
        if(!phoneTextField.getText().isEmpty()){
            String phone = phoneTextField.getText();
            if(!khachhang.checkphone(phone)){
                JOptionPane.showMessageDialog(jPanel2, "Đã tìm thấy!");
                
                kh = khachhang.getkhbyphone(phone);
                hd.setMaKhachHang(kh.getMaKhachHang());
                ud.setMaUuDai(kh.getMaUudai());
                ud = uudai.getud(ud);
                double tienud = (tongtien*ud.getTiLeGiam())/100;
                td = tichdiem.gettdbytien(tongtien);
                hd.setMaTichDiem(td.getMaTichDiem());
                diem = td.getDiemTichLuy();
                tongtienfi = tongtienfi - tienud;
                tongtienud = tienud;
                saleValue.setText("- "+Integer.toString((int)tienud)+" đ "+Integer.toString(ud.getTiLeGiam())+" %");
                creditPointValue.setText(Integer.toString(diem));
                totalValue.setText(Integer.toString((int)tongtienfi));
            }
            else{
                JOptionPane.showMessageDialog(jPanel2, "Không tìm thấy!");
            }
        }
        else{
            JOptionPane.showMessageDialog(jPanel2, "Chưa nhập số điện thoại cần kiểm tra!");
        }
    }//GEN-LAST:event_checkPhoneBtnActionPerformed

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void phoneTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneTextFieldActionPerformed

    private void khuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khuyenMaiActionPerformed
        // TODO add your handling code here:
        if(khuyenMai.getSelectedItem() != null){
            if(!khuyenMai.getSelectedItem().equals("Khuyến mãi")){
                String tenkhuyenmai = khuyenMai.getSelectedItem().toString();
                km = khuyenmai.getkmbyname(tenkhuyenmai);
                hd.setMaKhuyenMai(km.getMaKhuyenMai());
                double tienkm = (tongtien*km.getPhanTram())/100;
                tongtienkm = tienkm;
                discountValue.setText("-"+Integer.toString((int)tienkm)+" đ "+Double.toString(km.getPhanTram())+" %");
                tongtienfi = tongtienfi - tongtienkm;
                totalValue.setText(Integer.toString((int)tongtienfi));
            }
        }
    }//GEN-LAST:event_khuyenMaiActionPerformed

    private void discountValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountValueActionPerformed

    private void creditPointValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditPointValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditPointValueActionPerformed

    private void totalValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalValueActionPerformed

    private void saleValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saleValueActionPerformed

    private void thanhToanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thanhToanBtnActionPerformed
        // TODO add your handling code here:
        hd.setGhiChu(note.getText());
        hd.setNgayMua(new java.sql.Timestamp(((java.util.Date) new Date()).getTime()));
        hd.setTongTien(tongtienfi);
        hd.setIsHidden(0);
        
        if(hd.getMaKhachHang() == 0 && hd.getMaKhuyenMai() == 0){
            hoadon.addhdnokmkh(hd);
        }
        else if(hd.getMaKhachHang() ==0 ){
            hoadon.addhdnokh(hd);
            
            km.setSoLuongDaDung(km.getSoLuongDaDung()+1);
            khuyenmai.updatekhuyenmai(km);
        }
        else if(hd.getMaKhuyenMai() ==0 ){
            hoadon.addhdnokm(hd);
            
            kh.setDiemTichLuy(kh.getDiemTichLuy()+diem);
            kh.setMaUudai(uudai.setudbydiem(kh.getDiemTichLuy()).getMaUuDai());
            khachhang.updatediemtichluy(kh);
        }
        else{
            hoadon.add(hd);
            
            km.setSoLuongDaDung(km.getSoLuongDaDung()+1);
            khuyenmai.updatekhuyenmai(km);
                        
            kh.setDiemTichLuy(kh.getDiemTichLuy()+diem);
            kh.setMaUudai(uudai.setudbydiem(kh.getDiemTichLuy()).getMaUuDai());
            khachhang.updatediemtichluy(kh);
        }
        hd = hoadon.gethdgannhat();
        int mahd = hd.getMaHoaDon() + 1;
        // add chi tiet hoa don
        for(dtocthoadon i: limenu){
            i.setMaHoaDon(mahd);
            dtoctphieunhap ctpn = ctphieunhap.getspganhh(i.getMaSanPham());
            i.setDonGia(ctpn.getGiaBan());
            cthoadon.add(i);
            
            int masp = i.getMaSanPham();
            int soluong = ctpn.getSoluongtonkho() - i.getSoLuong();
            ctpn.setSoluongtonkho(soluong);
            ctphieunhap.update(ctpn);
            
            dtosanpham sp = new dtosanpham();
            sp.setMaSanPham(masp);
            sp = sanpham.getsp(sp);
            int soluongsanpham = sp.getSoLuong() - i.getSoLuong();
            sanpham.updateslsanpham(sp);
        }
        JOptionPane.showMessageDialog(this, "Thanh toán thành công! In hóa đơn");

        bHeight = Double.valueOf(limenu.size());

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
         try {
            pj.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
<<<<<<< Updated upstream
=======
        this.dispose();
>>>>>>> Stashed changes
    }//GEN-LAST:event_thanhToanBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.login.Button checkPhoneBtn;
    private javax.swing.JLabel creditPoint;
    private gui.swing.login.MyTextField creditPointValue;
    private gui.swing.login.MyTextField discountValue;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private gui.comp.Combobox khuyenMai;
    private gui.swing.login.MyTextField nameTextField;
    private javax.swing.JLabel namejLabel;
    private gui.swing.login.Button newCustomerBtn;
    private gui.comp.HintTextArea note;
    private gui.modal.layout.OptionLayoutUtils optionLayoutUtils1;
    private javax.swing.JTable paymentTable;
    private gui.swing.login.MyTextField phoneTextField;
    private javax.swing.JLabel phonejLabel;
    private javax.swing.JLabel sale;
    private gui.swing.login.MyTextField saleValue;
    private gui.swing.login.Button thanhToanBtn;
    private javax.swing.JLabel total;
    private gui.swing.login.MyTextField totalValue;
    // End of variables declaration//GEN-END:variables
}
