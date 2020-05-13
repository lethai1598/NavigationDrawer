package com.example.navigationdrawer;

public class Khach {
    private int id;
    private String tenKhach;
    private String sdt;
    private String soCmtnd;
    private String diaChi;
    private String ghiChu;
    private int idPhong;

    public Khach(int id, String tenKhach, String sdt, String soCmtnd, String diaChi, String ghiChu, int idPhong) {
        this.id = id;
        this.tenKhach = tenKhach;
        this.sdt = sdt;
        this.soCmtnd = soCmtnd;
        this.diaChi = diaChi;
        this.ghiChu = ghiChu;
        this.idPhong = idPhong;
    }
    public Khach(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getSoCmtnd() {
        return soCmtnd;
    }

    public void setSoCmtnd(String soCmtnd) {
        this.soCmtnd = soCmtnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }
}
