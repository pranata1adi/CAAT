package com.example.aseps.caat.Temp.home;

public class ItemPB {
    private int id;
    private String nama;
    private int statusAll;
    private int nilaiPb;

    public ItemPB(String nama, int statusAll, int nilaiPb) {
        this.nama = nama;
        this.statusAll = statusAll;
        this.nilaiPb = nilaiPb;
    }

    public ItemPB(int id, String nama, int statusAll, int nilaiPb) {
        this.id = id;
        this.nama = nama;
        this.statusAll = statusAll;
        this.nilaiPb = nilaiPb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getStatusAll() {
        return statusAll;
    }

    public void setStatusAll(int statusAll) {
        this.statusAll = statusAll;
    }

    public int getNilaiPb() {
        return nilaiPb;
    }

    public void setNilaiPb(int nilaiPb) {
        this.nilaiPb = nilaiPb;
    }
}