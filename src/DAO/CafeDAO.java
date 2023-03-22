
package DAO;

import Model.Cafe;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CafeDAO {
    public int ktThem = 0;
    public int ktXoa = 0;
    
    public List<Cafe> getAllCafe(){
        List<Cafe> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM tblCafe";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cafe cf = new Cafe();
                cf.setMaCafe(rs.getString("maCafe"));
                cf.setTenCafe(rs.getString("tenCafe"));
                cf.setMaDM(rs.getString("maDM"));
                cf.setGiaCheBien(rs.getLong("giaCheBien"));
                cf.setGiaBan(rs.getLong("giaBan"));
                cf.setMoTa(rs.getString("moTa"));
                cf.setThanhPhan(rs.getString("thanhPhan"));
                cf.setTrangThai(rs.getString("trangThai"));
                list.add(cf);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Cafe> getTimKiemTen(String tcf){
        List<Cafe> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM tblCafe WHERE tenCafe LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%"+tcf+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cafe cf = new Cafe();
                cf.setMaCafe(rs.getString("maCafe"));
                cf.setTenCafe(rs.getString("tenCafe"));
                cf.setMaDM(rs.getString("maDM"));
                cf.setGiaCheBien(rs.getLong("giaCheBien"));
                cf.setGiaBan(rs.getLong("giaBan"));
                cf.setMoTa(rs.getString("moTa"));
                cf.setThanhPhan(rs.getString("thanhPhan"));
                cf.setTrangThai(rs.getString("trangThai"));
                list.add(cf);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Cafe> getAllCafeKD(){
        List<Cafe> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM tblCafe WHERE trangThai = N'Đang kinh doanh'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cafe cf = new Cafe();
                cf.setMaCafe(rs.getString("maCafe"));
                cf.setTenCafe(rs.getString("tenCafe"));
                cf.setMaDM(rs.getString("maDM"));
                cf.setGiaCheBien(rs.getLong("giaCheBien"));
                cf.setGiaBan(rs.getLong("giaBan"));
                cf.setMoTa(rs.getString("moTa"));
                cf.setThanhPhan(rs.getString("thanhPhan"));
                cf.setTrangThai(rs.getString("trangThai"));
                list.add(cf);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Cafe getCafe(String mcf){
        Cafe cf = new Cafe();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM tblCafe WHERE maCafe = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mcf);
            ResultSet rs = ps.executeQuery();
            rs.next();
            cf.setMaCafe(rs.getString("maCafe"));
            cf.setTenCafe(rs.getString("tenCafe"));
            cf.setMaDM(rs.getString("maDM"));
            cf.setGiaCheBien(rs.getLong("giaCheBien"));
            cf.setGiaBan(rs.getLong("giaBan"));
            cf.setMoTa(rs.getString("moTa"));
            cf.setThanhPhan(rs.getString("thanhPhan"));
            cf.setTrangThai(rs.getString("trangThai"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return cf;
    }
    
    public Cafe getCafeTen(String tcf){
        Cafe cf = new Cafe();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM tblCafe WHERE tenCafe = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tcf);
            ResultSet rs = ps.executeQuery();
            rs.next();
            cf.setMaCafe(rs.getString("maCafe"));
            cf.setTenCafe(rs.getString("tenCafe"));
            cf.setMaDM(rs.getString("maDM"));
            cf.setGiaCheBien(rs.getLong("giaCheBien"));
            cf.setGiaBan(rs.getLong("giaBan"));
            cf.setMoTa(rs.getString("moTa"));
            cf.setThanhPhan(rs.getString("thanhPhan"));
            cf.setTrangThai(rs.getString("trangThai"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return cf;
    }
    
    //Top 10 cafe theo doanh thu
    public List<Cafe> getTopCafeDT(){
        List<Cafe> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT TOP 10 CF.maCafe, tenCafe, SUM(soLuong*donGia) AS 'doanhThu', SUM(soLuong) AS 'doanhSo'\n" +
                    "FROM tblChiTietDonHang AS CT, tblCafe AS CF\n" +
                    "WHERE CT.maCafe = CF.maCafe\n" +
                    "GROUP BY CF.maCafe, tenCafe\n" +
                    "ORDER BY SUM(soLuong*donGia) DESC;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cafe cf = new Cafe();
                cf.setMaCafe(rs.getString("maCafe"));
                cf.setTenCafe(rs.getString("tenCafe"));
                cf.setDoanhThu(rs.getLong("doanhThu"));
                cf.setDoanhSo(rs.getLong("doanhSo"));
                list.add(cf);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    //Top 10 cafe theo doanh so
    public List<Cafe> getTopCafeDS(){
        List<Cafe> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT TOP 10 CF.maCafe, tenCafe, SUM(soLuong*donGia) AS 'doanhThu', SUM(soLuong) AS 'doanhSo'\n" +
                    "FROM tblChiTietDonHang AS CT, tblCafe AS CF\n" +
                    "WHERE CT.maCafe = CF.maCafe\n" +
                    "GROUP BY CF.maCafe, tenCafe\n" +
                    "ORDER BY SUM(soLuong) DESC;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cafe cf = new Cafe();
                cf.setMaCafe(rs.getString("maCafe"));
                cf.setTenCafe(rs.getString("tenCafe"));
                cf.setDoanhThu(rs.getLong("doanhThu"));
                cf.setDoanhSo(rs.getLong("doanhSo"));
                list.add(cf);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void xoaCafe(String maCF){
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "DELETE FROM tblCafe WHERE maCafe = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maCF);
            ktXoa = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Cafe> getNhomCafe(String nhomCF){
        List<Cafe> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT C.*\n" +
                    "FROM tblCafe AS C, tblDanhMucCafe AS D\n" +
                    "WHERE C.maDM = D.maDM AND tenDM = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nhomCF);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cafe cf = new Cafe();
                cf.setMaCafe(rs.getString("maCafe"));
                cf.setTenCafe(rs.getString("tenCafe"));
                cf.setMaDM(rs.getString("maDM"));
                cf.setGiaCheBien(rs.getLong("giaCheBien"));
                cf.setGiaBan(rs.getLong("giaBan"));
                cf.setMoTa(rs.getString("moTa"));
                cf.setThanhPhan(rs.getString("thanhPhan"));
                cf.setTrangThai(rs.getString("trangThai"));
                list.add(cf);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Cafe> getNhomCafeKD(String nhomCF){
        List<Cafe> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT C.*\n" +
                    "FROM tblCafe AS C, tblDanhMucCafe AS D\n" +
                    "WHERE C.maDM = D.maDM AND tenDM = ? AND trangThai = N'Đang kinh doanh'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nhomCF);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Cafe cf = new Cafe();
                cf.setMaCafe(rs.getString("maCafe"));
                cf.setTenCafe(rs.getString("tenCafe"));
                cf.setMaDM(rs.getString("maDM"));
                cf.setGiaCheBien(rs.getLong("giaCheBien"));
                cf.setGiaBan(rs.getLong("giaBan"));
                cf.setMoTa(rs.getString("moTa"));
                cf.setThanhPhan(rs.getString("thanhPhan"));
                list.add(cf);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void themCafe(Cafe cf){
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "EXEC prIDCafe ?, ?, ?, ?, ?, ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cf.getTenCafe());
            ps.setString(2, cf.getMaDM());
            ps.setLong(3, cf.getGiaCheBien());
            ps.setLong(4, cf.getGiaBan());
            ps.setString(5, cf.getMoTa());
            ps.setString(6, cf.getThanhPhan());
            ktThem = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void suaCafe(Cafe cf){
        int kt = 0;
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE tblCafe" +
                     " SET tenCafe = ?, maDM = ?, giaCheBien = ?, giaBan = ?, moTa = ?, thanhPhan = ?" +
                     " WHERE maCafe = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cf.getTenCafe());
            ps.setString(2, cf.getMaDM());
            ps.setLong(3, cf.getGiaCheBien());
            ps.setLong(4, cf.getGiaBan());
            ps.setString(5, cf.getMoTa());
            ps.setString(6, cf.getThanhPhan());
            ps.setString(7, cf.getMaCafe());
            kt = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(kt);
    }
    
    public void ngungKinhDoanh(String mcf){
        int kt = 0;
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE tblCafe" +
                     " SET trangThai = ?" +
                     " WHERE maCafe = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "Ngừng kinh doanh");
            ps.setString(2, mcf);
            kt = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(kt);
    }
    
    public void kinhDoanh(String mcf){
        int kt = 0;
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE tblCafe" +
                     " SET trangThai = ?" +
                     " WHERE maCafe = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "Đang kinh doanh");
            ps.setString(2, mcf);
            kt = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(kt);
    }
}
