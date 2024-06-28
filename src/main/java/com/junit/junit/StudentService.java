package com.junit.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Cacheable("students")
	public Student getStudentById(long id) throws StudentNotFoundException {
		return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
	}
}
