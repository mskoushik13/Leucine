package com.college.service;

import com.college.model.AdministratorProfile;
import com.college.repository.AdministratorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdministratorProfileRepository adminProfileRepository;

    public List<AdministratorProfile> findAllAdmins() {
        return adminProfileRepository.findAll();
    }

    public Optional<AdministratorProfile> findAdminById(Long id) {
        return adminProfileRepository.findById(id);
    }

    public AdministratorProfile saveAdmin(AdministratorProfile adminProfile) {
        return adminProfileRepository.save(adminProfile);
    }

    public Optional<AdministratorProfile> updateAdmin(Long id, AdministratorProfile adminProfile) {
        if (adminProfileRepository.existsById(id)) {
            adminProfile.setUserId(id);
            return Optional.of(adminProfileRepository.save(adminProfile));
        }
        return Optional.empty();
    }

    public boolean deleteAdmin(Long id) {
        if (adminProfileRepository.existsById(id)) {
            adminProfileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
