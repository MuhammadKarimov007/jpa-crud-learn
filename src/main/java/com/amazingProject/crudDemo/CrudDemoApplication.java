package com.amazingProject.crudDemo;

import com.amazingProject.crudDemo.dao.StudentDAO;
import com.amazingProject.crudDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> createMultipleStudents(studentDAO);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating new 5 students...");
		List<Student> newStudents = new ArrayList<>(List.of(
				new Student("Shermukhammad", "Karimov", "example@gmail.com"),
				new Student("Doniyor", "Azimboyeb", "example@gmail.com"),
				new Student("Jamshid", "Uralov", "example@gmail.com"),
				new Student("Shoh", "Jahonov", "example@gmail.com"),
				new Student("Nozima", "Alisherova", "example@gmail.com")
		));

		for (Student student : newStudents) {
			studentDAO.save(student);
		}
		System.out.println("All new students created successfully.");
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students...");
		int x = studentDAO.deleteAll();
		System.out.println(x + " rows have been deleted.");
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentToRemove = 2;
		System.out.println("Deleting student id " + studentToRemove);
		studentDAO.delete(studentToRemove);
	}

	private void updateStudent(StudentDAO studentDAO) {
		System.out.println("Getting the student with id of 1...");
		Student student = studentDAO.findById(1);

		System.out.println("Setting the last name to Bumblebee...");
		student.setLastName("Bumblebee");

		System.out.println("Update success: " + student);
		studentDAO.update(student);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Anderson");
		students.forEach(System.out::println);
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
		Student tempStudent = new Student("Jack", "Anderson", "paul@example.com");
		//save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);
		//display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
