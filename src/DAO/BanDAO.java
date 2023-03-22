
package DAO;

import Model.Ban;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BanDAO {
    public List<Ban> getAllBan(){
        List<Ban> list = new ArrayList<>();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM tblBan";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Ban b = new Ban();
                b.setMaBan(rs.getString("maBan"));
                b.setTenBan(rs.getString("tenBan"));
                b.setSoCho(rs.getInt("soCho"));
                b.setDangNgoi(rs.getInt("dangNgoi"));
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Ban getBan(String maBan){
        Ban b = new Ban();
        Connection con = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM tblBan WHERE maBan = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maBan);
            ResultSet rs = ps.executeQuery();
            rs.next();
            b.setMaBan(rs.getString("maBan"));
            b.setTenBan(rs.getString("tenBan"));
            b.setSoCho(rs.getInt("soCho"));
            b.setDangNgoi(rs.getInt("dangNgoi"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return b;
    }
}
