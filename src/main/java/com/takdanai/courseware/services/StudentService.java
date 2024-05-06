package com.takdanai.courseware.services;

import com.takdanai.courseware.entities.Student;
import com.takdanai.courseware.repositories.StudentRepository;
import com.takdanai.courseware.services.base.BaseService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService extends BaseService<StudentRepository, Student> implements UserDetailsService {

    protected StudentService(StudentRepository repository) {
        super(repository);
    }

    @Override
    public List<Student> all() {
        return super.all();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Student create(Student entity) {
        return super.create(entity);
    }

    @Override
    public Optional<Student> update(Long id, Student entity) {
        return super.update(id, entity);
    }

    @Override
    public Optional<Student> delete(Long id) {
        return super.delete(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Student Not Found"));


        return student;
    }
}
