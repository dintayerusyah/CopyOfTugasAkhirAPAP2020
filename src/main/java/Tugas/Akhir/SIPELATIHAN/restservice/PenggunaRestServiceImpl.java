package Tugas.Akhir.SIPELATIHAN.restservice;

import Tugas.Akhir.SIPELATIHAN.rest.BaseResponsePengguna;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import Tugas.Akhir.SIPELATIHAN.rest.PostPenggunaDTO;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class PenggunaRestServiceImpl implements PenggunaRestService {
    private final WebClient webClient;

    public PenggunaRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://si-pegawai.herokuapp.com/").build();
    }

    @Override
    public Mono<String> getAllPengguna() {
        return this.webClient.get().uri("/api/v1/pegawai").retrieve().bodyToMono(String.class);
    }

    @Override
    public Mono<BaseResponsePengguna> getPengguna(String username) {
        return this.webClient.get().uri("/api/v1/pegawai/"+username).retrieve().bodyToMono(BaseResponsePengguna.class);
    }

    @Override
    public Mono<BaseResponsePengguna> postPengguna(PostPenggunaDTO postPenggunaDTO) {
        JSONObject data = new JSONObject();
        data.put("username", postPenggunaDTO.getUsername());
        data.put("nama", postPenggunaDTO.getNama());
        data.put("noTelepon", postPenggunaDTO.getNoTelepon());
        data.put("tempatLahir", postPenggunaDTO.getTempatLahir());
        data.put("tanggalLahir", postPenggunaDTO.getTanggalLahir());
        data.put("alamat", postPenggunaDTO.getAlamat());
        data.put("roleId", postPenggunaDTO.getRoleId());
        return this.webClient.post().uri("/api/v1/pegawai").contentType(MediaType.APPLICATION_JSON).bodyValue(data.toString()).retrieve().bodyToMono(BaseResponsePengguna.class);
    }
}
