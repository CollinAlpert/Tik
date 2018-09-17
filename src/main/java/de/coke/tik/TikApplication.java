package de.coke.tik;

import com.github.collinalpert.java2db.database.DatabaseTypes;
import com.github.collinalpert.java2db.utilities.IoC;
import com.github.collinalpert.java2db.utilities.SystemParameter;
import de.coke.tik.entities.Data;
import de.coke.tik.entities.User;
import de.coke.tik.services.DataService;
import de.coke.tik.services.SystemParameterService;
import de.coke.tik.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TikApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TikApplication.class, args);
		var instance = context.getBean(Instance.class);
		instance.initialize();
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
			SystemParameter.HOST = databaseHost;
			SystemParameter.DATABASE = databaseName;
			SystemParameter.USERNAME = databaseUser;
			SystemParameter.PASSWORD = databaseUser;
			SystemParameter.DATABASE_TYPE = DatabaseTypes.MYSQL;
			SystemParameter.PORT = 3306;

			IoC.registerService(Data.class, new DataService());
			IoC.registerService(de.coke.tik.entities.SystemParameter.class, new SystemParameterService());
			IoC.registerService(User.class, new UserService());
		}
	}
}
