
package View;

import DAO.NhanVienDAO;
import Model.NhanVien;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class QLNhanVien extends javax.swing.JFrame {
    private DefaultTableModel model;
    private NhanVienDAO nvdao;
    public String hvten = "";
    public QLNhanVien(String hvt) {
        initComponents();
        nvdao = new NhanVienDAO();
        hvten = hvt;
        hoVaTen.setText("Xin chào "+hvt+"!");
        setNhanVienTable(nvdao.getAllNhanVien());
        huyBT.setEnabled(false);
    }

    public void setNhanVienTable(List<NhanVien> lnv){
        model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        nhanVienTB.setModel(model);
        model.addColumn("STT");
        model.addColumn("Mã nhân viên");
        model.addColumn("Họ và tên");
        model.addColumn("Số điện thoại");
        model.addColumn("Chức vụ");
        model.addColumn("Lương");
        setDataNVTable(lnv);
        ListSelectionModel lsm = nhanVienTB.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsm.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                int row = nhanVienTB.getSelectedRow();
                if(row == -1 || "Lưu".equals(themBT.getText()))
                    return;
                String mnv = String.valueOf(nhanVienTB.getValueAt(row, 1));
                NhanVien nv = new NhanVien();
                nv = nvdao.getNhanVien(mnv);
                setChiTietNV(nv);
                if(nv.getTrangThai().equals("Đang làm việc"))
                    nghiViecBT.setText("Nghỉ việc");
                else
                    nghiViecBT.setText("Làm việc");
            }
        });
    }
    
    public void setDataNVTable(List<NhanVien> lnv){
        model.setRowCount(0);
        int dem = 0;
        for(NhanVien nv : lnv){
            dem++;
            String hvt = nv.getHoVaTen();
            if(nv.getTrangThai().equals("Nghỉ việc"))
                hvt += " {Nghỉ}";
            model.addRow(new Object[]{
                dem,
                nv.getMaNhanVien(),
                hvt,
                nv.getSoDienThoai(),
                nv.getTenChucVu(),
                nv.getLuong()
            });
        }
    }
    
    public void setChiTietNV(NhanVien nv){
        maNVTF.setText(String.valueOf(nv.getMaNhanVien()));
        maNVTF.setEnabled(false);
        String hvt = nv.getHoVaTen();
            if(nv.getTrangThai().equals("Nghỉ việc"))
                hvt += " {Nghỉ}";
        hvtTF.setText(hvt);
        if(nv.getGioiTinh().equals("Nam"))
            gioTinhCCB.setSelectedIndex(0);
        else
            gioTinhCCB.setSelectedIndex(1);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strNS = formatter.format(nv.getNgaySinh());
        ngaySinhTF.setText(strNS);
        diaChiTF.setText(nv.getDiaChi());
        sdtTF.setText(nv.getSoDienThoai());
        emailTF.setText(nv.getEmail());
        cmndTF.setText(nv.getCmnd());
        chucVuTF.setSelectedItem(nv.getTenChucVu());
        luongTF.setText(String.valueOf(nv.getLuong()));
        taiKhoanTF.setText(nv.getTaiKhoan());
        matKhauTF.setText(nv.getMatKhau());
        quyenCBB.setSelectedItem(nv.getQuyen());
    }
    
    public NhanVien getNhanVien(){
        NhanVien nv = new NhanVien();
        try {
            nv.setHoVaTen(hvtTF.getText());
            nv.setGioiTinh(String.valueOf(gioTinhCCB.getSelectedItem()));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilNS = format.parse(ngaySinhTF.getText());
            java.sql.Date sqlNS = new java.sql.Date(utilNS.getTime());
            nv.setNgaySinh(sqlNS);
            nv.setDiaChi(diaChiTF.getText());
            nv.setSoDienThoai(sdtTF.getText());
            nv.setEmail(emailTF.getText());
            nv.setCmnd(cmndTF.getText());
            String maCV = "CV0" + (chucVuTF.getSelectedIndex()+1);
            nv.setMaChucVu(maCV);
            nv.setLuong(Long.parseLong(luongTF.getText()));
            nv.setTaiKhoan(taiKhoanTF.getText());
            nv.setMatKhau(matKhauTF.getText());
            String maQ = "PQ0" + (quyenCBB.getSelectedIndex()+1);
            nv.setMaQuyen(maQ);
        } catch (Exception e) {
            System.out.println(e);
        }
        return nv;
    }
    
    public void saveCancel(){
        themBT.setText("Thêm");
        huyBT.setEnabled(false);
        suaBT.setEnabled(true);
        nghiViecBT.setEnabled(true);
        setReset();
    }
    
    public void setReset(){
        maNVTF.setText("");
        hvtTF.setText("");
        gioTinhCCB.setSelectedIndex(0);
        ngaySinhTF.setText("");
        diaChiTF.setText("");
        sdtTF.setText("");
        emailTF.setText("");
        cmndTF.setText("");
        chucVuTF.setSelectedIndex(0);
        luongTF.setText("");
        taiKhoanTF.setText("");
        matKhauTF.setText("");
        quyenCBB.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tongQuan = new javax.swing.JButton();
        QLCafe = new javax.swing.JButton();
        QLHoaDon = new javax.swing.JButton();
        dangXuat = new javax.swing.JButton();
        hoVaTen = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nhanVienTB = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        luaChonCV = new javax.swing.JComboBox<>();
        xoaBT = new javax.swing.JButton();
        xuatFile = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        maNVTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        hvtTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        gioTinhCCB = new javax.swing.JComboBox<>();
        ngaySinhTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        diaChiTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        themBT = new javax.swing.JButton();
        suaBT = new javax.swing.JButton();
        luongTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        sdtTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        chucVuTF = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cmndTF = new javax.swing.JTextField();
        taiKhoanTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        matKhauTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        quyenCBB = new javax.swing.JComboBox<>();
        nghiViecBT = new javax.swing.JButton();
        huyBT = new javax.swing.JButton();
        inputTK = new javax.swing.JTextField();
        timKiem = new javax.swing.JButton();

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

        jButton11.setBackground(new java.awt.Color(0, 102, 102));
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tongQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QLCafe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QLHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dangXuat))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tongQuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(QLCafe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(QLHoaDon)
                .addComponent(jButton11))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(dangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hoVaTen))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Nhân viên");

        nhanVienTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhân viên", "Họ và tên", "Số điện thoại", "Chức vụ", "Lương"
            }
        ));
        jScrollPane1.setViewportView(nhanVienTB);

        jLabel2.setText("Chức vụ:");

        luaChonCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Quản lý quán cafe", "Nhân viên order", "Nhân viên phục vụ", "Nhân viên bảo vệ", "Nhân viên pha chế" }));
        luaChonCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luaChonCVActionPerformed(evt);
            }
        });

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

        jLabel4.setText("Mã nhân viên:");

        jLabel5.setText("Họ và tên:");

        jLabel6.setText("Giới tính:");

        jLabel7.setText("Ngày sinh:");

        gioTinhCCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        jLabel8.setText("Địa chỉ:");

        jLabel9.setText("Chức vụ:");

        jLabel10.setText("Lương:");

        themBT.setBackground(new java.awt.Color(0, 102, 102));
        themBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        themBT.setForeground(new java.awt.Color(255, 255, 255));
        themBT.setText("Thêm");
        themBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBTActionPerformed(evt);
            }
        });

        suaBT.setBackground(new java.awt.Color(0, 153, 153));
        suaBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        suaBT.setForeground(new java.awt.Color(255, 255, 255));
        suaBT.setText("Sửa");
        suaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaBTActionPerformed(evt);
            }
        });

        jLabel11.setText("Số điện thoại:");

        jLabel12.setText("Tài khoản:");

        chucVuTF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý quán cafe", "Nhân viên order", "Nhân viên phục vụ", "Nhân viên bảo vệ", "Nhân viên pha chế" }));

        jLabel13.setText("Email:");

        jLabel14.setText("Cmnd:");

        jLabel15.setText("Mật khẩu:");

        jLabel16.setText("Quyền:");

        quyenCBB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên Order", "Không được đăng nhập" }));

        nghiViecBT.setBackground(new java.awt.Color(255, 102, 102));
        nghiViecBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nghiViecBT.setForeground(new java.awt.Color(255, 255, 255));
        nghiViecBT.setText("Nghỉ việc");
        nghiViecBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nghiViecBTActionPerformed(evt);
            }
        });

        huyBT.setBackground(new java.awt.Color(0, 255, 0));
        huyBT.setForeground(new java.awt.Color(255, 255, 255));
        huyBT.setText("Cancel");
        huyBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huyBTActionPerformed(evt);
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
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hvtTF)
                            .addComponent(gioTinhCCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ngaySinhTF)
                            .addComponent(diaChiTF)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sdtTF)
                            .addComponent(taiKhoanTF)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmndTF)
                            .addComponent(emailTF)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(52, 52, 52)
                        .addComponent(chucVuTF, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matKhauTF)
                            .addComponent(quyenCBB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(maNVTF, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(luongTF))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addGap(0, 313, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(huyBT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(themBT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addComponent(suaBT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nghiViecBT, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(maNVTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hvtTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gioTinhCCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ngaySinhTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(diaChiTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(sdtTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmndTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(chucVuTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(luongTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(taiKhoanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(matKhauTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(quyenCBB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themBT)
                    .addComponent(suaBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nghiViecBT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(huyBT)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        inputTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTKActionPerformed(evt);
            }
        });
        inputTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputTKKeyPressed(evt);
            }
        });

        timKiem.setBackground(new java.awt.Color(255, 255, 255));
        timKiem.setText("Tìm kiếm");
        timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemActionPerformed(evt);
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
                        .addGap(106, 106, 106)
                        .addComponent(inputTK, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timKiem)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(luaChonCV, 0, 133, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xoaBT, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xuatFile)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(luaChonCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(xoaBT)
                    .addComponent(xuatFile)
                    .addComponent(inputTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timKiem))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void luaChonCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luaChonCVActionPerformed
        if(luaChonCV.getSelectedIndex()==-1)
            return;
        else
            if(luaChonCV.getSelectedIndex()==0){
                setDataNVTable(nvdao.getAllNhanVien());
                setReset();
            }
            else
            {
                String cv = (String) luaChonCV.getSelectedItem();
                setDataNVTable(nvdao.getNVChucVu(cv));
                setReset();
            }
    }//GEN-LAST:event_luaChonCVActionPerformed

    private void xuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatFileActionPerformed
        JOptionPane.showMessageDialog(this, "Xuất file thành công!");
    }//GEN-LAST:event_xuatFileActionPerformed

    private void themBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBTActionPerformed
        if("Thêm".equals(themBT.getText())){
            themBT.setText("Lưu");
            huyBT.setEnabled(true);
            suaBT.setEnabled(false);
            nghiViecBT.setEnabled(false);
            setReset();
            maNVTF.setText("Tự động cập nhật!");
        }
        else{
            NhanVien nv = new NhanVien();
            nv = getNhanVien();
            nvdao.themNhanVien(nv);
            if(nvdao.ktThem == 1){
                JOptionPane.showMessageDialog(this, "Thêm một nhân viên thành công!");
                saveCancel();
                setDataNVTable(nvdao.getAllNhanVien());
                setReset();
            }
            else
                JOptionPane.showMessageDialog(this, "Nhập thông tin sai hoặc thiếu, thêm không thành công!");
        }
    }//GEN-LAST:event_themBTActionPerformed

    private void huyBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huyBTActionPerformed
        saveCancel();
    }//GEN-LAST:event_huyBTActionPerformed

    private void suaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaBTActionPerformed
        int row = nhanVienTB.getSelectedRow();
        if(row != -1){
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắc muốn sửa?");
            if(confirm == JOptionPane.YES_OPTION){
                NhanVien nv = new NhanVien();
                nv = getNhanVien();
                nv.setMaNhanVien(maNVTF.getText());
                nvdao.suaNhanVien(nv);
                setDataNVTable(nvdao.getAllNhanVien());
                setReset();
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hàng hóa cần sửa!");
    }//GEN-LAST:event_suaBTActionPerformed

    private void xoaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaBTActionPerformed
        int row = nhanVienTB.getSelectedRow();
        if(row != -1){
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắc muốn xóa nhân viên này?");
            if(confirm == JOptionPane.YES_OPTION){
                nvdao.xoaNhanVien((String) nhanVienTB.getValueAt(row, 1));
                if(nvdao.ktXoa == 1){
                    setDataNVTable(nvdao.getAllNhanVien());
                    setReset();
                }
                else
                    JOptionPane.showMessageDialog(this, "Không thể xóa vì nhân viên này đã thực hiện các giao dịch!");
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên cần xóa!");
    }//GEN-LAST:event_xoaBTActionPerformed

    private void inputTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputTKActionPerformed

    private void tongQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tongQuanActionPerformed
        ThongKeFrame tk = new ThongKeFrame(hvten);
        tk.setLocationRelativeTo(null);
        tk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_tongQuanActionPerformed

    private void QLCafeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLCafeActionPerformed
        QLCafeFrame cf = new QLCafeFrame(hvten);
        cf.setLocationRelativeTo(null);
        cf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_QLCafeActionPerformed

    private void QLHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QLHoaDonActionPerformed
        QLHoaDonFrame hd = new QLHoaDonFrame(hvten);
        hd.setVisible(true);
        hd.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_QLHoaDonActionPerformed

    private void nghiViecBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nghiViecBTActionPerformed
        int row = nhanVienTB.getSelectedRow();
        if(row != -1){
            String mnv = (String) nhanVienTB.getValueAt(row, 1);
            if(nghiViecBT.getText().equals("Nghỉ việc")){
                int confirm = JOptionPane.showConfirmDialog(this, "Nhân viên này chắc chắn nghỉ việc?");
                if(confirm == JOptionPane.YES_OPTION){
                    nvdao.setTrangThai(mnv, "Nghỉ việc");
                    setDataNVTable(nvdao.getAllNhanVien());
                    setReset();
                }
            }
            else
            {
                int confirm = JOptionPane.showConfirmDialog(this, "Nhân viên này làm việc trở lại?");
                if(confirm == JOptionPane.YES_OPTION){
                    nvdao.setTrangThai(mnv, "Đang làm việc");
                    setDataNVTable(nvdao.getAllNhanVien());
                    setReset();
                }
            }                
        }
        else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên!");
    }//GEN-LAST:event_nghiViecBTActionPerformed

    private void dangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangXuatActionPerformed
        DangNhapFrame dn = new DangNhapFrame();
        dn.setVisible(true);
        dn.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_dangXuatActionPerformed

    private void timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemActionPerformed
        List<NhanVien> list = new ArrayList<>();
        list = nvdao.timKiemNV(inputTK.getText());
        if(list.isEmpty()){
            JOptionPane.showMessageDialog(this, "Không có kết quả nào!");
            setDataNVTable(nvdao.getAllNhanVien());
        }
        else
            setDataNVTable(list);
    }//GEN-LAST:event_timKiemActionPerformed

    private void inputTKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputTKKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            List<NhanVien> list = new ArrayList<>();
            list = nvdao.timKiemNV(inputTK.getText());
            if(list.isEmpty()){
                JOptionPane.showMessageDialog(this, "Không có kết quả nào!");
                setDataNVTable(nvdao.getAllNhanVien());
            }
            else
                setDataNVTable(list);
        }
    }//GEN-LAST:event_inputTKKeyPressed

//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                QLNhanVien nv = new QLNhanVien();
//                nv.setVisible(true);
//                nv.setLocationRelativeTo(null);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton QLCafe;
    private javax.swing.JButton QLHoaDon;
    private javax.swing.JComboBox<String> chucVuTF;
    private javax.swing.JTextField cmndTF;
    private javax.swing.JButton dangXuat;
    private javax.swing.JTextField diaChiTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JComboBox<String> gioTinhCCB;
    private javax.swing.JButton hoVaTen;
    private javax.swing.JButton huyBT;
    private javax.swing.JTextField hvtTF;
    private javax.swing.JTextField inputTK;
    private javax.swing.JButton jButton11;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JComboBox<String> luaChonCV;
    private javax.swing.JTextField luongTF;
    private javax.swing.JTextField maNVTF;
    private javax.swing.JTextField matKhauTF;
    private javax.swing.JTextField ngaySinhTF;
    private javax.swing.JButton nghiViecBT;
    private javax.swing.JTable nhanVienTB;
    private javax.swing.JComboBox<String> quyenCBB;
    private javax.swing.JTextField sdtTF;
    private javax.swing.JButton suaBT;
    private javax.swing.JTextField taiKhoanTF;
    private javax.swing.JButton themBT;
    private javax.swing.JButton timKiem;
    private javax.swing.JButton tongQuan;
    private javax.swing.JButton xoaBT;
    private javax.swing.JButton xuatFile;
    // End of variables declaration//GEN-END:variables
}
