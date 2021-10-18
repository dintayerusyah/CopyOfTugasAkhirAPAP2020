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
@Table(name = "role")
public class RoleModel implements Serializable{
    @Id
    @Column(name = "id_role", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id_role;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama_role", nullable = false, unique = true)
    private String nama_role;

    @OneToMany(mappedBy = "roleModel", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PenggunaModel> listPengguna;

    public Long getId_role() {
        return id_role;
    }

    public void setId_role(Long id_role) {
        this.id_role = id_role;
    }

    public String getNama_role() {
        return nama_role;
    }

    public void setNama_role(String nama_role) {
        this.nama_role = nama_role;
    }

    public List<PenggunaModel> getListPengguna() {
        return listPengguna;
    }

    public void setListPengguna(List<PenggunaModel> listPengguna) {
        this.listPengguna = listPengguna;
    }
}
