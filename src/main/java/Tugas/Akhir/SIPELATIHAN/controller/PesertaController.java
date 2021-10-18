package Tugas.Akhir.SIPELATIHAN.controller;

import Tugas.Akhir.SIPELATIHAN.service.PesertaService;
import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PesertaController {
    @Autowired
    PesertaService pesertaService;

    @GetMapping(value="/peserta/tambah")
    public String formTambahPeserta(Model model) {
        model.addAttribute("peserta", new PesertaModel());
        return "form-tambah-peserta-ver2";
    }
    
    @PostMapping(value="/peserta/tambah")
    public String submitTambahPeserta(@ModelAttribute PesertaModel peserta, Model model){
        pesertaService.addPeserta(peserta);
        model.addAttribute("message", "Peserta dengan nama " + peserta.getNamapeserta() + " berhasil ditambahkan");
        return "peserta-message";
    }
}
