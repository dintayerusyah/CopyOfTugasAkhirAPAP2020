package Tugas.Akhir.SIPELATIHAN.restcontroller;

import Tugas.Akhir.SIPELATIHAN.rest.BaseResponsePengguna;
import Tugas.Akhir.SIPELATIHAN.rest.LaporanPesertaPelatihanDTO;
import Tugas.Akhir.SIPELATIHAN.service.PesertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.time.LocalTime;

import Tugas.Akhir.SIPELATIHAN.model.JenisPelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;
import Tugas.Akhir.SIPELATIHAN.model.TrainerModel;
import Tugas.Akhir.SIPELATIHAN.service.JenisPelatihanService;
import Tugas.Akhir.SIPELATIHAN.service.TrainerService;
import Tugas.Akhir.SIPELATIHAN.rest.BaseResponseLaporanPelatihan;
import Tugas.Akhir.SIPELATIHAN.rest.BaseResponseTambahPelatihan;
import Tugas.Akhir.SIPELATIHAN.rest.PelatihanDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import Tugas.Akhir.SIPELATIHAN.restservice.PelatihanRestService;
import reactor.core.publisher.Mono;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
public class PelatihanRestController {
    @Autowired
    private PelatihanRestService pelatihanRestService;

    @Autowired
    private JenisPelatihanService jenisPelatihanService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private PesertaService pesertaService;

    // Untuk sementara ini kayak gini dulu, tapi kayaknya link nama_peserta harus
    // diganti jadi id karena
    // nama_peserta mungkin akan ada spasi
    // Udah ku ganti jadi id bukan nama, tapi ada kemungkinan error masukin id yg
    // gak terdaftar di database si pelatihan
    // private Mono<LaporanPesertaPelatihanDTO> sendLaporan (@PathVariable(value =
    // "nama_peserta") String nama_peserta) {
    @PostMapping(value = "/pelatihan/laporanPeserta/{id_peserta}")
    private Mono<BaseResponseLaporanPelatihan> sendLaporan(@PathVariable(value = "id_peserta") int id_peserta) {
        String nama_peserta = pesertaService.getPesertaById(id_peserta).getNamapeserta();
        try {
            return pelatihanRestService.postLaporan(nama_peserta);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    // HttpStatus.NOT_FOUND, "Resep with Number " + String.valueOf(noResep) + " not
                    // found!"
                    HttpStatus.NOT_FOUND, "User dengan username " + nama_peserta
                            + " tidak ada di SIPAYROLL. // java.lang.NullPointerException");
        }
    }

    @PostMapping(value = "/pelatihan")
    private BaseResponseTambahPelatihan tambah_pelatihan(@Valid @RequestBody PelatihanDTO pelatihan,
            BindingResult bindingResult) {
        PelatihanModel pelatihanModel = new PelatihanModel();

        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            String tgl_mulai = pelatihan.getTanggal_mulai();
            String tgl_selesai = pelatihan.getTanggal_selesai();
            String w_mulai = pelatihan.getWaktu_mulai();
            String w_selesai = pelatihan.getWaktu_selesai();

            LocalDate tanggal_mulai = LocalDate.parse(tgl_mulai);
            LocalDate tanggal_selesai = LocalDate.parse(tgl_selesai);
            LocalTime waktu_mulai = LocalTime.parse(w_mulai);
            LocalTime waktu_selesai = LocalTime.parse(w_selesai);

            if (tanggal_mulai.compareTo(tanggal_selesai) > 0) {
                BaseResponseTambahPelatihan failedResponses = new BaseResponseTambahPelatihan();
                failedResponses.setupBaseResponse(400, "Waktu mulai pelatihan harus lebih dahulu dibandingkan waktu selesai pelatihan", null);
                
                return failedResponses;
            } else if (tanggal_mulai.compareTo(tanggal_mulai) == 0) {
                if (waktu_mulai.isAfter(waktu_selesai)) {
                    BaseResponseTambahPelatihan failedResponses = new BaseResponseTambahPelatihan();
                    failedResponses.setupBaseResponse(400, "Waktu mulai pelatihan harus lebih dahulu dibandingkan waktu selesai pelatihan", null);
                
                    return failedResponses;
                }
            }

            String nama_pelatihan = pelatihan.getNama_pelatihan();

            // check nama pelatihan whether nama pelatihan is exist in DB or not
            if(pelatihanRestService.getPelatihanByNama(nama_pelatihan) != null){
                BaseResponseTambahPelatihan failedResponses = new BaseResponseTambahPelatihan();
                failedResponses.setupBaseResponse(400, "Nama pelatihan sudah terdapat dalam database sipelatihan", null);
                
                return failedResponses;
            }

            String deskripsi = pelatihan.getDeskripsi();
            int kapasitas = pelatihan.getKapasitas();
            JenisPelatihanModel id_jenis_pelatihan = jenisPelatihanService
                    .getJenisPelatihanById(pelatihan.getId_jenis_pelatihan());
            TrainerModel id_trainer = trainerService.getTrainerById(pelatihan.getId_trainer());
            int status_persetujuan = pelatihan.getStatus_persetujuan();
            String uuid_pengaju = pelatihan.getUuid_pengaju();

            pelatihanModel.setNamaPelatihan(nama_pelatihan);
            pelatihanModel.setDeskripsi(deskripsi);
            pelatihanModel.setKapasitas(kapasitas);
            pelatihanModel.setTanggalMulai(tanggal_mulai);
            pelatihanModel.setTanggalSelesai(tanggal_selesai);
            pelatihanModel.setWaktu_mulai(waktu_mulai);
            pelatihanModel.setWaktu_selesai(waktu_selesai);
            pelatihanModel.setId_jenis_pelatihan(id_jenis_pelatihan);
            pelatihanModel.setId_trainer(id_trainer);
            pelatihanModel.setStatus_persetujuan(status_persetujuan);
            pelatihanModel.setUuid_pengaju(uuid_pengaju);

            pelatihanRestService.addPelatihan(pelatihanModel);

            // convert to DTO
            BaseResponseTambahPelatihan baseResponse = new BaseResponseTambahPelatihan();
            baseResponse.setupBaseResponse(200, "Add pelatihan success", pelatihanModel);

            return baseResponse;
        }
    }

    // Daftar Peserta Pelatihan
    // Parameter : String namaPeserta
    // Return : daftar pelatihan yang diambil oleh peserta dengan nama namaPeserta
    // (idPelatihan, namaPelatihan, tanggalMulai, tanggalSelesai)
    @GetMapping(value = "/pelatihan/{namaPeserta}")
    private Map<String, Object> daftarPelatihanByPeserta(@PathVariable(value = "namaPeserta") String namaPeserta) {
        PesertaModel peserta = pesertaService.getPesertaByNama(namaPeserta);
        if (peserta == null) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("status", "404");
            attributes.put("error", "Not Found");
            attributes.put("messages", "Peserta dengan nama " + namaPeserta + " tidak ditemukan di SIPELATIHAN!");
            return attributes;
        } else {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("status", "200");
            attributes.put("messages", "success");
            attributes.put("result", pelatihanRestService.retrievePelatihanByPeserta(peserta));
            return attributes;
        }
    }

    @GetMapping(value = "/all-pelatihan")
    private Map<String, Object> getAllDaftarPelatihan() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("status", "200");
        attributes.put("messages", "success");
        attributes.put("result", pelatihanRestService.getAllPelatihan());
        return attributes;
    }
}
