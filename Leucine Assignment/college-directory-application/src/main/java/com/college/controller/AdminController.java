package com.college.controller;

import com.college.model.AdministratorProfile;
import com.college.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<AdministratorProfile> getAllAdmins() {
        return adminService.findAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorProfile> getAdminById(@PathVariable Long id) {
        return adminService.findAdminById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AdministratorProfile createAdmin(@RequestBody AdministratorProfile adminProfile) {
        return adminService.saveAdmin(adminProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministratorProfile> updateAdmin(@PathVariable Long id, @RequestBody AdministratorProfile adminProfile) {
        return adminService.updateAdmin(id, adminProfile)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        if (adminService.deleteAdmin(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
