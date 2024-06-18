package com.smilemall.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@MapperScan(basePackages = {"com.smilemall.basic.**"})
@SpringBootApplication(exclude= SecurityAutoConfiguration.class)
public class SmilemallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmilemallApplication.class, args);
	}

}
