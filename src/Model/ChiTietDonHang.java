
package Model;

public class ChiTietDonHang {
    private String maHoaDon;
    private String maCafe;
    private String tenCafe;
    private int soLuong;
    private long donGia;
    private long thanhTien;

    public ChiTietDonHang() {
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaCafe() {
        return maCafe;
    }

    public void setMaCafe(String maCafe) {
        this.maCafe = maCafe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public String getTenCafe() {
        return tenCafe;
    }

    public void setTenCafe(String tenCafe) {
        this.tenCafe = tenCafe;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }
    
}
