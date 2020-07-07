package com.bifangan.dmDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.bifangan.dmDemo")
@SpringBootApplication
public class DmDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmDemoApplication.class, args);
	}

}
