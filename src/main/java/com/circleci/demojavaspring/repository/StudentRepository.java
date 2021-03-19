package com.circleci.demojavaspring.repository;

import com.circleci.demojavaspring.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Override
    List<Student> findAll();
}
