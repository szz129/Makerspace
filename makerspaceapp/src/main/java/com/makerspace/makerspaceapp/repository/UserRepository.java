package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    List<User> findByRole_Name(String roleName);
    List<User> findBySkillsContainingIgnoreCase(String skill);

}
