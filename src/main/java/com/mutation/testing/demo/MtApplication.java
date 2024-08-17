package com.mutation.testing.demo;

import com.mutation.testing.demo.config.ConfigApp;
import com.mutation.testing.demo.datasource.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mutation.testing.demo")
public class MtApplication {

	private final ConfigApp config;

	@Autowired
    public MtApplication(ConfigApp config) {
        this.config = config;
        DataSource.getInstance(config.getPath());
    }


    public static void main(String[] args) {
		SpringApplication.run(MtApplication.class, args);
	}

}
