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
@Table(name = "peserta_pelatihan")
// @JsonIgnoreProperties(value = {"pesertaModel"}, allowSetters = true)
public class PesertaPelatihanModel implements Serializable{
    
    @Id
    @NotNull
    @Size(max = 200)
    @Column(name = "no_peserta", nullable = false)
    private String no_peserta;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idpeserta", referencedColumnName = "idpeserta", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PesertaModel pesertaModel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_pelatihan", referencedColumnName = "idPelatihan", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PelatihanModel pelatihanModel;

    public void generateNoPeserta(){
        String noPeserta = pelatihanModel.getIdPelatihan()+"-"+pesertaModel.getDuaHurufPertamaKapitalDepartemen()+"-"+pesertaModel.getIdpeserta();
        setNo_peserta(noPeserta);
    }

    public PelatihanModel getId_pelatihan() {
        return pelatihanModel;
    }

    public void setId_pelatihan(PelatihanModel id_pelatihan) {
        this.pelatihanModel = id_pelatihan;
    }

    public String getNo_peserta() {
        return no_peserta;
    }

    public void setNo_peserta(String no_peserta) {
        this.no_peserta = no_peserta;
    }

    public PesertaModel getPesertaModel() {
        return pesertaModel;
    }
    
    public void setPesertaModel(PesertaModel pesertaModel) {
        this.pesertaModel = pesertaModel;
    }

}
