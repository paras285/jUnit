package com.junit.junit;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class StudentSerivceTest {

	@MockBean
	private StudentRepository studentRepository;
	@Autowired
	private StudentService studentService;

	@Test
	void getStudentByIdMockito() {
		Long id = 123l;
		when(studentRepository.findById(id)).thenReturn(Optional.of(new Student("Mark")));
		studentService.getStudentById(id);
		studentService.getStudentById(id);
		studentService.getStudentById(id);
		studentService.getStudentById(id);
		verify(studentRepository, times(1)).findById(id);
	}

	@Test
	void getStudentByIdBDDMockito() {
		// Given
		Long id = 123l;
		given(studentRepository.findById(id)).willReturn(Optional.of(new Student("Mark")));
		// When
		studentService.getStudentById(id);
		studentService.getStudentById(id);
		studentService.getStudentById(id);
		studentService.getStudentById(id);
		// Then
		then(studentRepository).should(times(1)).findById(id);

	}

	@Test
	void getStudentById_WhenNotFound_ThrowException_Mockito() {

		long id = 1234l;
		assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(id));
	}

	@Test
	void getStudentById_WhenNotFound_ThrowException_BDDMockito() {

		long id = 1234l;
		Throwable throwable = catchThrowable(() -> studentService.getStudentById(id));
		BDDAssertions.then(throwable).isInstanceOf(StudentNotFoundException.class);
	}

}
