package com.mutation.testing.demo;

import com.mutation.testing.demo.config.ConfigApp;
import com.mutation.testing.demo.datasource.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ConfigApp.class)
@EnableConfigurationProperties
class MtApplicationTest {

    @Autowired
    ConfigApp config;

    @Test
    void testAlIniciarElDatasourceTieneUnaInstancia(){
        MtApplication app = new MtApplication(config);
        Assertions.assertNotNull(DataSource.getInstance(""));
    }



}