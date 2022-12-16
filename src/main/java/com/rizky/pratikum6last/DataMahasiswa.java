package com.rizky.pratikum6last;

import javafx.beans.property.SimpleStringProperty;

/**
 * modeling data mahasiswa
 */
public class DataMahasiswa {

    private final SimpleStringProperty namaDosen;
    private final SimpleStringProperty mapel;
    private final SimpleStringProperty GKB;
    private final SimpleStringProperty waktu;
    private final SimpleStringProperty ruangan;

    public DataMahasiswa(String nama_dosen, String matkul, String gkb_aja, String waktu_aja, String ruangan_aja) {
        this.namaDosen = new SimpleStringProperty(nama_dosen);
        this.mapel = new SimpleStringProperty(matkul);
        this.GKB = new SimpleStringProperty(gkb_aja);
        this.waktu = new SimpleStringProperty(waktu_aja);
        this.ruangan = new SimpleStringProperty(ruangan_aja);
    }

    // getter
    public String getNamaDosen() {
        return namaDosen.get();
    }

    public String getMapel() {
        return mapel.get();
    }

    public String getGKB() {
        return GKB.get();
    }

    public String getWaktu() {
        return waktu.get();
    }

    public String getRuangan() {
        return ruangan.get();
    }


    // setter
    public void setNamaDosen(String namaDosen) {
        this.namaDosen.set(namaDosen);
    }

    public void setMapel(String mapel) {
        this.mapel.set(mapel);
    }

    public void setGKB(String gkbAJa) {
        this.GKB.set(gkbAJa);
    }

    public void setWaktu(String waktuAja) {
        this.waktu.set(waktuAja);
    }

    public void setRuangan(String ruangAja) {
        this.ruangan.set(ruangAja);
    }

}
