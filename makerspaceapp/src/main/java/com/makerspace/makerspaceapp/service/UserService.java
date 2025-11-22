package com.makerspace.makerspaceapp.service;

import com.makerspace.makerspaceapp.model.user;
import com.makerspace.makerspaceapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public user save(user u) {
        return userRepository.save(u);
    }

    public List<user> getAll() {
        return userRepository.findAll();
    }

    public user getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}