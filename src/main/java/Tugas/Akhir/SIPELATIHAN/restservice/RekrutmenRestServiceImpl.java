package Tugas.Akhir.SIPELATIHAN.restservice;

import Tugas.Akhir.SIPELATIHAN.rest.BaseResponseRekrutmen;
import Tugas.Akhir.SIPELATIHAN.rest.RekrutmenDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class RekrutmenRestServiceImpl  implements RekrutmenRestService {
    private final WebClient webClient;

    public RekrutmenRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://sirekrutmen-f-3.herokuapp.com/api/v1").build();;
    }

    @Override
    public Mono<BaseResponseRekrutmen> getAllRekrutmen() {
        return this.webClient.get().uri("/karyawan-baru").retrieve().bodyToMono(BaseResponseRekrutmen.class);
    }
}
