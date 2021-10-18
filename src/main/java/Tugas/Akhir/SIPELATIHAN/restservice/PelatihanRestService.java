package Tugas.Akhir.SIPELATIHAN.restservice;

import Tugas.Akhir.SIPELATIHAN.rest.LaporanPesertaPelatihanDTO;
import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;
import Tugas.Akhir.SIPELATIHAN.rest.BaseResponseLaporanPelatihan;
import Tugas.Akhir.SIPELATIHAN.projection.PelatihanProjection;
import reactor.core.publisher.Mono;
import java.util.List;

public interface PelatihanRestService {
    Mono<BaseResponseLaporanPelatihan> postLaporan(String nama_peserta);
    PelatihanModel addPelatihan(PelatihanModel pelatihan);
    PelatihanModel getPelatihanByNama(String namaPelatihan);
    List<PelatihanModel> getAllPelatihan();
    List<PelatihanProjection> retrievePelatihanByPeserta(PesertaModel peserta);
}