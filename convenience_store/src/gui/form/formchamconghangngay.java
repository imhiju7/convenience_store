/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import bus.buschamcong;
import bus.buschitietchamcong;
import bus.busnhanvien;
import dto.dtochamcong;
import dto.dtochitietchamcong;
import dto.dtonhanvien;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import javax.swing.*;
import java.awt.Image;
import static java.lang.String.valueOf;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AD
 */
public class formchamconghangngay extends javax.swing.JPanel {

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    int manv = 0;
    dtonhanvien nv = new dtonhanvien();
    buschitietchamcong busctcc = new buschitietchamcong();
    buschamcong buscc = new buschamcong();
    busnhanvien busnv = new busnhanvien();
    public formchamconghangngay(int manv) throws SQLException {
        startClock();
        initComponents();
        nv.setManhanvien(manv);
        getInfo(nv);
        
    }
    private void init() {
        
    }
    private void startClock() {
  
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/YYYY");
                timeLabel.setText(now.format(formatter));
            }
        });
        timer.start();
    }
    private void getInfo(dtonhanvien nv) throws SQLException {
        busnhanvien busnv = new busnhanvien();
        nv = busnv.getnv(nv);
        String maNV = String.valueOf(nv.getManhanvien());
        String tenNV = nv.getTennhanvien();
        String chucVu = busnv.getChucVuByMaNhanVien(nv.getManhanvien());
        String diaChi = nv.getDiachi();
        String avtpath = nv.getImg();

        maNVTextField.setText(maNV);
        nameTextField.setText(tenNV);
        chucVuTextField.setText(chucVu);
        diaChiTextField.setText(diaChi);
        
        ImageIcon curImg = new ImageIcon(System.getProperty("user.dir") + "/src/source/image/nhanvien/" + avtpath);
        Image scaledImg = curImg.getImage().getScaledInstance(170, 230, Image.SCALE_SMOOTH);
        ImageIcon editImg = new ImageIcon(scaledImg);
        imglabel.setIcon(editImg);
        
        Date day = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        
        int Month  = cal.get(Calendar.MONTH) + 1;
        int Year = cal.get(Calendar.YEAR);
        dtochamcong cc = new dtochamcong();
        cc.setManhanvien(manv);
        cc.setThangchamcong(Month);
        cc.setNamchamcong(Year);
        
        cc = buscc.get(cc);
        
        dtochitietchamcong ctcc = busctcc.getctcc(day, cc.getMachamcong());
        if(ctcc.getGiobatdau() != null){
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/YYYY");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            myTextField1.setText(formatter.format(ctcc.getGiobatdau()));
            combobox1.setSelectedItem(ctcc.getLoaichamcong());
            checkInTime = LocalDateTime.parse(ctcc.getGiobatdau().toString(), format);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myTextField1 = new gui.swing.login.MyTextField();
        myTextField2 = new gui.swing.login.MyTextField();
        button2 = new gui.swing.login.Button();
        button3 = new gui.swing.login.Button();
        button5 = new gui.swing.login.Button();
        combobox1 = new gui.comp.Combobox();
        timeLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        chucVuTextField = new gui.swing.login.MyTextField();
        maNVTextField = new gui.swing.login.MyTextField();
        nameTextField = new gui.swing.login.MyTextField();
        diaChiTextField = new gui.swing.login.MyTextField();
        imgpanel = new javax.swing.JPanel();
        imglabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        myTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giờ check in", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N
        myTextField1.setToolTipText("");
        myTextField1.setFocusable(false);
        myTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myTextField1ActionPerformed(evt);
            }
        });

        myTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giờ check out", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N
        myTextField2.setFocusable(false);
        myTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myTextField2ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(204, 204, 0));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Check In");
        button2.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(51, 153, 0));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Hoàn Tất Ca");
        button3.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button5.setBackground(new java.awt.Color(204, 0, 0));
        button5.setForeground(new java.awt.Color(255, 255, 255));
        button5.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        button5.setLabel("Check Out");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        combobox1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hình thức làm việc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N
        combobox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bình thường", "Tăng ca" }));
        combobox1.setFocusable(false);
        combobox1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        combobox1.setLabeText("");
        combobox1.setLineColor(new java.awt.Color(51, 153, 0));
        combobox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox1ActionPerformed(evt);
            }
        });

        timeLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chấm Công Hằng Ngày");

        chucVuTextField.setEditable(false);
        chucVuTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N
        chucVuTextField.setFocusable(false);
        chucVuTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chucVuTextFieldActionPerformed(evt);
            }
        });

        maNVTextField.setEditable(false);
        maNVTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N
        maNVTextField.setFocusable(false);
        maNVTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maNVTextFieldActionPerformed(evt);
            }
        });

        nameTextField.setEditable(false);
        nameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N
        nameTextField.setFocusable(false);
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        diaChiTextField.setEditable(false);
        diaChiTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Địa chỉ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12))); // NOI18N
        diaChiTextField.setFocusable(false);
        diaChiTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diaChiTextFieldActionPerformed(evt);
            }
        });

        imgpanel.setPreferredSize(new java.awt.Dimension(170, 230));

        javax.swing.GroupLayout imgpanelLayout = new javax.swing.GroupLayout(imgpanel);
        imgpanel.setLayout(imgpanelLayout);
        imgpanelLayout.setHorizontalGroup(
            imgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imglabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        imgpanelLayout.setVerticalGroup(
            imgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imglabel, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        imglabel.getAccessibleContext().setAccessibleName("imglabel");
        imglabel.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chucVuTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(23, 23, 23))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(maNVTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(diaChiTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imgpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combobox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(button5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imgpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maNVTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(chucVuTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(diaChiTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(myTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(myTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void myTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTextField1ActionPerformed

    private void myTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myTextField2ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        if(myTextField1.getText().isEmpty()){
            checkInTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/YYYY");
            
            String formattedTime = checkInTime.format(formatter);
            
            LocalTime[] startTimes = {
                LocalTime.of(5, 50),
                LocalTime.of(12, 50),
                LocalTime.of(17, 50),
                LocalTime.of(23, 00)
            };
            LocalTime[] endTimes = {
                LocalTime.of(6, 0),
                LocalTime.of(13, 0),
                LocalTime.of(18, 0),
                LocalTime.of(23, 50)
            };
            
            LocalTime currentTime = checkInTime.toLocalTime();
            
            boolean isValidTime = false;
            for (int i = 0; i < startTimes.length; i++) {
                if (!currentTime.isBefore(startTimes[i]) && !currentTime.isAfter(endTimes[i])) {
                    isValidTime = true;
                    break;
                }
            }
            if (!isValidTime) {
                JOptionPane.showMessageDialog(null, "Check-in không hợp lệ! Vui lòng check-in trong các khoảng thời gian quy định.");
                return;
            }
            myTextField1.setText(checkInTime.format(formatter));
            // insert
            dtochamcong cc = new dtochamcong();
            cc.setManhanvien(manv);
            cc.setThangchamcong(checkInTime.getMonthValue());
            cc.setNamchamcong(checkInTime.getYear());
            String workMode = combobox1.getSelectedItem().toString();
            cc = buscc.get(cc);

            dtochitietchamcong ctcc = new dtochitietchamcong();

            ctcc.setMachamcong(cc.getMachamcong());
            ctcc.setGiobatdau(Timestamp.valueOf(checkInTime));
            ctcc.setLoaichamcong(workMode);
            ctcc.setNgaychamcong(Date.from(checkInTime.atZone(ZoneId.systemDefault()).toInstant()));
            ctcc.setSogiolam(0);
            ctcc.setTennhanvien(manv);

            busctcc.create(ctcc);
        }
        else{
            JOptionPane.showMessageDialog(this,"Bạn đã thực hiện check-in ở lần trước đó!");
        }
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        if (checkInTime != null && checkOutTime != null) {
            Duration duration = Duration.between(checkInTime, checkOutTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            
            String workMode = combobox1.getSelectedItem().toString();
            JOptionPane.showMessageDialog(this, 
                "Thời gian làm việc: " + hours + " giờ " + minutes + " phút\nHình thức làm việc: " + workMode,
                "Hoàn Tất Ca", JOptionPane.INFORMATION_MESSAGE);
            dtochamcong cc = new dtochamcong();
            cc.setManhanvien(manv);
            cc.setThangchamcong(checkInTime.getMonthValue());
            cc.setNamchamcong(checkInTime.getYear());
            
            cc = buscc.get(cc);
            dtochitietchamcong ctcc = busctcc.getctccab(Date.from(checkInTime.atZone(ZoneId.systemDefault()).toInstant()), Timestamp.valueOf(checkInTime), Timestamp.valueOf(checkOutTime));
            
            int time = ctcc.getSogiolam();
            if(workMode.equals("Bình thường")){
                cc.setSogiolamviec(cc.getSogiolamviec() + time);
            }
            else{
                cc.setSogiolamthem(cc.getSogiolamthem() + time);
            }
            if(busctcc.getctccdathuchien(Date.from(checkInTime.atZone(ZoneId.systemDefault()).toInstant()), cc.getMachamcong()).size() < 2){
                cc.setSongaylamviec(cc.getSongaylamviec()+1);
            }
            buscc.update(cc);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng thực hiện Check In và Check Out trước!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button3ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        if (myTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng thực hiện Check In trước!", 
            "Lỗi", JOptionPane.ERROR_MESSAGE);
            return; // Dừng lại và không cho phép Check Out
        }
    
        if(myTextField2.getText().isEmpty()){
            checkOutTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/YYYY");
            
            String formattedTime = checkOutTime.format(formatter);
            
            LocalTime[] startTimes = {
                LocalTime.of(5, 50),
                LocalTime.of(12, 50),
                LocalTime.of(17, 50),
                LocalTime.of(0, 00)
            };
            LocalTime[] endTimes = {
                LocalTime.of(6, 0),
                LocalTime.of(13, 0),
                LocalTime.of(18, 0),
                LocalTime.of(2, 50)
            };
            
            LocalTime currentTime = checkOutTime.toLocalTime();
            
            boolean isValidTime = false;
            for (int i = 0; i < startTimes.length; i++) {
                if (!currentTime.isBefore(startTimes[i]) && !currentTime.isAfter(endTimes[i])) {
                    isValidTime = true;
                    break;
                }
            }
            if (!isValidTime) {
                JOptionPane.showMessageDialog(null, "Check-out không hợp lệ! Vui lòng check-out trong các khoảng thời gian quy định.");
                return;
            }
            Duration duration = Duration.between(checkInTime, checkOutTime);
            long hours = duration.toHours();
            if(hours < 2){
                JOptionPane.showMessageDialog(null, "Vui lòng làm đủ thời gian của 1 ca làm việc!");
                return;
            }
            myTextField2.setText(checkOutTime.format(formatter));
            // insert
            dtochamcong cc = new dtochamcong();
            cc.setManhanvien(manv);
            cc.setThangchamcong(checkInTime.getMonthValue());
            cc.setNamchamcong(checkInTime.getYear());
            
            cc = buscc.get(cc);
            Date day = new Date();
            dtochitietchamcong ctcc = busctcc.getctcc(day, cc.getMachamcong());
            ctcc.setGioketthuc(Timestamp.valueOf(checkOutTime));
            ctcc.setSogiolam((int) hours);
            busctcc.update(ctcc);
        }
        else{
            JOptionPane.showMessageDialog(this,"Bạn đã thực hiện check-out ở lần trước đó!");
        }
    }//GEN-LAST:event_button5ActionPerformed

    private void maNVTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maNVTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maNVTextFieldActionPerformed

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void chucVuTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chucVuTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chucVuTextFieldActionPerformed

    private void diaChiTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diaChiTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diaChiTextFieldActionPerformed

    private void combobox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combobox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.login.Button button2;
    private gui.swing.login.Button button3;
    private gui.swing.login.Button button5;
    private gui.swing.login.MyTextField chucVuTextField;
    private gui.comp.Combobox combobox1;
    private gui.swing.login.MyTextField diaChiTextField;
    private javax.swing.JLabel imglabel;
    private javax.swing.JPanel imgpanel;
    private javax.swing.JLabel jLabel1;
    private gui.swing.login.MyTextField maNVTextField;
    private gui.swing.login.MyTextField myTextField1;
    private gui.swing.login.MyTextField myTextField2;
    private gui.swing.login.MyTextField nameTextField;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
