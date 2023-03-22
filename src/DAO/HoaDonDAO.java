
package DAO;

import Model.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class HoaDonDAO {
    public int ktTT = 0;
    public List<HoaDon> getAllHoaDon(){
        List<HoaDon> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT HD.maHoaDon, thoiGian, phieuKhach, tinhTrangDon, hoVaTen, SUM(soLuong*donGia*(100-chietkhau+vat)/100) As N'thanhTien'\n" +
                    "FROM tblHoaDon AS HD, tblChiTietDonHang AS CTDH, tblNhanVien AS NV\n" +
                    "WHERE HD.maHoaDon = CTDH.maHoaDon AND HD.maNhanVien = NV.maNhanVien\n" +
                    "GROUP BY HD.maHoaDon, thoiGian, phieuKhach, tinhTrangDon, hoVaTen\n" +
                    "ORDER BY HD.maHoaDon DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setThoiGian(rs.getTimestamp("thoiGian"));
                hd.setPhieuKhach(rs.getInt("phieuKhach"));
                hd.setTinhTrangDon(rs.getString("tinhTrangDon"));
                hd.setTenNhanVien(rs.getString("hoVaTen"));
                hd.setThanhTien(rs.getLong("thanhTien"));
                list.add(hd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public List<HoaDon> getDSHDTheoBan(String maBan){
        List<HoaDon> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT HD.maHoaDon, thoiGian, phieuKhach, tinhTrangDon, hoVaTen, maBan, SUM(soLuong*donGia*(100-chietkhau+vat)/100) As N'thanhTien'\n" +
                    "FROM tblHoaDon AS HD, tblChiTietDonHang AS CTDH, tblNhanVien AS NV\n" +
                    "WHERE HD.maHoaDon = CTDH.maHoaDon AND HD.maNhanVien = NV.maNhanVien AND tinhTrangDon <> N'Đã về' AND maBan = ?\n" +
                    "GROUP BY HD.maHoaDon, thoiGian, phieuKhach, tinhTrangDon, hoVaTen, maBan";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maBan);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setThoiGian(rs.getTimestamp("thoiGian"));
                hd.setPhieuKhach(rs.getInt("phieuKhach"));
                hd.setTinhTrangDon(rs.getString("tinhTrangDon"));
                hd.setTenNhanVien(rs.getString("hoVaTen"));
                hd.setThanhTien(rs.getLong("thanhTien"));
                hd.setMaBan(rs.getString("maBan"));
                list.add(hd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public List<HoaDon> getDSHDTrongNgay(){
        List<HoaDon> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql =  "SELECT tblHoaDon.maHoaDon, thoiGian, SUM(soLuong*donGia*(100-chietkhau+vat)/100) As 'thanhTien', tinhTrangDon, phieuKhach, hoVaTen\n" +
                    "FROM tblChiTietDonHang, tblHoaDon, tblNhanVien\n" +
                    "WHERE tblChiTietDonHang.maHoaDon = tblHoaDon.maHoaDon AND tblHoaDon.maNhanVien = tblNhanVien.maNhanVien AND CONVERT(VARCHAR, thoiGian, 103) = CONVERT(VARCHAR, getdate(), 103)\n" +
                    "GROUP BY tblHoaDon.maHoaDon, thoiGian, tinhTrangDon, phieuKhach, hoVaTen\n" +
                    "ORDER BY tblHoaDon.maHoaDon DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setThoiGian(rs.getTimestamp("thoiGian"));
                hd.setThanhTien(rs.getLong("thanhTien"));
                hd.setPhieuKhach(rs.getInt("phieuKhach"));
                hd.setTinhTrangDon(rs.getString("tinhTrangDon"));
                hd.setTenNhanVien(rs.getString("hoVaTen"));
                list.add(hd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<HoaDon> getDSHDTrongNgayTT(String tt){
        List<HoaDon> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT tblHoaDon.maHoaDon, thoiGian, SUM(soLuong*donGia*(100-chietkhau+vat)/100) As 'thanhTien', tinhTrangDon, phieuKhach, hoVaTen\n" +
                    "FROM tblChiTietDonHang, tblHoaDon, tblNhanVien\n" +
                    "WHERE tblChiTietDonHang.maHoaDon = tblHoaDon.maHoaDon AND tblHoaDon.maNhanVien = tblNhanVien.maNhanVien AND CONVERT(VARCHAR, thoiGian, 103) = CONVERT(VARCHAR, getdate(), 103) AND tinhTrangDon = ?\n" +
                    "GROUP BY tblHoaDon.maHoaDon, thoiGian, tinhTrangDon, phieuKhach, hoVaTen\n" +
                    "ORDER BY tblHoaDon.maHoaDon DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tt);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setThoiGian(rs.getTimestamp("thoiGian"));
                hd.setThanhTien(rs.getLong("thanhTien"));
                hd.setPhieuKhach(rs.getInt("phieuKhach"));
                hd.setTinhTrangDon(rs.getString("tinhTrangDon"));
                hd.setTenNhanVien(rs.getString("hoVaTen"));
                list.add(hd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public HoaDon getChiTiet1HD(String mahd){
        HoaDon hd = new HoaDon();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT phieuKhach, SUM(soLuong*donGia) As 'tongCong', chietKhau, vat, tienKhachTra, tinhTrangDon, maBan"+
                    " FROM tblChiTietDonHang, tblHoaDon"+
                    " WHERE tblChiTietDonHang.maHoaDon = tblHoaDon.maHoaDon AND tblHoaDon.maHoaDon = ?"+
                    " GROUP BY phieuKhach, chietKhau, vat, tienKhachTra, tinhTrangDon, maBan";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mahd);
            ResultSet rs = ps.executeQuery();
            rs.next();
            hd.setPhieuKhach(rs.getInt("phieuKhach"));
            hd.setTongCong(rs.getLong("tongCong"));
            hd.setChietKhau(rs.getInt("chietKhau"));
            hd.setVat(rs.getInt("vat"));
            hd.setTienKhachTra(rs.getLong("tienKhachTra"));
            hd.setTinhTrangDon(rs.getString("tinhTrangDon"));
            hd.setMaBan(rs.getString("maBan"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }
    public int kt = 0;
    public void addHoaDon(HoaDon hd){
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "EXEC prIDHoaDon ?, ?, ?, ?, ?, ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hd.getChietKhau());
            ps.setInt(2, hd.getVat());
            ps.setLong(3, hd.getTienKhachTra());
            ps.setInt(4, hd.getPhieuKhach());
            ps.setString(5, hd.getMaBan());
            ps.setString(6, hd.getMaNhanVien());
            kt = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(kt);
    }
    
   public String getMaHDMoiThem(){
        String mhd = "";
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT TOP 1 RIGHT(maHoaDon, 4), maHoaDon\n" +
                    "FROM tblHoaDon\n" +
                    "ORDER BY RIGHT(maHoaDon, 4) DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            mhd = rs.getString("maHoaDon");
            return mhd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mhd;
    }
   
   //Lấy doanh thu theo ngày
   public long getDTTheoNgay(Date d){
        long dt = 0;
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT SUM(soLuong*donGia*(100-chietkhau+vat)/100) As 'doanhThu'"+
                    " FROM tblChiTietDonHang, tblHoaDon"+
                    " WHERE tblChiTietDonHang.maHoaDon = tblHoaDon.maHoaDon AND CONVERT(VARCHAR, thoiGian, 103) = CONVERT(VARCHAR, ?, 103)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, d);
            ResultSet rs = ps.executeQuery();
            rs.next();
            dt = rs.getLong("doanhThu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }
   
   public List<HoaDon> getDSHDKhoangNgay(Date bd, Date kt){
        List<HoaDon> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT H.maHoaDon, thoiGian, SUM(soLuong*donGia*(100-chietkhau+vat)/100) As N'thanhTien', tinhTrangDon, phieuKhach, hoVaTen\n" +
                    "FROM tblChiTietDonHang AS C, tblHoaDon AS H, tblNhanVien AS NV\n" +
                    "WHERE C.maHoaDon = H.maHoaDon AND H.maNhanVien = NV.maNhanVien AND CONVERT(VARCHAR, thoiGian, 103) BETWEEN CONVERT(VARCHAR, ?, 103) AND CONVERT(VARCHAR, ?, 103)\n" +
                    "GROUP BY H.maHoaDon, thoiGian, tinhTrangDon, phieuKhach, hoVaTen\n" +
                    "ORDER BY H.maHoaDon DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, bd);
            ps.setDate(2, kt);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setThoiGian(rs.getTimestamp("thoiGian"));
                hd.setThanhTien(rs.getLong("thanhTien"));
                hd.setPhieuKhach(rs.getInt("phieuKhach"));
                hd.setTinhTrangDon(rs.getString("tinhTrangDon"));
                hd.setTenNhanVien(rs.getString("hoVaTen"));
                list.add(hd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<HoaDon> getHDTrangThai(String trangThai){
        List<HoaDon> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT HD.maHoaDon, thoiGian, phieuKhach, tinhTrangDon, hoVaTen, SUM(soLuong*donGia*(100-chietkhau+vat)/100) As N'thanhTien'\n" +
                    "FROM tblHoaDon AS HD, tblChiTietDonHang AS CTDH, tblNhanVien AS NV\n" +
                    "WHERE HD.maHoaDon = CTDH.maHoaDon AND HD.maNhanVien = NV.maNhanVien AND tinhTrangDon = ?\n" +
                    "GROUP BY HD.maHoaDon, thoiGian, phieuKhach, tinhTrangDon, hoVaTen\n" +
                    "ORDER BY HD.maHoaDon DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString("maHoaDon"));
                hd.setThoiGian(rs.getTimestamp("thoiGian"));
                hd.setPhieuKhach(rs.getInt("phieuKhach"));
                hd.setTinhTrangDon(rs.getString("tinhTrangDon"));
                hd.setTenNhanVien(rs.getString("hoVaTen"));
                hd.setThanhTien(rs.getLong("thanhTien"));
                list.add(hd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void setTrangThai(String mhd, String trangThai){
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "UPDATE tblHoaDon" +
                     " SET tinhTrangDon = ?" +
                     " WHERE maHoaDon = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, trangThai);
            ps.setString(2, mhd);
            ktTT = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(kt);
    }
}
