package edu.poly.sprg.manager;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SpringJava5Application extends SpringBootServletInitializer
		implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringJava5Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJava5Application.class, args);
	}

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
	}
}
