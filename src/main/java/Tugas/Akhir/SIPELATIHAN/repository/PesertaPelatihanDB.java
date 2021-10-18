package Tugas.Akhir.SIPELATIHAN.repository;

import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;
import Tugas.Akhir.SIPELATIHAN.model.PesertaPelatihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesertaPelatihanDB extends JpaRepository<PesertaPelatihanModel, String> {
    /*List<PesertaPelatihanModel> findById_pelatihan(int id);*/
    List<PesertaPelatihanModel> findByPesertaModel(PesertaModel peserta);
}
