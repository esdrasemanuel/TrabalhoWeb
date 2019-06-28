package com.ufc.br.security;

import com.ufc.br.model.Usuario;
import com.ufc.br.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class UserDetailsServiceImplemetacao implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return new User(usuario.getUsername(),usuario.getPassword(),true,true,true,true,usuario.getAuthorities());

    }

}
