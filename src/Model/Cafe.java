
package Model;

public class Cafe {
    private String maCafe;
    private String tenCafe;
    private String maDM;
    private long giaCheBien;
    private long giaBan;
    private String moTa;
    private String thanhPhan;
    private long doanhThu;
    private long doanhSo;
    private String trangThai;

    public Cafe() {
    }
    
    public Cafe(String maCafe, String tenCafe, String maDM, long giaCheBien, long giaBan, String moTa, String thanhPhan) {
        this.maCafe = maCafe;
        this.tenCafe = tenCafe;
        this.maDM = maDM;
        this.giaCheBien = giaCheBien;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.thanhPhan = thanhPhan;
    }

    public String getMaCafe() {
        return maCafe;
    }

    public void setMaCafe(String maCafe) {
        this.maCafe = maCafe;
    }

    public String getTenCafe() {
        return tenCafe;
    }

    public void setTenCafe(String tenCafe) {
        this.tenCafe = tenCafe;
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public long getGiaCheBien() {
        return giaCheBien;
    }

    public void setGiaCheBien(long giaCheBien) {
        this.giaCheBien = giaCheBien;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getThanhPhan() {
        return thanhPhan;
    }

    public void setThanhPhan(String thanhPhan) {
        this.thanhPhan = thanhPhan;
    }

    public long getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(long doanhThu) {
        this.doanhThu = doanhThu;
    }

    public long getDoanhSo() {
        return doanhSo;
    }

    public void setDoanhSo(long doanhSo) {
        this.doanhSo = doanhSo;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    } 
}
