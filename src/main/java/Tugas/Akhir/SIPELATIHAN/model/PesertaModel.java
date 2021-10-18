package Tugas.Akhir.SIPELATIHAN.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import java.util.List;

@Entity
@Table(name = "peserta")
@JsonIgnoreProperties(value = {"list_pelatihan"}, allowSetters = true)
public class PesertaModel implements Serializable {
    @Id
    @Column(name = "idpeserta", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int idpeserta;

    @Column(name = "idPelamar", nullable = true)
    private int idPelamar;

    @NotNull
    @Column(name = "namapeserta", nullable = false)
    @Size(max = 100)
    private String namapeserta;

    @NotNull
    @Column(name = "no_telepon", nullable = false)
    @Size(max = 20)
    private String no_telepon;

    @NotNull
    @Column(name = "alamat", nullable = false)
    @Size(max = 100)
    private String alamat;

    @NotNull
    @Column(name = "departemen", nullable = false)
    @Size(max = 100)
    private String departemen;

    @OneToMany(mappedBy = "pesertaModel", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PesertaPelatihanModel> list_pelatihan;

    public int getIdpeserta() {
        return this.idpeserta;
    }

    public void setIdpeserta(int idpeserta) {
        this.idpeserta = idpeserta;
    }

    public String getNamapeserta() {
        return this.namapeserta;
    }

    public void setNamapeserta(String namapeserta) {
        this.namapeserta = namapeserta;
    }

    public String getNo_telepon() {
        return this.no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDepartemen() {
        return this.departemen;
    }

    public String getDuaHurufPertamaKapitalDepartemen(){
        String capsDepartemen = this.departemen.toUpperCase();
        return capsDepartemen.substring(0, 2);
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public List<PesertaPelatihanModel> getList_pelatihan() {
        return this.list_pelatihan;
    }

    public void setList_pelatihan(List<PesertaPelatihanModel> list_pelatihan) {
        this.list_pelatihan = list_pelatihan;
    }

   public int getIdPelamar() {
       return idPelamar;
   }

   public void setIdPelamar(int idPelamar) {
       this.idPelamar = idPelamar;
   }
}
