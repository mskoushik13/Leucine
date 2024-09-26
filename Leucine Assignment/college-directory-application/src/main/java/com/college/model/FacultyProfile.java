package com.college.model;

import javax.persistence.*;

@Entity
@Table(name = "faculty_profiles")
public class FacultyProfile {
    @Id
    private Long userId; // Foreign Key

    private String photo;
    private Long departmentId; // Foreign Key
    private String officeHours;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
