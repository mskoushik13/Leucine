package com.college.service;

import com.college.model.FacultyProfile;
import com.college.repository.FacultyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    public List<FacultyProfile> findAllFaculty() {
        return facultyProfileRepository.findAll();
    }

    public Optional<FacultyProfile> findFacultyById(Long id) {
        return facultyProfileRepository.findById(id);
    }

    public FacultyProfile saveFaculty(FacultyProfile facultyProfile) {
        return facultyProfileRepository.save(facultyProfile);
    }

    public Optional<FacultyProfile> updateFaculty(Long id, FacultyProfile facultyProfile) {
        if (facultyProfileRepository.existsById(id)) {
            facultyProfile.setUserId(id);
            return Optional.of(facultyProfileRepository.save(facultyProfile));
        }
        return Optional.empty();
    }

    public boolean deleteFaculty(Long id) {
        if (facultyProfileRepository.existsById(id)) {
            facultyProfileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
