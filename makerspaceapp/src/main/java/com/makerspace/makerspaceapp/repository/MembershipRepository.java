package com.makerspace.makerspaceapp.repository;

import com.makerspace.makerspaceapp.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByUser_UserId(Long userId);
    List<Membership> findByStatus(String status);


    // Custom JPQL: returns the active membership of a user
    @Query("SELECT m FROM Membership m WHERE m.user.userId = :userId AND m.status = 'ACTIVE'")
    Membership findActiveByUserId(@Param("userId") Long userId);
}

