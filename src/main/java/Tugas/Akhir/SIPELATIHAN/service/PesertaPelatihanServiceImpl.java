package Tugas.Akhir.SIPELATIHAN.service;

import Tugas.Akhir.SIPELATIHAN.model.PesertaPelatihanModel;
import Tugas.Akhir.SIPELATIHAN.repository.PesertaPelatihanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PesertaPelatihanServiceImpl implements PesertaPelatihanService {
    @Autowired
    PesertaPelatihanDB pesertaPelatihanDB;

    @Override
    public List<PesertaPelatihanModel> getAllPesertaPelatihan(){
        return pesertaPelatihanDB.findAll();
    }

    @Override
    public void savePesertaPelatihan(PesertaPelatihanModel pesertaPelatihan) {
        pesertaPelatihanDB.save(pesertaPelatihan);
    }
}
