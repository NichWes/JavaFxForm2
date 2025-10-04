package com.gudang.model;

import javafx.beans.property.SimpleStringProperty;

public class Supplier {
    private final SimpleStringProperty kode;
    private final SimpleStringProperty nama;
    private final SimpleStringProperty alamat;
    private final SimpleStringProperty telepon;

    public Supplier(String kode, String nama, String alamat, String telepon) {
        this.kode = new SimpleStringProperty(kode);
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.telepon = new SimpleStringProperty(telepon);
    }

    public String getKode() { return kode.get(); }
    public void setKode(String val) { kode.set(val); }
    public SimpleStringProperty kodeProperty() { return kode; }

    public String getNama() { return nama.get(); }
    public void setNama(String val) { nama.set(val); }
    public SimpleStringProperty namaProperty() { return nama; }

    public String getAlamat() { return alamat.get(); }
    public void setAlamat(String val) { alamat.set(val); }
    public SimpleStringProperty alamatProperty() { return alamat; }

    public String getTelepon() { return telepon.get(); }
    public void setTelepon(String val) { telepon.set(val); }
    public SimpleStringProperty teleponProperty() { return telepon; }
}
