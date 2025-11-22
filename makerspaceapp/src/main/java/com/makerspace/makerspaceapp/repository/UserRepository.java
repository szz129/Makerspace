package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {
    user findByEmail(String email);
    boolean existsByEmail(String email);
    List<user> findByRole(String role);
}