package Tugas.Akhir.SIPELATIHAN.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .antMatchers("/scss/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/trainer/**").hasAnyAuthority("Staff Training")
                .antMatchers("/user/signup").hasAnyAuthority("Kepala Bagian")
                .antMatchers("/pelatihan/**").hasAnyAuthority("Kepala Departemen HR", "Kepala Bagian", "Staff Training")
                .antMatchers("/pelatihan/ubah-status/**").hasAnyAuthority("Kepala Departemen HR", "Kepala Bagian")
                .antMatchers("/peserta/**").hasAnyAuthority("Kepala Bagian", "Staff Training") 
                .antMatchers("/api/v1/pelatihan/laporanPeserta/**").hasAnyAuthority("Kepala Bagian")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                .and()
                .cors()
                .and()
                .csrf()
                .disable();;
    }

    @Bean
    public BCryptPasswordEncoder encoder(){return new BCryptPasswordEncoder();}

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
       auth.inMemoryAuthentication()
               .passwordEncoder(encoder())
               .withUser("dinta").password(encoder().encode("yerusyah"))
               .roles("USER");
   }

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
