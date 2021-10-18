package Tugas.Akhir.SIPELATIHAN.restservice;
import Tugas.Akhir.SIPELATIHAN.rest.BaseResponsePengguna;
import Tugas.Akhir.SIPELATIHAN.rest.PostPenggunaDTO;
import reactor.core.publisher.Mono;

public interface PenggunaRestService {
    Mono<String> getAllPengguna();
    Mono<BaseResponsePengguna> getPengguna(String username);
    Mono<BaseResponsePengguna> postPengguna(PostPenggunaDTO postPenggunaDTO);
}
