
package View;

import DAO.CafeDAO;
import Model.Cafe;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class QLCafeFrame extends javax.swing.JFrame {
    private CafeDAO cfdao;
    private DefaultTableModel model;
    public String hvten = "";
    public QLCafeFrame(String hvt) {
        initComponents();
        cfdao = new CafeDAO();
        hvten = hvt;
        hoVaTen.setText("Xin chào "+hvt+"!");
        setCafeTable(cfdao.getAllCafe());
        maCFTF.setEnabled(false);
        cancelBT.setEnabled(false);
    }
    
    public void setCafeTable(List<Cafe> lcf){
        model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cafeTB.setModel(model);
        model.addColumn("STT");
        model.addColumn("Mã Cafe");
        model.addColumn("Tên Cafe");
        model.addColumn("Giá chế biến");
        model.addColumn("Giá bán");
        setDataCafeTable(lcf);
        ListSelectionModel lsm = cafeTB.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                int row = cafeTB.getSelectedRow();
                if(row == -1 || "Lưu".equals(themBT.getText()))
                    return;
                String mcf = String.valueOf(cafeTB.getValueAt(row, 1));
                Cafe cf = new Cafe();
                cf = cfdao.getCafe(mcf);
                setChiTietCafe(cf);
                if(cf.getTrangThai().equals("Đang kinh doanh"))
                    ngungKinhDoanh.setText("Ngừng kinh doanh");
                else
                    ngungKinhDoanh.setText("Kinh doanh");
            }
        });
    }
    
    public void setChiTietCafe(Cafe cf){
        maCFTF.setText(String.valueOf(cf.getMaCafe()));
        String tcf = "";
            if("Đang kinh doanh".equals(cf.getTrangThai()))
                tcf = cf.getTenCafe();
            else
                tcf = cf.getTenCafe() + " {Ngừng}";
        tenCFTF.setText(tcf);
        String maDM = ""+cf.getMaDM().charAt(3);
        nhomCFCBB.setSelectedIndex(Integer.parseInt(maDM) - 1);
        giaCBTF.setText(String.valueOf(cf.getGiaCheBien()));
        giaBTF.setText(String.valueOf(cf.getGiaBan()));
        if(cf.getMoTa() != null)
            moTaTF.setText(cf.getMoTa());
        if(cf.getThanhPhan()!= null)
            thanhPhanTF.setText(cf.getThanhPhan());
    }
    
    public void setDataCafeTable(List<Cafe> lcf){
        model.setRowCount(0);
        int dem = 0;
        for(Cafe cf : lcf){
            String tcf = "";
            if("Đang kinh doanh".equals(cf.getTrangThai()))
                tcf = cf.getTenCafe();
            else
                tcf = cf.getTenCafe() + " {Ngừng}";
            dem++;
            model.addRow(new Object[]{
                dem,
                cf.getMaCafe(),
                tcf,
                cf.getGiaCheBien(),
                cf.getGiaBan()
            });
        }
    }
    
    public Cafe getCafe(){
        Cafe cf = new Cafe();
        try {
            cf.setTenCafe(tenCFTF.getText());
            String maDM = "DM0" + (nhomCFCBB.getSelectedIndex()+1);
            cf.setMaDM(maDM);
            cf.setGiaCheBien(Long.parseLong(giaCBTF.getText()));
            cf.setGiaBan(Long.parseLong(giaBTF.getText()));
            cf.setMoTa(moTaTF.getText());
            cf.setThanhPhan(thanhPhanTF.getText());
        } catch (Exception e) {
            System.out.println(e);
        }
        return cf;
    }
    
    public void setReset(){
        maCFTF.setText("");
        tenCFTF.setText("");
        nhomCFCBB.setSelectedIndex(0);
        giaCBTF.setText("");
        giaBTF.setText("");
        moTaTF.setText("");
        thanhPhanTF.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tongQuan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        QLHoaDon = new javax.swing.JButton();
        dangXuat = new javax.swing.JButton();
        hoVaTen = new javax.swing.JButton();
        QLNhanVien = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cafeTB = new javax.swing.JTable();
        nhomCafe = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        xoaBT = new javax.swing.JButton();
        xuatFile = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        maCFTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tenCFTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nhomCFCBB = new javax.swing.JComboBox<>();
        giaCBTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        giaBTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        moTaTF = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        thanhPhanTF = new javax.swing.JTextArea();
        themBT = new javax.swing.JButton();
        suaBT = new javax.swing.JButton();
        cancelBT = new javax.swing.JButton();
        ngungKinhDoanh = new javax.swing.JButton();
        tuKhoa = new javax.swing.JTextField();
        timKiemBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý");

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        tongQuan.setBackground(new java.awt.Color(255, 255, 255));
        tongQuan.setText("Tổng quan");
        tongQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tongQuanActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cafe");

        QLHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        QLHoaDon.setText("Hóa đơn");
        QLHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLHoaDonActionPerformed(evt);
            }
        });

        dangXuat.setBackground(new java.awt.Color(255, 255, 255));
        dangXuat.setText("Đăng xuất");
        dangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangXuatActionPerformed(evt);
            }
        });

        hoVaTen.setBackground(new java.awt.Color(255, 255, 255));
        hoVaTen.setText("Tài khoản");

        QLNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        QLNhanVien.setText("Nhân viên");
        QLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QLHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QLNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dangXuat))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tongQuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(QLHoaDon)
                .addComponent(QLNhanVien))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(dangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hoVaTen))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Cafe");

        cafeTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Cafe", "Tên Cafe", "Giá chế biến", "Giá bán"
            }
        ));
        jScrollPane1.setViewportView(cafeTB);

        nhomCafe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Socola", "Cafe đá xay", "Thạch và topping", "Smoothies", "Cafe Espresso", "Trái cây đá xay" }));
        nhomCafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhomCafeActionPerformed(evt);
            }
        });

        jLabel2.setText("Nhóm cafe:");

        xoaBT.setBackground(new java.awt.Color(204, 0, 0));
        xoaBT.setForeground(new java.awt.Color(255, 255, 255));
        xoaBT.setText("Xóa");
        xoaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaBTActionPerformed(evt);
            }
        });

        xuatFile.setBackground(new java.awt.Color(255, 255, 255));
        xuatFile.setText("Xuất file");
        xuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatFileActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Thông tin");

        jLabel4.setText("Mã cafe:");

        jLabel5.setText("Tên cafe:");

        jLabel6.setText("Nhóm cafe:");

        jLabel7.setText("Giá chế biến:");

        nhomCFCBB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Socola", "Cafe đá xay", "Thạch và topping", "Smoothies", "Cafe Espresso", "Trái cây đá xay" }));

        jLabel8.setText("Giá bán:");

        jLabel9.setText("Mô tả:");

        moTaTF.setColumns(20);
        moTaTF.setRows(5);
        jScrollPane2.setViewportView(moTaTF);

        jLabel10.setText("Thành phần:");

        thanhPhanTF.setColumns(20);
        thanhPhanTF.setRows(5);
        jScrollPane3.setViewportView(thanhPhanTF);

        themBT.setBackground(new java.awt.Color(0, 102, 102));
        themBT.setForeground(new java.awt.Color(255, 255, 255));
        themBT.setText("Thêm");
        themBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBTActionPerformed(evt);
            }
        });

        suaBT.setBackground(new java.awt.Color(0, 153, 153));
        suaBT.setForeground(new java.awt.Color(255, 255, 255));
        suaBT.setText("Sửa");
        suaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaBTActionPerformed(evt);
            }
        });

        cancelBT.setBackground(new java.awt.Color(0, 102, 102));
        cancelBT.setForeground(new java.awt.Color(255, 255, 255));
        cancelBT.setText("Hủy bỏ");
        cancelBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBTActionPerformed(evt);
            }
        });

        ngungKinhDoanh.setBackground(new java.awt.Color(0, 204, 0));
        ngungKinhDoanh.setForeground(new java.awt.Color(255, 255, 255));
        ngungKinhDoanh.setText("Ngừng kinh doanh");
        ngungKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngungKinhDoanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(maCFTF, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane3))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tenCFTF, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nhomCFCBB, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(giaBTF, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(giaCBTF)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cancelBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(themBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(suaBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ngungKinhDoanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(maCFTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tenCFTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nhomCFCBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(giaCBTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(giaBTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(themBT)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(suaBT)
                        .addComponent(ngungKinhDoanh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelBT)
                .addContainerGap())
        );

        tuKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuKhoaActionPerformed(evt);
            }
        });
        tuKhoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tuKhoaKeyPressed(evt);
            }
        });

        timKiemBT.setBackground(new java.awt.Color(255, 255, 255));
        timKiemBT.setText("Tìm kiếm");
        timKiemBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(162, 162, 162)
                        .addComponent(tuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timKiemBT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nhomCafe, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(xoaBT, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(xuatFile)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nhomCafe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(xoaBT)
                    .addComponent(xuatFile)
                    .addComponent(tuKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timKiemBT))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tuKhoaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tuKhoaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            List<Cafe> list = new ArrayList<>();
            list = cfdao.getTimKiemTen(tuKhoa.getText());
            if(list.isEmpty()){
                JOptionPane.showMessageDialog(this, "Không có kết quả nào!");
                setDataCafeTable(cfdao.getAllCafe());
            }
            else
                setDataCafeTable(list);
        }
    }//GEN-LAST:event_tuKhoaKeyPressed

    private void xoaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaBTActionPerformed
        int row = cafeTB.getSelectedRow();
        if(row != -1){
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắc muốn xóa?");
            if(confirm == JOptionPane.YES_OPTION){
                cfdao.xoaCafe((String) cafeTB.getValueAt(row, 1));
                if(cfdao.ktXoa == 1){
                    setDataCafeTable(cfdao.getAllCafe());
                    setReset();
                }
                else
                    JOptionPane.showMessageDialog(this, "Không thể xóa vì Cafe này đang nằm trong giao dịch!");
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hàng hóa cần xóa!");
    }//GEN-LAST:event_xoaBTActionPerformed

    private void nhomCafeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhomCafeActionPerformed
        String nhom = (String) nhomCafe.getSelectedItem();
        if(nhom.equals("Tất cả"))
            setDataCafeTable(cfdao.getAllCafe());
        else
            setDataCafeTable(cfdao.getNhomCafe(nhom));
    }//GEN-LAST:event_nhomCafeActionPerformed

    private void timKiemBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemBTActionPerformed
        List<Cafe> list = new ArrayList<>();
        list = cfdao.getTimKiemTen(tuKhoa.getText());
        if(list.isEmpty()){
            JOptionPane.showMessageDialog(this, "Không có kết quả nào!");
            setDataCafeTable(cfdao.getAllCafe());
        }
        else
            setDataCafeTable(list);
    }//GEN-LAST:event_timKiemBTActionPerformed

    private void themBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBTActionPerformed
        if("Thêm".equals(themBT.getText())){
            themBT.setText("Lưu");
            cancelBT.setEnabled(true);
            suaBT.setEnabled(false);
            ngungKinhDoanh.setEnabled(false);
            setReset();
            maCFTF.setText("Tự động cập nhật!");
        }
        else{
            if("".equals(tenCFTF.getText()) || "".equals(giaBTF.getText())){
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập đầy đủ thông tin!");
                return;
            }
            Cafe cf = new Cafe();
            cf = getCafe();
            cfdao.themCafe(cf);
            if(cfdao.ktThem == 1){
                JOptionPane.showMessageDialog(this, "Thêm một món Cafe thành công!");
                themBT.setText("Thêm");
                cancelBT.setEnabled(false);
                suaBT.setEnabled(true);
                ngungKinhDoanh.setEnabled(true);
                int row = cafeTB.getSelectedRow();
                if(row != -1)
                    maCFTF.setText(String.valueOf(cafeTB.getValueAt(row, 1)));
                else
                    maCFTF.setText("");
                setDataCafeTable(cfdao.getAllCafe());
                setReset();
            }
            else
                JOptionPane.showMessageDialog(this, "Thêm không thành công!");
        }
    }//GEN-LAST:event_themBTActionPerformed

    private void cancelBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBTActionPerformed
        themBT.setText("Thêm");
        cancelBT.setEnabled(false);
        suaBT.setEnabled(true);
        ngungKinhDoanh.setEnabled(true);
        setDataCafeTable(cfdao.getAllCafe());
        setReset();
    }//GEN-LAST:event_cancelBTActionPerformed

    private void suaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaBTActionPerformed
        int row = cafeTB.getSelectedRow();
        if(row != -1){
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắc muốn sửa?");
            if(confirm == JOptionPane.YES_OPTION){
                Cafe cf = new Cafe();
                cf = getCafe();
                cf.setMaCafe(maCFTF.getText());
                cfdao.suaCafe(cf);
                setDataCafeTable(cfdao.getAllCafe());
                setReset();
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hàng hóa cần sửa!");
    }//GEN-LAST:event_suaBTActionPerformed

    private void xuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatFileActionPerformed
        JOptionPane.showMessageDialog(this, "Xuất file thành công!");
    }//GEN-LAST:event_xuatFileActionPerformed

    private void QLHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLHoaDonActionPerformed
        QLHoaDonFrame hd = new QLHoaDonFrame(hvten);
        hd.setLocationRelativeTo(null);
        hd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_QLHoaDonActionPerformed

    private void tuKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tuKhoaActionPerformed

    }//GEN-LAST:event_tuKhoaActionPerformed

    private void tongQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tongQuanActionPerformed
        ThongKeFrame tk = new ThongKeFrame(hvten);
        tk.setLocationRelativeTo(null);
        tk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_tongQuanActionPerformed

    private void QLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLNhanVienActionPerformed
        QLNhanVien nv = new QLNhanVien(hvten);
        nv.setLocationRelativeTo(null);
        nv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_QLNhanVienActionPerformed

    private void dangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangXuatActionPerformed
        DangNhapFrame dn = new DangNhapFrame();
        dn.setVisible(true);
        dn.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_dangXuatActionPerformed

    private void ngungKinhDoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngungKinhDoanhActionPerformed
        int row = cafeTB.getSelectedRow();
        if(row != -1){
            String mcf = (String) cafeTB.getValueAt(row, 1);
            if(ngungKinhDoanh.getText().equals("Ngừng kinh doanh")){
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắc muốn ngừng kinh doanh Cafe này?");
                if(confirm == JOptionPane.YES_OPTION){
                    cfdao.ngungKinhDoanh(mcf);
                    setDataCafeTable(cfdao.getAllCafe());
                    setReset();
                }
            }
            else
            {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắc muốn kinh doanh Cafe này?");
                if(confirm == JOptionPane.YES_OPTION){
                    cfdao.kinhDoanh(mcf);
                    setDataCafeTable(cfdao.getAllCafe());
                    setReset();
                }
            }                
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn Cafe!");
    }//GEN-LAST:event_ngungKinhDoanhActionPerformed

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                QLCafeFrame cf = new QLCafeFrame();
//                cf.setVisible(true);
//                cf.setLocationRelativeTo(null);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton QLHoaDon;
    private javax.swing.JButton QLNhanVien;
    private javax.swing.JTable cafeTB;
    private javax.swing.JButton cancelBT;
    private javax.swing.JButton dangXuat;
    private javax.swing.JTextField giaBTF;
    private javax.swing.JTextField giaCBTF;
    private javax.swing.JButton hoVaTen;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField maCFTF;
    private javax.swing.JTextArea moTaTF;
    private javax.swing.JButton ngungKinhDoanh;
    private javax.swing.JComboBox<String> nhomCFCBB;
    private javax.swing.JComboBox<String> nhomCafe;
    private javax.swing.JButton suaBT;
    private javax.swing.JTextField tenCFTF;
    private javax.swing.JTextArea thanhPhanTF;
    private javax.swing.JButton themBT;
    private javax.swing.JButton timKiemBT;
    private javax.swing.JButton tongQuan;
    private javax.swing.JTextField tuKhoa;
    private javax.swing.JButton xoaBT;
    private javax.swing.JButton xuatFile;
    // End of variables declaration//GEN-END:variables
}
