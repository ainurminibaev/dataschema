package ru.ainurminibaev.db.config;

import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("ru.ainurminibaev.db.service")
public class CoreConfig {


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dyillyrst");
        config.put("api_key", "511627349758915");
        config.put("api_secret", "SN8r2susinjcnM3ZHGelyRHZpeQ");
        return new Cloudinary(config);
    }
}
