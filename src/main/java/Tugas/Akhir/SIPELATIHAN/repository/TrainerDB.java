package Tugas.Akhir.SIPELATIHAN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Tugas.Akhir.SIPELATIHAN.model.TrainerModel;

@Repository
public interface TrainerDB extends JpaRepository<TrainerModel, Integer>{
    TrainerModel findById(int id);
    TrainerModel findByNoKtp(String no_ktp);
}
