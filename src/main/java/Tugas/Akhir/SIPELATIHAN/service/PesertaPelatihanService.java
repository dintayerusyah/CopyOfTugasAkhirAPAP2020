package Tugas.Akhir.SIPELATIHAN.service;

import Tugas.Akhir.SIPELATIHAN.model.PesertaPelatihanModel;

import java.util.List;

public interface PesertaPelatihanService {
    List<PesertaPelatihanModel> getAllPesertaPelatihan();
    void savePesertaPelatihan(PesertaPelatihanModel pesertaPelatihan);
}
