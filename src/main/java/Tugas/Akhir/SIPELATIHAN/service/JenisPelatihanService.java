package Tugas.Akhir.SIPELATIHAN.service;

import java.util.List;

import Tugas.Akhir.SIPELATIHAN.model.JenisPelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;

public interface JenisPelatihanService {
    List<JenisPelatihanModel> getAllJenisPelatihan();
    JenisPelatihanModel getJenisPelatihanById(int id_pelatihan);
}
