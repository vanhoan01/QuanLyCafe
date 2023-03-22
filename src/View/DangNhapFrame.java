
package View;

import DAO.NhanVienDAO;
import Model.NhanVien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DangNhapFrame extends javax.swing.JFrame {
    private NhanVienDAO nvdao;
    private String nhapTK, nhapMK;
    public DangNhapFrame() {
        initComponents();
        nvdao = new NhanVienDAO();
        anHienCB.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(anHienCB.isSelected()){
                matKhauTF.setEchoChar((char)0);
            }else{
                matKhauTF.setEchoChar('*');
            }
        }
       });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        taiKhoanTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        matKhauTF = new javax.swing.JPasswordField();
        QuanLy = new javax.swing.JButton();
        BanCafe = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        thoat = new javax.swing.JButton();
        anHienCB = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Đăng nhập");

        jLabel2.setText("Tài khoản:");

        jLabel3.setText("Mật khẩu:");

        matKhauTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                matKhauTFKeyPressed(evt);
            }
        });

        QuanLy.setBackground(new java.awt.Color(0, 102, 102));
        QuanLy.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        QuanLy.setForeground(new java.awt.Color(255, 255, 255));
        QuanLy.setText("Quản lý");
        QuanLy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuanLyActionPerformed(evt);
            }
        });

        BanCafe.setBackground(new java.awt.Color(0, 102, 0));
        BanCafe.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BanCafe.setForeground(new java.awt.Color(255, 255, 255));
        BanCafe.setText("Bán cafe");
        BanCafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BanCafeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Phần mềm quản lý quán Cafe");

        thoat.setBackground(new java.awt.Color(204, 0, 51));
        thoat.setForeground(new java.awt.Color(255, 255, 255));
        thoat.setText("Thoát");
        thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoatActionPerformed(evt);
            }
        });

        anHienCB.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(thoat)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(QuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BanCafe, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(taiKhoanTF)
                                            .addComponent(matKhauTF, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anHienCB))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addGap(163, 163, 163)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(taiKhoanTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(matKhauTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(anHienCB))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BanCafe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(QuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(thoat)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("dangNhap");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BanCafeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BanCafeActionPerformed
        List<NhanVien> list = new ArrayList<>();
        list = nvdao.getAllTaiKhoan();
        nhapTK = taiKhoanTF.getText();
        nhapMK = matKhauTF.getText();
        for(NhanVien nv : list){
            if(nhapTK.equals(nv.getTaiKhoan()) && nhapMK.equals(nv.getMatKhau()) && "PQ02".equals(nv.getMaQuyen()) && "Đang làm việc".equals(nv.getTrangThai()))
            {
                BanFrame b = new BanFrame(nv);
                b.setVisible(true);
                b.setLocationRelativeTo(null);
                this.dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu sai!");
    }//GEN-LAST:event_BanCafeActionPerformed

    private void QuanLyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuanLyActionPerformed
        List<NhanVien> list = new ArrayList<>();
        list = nvdao.getAllTaiKhoan();
        nhapTK = taiKhoanTF.getText();
        nhapMK = matKhauTF.getText();
        for(NhanVien nv : list){
            if(nhapTK.equals(nv.getTaiKhoan()) && nhapMK.equals(nv.getMatKhau()) && "PQ01".equals(nv.getMaQuyen()) && "Đang làm việc".equals(nv.getTrangThai()))
            {
                ThongKeFrame tk = new ThongKeFrame(nv.getHoVaTen());
                tk.setVisible(true);
                tk.setLocationRelativeTo(null);
                this.dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu sai!");
    }//GEN-LAST:event_QuanLyActionPerformed

    private void thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_thoatActionPerformed

    private void matKhauTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_matKhauTFKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            List<NhanVien> list = new ArrayList<>();
            list = nvdao.getAllTaiKhoan();
            nhapTK = taiKhoanTF.getText();
            nhapMK = matKhauTF.getText();
            for(NhanVien nv : list){
                if(nhapTK.equals(nv.getTaiKhoan()) && nhapMK.equals(nv.getMatKhau()) && "PQ02".equals(nv.getMaQuyen()) && "Đang làm việc".equals(nv.getTrangThai()))
                {
                    BanFrame b = new BanFrame(nv);
                    b.setVisible(true);
                    b.setLocationRelativeTo(null);
                    this.dispose();
                    return;
                }
                if(nhapTK.equals(nv.getTaiKhoan()) && nhapMK.equals(nv.getMatKhau()) && "PQ01".equals(nv.getMaQuyen()) && "Đang làm việc".equals(nv.getTrangThai()))
                {
                    ThongKeFrame tk = new ThongKeFrame(nv.getHoVaTen());
                    tk.setVisible(true);
                    tk.setLocationRelativeTo(null);
                    this.dispose();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu sai!");
        }
    }//GEN-LAST:event_matKhauTFKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangNhapFrame dn = new DangNhapFrame();
                dn.setVisible(true);
                dn.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BanCafe;
    private javax.swing.JButton QuanLy;
    private javax.swing.JCheckBox anHienCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField matKhauTF;
    private javax.swing.JTextField taiKhoanTF;
    private javax.swing.JButton thoat;
    // End of variables declaration//GEN-END:variables
}
