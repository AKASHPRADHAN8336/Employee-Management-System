package net.javaguides.departmentservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class DepartmentService1Application {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentService1Application.class, args);
	}

}
