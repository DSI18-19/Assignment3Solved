package cat.tecnocampus;

import cat.tecnocampus.persistence.UserLabDAO;
import cat.tecnocampus.useCases.UserUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApplication {

	@Autowired
	private UserLabDAO userLabDAO;

	@Autowired
	private UserUseCases userUseCases;

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}
}
