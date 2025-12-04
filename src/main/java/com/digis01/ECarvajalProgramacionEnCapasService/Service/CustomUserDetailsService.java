/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.ECarvajalProgramacionEnCapasService.Service;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.IUsuarioRepositoryDAO;
import com.digis01.ECarvajalProgramacionEnCapasService.JPA.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepositoryDAO usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioJPA usuario = usuarioRepository.findByUserName(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (usuario.Roll != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.Roll.getNombreRoll()));
        }
        
        return new User(
            usuario.getUserName(),
            usuario.getPassword(),
            true, true, true, true,
            authorities
        );
    }
    
    public Integer getUserIdByUsername(String username) {
        UsuarioJPA usuario = usuarioRepository.findByUserName(username);
        return usuario != null ? usuario.getIdUsuario() : null;
    }
}