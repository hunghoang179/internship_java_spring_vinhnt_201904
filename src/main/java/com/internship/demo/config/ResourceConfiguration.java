package com.internship.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {

 private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
     {"classpath:/resources/", "classpath:/static/"};



 @Override
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
   registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
 }

 @Override
 public void addViewControllers(ViewControllerRegistry registry) {
   // Map "/"
   registry.addViewController("/").setViewName("client/home.html");

   // Map "/word", "/word/word", and "/word/word/word" - except for anything
   // starting with
   // "/api/..." or ending with
   // a file extension like ".js" - to index.html. By doing this, the client
   // receives and routes
   // the url. It also
   // allows client-side URLs to be bookmarked.

   // Single directory level - no need to exclude "api"
   registry.addViewController("/{x:[\\w\\-]+}").setViewName("client/home.html");
   // Multi-level directory path, need to exclude "api" on the first part of the
   // path
   registry.addViewController("/{x:^(?!api$).*$}/**/{y:[\\w\\-]+}").setViewName("client/home.html");
 }
}