package com.digis01.ECarvajalProgramacionEnCapasService.Service;

import com.digis01.ECarvajalProgramacionEnCapasService.DAO.IUsuarioRepositoryDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUsuarioRepositoryDAO userRepository;

    public CustomUserDetailsService(IUsuarioRepositoryDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return (UserDetails) userRepository.findByUserName(username);
                
    }
}