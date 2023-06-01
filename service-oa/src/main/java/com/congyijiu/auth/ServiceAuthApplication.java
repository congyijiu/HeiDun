package com.congyijiu.auth;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author congyijiu
 * @create 2023-05-30-22:31
 */

@SpringBootApplication
@ComponentScan("com.congyijiu")
@MapperScan("com.congyijiu.*.mapper")
@ServletComponentScan(basePackages = "com.congyijiu.auth.filter")
public class ServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }

}