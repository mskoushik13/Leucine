package com.college.controller;

import com.college.model.FacultyProfile;
import com.college.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public List<FacultyProfile> getAllFaculty() {
        return facultyService.findAllFaculty();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyProfile> getFacultyById(@PathVariable Long id) {
        return facultyService.findFacultyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FacultyProfile createFaculty(@RequestBody FacultyProfile facultyProfile) {
        return facultyService.saveFaculty(facultyProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyProfile> updateFaculty(@PathVariable Long id, @RequestBody FacultyProfile facultyProfile) {
        return facultyService.updateFaculty(id, facultyProfile)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        if (facultyService.deleteFaculty(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
