package Tugas.Akhir.SIPELATIHAN.service;

import Tugas.Akhir.SIPELATIHAN.model.RoleModel;
import Tugas.Akhir.SIPELATIHAN.repository.RoleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDB roleDB;

    @Override
    public List<RoleModel> getListRole(){
        List<RoleModel> listRole = roleDB.findAll();
        return listRole;
    }
}
