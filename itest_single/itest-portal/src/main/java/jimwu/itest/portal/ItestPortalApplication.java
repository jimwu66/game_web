package jimwu.itest.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jimwu.itest.portal.mapper")
public class ItestPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItestPortalApplication.class, args);
	}

}
