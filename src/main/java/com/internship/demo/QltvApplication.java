package com.internship.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class QltvApplication {

  public static void main(String[] args) {
    SpringApplication.run(QltvApplication.class, args);
  }
  
  /*
   * @Bean public WebMvcConfigurer corsConfigurer() { return new WebMvcConfigurer() {
   * 
   * @Override public void addCorsMappings(CorsRegistry registry) {
   * registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST",
   * "GET").allowCredentials(false).maxAge(3600); } }; }
   */

}
