package com.junit.junit;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void contextLoads() {
		// Given
		studentRepository.save(new Student("Mark"));
		// When
		Student student = studentRepository.getStudentByName("Mark");
		// then
		then(student.getId()).isNotNull();
		then(student.getName()).isNotBlank();

	}

	@Test
	void contextLoads1_challenge() {
		// Given
		Student mark = new Student("Mark", true, 80);
		Student susan = new Student("Susan", true, 100);
		Student peter = new Student("Peter", false, 50);

		List<Student> studentList = new ArrayList();
		studentList.add(mark);
		studentList.add(susan);
		studentList.add(peter);

		studentList.forEach(student -> studentRepository.save(student));

		Double score = studentRepository.getAverageGradeOfActiveStudent();
		then(score).isEqualTo(90);

	}
}
