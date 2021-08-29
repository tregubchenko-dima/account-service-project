package com.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class SecurityApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApp.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserService userService){
//        return args -> {
//            userService.addRole(new Role(null, "USER"));
//            userService.addRole(new Role(null, "ADMIN"));
//            userService.addRole(new Role(null, "MANAGER"));
//
//            userService.addUser(new User(null, "Dima Tregu", "dima", "1234", new HashSet<>()));
//            userService.addUser(new User(null, "Valera Grich", "valera", "4321", new HashSet<>()));
//            userService.addUser(new User(null, "Denis Frol", "denis", "0000", new HashSet<>()));
//
//            userService.addRoleToUser("valera", "USER");
//            userService.addRoleToUser("denis", "USER");
//            userService.addRoleToUser("denis", "MANAGER");
//            userService.addRoleToUser("dima", "ADMIN");
//            userService.addRoleToUser("dima", "USER");
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
