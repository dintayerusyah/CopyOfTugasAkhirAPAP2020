package Tugas.Akhir.SIPELATIHAN.restservice;


import javax.transaction.Transactional;

import Tugas.Akhir.SIPELATIHAN.model.JenisPelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.TrainerModel;
import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;
import Tugas.Akhir.SIPELATIHAN.repository.PelatihanDB;
import Tugas.Akhir.SIPELATIHAN.rest.BaseResponseLaporanPelatihan;
import Tugas.Akhir.SIPELATIHAN.rest.BaseResponseTambahPelatihan;
import Tugas.Akhir.SIPELATIHAN.rest.PostPenggunaDTO;
import Tugas.Akhir.SIPELATIHAN.service.JenisPelatihanService;
import Tugas.Akhir.SIPELATIHAN.service.PesertaService;
import Tugas.Akhir.SIPELATIHAN.service.TrainerService;
import Tugas.Akhir.SIPELATIHAN.projection.PelatihanProjection;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//import Tugas.Akhir.SIPELATIHAN.rest.PostPenggunaDTO;
import Tugas.Akhir.SIPELATIHAN.rest.LaporanPesertaPelatihanDTO;
import Tugas.Akhir.SIPELATIHAN.rest.PelatihanDTO;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PelatihanRestServiceImpl implements PelatihanRestService {

    @Autowired
    PesertaService pesertaService;

    @Autowired
    private PelatihanDB pelatihanDb;

    private final WebClient webClient;

    public PelatihanRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://sipayroll.herokuapp.com/").build();
    }


    @Override
    public Mono<BaseResponseLaporanPelatihan> postLaporan(String nama_peserta) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String tanggal = dtf.format(now).toString();
        JSONObject data = new JSONObject();
        data.put("username", nama_peserta);
        data.put("jumlahTraining", pesertaService.getJumlahPelatihanYangDiikutiByNamaPeserta(nama_peserta));
        data.put("tanggalDiberikan", tanggal);
//        return this.webClient.post().uri("/api/v1/bonus").contentType(MediaType.APPLICATION_JSON).bodyValue(data.toString()).retrieve().bodyToMono(LaporanPesertaPelatihanDTO.class);
        return this.webClient.post().uri("/api/v1/bonus").contentType(MediaType.APPLICATION_JSON).bodyValue(data.toString()).retrieve().bodyToMono(BaseResponseLaporanPelatihan.class);
    }

    @Override
    public PelatihanModel addPelatihan(PelatihanModel pelatihan){            
        return pelatihanDb.save(pelatihan);
    }

    @Override
    public PelatihanModel getPelatihanByNama(String namaPelatihan) {
        return pelatihanDb.findByNamaPelatihan(namaPelatihan);
    }

    @Override
    public List<PelatihanProjection> retrievePelatihanByPeserta(PesertaModel peserta){
        return pelatihanDb.retrieveDataPelatihanByPeserta(peserta);
    }

    @Override
    public List<PelatihanModel> getAllPelatihan() {
        return pelatihanDb.findAll();
    }
}
