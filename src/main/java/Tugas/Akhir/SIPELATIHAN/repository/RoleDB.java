package Tugas.Akhir.SIPELATIHAN.repository;

import Tugas.Akhir.SIPELATIHAN.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDB extends JpaRepository<RoleModel, Long> {
}
