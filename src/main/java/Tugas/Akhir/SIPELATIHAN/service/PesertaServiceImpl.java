package Tugas.Akhir.SIPELATIHAN.service;

import Tugas.Akhir.SIPELATIHAN.repository.PesertaPelatihanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Tugas.Akhir.SIPELATIHAN.repository.PesertaDB;
import Tugas.Akhir.SIPELATIHAN.model.PesertaModel;

import java.util.List;

@Service
@Transactional
public class PesertaServiceImpl implements PesertaService {
    @Autowired
    PesertaDB pesertaDB;

    @Autowired
    PesertaPelatihanDB pesertaPelatihanDB;

    @Override
    public PesertaModel addPeserta(PesertaModel peserta) {
        pesertaDB.save(peserta);
        return peserta;
    }

    @Override
    public void simpan(PesertaModel peserta) {
        pesertaDB.save(peserta);
    }

    @Override
    public List<PesertaModel> getAllPeserta() {
        return pesertaDB.findAll();
    }

    @Override
    public PesertaModel getPesertaById(int id) {
        return pesertaDB.findById(id);
    }

    @Override
    public List<PesertaModel> getPesertaByIdPelamar(int id) {
        return pesertaDB.findAllByIdPelamar(id);
    }

    @Override
    public PesertaModel updatePeserta(PesertaModel peserta) {
        PesertaModel target = pesertaDB.findById(peserta.getIdpeserta());
        target.setIdpeserta(peserta.getIdpeserta());

        target.setNamapeserta(peserta.getNamapeserta());
        target.setNo_telepon(peserta.getNo_telepon());
        target.setAlamat(peserta.getAlamat());
        target.setDepartemen(peserta.getDepartemen());
        pesertaDB.save(target);
        return target;
    }

    @Override
    public void deletePeserta(PesertaModel peserta) {
        pesertaDB.delete(peserta);
    }

    @Override
    public int getJumlahPelatihanYangDiikutiByNamaPeserta(String nama_peserta) {
        PesertaModel peserta = pesertaDB.findByNamapeserta(nama_peserta);
        // int id_peserta = peserta.getId_peserta();
        // eh ini size dikurang 1 atau enggak?
        // terus perlu try catch gak?
        // int jumlahPelatihan = pesertaPelatihanDB.findByIdpeserta(id_peserta).size();
        int jumlahPelatihan = pesertaPelatihanDB.findByPesertaModel(peserta).size();
        return jumlahPelatihan;
        // return pelatihanDB.findByNamaPelatihan(namaPelatihan);
    }

    @Override
    public PesertaModel getPesertaByNama(String namaPeserta){
        return pesertaDB.findByNamapeserta(namaPeserta);
    }
}
