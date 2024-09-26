package com.college.service;

import com.college.model.StudentProfile;
import com.college.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public List<StudentProfile> findAllStudents() {
        return studentProfileRepository.findAll();
    }

    public Optional<StudentProfile> findStudentById(Long id) {
        return studentProfileRepository.findById(id);
    }

    public StudentProfile saveStudent(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

    public Optional<StudentProfile> updateStudent(Long id, StudentProfile studentProfile) {
        if (studentProfileRepository.existsById(id)) {
            studentProfile.setUserId(id);
            return Optional.of(studentProfileRepository.save(studentProfile));
        }
        return Optional.empty();
    }

    public boolean deleteStudent(Long id) {
        if (studentProfileRepository.existsById(id)) {
            studentProfileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
