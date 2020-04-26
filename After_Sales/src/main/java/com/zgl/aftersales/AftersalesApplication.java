package com.zgl.aftersales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AftersalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AftersalesApplication.class, args);
    }

}
