package Tugas.Akhir.SIPELATIHAN.restcontroller;

import Tugas.Akhir.SIPELATIHAN.rest.BaseResponsePengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Tugas.Akhir.SIPELATIHAN.rest.PenggunaDTO;
import Tugas.Akhir.SIPELATIHAN.restservice.PenggunaRestService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class PenggunaRestController {
    @Autowired
    private PenggunaRestService penggunaRestService;

    @GetMapping(value = "/pengguna")
    private Mono<String> getAllPengguna(){
        return penggunaRestService.getAllPengguna();
    }

    @GetMapping(value = "/pengguna/{username}")
    private Mono<BaseResponsePengguna> getAllPengguna(@PathVariable("username") String username){
        return penggunaRestService.getPengguna(username);
    }
}
