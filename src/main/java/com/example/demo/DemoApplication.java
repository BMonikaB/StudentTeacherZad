package com.example.demo;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.StudentServiceImpl;
import com.example.demo.service.StudentTeacherServiceImpl;
import com.example.demo.service.TeacherServiceImpl;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;


@SpringBootApplication
@Transactional
public class DemoApplication implements CommandLineRunner {


	private static final Log LOGGER = LogFactory.getLog(DemoApplication.class);


	@Autowired
	private StudentTeacherServiceImpl studentTeacherService;
	@Autowired
	private StudentServiceImpl studentService;
	@Autowired
	private TeacherServiceImpl teacherService;





	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);



	}

	@Override
	public void run(String... args) throws Exception {

		//Testowanie, potrzebne było mi do bazy h2

		Student student1 = new Student("Anna","Nowak",30,"annanowak@gmai.com","Matematyka");
		Student student2 = new Student("Zuznanna","Nowak",40,"zosianowak@gmai.com","Polski");
		Student student3 = new Student("ALa","Kot",35,"alakot@gmai.com","Matematyka");
		Student student4 = new Student("MArtyna","Nowak",40,"zosianowak@gmai.com","Polski");

		Teacher teacher1 = new Teacher("Matylda","Kowalska",70,"kowalskam@gmail.com","Matematyka");

		teacherService.addTeacher(teacher1);
		studentService.addStudent(student1);
		studentService.addStudent(student2);
		studentService.addStudent(student3);
		studentService.addStudent(student4);


		studentTeacherService.addStudentToTeacher(student1,teacher1);
		studentTeacherService.addStudentToTeacher(student2,teacher1);
		studentTeacherService.removeStudentFromTeacher(student1,teacher1);


	}
}
