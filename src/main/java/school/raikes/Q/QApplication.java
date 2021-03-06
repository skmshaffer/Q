package school.raikes.Q;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import school.raikes.Q.utility.RandomUtility;

@SpringBootApplication
@EnableAutoConfiguration
public class QApplication {

	public static void main(String[] args) {
		SpringApplication.run(QApplication.class, args);
	}
}
