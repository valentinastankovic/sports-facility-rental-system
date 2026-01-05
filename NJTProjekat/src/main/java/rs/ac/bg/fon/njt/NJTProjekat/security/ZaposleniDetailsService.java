/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.NJTProjekat.security;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.NJTProjekat.entity.impl.Zaposleni;
import rs.ac.bg.fon.njt.NJTProjekat.repository.impl.ZaposleniRepository;

/**
 *
 * @author hallo
 */
@Service
public class ZaposleniDetailsService implements UserDetailsService {
    private final ZaposleniRepository zaposleniRepository;

    public ZaposleniDetailsService(ZaposleniRepository zaposleniRepository) {
        this.zaposleniRepository = zaposleniRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Zaposleni zaposleni = zaposleniRepository.findByUsername(username);

        if (zaposleni == null) {
            throw new UsernameNotFoundException("Zaposleni sa korisničkim imenom " + username + " nije pronađen.");
        }

        return new org.springframework.security.core.userdetails.User(
                zaposleni.getUsername(),
                zaposleni.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ZAPOSLENI"))
        );
    }
}
