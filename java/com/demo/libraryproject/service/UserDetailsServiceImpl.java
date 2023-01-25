package com.demo.libraryproject.service;

import com.demo.libraryproject.model.Clan;
import com.demo.libraryproject.repository.UserRepository;
import com.demo.libraryproject.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Clan clan = userRepository.findClanByUsername(username);

        if(clan == null){
            throw new UsernameNotFoundException("Clan " + username + " nije pronadjen.");
        }
        return new CustomUserDetails(clan);
    }
}
