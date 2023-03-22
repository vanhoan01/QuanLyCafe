
package Model;

import java.sql.Timestamp;

public class HoaDon {
    private String maHoaDon;
    private Timestamp thoiGian;
    private long tongCong;
    private int chietKhau;
    private int vat;
    private long thanhTien;
    private long tienKhachTra;
    private int phieuKhach;
    private String maBan;
    private String tinhTrangDon;
    private String maNhanVien;
    private String tenNhanVien;

    public HoaDon() {
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public long getTongCong() {
        return tongCong;
    }

    public void setTongCong(long tongCong) {
        this.tongCong = tongCong;
    }

    public int getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(int chietKhau) {
        this.chietKhau = chietKhau;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public long getTienKhachTra() {
        return tienKhachTra;
    }

    public void setTienKhachTra(long tienKhachTra) {
        this.tienKhachTra = tienKhachTra;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public int getPhieuKhach() {
        return phieuKhach;
    }

    public void setPhieuKhach(int maKhach) {
        this.phieuKhach = maKhach;
    }

    public String getTinhTrangDon() {
        return tinhTrangDon;
    }

    public void setTinhTrangDon(String tinhTrangDon) {
        this.tinhTrangDon = tinhTrangDon;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }
    
}
