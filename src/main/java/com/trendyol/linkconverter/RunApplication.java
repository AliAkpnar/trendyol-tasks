package com.trendyol.linkconverter;

import com.trendyol.linkconverter.service.UrlServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunApplication {
    private static final Logger logger = LoggerFactory.getLogger(RunApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class);

        logger.info("Started");
    }
}
