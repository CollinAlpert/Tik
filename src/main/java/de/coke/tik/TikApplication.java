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

@SpringBootApplication
public class TikApplication {

	@Value("${database.host}")
	private static String databaseHost;

	@Value("${database.name}")
	private static String databaseName;

	@Value("${database.user}")
	private static String databaseUser;

	@Value("${database.password}")
	private static String databasePassword;

	public static void main(String[] args) {
		SystemParameter.HOST = databaseHost;
		SystemParameter.DATABASE = databaseName;
		SystemParameter.USERNAME = databaseUser;
		SystemParameter.PASSWORD = databasePassword;
		SystemParameter.DATABASE_TYPE = DatabaseTypes.MYSQL;
		SystemParameter.PORT = 3306;

		IoC.registerService(Data.class, new DataService());
		IoC.registerService(de.coke.tik.entities.SystemParameter.class, new SystemParameterService());
		IoC.registerService(User.class, new UserService());
		SpringApplication.run(TikApplication.class, args);
	}
}
