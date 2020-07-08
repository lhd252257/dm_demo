package com.bifangan.dmDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bifangan.dmDemo.common.UUIDUtils;
@MapperScan("com.bifangan.dmDemo")
@SpringBootApplication
@CrossOrigin("*")
public class DmDemoApplication {

	public static void main(String[] args) {
		System.out.println(UUIDUtils.getUUID36());
		//SpringApplication.run(DmDemoApplication.class, args);
	}

}
