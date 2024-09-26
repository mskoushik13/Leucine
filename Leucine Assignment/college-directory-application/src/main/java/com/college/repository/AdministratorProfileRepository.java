package com.college.repository;

import com.college.model.AdministratorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorProfileRepository extends JpaRepository<AdministratorProfile, Long> {
}
