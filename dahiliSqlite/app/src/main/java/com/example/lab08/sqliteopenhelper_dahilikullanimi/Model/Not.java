package com.example.lab08.sqliteopenhelper_dahilikullanimi.Model;

public class Not {
    private int id;
    private String notBasligi;
    private String notTarihi;
    private String notIcerigi;

    public Not() {
    }

    public Not(int id, String notBasligi, String notTarihi, String notIcerigi) {
        this.id = id;
        this.notBasligi = notBasligi;
        this.notTarihi = notTarihi;
        this.notIcerigi = notIcerigi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotBasligi() {
        return notBasligi;
    }

    public void setNotBasligi(String notBasligi) {
        this.notBasligi = notBasligi;
    }

    public String getNotTarihi() {
        return notTarihi;
    }

    public void setNotTarihi(String notTarihi) {
        this.notTarihi = notTarihi;
    }

    public String getNotIcerigi() {
        return notIcerigi;
    }

    public void setNotIcerigi(String notIcerigi) {
        this.notIcerigi = notIcerigi;
    }
}
