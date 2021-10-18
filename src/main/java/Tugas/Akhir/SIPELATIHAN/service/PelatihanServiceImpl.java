package Tugas.Akhir.SIPELATIHAN.service;

import Tugas.Akhir.SIPELATIHAN.repository.PelatihanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Tugas.Akhir.SIPELATIHAN.model.PelatihanModel;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PelatihanServiceImpl implements PelatihanService {

    @Autowired
    PelatihanDB pelatihanDB;

    @Override
    public List<PelatihanModel> getAllPelatihan() {
        return pelatihanDB.findAll();
    }

    @Override
    public PelatihanModel getPelatihanById(int idPelatihan) {
        return pelatihanDB.findById(idPelatihan);
    }

    @Override
    public boolean deletePelatihan(PelatihanModel pelatihan) {
        int status = pelatihan.getStatus_persetujuan();
        if(status == 0 || status == 1){
            pelatihanDB.delete(pelatihan);
            return true;
        }
        else {
            return false;
        }
        /*return status;*/
    }

    @Override
    public void savePelatihan(PelatihanModel pelatihan) {
        pelatihanDB.save(pelatihan);
    }

    @Override
    public PelatihanModel getPelatihanByNamaPelatihan(String namaPelatihan) {
        return pelatihanDB.findByNamaPelatihan(namaPelatihan);
    }

    @Override
    public void editStatusPersetujuan(PelatihanModel pelatihan){
        int idPelatihan = pelatihan.getIdPelatihan();
        int statusPersetujuan = pelatihan.getStatus_persetujuan();
        PelatihanModel target = pelatihanDB.findById(idPelatihan);
        target.setStatus_persetujuan(statusPersetujuan);
        pelatihanDB.save(target);
    }


}
