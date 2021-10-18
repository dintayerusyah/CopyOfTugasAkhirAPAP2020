package Tugas.Akhir.SIPELATIHAN.controller;

import Tugas.Akhir.SIPELATIHAN.model.TrainerModel;
import Tugas.Akhir.SIPELATIHAN.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    //get daftar trainer
    @GetMapping("/view")
    public String daftarTrainer(Model model){
        List<TrainerModel> listTrainer = trainerService.getAllTrainer();
        model.addAttribute("listTrainer", listTrainer);
        return "daftar-trainer-ver2";
    }

    //getMapping form tambah trainer
    @GetMapping("/tambah")
    public String formTambahTrainer(Model model){
        TrainerModel trainer = new TrainerModel();
        model.addAttribute("trainer", trainer);
        return "form-tambah-trainer-ver2";
    }

    @PostMapping("/tambah")
    public  String tambahTrainer(@ModelAttribute TrainerModel trainer, Model model){
        boolean cekKtp = trainerService.cekNoKtp(trainer.getNoKtp());
        model.addAttribute("trainer",trainer);
        if(cekKtp == false){
            model.addAttribute("pesan","Gagal disimpan (Nomor KTP sudah tersedia)");
            if(trainer.getNoKtp().length() != 16){
                model.addAttribute("pesan","Gagal disimpan (Pastikan nomor KTP terdiri dari 16 digit)");
            }
            return "notif-trainer-v2";
        }
        trainerService.tambahTrainer(trainer);
        model.addAttribute("pesan","Berhasil disimpan");
        return "notif-trainer-v2";
    }

    //get ke halaman form-ubah-trainer
    @GetMapping(value = "/ubah/{id}")
    public String formUbahTrainer(@PathVariable int id, Model model){
        TrainerModel trainer = trainerService.getTrainerById(id);
        model.addAttribute("trainer", trainer);
        return "form-ubah-trainer-ver2";
    }

    //post ke halaman ubah trainer
    @PostMapping(value= "/ubah/{id}")
    public String postUpdateTrainer(@ModelAttribute TrainerModel trainer, Model model){
        boolean cekKtp = trainerService.cekNoKtp(trainer.getNoKtp());
        model.addAttribute("trainer",trainer);
        TrainerModel trainerBefore = trainerService.getTrainerById(trainer.getId());
        if(trainerBefore.getId() != trainer.getId()){
            if(cekKtp == false){
                model.addAttribute("pesan","Gagal diperbaharui (Nomor KTP sudah tersedia)");
                return "notif-trainer-v2";
            }
        }
        if(cekKtp == false){
            model.addAttribute("pesan","Gagal disimpan (Nomor KTP sudah tersedia)");
            if(trainer.getNoKtp().length() != 16){
                model.addAttribute("pesan","Gagal disimpan ((Pastikan nomor KTP terdiri dari 16 digit))");
            }
            return "notif-trainer-v2";
        }
        trainerService.tambahTrainer(trainer);
        model.addAttribute("pesan","Berhasil diperbaharui");
        return "notif-trainer-v2";
    }

    //hapus TrainerModel
    @GetMapping(value="/hapus/{id}")
    public String hapusTrainer(@PathVariable int id, Model model){
        TrainerModel trainer = trainerService.getTrainerById(id);
        trainerService.hapusTrainer(trainer);
        List<TrainerModel> listTrainer = trainerService.getAllTrainer();
        model.addAttribute("listTrainer", listTrainer);
        return "daftar-trainer-ver2";
    }
}
