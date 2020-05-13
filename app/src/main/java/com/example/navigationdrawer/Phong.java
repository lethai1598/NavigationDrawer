package com.example.navigationdrawer;

public class Phong {
    private int id;
    private String tenPhong;
    private String moTa;
    private int giaPhong;

    public Phong(int id, String tenPhong, String moTa, int giaPhong) {
        this.id = id;
        this.tenPhong = tenPhong;
        this.moTa = moTa;
        this.giaPhong = giaPhong;
    }

    public Phong() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(int giaPhong) {
        this.giaPhong = giaPhong;
    }
}
