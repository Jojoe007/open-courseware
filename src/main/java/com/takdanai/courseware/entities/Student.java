package com.takdanai.courseware.entities;

import com.takdanai.courseware.controllers.payload.requests.RegisterRequest;
import com.takdanai.courseware.entities.base.BaseEntity;
import com.takdanai.courseware.entities.enums.Gender;
import com.takdanai.courseware.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(indexes = {@Index(columnList = "username", unique = true)})
public class Student extends BaseEntity implements UserDetails, Serializable {

    public static final Integer MINIMUM_AGE = 8;

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;

    private Role role = Role.ROLE_USER;

    private String firstName;
    private String lastName;

    private LocalDate birthday;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @OneToOne
    private Storage.Image avatar;
    private String bio;

    public String fullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public static List<Gender> genders() {
        return List.of(Gender.values());
    }

    public static Student formRequest(RegisterRequest request, PasswordEncoder encoder) {
        Student student = new Student();
        student.username = request.getUsername();
        student.password = encoder.encode(request.getPassword());
        student.firstName = request.getFirstName();
        student.lastName = request.getLastName();
        student.birthday = request.getBirthday();
        student.gender = request.getGender();
        student.bio = request.getBio();

        return student;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}