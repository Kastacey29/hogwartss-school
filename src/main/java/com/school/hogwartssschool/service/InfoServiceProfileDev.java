package com.school.hogwartssschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Dev")
public class InfoServiceProfileDev {
    private Logger logger = LoggerFactory.getLogger(InfoServiceProfileTest.class);
    @Value("${server.port}")
    private String port;


    public String getPort() {
        logger.info("Вызван метод получения порта");
        return port;
    }
}
