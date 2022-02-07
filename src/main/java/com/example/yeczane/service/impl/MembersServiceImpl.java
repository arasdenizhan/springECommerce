package com.example.yeczane.service.impl;

import com.example.yeczane.model.Users;
import com.example.yeczane.repository.UserRepository;
import com.example.yeczane.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MembersServiceImpl implements MembersService {

    private final UserRepository userRepository;
    private final ApplicationContext applicationContext;

    @Autowired
    public MembersServiceImpl(UserRepository userRepository, ApplicationContext applicationContext) {
        this.userRepository = userRepository;
        this.applicationContext = applicationContext;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        User.UserBuilder userBuilder;
        if (user != null) {
            userBuilder = User.withUsername(username);
            userBuilder.password(applicationContext.getBean(BCryptPasswordEncoder.class).encode(user.getPassword()));
            userBuilder.roles(String.valueOf(user.getRole()));
            return userBuilder.build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
