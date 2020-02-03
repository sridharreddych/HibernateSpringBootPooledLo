package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            bookstoreService.save3Authors();
            bookstoreService.saveAuthorNative();            
        };
    }
}


/*
 * 
 * 
 * How To Generate Sequences Of Identifiers Via Hibernate pooled-lo Algorithm

Note: Rely on pooled-lo or pooled especially if, beside your application, external systems needs to insert rows in your tables. Don't rely on hi/lo since, in such cases, it may cause errors resulted from generating duplicated identifiers.

Description: This is a Spring Boot example of using the pooled-lo algorithm. The pooled-lo is an optimization of hi/lo similar with pooled. Only that, the strategy of this algorithm fetches from the database the current sequence value and use it as the in-memory lowest boundary identifier. The number of in-memory generated identifiers is equal to increment_size.

Key points:

use the SEQUENCE generator type (e.g., in PostgreSQL)
configure the pooled-lo algorithm as in Author.java entity
insert a few records via pooled-lo
insert a few records natively (this acts as an external system that relies on NEXTVAL('hilo_sequence') and is not aware of pooled-lo presence and/or behavior)
 * 
 */
