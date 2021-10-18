package Tugas.Akhir.SIPELATIHAN.projection;

import java.time.LocalDate;

import org.springframework.data.rest.core.config.Projection;

import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;

@Projection(name = "pelatihanProjection", types = {PelatihanModel.class})
public interface PelatihanProjection {
    Integer getIdPelatihan();
    String getNamaPelatihan();
    LocalDate getTanggalMulai();
    LocalDate getTanggalSelesai();
}
