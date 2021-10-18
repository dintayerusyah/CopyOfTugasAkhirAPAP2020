package Tugas.Akhir.SIPELATIHAN.repository;

import Tugas.Akhir.SIPELATIHAN.model.PenggunaModel;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenggunaDB extends JpaRepository<PenggunaModel, Long> {
   PenggunaModel findByUsername(String username);
   PenggunaModel findByUuid(String uuid);
}
