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
public class DemoApplication implements CommandLineRunner {


	private static final Log LOGGER = LogFactory.getLog(DemoApplication.class);



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);



	}

	@Override
	public void run(String... args) throws Exception {



	}
}
