package Tugas.Akhir.SIPELATIHAN.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostPenggunaDTO {
    private String username;

    private String nama;

    private String noTelepon;

    private String tempatLahir;

    private String tanggalLahir;

    private String alamat;

    private Long roleId;

    public PostPenggunaDTO(String username, String nama, String noTelepon, String tempatLahir, String tanggalLahir, String alamat, Long roleId) {
        this.username = username;
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String toString(){
        String hasil = "usename : " + username + "\n";
        hasil += "nama : " + nama + "\n";
        hasil += "noTelepon : " + noTelepon + "\n";
        hasil += "tempatLahir : " + tempatLahir + "\n";
        hasil += "tanggalLahir : " + tanggalLahir + "\n";
        hasil += "alamat : " + alamat + "\n";
        hasil += "roleId : " + roleId + "\n";
        return hasil;
    }
}
