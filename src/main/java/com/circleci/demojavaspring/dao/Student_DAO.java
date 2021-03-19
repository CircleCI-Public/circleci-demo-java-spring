package com.circleci.demojavaspring.dao;

import com.circleci.demojavaspring.model.Student;

import java.util.List;
import java.util.Optional;

public interface Student_DAO {

	public boolean saveStudent(Student student);
	public List<Student> getStudents();
	public boolean deleteStudent(Student student);
	public Optional<Student> getStudentByID(Student student);
	public boolean updateStudent(Student student);
}
