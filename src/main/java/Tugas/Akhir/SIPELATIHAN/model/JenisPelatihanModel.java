package Tugas.Akhir.SIPELATIHAN.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jenis_pelatihan")
@JsonIgnoreProperties(value = {"list_pelatihan"}, allowSetters = true)
public class JenisPelatihanModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @NotNull
    private int id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false, unique = true)
    private String nama;

    @OneToMany(mappedBy = "id_jenis_pelatihan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PelatihanModel> list_pelatihan;

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

    public List<PelatihanModel> getList_pelatihan() {
        return list_pelatihan;
    }

    public void setList_pelatihan(List<PelatihanModel> list_pelatihan) {
        this.list_pelatihan = list_pelatihan;
    }
}
