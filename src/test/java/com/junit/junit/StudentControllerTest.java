package com.junit.junit;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	@Test
	void getStudent_forSavedStudent() throws Exception {
		given(studentService.getStudentById(anyLong())).willReturn(new Student("Mike", true, 90));
		mockMvc.perform(get("/students/1")).andExpect(status().isOk());
	}

	@Test
	void getStudent_forMissingStudent() throws Exception {
		given(studentService.getStudentById(anyLong())).willThrow(StudentNotFoundException.class);
		mockMvc.perform(get("/students/1")).andExpect(status().isNotFound());
	}

}
