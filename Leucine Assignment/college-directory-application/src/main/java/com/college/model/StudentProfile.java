package com.college.model;

import javax.persistence.*;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {
    @Id
    private Long userId; // Foreign Key

    private String photo;
    private Long departmentId; // Foreign Key
    private String year;

    // Removed the user reference, since we're using userId as FK
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
