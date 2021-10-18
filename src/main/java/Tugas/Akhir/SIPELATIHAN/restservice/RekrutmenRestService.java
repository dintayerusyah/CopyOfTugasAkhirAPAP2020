package Tugas.Akhir.SIPELATIHAN.restservice;

import Tugas.Akhir.SIPELATIHAN.rest.BaseResponseRekrutmen;
import Tugas.Akhir.SIPELATIHAN.rest.RekrutmenDTO;
import reactor.core.publisher.Mono;

public interface RekrutmenRestService {
    Mono<BaseResponseRekrutmen> getAllRekrutmen();
}
