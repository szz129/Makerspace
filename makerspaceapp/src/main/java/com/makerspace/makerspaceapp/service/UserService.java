package com.makerspace.makerspaceapp.service;


import com.makerspace.makerspaceapp.model.user;
import com.makerspace.makerspaceapp.repository.user_repository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
private final user_repository userRepository;


public UserService(user_repository userRepository) {
this.userRepository = userRepository;
}


public user save(user u) {
return userRepository.save(u);
}


public List<user> getAll() { return userRepository.findAll(); }


public user getById(Long id) { return userRepository.findById(id).orElse(null); }
}