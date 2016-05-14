package ru.ainurminibaev.db.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.ainurminibaev.db.repository.CustomerRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = CustomerRepository.class)
public class SpringMongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "db_schema_ui";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getMappingBasePackage() {
        return "ru.ainurminibaev.db.model";
    }
}