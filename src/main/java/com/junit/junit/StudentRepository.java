package com.junit.junit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student getStudentByName(String name);

	@Query(value = "SELECT AVG(grade) FROM Student WHERE active = TRUE")
	Double getAverageGradeOfActiveStudent();
}
