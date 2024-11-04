
package gui.form;

/**
 *
 * @author AD
 */
import gui.comp.Combobox;
import gui.comp.TableSorter;
public class formthanhtoan extends javax.swing.JPanel {

    /**
     * Creates new form formthanhtoan
     */
    public formthanhtoan() {
        init();
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
        TableSorter.configureTableColumnSorter(paymentTable, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(paymentTable, 1, TableSorter.STRING_COMPARATOR);
        TableSorter.configureTableColumnSorter(paymentTable, 2, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(paymentTable, 3, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(paymentTable, 4, TableSorter.VND_CURRENCY_COMPARATOR);
        paymentTable.getTableHeader().setReorderingAllowed(false);
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
    }// </editor-fold>//GEN-END:initComponents

    private void newCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCustomerBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newCustomerBtnActionPerformed

    private void checkPhoneBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPhoneBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPhoneBtnActionPerformed

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void phoneTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneTextFieldActionPerformed

    private void khuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khuyenMaiActionPerformed
        // TODO add your handling code here:
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
