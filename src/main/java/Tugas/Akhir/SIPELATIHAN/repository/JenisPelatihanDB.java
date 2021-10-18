package Tugas.Akhir.SIPELATIHAN.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Tugas.Akhir.SIPELATIHAN.model.JenisPelatihanModel;

@Repository
public interface JenisPelatihanDB extends JpaRepository<JenisPelatihanModel, Integer>{
    JenisPelatihanModel findById(int id);
}
