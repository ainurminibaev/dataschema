package ru.ainurminibaev.db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import ru.ainurminibaev.db.model.Customer;
import ru.ainurminibaev.db.repository.CustomerRepository;

//@SpringBootApplication(scanBasePackages = "ru.ainurminibaev.db.config")
//@EnableAutoConfiguration(exclude = { EnableMongoRepositories.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
public class Application {

	public static void main(String[] args) throws SQLException {
//		ApplicationContext ctx = SpringApplication.run(Application.class, args);
//		CustomerRepository customerRepository = ctx.getBean(CustomerRepository.class);
		CustomerRepository customerRepository = null;
		Customer c = new Customer("df", "dsf");
		customerRepository.save(c);

//		Connection conn = ctx.getBean(Connection.class, "psql");
//		DatabaseMetaData metaData = conn.getMetaData();
		DatabaseMetaData metaData = null;

		String tableType[] = { "TABLE" };

		StringBuilder builder = new StringBuilder();

		ResultSet result = metaData.getTables(null, "public", null, tableType);
		while (result.next()) {
			String tableName = result.getString(3);

			builder.append(tableName + "( ");
			ResultSet columns = metaData.getColumns(null, null, tableName, null);

			while (columns.next()) {
				String columnName = columns.getString(4);
				builder.append(columnName);
				builder.append(",");
			}
			builder.deleteCharAt(builder.lastIndexOf(","));
			builder.append(" )");
			builder.append("\n");
			builder.append("----------------");
			builder.append("\n");

		}

		System.out.println(builder.toString());
	}

}