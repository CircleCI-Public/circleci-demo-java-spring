package com.circleci.demojavaspring.dao;

import com.circleci.demojavaspring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.circleci.demojavaspring.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class Student_DAO_Imp  implements Student_DAO{

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public boolean saveStudent(Student student) {
		boolean status=false;
		try {
			studentRepository.save(student);
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Student> getStudents() {

		return studentRepository.findAll();
	}

	@Override
	public boolean deleteStudent(Student student) {
		boolean status=false;
		try {
			studentRepository.delete(student);
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Optional<Student> getStudentByID(Student student) {
		return studentRepository.findById(student.getStudent_id());
	}

	@Override
	public boolean updateStudent(Student student) {
		boolean status=false;
		try {
			studentRepository.save(student);
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
