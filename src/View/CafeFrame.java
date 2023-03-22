
package View;

import DAO.BanDAO;
import DAO.CafeDAO;
import DAO.ChiTietDonHangDAO;
import DAO.HoaDonDAO;
import DAO.NhanVienDAO;
import Model.Ban;
import Model.Cafe;
import Model.ChiTietDonHang;
import Model.HoaDon;
import Model.NhanVien;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class CafeFrame extends javax.swing.JFrame implements ActionListener{
    private CafeDAO cfdao;
    private BanDAO bdao;
    private HoaDonDAO hddao;
    private ChiTietDonHangDAO ctdhdao;
    private NhanVienDAO nvdao;
    private DefaultTableModel model2;
    private JButton bt;
    private long tongCong, thanhTien;
    NhanVien nv2;
    public String maNhanVien;
    private String mb;
    Ban ban;
    private String mahd;
    public CafeFrame(NhanVien nv, Ban b){
        initComponents();
        nv2 = new NhanVien();
        nv2 = nv;
        ban = new Ban();
        ban = b;
        mb = b.getMaBan();
        cfdao = new CafeDAO();
        bdao = new BanDAO();
        hddao = new HoaDonDAO();
        ctdhdao = new ChiTietDonHangDAO();
        nvdao = new NhanVienDAO();
        tenTK.setText("Xin chào " + nv.getHoVaTen());
        maNhanVien = nv.getMaNhanVien();
        thucDonBT.setBackground(new Color(0, 102, 102));
        thucDonBT.setForeground(Color.white);
        banBT.setBackground(Color.white);
        banBT.setForeground(Color.black);
        tebBanLB.setText(ban.getTenBan());
        trangThaiLB.setText("");
        setCTHD();
        setDataMonCafe(cfdao.getAllCafeKD());
        setHoaDon(hddao.getDSHDTheoBan(mb));
    }
    
    public void setDataMonCafe(List<Cafe> lcf){
        dsThucDon.removeAll();
        sauBT.setEnabled(false);
        int trang = Integer.parseInt(trangCF.getText());
        if(trang > 1)
            truocBT.setEnabled(true);
        else
            truocBT.setEnabled(false);
        int dem = 0;
        for(Cafe cf : lcf){
            dem++;
            if(dem > (trang - 1)*21 && dem <= trang*21){
                bt = new JButton(cf.getTenCafe() + " " + cf.getGiaBan() +"đ");
                bt.setSize(60, 60);
                bt.setBackground(Color.white);
                bt.addActionListener(this);
                bt.setActionCommand(String.valueOf(cf.getMaCafe()));
                dsThucDon.add(bt);
            }
        }
        if(dem <= trang*21)
            for(int i = ++dem; i <= trang*21; i++){
                bt = new JButton();
                bt.setSize(60, 60);
                bt.setBackground(Color.white);
                dsThucDon.add(bt);
                bt.setEnabled(false);
            }
        else
            sauBT.setEnabled(true);
        dsThucDon.updateUI();
    }
    public void enableBTfalse(){
        truBT.setEnabled(false);
        congBT.setEnabled(false);
        xoaBT.setEnabled(false);
        in.setText("Hoàn thành");
        baoCheBien.setText("Đi về");
        thanhToanBT.setVisible(false);
    }
    public void enableBTtrue(){
        truBT.setEnabled(true);
        congBT.setEnabled(true);
        xoaBT.setEnabled(true);
        in.setText("In");
        baoCheBien.setText("Báo chế biến");
        thanhToanBT.setVisible(true);
        model2.setRowCount(0);
        trangThaiLB.setText("");
        phieuKhachTF.setText("");
        tongCongTF.setText("");
        chietKhauTF.setText("");
        vatTF.setText("");
        thanhTienTF.setText("");
        tienKhachTraTF.setText("");
        tienThuaTF.setText("");
    }
    
    public void setHoaDon(List<HoaDon> lhd){
        HDBanPanel.removeAll();
        myButton bt;
        int dem = 0;
        for(HoaDon hd : lhd){
            dem++;
            if(dem <= ban.getSoCho()){
                bt = new myButton("Hóa đơn " + dem);
                bt.setSize(30, 30);
                bt.setBackground(new Color(102,153,255));
                bt.setHoverBackgroundColor(new Color(51,255,0));
                bt.setPressedBackgroundColor(new Color(0,153,153));
                bt.setForeground(Color.white);
                bt.addActionListener(this);
                bt.setActionCommand(hd.getMaHoaDon());
                HDBanPanel.add(bt);
            }
            if(dem == ban.getSoCho()){
                setDataCTHD(ctdhdao.getChiTietDonHang(hd.getMaHoaDon()));
                setDataHoaDon(hd.getMaHoaDon());
                mahd = hd.getMaHoaDon();
                enableBTfalse();
            }
        }
        dem++;
        if(dem <= ban.getSoCho()){
            bt = new myButton("Hóa đơn " + dem);
            bt.setSize(30, 30);
            bt.setBackground(Color.white);
            bt.addActionListener(this);
            bt.setActionCommand("00000");
            mahd = "00000";
            HDBanPanel.add(bt);
            dem++;
            for(int i = dem; i <= ban.getSoCho(); i++){
                bt = new myButton();
                bt.setSize(30, 30);
                bt.setBackground(Color.white);
                HDBanPanel.add(bt);
                bt.setEnabled(false);
            }
        }
        HDBanPanel.updateUI();
    }
    
    public void setCTHD(){
        model2 = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        chiTietHDTB.setModel(model2);
        model2.addColumn("Tên Cafe");
        model2.addColumn("Số lượng");
        model2.addColumn("Đơn giá");
        model2.addColumn("Thành tiền");
    }
    
    public void setDataCTHD(List<ChiTietDonHang> cthdList){
        model2.setRowCount(0);
        for(ChiTietDonHang cthd : cthdList){
            model2.addRow(new Object[]{
                cthd.getTenCafe(),
                cthd.getSoLuong(),
                cthd.getDonGia(),
                cthd.getThanhTien()
            });
        }
    }
    
    public void setDataHoaDon(String maHD){
        HoaDon hd = hddao.getChiTiet1HD(maHD);
        Ban b = new Ban();
        b = bdao.getBan(hd.getMaBan());
        trangThaiLB.setText(hd.getTinhTrangDon());
        phieuKhachTF.setText(String.valueOf(hd.getPhieuKhach()));
        tongCongTF.setText(String.valueOf(hd.getTongCong()));
        chietKhauTF.setText(String.valueOf(hd.getChietKhau()));
        vatTF.setText(String.valueOf(hd.getVat()));
        long tt = hd.getTongCong()*(100-hd.getChietKhau()+hd.getVat())/100;
        thanhTienTF.setText(String.valueOf(tt));
        tienKhachTraTF.setText(String.valueOf(hd.getTienKhachTra()));
        tienThuaTF.setText(String.valueOf(hd.getTienKhachTra()-tt));
        setDataCTHD(ctdhdao.getChiTietDonHang(maHD));
    }
    
    //Bắt sự kiện nhấn vào các món cafe hoặc hóa đơn
    @Override
    public void actionPerformed(ActionEvent event) {
        String maKT;
        String ma = event.getActionCommand();
        maKT = ma.substring(0, 2);
        if("CF".equals(maKT) && mahd.equals("00000")){
            int ck, v;
            Cafe cf = new Cafe();
            cf = cfdao.getCafe(ma);
            int row = model2.getRowCount();
            int kt = 0;
            for(int i = 0; i < row; i++)
                if(String.valueOf(model2.getValueAt(i, 0)).equals(cf.getTenCafe())){
                    kt = 1;
                }
            if(kt == 0){
                model2.addRow(new Object[]{
                        cf.getTenCafe(),
                        1,
                        cf.getGiaBan(),
                        cf.getGiaBan()
                    });
            }
            setThanhTienChiTiet();
            setTongCong();
            setThanhTien();
        }
        else
            if("HD".equals(maKT)){
                setDataHoaDon(ma);
                setDataCTHD(ctdhdao.getChiTietDonHang(ma));
                enableBTfalse();
                mahd = ma;
            }
            else
                if(ma.equals("00000")){
                    mahd = ma;
                    enableBTtrue();
                } 
    }
    
    public void setTongCong(){
        tongCong = 0;
        for(int i = 0; i < model2.getRowCount(); i++){
            tongCong += Integer.parseInt(String.valueOf(chiTietHDTB.getValueAt(i, 3)));
        }
        tongCongTF.setText(String.valueOf(tongCong));
    }
    
    public void setThanhTienChiTiet(){
        int row = model2.getRowCount();
        for(int i = 0; i < row; i++){
            int sl = (int) model2.getValueAt(i, 1);
            long dg = (long) model2.getValueAt(i, 2);
            model2.setValueAt(sl*dg, i, 3);
        }
    }
    
    public void setThanhTien(){
        int ck, v;
        try {
            if("".equals(chietKhauTF.getText()))
                ck = 0;
            else
                ck = Integer.parseInt(chietKhauTF.getText());
        } catch (Exception e1) {
            ck = 0;
            System.out.println(e1);
        }
        
        try {
            if("".equals(vatTF.getText()))
                v = 0;
            else
                v = Integer.parseInt(vatTF.getText());
        } catch (Exception e2) {
            v = 0;
            System.out.println(e2);
        }
        thanhTien = tongCong*(100-ck+v)/100;
        thanhTienTF.setText(String.valueOf(thanhTien));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainLP = new javax.swing.JLayeredPane();
        dsThucDon = new javax.swing.JPanel();
        truocBT = new javax.swing.JButton();
        sauBT = new javax.swing.JButton();
        trangCF = new javax.swing.JLabel();
        danhMuc = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tatCaBT = new javax.swing.JButton();
        cafeDXBT = new javax.swing.JButton();
        thachBT = new javax.swing.JButton();
        smoothiesBT = new javax.swing.JButton();
        socolaBT = new javax.swing.JButton();
        espressoBT = new javax.swing.JButton();
        traiCayBT = new javax.swing.JButton();
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
        phieuKhachTF = new javax.swing.JTextField();
        tongCongTF = new javax.swing.JLabel();
        thanhTienTF = new javax.swing.JLabel();
        HDBanPanel = new javax.swing.JPanel();
        tebBanLB = new javax.swing.JLabel();
        trangThaiLB = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        thucDonBT = new javax.swing.JButton();
        banBT = new javax.swing.JButton();
        nhapTimKiem = new javax.swing.JTextField();
        timKiem = new javax.swing.JButton();
        dangXuatBT = new javax.swing.JButton();
        tenTK = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bán Cafe");

        mainLP.setBackground(new java.awt.Color(255, 255, 255));
        mainLP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dsThucDon.setBackground(new java.awt.Color(255, 255, 255));
        dsThucDon.setLayout(new java.awt.GridLayout(7, 0, 15, 15));

        truocBT.setBackground(new java.awt.Color(255, 255, 255));
        truocBT.setText("<");
        truocBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                truocBTActionPerformed(evt);
            }
        });

        sauBT.setBackground(new java.awt.Color(255, 255, 255));
        sauBT.setText(">");
        sauBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sauBTActionPerformed(evt);
            }
        });

        trangCF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        trangCF.setText("1");

        danhMuc.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Danh mục:");

        tatCaBT.setBackground(new java.awt.Color(0, 153, 153));
        tatCaBT.setForeground(new java.awt.Color(255, 255, 255));
        tatCaBT.setText("Tất cả");
        tatCaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tatCaBTActionPerformed(evt);
            }
        });

        cafeDXBT.setBackground(new java.awt.Color(0, 153, 153));
        cafeDXBT.setForeground(new java.awt.Color(255, 255, 255));
        cafeDXBT.setText("Cafe đá xay");
        cafeDXBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cafeDXBTActionPerformed(evt);
            }
        });

        thachBT.setBackground(new java.awt.Color(0, 153, 153));
        thachBT.setForeground(new java.awt.Color(255, 255, 255));
        thachBT.setText("Thạch và topping");
        thachBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thachBTActionPerformed(evt);
            }
        });

        smoothiesBT.setBackground(new java.awt.Color(0, 153, 153));
        smoothiesBT.setForeground(new java.awt.Color(255, 255, 255));
        smoothiesBT.setText("Smoothies");
        smoothiesBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smoothiesBTActionPerformed(evt);
            }
        });

        socolaBT.setBackground(new java.awt.Color(0, 153, 153));
        socolaBT.setForeground(new java.awt.Color(255, 255, 255));
        socolaBT.setText("Socola");
        socolaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                socolaBTActionPerformed(evt);
            }
        });

        espressoBT.setBackground(new java.awt.Color(0, 153, 153));
        espressoBT.setForeground(new java.awt.Color(255, 255, 255));
        espressoBT.setText("Cafe Espresso");
        espressoBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espressoBTActionPerformed(evt);
            }
        });

        traiCayBT.setBackground(new java.awt.Color(0, 153, 153));
        traiCayBT.setForeground(new java.awt.Color(255, 255, 255));
        traiCayBT.setText("Trái cây đá xay");
        traiCayBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traiCayBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout danhMucLayout = new javax.swing.GroupLayout(danhMuc);
        danhMuc.setLayout(danhMucLayout);
        danhMucLayout.setHorizontalGroup(
            danhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhMucLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tatCaBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socolaBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cafeDXBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thachBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(smoothiesBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(espressoBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(traiCayBT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        danhMucLayout.setVerticalGroup(
            danhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, danhMucLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(danhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tatCaBT)
                    .addComponent(jLabel2)
                    .addComponent(cafeDXBT)
                    .addComponent(thachBT)
                    .addComponent(smoothiesBT)
                    .addComponent(socolaBT)
                    .addComponent(espressoBT)
                    .addComponent(traiCayBT))
                .addContainerGap())
        );

        mainLP.setLayer(dsThucDon, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainLP.setLayer(truocBT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainLP.setLayer(sauBT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainLP.setLayer(trangCF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainLP.setLayer(danhMuc, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout mainLPLayout = new javax.swing.GroupLayout(mainLP);
        mainLP.setLayout(mainLPLayout);
        mainLPLayout.setHorizontalGroup(
            mainLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(danhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dsThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(truocBT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trangCF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sauBT)
                .addGap(311, 311, 311))
        );
        mainLPLayout.setVerticalGroup(
            mainLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLPLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(danhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dsThucDon, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        phieuKhachTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        tongCongTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tongCongTF.setText("0");

        thanhTienTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        thanhTienTF.setText("0");

        HDBanPanel.setBackground(new java.awt.Color(255, 255, 255));
        HDBanPanel.setLayout(new java.awt.GridLayout(1, 0));

        tebBanLB.setText("Bàn 0");

        trangThaiLB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        trangThaiLB.setForeground(new java.awt.Color(0, 102, 102));
        trangThaiLB.setText("Đang xử lý");

        javax.swing.GroupLayout hoaDonThanhToanLayout = new javax.swing.GroupLayout(hoaDonThanhToan);
        hoaDonThanhToan.setLayout(hoaDonThanhToanLayout);
        hoaDonThanhToanLayout.setHorizontalGroup(
            hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HDBanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(hoaDonThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoaDonThanhToanLayout.createSequentialGroup()
                        .addComponent(truBT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(congBT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(xoaBT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(hoaDonThanhToanLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(21, 21, 21)
                        .addComponent(phieuKhachTF))
                    .addGroup(hoaDonThanhToanLayout.createSequentialGroup()
                        .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(hoaDonThanhToanLayout.createSequentialGroup()
                                .addComponent(tebBanLB)
                                .addGap(33, 33, 33)
                                .addComponent(trangThaiLB))
                            .addGroup(hoaDonThanhToanLayout.createSequentialGroup()
                                .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(baoCheBien, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(thanhToanBT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        hoaDonThanhToanLayout.setVerticalGroup(
            hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoaDonThanhToanLayout.createSequentialGroup()
                .addComponent(HDBanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tebBanLB)
                    .addComponent(trangThaiLB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(congBT)
                    .addComponent(truBT)
                    .addComponent(xoaBT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(hoaDonThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(phieuKhachTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap())
        );

        menu.setBackground(new java.awt.Color(102, 153, 255));

        thucDonBT.setBackground(new java.awt.Color(0, 153, 153));
        thucDonBT.setForeground(new java.awt.Color(255, 255, 255));
        thucDonBT.setText("Thực đơn");
        thucDonBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thucDonBTActionPerformed(evt);
            }
        });

        banBT.setBackground(new java.awt.Color(255, 255, 255));
        banBT.setText("Bàn");
        banBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banBTActionPerformed(evt);
            }
        });

        nhapTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapTimKiemActionPerformed(evt);
            }
        });
        nhapTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nhapTimKiemKeyPressed(evt);
            }
        });

        timKiem.setBackground(new java.awt.Color(255, 255, 255));
        timKiem.setText("Tìm kiếm");
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
                    .addComponent(mainLP)
                    .addComponent(hoaDonThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void banBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banBTActionPerformed
        BanFrame b = new BanFrame(nv2);
        b.setVisible(true);
        b.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_banBTActionPerformed

    private void thucDonBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thucDonBTActionPerformed

    }//GEN-LAST:event_thucDonBTActionPerformed

    private void thanhToanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thanhToanBTActionPerformed
        if(tienKhachTraTF.getText().equals("") || phieuKhachTF.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập đầy đủ!");
        }
        else{
            try {
                HoaDon hd = new HoaDon();
                if(chietKhauTF.getText().equals(""))
                    hd.setChietKhau(0);
                else
                    hd.setChietKhau(Integer.parseInt(chietKhauTF.getText()));
                if(vatTF.getText().equals(""))
                    hd.setVat(0);
                else
                    hd.setVat(Integer.parseInt(vatTF.getText()));
                hd.setTienKhachTra(Long.parseLong(tienKhachTraTF.getText()));
                hd.setPhieuKhach(Integer.parseInt(phieuKhachTF.getText()));
                hd.setMaNhanVien(maNhanVien);
                hd.setMaBan(mb);
                hddao.addHoaDon(hd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ChiTietDonHang ctdh;
        if(model2.getRowCount() == 0)
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn món!");
        else
        {
            for(int i = 0; i < model2.getRowCount(); i++){
                try {
                    ctdh = new ChiTietDonHang();
                    ctdh.setMaHoaDon(hddao.getMaHDMoiThem());
                    Cafe cf = new Cafe();
                    cf = cfdao.getCafeTen((String) chiTietHDTB.getValueAt(i, 0));
                    ctdh.setMaCafe(cf.getMaCafe());
                    ctdh.setSoLuong(Integer.parseInt(String.valueOf(chiTietHDTB.getValueAt(i, 1))));
                    ctdh.setDonGia(Long.parseLong(String.valueOf(chiTietHDTB.getValueAt(i, 2))));
                    ctdhdao.addCTDH(ctdh);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(hddao.kt == 1 && ctdhdao.kt == 1){
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                BanFrame b = new BanFrame(nv2);
                b.setVisible(true);
                b.setLocationRelativeTo(null);
                this.dispose();
            }
            else
                JOptionPane.showMessageDialog(this, "Thanh toán thất bại!");
        }
    }//GEN-LAST:event_thanhToanBTActionPerformed

    private void chietKhauTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chietKhauTFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            setThanhTien();
        }
    }//GEN-LAST:event_chietKhauTFKeyPressed

    private void vatTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vatTFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            setThanhTien();
        }
    }//GEN-LAST:event_vatTFKeyPressed

    private void tienKhachTraTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tienKhachTraTFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            long tienThua, tKhachTra;
            try {
                tKhachTra = Integer.parseInt(tienKhachTraTF.getText());
            } catch (Exception e) {
                tKhachTra = 0;
                System.out.println(e);
            }
            if(tKhachTra < thanhTien)
                JOptionPane.showMessageDialog(this, "Tiền khách trả phải lớn hơn hặc bằng thành tiền!");
            else{
                tienThua = tKhachTra - thanhTien;
                tienThuaTF.setText(String.valueOf(tienThua));
            }
        }
    }//GEN-LAST:event_tienKhachTraTFKeyPressed

    private void sauBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sauBTActionPerformed
        int trang = Integer.parseInt(trangCF.getText()) + 1;
        trangCF.setText(String.valueOf(trang));
        setDataMonCafe(cfdao.getAllCafeKD());
    }//GEN-LAST:event_sauBTActionPerformed

    private void truocBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_truocBTActionPerformed
        int trang = Integer.parseInt(trangCF.getText()) - 1;
        trangCF.setText(String.valueOf(trang));
        setDataMonCafe(cfdao.getAllCafeKD());
    }//GEN-LAST:event_truocBTActionPerformed

    private void tatCaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tatCaBTActionPerformed
        trangCF.setText("1");
        setDataMonCafe(cfdao.getAllCafeKD());
    }//GEN-LAST:event_tatCaBTActionPerformed

    private void socolaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_socolaBTActionPerformed
        // Socola
        trangCF.setText("1");
        setDataMonCafe(cfdao.getNhomCafeKD("Socola"));
    }//GEN-LAST:event_socolaBTActionPerformed

    private void cafeDXBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cafeDXBTActionPerformed
        // Cafe đá xay
        trangCF.setText("1");
        setDataMonCafe(cfdao.getNhomCafeKD("Cafe đá xay"));
    }//GEN-LAST:event_cafeDXBTActionPerformed

    private void thachBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thachBTActionPerformed
        // Thạch và topping
        trangCF.setText("1");
        setDataMonCafe(cfdao.getNhomCafeKD("Thạch và topping"));
    }//GEN-LAST:event_thachBTActionPerformed

    private void smoothiesBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smoothiesBTActionPerformed
        // Smoothies
        trangCF.setText("1");
        setDataMonCafe(cfdao.getNhomCafeKD("Smoothies"));
    }//GEN-LAST:event_smoothiesBTActionPerformed

    private void espressoBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espressoBTActionPerformed
        // Cafe Espresso
        trangCF.setText("1");
        setDataMonCafe(cfdao.getNhomCafeKD("Cafe Espresso"));
    }//GEN-LAST:event_espressoBTActionPerformed

    private void traiCayBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traiCayBTActionPerformed
        // Trái cây đá xay
        trangCF.setText("1");
        setDataMonCafe(cfdao.getNhomCafeKD("Trái cây đá xay"));
    }//GEN-LAST:event_traiCayBTActionPerformed

    private void congBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_congBTActionPerformed
        int row = chiTietHDTB.getSelectedRow();
        if(row != -1){
            int sl =  (int) chiTietHDTB.getValueAt(row, 1);
            chiTietHDTB.setValueAt(sl + 1, row, 1);
            setThanhTienChiTiet();
            setTongCong();
            setThanhTien();
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn Cafe!");
    }//GEN-LAST:event_congBTActionPerformed

    private void truBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_truBTActionPerformed
        int row = chiTietHDTB.getSelectedRow();
        if(row != -1){
            int sl =  (int) chiTietHDTB.getValueAt(row, 1);
            if(sl > 1){
                chiTietHDTB.setValueAt(sl - 1, row, 1);
                setThanhTienChiTiet();
                setTongCong();
                setThanhTien();
            } 
            else
                JOptionPane.showMessageDialog(this, "Số lượng đang là 1!");
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn Cafe!");
    }//GEN-LAST:event_truBTActionPerformed

    private void xoaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaBTActionPerformed
        int row = chiTietHDTB.getSelectedRow();
        if(row != -1)
            while(row != -1){
                model2.removeRow(row);
                row = chiTietHDTB.getSelectedRow();
                setThanhTienChiTiet();
                setTongCong();
                setThanhTien();
            }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn Cafe cần xóa!");
    }//GEN-LAST:event_xoaBTActionPerformed

    private void baoCheBienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baoCheBienActionPerformed
        if("Báo chế biến".equals(baoCheBien.getText()))
            JOptionPane.showMessageDialog(this, "Đã báo chế biến!");
        else{
            hddao.setTrangThai(mahd, "Đã về");
            if(hddao.ktTT == 1){
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                CafeFrame cf = new CafeFrame(nv2, ban);
                cf.setVisible(true);
                cf.setLocationRelativeTo(null);
                this.dispose();
            }
            else
                JOptionPane.showMessageDialog(this, "Cập nhật không thành công!");
        }
    }//GEN-LAST:event_baoCheBienActionPerformed

    private void inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inActionPerformed
        if("In".equals(in.getText()))
            JOptionPane.showMessageDialog(this, "Đã in!");
        else{
            hddao.setTrangThai(mahd, "Hoàn thành");
            if(hddao.ktTT == 1){
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                CafeFrame cf = new CafeFrame(nv2, ban);
                cf.setVisible(true);
                cf.setLocationRelativeTo(null);
                this.dispose();
            }
            else
                JOptionPane.showMessageDialog(this, "Cập nhật không thành công!");
        }
    }//GEN-LAST:event_inActionPerformed

    private void dangXuatBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangXuatBTActionPerformed
        DangNhapFrame dn = new DangNhapFrame();
        dn.setVisible(true);
        dn.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_dangXuatBTActionPerformed

    private void nhapTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nhapTimKiemActionPerformed

    private void timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemActionPerformed
        List<Cafe> list = new ArrayList<>();
        list = cfdao.getTimKiemTen(nhapTimKiem.getText());
        if(list.isEmpty()){
            JOptionPane.showMessageDialog(this, "Không có kết quả nào!");
            setDataMonCafe(cfdao.getAllCafeKD());  
        }
        else
            setDataMonCafe(list);       
    }//GEN-LAST:event_timKiemActionPerformed

    private void nhapTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nhapTimKiemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            List<Cafe> list = new ArrayList<>();
            list = cfdao.getTimKiemTen(nhapTimKiem.getText());
            if(list.isEmpty()){
                JOptionPane.showMessageDialog(this, "Không có kết quả nào!");
                setDataMonCafe(cfdao.getAllCafeKD());  
            }
            else
                setDataMonCafe(list);
        }
    }//GEN-LAST:event_nhapTimKiemKeyPressed

//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                BanCafeFrame bc = new BanCafeFrame();
//                bc.setVisible(true);
//                bc.setLocationRelativeTo(null);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HDBanPanel;
    private javax.swing.JButton banBT;
    private javax.swing.JButton baoCheBien;
    private javax.swing.JButton cafeDXBT;
    private javax.swing.JTable chiTietHDTB;
    private javax.swing.JTextField chietKhauTF;
    private javax.swing.JButton congBT;
    private javax.swing.JButton dangXuatBT;
    private javax.swing.JPanel danhMuc;
    private javax.swing.JPanel dsThucDon;
    private javax.swing.JButton espressoBT;
    private javax.swing.JPanel hoaDonThanhToan;
    private javax.swing.JButton in;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane mainLP;
    private javax.swing.JPanel menu;
    private javax.swing.JTextField nhapTimKiem;
    private javax.swing.JTextField phieuKhachTF;
    private javax.swing.JButton sauBT;
    private javax.swing.JButton smoothiesBT;
    private javax.swing.JButton socolaBT;
    private javax.swing.JButton tatCaBT;
    private javax.swing.JLabel tebBanLB;
    private javax.swing.JLabel tenTK;
    private javax.swing.JButton thachBT;
    private javax.swing.JLabel thanhTienTF;
    private javax.swing.JButton thanhToanBT;
    private javax.swing.JButton thucDonBT;
    private javax.swing.JTextField tienKhachTraTF;
    private javax.swing.JTextField tienThuaTF;
    private javax.swing.JButton timKiem;
    private javax.swing.JLabel tongCongTF;
    private javax.swing.JButton traiCayBT;
    private javax.swing.JLabel trangCF;
    private javax.swing.JLabel trangThaiLB;
    private javax.swing.JButton truBT;
    private javax.swing.JButton truocBT;
    private javax.swing.JTextField vatTF;
    private javax.swing.JButton xoaBT;
    // End of variables declaration//GEN-END:variables
}
