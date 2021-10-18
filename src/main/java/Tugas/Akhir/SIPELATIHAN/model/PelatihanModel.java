package Tugas.Akhir.SIPELATIHAN.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

  
@Entity  
@Table(name="pelatihan")
@JsonIgnoreProperties(value = {"list_peserta"}, allowSetters = true)
public class PelatihanModel implements Serializable{

    @Id
    @NotNull
    @Column(name = "idPelatihan", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPelatihan;

    @NotNull
    @Size(max = 50)
    @Column(name = "namaPelatihan", nullable = false, unique = true)
    private String namaPelatihan;

    @Size(max = 200)
    @Column(name = "deskripsi")
    private String deskripsi;

    @NotNull
    @Column(name = "kapasitas", nullable = false)
    private int kapasitas;

    @NotNull
    @Column(name = "tanggalMulai", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalMulai;

    @NotNull
    @Column(name = "tanggalSelesai", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalSelesai;

    @NotNull
    @Column(name = "waktu_mulai", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktu_mulai;

    @NotNull
    @Column(name = "waktu_selesai", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime waktu_selesai;

    @NotNull
    @Column(name = "status_persetujuan", columnDefinition = "integer default 0")
    private int status_persetujuan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_trainer", referencedColumnName = "id", nullable = false)
    private TrainerModel id_trainer;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_jenis_pelatihan", referencedColumnName = "id", nullable = false)
    private JenisPelatihanModel id_jenis_pelatihan;

    @Column(name = "uuid_penyetuju", nullable = true)
    private String uuid_penyetuju;

    @NotNull
    @Column(name = "uuid_pengaju", nullable = false)
    private String uuid_pengaju;

    @OneToMany(mappedBy = "pelatihanModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PesertaPelatihanModel> listPeserta;

    public int getIdPelatihan() {
        return idPelatihan;
    }

    public void setIdPelatihan(int id_pelatihan) {
        this.idPelatihan = id_pelatihan;
    }

    public String getNamaPelatihan() {
        return namaPelatihan;
    }

    public void setNamaPelatihan(String nama_pelatihan) {
        this.namaPelatihan = nama_pelatihan;
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

    public LocalDate getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(LocalDate tanggal_mulai) {
        this.tanggalMulai = tanggal_mulai;
    }

    public LocalDate getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(LocalDate tanggal_selesai) {
        this.tanggalSelesai = tanggal_selesai;
    }

    public LocalTime getWaktu_mulai() {
        return waktu_mulai;
    }

    public void setWaktu_mulai(LocalTime waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public LocalTime getWaktu_selesai() {
        return waktu_selesai;
    }

    public void setWaktu_selesai(LocalTime waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public int getStatus_persetujuan() {
        return status_persetujuan;
    }

    public void setStatus_persetujuan(int status_persetujuan) {
        this.status_persetujuan = status_persetujuan;
    }

    public TrainerModel getId_trainer() {
        return id_trainer;
    }

    public void setId_trainer(TrainerModel id_trainer) {
        this.id_trainer = id_trainer;
    }

    public JenisPelatihanModel getId_jenis_pelatihan() {
        return id_jenis_pelatihan;
    }

    public void setId_jenis_pelatihan(JenisPelatihanModel id_jenis_pelatihan) {
        this.id_jenis_pelatihan = id_jenis_pelatihan;
    }

    public String getUuid_penyetuju() {
        return uuid_penyetuju;
    }

    public void setUuid_penyetuju(String uuid_penyetuju) {
        this.uuid_penyetuju = uuid_penyetuju;
    }

    public String getUuid_pengaju() {
        return uuid_pengaju;
    }

    public void setUuid_pengaju(String uuid_pengaju) {
        this.uuid_pengaju = uuid_pengaju;
    }

    public List<PesertaPelatihanModel> getList_peserta() {
        return listPeserta;
    }

    public void setList_peserta(List<PesertaPelatihanModel> list_peserta) {
        this.listPeserta = list_peserta;
    }
}
