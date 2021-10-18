package Tugas.Akhir.SIPELATIHAN.service;

import java.util.List;

import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;

public interface PelatihanService {
    List<PelatihanModel> getAllPelatihan();
    PelatihanModel getPelatihanById(int idPelatihan);
    PelatihanModel getPelatihanByNamaPelatihan(String namaPelatihan);
    boolean deletePelatihan(PelatihanModel pelatihan);
    void savePelatihan(PelatihanModel pelatihan);
    void editStatusPersetujuan(PelatihanModel pelatihan);
}
