package Tugas.Akhir.SIPELATIHAN.service;

import Tugas.Akhir.SIPELATIHAN.model.PenggunaModel;

import java.util.List;
import java.util.UUID;


public interface PenggunaService {
    PenggunaModel addAccount(PenggunaModel user);
    PenggunaModel getPenggunaByUsername(String username);
    String encrypt(String password);
    List<PenggunaModel> getAllPengguna();
    PenggunaModel getPenggunaById (String id);
    /*String getStringOfUuid (String id);*/
    /*void savePelatihan(PelatihanModel pelatihan);*/

}
