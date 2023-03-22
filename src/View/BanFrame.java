
package View;

import DAO.BanDAO;
import Model.Ban;
import Model.NhanVien;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BanFrame extends javax.swing.JFrame implements ActionListener{
    private BanDAO bdao;
    private JButton bt;
    NhanVien nvien;
    Ban b;
    public BanFrame(NhanVien nv) {
        initComponents();
        bdao = new BanDAO();
        nvien = nv;
        b = new Ban();
        b.setMaBan("");
        setDataBan(bdao.getAllBan());
        enableBT();
        tenTK.setText("Xin chào " + nv.getHoVaTen());
    }
    
    public void enableBT(){
        truBT.setEnabled(false);
        congBT.setEnabled(false);
        xoaBT.setEnabled(false);
        in.setEnabled(false);
        baoCheBien.setEnabled(false);
        thanhToanBT.setEnabled(false);
    }
    
    public void setDataBan(List<Ban> lb){
        BanPanel.removeAll();
        sauBT.setEnabled(false);
        int trang = Integer.parseInt(trangCF.getText());
        if(trang > 1)
            truocBT.setEnabled(true);
        else
            truocBT.setEnabled(false);
        int dem = 0;
        for(Ban b : lb){
            dem++;
            if(dem > (trang - 1)*21 && dem <= trang*21){
                bt = new JButton(b.getTenBan());
                bt.setSize(60, 60);
                if(b.getDangNgoi() == 0)
                    bt.setBackground(Color.white);
                else
                    if(b.getSoCho() == b.getDangNgoi())
                        bt.setBackground(new Color(0,153,153));
                    else
                        bt.setBackground(new Color(102,153,255));
                bt.addActionListener(this);
                bt.setActionCommand(b.getMaBan());
                BanPanel.add(bt);
            }
        }
        if(dem <= trang*21)
            for(int i = ++dem; i <= trang*21; i++){
                bt = new JButton();
                bt.setSize(60, 60);
                bt.setBackground(Color.white);
                BanPanel.add(bt);
                bt.setEnabled(false);
            }
        else
            sauBT.setEnabled(true);
        BanPanel.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String maBan = event.getActionCommand();
        b = bdao.getBan(maBan);
        CafeFrame cf = new CafeFrame(nvien, b);
        cf.setVisible(true);
        cf.setLocationRelativeTo(null);
        this.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainLP = new javax.swing.JLayeredPane();
        BanPanel = new javax.swing.JPanel();
        truocBT = new javax.swing.JButton();
        sauBT = new javax.swing.JButton();
        trangCF = new javax.swing.JLabel();
        hoaDonThanhToan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chiTietHDTB = new javax.swing.JTable();
        in = new javax.swing.JButton();
        baoCheBien = new javax.swing.JButton();
        thanhToanBT = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tienKhachTraTF = new javax.swing.JTextField();
        tienThuaTF = new javax.swing.JTextField();
        vatTF = new javax.swing.JTextField();
        chietKhauTF = new javax.swing.JTextField();
        truBT = new javax.swing.JButton();
        congBT = new javax.swing.JButton();
        xoaBT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        maKhachTF = new javax.swing.JTextField();
        tongCongTF = new javax.swing.JLabel();
        thanhTienTF = new javax.swing.JLabel();
        HDBanPanel = new javax.swing.JPanel();
        banLB = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        thucDonBT = new javax.swing.JButton();
        banBT = new javax.swing.JButton();
        nhapTimKiem = new javax.swing.JTextField();
        timKiem = new javax.swing.JButton();
        dangXuatBT = new javax.swing.JButton();
        tenTK = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainLP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BanPanel.setBackground(new java.awt.Color(255, 255, 255));
        BanPanel.setLayout(new java.awt.GridLayout(7, 0, 15, 15));

        truocBT.setText("<");
        truocBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                truocBTActionPerformed(evt);
            }
        });

        sauBT.setText(">");
        sauBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauBTActionPerformed(evt);
            }
        });

        trangCF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trangCF.setText("1");

        mainLP.setLayer(BanPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainLP.setLayer(truocBT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainLP.setLayer(sauBT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainLP.setLayer(trangCF, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout mainLPLayout = new javax.swing.GroupLayout(mainLP);
        mainLP.setLayout(mainLPLayout);
        mainLPLayout.setHorizontalGroup(
            mainLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(truocBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trangCF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sauBT)
                .addGap(305, 305, 305))
        );
        mainLPLayout.setVerticalGroup(
            mainLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(truocBT)
                    .addComponent(sauBT)
                    .addComponent(trangCF))
                .addContainerGap())
        );

        hoaDonThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        hoaDonThanhToan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        chiTietHDTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Cafe", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(chiTietHDTB);

        in.setBackground(new java.awt.Color(102, 102, 102));
        in.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        in.setForeground(new java.awt.Color(255, 255, 255));
        in.setText("In");
        in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inActionPerformed(evt);
            }
        });

        baoCheBien.setBackground(new java.awt.Color(51, 204, 0));
        baoCheBien.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        baoCheBien.setForeground(new java.awt.Color(255, 255, 255));
        baoCheBien.setText("Báo chế biến");
        baoCheBien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baoCheBienActionPerformed(evt);
            }
        });

        thanhToanBT.setBackground(new java.awt.Color(0, 153, 153));
        thanhToanBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        thanhToanBT.setForeground(new java.awt.Color(255, 255, 255));
        thanhToanBT.setText("Thanh toán");
        thanhToanBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thanhToanBTActionPerformed(evt);
            }
        });

        jLabel3.setText("Chiết khấu:");

        jLabel4.setText("VAT:");

        jLabel5.setText("Tổng cộng:");

        jLabel6.setText("Tiền khách trả:");

        jLabel7.setText("Tiền thừa");

        jLabel8.setText("Thành tiền:");

        tienKhachTraTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tienKhachTraTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tienKhachTraTFKeyPressed(evt);
            }
        });

        tienThuaTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        vatTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        vatTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vatTFKeyPressed(evt);
            }
        });

        chietKhauTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        chietKhauTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chietKhauTFKeyPressed(evt);
            }
        });

        truBT.setBackground(new java.awt.Color(0, 153, 153));
        truBT.setForeground(new java.awt.Color(255, 255, 255));
        truBT.setText("-");
        truBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                truBTActionPerformed(evt);
            }
        });

        congBT.setBackground(new java.awt.Color(0, 153, 153));
        congBT.setForeground(new java.awt.Color(255, 255, 255));
        congBT.setText("+");
        congBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                congBTActionPerformed(evt);
            }
        });

        xoaBT.setBackground(new java.awt.Color(0, 153, 153));
        xoaBT.setForeground(new java.awt.Color(255, 255, 255));
        xoaBT.setText("Xóa");
        xoaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaBTActionPerformed(evt);
            }
        });

        jLabel1.setText("Phiếu khách:");

        maKhachTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        tongCongTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tongCongTF.setText("0");

        thanhTienTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        thanhTienTF.setText("0");

        HDBanPanel.setBackground(new java.awt.Color(102, 153, 255));
        HDBanPanel.setLayout(new java.awt.GridLayout(1, 0));

        banLB.setText("Bàn 0");

        javax.swing.GroupLayout hoaDonThanhToanLayout = new javax.swing.GroupLayout(hoaDonThanhToan);
        hoaDonThanhToan.setLayout(hoaDonThanhToanLayout);
        hoaDonThanhToanLayout.setHorizontalGroup(
            hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HDBanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(hoaDonThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(banLB)
                    .addGroup(hoaDonThanhToanLayout.createSequentialGroup()
                        .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tienKhachTraTF)
                            .addComponent(tienThuaTF)
                            .addComponent(vatTF)
                            .addComponent(chietKhauTF)
                            .addComponent(tongCongTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thanhTienTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoaDonThanhToanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(truBT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(congBT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(xoaBT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hoaDonThanhToanLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addComponent(maKhachTF))
                    .addGroup(hoaDonThanhToanLayout.createSequentialGroup()
                        .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(baoCheBien, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(thanhToanBT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        hoaDonThanhToanLayout.setVerticalGroup(
            hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoaDonThanhToanLayout.createSequentialGroup()
                .addComponent(HDBanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(banLB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(congBT)
                    .addComponent(truBT)
                    .addComponent(xoaBT))
                .addGap(15, 15, 15)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(maKhachTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tongCongTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(chietKhauTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(vatTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(thanhTienTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tienKhachTraTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tienThuaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(baoCheBien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thanhToanBT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menu.setBackground(new java.awt.Color(102, 153, 255));

        thucDonBT.setBackground(new java.awt.Color(255, 255, 255));
        thucDonBT.setText("Thực đơn");
        thucDonBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thucDonBTActionPerformed(evt);
            }
        });

        banBT.setBackground(new java.awt.Color(0, 153, 153));
        banBT.setForeground(new java.awt.Color(255, 255, 255));
        banBT.setText("Bàn");
        banBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banBTActionPerformed(evt);
            }
        });

        nhapTimKiem.setEditable(false);
        nhapTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapTimKiemActionPerformed(evt);
            }
        });

        timKiem.setBackground(new java.awt.Color(255, 255, 255));
        timKiem.setText("Tìm kiếm");
        timKiem.setEnabled(false);
        timKiem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemActionPerformed(evt);
            }
        });

        dangXuatBT.setBackground(new java.awt.Color(255, 255, 255));
        dangXuatBT.setText("Đăng xuất");
        dangXuatBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangXuatBTActionPerformed(evt);
            }
        });

        tenTK.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tenTK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tenTK.setText("Họ và tên");

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(banBT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thucDonBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nhapTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timKiem)
                .addGap(166, 166, 166)
                .addComponent(tenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dangXuatBT)
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(banBT)
                            .addComponent(nhapTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timKiem)
                            .addComponent(dangXuatBT)
                            .addComponent(thucDonBT))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tenTK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainLP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hoaDonThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hoaDonThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainLP))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void truocBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_truocBTActionPerformed
        int trang = Integer.parseInt(trangCF.getText()) - 1;
        trangCF.setText(String.valueOf(trang));
    }//GEN-LAST:event_truocBTActionPerformed

    private void sauBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sauBTActionPerformed
        int trang = Integer.parseInt(trangCF.getText()) + 1;
        trangCF.setText(String.valueOf(trang));
    }//GEN-LAST:event_sauBTActionPerformed

    private void inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inActionPerformed
        JOptionPane.showMessageDialog(this, "Đã in!");
    }//GEN-LAST:event_inActionPerformed

    private void baoCheBienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baoCheBienActionPerformed
        JOptionPane.showMessageDialog(this, "Đã báo chế biến!");
    }//GEN-LAST:event_baoCheBienActionPerformed

    private void thanhToanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thanhToanBTActionPerformed
        
    }//GEN-LAST:event_thanhToanBTActionPerformed

    private void tienKhachTraTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tienKhachTraTFKeyPressed
        
    }//GEN-LAST:event_tienKhachTraTFKeyPressed

    private void vatTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vatTFKeyPressed
        
    }//GEN-LAST:event_vatTFKeyPressed

    private void chietKhauTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chietKhauTFKeyPressed
        
    }//GEN-LAST:event_chietKhauTFKeyPressed

    private void truBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_truBTActionPerformed
        
    }//GEN-LAST:event_truBTActionPerformed

    private void congBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_congBTActionPerformed
        
    }//GEN-LAST:event_congBTActionPerformed

    private void xoaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaBTActionPerformed
        
    }//GEN-LAST:event_xoaBTActionPerformed

    private void thucDonBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thucDonBTActionPerformed
        if("".equals(b.getMaBan()) == false){
            CafeFrame cf = new CafeFrame(nvien, b);
            cf.setVisible(true);
            cf.setLocationRelativeTo(null);
            this.dispose();
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn bàn!");
    }//GEN-LAST:event_thucDonBTActionPerformed

    private void banBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banBTActionPerformed
        
    }//GEN-LAST:event_banBTActionPerformed

    private void nhapTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapTimKiemActionPerformed
        
    }//GEN-LAST:event_nhapTimKiemActionPerformed

    private void timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemActionPerformed
        
    }//GEN-LAST:event_timKiemActionPerformed

    private void dangXuatBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangXuatBTActionPerformed
        DangNhapFrame dn = new DangNhapFrame();
        dn.setVisible(true);
        dn.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_dangXuatBTActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(BanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BanFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BanPanel;
    private javax.swing.JPanel HDBanPanel;
    private javax.swing.JButton banBT;
    private javax.swing.JLabel banLB;
    private javax.swing.JButton baoCheBien;
    private javax.swing.JTable chiTietHDTB;
    private javax.swing.JTextField chietKhauTF;
    private javax.swing.JButton congBT;
    private javax.swing.JButton dangXuatBT;
    private javax.swing.JPanel hoaDonThanhToan;
    private javax.swing.JButton in;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField maKhachTF;
    private javax.swing.JLayeredPane mainLP;
    private javax.swing.JPanel menu;
    private javax.swing.JTextField nhapTimKiem;
    private javax.swing.JButton sauBT;
    private javax.swing.JLabel tenTK;
    private javax.swing.JLabel thanhTienTF;
    private javax.swing.JButton thanhToanBT;
    private javax.swing.JButton thucDonBT;
    private javax.swing.JTextField tienKhachTraTF;
    private javax.swing.JTextField tienThuaTF;
    private javax.swing.JButton timKiem;
    private javax.swing.JLabel tongCongTF;
    private javax.swing.JLabel trangCF;
    private javax.swing.JButton truBT;
    private javax.swing.JButton truocBT;
    private javax.swing.JTextField vatTF;
    private javax.swing.JButton xoaBT;
    // End of variables declaration//GEN-END:variables
}
