package Tugas.Akhir.SIPELATIHAN.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;
import Tugas.Akhir.SIPELATIHAN.projection.PelatihanProjection;

@Repository
public interface PelatihanDB extends JpaRepository<PelatihanModel, Integer>{
    PelatihanModel findById(int id);
    PelatihanModel findByNamaPelatihan(String namaPelatihan);

    @Query(value = "SELECT a FROM PelatihanModel a JOIN a.listPeserta b WHERE b.pesertaModel=:peserta")
    List<PelatihanProjection> retrieveDataPelatihanByPeserta(@Param("peserta") PesertaModel peserta);
}
