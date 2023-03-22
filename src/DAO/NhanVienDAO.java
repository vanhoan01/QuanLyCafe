
package DAO;

import Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    public int ktThem = 0;
    public int ktXoa = 0;
    public List<NhanVien> getAllTaiKhoan(){
        List<NhanVien> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT maNhanVien, hoVaTen, taiKhoan, matKhau, maQuyen, trangThai FROM tblNhanVien"
                + " WHERE maQuyen = 'PQ01' OR maQuyen = 'PQ02'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("maNhanVien"));
                nv.setHoVaTen(rs.getString("hoVaTen"));
                nv.setTaiKhoan(rs.getString("taiKhoan"));
                nv.setMatKhau(rs.getString("matKhau"));
                nv.setMaQuyen(rs.getString("maQuyen"));
                nv.setTrangThai(rs.getString("trangThai"));
                list.add(nv);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<NhanVien> getAllNhanVien(){
        List<NhanVien> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT maNhanVien, hoVaTen, soDienThoai, tenChucVu, luong, trangThai\n" +
                    "FROM tblNhanVien AS NV, tblChucVu AS CV\n" +
                    "WHERE NV.maChucVu = CV.maChucVu";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("maNhanVien"));
                nv.setHoVaTen(rs.getString("hoVaTen"));
                nv.setSoDienThoai(rs.getString("soDienThoai"));
                nv.setTenChucVu(rs.getString("tenChucVu"));
                nv.setLuong(rs.getLong("luong"));
                nv.setTrangThai(rs.getString("trangThai"));
                list.add(nv);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<NhanVien> getNVChucVu(String cv){
        List<NhanVien> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT maNhanVien, hoVaTen, soDienThoai, tenChucVu, luong, trangThai\n" +
                    "FROM tblNhanVien AS NV, tblChucVu AS CV\n" +
                    "WHERE NV.maChucVu = CV.maChucVu AND tenChucVu = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cv);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("maNhanVien"));
                nv.setHoVaTen(rs.getString("hoVaTen"));
                nv.setSoDienThoai(rs.getString("soDienThoai"));
                nv.setTenChucVu(rs.getString("tenChucVu"));
                nv.setLuong(rs.getLong("luong"));
                nv.setTrangThai(rs.getString("trangThai"));
                list.add(nv);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public NhanVien getNhanVien(String manv){
        NhanVien nv = new NhanVien();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT NV.*, tenChucVu, quyen\n" +
                    "FROM tblNhanVien AS NV, tblChucVu AS CV, tblPhanQuyen AS Q\n" +
                    "WHERE NV.maChucVu = CV.maChucVu AND NV.maQuyen = Q.maQuyen AND maNhanVien = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, manv);
            ResultSet rs = ps.executeQuery();
            rs.next();
            nv.setMaNhanVien(rs.getString("maNhanVien"));
            nv.setHoVaTen(rs.getString("hoVaTen"));
            nv.setGioiTinh(rs.getString("gioiTinh"));
            nv.setNgaySinh(rs.getDate("ngaySinh"));
            nv.setDiaChi(rs.getString("diaChi"));
            nv.setSoDienThoai(rs.getString("soDienThoai"));
            nv.setEmail(rs.getString("email"));
            nv.setCmnd(rs.getString("cmnd"));
            nv.setTenChucVu(rs.getString("tenChucVu"));
            nv.setLuong(rs.getLong("luong"));
            nv.setTaiKhoan(rs.getString("taiKhoan"));
            nv.setMatKhau(rs.getString("matKhau"));
            nv.setQuyen(rs.getString("quyen"));
            nv.setTrangThai(rs.getString("trangThai"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return nv;
    }
    
    public List<NhanVien> timKiemNV(String hvt){
        List<NhanVien> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT NV.*, tenChucVu, quyen\n" +
                    "FROM tblNhanVien AS NV, tblChucVu AS CV, tblPhanQuyen AS Q\n" +
                    "WHERE NV.maChucVu = CV.maChucVu AND NV.maQuyen = Q.maQuyen AND hoVaTen LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%"+hvt+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("maNhanVien"));
                nv.setHoVaTen(rs.getString("hoVaTen"));
                nv.setGioiTinh(rs.getString("gioiTinh"));
                nv.setNgaySinh(rs.getDate("ngaySinh"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setSoDienThoai(rs.getString("soDienThoai"));
                nv.setEmail(rs.getString("email"));
                nv.setCmnd(rs.getString("cmnd"));
                nv.setTenChucVu(rs.getString("tenChucVu"));
                nv.setLuong(rs.getLong("luong"));
                nv.setTaiKhoan(rs.getString("taiKhoan"));
                nv.setMatKhau(rs.getString("matKhau"));
                nv.setQuyen(rs.getString("quyen"));
                nv.setTrangThai(rs.getString("trangThai"));
                list.add(nv);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void themNhanVien(NhanVien nv){
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "EXEC prIDNhanVien ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getHoVaTen());
            ps.setString(2, nv.getGioiTinh());
            ps.setDate(3, nv.getNgaySinh());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getSoDienThoai());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getCmnd());
            ps.setString(8, nv.getMaChucVu());
            ps.setLong(9, nv.getLuong());
            ps.setString(10, nv.getTaiKhoan());
            ps.setString(11, nv.getMatKhau());
            ps.setString(12, nv.getMaQuyen());
            ktThem = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void suaNhanVien(NhanVien nv){
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE tblNhanVien" +
                     " SET hoVaTen = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, soDienThoai = ?, cmnd = ?, email = ?" +
                     ", maChucVu = ?, luong = ?, taiKhoan = ?, matKhau = ?, maQuyen = ?"+
                     " WHERE maNhanVien = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getHoVaTen());
            ps.setString(2, nv.getGioiTinh());
            ps.setDate(3, nv.getNgaySinh());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getSoDienThoai());
            ps.setString(6, nv.getCmnd());
            ps.setString(7, nv.getEmail());
            ps.setString(8, nv.getMaChucVu());
            ps.setLong(9, nv.getLuong());
            ps.setString(10, nv.getTaiKhoan());
            ps.setString(11, nv.getMatKhau());
            ps.setString(12, nv.getMaQuyen());
            ps.setString(13, nv.getMaNhanVien());
            int x = ps.executeUpdate();
            System.out.println(x);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
        
    public void xoaNhanVien(String maNV){
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "DELETE FROM tblNhanVien WHERE maNhanVien = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNV);
            ktXoa = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(ktXoa);
    }
    
    public void setTrangThai(String mnv, String trangThai){
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE tblNhanVien" +
                     " SET trangThai = ?" +
                     " WHERE maNhanVien = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, trangThai);
            ps.setString(2, mnv);
            int x = ps.executeUpdate();
            System.out.println(x);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
