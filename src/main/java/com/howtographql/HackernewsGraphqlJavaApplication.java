package com.howtographql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HackernewsGraphqlJavaApplication {

    public static void main(String[] args) {

        Object[] sources = {HackernewsGraphqlJavaApplication.class, "classpath:/com/howtographql/config/CustomConfig" +
                ".groovy"};
        SpringApplication.run(sources, args);
    }
}
