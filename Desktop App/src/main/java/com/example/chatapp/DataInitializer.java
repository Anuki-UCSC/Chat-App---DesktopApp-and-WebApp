//package com.example.chatapp;
//import com.example.chatapp.model.entity.User;
//import com.example.chatapp.model.repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DataInitializer {
//
//    @Autowired
//    private UserRepo userRepo;
//    @Bean
//    public CommandLineRunner getCommandLineRunner(){
//        return args->{
//
//            User admin = new User();
//            admin.setUsername("Clara Johns");
//            admin.setName("Clara Johns");
//            admin.setRole(User.Role.Admin);
//            admin.setChat1(true);
//            admin.setChat2(true);
////            admin.setPassword("admin");
//            userRepo.save(admin);
//
//            User John = new User();
//            John.setUsername("Godfird Ronald");
//            John.setName("Godfrid Ronald");
//            John.setRole(User.Role.GeneralUser);
//            John.setChat1(false);
//            John.setChat2(false);
////            admin.setPassword("admin");
//            userRepo.save(John);
//
//            User Christina = new User();
//            Christina.setUsername("john");
//            Christina.setName("John");
//            Christina.setRole(User.Role.GeneralUser);
//            Christina.setChat1(false);
//            Christina.setChat2(false);
////            admin.setPassword("admin");
//            userRepo.save(Christina);
//        };
//    }
//}
