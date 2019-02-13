package de.coke.tik;

import com.github.collinalpert.java2db.database.DBConnection;
import com.github.collinalpert.java2db.utilities.IoC;
import de.coke.tik.entities.Data;
import de.coke.tik.entities.SystemParameter;
import de.coke.tik.entities.User;
import de.coke.tik.services.DataService;
import de.coke.tik.services.SystemParameterService;
import de.coke.tik.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TikApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(TikApplication.class, args);
		context.getBean(Instance.class).initialize();
	}

	@Bean
	Instance getInstance() {
		return new Instance();
	}

	private static class Instance {

		@Value("${database.host}")
		private String databaseHost;

		@Value("${database.name}")
		private String databaseName;

		@Value("${database.user}")
		private String databaseUser;

		@Value("${database.password}")
		private String databasePassword;

		public void initialize() {
			DBConnection.HOST = databaseHost;
			DBConnection.DATABASE = databaseName;
			DBConnection.USERNAME = databaseUser;
			DBConnection.PASSWORD = databasePassword;
			DBConnection.PORT = 3306;

			IoC.registerService(Data.class, new DataService());
			IoC.registerService(SystemParameter.class, new SystemParameterService());
			IoC.registerService(User.class, new UserService());
		}
	}
}
