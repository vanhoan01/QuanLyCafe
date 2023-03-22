
package View;

import DAO.BanDAO;
import DAO.CafeDAO;
import DAO.ChiTietDonHangDAO;
import DAO.HoaDonDAO;
import DAO.NhanVienDAO;
import Model.Ban;
import Model.ChiTietDonHang;
import Model.HoaDon;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class QLHoaDonFrame extends javax.swing.JFrame {
    private CafeDAO cfdao;
    private HoaDonDAO hddao;
    private ChiTietDonHangDAO ctdhdao;
    private NhanVienDAO nvdao;
    private BanDAO bdao;
    private DefaultTableModel mdHD, mdCTHD;
    public String hvten = "";
    public QLHoaDonFrame(String hvt) {
        initComponents();
        cfdao = new CafeDAO();
        hddao = new HoaDonDAO();
        ctdhdao = new ChiTietDonHangDAO();
        nvdao = new NhanVienDAO();
        bdao = new BanDAO();
        hvten = hvt;
        hoVaTen.setText("Xin chào "+hvt+"!");
        setTableHoaDon(hddao.getAllHoaDon());
        setTableCTHD();
    }
    
    public void setTableHoaDon(List<HoaDon> lhd){
        mdHD = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        hoaDonTable.setModel(mdHD);
        mdHD.addColumn("STT");
        mdHD.addColumn("Mã hóa đơn");
        mdHD.addColumn("Thời gian");
        mdHD.addColumn("Thành tiền");
        mdHD.addColumn("Trạng thái");
        mdHD.addColumn("Nhân viên");
        ListSelectionModel lsm = hoaDonTable.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                int row = hoaDonTable.getSelectedRow();
                if(row == -1)
                    return;
                String mhd = (String) hoaDonTable.getValueAt(row, 1);
                setThonTinHD(row, mhd);
                setDataCTHD(ctdhdao.getChiTietDonHang(mhd));
            }
        });
        setDataHD(lhd);
    }
    public void setDataHD(List<HoaDon> lhd){
        mdHD.setRowCount(0);
        int dem = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        for(HoaDon hd : lhd){
            dem++;
            String tg = formatter.format(hd.getThoiGian());
            mdHD.addRow(new Object[]{
                dem,
                hd.getMaHoaDon(),
                tg,
                hd.getThanhTien(),
                hd.getTinhTrangDon(),
                hd.getTenNhanVien()
            });
        }
    }
    
    public void setTableCTHD(){
        mdCTHD = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        chiTietHDTB.setModel(mdCTHD);
        mdCTHD.addColumn("Tên Cafe");
        mdCTHD.addColumn("Đơn giá");
        mdCTHD.addColumn("Số lượng");
        mdCTHD.addColumn("Thành tiền");
    }
    
    public void setDataCTHD(List<ChiTietDonHang> cthdList){ 
        mdCTHD.setRowCount(0);
        for(ChiTietDonHang cthd : cthdList){
            mdCTHD.addRow(new Object[]{
                cthd.getTenCafe(),
                cthd.getDonGia(),
                cthd.getSoLuong(),
                cthd.getThanhTien()
            });
        }
    }
    
    public void setThonTinHD(int row, String mhd){
        HoaDon hd = hddao.getChiTiet1HD(mhd);
        Ban b = new Ban();
        b = bdao.getBan(hd.getMaBan());
        maHDTF.setText(String.valueOf(hoaDonTable.getValueAt(row, 1)));
        maKTF.setText(String.valueOf(hd.getPhieuKhach()));
        thoiGianTF.setText(String.valueOf(hoaDonTable.getValueAt(row, 2)));
        tongCongTF.setText(String.format("%,d", hd.getTongCong()));
        chietKhauTF.setText(String.valueOf(hd.getChietKhau()));
        vatTF.setText(String.valueOf(hd.getVat()));
        long tt = (long) hoaDonTable.getValueAt(row, 3);
        thanhTienTF.setText(String.format("%,d", tt));
        tienKhachTraTF.setText(String.format("%,d", hd.getTienKhachTra()));
        tienThuaTF.setText(String.format("%,d", hd.getTienKhachTra()- tt));
        nhanVienTF.setText((String) hoaDonTable.getValueAt(row, 5));
        banTF.setText(b.getTenBan());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tongQuan = new javax.swing.JButton();
        QLCafe = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        dangXuat = new javax.swing.JButton();
        hoVaTen = new javax.swing.JButton();
        QLNhanVien = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hoaDonTable = new javax.swing.JTable();
        xuatFile = new javax.swing.JButton();
        nvnv = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        chiTietHDTB = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tongCongTF = new javax.swing.JLabel();
        chietKhauTF = new javax.swing.JLabel();
        vatTF = new javax.swing.JLabel();
        thanhTienTF = new javax.swing.JLabel();
        tienKhachTraTF = new javax.swing.JLabel();
        tienThuaTF = new javax.swing.JLabel();
        thoiGianTF = new javax.swing.JLabel();
        maKTF = new javax.swing.JLabel();
        maHDTF = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        nhanVienTF = new javax.swing.JLabel();
        banTF = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        thoiGianCBB = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        trangThaiCBB = new javax.swing.JComboBox<>();

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

        QLCafe.setBackground(new java.awt.Color(255, 255, 255));
        QLCafe.setText("Cafe");
        QLCafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QLCafeActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Hóa đơn");

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
                .addComponent(QLCafe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QLNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dangXuat))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tongQuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(QLCafe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton3)
                .addComponent(QLNhanVien))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(dangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hoVaTen))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Hóa đơn");

        hoaDonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Thời gian", "Tổng thanh toán", "Trạng thái", "Nhân viên"
            }
        ));
        jScrollPane1.setViewportView(hoaDonTable);

        xuatFile.setBackground(new java.awt.Color(255, 255, 255));
        xuatFile.setText("Xuất file");
        xuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatFileActionPerformed(evt);
            }
        });

        nvnv.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Thông tin");

        chiTietHDTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Cafe", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ));
        jScrollPane4.setViewportView(chiTietHDTB);

        jLabel11.setText("Thời gian:");

        jLabel5.setText("Tổng cộng:");

        jLabel12.setText("Chiết khấu:");

        jLabel4.setText("VAT:");

        jLabel8.setText("Thành tiền:");

        jLabel6.setText("Tiền khách trả:");

        jLabel7.setText("Tiền thừa");

        jLabel9.setText("Phiếu khách");

        jLabel13.setText("Mã hóa đơn:");

        tongCongTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tongCongTF.setText("0");
        tongCongTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chietKhauTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chietKhauTF.setText("0");
        chietKhauTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        vatTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        vatTF.setText("0");
        vatTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        thanhTienTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        thanhTienTF.setText("0");
        thanhTienTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tienKhachTraTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tienKhachTraTF.setText("0");
        tienKhachTraTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tienThuaTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tienThuaTF.setText("0");
        tienThuaTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        thoiGianTF.setText("0");
        thoiGianTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        maKTF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        maKTF.setText("0");
        maKTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        maHDTF.setText("0");
        maHDTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setText("Nhân viên:");

        nhanVienTF.setText("0");
        nhanVienTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        banTF.setText("0");
        banTF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setText("Bàn:");

        javax.swing.GroupLayout nvnvLayout = new javax.swing.GroupLayout(nvnv);
        nvnv.setLayout(nvnvLayout);
        nvnvLayout.setHorizontalGroup(
            nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nvnvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .addGroup(nvnvLayout.createSequentialGroup()
                        .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(banTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tongCongTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chietKhauTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vatTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thanhTienTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tienKhachTraTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tienThuaTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thoiGianTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maKTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maHDTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nhanVienTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        nvnvLayout.setVerticalGroup(
            nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nvnvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(maHDTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(maKTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(thoiGianTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tongCongTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(chietKhauTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(vatTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(thanhTienTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tienKhachTraTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tienThuaTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(nhanVienTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(nvnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(banTF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Thời gian:");

        thoiGianCBB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hôm nay", "Hôm qua", "Tháng này" }));
        thoiGianCBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoiGianCBBActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng thái:");

        trangThaiCBB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang xử lý", "Hoàn thành", "Đã về" }));
        trangThaiCBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trangThaiCBBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trangThaiCBB, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(thoiGianCBB, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nvnv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(xuatFile)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(thoiGianCBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(trangThaiCBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(xuatFile)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nvnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trangThaiCBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trangThaiCBBActionPerformed
        try {
            int lc = (int) trangThaiCBB.getSelectedIndex();
            if(lc == 0)
                setDataHD(hddao.getAllHoaDon());
            else
                if(lc == 1)
                    setDataHD(hddao.getHDTrangThai("Đang xử lý"));
                else
                    if(lc == 2)
                        setDataHD(hddao.getHDTrangThai("Hoàn thành"));
                    else
                        if(lc == 3)
                            setDataHD(hddao.getHDTrangThai("Đã về"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_trangThaiCBBActionPerformed

    private void thoiGianCBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoiGianCBBActionPerformed
        int lc = -1;
        try {
            lc = (int) thoiGianCBB.getSelectedIndex();
        } catch (Exception e) {
        }
        if(lc == -1)
            return;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        java.util.Date utilKT = new java.util.Date();
        utilKT = cal.getTime();
        java.sql.Date kt = new java.sql.Date(utilKT.getTime());
        if(lc == 0)
            setDataHD(hddao.getAllHoaDon());
        else
            if(lc == 1)
                setDataHD(hddao.getDSHDTrongNgay());
            else
                if(lc == 2){
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    java.util.Date utilBD = new java.util.Date();
                    utilBD = cal.getTime();
                    java.sql.Date bd = new java.sql.Date(utilBD.getTime());
                    setDataHD(hddao.getDSHDKhoangNgay(bd, bd));
                }
                else
                    if(lc == 3){
                        cal.set(Calendar.DAY_OF_MONTH, 1);
                        java.util.Date utilBD = new java.util.Date();
                        utilBD = cal.getTime();
                        java.sql.Date bd = new java.sql.Date(utilBD.getTime());
                        setDataHD(hddao.getDSHDKhoangNgay(bd, kt));
                    }
    }//GEN-LAST:event_thoiGianCBBActionPerformed

    private void QLCafeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLCafeActionPerformed
        QLCafeFrame cf = new QLCafeFrame(hvten);
        cf.setLocationRelativeTo(null);
        cf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_QLCafeActionPerformed

    private void QLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLNhanVienActionPerformed
        QLNhanVien nv = new QLNhanVien(hvten);
        nv.setLocationRelativeTo(null);
        nv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_QLNhanVienActionPerformed

    private void tongQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tongQuanActionPerformed
        ThongKeFrame tk = new ThongKeFrame(hvten);
        tk.setLocationRelativeTo(null);
        tk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_tongQuanActionPerformed

    private void dangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangXuatActionPerformed
        DangNhapFrame dn = new DangNhapFrame();
        dn.setVisible(true);
        dn.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_dangXuatActionPerformed

    private void xuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatFileActionPerformed
        JOptionPane.showMessageDialog(this, "Xuất file thành công!");
    }//GEN-LAST:event_xuatFileActionPerformed

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                QLHoaDonFrame hd = new QLHoaDonFrame();
//                hd.setVisible(true);
//                hd.setLocationRelativeTo(null);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton QLCafe;
    private javax.swing.JButton QLNhanVien;
    private javax.swing.JLabel banTF;
    private javax.swing.JTable chiTietHDTB;
    private javax.swing.JLabel chietKhauTF;
    private javax.swing.JButton dangXuat;
    private javax.swing.JButton hoVaTen;
    private javax.swing.JTable hoaDonTable;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel maHDTF;
    private javax.swing.JLabel maKTF;
    private javax.swing.JLabel nhanVienTF;
    private javax.swing.JPanel nvnv;
    private javax.swing.JLabel thanhTienTF;
    private javax.swing.JComboBox<String> thoiGianCBB;
    private javax.swing.JLabel thoiGianTF;
    private javax.swing.JLabel tienKhachTraTF;
    private javax.swing.JLabel tienThuaTF;
    private javax.swing.JLabel tongCongTF;
    private javax.swing.JButton tongQuan;
    private javax.swing.JComboBox<String> trangThaiCBB;
    private javax.swing.JLabel vatTF;
    private javax.swing.JButton xuatFile;
    // End of variables declaration//GEN-END:variables
}
