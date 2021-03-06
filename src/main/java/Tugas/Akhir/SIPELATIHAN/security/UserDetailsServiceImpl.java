package Tugas.Akhir.SIPELATIHAN.security;

import Tugas.Akhir.SIPELATIHAN.model.PenggunaModel;
import Tugas.Akhir.SIPELATIHAN.repository.PenggunaDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

//import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PenggunaDB userDb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PenggunaModel user = userDb.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRoleModel().getNama_role()));
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
