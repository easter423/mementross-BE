package hello.hellospring2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "hello.hellospring2")
public class HelloSpring2Application {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpring2Application.class, args);
	}

}
