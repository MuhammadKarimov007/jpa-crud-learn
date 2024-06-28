package com.amazingProject.crudDemo;

import com.amazingProject.crudDemo.dao.StudentDAO;
import com.amazingProject.crudDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> queryForStudents(studentDAO);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> queriedStudents = studentDAO.findAll();

		for (Student student : queriedStudents) {
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		System.out.println("Creating the student...");
		Student student = new Student("Daffy", "Duck", "duck@example.com");

		//save the object
		System.out.println("Saving the student...");
		studentDAO.save(student);
		//display id of object
		System.out.println("Saved student. Generated id: " + student.getId());
		//retrieve object based on id
		System.out.println("Retrieving student with the id " + student.getId());
		//display object
		System.out.println("Found the student: " + studentDAO.findById(student.getId()));
	}

	private void createStudent(StudentDAO studentDAO) {
		//create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@example.com");
		//save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);
		//display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
