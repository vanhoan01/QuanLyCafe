
package DAO;

import Model.ChiTietDonHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChiTietDonHangDAO {
    public int dem = 0;
    public List<ChiTietDonHang> getChiTietDonHang(String mhd){
        List<ChiTietDonHang> list = new ArrayList<>();
        ChiTietDonHang ctdh;
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT tenCafe, soLuong, donGia, (soLuong*donGia) AS 'thanhTien'"+
                " FROM tblChiTietDonHang, tblCafe"+
                " WHERE tblChiTietDonHang.maCafe = tblCafe.maCafe AND maHoaDon = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mhd);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                dem++;
                ctdh = new ChiTietDonHang();
                ctdh.setTenCafe(rs.getString("tenCafe"));
                ctdh.setSoLuong(rs.getInt("soLuong"));
                ctdh.setDonGia(rs.getLong("donGia"));
                ctdh.setThanhTien(rs.getLong("thanhTien"));
                list.add(ctdh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int kt = 0;
    public void addCTDH(ChiTietDonHang ctdh){
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO tblChiTietDonHang(maHoaDon, maCafe, soLuong, donGia)" +
                     " VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ctdh.getMaHoaDon());
            ps.setString(2, ctdh.getMaCafe());
            ps.setInt(3, ctdh.getSoLuong());
            ps.setLong(4, ctdh.getDonGia());            
            kt = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(kt);
    }
}
