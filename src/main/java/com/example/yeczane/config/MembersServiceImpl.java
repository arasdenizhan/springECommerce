package com.example.yeczane.config;

import com.example.yeczane.model.Users;
import com.example.yeczane.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MembersServiceImpl implements MembersService{

    private final UserRepository userRepository;

    @Autowired
    public MembersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        User.UserBuilder userBuilder;
        if (user != null) {
            userBuilder = User.withUsername(username);
            userBuilder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            userBuilder.roles(String.valueOf(user.getRole()));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return null;
    }
}
