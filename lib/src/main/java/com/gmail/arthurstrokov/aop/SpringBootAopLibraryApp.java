package com.gmail.arthurstrokov.aop;

import com.gmail.arthurstrokov.aop.service.FakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootAopLibraryApp {
    private final FakeService fakeService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAopLibraryApp.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> fakeService.someFakeMethod();
    }
}
