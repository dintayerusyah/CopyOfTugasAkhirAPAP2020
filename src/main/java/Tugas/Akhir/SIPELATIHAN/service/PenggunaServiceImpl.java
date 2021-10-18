package Tugas.Akhir.SIPELATIHAN.service;

import Tugas.Akhir.SIPELATIHAN.model.PenggunaModel;
import Tugas.Akhir.SIPELATIHAN.repository.PenggunaDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PenggunaServiceImpl implements PenggunaService {
    @Autowired
    private PenggunaDB userDb;

    @Override
    public PenggunaModel addAccount(PenggunaModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public PenggunaModel getPenggunaByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public List<PenggunaModel> getAllPengguna() {
        return userDb.findAll();
    }

    @Override
    public PenggunaModel getPenggunaById(String id) {
        return userDb.findByUuid(id);
    }
  

    /*@Override
    public String getStringOfUuid (String id) {
        String rawUuid = id;
        *//*String uuid = rawUuid.substring(0, 8) + rawUuid.substring(9, 13) + rawUuid.substring(14, 18) + rawUuid.substring(19, 23) + rawUuid.substring(24, 36);*//*
        return uuid;
    }*/


}
