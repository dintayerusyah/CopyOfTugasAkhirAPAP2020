package Tugas.Akhir.SIPELATIHAN.model;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="trainer")
@JsonIgnoreProperties(value = {"list_pelatihan"}, allowSetters = true)
public class TrainerModel implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    @Size(min=16,max=16)
    @Column(name="no_ktp", nullable = false, unique = true)
    private String noKtp;

    @NotNull
    @Size(max=200)
    @Column(name = "nama_trainer", nullable = false)
    private String nama_trainer;

    @NotNull
    @Size(max=200)
    @Column(name = "no_telepon", nullable = false)
    private String no_telepon;

    @NotNull
    @Size(max=100)
    @Column(name = "email", nullable = false)
    private String email;

   @OneToMany(mappedBy = "id_trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
   private List<PelatihanModel> list_pelatihan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String no_ktp) {
        this.noKtp = no_ktp;
    }

    public String getNama_trainer() {
        return nama_trainer;
    }

    public void setNama_trainer(String nama_trainer) {
        this.nama_trainer = nama_trainer;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PelatihanModel> getList_pelatihan() {
        return list_pelatihan;
    }

    public void setList_pelatihan(List<PelatihanModel> list_pelatihan) {
        this.list_pelatihan = list_pelatihan;
    }
}
