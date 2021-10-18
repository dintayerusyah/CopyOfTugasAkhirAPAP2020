package Tugas.Akhir.SIPELATIHAN.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LaporanPesertaPelatihanDTO {

    private String username;

    private int jumlahTraining;

    private String tanggalDiberikan;

    public LaporanPesertaPelatihanDTO(String username, int jumlahTraining, String tanggalDiberikan) {
        this.username = username;
        this.jumlahTraining = jumlahTraining;
        this.tanggalDiberikan = tanggalDiberikan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getJumlahTraining() {
        return jumlahTraining;
    }

    public void setJumlahTraining(int jumlahTraining) {
        this.jumlahTraining = jumlahTraining;
    }

    public String getTanggalDiberikan() {
        return tanggalDiberikan;
    }

    public void setTanggalDiberikan(String tanggalDiberikan) {
        this.tanggalDiberikan = tanggalDiberikan;
    }

    public String toString(){
        String hasil = "usename : " + username + "\n";
        hasil += "jumlahTraining : " + jumlahTraining + "\n";
        hasil += "tanggalDiberikan : " + tanggalDiberikan + "\n";
        return hasil;
    }
}