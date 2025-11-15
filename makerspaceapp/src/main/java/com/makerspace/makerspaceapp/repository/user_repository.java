package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface user_repository extends JpaRepository<user, Long> {
user findByEmail(String email);
}