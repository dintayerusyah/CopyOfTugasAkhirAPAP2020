package Tugas.Akhir.SIPELATIHAN.repository;

import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PesertaDB extends JpaRepository<PesertaModel, Integer> {
    PesertaModel findById(int id);

    List<PesertaModel> findAllByIdPelamar (int idPelamar);

    PesertaModel findByNamapeserta(String nama);
}
