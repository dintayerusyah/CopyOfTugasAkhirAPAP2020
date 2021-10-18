package Tugas.Akhir.SIPELATIHAN.service;

import Tugas.Akhir.SIPELATIHAN.model.JenisPelatihanModel;
import Tugas.Akhir.SIPELATIHAN.model.PesertaPelatihanModel;
import Tugas.Akhir.SIPELATIHAN.repository.JenisPelatihanDB;
import Tugas.Akhir.SIPELATIHAN.repository.PesertaPelatihanDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class JenisPelatihanServiceImpl implements JenisPelatihanService {
    @Autowired
    JenisPelatihanDB jenisPelatihanDB;

    @Override
    public List<JenisPelatihanModel> getAllJenisPelatihan() {
        return jenisPelatihanDB.findAll();
    }

    @Override 
    public JenisPelatihanModel getJenisPelatihanById(int id_pelatihan){
        return jenisPelatihanDB.findById(id_pelatihan);
    }
}
