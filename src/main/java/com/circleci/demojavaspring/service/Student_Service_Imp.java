package com.circleci.demojavaspring.service;

import com.circleci.demojavaspring.dao.Student_DAO;
import com.circleci.demojavaspring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Student_Service_Imp implements Student_Service {
 
	@Autowired
	private Student_DAO studentdao;
	
	@Override
	public boolean saveStudent(Student student) {
		return studentdao.saveStudent(student);
	}

	@Override
	public List<Student> getStudents() {
		return studentdao.getStudents();
	}

	@Override
	public boolean deleteStudent(Student student) {
		return studentdao.deleteStudent(student);
	}

	@Override
	public List<Student> getStudentByID(Student student) {
		Student studa = studentdao.getStudentByID(student).get();
		ArrayList<Student> studentArrayList = new ArrayList<>();
		studentArrayList.add(studa);
		return studentArrayList;
	}

	@Override
	public boolean updateStudent(Student student) {
		return studentdao.updateStudent(student);
	}

}
