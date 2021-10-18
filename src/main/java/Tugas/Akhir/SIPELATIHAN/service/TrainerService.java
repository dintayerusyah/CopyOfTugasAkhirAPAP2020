package Tugas.Akhir.SIPELATIHAN.service;

import java.util.List;

import Tugas.Akhir.SIPELATIHAN.model.TrainerModel;


public interface TrainerService {
    void tambahTrainer(TrainerModel trainer);
    void hapusTrainer(TrainerModel trainer);
    TrainerModel getTrainerById(int IdTrainer);
    List<TrainerModel> getAllTrainer();
    boolean cekNoKtp(String no_ktp);
}
