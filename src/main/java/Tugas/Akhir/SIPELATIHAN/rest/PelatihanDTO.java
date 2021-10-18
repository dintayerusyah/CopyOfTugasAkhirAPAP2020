package Tugas.Akhir.SIPELATIHAN.rest;

import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PelatihanDTO {
    private String nama_pelatihan;
    private String deskripsi;
    private int kapasitas;
    private String tanggal_mulai;
    private String tanggal_selesai;
    private String waktu_mulai;
    private String waktu_selesai;
    private int status_persetujuan;
    private int id_trainer;
    private int id_jenis_pelatihan;
    private String uuid_pengaju;

    public void convertToDTO(PelatihanModel pelatihanModel){
        this.nama_pelatihan = pelatihanModel.getNamaPelatihan();
        this.deskripsi = pelatihanModel.getDeskripsi();
        this.kapasitas = pelatihanModel.getKapasitas();
        this.status_persetujuan = pelatihanModel.getStatus_persetujuan();
        this.id_trainer = pelatihanModel.getId_trainer().getId();
        this.id_jenis_pelatihan = pelatihanModel.getId_jenis_pelatihan().getId();
        this.uuid_pengaju = pelatihanModel.getUuid_pengaju();
        this.waktu_mulai = pelatihanModel.getWaktu_mulai().toString();
        this.waktu_selesai = pelatihanModel.getWaktu_selesai().toString();
        this.tanggal_mulai = pelatihanModel.getTanggalMulai().toString();
        this.tanggal_selesai = pelatihanModel.getTanggalSelesai().toString();
    }

    public String getNama_pelatihan() {
        return nama_pelatihan;
    }

    public void setNama_pelatihan(String nama_pelatihan) {
        this.nama_pelatihan = nama_pelatihan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(String tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public String getTanggal_selesai() {
        return tanggal_selesai;
    }

    public void setTanggal_selesai(String tanggal_selesai) {
        this.tanggal_selesai = tanggal_selesai;
    }
    
    public String getWaktu_mulai() {
        return waktu_mulai;
    }
    
    public void setWaktu_mulai(String waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }
    
    public String getWaktu_selesai() {
        return waktu_selesai;
    }
    
    public void setWaktu_selesai(String waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public int getId_trainer() {
        return id_trainer;
    }

    public void setId_trainer(int id_trainer) {
        this.id_trainer = id_trainer;
    }

    public int getId_jenis_pelatihan() {
        return id_jenis_pelatihan;
    }

    public void setId_jenis_pelatihan(int id_jenis_pelatihan) {
        this.id_jenis_pelatihan = id_jenis_pelatihan;
    }

    public int getStatus_persetujuan() {
        return status_persetujuan;
    }

    public void setStatus_persetujuan(int status_persetujuan) {
        this.status_persetujuan = status_persetujuan;
    }

    public String getUuid_pengaju() {
        return uuid_pengaju;
    }

    public void setUuid_pengaju(String uuid_pengaju) {
        this.uuid_pengaju = uuid_pengaju;
    }
}
