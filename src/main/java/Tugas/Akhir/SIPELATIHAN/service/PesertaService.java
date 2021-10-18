package Tugas.Akhir.SIPELATIHAN.service;

import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;

import java.util.List;

public interface PesertaService {
    List<PesertaModel> getAllPeserta();

    void simpan(PesertaModel peserta);

    PesertaModel getPesertaById(int id);

    List<PesertaModel> getPesertaByIdPelamar(int id);

    PesertaModel getPesertaByNama(String namaPeserta);

    PesertaModel addPeserta(PesertaModel peserta);

    PesertaModel updatePeserta(PesertaModel peserta);

    void deletePeserta(PesertaModel peserta);

    int getJumlahPelatihanYangDiikutiByNamaPeserta(String nama_peserta);
}
