 package Tugas.Akhir.SIPELATIHAN.service;

 import Tugas.Akhir.SIPELATIHAN.model.TrainerModel;
 import Tugas.Akhir.SIPELATIHAN.repository.TrainerDB;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.List;

 import org.springframework.transaction.annotation.Transactional;


 @Service
 @Transactional
 public class TrainerServiceImpl implements TrainerService{
     @Autowired
     private TrainerDB trainerDB;

     @Override
     public void tambahTrainer(TrainerModel trainer) {
        trainerDB.save(trainer);
     }

     @Override
     public void hapusTrainer(TrainerModel trainer) {
         trainerDB.delete(trainer);
     }

     @Override
     public TrainerModel getTrainerById(int IdTrainer) {
         return trainerDB.findById(IdTrainer);
     }

     @Override
     public boolean cekNoKtp(String no_ktp) {
         TrainerModel trainer = trainerDB.findByNoKtp(no_ktp);
         if(trainer != null){
             return false;
         }
         if(no_ktp.length() !=16){
             return false;
         }
         return true;
     }

     @Override
     public List<TrainerModel> getAllTrainer() {
         return trainerDB.findAll();
     }
 }
