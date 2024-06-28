package com.junit.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("students/{id}")
	public Student getStudent(@PathVariable long id) {
		return studentService.getStudentById(id);
	}

	@ExceptionHandler(value = StudentNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	void studentNotFoundExceptionHandler() {
		System.out.println("Stuent not found");
	}
}
