package com.asimtechs.springsecurityjpa.services;

import com.asimtechs.springsecurityjpa.models.MyUserDetails;
import com.asimtechs.springsecurityjpa.models.User;
import com.asimtechs.springsecurityjpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       //return new MyUserDetails(s);
        Optional<User> user = userRepository.findByUserName(s);
        user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + s));
        return user.map(MyUserDetails::new).get();
    }
}
