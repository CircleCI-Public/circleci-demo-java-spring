package com.circleci.demojavaspring.service;

import com.circleci.demojavaspring.model.Student;

import java.util.List;
import java.util.Optional;

public interface Student_Service {
	public boolean saveStudent(Student student);
	public List<Student> getStudents();
	public boolean deleteStudent(Student student);
	public List<Student> getStudentByID(Student student);
	public boolean updateStudent(Student student);
}
