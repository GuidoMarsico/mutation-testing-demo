package com.mutation.testing.demo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "config.publicaciones")
@Configuration
public class ConfigApp {

        private String path;

        public String getPath() {
                return path;
        }

        public void setPath(String path) {
                this.path = path;
        }
}
